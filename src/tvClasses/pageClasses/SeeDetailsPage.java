package tvClasses.pageClasses;

import com.fasterxml.jackson.databind.node.ArrayNode;
import movieClasses.Movie;

import java.util.ArrayList;

public class SeeDetailsPage extends Page{
    public SeeDetailsPage(String name, ArrayList<Movie> movies) {
        super(name, movies);
    }

    public SeeDetailsPage(){
        this.setName("SeeDetailsPage");
    }
    @Override
    public void createOutput(ArrayNode output) {

    }
}
