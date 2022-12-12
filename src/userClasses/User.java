package userClasses;

import inputClasses.CredentialsInput;
import inputClasses.UserInput;
import movieClasses.Movie;

import java.util.ArrayList;

public class User {
    private Credentials credentials;

    private int tokensCount;
    private int numFreePremiumMovies;
    private ArrayList<Movie> purchasedMovies = new ArrayList<>();
    private ArrayList<Movie> watchedMovies = new ArrayList<>();
    private ArrayList<Movie> likedMovies = new ArrayList<>();
    private ArrayList<Movie> ratedMovies = new ArrayList<>();

    public User(final UserInput userInput) {
        credentials = new Credentials(userInput.getCredentials());
        tokensCount = 0;
        numFreePremiumMovies = 15;
    }

    public User() {
        tokensCount = 0;
        numFreePremiumMovies = 15;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public void setCredentialsInput(final CredentialsInput credentials) {
        this.credentials.setName(credentials.getName());
        this.credentials.setBalanceNr(credentials.getBalance());
        this.credentials.setCountry(credentials.getCountry());
        this.credentials.setPassword(credentials.getPassword());
        this.credentials.setAccountType(credentials.getAccountType());
    }

    @Override
    public String toString() {
        return "User{" +
                "credentials=" + credentials +
                '}';
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }
}
