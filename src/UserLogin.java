// JH 11/1
// Small class that handles user logins

public class UserLogin {
    UserDatabase userDatabase;

    // Constructor that passes a userDatabase to here
    public UserLogin(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    // Single method to verify logins of users
    public int login(String username, String password) {

        // Iterates through array of users
        for (int i = 0; i < userDatabase.getSize(); i++) {

            // Checks each user to the inputted username
            if (username.equals(userDatabase.getEntry(i).getUsername())) {
                // Good username
                if (!password.equals(userDatabase.getEntry(i).getPassword())) {
                    // Bad password; password does not match user on file
                    return 0;
                } else {
                    // Good password; password matches user on file
                    return 1;
                }
            }
        }

        // Bad username; username could not be found
        return -1;
    }
}