package tvClasses.pageClasses;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import inputClasses.ActionInput;
import movieClasses.Movie;
import tvClasses.TV;
import tvClasses.actionClasses.PageVisitor;
import userClasses.User;

import java.util.ArrayList;

public class RegisterPage extends Page {
    public RegisterPage(String name, ArrayList<Movie> movies) {
        super(name, movies);
    }

    public RegisterPage(){
        this.setName("RegisterPage");
    }

    public void createOutput(final TV tv, User new_user) {
        ArrayNode output = tv.getOutput();
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.createObjectNode();
        node.set("error", objectMapper.convertValue(null, JsonNode.class));
        node.set("currentMoviesList", objectMapper.convertValue(this.getMoviesShown(), JsonNode.class));
        node.set("currentUser", objectMapper.convertValue(new_user, JsonNode.class));
        output.add(node);
    }



    @Override
    public void accept(PageVisitor v, ActionInput actionInput, final TV tv) {
        v.visit(this, actionInput, tv);
    }
}
