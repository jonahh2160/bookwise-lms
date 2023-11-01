// MT 10/15

public class User {
    public String primaryKey;
    private String fullName, firstName, lastName, username, password, emailAddress;
    private int perms;
    private double accountBalance;
    private boolean accountActive;

    public User (String fullName, String username, String password, int perms) {
        // TODO: Consider making this constructor less like vomit
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.perms = perms;
        this.accountBalance = 0.0;
        this.accountActive = true;

        separateFullName(fullName);
    }

    // A method which separates the full name entered from User into first and last name
    public void separateFullName(String fullName) {
        boolean spaceFound = false; 
        char c = ' ';
        
        for (int i = 0; i < fullName.length(); i++) {
            c = fullName.charAt(i);
            if (spaceFound == false) {
                if (c == ' ') {
                    spaceFound = true;      
                } else {
                    firstName += c;
                }
            } else {
                lastName += c;
            }
        }
    }
 
    // Getters
    public String getFullName() {
        return fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public boolean getActive() {
        return accountActive;
    }

    public int getPerms() {
        return perms;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    // Setters
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setActive(boolean accountActive) {
        this.accountActive = accountActive;
    }

    public void setPerms(int perms) {
        this.perms = perms;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }
loshdoi osi o
}