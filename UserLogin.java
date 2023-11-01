// JH 11/1
// Temporary(?) class that handles user login. Will use simple terminal input and response for now
// Accesses user database and iterates through it, comparing Strings
// TODO: In the main method, be able to set a loggedIn boolean to true

public class UserLogin {
    UserDatabase userDatabase;

    // Constructor that passes a userDatabase to here
    public UserLogin(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public int login(String username, String password) {

        for (int i = 0; i < userDatabase.getSize(); i++) {

            if (username.equals(userDatabase.getEntry(i).getUsername())) {
                // Continues if good username
                if (!password.equals(userDatabase.getEntry(i).getPassword())) {
                    // Incorrect password
                    return 0;
                }
                else {
                    // Correct password
                    return 1;
                }
            }

        }

        return -1;

    }
  
}
