package tvClasses.pageClasses;

import com.fasterxml.jackson.databind.node.ArrayNode;
import movieClasses.Movie;

import java.util.ArrayList;

public class HomepageNon extends Page{
    public HomepageNon(String name, ArrayList<Movie> movies) {
        super(name, movies);
    }

    public HomepageNon() {
        this.setName("HomepageNon");
    }
    @Override
    public void createOutput(ArrayNode output) {

    }
}
