// IG 10/13
// Class for the Transaction object that contains its attributes

public class Transaction extends DateManager {
    // Declaration of variables
    private Book book;
    private User user;
    private String dateBorrowed, dateReturned, dateDue;
    public String primaryKey;
    double overdueFee = 0.25;

    // Full constructor
    public Transaction(User user, Book book, String dateBorrowed, String dateDue) {
        this.user = user;
        this.book = book;
        this.dateBorrowed = dateBorrowed;
        this.dateDue = dateDue;
        this.dateReturned = "";

        book.setAvailability(false);
    }

    // Returns true if the book has been returned (transaction complete), else false
    public boolean getStatus() {
        if (dateReturned != "") {
            return (true);
        } else {
            return (false);
        }
    }

    // Officially completes the transaction by updating vars and calculating fees
    public void completeTransaction(String dateReturned) {
        setDateReturned(dateReturned);
        book.setAvailability(true);
        int daysOverdue = getDateDifference(this.dateDue, this.dateReturned);
        if (daysOverdue > 0) {
            user.setAccountBalance(user.getAccountBalance() - (daysOverdue * overdueFee));
        }
    }

    // Getters
    public User getUser() {
        return (user);
    }

    public Book getBook() {
        return (book);
    }

    public String getDateBorrowed() {
        return (dateBorrowed);
    }

    public String getDateDue() {
        return (dateDue);
    }

    public String getDateReturned() {
        return (dateReturned);
    }

    public String getPrimaryKey() {
        return (primaryKey);
    }

    // Setters
    public void setUser(User user) {
        this.user = user;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }

    public void setDateReturned(String dateReturned) {
        this.dateReturned = dateReturned;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }
}