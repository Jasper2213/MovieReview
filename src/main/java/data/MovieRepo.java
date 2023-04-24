package data;

import domain.Movie;

import java.util.ArrayList;

public interface MovieRepo {
    ArrayList<Movie> getMovies(String query);
}
