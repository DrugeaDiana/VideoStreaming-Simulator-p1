package tvClasses.actionClasses;

import inputClasses.ActionInput;
import inputClasses.FiltersInput;
import inputClasses.InputData;
import movieClasses.Movie;
import tvClasses.TV;
import tvClasses.databasesClasses.MovieDatabase;
import tvClasses.databasesClasses.UsersDatabase;
import tvClasses.pageClasses.*;
import userClasses.Credentials;
import userClasses.User;

import java.util.ArrayList;

public class OnPageVisitor implements PageVisitor {

    @Override
    public void visit(LoginPage loginPage, ActionInput actionInput, final TV tv) {
        Credentials credentials = new Credentials(actionInput.getCredentials());
        if (UsersDatabase.getInstance().checkExistingUser(credentials)) {
            User user = UsersDatabase.getInstance().getSpecificUser(credentials);
            //System.out.println("am gasit pe cineva " + credentials.getName());
            //System.out.println("parola e " + credentials.getPassword());
            if (user.getCredentials().checkPassword(credentials)) {
                tv.setActivePage(tv.getHomepageAuth());
                //loginPage.setMoviesShown(MovieDatabase.getInstance().getMovies());
                tv.setActiveUser(user);
                loginPage.createOutput(tv);
            } else {
               // System.out.println("wrong pass my guy :P");
                loginPage.createOutputError(tv);
                tv.setActivePage(tv.getHomepageNon());
            }
            //System.out.println("neato burrito");
        } else {
            loginPage.createOutputError(tv);
            //System.out.println("wrong user my boy");
            tv.setActivePage(tv.getHomepageNon());
        }
    }

    @Override
    public void visit(RegisterPage registerPage, ActionInput actionInput, final TV tv) {
        Credentials credentials = new Credentials(actionInput.getCredentials());
        User user = new User();
        user.setCredentials(credentials);
        if (UsersDatabase.getInstance().checkExistingUser(credentials)) {
            registerPage.createOutputError(tv);
            tv.setActivePage(tv.getHomepageNon());
            return;
        }
        UsersDatabase.getInstance().addUser(user);
        registerPage.createOutput(tv, user);
        tv.setActiveUser(user);
        tv.setActivePage(tv.getHomepageAuth());
    }

    @Override
    public void visit(MoviesPage moviesPage, ActionInput actionInput, final TV tv) {
        String command = actionInput.getFeature();
        if (command.equals("search")) {
            String movieName = actionInput.getStartsWith();
            ArrayList<Movie> foundMovies = MovieDatabase.getInstance().searchMoviesName(movieName);
            moviesPage.createOutput(tv, foundMovies);
        } else if (command.equals("filter")) {
            FiltersInput filter = actionInput.getFilters();
            ArrayList<Movie> sortedMovies = moviesPage.getMoviesShown();
            if (filter.getSort() != null) {
                sortedMovies = MovieDatabase.getInstance().sortedMovieList(filter.getSort(),sortedMovies);
            }
            if (filter.getContains() != null) {
                sortedMovies = MovieDatabase.getInstance().filterByContains(filter.getContains(), sortedMovies);
            }
            moviesPage.setMoviesShown(sortedMovies);
            moviesPage.createOutput(tv);
        } else {
            moviesPage.createOutputError(tv);
        }
    }

    @Override
    public void visit(SeeDetailsPage seeDetailsPage, ActionInput actionInput, final TV tv) {
        String command = actionInput.getFeature();
        Movie auxMovie;
        switch (command) {
            case "purchase" -> {
                auxMovie = MovieDatabase.getInstance().getMovieByName(actionInput.getMovie());
                if (auxMovie == null) {
                    seeDetailsPage.createOutputError(tv);
                    return;
                }
                if (tv.getActiveUser().getPurchasedMovies().contains(auxMovie)) {
                    seeDetailsPage.createOutputError(tv);
                    return;
                }
                if (tv.getActiveUser().getNumFreePremiumMovies() > 0) {
                    tv.getActiveUser().setNumFreePremiumMovies(tv.getActiveUser().getNumFreePremiumMovies() - 1);
                    tv.getActiveUser().getPurchasedMovies().add(auxMovie);
                    seeDetailsPage.createOutput(tv);
                } else {
                    if (tv.getActiveUser().getTokensCount() > 2) {
                        tv.getActiveUser().setTokensCount(tv.getActiveUser().getTokensCount() - 2);
                        tv.getActiveUser().getPurchasedMovies().add(auxMovie);
                        seeDetailsPage.createOutput(tv);
                    } else {
                        seeDetailsPage.createOutputError(tv);
                    }
                }
            }
            case "watch" -> {
                auxMovie = MovieDatabase.getInstance().getMovieByName(actionInput.getMovie());
                if (auxMovie == null) {
                    seeDetailsPage.createOutputError(tv);
                    return;
                }
                if (tv.getActiveUser().getPurchasedMovies().contains(auxMovie)) {
                    tv.getActiveUser().getWatchedMovies().add(auxMovie);
                    seeDetailsPage.createOutput(tv);
                } else {
                    seeDetailsPage.createOutputError(tv);
                }
            }
            case "like" -> {
                auxMovie = MovieDatabase.getInstance().getMovieByName(actionInput.getMovie());
                if (auxMovie == null) {
                    seeDetailsPage.createOutputError(tv);
                    return;
                }
                if (tv.getActiveUser().getWatchedMovies().contains(auxMovie)) {
                    tv.getActiveUser().getLikedMovies().add(auxMovie);
                    auxMovie.setNumLikes(auxMovie.getNumLikes() + 1);
                    seeDetailsPage.createOutput(tv);
                } else {
                    seeDetailsPage.createOutputError(tv);
                }
            }
            case "rate" -> {
                auxMovie = MovieDatabase.getInstance().getMovieByName(actionInput.getMovie());
                if (auxMovie == null) {
                    seeDetailsPage.createOutputError(tv);
                    return;
                }
                if (tv.getActiveUser().getWatchedMovies().contains(auxMovie)) {
                    double rate = actionInput.getRate();
                    double totalRate = auxMovie.getTotalRatings();
                    totalRate = totalRate + rate;
                    int nr = auxMovie.getNumRatings();
                    nr = nr + 1;
                    rate = totalRate / nr;
                    auxMovie.setRating(rate);
                    auxMovie.setTotalRatings(totalRate);
                    auxMovie.setNumRatings(nr);
                    seeDetailsPage.createOutput(tv);
                } else {
                    seeDetailsPage.createOutputError(tv);
                }
            }
            default -> seeDetailsPage.createOutputError(tv);
        }
    }

    @Override
    public void visit(UpgradesPage upgradesPage, ActionInput actionInput, final TV tv) {
        String command = actionInput.getFeature();
        User activeUser = tv.getActiveUser();
        if (command.equals("buy tokens")) {
            int nr = actionInput.getCount();
            if (activeUser.getCredentials().getBalanceNr() > nr) {
                activeUser.setTokensCount(activeUser.getTokensCount() + nr);
                activeUser.getCredentials().setBalanceNr(activeUser.getCredentials().getBalanceNr() - nr);
            } else {
                upgradesPage.createOutputError(tv);
            }
        } else if (command.equals("buy premium account")) {
            if (activeUser.getTokensCount() > 10) {
                activeUser.setTokensCount(activeUser.getTokensCount() - 10);
                activeUser.getCredentials().setAccountType("premium");
            } else {
                upgradesPage.createOutputError(tv);
            }
        } else {
            upgradesPage.createOutputError(tv);
        }
    }

    @Override
    public void visit(LogoutPage logoutPage, ActionInput actionInput, final TV tv) {
        System.out.println("hewwo 6");
    }

    @Override
    public void visit(HomepageAuth homepageAuth, ActionInput actionInput, final TV tv) {
        homepageAuth.createOutputError(tv);
    }

    @Override
    public void visit(HomepageNon homepageNon, ActionInput actionInput, final TV tv) {
        homepageNon.createOutputError(tv);
    }
}
