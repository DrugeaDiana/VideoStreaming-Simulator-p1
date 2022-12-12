package tvClasses;

import com.fasterxml.jackson.databind.node.ArrayNode;
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
    private final ActionHandler actionHandler = new ActionHandler();
    private final InputData input;
    private final ArrayNode output;

    public TV(InputData input, ArrayNode output) {
        this.input = input;
        this.output = output;
    }
    public void initialize() {
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

    public void mainLoop() {
        for(ActionInput actionInput : input.getActions()) {
            //System.out.println(actionInput.getType());
            actionHandler.actionParsing(actionInput, this);
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

    public InputData getInput(){
        return input;
    }

    public ArrayNode getOutput() {
        return output;
    }

    public HomepageNon getHomepageNon() {
        return homepageNon;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public LogoutPage getLogoutPage() {
        return logoutPage;
    }

    public MoviesPage getMoviesPage() {
        return moviesPage;
    }

    public RegisterPage getRegisterPage() {
        return registerPage;
    }

    public SeeDetailsPage getSeeDetailsPage() {
        return seeDetailsPage;
    }

    public UpgradesPage getUpgradesPage() {
        return upgradesPage;
    }

    public HomepageAuth getHomepageAuth() {
        return homepageAuth;
    }
}


