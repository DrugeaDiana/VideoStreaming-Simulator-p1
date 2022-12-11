package tvClasses.pageClasses;

import com.fasterxml.jackson.databind.node.ArrayNode;
import movieClasses.Movie;

import java.util.ArrayList;

public class MoviesPage extends Page {
    public MoviesPage(String name, ArrayList<Movie> movies) {
        super(name, movies);
    }

    public MoviesPage() {
        this.setName("MoviesPage");
    }
    @Override
    public void createOutput(ArrayNode output) {

    }
}
