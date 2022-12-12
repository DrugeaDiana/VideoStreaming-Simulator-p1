package tvClasses.pageClasses;

import com.fasterxml.jackson.databind.node.ArrayNode;
import inputClasses.ActionInput;
import movieClasses.Movie;
import tvClasses.TV;
import tvClasses.actionClasses.PageVisitor;

import java.util.ArrayList;

public class LogoutPage extends Page {
    public LogoutPage(String name, ArrayList<Movie> movies) {
        super(name, movies);
    }

    public LogoutPage(){
        this.setName("LogoutPage");
    }


    @Override
    public void accept(PageVisitor v, ActionInput actionInput, final TV tv) {
        v.visit(this, actionInput, tv);
    }
}
