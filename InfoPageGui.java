// IG 11/10

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.*;
import java.lang.Math;

public class InfoPageGui {
    private JFrame frame;
    private JPanel infoPanel;
    private TransactionDatabase transactionDatabase;
    private DefaultTableModel transactionTableModel, infoTableModel;
    private JTable transactionTable, infoTable;
    private JScrollPane scrollPane, infoScrollPane;
    private JButton editButton, closeButton, transactionButton;
    private JLabel infoLabel, transactionLabel;
    private ManagementPageGui managementPage;
    final int tableX = 20;
    final int tableY = 150;
    final int tableWidth = 480;
    final int tableHeight = 270;
    final int infoX = 20;
    final int infoY = 50;
    final int infoWidth = 480;
    final int infoHeight = 37;
    final String[] transactionColumnNames = {"User","Book","Borrowed","Due","Returned","ID"};
    final String[] bookColumnNames = {"Title","Author","Genre","ID","Available"};
    final String[] userColumnNames = {"Name","Username","ID","Status","Balance"};
    private String[] columnNames = bookColumnNames;
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private int currentType = 0;
    private SearchPageGui searchPage;
    private CreateInstanceGui createPage;
    private Book book;
    private User user;
    // Colors
    private Color navyColor = new Color(34,32,52);
    private Color darkNavyColor = new Color(24,23,43);
    private Color goldColor = new Color(208,201,46);

    public InfoPageGui(BookDatabase bookDatabase, UserDatabase userDatabase, TransactionDatabase transactionDatabase, SearchPageGui searchPage, CreateInstanceGui createPage) {
        this.transactionDatabase = transactionDatabase;
        this.searchPage = searchPage;
        this.createPage = createPage;
        InfoPageGui infoPage = this;
        managementPage = new ManagementPageGui(bookDatabase, userDatabase, transactionDatabase, this,searchPage);
        frame = new JFrame("Info Page");
        frame.setSize(640,480);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        columnNames = bookColumnNames;

        infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBackground(navyColor);

        // Creating info table
        infoTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return(false);
            }
        };
        infoTable = new JTable(infoTableModel);
        infoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        infoTable.getTableHeader().setBackground(darkNavyColor);
        infoTable.getTableHeader().setForeground(Color.WHITE);
        // Creating the scroll pane for info
        infoScrollPane = new JScrollPane(infoTable);
        infoScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        infoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        infoScrollPane.setBounds(infoX,infoY,infoWidth,infoHeight);
        infoScrollPane.setVisible(true);

        // Creating the transaction table
        transactionTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return(false);
            }
        };
        transactionTable = new JTable(transactionTableModel);
        transactionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        transactionTableModel.setColumnCount(transactionColumnNames.length);
        for (int i=0;i<transactionColumnNames.length;i++) {
            transactionTable.getColumnModel().getColumn(i).setHeaderValue(transactionColumnNames[i]);
        }
        transactionTable.getTableHeader().repaint();
        transactionTable.getTableHeader().setBackground(darkNavyColor);
        transactionTable.getTableHeader().setForeground(Color.WHITE);
        // Creating the scroll pane to be able to scroll through table
        scrollPane = new JScrollPane(transactionTable);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(tableX,tableY,tableWidth,tableHeight);
        scrollPane.setVisible(true);
        scrollPane.getViewport().setBackground(darkNavyColor);

        //Buttons
        closeButton = new JButton("Close");
        closeButton.setSize(100,30);
        closeButton.setLocation(510,390);
        editButton = new JButton("Edit");
        editButton.setSize(100,30);
        editButton.setLocation(infoX + infoWidth+10,infoY);
        transactionButton = new JButton("Add");
        transactionButton.setSize(100,30);
        transactionButton.setLocation(510,390-40);

        //Lables
        infoLabel = new JLabel("");
        infoLabel.setLocation(infoX,infoY-20);
        infoLabel.setSize(100,20);
        infoLabel.setForeground(Color.WHITE);
        transactionLabel = new JLabel("Transaction History:");
        transactionLabel.setLocation(tableX,tableY-20);
        transactionLabel.setSize(200,20);
        transactionLabel.setForeground(Color.WHITE);

        // Close button logic
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // Edit button logic
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editButton.setEnabled(false);
                if (currentType == 0) {
                    managementPage.changeBookInfo(book);
                } else if (currentType == 1) {
                    managementPage.changeUserInfo(user);
                }
                editButton.setEnabled(true);
            }
        });

        // add transaction button logic
        transactionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentType == 0) {
                    createPage.createTransactionPage(book,infoPage);
                } else {
                    createPage.createTransactionPage(user,infoPage);
                }
            }
        });

        // Logic for clicking on a transaction cell
        transactionTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = transactionTable.rowAtPoint(e.getPoint());
                String id = String.valueOf(transactionTable.getValueAt(row,5));
                if (searchPage.getUser() != null && searchPage.getUser().getPerms() > 0) {
                    Transaction transaction = transactionDatabase.getEntry(id);
                    managementPage.changeTransactionInfo(transaction);
                }
            }
        });

    }

    // Resets the column names (for switching between books and users)
    private void refreshColumnNames() {
        infoTableModel.setRowCount(1);
        infoTableModel.setColumnCount(0);
        infoTableModel.setColumnCount(columnNames.length);
        for (int i = 0;i<columnNames.length;i++) {
            infoTable.getColumnModel().getColumn(i).setHeaderValue(columnNames[i]);
        }
        infoTable.getTableHeader().repaint();
    }

    public void refreshTransactions() {
         transactions.clear();
        if (currentType == 0) {
            transactions = transactionDatabase.getBookTransactions(book);
            getTransactionData(transactions);
        } else {
            transactions = transactionDatabase.getUserTransactions(user);
            getTransactionData(transactions);
        }
    }

    // Gets the current type (user or book)
    public int getType() {
        return (currentType);
    }
    public JFrame getFrame() {
        return(frame);
    }

    // Gets the book's info and stores it in table
    private void getBookInfo(Book book) {
        // Input book data into the cells
        infoLabel.setText("Book Info:");
        columnNames = bookColumnNames;
        refreshColumnNames();
        infoTable.setValueAt(book.getTitle(),0,0);
        infoTable.setValueAt(book.getAuthor(),0,1);
        infoTable.setValueAt(book.getGenre(),0,2);
        infoTable.setValueAt(book.getPrimaryKey(),0,3);
        if (book.getAvailability()) {
            infoTable.setValueAt("✓", 0, 4);
        } else {
            infoTable.setValueAt("✗", 0, 4);
        }
        transactions.clear();
        transactions = transactionDatabase.getBookTransactions(book);
        getTransactionData(transactions);
    }

    // Gets the user's info and stores it in table
    private void getUserInfo(User user) {
        // Input user data into the cells
        infoLabel.setText("User Info:");
        columnNames = userColumnNames;
        refreshColumnNames();
        infoTable.setValueAt(user.getFullName(),0,0);
        infoTable.setValueAt(user.getUsername(),0,1);
        infoTable.setValueAt(user.getPrimaryKey(),0,2);
        if (user.getActive()) {
            infoTable.setValueAt("✓ Active", 0, 3);
        } else {
            infoTable.setValueAt("✗ Inactive", 0, 3);
        }
        double balance = user.getAccountBalance();
        String sign;
        if (balance < 0) {sign = "-";} else {sign = "";};
        infoTable.setValueAt(sign + "$" + Math.abs(balance),0,4);
        transactions.clear();
        transactions = transactionDatabase.getUserTransactions(user);
        getTransactionData(transactions);
    }

    // This inputs the transaction data into the cells
    private void getTransactionData(ArrayList<Transaction> database) {
        // Reset row count to clear the data
        transactionTableModel.setRowCount(0);
        transactionTableModel.setRowCount(database.size());
        // Input book data into the cells
        for(int i = 0; i < database.size();i++) {
            transactionTable.setValueAt(database.get(i).getUser().getUsername(),i,0);
            transactionTable.setValueAt(database.get(i).getBook().getTitle(),i,1);
            transactionTable.setValueAt(database.get(i).getDateBorrowed(),i,2);
            transactionTable.setValueAt(database.get(i).getDateDue(),i,3);
            transactionTable.setValueAt(database.get(i).getDateReturned(),i,4);
            transactionTable.setValueAt(database.get(i).getPrimaryKey(),i,5);
        }
    }

    // Call this method to open a book info page
    public void bookInfoPage(Book book) {
        this.book = book;
        currentType = 0;
        // Remove everything from panel before we add components
        infoPanel.removeAll();
        // Put book info in table
        getBookInfo(book);

        // Add Info Pane
        infoPanel.add(infoScrollPane);

        

        // Add transaction window and edit button
        if (searchPage.getUser() != null && searchPage.getUser().getPerms() > 0) {
            infoPanel.add(transactionLabel);
            infoPanel.add(scrollPane);
            infoPanel.add(editButton);
            infoPanel.add(transactionButton);
        }

        // Add buttons
        infoPanel.add(closeButton);

        // Add labels
        infoPanel.add(infoLabel);

        frame.add(infoPanel);
        frame.setVisible(true);
    }

    // This method calls the book info page with the book it already has (for refreshing)
    public void bookInfoPage() {
        bookInfoPage(book);
    }

    public void userInfoPage(User user) {
        this.user = user;
        currentType = 1;
        // Remove everything from panel before we add components
        infoPanel.removeAll();
        // Put user info in table
        getUserInfo(user);

        // Add Info Pane
        infoPanel.add(infoScrollPane);

        // Add transaction window and edit button
        if (searchPage.getUser() != null && (searchPage.getUser().getPerms() > 0 || searchPage.getUser() == user)) {
            infoPanel.add(transactionLabel);
            infoPanel.add(scrollPane);
        }

        // Add edit button
        if (searchPage.getUser() != null && searchPage.getUser().getPerms() > 0) {
            infoPanel.add(editButton);
            infoPanel.add(transactionButton);
        }

        // Add close button
        infoPanel.add(closeButton);

        // Add labels
        infoPanel.add(infoLabel);

        frame.add(infoPanel);
        frame.setVisible(true);
    }

    // This method calls the user info page with the user it already has (for refreshing)
    public void userInfoPage() {
        userInfoPage(user);
    }

}
