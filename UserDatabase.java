public class UserDatabase extends Database {

static Scanner scn = new Scanner(System.in);
ArrayList<User> member = new ArrayList<>();
private String username = " ", password = " ";
private String fullName = " ", firstName = " ", lastName = " ";
private String emailAddress = " ", currentAddress = " ";
private Double accountBalance = 0.00;
private int primaryKey;
User us;


 // Initial constructor for user object before more details are entered //
public UserDatabase(String fullName){
    this.fullName = fullName;
}

// Full constructor to collect all additional information from a  user //
public UserDatabase(String fullName, String emailAddress, String currentAddress, Double accountBalance, String username, String password) {
    this.fullName = fullName;
    separateFullName();
    this.emailAddress = emailAddress;
    this.currentAddress = currentAddress;
    this.accountBalance = accountBalance;
    this.username = username;
    this.password = password;
}

    public void editEntry() {

    }

    public void removeEntry() {

    }

    public void addEntry() {
    String name;
        
    System.out.println("Enter student's name.");
    name = scn.nextLine();
    us = new User(name);
    us.enterDetails();
    member.add(us);
    }

}