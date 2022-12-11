package tvClasses.pageClasses;

import com.fasterxml.jackson.databind.node.ArrayNode;
import movieClasses.Movie;

import java.util.ArrayList;

public class UpgradesPage extends Page {
    public UpgradesPage(String name, ArrayList<Movie> movies) {
        super(name, movies);
    }

    public UpgradesPage() {
        this.setName("UpgradesPage");
    }

    @Override
    public void createOutput(ArrayNode output) {

    }
}
