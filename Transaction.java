// IG 10/13

public class Transaction extends DateManager {
    Book book;
    User user;
    String dateBorrowed, dateReturned, dateDue, primaryKey;
    double overdueFee = 0.25;

    public Transaction(User user, Book book, String dateBorrowed, String dateDue) {
        this.user = user;
        this.book = book;
        this.dateBorrowed = dateBorrowed;
        this.dateDue = dateDue;
        this.dateReturned = "";

        book.setAvailability(false);
    }

    //-------------------------------------------Basic getter/setter methods------------------------------------------------
    public User getUser() {
        return(user);
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Book getBook() {
        return(book);
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public String getDateBorrowed() {
        return(dateBorrowed);
    }
    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }
    public String getDateDue() {
        return(dateDue);
    }
    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }
    public String getDateReturned() {
        return(dateReturned);
    }
    public void setDateReturned(String dateReturned) {
        this.dateReturned = dateReturned;
    }
    public String getPrimaryKey() {
        return(primaryKey);
    }
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    //---------------------------------------------------------end of basic getter/setter methods--------------------------------------

    //this method returns true or false whether the transaction is complete or not (book has been returned)
    public boolean getStatus() {
        if (dateReturned != "") {
            return(true);
        } else {
            return(false);
        }
    }

    //this method officially completes the transaction, sets the dateReturned, sets the book's availability to true, and calculates overdue fees
    public void completeTransaction(String dateReturned) {
        setDateReturned(dateReturned);
        book.setAvailability(true);
        int daysOverdue = getDateDifference(this.dateDue,this.dateReturned);
        if (daysOverdue > 0) {
            user.SetAccountBalance(user.GetAccountBalance() - (daysOverdue*overdueFee));
        }
    }

}
