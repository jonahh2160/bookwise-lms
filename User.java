//   10/15/23  MT  //
import java.util.Scanner;
import java.util.ArrayList;

public class User {

// Variables to be used for user class //
static Scanner scn = new Scanner(System.in);
ArrayList<user> member = new ArrayList<>();
private String username = " ", password = " ";
private String fullName = " ", firstName = " ", lastName = " ";
private String emailAddress = " ", currentAddress = " ";
private Double accountBalance = 0.00;
private int primaryKey;
User us; 

// Initial constructor for user object before more details are entered //
public User(String fullName){
    this.fullName = fullName;
}

// Full constructor to collect all additional information from a  user //
public User(String fullName, String emailAddress, String currentAddress, Double accountBalance, String username, String password) {
    this.fullName = fullName;
    separateFullName();
    this.emailAddress = emailAddress;
    this.currentAddress = currentAddress;
    this.accountBalance = accountBalance;
    this.username = username;
    this.password = password;
}

// A method which separates the full name entered from user into first and last name //
private void separateFullName() {
        boolean spaceFound = false; 
        char c = ' ';
        
        for (int i = 0; i < fullName.length(); i++){
        c = fullName.charAt(i);
        if ( spaceFound == false){
             if ( c == ' '){
                spaceFound = true;       
                } else {
                firstName += c;
                }
        } else {
           lastName += c;
        }
                                                      }
}

// Getter and Setter methods for all variables of a user //
public String GetFullName() {
    return fullName;
}

public void SetFullName(String newFullname) {
    fullName = newFullname;
}

public String GetFirstName() {
    return firstName;
}

public void SetFirstName(String newFirstName) {
    firstName = newFirstName;
}

public String GetLastName() {
    return lastName;
}

public void SetLastName(String newLastName) {
    lastName = newLastName;
}

public String GetUsername() {
    return username;
}

public void SetUsername(String newUsername) {
    username = newUsername;
}

public String GetPassword() {
    return password;
}

public void SetPassword(String newPassword) {
    password = newPassword;
}

public String GetEmailAddress() {
    return emailAddress;
}

public void SetEmailAddress(String newEmailAddress) {
    emailAddress = newEmailAddress;
}

public String GetCurrentAddress() {
    return currentAddress;
}

public void SetCurrentAddress(String newCurrentAddress) {
    currentAddress = newCurrentAddress;
}

public Double GetAccountBalance() {
    return accountBalance;
}

public void SetAccountBalance(Double newAccountBalance) {
    accountBalance = newAccountBalance;
}

// A method for collecting additional information from a user such as their email, password, username and address //
public void enterDetails(){
    System.out.println("Please enter your first and last name with a space.");
    fullName = scn.nextLine();
    separateFullName();
    System.out.println("Please enter your email Address.");
    emailAddress = scn.nextLine();
    System.out.println("Please enter a password with 10 characters, a capital letter and a number");
    password = scn.nextLine();
    System.out.println("Please enter a username.");
    username = scn.nextLine();
    System.out.println("Please enter you address.");
    currentAddress = scn.nextLine();
}

// A method for adding a new member to the user array //
public void addMember() {
    String name;
        
    System.out.println("Enter student's name.");
    name = scn.nextLine();
    us = new User(name);
    us.enterDetails();
    member.add(us);    
}

// A method for finding the index of a specific member in the user array //
static int findMember(ArrayList<user> members, String fullName) {
        int returnVal = -1;
        
        for (int i =0; i < members.size(); i++ ) {
            if ( fullName.equals(members.get(i).GetFullName())) {
                 returnVal = i;
            }   
        } 
        return returnVal;
}

// A method for removing a member in the user array //
public void removeMember(ArrayList<user> members){
    String memberToDelete = " ";
    String answer = " ";
    int i;

    System.out.println("Please enter the name of the member you'd like to delete.");
        memberToDelete = scn.nextLine();

        i = findMember(members, memberToDelete);
        
        if (i != -1) {
            System.out.println("Are you sure you want to delete this member?");
            answer = scn.nextLine();
           if ( answer.equals("yes") ){
            members.remove(i);
            }
            else {
            System.out.println("They live to read another day!");
            }
        } 
        else {
            System.out.println("Record Not Found.");
        }
}

// A method for editing personal details on a specific member in the user array by asking which variable you would like to change //
public void editMember(ArrayList<user> members) {
    String memberToEdit = " ";
    String answer = " ";
    int i;
    String newName = " ", newEmailAddress = " ", newPassword = " ", newUsername = " ", newAddress = " ";
    String newFirstName = " ", newLastName = " ";
    i = findMember(members, memberToEdit);
    
    if (i != -1) {
            System.out.println("Would you like to edit the user's name, email address, password, username, or current address?");
            answer = scn.nextLine();
          if ( answer.equals("name") ){
            System.out.println("What would you like to change the first, last or full name?");
            answer = scn.nextLine();
                if ( answer.equals("full name") ) {
                System.out.println("What would you like to change the their full name to?");
                newName = scn.nextLine();
                members.get(i).SetFullName(newName);
                }
                else if( answer.equals("first name")){
                System.out.println("What would you like to change their first name to?");
                newFirstName = scn.nextLine();
                members.get(i).SetFirstName(newFirstName);
                }
                else {
                System.out.println("What would you like to change their last name to?");
                newLastName = scn.nextLine();
                members.get(i).SetLastName(newLastName);
                }
                }
          else if( answer.equals("email address")) {
               System.out.println("What would you like to change the email address to?");
               newEmailAddress = scn.nextLine();
               members.get(i).SetEmailAddress(newEmailAddress);
           }
          else if( answer.equals("password")) {
               System.out.println("What would you like to change the password to?");
               newPassword = scn.nextLine();
               members.get(i).SetPassword(newPassword);
           }
          else if( answer.equals("username")) {
               System.out.println("What would you like to change the username to?");
               newUsername = scn.nextLine();
               members.get(i).SetUsername(newUsername);
           }
          else {
            System.out.println("What would you like to change the current address to?");
            newAddress = scn.nextLine();
            members.get(i).SetCurrentAddress(newAddress);
        }
    } 
    else {
            System.out.println("Record Not Found.");
        }
}
}