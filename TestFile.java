
// IG
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

public class TestFile {

    public static void main(String args[]) throws IOException, ParseException {

        BookDatabase bookDatabase = new BookDatabase();
        UserDatabase userDatabase = new UserDatabase();
        TransactionDatabase transactionDatabase = new TransactionDatabase();
        Searcher searcher = new Searcher(bookDatabase, userDatabase, transactionDatabase);

        Book myBook = new Book("MyBook", "Isaac", "Jonah", "Sci-Fi", 87688575, 2004, 350);
        Book myOtherBook = new Book("My Other Book", "Jonah Hamption", "Michael Toon", "Fantasy", 88888, 2021, 58);
        Book myOtherOtherBook = new Book("The Princess Bride", "Johnny Appleseed", "Fruitcup", "Fantasy", 88888, 2001,
                158);

        bookDatabase.addEntry(myBook);
        bookDatabase.addEntry(myOtherBook);
        bookDatabase.addEntry(myOtherOtherBook);

        bookDatabase.display();

        ArrayList<Book> myList = searcher.searchBook(1, "Jo");

        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i).getTitle());
        }

        System.out.println("TESTING USER LOGIN -=-=-=-=-=-=-=-=-");

        User myUser = new User("Isaac Gunderson", "cha0sdrive", "qwerty", 2);
        userDatabase.addEntry(myUser);

        userDatabase.display();

        UserLogin userLogin = new UserLogin(userDatabase);

        System.out.println("SHOULD PRINT -1: " + userLogin.login("ssbtempest", "Blood is fuel"));
        System.out.println();
        System.out.println("SHOULD PRINT 0: " + userLogin.login("cha0sdrive", "Blood is fuel"));
        System.out.println();
        System.out.println("SHOULD PRINT 1: " + userLogin.login("cha0sdrive", "qwerty"));
        System.out.println();
        System.out.println("MORE TEST CASES: " + userLogin.login("", ""));
        System.out.println();

        // testing the sorting
        System.out.println("Sorted List: ");
        Sorter sorter = new Sorter();
        ArrayList<Book> sortedList = sorter.titleSorter(bookDatabase.getArray());

        for (int i = 0; i < sortedList.size(); i += 1) {
            System.out.println(sortedList.get(i).getTitle());
        }
    }

}