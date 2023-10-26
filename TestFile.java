
public class TestFile {

public static void main(String args[]) {

    BookDatabase bookDatabase = new BookDatabase();
    TransactionDatabase transactionDatabase = new TransactionDatabase();

    Book myBook = new Book("MyBook","Isaac","Jonah","Sci-Fi",87688575,2004,350);
    Book myOtherBook = new Book("My Other Book","Jonah Hamption","Michael Toon","Fantasy",88888,2021,58);
    User myUser = new User("Michael Toon");
    Transaction myTransaction = new Transaction(myUser,myBook,"09/08/2021","09/22/2021");

    bookDatabase.addEntry(myBook);
    bookDatabase.addEntry(myOtherBook);
    transactionDatabase.addEntry(myTransaction);

    bookDatabase.removeEntry(bookDatabase.getEntry(1).getPrimaryKey());

    bookDatabase.display();
    transactionDatabase.display();
}

}