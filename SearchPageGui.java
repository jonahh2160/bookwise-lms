// IG 11/7
// Used for testing the GUI components

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.*;

public class SearchPageGui {
    private Searcher searcher;
    private BookDatabase bookDatabase;
    private UserDatabase userDatabase;
    private TransactionDatabase transactionDatabase;
    private JFrame frame;
    private JPanel bookPanel;
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton bookButton, userButton, currentButton, loginButton;
    private InfoPageGui infoPage;
    final int tableX = 160;
    final int tableY = 100;
    final int tableWidth = 600;
    final int tableHeight = 384;
    final String[] bookColumnNames = {"Title","Author","Genre","ID","Available"};
    final String[] userColumnNames = {"Name","Username","ID","Status"};
    private String[] columnNames = bookColumnNames;
    private int cellWidth = tableWidth/columnNames.length;
    private User userLoggedIn;
    final int cellHeight = 20;
    final int buttonWidth = cellWidth*2;

    // Constructor that initializes the frame, panel, table model, scroll pane, table, and buttons
    public SearchPageGui(BookDatabase bookDatabase, UserDatabase userDatabase, TransactionDatabase transactionDatabase) {
        this.searcher = new Searcher(bookDatabase,userDatabase);
        this.bookDatabase = bookDatabase;
        this.userDatabase = userDatabase;
        this.transactionDatabase = transactionDatabase;
        infoPage = new InfoPageGui(transactionDatabase);
        userLoggedIn = null;
        
        // Create the JFrame
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960,540);
        frame.setLocationRelativeTo(null);

        // Create the JPanel
        bookPanel = new JPanel();
        bookPanel.setLayout(null);

        // Creating the table
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return(false);
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().repaint();
        // Creating the scroll pane to be able to scroll through table
        scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(tableX,tableY,tableWidth,tableHeight);
        scrollPane.setVisible(true);
        

        // Book/User buttons
        bookButton = new JButton("Switch to Books");
        bookButton.setSize(cellWidth*2,cellHeight);
        bookButton.setLocation(tableX+tableWidth-(cellWidth*2),tableY-(cellHeight));
        userButton = new JButton("Switch to Users");
        userButton.setSize(cellWidth*2,cellHeight);
        userButton.setLocation(tableX+tableWidth-(cellWidth*2),tableY-(cellHeight));
        bookButton.setVisible(false);
        loginButton = new JButton("Login");
        loginButton.setSize(cellWidth,cellHeight*2);
        loginButton.setLocation(960-cellWidth-20,10);

        currentButton = userButton;

    }

    // Getter/Setter for UserLoggedIn
    public User getUser() {
        return(userLoggedIn);
    }
    public void setUser(User user) {
        userLoggedIn = user;
        refreshPage();
    }

    // This method is called to refresh the page after searching, or after switching between books and users
    private void refreshPage() {
        // Reset cell width based on how many columns there are
        cellWidth = tableWidth/columnNames.length;
        // Refresh row and column count
        tableModel.setRowCount(bookDatabase.getSize());
        tableModel.setColumnCount(columnNames.length);
        // Refresh the data in the table
        if (currentButton == userButton) {
            getBookData(bookDatabase.getArray(),tableModel,table);
        }
        if (currentButton == bookButton) {
            getUserData(userDatabase.getArray(),tableModel,table);
        }
        // Refresh column headers
        for(int i = 0;i < columnNames.length;i++) {
            table.getColumnModel().getColumn(i).setHeaderValue(columnNames[i]);
        }
        table.getTableHeader().repaint();

        //refresh user
        if (userLoggedIn == null) {
            loginButton.setVisible(true);
        } else {
            loginButton.setVisible(false);
        }
    }

    // This inputs the book data into the cells
    private void getBookData(ArrayList<Book> database,DefaultTableModel tableModel, JTable table) {
        // Reset row count to clear the data
        tableModel.setRowCount(0);
        tableModel.setRowCount(database.size());
        // Input book data into the cells
        for(int i = 0; i < database.size();i++) {
            table.setValueAt(database.get(i).getTitle(),i,0);
            table.setValueAt(database.get(i).getAuthor(),i,1);
            table.setValueAt(database.get(i).getGenre(),i,2);
            table.setValueAt(database.get(i).getPrimaryKey(),i,3);
            table.setValueAt(String.valueOf(database.get(i).getAvailability()),i,4);
        }
    }

    // This inputs the user data into the cells
    private void getUserData(ArrayList<User> database,DefaultTableModel tableModel, JTable table) {
        // Reset row count to clear the data
        tableModel.setRowCount(0);
        tableModel.setRowCount(database.size());
        // Input user data into the cells
        for(int i = 0; i < database.size();i++) {
            table.setValueAt(database.get(i).getFullName(),i,0);
            table.setValueAt(database.get(i).getUsername(),i,1);
            table.setValueAt(database.get(i).getPrimaryKey(),i,2);
            table.setValueAt(String.valueOf(database.get(i).getActive()),i,3);
        }
    }

    // This method does all of the logic for the search page
    public void searchPage() {
        // Refresh the page
        refreshPage();

        // Search bar
        JTextField searchField = new JTextField("");
        searchField.setSize(tableWidth-buttonWidth,cellHeight);
        searchField.setLocation(tableX,tableY-(cellHeight));
        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentButton == userButton) {
                    getBookData(searcher.searchBook(searchField.getText()),tableModel,table);
                } else {
                    getUserData(searcher.searchUser(searchField.getText()),tableModel,table);
                }
                return;
            }
        });
        bookPanel.add(searchField);
        
        // Logic for clicking on a table cell
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (currentButton == userButton) {
                    infoPage.bookInfoPage(bookDatabase.getEntry(table.getValueAt(row,3).toString()));
                } else {
                    infoPage.userInfoPage(userDatabase.getEntry(table.getValueAt(row,2).toString()));
                }
            }
        });

        // Add the scrollpane(table) to the panel
        bookPanel.add(scrollPane);

        // Book/user button logic
        userButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userButton.setVisible(false);
                bookButton.setVisible(true);
                currentButton = bookButton;
                columnNames = userColumnNames;
                refreshPage();
                return;
            }
        });
        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userButton.setVisible(true);
                bookButton.setVisible(false);
                currentButton = userButton;
                columnNames = bookColumnNames;
                refreshPage();
                return;
            }
        });
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });


        bookPanel.add(userButton);
        bookPanel.add(bookButton);
        bookPanel.add(loginButton);

        // Add this panel to the frame
        frame.add(bookPanel);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        BookDatabase bookDatabase = new BookDatabase();
        UserDatabase userDatabase = new UserDatabase();
        TransactionDatabase transactionDatabase = new TransactionDatabase();
        
        SearchPageGui mainPageGui = new SearchPageGui(bookDatabase, userDatabase, transactionDatabase);
        Book book1 = new Book("MyBook","Isaac","Fantasy",202039,2003);
        Book book2 = new Book("Another Book","Michael","Sci-Fi",504039,2022);
        Book book3 = new Book("Yet Another One","Jonah","Fantasy",706039,2006);
        Book book4 = new Book("MyBook","Isaac","Fantasy",202039,2003);
        Book book5 = new Book("Another Book","Michael","Sci-Fi",504039,2022);
        Book book6 = new Book("Yet Another One","Jonah","Fantasy",706039,2006);
        bookDatabase.addEntry(book1);
        bookDatabase.addEntry(book2);
        bookDatabase.addEntry(book3);
        bookDatabase.addEntry(book4);
        bookDatabase.addEntry(book5);
        bookDatabase.addEntry(book6);

        User user1 = new User("Isaac Gunderson","jpgundi","1234",0);
        User user2 = new User("Jonah Hampton","tempest","1234",0);
        User user3 = new User("Michael Toon","mtoon","1234",0);
        userDatabase.addEntry(user1);
        userDatabase.addEntry(user2);
        userDatabase.addEntry(user3);

        Transaction transaction1 = new Transaction(user1,book2,"09/07/2023","09/22/2023");
        transactionDatabase.addEntry(transaction1);
        mainPageGui.searchPage();
        //mainPageGui.searchUserPage(userDatabase.getArray());
    }

}
