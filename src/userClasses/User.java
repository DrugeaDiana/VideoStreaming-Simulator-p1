package userClasses;

import inputClasses.CredentialsInput;
import inputClasses.UserInput;

public class User {
    private Credentials credentials;

    public User(final UserInput userInput) {
        credentials = new Credentials(userInput.getCredentials());
    }

    public User() { }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public void setCredentialsInput(final CredentialsInput credentials) {
        this.credentials.setName(credentials.getName());
        this.credentials.setBalance(credentials.getBalance());
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
}
