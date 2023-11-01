// IG
import java.util.ArrayList;

public class TestFile {

public static void main(String args[]) {

    BookDatabase bookDatabase = new BookDatabase();
    UserDatabase userDatabase = new UserDatabase();
    TransactionDatabase transactionDatabase = new TransactionDatabase();
    Searcher searcher = new Searcher(bookDatabase,userDatabase,transactionDatabase);

    Book myBook = new Book("MyBook","Isaac","Jonah","Sci-Fi",87688575,2004,350);
    Book myOtherBook = new Book("My Other Book","Jonah Hamption","Michael Toon","Fantasy",88888,2021,58);
    Book myOtherOtherBook = new Book("The Princess Bride","Johnny Appleseed","Fruitcup","Fantasy",88888,2001,158);

    bookDatabase.addEntry(myBook);
    bookDatabase.addEntry(myOtherBook);
    bookDatabase.addEntry(myOtherOtherBook);

    bookDatabase.display();

    ArrayList<Book> myList = searcher.searchBook(2,"Fan");

    for (int i = 0; i < myList.size(); i++) {
        System.out.println(myList.get(i).getTitle());
    }

}

}