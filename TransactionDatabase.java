//IG 10/18

import java.util.ArrayList;

public class TransactionDatabase extends Table<Transaction> {

    //empty constructor
    public TransactionDatabase() {
        database = new ArrayList<Transaction>();
    }

    //method for adding a transaction to the database
    @Override
    public void addEntry(Transaction entry) {
        super.addEntry(entry);
        entry.setPrimaryKey(generateID());
        ///update external database here
    }

    @Override
    public void setEntry(){};

    @Override
    public String getID(int index) {
        return(database.get(index).primaryKey);
    }

    //this method is for testing purposes
    public void display() {
        for (int i=0;i<database.size();i++) {
            System.out.println("Transaction ID " + i + ": " + database.get(i).primaryKey);
        }
    }

}
