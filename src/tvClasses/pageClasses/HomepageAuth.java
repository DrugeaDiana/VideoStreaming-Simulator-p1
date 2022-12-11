package tvClasses.pageClasses;

import com.fasterxml.jackson.databind.node.ArrayNode;
import movieClasses.Movie;

import java.util.ArrayList;

public class HomepageAuth extends Page {
    public HomepageAuth(String name, ArrayList<Movie> movies) {
        super(name, movies);
    }

    public HomepageAuth() {
        this.setName("HomepageAuth");
    }
    @Override
    public void createOutput(ArrayNode output) {

    }
}
