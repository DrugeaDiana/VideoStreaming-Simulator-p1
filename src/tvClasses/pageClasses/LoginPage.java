package tvClasses.pageClasses;

import com.fasterxml.jackson.databind.node.ArrayNode;
import movieClasses.Movie;

import java.util.ArrayList;

public class LoginPage extends Page {
    public LoginPage(String name, ArrayList<Movie> movies) {
        super(name, movies);
    }

    public LoginPage(){
        this.setName("LoginPage");
    }

    @Override
    public void createOutput(ArrayNode output) {

    }
}
