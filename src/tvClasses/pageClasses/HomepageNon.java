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

public class HomepageNon extends Page{
    public HomepageNon(String name, ArrayList<Movie> movies) {
        super(name, movies);
    }

    public HomepageNon() {
        this.setName("HomepageNon");
    }


    @Override
    public void accept(PageVisitor v, ActionInput actionInput, final TV tv) {
        v.visit(this, actionInput, tv);
    }
}
