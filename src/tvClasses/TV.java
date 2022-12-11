package tvClasses;

import inputClasses.ActionInput;
import inputClasses.InputData;
import inputClasses.MovieInput;
import inputClasses.UserInput;
import movieClasses.Movie;
import tvClasses.actionClasses.ActionHandler;
import tvClasses.databasesClasses.MovieDatabase;
import tvClasses.databasesClasses.UsersDatabase;
import tvClasses.pageClasses.*;
import userClasses.User;

import java.util.ArrayList;

public class TV {
    private ArrayList<Page> pages;
    private User activeUser;
    private Page activePage;
    //private ArrayList<Action> actions;
    private final HomepageAuth homepageAuth = new HomepageAuth();
    private final HomepageNon homepageNon = new HomepageNon();
    private final LoginPage loginPage = new LoginPage();
    private final LogoutPage logoutPage = new LogoutPage();
    private final MoviesPage moviesPage = new MoviesPage();
    private final RegisterPage registerPage = new RegisterPage();
    private final SeeDetailsPage seeDetailsPage = new SeeDetailsPage();
    private final UpgradesPage upgradesPage = new UpgradesPage();

    public void initialize(InputData input) {
        ArrayList<User> users = new ArrayList<>();
        for (UserInput user : input.getUsers()){
            users.add(new User(user));
        }
        UsersDatabase.getInstance().setUsers(users);

        ArrayList<Movie> movies = new ArrayList<>();
        for (MovieInput movie : input.getMovies()) {
            movies.add(new Movie(movie));
        }
        MovieDatabase.getInstance().setMovies(movies);

        activeUser = null;
        activePage = homepageNon;
    }

    public void mainLoop(InputData inputData) {
        ActionHandler actionHandler = new ActionHandler();
        for(ActionInput actionInput : inputData.getActions()) {
            actionHandler.actionParsing(actionInput, activePage);
        }
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public Page getActivePage() {
        return activePage;
    }

    public void setActivePage(Page activePage) {
        this.activePage = activePage;
    }
}


