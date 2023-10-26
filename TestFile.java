
public class TestFile {

public static void main(String args[]) {

    BookDatabase bookDatabase = new BookDatabase();

    Book myBook = new Book("MyBook","Isaac","Jonah","Sci-Fi",87688575,2004,350);

    bookDatabase.addEntry(myBook);

    bookDatabase.display();
}

}