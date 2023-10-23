//IG 10/18

import java.util.ArrayList;

public class TransactionDatabase {
    ArrayList<Transaction> database = new ArrayList<Transaction>();

    //empty constructor
    public TransactionDatabase() {};

    //method for adding a transaction to the database
    public void addEntry(Transaction transaction) {
        database.add(transaction);
        ///update external database here
    }

    //method for getting a transaction from the database using primary key
    public Transaction getTransactionFromID(int primaryKey) {
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).primaryKey == primaryKey) {
                return(database.get(i));
            }
        }
        return(null);
    }

    //method for getting a transaction from the database using array index
    public Transaction getTransactionFromIndex(int index) {
        return(database.get(index));
    }


    //method for removing a transaction
    public void removeEntry(int primaryKey) {
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).primaryKey == primaryKey) {
                database.remove(i);
                //update external database here
                break;
            }
        }
    }
    

}
