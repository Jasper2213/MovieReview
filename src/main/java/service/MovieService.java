package service;

import data.MovieRepo;
import data.Repos;
import data.ReviewRepo;
import data.UserRepo;
import domain.Movie;
import util.Crypto;
import util.MovieException;

import java.util.List;

public class MovieService {

    private final MovieRepo movies;
    private final ReviewRepo reviews;
    private final UserRepo users;

    private String loggedInUser;

    public MovieService() {
        this(
                Repos.MOVIES,
                Repos.REVIEWS,
                Repos.USERS
        );
    }

    public MovieService(MovieRepo movieRepo, ReviewRepo reviewRepo, UserRepo userRepo) {
        this.movies = movieRepo;
        this.reviews = reviewRepo;
        this.users = userRepo;
    }

    public boolean login(String username, String password) {
        try {
            boolean ok =  Crypto.getInstance().comparePasswords(
                    password,
                    users.findPassword(username));

            if (ok) {
                loggedInUser = username;
            }

            return ok;

        } catch (MovieException ex) {
            return false;
        }
    }

    public boolean register(String usernameText, String password) {
        try {
            boolean ok =  users.addUser(usernameText, Crypto.getInstance().hash(password));
            if (ok) {
                loggedInUser = usernameText;
            }
            return ok;
        }
        catch (MovieException ex) {
            return false;
        }
    }

    public List<Movie> getMovies(String query) {
        return movies.getMovies(query);
    }

    public void addReview(int movieId, String review, int score) {
        reviews.addReview(loggedInUser, movieId, review, score);
    }
}
