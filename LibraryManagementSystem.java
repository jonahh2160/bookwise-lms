import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Step 1: Define the database connection parameters.
        String url = "jdbc:mysql://localhost:3306/LibraryManagementSystem"; // JDBC URL for the database
        String username = "your_username"; // Your MySQL username
        String password = "your_password"; // Your MySQL password
        // Step 2: Establish a connection to the database.
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            // Step 3: Create an SQL INSERT statement.
            String insertQuery = "INSERT INTO Users (FirstName, LastName, Email, Phone, Address)VALUES (?, ?, ?, ?, ?)";
            // Step 4: Prepare and execute the SQL statement.
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                // Set the parameter values for the INSERT statement.
                preparedStatement.setString(1, "John");
                preparedStatement.setString(2, "Doe");
                preparedStatement.setString(3, "john.doe@email.com");
                preparedStatement.setString(4, "123-456-7890");
                preparedStatement.setString(5, "123 Main St, City");
                // Execute the INSERT statement.
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("New user record inserted successfully.");
                } else {
                    System.out.println("Insert operation failed.");
                }
            }

            // Step 5: Close the database connection.
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
