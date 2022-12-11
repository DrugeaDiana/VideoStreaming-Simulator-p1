package tvClasses.pageClasses;

import com.fasterxml.jackson.databind.node.ArrayNode;
import movieClasses.Movie;

import java.util.ArrayList;

public class RegisterPage extends Page {
    public RegisterPage(String name, ArrayList<Movie> movies) {
        super(name, movies);
    }

    public RegisterPage(){
        this.setName("RegisterPage");
    }

    @Override
    public void createOutput(ArrayNode output) {

    }
}
