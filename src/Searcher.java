// IG 10/31
// Handles object searches, returning a new array with the requested items

import java.util.ArrayList;

public class Searcher {
    // Declaration of variables (lists/objects)
    BookDatabase bookDatabase;
    UserDatabase userDatabase;
    ArrayList<Book> bookList = new ArrayList<Book>();
    ArrayList<User> userList = new ArrayList<User>();

    // Constructor will set the list variables to refer to our three main databases
    public Searcher(BookDatabase bookDatabase, UserDatabase userDatabase) {
        this.bookDatabase = bookDatabase;
        this.userDatabase = userDatabase;
    }

    // BOOK SEARCH METHODS

    // Check each book to see if the information matches the search term, return an
    // ArrayList of the searched books
    public ArrayList<Book> searchBook(String term) {
        // Make sure the book list is empty before starting
        bookList.clear();
        // Set everything to lowercase (for comparison reasons)
        String strSearch = term.toLowerCase();
        // Loop through books, if any match the search term, then add them to book list
        // and return the list of searched books
        for (int i = 0; i < bookDatabase.getSize(); i++) {
            String strCompare = getBookSearchTerm(i, strSearch);
            if (strCompare.contains(strSearch)) {
                bookList.add(bookDatabase.getEntry(i));
            }
        }
        return (bookList);
    }

    // This is used to put the search term and actual book term in the same format
    // for comparing
    private String getBookSearchTerm(int entry, String term) {
        // 0=Title;1=Author;2=Genre;3=PrimaryKey
        String strCompare = "";
        // Get different term depending on what category is given
        strCompare += bookDatabase.getEntry(entry).getTitle().toLowerCase() + " ";

        strCompare += bookDatabase.getEntry(entry).getAuthor().toLowerCase() + " ";

        strCompare += bookDatabase.getEntry(entry).getGenre().toLowerCase() + " ";

        strCompare += bookDatabase.getEntry(entry).getPrimaryKey().toLowerCase() + " ";
        
        return (strCompare);
    }

    // USER SEARCH METHODS

    // Check each user to see if the information matches the search term, return an
    // ArrayList of the searched users
    public ArrayList<User> searchUser(String term) {
        // Make sure the user list is empty before starting
        userList.clear();
        // Set everything to lowercase (for comparison reasons)
        String strSearch = term.toLowerCase();
        // Loop through users and if any of them match the search term, then add it to
        // user list and return the list of searched users
        for (int i = 0; i < userDatabase.getSize(); i++) {
            String strCompare = getUserSearchTerm(i, strSearch);
            if (strCompare.contains(strSearch)) {
                userList.add(userDatabase.getEntry(i));
            }
        }
        return (userList);
    }

    private String getUserSearchTerm(int entry, String term) {
        // 0=Fullname;1=Username;2=PrimaryKey
        String strCompare = "";
        // Get different term depending on what category is given
        strCompare += userDatabase.getEntry(entry).getFullName().toLowerCase() + " ";
        strCompare += userDatabase.getEntry(entry).getUsername().toLowerCase() + " ";
        strCompare += userDatabase.getEntry(entry).getPrimaryKey().toLowerCase() + " ";

        return (strCompare);
    }

}