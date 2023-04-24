package messages;


import domain.Movie;

import java.util.ArrayList;

public class MovieResultMessage extends Message {

    private final ArrayList<Movie> results;

    public MovieResultMessage(ArrayList<Movie> results) {
        this.results = results;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "MovieResultMessage{" +
                "results=" + results +
                '}';
    }
}
