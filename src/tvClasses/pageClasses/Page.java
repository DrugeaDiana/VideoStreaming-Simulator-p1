package tvClasses.pageClasses;

import com.fasterxml.jackson.databind.node.ArrayNode;
import movieClasses.Movie;

import java.util.ArrayList;

public abstract class Page {
    private String name;
    private ArrayList<Movie> moviesShown;

    public Page() {}

    public Page(String name, ArrayList<Movie> movies) {
        this.name = name;
        this.moviesShown = movies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Movie> getMoviesShown() {
        return moviesShown;
    }

    public void setMoviesShown(ArrayList<Movie> moviesShown) {
        this.moviesShown = moviesShown;
    }

    public abstract void createOutput(ArrayNode output);
}
