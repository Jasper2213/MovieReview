package data;

import util.MovieException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static data.JDBCConnection.getConnection;

public class MySQLRepoImpl implements ReviewRepo, UserRepo {

    // LOGGING
    private static final Logger LOGGER = Logger.getLogger(MySQLRepoImpl.class.getName());
    private static final String FAILED_TO_RETRIEVE_USER_PASSWORD = "Failed to retrieve user password";
    private static final String FAILED_TO_ADD_USER = "Failed to add user";
    private static final String FAILED_TO_ADD_REVIEW = "Failed to add review";

    // SQL
    private static final String SELECT_USER = "SELECT * FROM users WHERE username = ?;";
    private static final String ADD_USER = "INSERT INTO users (username, password) VALUES (?, ?)";
    private static final String ADD_REVIEW = "INSERT INTO reviews (username, movie_id, review, score) VALUES (?, ?, ?, ?)";

    @Override
    public String findPassword(String username) {
        try ( Connection con = getConnection();
              PreparedStatement stmt = con.prepareStatement(SELECT_USER, Statement.RETURN_GENERATED_KEYS))
        {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    return rs.getString("password");

                throw new MovieException(FAILED_TO_RETRIEVE_USER_PASSWORD);
            }
        }
        catch (SQLException ex) {
            LOGGER.log(Level.INFO, FAILED_TO_RETRIEVE_USER_PASSWORD, ex);
            throw new MovieException(FAILED_TO_RETRIEVE_USER_PASSWORD);
        }
    }

    @Override
    public boolean addUser(String username, String password) {
        try (Connection con = getConnection();
        PreparedStatement stmt = con.prepareStatement(ADD_USER))
        {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.execute();
            return true;
        }
        catch (SQLException ex) {
            LOGGER.log(Level.INFO, FAILED_TO_ADD_USER, ex);
            throw new MovieException(FAILED_TO_ADD_USER);
        }
    }

    @Override
    public void addReview(String loggedInUser, int movieId, String review, int score) {
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(ADD_REVIEW))
        {
            stmt.setString(1, loggedInUser);
            stmt.setInt(2, movieId);
            stmt.setString(3, review);
            stmt.setInt(4, score);
            stmt.execute();
        }
        catch (SQLException ex) {
            LOGGER.log(Level.INFO, FAILED_TO_ADD_REVIEW, ex);
            throw new MovieException(FAILED_TO_ADD_REVIEW);
        }
    }
}
