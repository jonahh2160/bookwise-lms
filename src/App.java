// IG 11/14
// Main class with main method to run the program

import java.io.*;
import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) {
        BookDatabase bookDatabase = new BookDatabase();
        UserDatabase userDatabase = new UserDatabase();
        TransactionDatabase transactionDatabase = new TransactionDatabase();

        DatabaseManager databaseManager = new DatabaseManager(bookDatabase, userDatabase, transactionDatabase);
        SearchPageGui mainPageGui = new SearchPageGui(bookDatabase, userDatabase, transactionDatabase, databaseManager);

        try {
            databaseManager.openSavedRecords();
            databaseManager.saveRecords();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not access records file :(");
        }

        mainPageGui.searchPage();
    }
}