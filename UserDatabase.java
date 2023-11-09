// MT 10/23

import java.util.ArrayList;

public class UserDatabase extends Table<User> {

    // Constructor; initializes database ArrayList
    public UserDatabase() {
        this.database = new ArrayList<User>();
    }

    // Overriden method to add an entry with the parent method and generate an ID
    @Override
    public void addEntry(User entry) {
        super.addEntry(entry);
        entry.setPrimaryKey(generateID());
    }

    // Implements the ability to edit user entries
    public void setEntry(User user, String fullName, String username, String password, String emailAddress,
            double accountBalance,
            boolean accountActive, int perms, String primaryKey) {
        if (fullName != null) {
            user.setFullName(fullName);
            user.separateFullName(fullName);
        }
        if (username != null) {
            user.setUsername(username);
        }
        if (password != null) {
            user.setPassword(password);
        }
        if (emailAddress != null) {
            user.setEmailAddress(emailAddress);
        }
        if (accountBalance != -1) {
            user.setAccountBalance(accountBalance);
        }
        user.setActive(accountActive);
        if (perms != -1) {
            user.setPerms(perms);
        }
        if (primaryKey != null) {
            user.setPrimaryKey(primaryKey);
        }
    }

    // Returns the primaryKey at a given index
    // Overriden so that the parent class can access the primaryKey
    @Override
    public String getID(int index) {
        return (database.get(index).primaryKey);
    }

    // Displays each primary key (for testing purposes)
    public void display() {
        for (int i = 0; i < database.size(); i++) {
            System.out.println("User ID " + i + ": " + database.get(i).primaryKey);
        }
    }
}