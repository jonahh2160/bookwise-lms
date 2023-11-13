import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagementPageGUI {
    private InfoPageGui infoPage;
    private SearchPageGui searchPage;

    // Constructor
    public ManagementPageGUI(InfoPageGui infoPage, SearchPageGui searchPage) {
        this.infoPage = infoPage;
        this.searchPage = searchPage;
    }   

//Method to assign action event for the edit button in the case of editing the variables of a book;
    public void changeBookInfo(Book book) {

                String title = book.getTitle();
                String author =book.getAuthor();
                String genre = book.getGenre();
                String publisher = book.getPublisher();
                long isbn = book.getIsbn();

                String[] responses = {"Title","Author","Genre","Publisher","ISBN","Cancel"};
		        int answer = JOptionPane.showOptionDialog(    // TODO need to fix this bug by instantiating an istance to refer for closing methods
				null,
				"Which field would you like to edit?", 
				"Book Management", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, responses, responses[0]);
                if(answer == 0) {
                    title = JOptionPane.showInputDialog("What would you like to change the title to? ");
                    book.setTitle(title);
                    infoPage.bookInfoPage(book);
                    searchPage.refreshPage();
                    return;
                    
                }
                if(answer == 1) {
                    author = JOptionPane.showInputDialog("What would you like to change the author to? ");
                    book.setAuthor(author);
                    infoPage.bookInfoPage(book);
                    searchPage.refreshPage();
                    return;
                    
                }
                if(answer == 2) {
                    genre = JOptionPane.showInputDialog("What would you like to change the genre to? ");
                    book.setGenre(genre);
                    infoPage.bookInfoPage(book);
                    searchPage.refreshPage();
                    return;
                }
                if(answer == 3) {
                    publisher = JOptionPane.showInputDialog("What would you like to change the publisher to? ");
                    book.setPublisher(publisher);
                    infoPage.bookInfoPage(book);
                    searchPage.refreshPage();
                    return;
                }
                if(answer == 4) {
                    isbn = Long.parseLong(JOptionPane.showInputDialog("What would you like to change the isbn to? "));
                    book.setIsbn(isbn);
                    infoPage.bookInfoPage(book);
                    searchPage.refreshPage();
                    return;
                }
            }
          

//Method to assign action event for the edit button in the case of editing the variables of a user;
    public void changeUserInfo(User user) {

                String fullName = user.getFullName();
                String username =user.getUsername();
                String password = user.getPassword();
                int perms = user.getPerms();
                String[] memberLevel = {"Member","Librarian","Cancel"};

                String[] responses = {"Name","Username","Password","Perms","Cancel"};
		        int answer = JOptionPane.showOptionDialog(  // TODO need to fix this bug by instantiating an istance to refer for closing methods
				null,
				"Which field would you like to edit?", 
				"User Management", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, responses, responses[0]);
                if(answer == 0) {
                    fullName = JOptionPane.showInputDialog("What would you like to change the name to? ");
                    user.setFullName(fullName);
                    infoPage.userInfoPage(user);
                    searchPage.refreshPage();
                    return;
                }
                if(answer == 1) {
                    username = JOptionPane.showInputDialog("What would you like to change the username to? ");
                    user.setUsername(username);
                    infoPage.userInfoPage(user);
                    searchPage.refreshPage();
                    return;
                }
                if(answer == 2) {
                    password = JOptionPane.showInputDialog("What would you like to change the password to? ");
                    user.setPassword(password);
                    infoPage.userInfoPage(user);
                    searchPage.refreshPage();
                    return;
                }
                if(answer == 3) {
		            perms = JOptionPane.showOptionDialog(
				    null,
				    "Which permission level would you like to assign?", 
				    "Permission Management", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, memberLevel, memberLevel[0]);
                    if (perms < 2) {
                        user.setPerms(perms);
                    }
                    if (perms == 3) {return;};
                    infoPage.userInfoPage(user);
                    searchPage.refreshPage();
                    return;
                }
                }

//Method to assign action event for the edit button in the case of editing the variables of a transaction;
    public void changeTransactionInfo(Transaction transaction) {

                String dateBorrowed = " ";
                String dateReturned =" ";
                String dateDue = " ";

                String[] responses = {"Date Borrowed","Date Returned","Date Due","Cancel"};
		        int answer = JOptionPane.showOptionDialog(  // TODO need to fix this bug by instantiating an istance to refer for closing methods
				null,
				"Which field would you like to edit?", 
				"User Management", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, responses, responses[0]);
                if(answer == 0) 
                dateBorrowed = JOptionPane.showInputDialog("What would you like to change the date borrowed to? ");
                transaction.setDateBorrowed(dateBorrowed);
                if(answer == 1)
                dateReturned = JOptionPane.showInputDialog("What would you like to change the date returned to? ");
                transaction.setDateReturned(dateReturned);
                if(answer == 2)
                dateDue = JOptionPane.showInputDialog("What would you like to change the date due to? ");
                transaction.setDateDue(dateDue);  
                }

              
    }
