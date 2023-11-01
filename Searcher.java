// IG 10/31
import java.util.ArrayList;

public class Searcher {
    BookDatabase bookDatabase;
    UserDatabase userDatabase;
    TransactionDatabase transactionDatabase;
    ArrayList<Book> bookList = new ArrayList<Book>();
    ArrayList<User> userList = new ArrayList<User>();
    ArrayList<Transaction> transactionList = new ArrayList<Transaction>();

    // Constructor will set the list variables to refer to our three main databases
    public Searcher(BookDatabase bookDatabase, UserDatabase userDatabase, TransactionDatabase transactionDatabase) {
        this.bookDatabase = bookDatabase;
        this.userDatabase = userDatabase;
        this.transactionDatabase = transactionDatabase;
    }

    // BOOK SEARCH METHODS

    // Check each book to see if the information matches the search term, return an ArrayList of the searched books
    public ArrayList<Book> searchBook(int category, String term) {
        // Make sure the book list is empty before starting
        bookList.clear();
        // Set everything to lowercase (for comparison reasons)
        String strSearch = term.toLowerCase();
        // Loop through books, if any match the search term, then add them to book list and return the list of searched books
        for (int i = 0; i < bookDatabase.getSize();i++) {
            String termParsed = getBookSearchTerm(category,i,strSearch);
            if (strSearch.equals(termParsed)) {
                bookList.add(bookDatabase.getEntry(i));
            }
        }
        return(bookList);
    }

    // This is used to put the search term and actual book term in the same format for comparing
    private String getBookSearchTerm(int category,int entry, String term) {
        // 0=Title;1=Author;2=Genre;3=PrimaryKey
        String strCompare = "";
        // Get different term depending on what category is given
        if (category == 0) {strCompare = bookDatabase.getEntry(entry).getTitle().toLowerCase();}
        if (category == 1) {strCompare = bookDatabase.getEntry(entry).getAuthor().toLowerCase();}
        if (category == 2) {strCompare = bookDatabase.getEntry(entry).getGenre().toLowerCase();}
        if (category == 3) {strCompare = bookDatabase.getEntry(entry).getPrimaryKey().toLowerCase();}
        // Make the term the same length as the search term
        String termParsed = "";
        for (int i = 0; i < term.length();i++) {
            if (i < strCompare.length()) {
                termParsed += strCompare.charAt(i);
            }
        }
        return(termParsed);
    }

    // USER SEARCH METHODS

    // Check each user to see if the information matches the search term, return an ArrayList of the searched users
    public ArrayList<User> searchUser(int category, String term) {
        // Make sure the user list is empty before starting
        userList.clear();
        // Set everything to lowercase (for comparison reasons)
        String strSearch = term.toLowerCase();
        // Loop through users and if any of them match the search term, then add it to user list and return the list of searched users
        for (int i = 0; i < userDatabase.getSize();i++) {
            String termParsed = getUserSearchTerm(category,i,strSearch);
            if (strSearch.equals(termParsed)) {
                userList.add(userDatabase.getEntry(i));
            }
        }
        return(userList);
    }

    private String getUserSearchTerm(int category,int entry, String term) {
        // 0=Fullname;1=Username;2=PrimaryKey
        String strCompare = "";
        // Get different term depending on what category is given
        if (category == 0) {strCompare = userDatabase.getEntry(entry).getFullName().toLowerCase();}
        if (category == 1) {strCompare = userDatabase.getEntry(entry).getUsername().toLowerCase();}
        if (category == 2) {strCompare = userDatabase.getEntry(entry).getPrimaryKey().toLowerCase();}
        String termParsed = "";
        // Make sure the term is the same length as the search term
        for (int i = 0; i < term.length();i++) {
            if (i < strCompare.length()) {
                termParsed += strCompare.charAt(i);
            }
        }
        return(termParsed);
    }

    // TRANSACTION SEARCH METHODS
    // Check each transaction to see if the information matches the search term, return an ArrayList of the searched transactions
    public ArrayList<Transaction> searchTransaction(int category, String term) {
        // Make sure transaction list is empty before starting
        transactionList.clear();
        // Set everything to lowercase (for comparison reasons)
        String strSearch = term.toLowerCase();
        // Loop through transactions and if any of them match the search term, add it to the list and return the list of searched transactions
        for (int i = 0; i < transactionDatabase.getSize();i++) {
            String termParsed = getTransactionSearchTerm(category,i,strSearch);
            if (strSearch.equals(termParsed)) {
                transactionList.add(transactionDatabase.getEntry(i));
            }
        }
        return(transactionList);
    }

    private String getTransactionSearchTerm(int category,int entry, String term) {
        // 0=UserUsername;1=BookTitle;2=DateDue;3=PrimaryKey
        String strCompare = "";
        // Get different term based on what category is given
        if (category == 0) {strCompare = transactionDatabase.getEntry(entry).getUser().getUsername().toLowerCase();}
        if (category == 1) {strCompare = transactionDatabase.getEntry(entry).getBook().getTitle().toLowerCase();}
        if (category == 2) {strCompare = transactionDatabase.getEntry(entry).getDateDue().toLowerCase();}
        if (category == 3) {strCompare = transactionDatabase.getEntry(entry).getPrimaryKey().toLowerCase();}
        // Make sure the actual term and search term are the same length
        String termParsed = "";
        for (int i = 0; i < term.length();i++) {
            if (i < strCompare.length()) {
                termParsed += strCompare.charAt(i);
            }
        }
        return(termParsed);
    }

}
