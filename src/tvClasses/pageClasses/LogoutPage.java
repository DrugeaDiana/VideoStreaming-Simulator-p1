package tvClasses.pageClasses;

import com.fasterxml.jackson.databind.node.ArrayNode;
import movieClasses.Movie;

import java.util.ArrayList;

public class LogoutPage extends Page {
    public LogoutPage(String name, ArrayList<Movie> movies) {
        super(name, movies);
    }

    public LogoutPage(){
        this.setName("LogoutPage");
    }
    @Override
    public void createOutput(ArrayNode output) {

    }
}
