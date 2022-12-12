package tvClasses.databasesClasses;

import userClasses.Credentials;
import userClasses.User;

import java.util.ArrayList;

/**
 * Singleton Pattern
 */
public class UsersDatabase {
    private final static UsersDatabase instance = new UsersDatabase();

    private UsersDatabase() {}

    public static UsersDatabase getInstance() {
        return instance;
    }

    private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public boolean checkExistingUser(Credentials userData) {
        for (User user : users) {
            if (user.getCredentials().getName().equals(userData.getName())) {
                return true;
            }
        }
        return false;
    }

    public User getSpecificUser(Credentials userData) {
        for (User user : users) {
            if (user.getCredentials().getName().equals(userData.getName())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "UsersDatabase{" +
                "users=" + users +
                '}';
    }
}
