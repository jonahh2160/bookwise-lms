import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagementPageGui {
    private InfoPageGui infoPage;
    private SearchPageGui searchPage;

    // Constructor
    public ManagementPageGui(InfoPageGui infoPage, SearchPageGui searchPage) {
        this.infoPage = infoPage;
        this.searchPage = searchPage;
    }

    // Method to assign action event for the edit button in the case of editing the
    // variables of a book;
    public void changeBookInfo(Book book) {

        String title = book.getTitle();
        String author = book.getAuthor();
        String genre = book.getGenre();
        int availability = 0;
        String[] available = {"Unavailable","Available","Cancel"};

        String[] responses = { "Title", "Author", "Genre","Availability", "Cancel" };
        int answer = JOptionPane.showOptionDialog(
                null,
                "Which field would you like to edit?",
                "Book Management", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, responses,
                responses[0]);
        if (answer == 0) {
            title = JOptionPane.showInputDialog("What would you like to change the title to? ");
            if (title != null) {book.setTitle(title);}
        } else if (answer == 1) {
            author = JOptionPane.showInputDialog("What would you like to change the author to? ");
            if (author != null) {book.setAuthor(author);}
        } else if (answer == 2) {
            genre = JOptionPane.showInputDialog("What would you like to change the genre to? ");
            if (genre != null) {book.setGenre(genre);}
        } else if (answer == 3) {
            availability = JOptionPane.showOptionDialog(
                    null,
                    "Which permission level would you like to assign?",
                    "Permission Management", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                    available, available[0]);
            if (availability == 0) {book.setAvailability(false);} else if (availability == 1) {
                book.setAvailability(true);
            }
        }

        infoPage.bookInfoPage(book);
        searchPage.refreshPage();
    }

    // Method to assign action event for the edit button in the case of editing the
    // variables of a user;
    public void changeUserInfo(User user) {

        String fullName = user.getFullName();
        String username = user.getUsername();
        String password = user.getPassword();
        int perms = user.getPerms();
        String[] memberLevel = { "Member", "Librarian", "Cancel" };

        String[] responses = { "Name", "Username", "Password", "Perms", "Cancel" };
        int answer = JOptionPane.showOptionDialog( // TODO need to fix this bug by instantiating an istance to refer for
                                                   // closing methods
                null,
                "Which field would you like to edit?",
                "User Management", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, responses,
                responses[0]);
        if (answer == 0) {
            fullName = JOptionPane.showInputDialog("What would you like to change the name to? ");
            if (fullName != null) {user.setFullName(fullName);}
        } else if (answer == 1) {
            username = JOptionPane.showInputDialog("What would you like to change the username to? ");
            if (username != null) {user.setUsername(username);}
        } else if (answer == 2) {
            password = JOptionPane.showInputDialog("What would you like to change the password to? ");
            if (password != null) {user.setPassword(password);}
        } else if (answer == 3) {
            perms = JOptionPane.showOptionDialog(
                    null,
                    "Which permission level would you like to assign?",
                    "Permission Management", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                    memberLevel, memberLevel[0]);
            if (perms < 2) {user.setPerms(perms);}
        }

        // Refresh the info page
        infoPage.userInfoPage(user);
        searchPage.refreshPage();
    }

    // Method to assign action event for the edit button in the case of editing the
    // variables of a transaction;
    public void changeTransactionInfo(Transaction transaction) {

        String dateBorrowed = transaction.getDateBorrowed();
        String dateReturned = transaction.getDateReturned();
        String dateDue = transaction.getDateDue();

        String[] responses1 = { "Date Borrowed", "Date Due", "Complete Transaction", "Cancel" };
        String[] responses2 = { "Date Borrowed", "Date Due", "Date Returned", "Cancel" };
        String[] responses;
        if (transaction.getStatus() == false) {
            responses = responses1;
        } else {
            responses = responses2;
        }
        int answer = JOptionPane.showOptionDialog( // TODO need to fix this bug by instantiating an istance to refer for
                                                   // closing methods
                null,
                "Which field would you like to edit?",
                "User Management", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, responses,
                responses[0]);
        if (answer == 0) {
            dateBorrowed = JOptionPane.showInputDialog("What would you like to change the borrowed date to?","D/M/Y");
            if (dateBorrowed != null) {transaction.setDateBorrowed(dateBorrowed);}
        } else if (answer == 1) {
            dateDue = JOptionPane.showInputDialog("What would you like to change the due date to?","D/M/Y");
            if (dateDue != null) {transaction.setDateDue(dateDue);}
        } else if (answer == 2) {
            if (responses == responses2) {
                dateReturned = JOptionPane.showInputDialog("What would you like to change the returned date to?","D/M/Y");
                if (dateReturned != null) {transaction.setDateReturned(dateReturned);}
            } else {
                dateReturned = JOptionPane.showInputDialog("Input the returned date.","D/M/Y");
                if (dateReturned != null) {transaction.completeTransaction(dateReturned);}
            }
        }

        // refresh the info and search page with new information
        infoPage.refreshTransactions();
        if (infoPage.getType() == 0) {
            infoPage.bookInfoPage();
        } else {
            infoPage.userInfoPage();
        }
        searchPage.refreshPage();
    }

}
