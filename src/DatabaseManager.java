
//IG 11/15
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class DatabaseManager {

    static Scanner scn = new Scanner(System.in);
    private ArrayList<Book> bookArray = new ArrayList<Book>();
    private ArrayList<User> userArray = new ArrayList<User>();
    private ArrayList<Transaction> transactionArray = new ArrayList<Transaction>();
    private BookDatabase bookDatabase;
    private UserDatabase userDatabase;
    private TransactionDatabase transactionDatabase;

    public DatabaseManager(BookDatabase bookDatabase, UserDatabase userDatabase,
            TransactionDatabase transactionDatabase) {
        this.bookDatabase = bookDatabase;
        this.userDatabase = userDatabase;
        this.transactionDatabase = transactionDatabase;
    }

    public void saveRecords() throws IOException {
        FileOutputStream fout = new FileOutputStream("lib/records.txt");
        PrintWriter out = new PrintWriter(fout);

        // save books
        for (int i = 0; i < bookDatabase.getSize(); i++) {
            // Book
            out.print("[BOOK] ");
            // Title
            out.print(removeSpacesFromString(bookDatabase.getEntry(i).getTitle()) + " ");
            // Author
            out.print(removeSpacesFromString(bookDatabase.getEntry(i).getAuthor()) + " ");
            // Genre
            out.print(removeSpacesFromString(bookDatabase.getEntry(i).getGenre()) + " ");
            // Availability
            out.print(bookDatabase.getEntry(i).getAvailability() + " ");
            // Year
            out.print(bookDatabase.getEntry(i).getYear() + " ");
            // Primary Key
            out.println(bookDatabase.getEntry(i).getPrimaryKey() + " ");
        }
        // save users
        for (int i = 0; i < userDatabase.getSize(); i++) {
            // User
            out.print("[USER] ");
            // Name
            out.print(removeSpacesFromString(userDatabase.getEntry(i).getFullName()) + " ");
            // Username
            out.print(removeSpacesFromString(userDatabase.getEntry(i).getUsername()) + " ");
            // Password
            out.print(removeSpacesFromString(userDatabase.getEntry(i).getPassword()) + " ");
            // Perms
            out.print(userDatabase.getEntry(i).getPerms() + " ");
            // Balance
            out.print(userDatabase.getEntry(i).getAccountBalance() + " ");
            // Status
            out.print(userDatabase.getEntry(i).getActive() + " ");
            // Primary Key
            out.println(userDatabase.getEntry(i).getPrimaryKey() + " ");
        }
        // save transactions
        for (int i = 0; i < transactionDatabase.getSize(); i++) {
            // Transaction
            out.print("[TRANSACTION] ");
            // Book
            out.print(transactionDatabase.getEntry(i).getBook().getPrimaryKey() + " ");
            // User
            out.print(transactionDatabase.getEntry(i).getUser().getPrimaryKey() + " ");
            // Date Borrowed
            out.print(removeSpacesFromString(transactionDatabase.getEntry(i).getDateBorrowed()) + " ");
            // Date Due
            out.print(removeSpacesFromString(transactionDatabase.getEntry(i).getDateDue()) + " ");
            // Status
            out.print(transactionDatabase.getEntry(i).getStatus() + " ");
            // Date Returned
            if (transactionDatabase.getEntry(i).getStatus() == true) {
                out.print(removeSpacesFromString(transactionDatabase.getEntry(i).getDateReturned()) + " ");
            }
            // Primary Key
            out.println(transactionDatabase.getEntry(i).getPrimaryKey() + " ");
        }
        out.close();
    }

    public void openSavedRecords() throws IOException {
        FileInputStream fileInput = new FileInputStream("lib/records.txt");
        Scanner fileSCN = new Scanner(fileInput);
        bookArray.clear();
        userArray.clear();
        transactionArray.clear();

        while (fileSCN.hasNext()) {
            String type = fileSCN.next();
            if (type.equals("[BOOK]")) {
                String title, author, genre, bookID;
                boolean availability;
                int year;
                title = addSpacesToString(fileSCN.next());
                author = addSpacesToString(fileSCN.next());
                genre = addSpacesToString(fileSCN.next());
                availability = fileSCN.nextBoolean();
                year = fileSCN.nextInt();
                bookID = fileSCN.next();
                Book book = new Book(title, author, genre, year);
                bookDatabase.addEntry(book);
                book.setPrimaryKey(bookID);
                book.setAvailability(availability);
                fileSCN.nextLine();
            }
            if (type.equals("[USER]")) {
                String name, username, password, userID;
                boolean active;
                int perms;
                double balance;
                name = addSpacesToString(fileSCN.next());
                username = addSpacesToString(fileSCN.next());
                password = addSpacesToString(fileSCN.next());
                perms = fileSCN.nextInt();
                balance = fileSCN.nextDouble();
                active = fileSCN.nextBoolean();
                userID = fileSCN.next();
                User user = new User(name, username, password, perms);
                userDatabase.addEntry(user);
                user.setPrimaryKey(userID);
                user.setActive(active);
                user.setAccountBalance(balance);
                fileSCN.nextLine();
            }
            if (type.equals("[TRANSACTION]")) {
                Book book;
                User user;
                String dateBorrowed, dateDue, dateReturned, transactionID;
                boolean status;
                book = bookDatabase.getEntry(fileSCN.next());
                user = userDatabase.getEntry(fileSCN.next());
                dateBorrowed = addSpacesToString(fileSCN.next());
                dateDue = addSpacesToString(fileSCN.next());
                status = fileSCN.nextBoolean();
                if (status == true) {
                    dateReturned = addSpacesToString(fileSCN.next());
                } else {
                    dateReturned = "";
                }
                transactionID = fileSCN.next();
                Transaction transaction = new Transaction(user, book, dateBorrowed, dateDue);
                transactionDatabase.addEntry(transaction);
                transaction.setPrimaryKey(transactionID);
                if (status == true) {
                    transaction.setDateReturned(dateReturned);
                }
                fileSCN.nextLine();
            }
        }
        fileSCN.close();
    }

    private String removeSpacesFromString(String string) {
        char c = 0;
        String newString = "";
        for (int i = 0; i < string.length(); i++) {
            c = string.charAt(i);
            if (c == 32) {
                c = 95;
            }
            newString += c;
        }
        return (newString);
    }

    private String addSpacesToString(String string) {
        char c = 0;
        String newString = "";
        for (int i = 0; i < string.length(); i++) {
            c = string.charAt(i);
            if (c == 95) {
                c = 32;
            }
            newString += c;
        }
        return (newString);
    }

}