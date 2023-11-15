// IG 11/7
// Used for testing the GUI components

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.io.IOException;

public class SearchPageGui {
    private Searcher searcher;
    private Sorter sorter;
    private BookDatabase bookDatabase;
    private UserDatabase userDatabase;
    private TransactionDatabase transactionDatabase;
    private JFrame frame;
    private JPanel bookPanel;
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton bookButton, userButton, currentButton, loginButton, accountButton, manageButton, sortButton;
    private InfoPageGui infoPage;
    private UserLoginGui loginPage;
    private CreateInstanceGui createPage;
    private JTextField searchField;
    final int tableX = 160;
    final int tableY = 100;
    final int tableWidth = 600;
    final int tableHeight = 384;
    final String[] bookColumnNames = { "Title", "Author", "Genre", "Year", "ID", "Available" };
    final String[] userColumnNames = { "Name", "Username", "ID", "Status" };
    final String[] bookSortNames = {"Sorting by Title","Sorting by Author","Sorting by Year","Sorting by Available"};
    final String[] userSortNames = {"Sorting by Name","Sorting by Username"};
    private int sortIndex = 0;
    private String[] columnNames = bookColumnNames;
    private int cellWidth = tableWidth / columnNames.length;
    private User userLoggedIn;
    final int cellHeight = 20;
    final int buttonWidth = cellWidth * 2;
    // Colors
    private Color navyColor = new Color(34, 32, 52);
    private Color darkNavyColor = new Color(24, 23, 43);
    private Color goldColor = new Color(208, 201, 46);
    private Color grayColor = new Color(150, 150, 150);
    // Images
    private BufferedImage lmsImage, searchImage;
    private JLabel lmsLabel, searchText;
    private URL imgPath;

    // Constructor that initializes the frame, panel, table model, scroll pane,
    // table, and buttons
    public SearchPageGui(BookDatabase bookDatabase, UserDatabase userDatabase,
            TransactionDatabase transactionDatabase, DatabaseManager databaseManager) {
        this.searcher = new Searcher(bookDatabase, userDatabase);
        this.sorter = new Sorter();
        this.bookDatabase = bookDatabase;
        this.userDatabase = userDatabase;
        this.transactionDatabase = transactionDatabase;
        loginPage = new UserLoginGui(userDatabase, this);
        createPage = new CreateInstanceGui(bookDatabase, userDatabase, transactionDatabase, this,databaseManager);
        infoPage = new InfoPageGui(bookDatabase, userDatabase, transactionDatabase, this, createPage, databaseManager);
        userLoggedIn = null;

        // Create the JFrame
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960, 540);
        frame.setLocationRelativeTo(null);

        // Create the JPanel
        bookPanel = new JPanel();
        bookPanel.setLayout(null);
        bookPanel.setBackground(navyColor);

        // Creating the images
        try {
            imgPath = getClass().getResource("lmsicon.png");
            if (imgPath == null) {
                System.out.println("Logo could not be found! Skipping...");
            } else {
                lmsImage = ImageIO.read(imgPath);
                lmsLabel = new JLabel(new ImageIcon(lmsImage));
                lmsLabel.setSize(136, 231);
                lmsLabel.setLocation(10, 130);
            }
        } catch (IOException ex) {
        }
        searchText = new JLabel("🔍 Search:");
        searchText.setSize(200, 40);
        searchText.setLocation(tableX, tableY - 52);
        searchText.setForeground(Color.WHITE);

        // Creating the table
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return (false);
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().repaint();
        table.getTableHeader().setReorderingAllowed(false);
        // table.setSelectionBackground(darkNavyColor);
        table.setGridColor(navyColor);
        // table.setSelectionForeground(Color.WHITE);
        table.getTableHeader().setBackground(darkNavyColor);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setForeground(darkNavyColor);
        // Creating the scroll pane to be able to scroll through table
        scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(tableX, tableY, tableWidth, tableHeight);
        scrollPane.setVisible(true);
        scrollPane.getViewport().setBackground(darkNavyColor);
        scrollPane.getVerticalScrollBar().setForeground(goldColor);
        scrollPane.getVerticalScrollBar().setBackground(navyColor);

        // Book/User buttons
        bookButton = new JButton("↻ Books");
        bookButton.setSize(cellWidth * 1 + 14, cellHeight);
        bookButton.setLocation(tableX + tableWidth - (cellWidth * 1) - 14, tableY - (cellHeight));
        bookButton.setVisible(false);
        bookButton.setForeground(darkNavyColor);
        userButton = new JButton("↻ Users");
        userButton.setSize(cellWidth * 1 + 14, cellHeight);
        userButton.setLocation(tableX + tableWidth - (cellWidth * 1) - 14, tableY - (cellHeight));
        userButton.setEnabled(false);
        userButton.setForeground(darkNavyColor);
        sortButton = new JButton(bookSortNames[0]);
        sortButton.setSize(cellWidth * 2 - 6, cellHeight);
        sortButton.setLocation(tableX + tableWidth - (cellWidth * 3) - 8, tableY - (cellHeight));
        loginButton = new JButton("Login");
        loginButton.setSize(cellWidth, cellHeight * 2);
        loginButton.setLocation(960 - cellWidth - 30, 540 - (cellHeight * 2) - 50);
        // loginButton.setBackground(goldColor);
        loginButton.setForeground(navyColor);
        manageButton = new JButton("Manage");
        manageButton.setEnabled(false);
        manageButton.setSize(cellWidth, cellHeight * 2);
        manageButton.setLocation(960 - cellWidth - 30, 10);
        accountButton = new JButton("My Account");
        accountButton.setSize(cellWidth, cellHeight * 2);
        accountButton.setLocation(960 - cellWidth - 30, 540 - (cellHeight * 2) - 50 - (cellHeight * 2) - 20);
        // accountButton.setBackground(goldColor);
        accountButton.setForeground(darkNavyColor);
        currentButton = userButton;

        // Search bar
        searchField = new JTextField("");
        searchField.setSize(tableWidth - cellWidth * 3 - 8, cellHeight);
        searchField.setLocation(tableX, tableY - (cellHeight));
        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentButton == userButton) {
                    getBookData(bookSearchAndSort(searchField.getText()), tableModel, table);
                } else {
                    getUserData(userSearchAndSort(searchField.getText()), tableModel, table);
                }
                return;
            }
        });

        // sort button logic
        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentButton == userButton) {
                    if (sortIndex < bookSortNames.length - 1) {
                        sortIndex += 1;
                    } else {sortIndex = 0;}
                    sortButton.setText(bookSortNames[sortIndex]);
                    refreshPage();
                } else {
                    if (sortIndex < userSortNames.length - 1) {
                        sortIndex += 1;
                    } else {sortIndex = 0;}
                    sortButton.setText(userSortNames[sortIndex]);
                    refreshPage();
                }

                refreshPage();
                return;
            }
        });

    }

    // Getter/Setter for UserLoggedIn
    public User getUser() {
        return (userLoggedIn);
    }

    public void setUser(User user) {
        userLoggedIn = user;
        if (user == null) {
            userButton.setVisible(true);
            userButton.setEnabled(false);
            bookButton.setVisible(false);
            currentButton = userButton;
            columnNames = bookColumnNames;
        } else {
            if (user.getPerms() == 0) {
                userButton.setVisible(true);
                userButton.setEnabled(false);
                bookButton.setVisible(false);
                currentButton = userButton;
                columnNames = bookColumnNames;
            }
            if (user.getPerms() == 1) {
                userButton.setVisible(true);
                userButton.setEnabled(true);
                bookButton.setVisible(false);
            }
        }
        refreshPage();
    }

    // This method is called to refresh the page after searching, or after switching
    // between books and users
    public void refreshPage() {
        // Reset cell width based on how many columns there are
        cellWidth = tableWidth / columnNames.length;
        // Refresh row and column count
        tableModel.setRowCount(bookDatabase.getSize());
        tableModel.setColumnCount(columnNames.length);
        // Refresh the data in the table
        if (currentButton == userButton) {
            getBookData(bookSearchAndSort(searchField.getText()), tableModel, table);
        }
        if (currentButton == bookButton) {
            getUserData(userSearchAndSort(searchField.getText()), tableModel, table);
        }
        // Refresh column headers
        for (int i = 0; i < columnNames.length; i++) {
            table.getColumnModel().getColumn(i).setHeaderValue(columnNames[i]);
        }
        table.getTableHeader().repaint();

        // refresh user
        if (userLoggedIn == null) {
            loginButton.setText("Login");
            accountButton.setEnabled(false);
            manageButton.setEnabled(false);
        } else {
            loginButton.setText("Log Out");
            accountButton.setEnabled(true);
            if (userLoggedIn.getPerms() > 0) {
                manageButton.setEnabled(true);
            } else {
                manageButton.setEnabled(false);
            }
            ;
        }

    }

    // This inputs the book data into the cells
    private void getBookData(ArrayList<Book> database, DefaultTableModel tableModel, JTable table) {
        // Reset row count to clear the data
        tableModel.setRowCount(0);
        tableModel.setRowCount(database.size());
        // Input book data into the cells
        for (int i = 0; i < database.size(); i++) {
            table.setValueAt(database.get(i).getTitle(), i, 0);
            table.setValueAt(database.get(i).getAuthor(), i, 1);
            table.setValueAt(database.get(i).getGenre(), i, 2);
            table.setValueAt(database.get(i).getYear(), i, 3);
            table.setValueAt(database.get(i).getPrimaryKey(), i, 4);
            if (database.get(i).getAvailability()) {
                table.setValueAt("✓", i, 5);
            } else {
                table.setValueAt("✗", i, 5);
            }
        }
    }

    // This inputs the user data into the cells
    private void getUserData(ArrayList<User> database, DefaultTableModel tableModel, JTable table) {
        // Reset row count to clear the data
        tableModel.setRowCount(0);
        tableModel.setRowCount(database.size());
        // Input user data into the cells
        for (int i = 0; i < database.size(); i++) {
            table.setValueAt(database.get(i).getFullName(), i, 0);
            table.setValueAt(database.get(i).getUsername(), i, 1);
            table.setValueAt(database.get(i).getPrimaryKey(), i, 2);
            if (database.get(i).getActive()) {
                table.setValueAt("✓ Active", i, 3);
            } else {
                table.setValueAt("✗ Inactive", i, 3);
            }
        }
    }

    // This method will search and sort
    private ArrayList<Book> bookSearchAndSort(String searchTerm) {
        ArrayList<Book> books = new ArrayList<Book>();
        books = searcher.searchBook(searchTerm);
        if (sortIndex == 0) {
            books = sorter.titleSorter(books);
        } else if (sortIndex == 1) {
            books = sorter.authorSorter(books);
        } else if (sortIndex == 2) {
            books = sorter.yearSorter(books);
        } else if (sortIndex == 3) {
            books = sorter.availabilitySorter(books);
        }
        return (books);
    }

    // This method will search and sort
    private ArrayList<User> userSearchAndSort(String searchTerm) {
        ArrayList<User> users = new ArrayList<User>();
        users = searcher.searchUser(searchTerm);
        if (sortIndex == 0) {
            users = sorter.nameSorter(users);
        } else if (sortIndex == 1) {
            users = sorter.usernameSorter(users);
        }
        return (users);
    }

    // This method does all of the logic for the search page
    public void searchPage() {
        // Refresh the page
        refreshPage();

        bookPanel.add(searchField);

        // Logic for clicking on a table cell
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (currentButton == userButton) {
                    infoPage.bookInfoPage(bookDatabase.getEntry(table.getValueAt(row, 4).toString()));
                } else {
                    infoPage.userInfoPage(userDatabase.getEntry(table.getValueAt(row, 2).toString()));
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
                sortIndex = 0;
                sortButton.setText(userSortNames[sortIndex]);
                refreshPage();
                //return;
            }
        });
        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userButton.setVisible(true);
                bookButton.setVisible(false);
                currentButton = userButton;
                columnNames = bookColumnNames;
                sortIndex = 0;
                sortButton.setText(bookSortNames[sortIndex]);
                refreshPage();
                return;
            }
        });
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loginButton.getText().equals("Login")) {
                    loginPage.logIn();
                } else {
                    String[] options = { "Yes", "No" };
                    int answer = JOptionPane.showOptionDialog(null, "Are you sure you want to log out?",
                            "Log Out", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
                            options[0]);
                    if (answer == 0) {
                        JOptionPane.showMessageDialog(null, "Logout successful!");
                        setUser(null);
                    }
                }
            }
        });
        accountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoPage.userInfoPage(userLoggedIn);
            }
        });
        manageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createPage.createInstancePage();
            }
        });

        bookPanel.add(userButton);
        bookPanel.add(bookButton);
        bookPanel.add(sortButton);
        bookPanel.add(loginButton);
        bookPanel.add(accountButton);
        bookPanel.add(manageButton);

        // Images
        if (imgPath != null) {
            bookPanel.add(lmsLabel);
        }
        bookPanel.add(searchText);

        // Add this panel to the frame
        frame.add(bookPanel);
        frame.setVisible(true);
    }

}
