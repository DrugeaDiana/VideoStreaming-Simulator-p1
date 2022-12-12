package tvClasses.actionClasses;

import inputClasses.ActionInput;
import inputClasses.InputData;
import movieClasses.Movie;
import tvClasses.TV;
import tvClasses.databasesClasses.MovieDatabase;
import tvClasses.pageClasses.*;

import java.util.ArrayList;

public class ChangePageVisitor implements PageVisitor {

    @Override
    public void visit(LoginPage loginPage, ActionInput actionInput, final TV tv) {
        loginPage.createOutputError(tv);
    }

    @Override
    public void visit(RegisterPage registerPage, ActionInput actionInput, final TV tv) {
        registerPage.createOutputError(tv);
    }

    @Override
    public void visit(MoviesPage moviesPage, ActionInput actionInput, final TV tv) {
        String page = actionInput.getPage();
        switch (page) {
            case "homepage" -> tv.setActivePage(tv.getHomepageAuth());
            case "see details" -> {
                if (actionInput.getMovie() != null) {
                   // System.out.println(actionInput.getMovie());
                    Movie movie = MovieDatabase.getInstance().getMovieByName(actionInput.getMovie());

                    if (movie != null) {
                        //System.out.println(moviesPage.getMoviesShown());
                        ArrayList<Movie> moviesDetails = new ArrayList<>();
                        //System.out.println(movie);
                        if (moviesPage.getMoviesShown().contains(movie)) {
                            moviesDetails.add(movie);
                            tv.getSeeDetailsPage().setMoviesShown(moviesDetails);
                            tv.setActivePage(tv.getSeeDetailsPage());
                            tv.getSeeDetailsPage().createOutput(tv);
                        } else {
                            tv.getSeeDetailsPage().createOutputError(tv);
                            //tv.setActivePage(tv.getHomepageAuth());
                        }
                    } else {
                        tv.getSeeDetailsPage().createOutputError(tv);
                        //tv.setActivePage(tv.getHomepageAuth());
                    }
                }

            }
            case "logout" -> {
                tv.setActiveUser(null);
                tv.setActivePage(tv.getHomepageNon());
            }
            default -> {
                moviesPage.createOutputError(tv);
                tv.setActivePage(tv.getHomepageAuth());
            }
        }
    }

    @Override
    public void visit(SeeDetailsPage seeDetailsPage, ActionInput actionInput, final TV tv) {
        String page = actionInput.getPage();
        switch (page) {
            case "movies" -> {
                tv.setActivePage(tv.getMoviesPage());
                tv.getMoviesPage().createOutput(tv);
            }
            case "upgrades" -> tv.setActivePage(tv.getUpgradesPage());
            case "logout" -> {
                tv.setActivePage(tv.getHomepageNon());
                tv.setActiveUser(null);
            }
            default -> seeDetailsPage.createOutputError(tv);
        }
    }

    @Override
    public void visit(UpgradesPage upgradesPage, ActionInput actionInput, final TV tv) {
        String page = actionInput.getPage();
        if (page.equals("movies")) {
            tv.setActivePage(tv.getMoviesPage());
            tv.getMoviesPage().createOutput(tv);
        } else {
            upgradesPage.createOutputError(tv);
        }
    }

    @Override
    public void visit(LogoutPage logoutPage, ActionInput actionInput, final TV tv) {
        System.out.println("hewwo 13");
    }

    @Override
    public void visit(HomepageAuth homepageAuth, ActionInput actionInput, final TV tv) {
        String page = actionInput.getPage();
        switch (page) {
            case "movies" -> {
                tv.getMoviesPage().setMoviesShown(MovieDatabase.getInstance().filterBanned(tv.getActiveUser()));
                tv.setActivePage(tv.getMoviesPage());
                tv.getMoviesPage().createOutput(tv);
            }
            case "upgrades" -> tv.setActivePage(tv.getUpgradesPage());
            case "logout" -> {
                tv.setActiveUser(null);
                tv.setActivePage(tv.getHomepageNon());
            }
            default -> {
                tv.setActivePage(tv.getHomepageAuth());
                //tv.setActiveUser(null);
                homepageAuth.createOutputError(tv);
            }
        }
    }

    @Override
    public void visit(HomepageNon homepageNon, ActionInput actionInput, final TV tv) {
        String page = actionInput.getPage();
        if (page.equals("login")) {
            tv.setActivePage(tv.getLoginPage());
            //homepageNon.createOutput(tv);
        } else if (page.equals("register")) {
            tv.setActivePage(tv.getRegisterPage());
            //homepageNon.createOutput(tv);
        } else {
            homepageNon.createOutputError(tv);
        }
    }
}
