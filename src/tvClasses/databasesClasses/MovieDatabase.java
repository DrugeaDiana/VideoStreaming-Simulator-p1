package tvClasses.databasesClasses;

import movieClasses.Movie;

import java.util.ArrayList;

/**
 *  Singleton pattern
 */
public class MovieDatabase {
    @Override
    public String toString() {
        return "MovieDatabase{" +
                "movies=" + movies +
                '}';
    }

    private final static MovieDatabase instance = new MovieDatabase();

    private  MovieDatabase() {}

    public static MovieDatabase getInstance() {
        return instance;
    }

    private ArrayList<Movie> movies;

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
