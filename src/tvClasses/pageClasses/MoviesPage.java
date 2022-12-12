package tvClasses.pageClasses;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import inputClasses.ActionInput;
import movieClasses.Movie;
import tvClasses.TV;
import tvClasses.actionClasses.PageVisitor;

import java.util.ArrayList;

public class MoviesPage extends Page {
    public MoviesPage(String name, ArrayList<Movie> movies) {
        super(name, movies);
    }

    public MoviesPage() {
        this.setName("MoviesPage");
    }

    public void createOutput(final TV tv, ArrayList<Movie> foundMovies){
        ArrayNode output = tv.getOutput();
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.createObjectNode();
        node.set("error", objectMapper.convertValue(null, JsonNode.class));
        node.set("currentMoviesList", objectMapper.convertValue(foundMovies, JsonNode.class));
        node.set("currentUser", objectMapper.convertValue(tv.getActiveUser(), JsonNode.class));

        output.add(node);
    }

    @Override
    public void accept(PageVisitor v, ActionInput actionInput, final TV tv) {
        v.visit(this, actionInput, tv);
    }
}
