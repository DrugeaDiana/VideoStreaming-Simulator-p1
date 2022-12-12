package tvClasses.databasesClasses;

import inputClasses.ContainsInput;
import inputClasses.SortingInput;
import movieClasses.Movie;
import movieClasses.MovieComparatorDecr;
import userClasses.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    public Movie getMovieByName(String name) {
        for (Movie movie : movies) {
            if (movie.getName().equals(name)) {
                return movie;
            }
        }
        return null;
    }

    public ArrayList<Movie> searchMoviesName(String name) {
        ArrayList<Movie> foundMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getName().equals(name)) {
                foundMovies.add(movie);
            }
        }
        return foundMovies;
    }

    public ArrayList<Movie> filterBanned(User user) {
        ArrayList<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (!movie.getCountriesBanned().contains(user.getCredentials().getCountry())) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

    public ArrayList<Movie> sortedMovieList(SortingInput sortCrit, ArrayList<Movie> filtered) {

        String param1 = sortCrit.getRating();
        String param2 = sortCrit.getDuration();

        if (param1 != null && param2 != null) {
            if (param1.equals("decreasing") && param2.equals("decreasing")) {
                filtered.sort(((o1, o2) -> o1.getRating() == o2.getRating() ? o2.getDuration() - o1.getDuration() : (int) (o2.getRating() - o1.getRating())));
                //System.out.println(sortedMovies);
            } else if (param1.equals("increasing") && param2.equals("increasing")) {
                filtered.sort(((o1, o2) -> o1.getRating() == o2.getRating() ? o1.getDuration() - o2.getDuration() : (int) (o1.getRating() - o2.getRating())));
            } else if (param1.equals("increasing") && param2.equals("decreasing")) {
                filtered.sort(((o1, o2) -> o1.getRating() == o2.getRating() ? o2.getDuration() - o1.getDuration() : (int) (o1.getRating() - o2.getRating())));
            } else if (param1.equals("decreasing") && param2.equals("increasing")) {
                filtered.sort(((o1, o2) -> o1.getRating() == o2.getRating() ? o1.getDuration() - o2.getDuration() : (int) (o2.getRating() - o1.getRating())));
            }
        } else if (param2 == null && param1 != null) {
            if (param1.equals("decreasing")) {
                filtered.sort(((o1, o2) -> (int) (o2.getRating() - o1.getRating())));
            } else {
                filtered.sort(((o1, o2) -> (int) (o1.getRating() - o2.getRating())));
            }
        } else if (param2 != null) {
            if (param2.equals("decreasing")) {
                filtered.sort(((o1, o2) -> o2.getDuration() - o1.getDuration()));
            } else {
                filtered.sort((Comparator.comparingInt(Movie::getDuration)));
            }
        }
        return filtered;
    }

    public ArrayList<Movie> filterByContains (ContainsInput containsInput, ArrayList<Movie> sortedMovie) {
        ArrayList<String> actors = containsInput.getActors();
        ArrayList<String> genres = containsInput.getGenre();
        ArrayList<Movie> toBeRemovedAc = new ArrayList<>();
        ArrayList<Movie> toBeRemovedGen = new ArrayList<>();
        if (actors != null) {
            for (String actor : actors) {
                for (Movie movie : sortedMovie) {
                    if (!movie.getActors().contains(actor)) {
                        toBeRemovedAc.add(movie);
                    }
                }
            }
            sortedMovie.removeAll(toBeRemovedAc);
        }
        if (genres != null) {
            for (String genre : genres) {
                for (Movie movie : sortedMovie) {
                    if (!movie.getGenres().contains(genre)) {
                        toBeRemovedGen.add(movie);
                    }
                }
            }
            sortedMovie.removeAll(toBeRemovedGen);
        }
        return sortedMovie;
    }
}
