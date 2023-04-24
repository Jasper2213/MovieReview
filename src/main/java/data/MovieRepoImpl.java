package data;

import domain.Movie;
import messages.ErrorMessage;
import messages.Message;
import messages.MovieResultMessage;
import messages.MovieSearchMessage;
import util.MovieException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieRepoImpl implements MovieRepo {

    private static final Logger LOGGER = Logger.getLogger(MovieRepoImpl.class.getName());

    @Override
    public ArrayList<Movie> getMovies(String query) {
        try (Socket socket = new Socket("192.168.32.32", 12345) ){
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(new MovieSearchMessage(query));
            Message response = (Message) in.readObject();

            if (response instanceof MovieResultMessage) {
                return ((MovieResultMessage) response).getResults();
            }
            else {
                LOGGER.log(Level.INFO, "Server failed: {0}", ((ErrorMessage)response).getMessage());
                throw new MovieException( ((ErrorMessage)response).getMessage());
            }


        } catch (IOException | ClassNotFoundException ex) {
            LOGGER.log(Level.INFO, "Failed to get movies", ex);
            throw new MovieException("Failed to get movies");
        }
    }
}
