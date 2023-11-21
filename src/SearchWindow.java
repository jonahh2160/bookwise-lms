//IG 11/15
// This opens a tiny search window similar to searchpage

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.*;

public class SearchWindow {
    private Searcher searcher;
    private BookDatabase bookDatabase;
    private UserDatabase userDatabase;
    private JFrame frame;
    private JPanel panel;
    private JTextField searchField;
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JLabel searchLabel;
    private int frameWidth = 500;
    private int frameHeight = 400;
    private int searchHeight = 25;
    private String[] bookColumnNames = { "Title", "Author", "ID", "Availability" };
    private String[] columnNames = bookColumnNames;
    private int currentType = 0;
    // Colors
    private Color navyColor = new Color(34, 32, 52);
    private Color darkNavyColor = new Color(24, 23, 43);

    // Constructor
    public SearchWindow(CreateInstanceGui createPage, BookDatabase bookDatabase, UserDatabase userDatabase) {
        this.searcher = new Searcher(bookDatabase, userDatabase);
        this.bookDatabase = bookDatabase;
        this.userDatabase = userDatabase;
        frame = new JFrame("Search Book");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(navyColor);

        // Creating the table
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return (false);
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        tableModel.setColumnCount(columnNames.length);
        for (int i = 0; i < columnNames.length; i++) {
            table.getColumnModel().getColumn(i).setHeaderValue(columnNames[i]);
        }
        table.getTableHeader().repaint();
        table.getTableHeader().setBackground(darkNavyColor);
        table.getTableHeader().setForeground(Color.WHITE);
        // Creating the scroll pane to be able to scroll through table
        scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 35 + searchHeight, frameWidth - 35, frameHeight - 135);
        scrollPane.setVisible(true);
        scrollPane.getViewport().setBackground(darkNavyColor);

        // Search label
        searchLabel = new JLabel("🔍 Search:");
        searchLabel.setSize(200, 35);
        searchLabel.setLocation(10, 10);
        searchLabel.setForeground(Color.WHITE);

        // Search bar
        searchField = new JTextField("");
        searchField.setSize(frameWidth - 35, searchHeight);
        searchField.setLocation(10, 35);
        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshPage();
                return;
            }
        });

        // Logic for clicking on a transaction cell
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                String id;
                if (currentType == 0) {
                    id = String.valueOf(table.getValueAt(row, 2));
                    createPage.setTe2(id);
                } else {
                    id = String.valueOf(table.getValueAt(row, 2));
                    createPage.setTe1(id);
                }
                frame.dispose();
            }
        });

    }

    // Refresh search
    private void refreshPage() {
        // Refresh row and column count
        if (currentType == 0) {
            tableModel.setRowCount(bookDatabase.getSize());
        } else {
            tableModel.setRowCount(userDatabase.getSize());
        }
        tableModel.setColumnCount(columnNames.length);
        // Refresh the data in the table
        if (currentType == 0) {
            getBookData(searcher.searchBook(searchField.getText()), tableModel, table);
        }
        if (currentType == 1) {
            getUserData(searcher.searchUser(searchField.getText()), tableModel, table);
        }
        // Refresh column headers
        for (int i = 0; i < columnNames.length; i++) {
            table.getColumnModel().getColumn(i).setHeaderValue(columnNames[i]);
        }
        table.getTableHeader().repaint();
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
            table.setValueAt(database.get(i).getPrimaryKey(), i, 2);
            if (database.get(i).getAvailability()) {
                table.setValueAt("✓", i, 3);
            } else {
                table.setValueAt("✗", i, 3);
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

    // Create the search window
    public void searchWindow(int type) {
        currentType = type;
        refreshPage();
        panel.removeAll();

        panel.add(searchField);
        panel.add(scrollPane);
        panel.add(searchLabel);

        frame.add(panel);
        frame.setVisible(true);
    }

}