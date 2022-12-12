package tvClasses.pageClasses;

import com.fasterxml.jackson.databind.node.ArrayNode;
import inputClasses.ActionInput;
import movieClasses.Movie;
import tvClasses.TV;
import tvClasses.actionClasses.PageVisitor;

import java.util.ArrayList;

public class SeeDetailsPage extends Page{
    public SeeDetailsPage(String name, ArrayList<Movie> movies) {
        super(name, movies);
    }

    public SeeDetailsPage(){
        this.setName("SeeDetailsPage");
    }


    @Override
    public void accept(PageVisitor v, ActionInput actionInput, final TV tv) {
        v.visit(this, actionInput, tv);
    }
}
