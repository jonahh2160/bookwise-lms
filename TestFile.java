// IG
public class TestFile {

public static void main(String args[]) {

    BookDatabase bookDatabase = new BookDatabase();

    Book myBook = new Book("MyBook","Isaac","Jonah","Sci-Fi",87688575,2004,350);
    Book myOtherBook = new Book("My Other Book","Jonah Hamption","Michael Toon","Fantasy",88888,2021,58);

    bookDatabase.addEntry(myBook);
    bookDatabase.addEntry(myOtherBook);

    bookDatabase.removeEntry(bookDatabase.getEntry(1).getPrimaryKey());
    bookDatabase.setEntry(bookDatabase.getEntry(0),null,null,null,null,-1,-1,-1,true,"6942069");

    bookDatabase.display();
}

}