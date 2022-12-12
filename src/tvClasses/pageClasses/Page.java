package tvClasses.pageClasses;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import movieClasses.Movie;
import tvClasses.TV;
import tvClasses.actionClasses.Visitable;

import java.util.ArrayList;

public abstract class Page implements Visitable {
    private String name;
    private ArrayList<Movie> moviesShown = new ArrayList<>();

    public Page() {}

    public Page(final String name, final ArrayList<Movie> movies) {
        this.name = name;
        this.moviesShown = movies;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ArrayList<Movie> getMoviesShown() {
        return moviesShown;
    }

    public void setMoviesShown(final ArrayList<Movie> moviesShown) {
        this.moviesShown = moviesShown;
    }

    public void createOutput(final TV tv){
        ArrayNode output = tv.getOutput();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.createObjectNode();
        node.set("error", objectMapper.convertValue(null, JsonNode.class));
        node.set("currentMoviesList", objectMapper.convertValue(this.getMoviesShown(), JsonNode.class));
        node.set("currentUser", objectMapper.convertValue(tv.getActiveUser(), JsonNode.class));

        output.add(node);
    }

    public void createOutputError(final TV tv) {
        ArrayNode output = tv.getOutput();
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.createObjectNode();
        ArrayList<Movie> notMovie = new ArrayList<>();
        node.put("error", "Error");
        node.set("currentMoviesList", objectMapper.convertValue(notMovie, JsonNode.class));
        node.set("currentUser", objectMapper.convertValue(null, JsonNode.class));
        //node.put("currentPage", this.name);
        output.add(node);
    }
}
