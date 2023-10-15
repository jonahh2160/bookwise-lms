public class Transaction extends DateManager {
    Book book;
    User user;
    String dateBorrowed, dateReturned, dateDue;
    double overdueFee = 0.25;

    public Transaction(User user, Book book, String dateBorrowed, String dateDue) {
        this.user = user;
        this.book = book;
        this.dateBorrowed = dateBorrowed;
        this.dateDue = dateDue;
        this.dateReturned = "";
    }

    //-------------------------------------------Basic getter/setter methods------------------------------------------------
    public User GetUser() {
        return(user);
    }
    public void SetUser(User user) {
        this.user = user;
    }
    public Book GetBook() {
        return(book);
    }
    public void SetBook(Book book) {
        this.book = book;
    }
    public String GetDateBorrowed() {
        return(dateBorrowed);
    }
    public void SetDateBorrowed(String dateBorrowed) {
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
    //---------------------------------------------------------end of basic getter/setter methods--------------------------------------

    //this method returns true or false whether the transaction is complete or not (book has been returned)
    public boolean GetStatus() {
        if (dateReturned != "") {
            return(true);
        } else {
            return(false);
        }
    }

    //this method officially completes the transaction, sets the dateReturned, sets the book's availability to true, and calculates overdue fees
    public void CompleteTransaction(String dateReturned) {
        setDateReturned(dateReturned);
        book.setAvailability(true);
        int daysOverdue = getDateDifference(this.dateDue,this.dateReturned);
        if (daysOverdue > 0) {
            user.addAccountBalance(-(daysOverdue*overdueFee));
        }
    }

}
