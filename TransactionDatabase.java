import java.util.ArrayList;
import java.util.Random;

public class TransactionDatabase {
    //database array
    ArrayList<Transaction> database = new ArrayList<Transaction>();

    //empty constructor
    public TransactionDatabase() {};

    //method for adding a transaction to the database
    public void addTransaction(User user, Book book, String dateBorrowed, String dateDue) {
        Transaction transaction = new Transaction(user,book,dateBorrowed,dateDue);
        database.add(transaction);
    }

    //method for removing a transaction

    
}
