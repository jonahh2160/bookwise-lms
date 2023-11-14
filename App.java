public class App {
    public static void main(String[] args) {
        BookDatabase bookDatabase = new BookDatabase();
        UserDatabase userDatabase = new UserDatabase();
        TransactionDatabase transactionDatabase = new TransactionDatabase();

        SearchPageGui mainPageGui = new SearchPageGui(bookDatabase, userDatabase, transactionDatabase);
        Book book1 = new Book("MyBook", "Isaac", "Fantasy", 2003);
        Book book2 = new Book("Another Book", "Michael", "Sci-Fi", 2022);
        Book book3 = new Book("Yet Another One", "Jonah", "Fantasy", 2006);
        Book book4 = new Book("MyBook", "Isaac", "Fantasy", 2003);
        Book book5 = new Book("Another Book", "Michael", "Sci-Fi", 2022);
        Book book6 = new Book("Yet Another One", "Jonah", "Fantasy", 2006);
        bookDatabase.addEntry(book1);
        bookDatabase.addEntry(book2);
        bookDatabase.addEntry(book3);
        bookDatabase.addEntry(book4);
        bookDatabase.addEntry(book5);
        bookDatabase.addEntry(book6);

        User user1 = new User("Isaac Gunderson", "jpgundi", "1234", 0);
        User user2 = new User("Jonah Hampton", "tempest", "1234", 1);
        User user3 = new User("Michael Toon", "mtoon", "1234", 0);
        userDatabase.addEntry(user1);
        userDatabase.addEntry(user2);
        userDatabase.addEntry(user3);

        Transaction transaction1 = new Transaction(user1, book2, "09/07/2023", "09/22/2023");
        transactionDatabase.addEntry(transaction1);
        mainPageGui.searchPage();
    }
}
