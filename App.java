import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {
        BookDatabase bookDatabase = new BookDatabase();
        UserDatabase userDatabase = new UserDatabase();
        TransactionDatabase transactionDatabase = new TransactionDatabase();
        DatabaseManager databaseManager = new DatabaseManager(bookDatabase,userDatabase,transactionDatabase);

        SearchPageGui mainPageGui = new SearchPageGui(bookDatabase, userDatabase, transactionDatabase,databaseManager);

        databaseManager.openSavedRecords();
        databaseManager.saveRecords();

        mainPageGui.searchPage();
    }
}
