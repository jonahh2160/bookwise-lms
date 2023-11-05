// IG 10/18

import java.util.ArrayList;

public class TransactionDatabase extends Table<Transaction> {

    // The constructor will intialize the database arraylist
    public TransactionDatabase() {
        database = new ArrayList<Transaction>();
    }

    // This will add an entry to the database using the parent method, and then give
    // the new entry a unique ID
    @Override
    public void addEntry(Transaction entry) {
        super.addEntry(entry);
        entry.setPrimaryKey(generateID());
        /// update external database here
    }

    // This will implement the ability to edit entries
    public void setEntry(Transaction transaction, User user, Book book, String dateBorrowed, String dateDue,
            String dateReturned, String primaryKey) {
        if (user != null) {
            transaction.setUser(user);
        }
        if (book != null) {
            transaction.setBook(book);
        }
        if (dateBorrowed != null) {
            transaction.setDateBorrowed(dateBorrowed);
        }
        if (dateDue != null) {
            transaction.setDateDue(dateDue);
        }
        if (dateReturned != null) {
            transaction.setDateReturned(dateReturned);
        }
        if (primaryKey != null) {
            transaction.setPrimaryKey(primaryKey);
        }
    }

    // This will return the primary key at a given index in the database (mainly so
    // that the parent class can access the primary key)
    @Override
    public String getID(int index) {
        return (database.get(index).primaryKey);
    }

    // This displays each primary key (for testing purposes)
    public void display() {
        for (int i = 0; i < database.size(); i++) {
            System.out.println("Transaction ID " + i + ": " + database.get(i).primaryKey);
        }
    }

}
