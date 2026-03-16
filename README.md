# Bank Management System

A comprehensive desktop application designed to streamline banking operations for Managers, Customer Service Representatives (CSRs), and Account Holders. This project provides a robust interface for managing user accounts, tracking transactions, and maintaining financial security.

## 🚀 Key Features

### 👤 Manager Portal
- **CSR Management**: Add new Customer Service Representatives to the system or remove existing ones.
- **Account Oversight**: View a detailed list of all account holders, including their personal information and current balances.

### 🎧 Customer Service Representative (CSR)
- **Account Lifecycle**: Create new bank accounts or delete existing ones.
- **Member Search**: Easily search and view specific account details.
- **Financial Services**: Process cash deposits for account holders.

### 🏦 Account Holder Portal
- **Transaction Management**: Securely withdraw funds from the account.
- **Balance Inquiry**: View current and accurate account balances (interactive hover feature).
- **History Tracking**: Access a complete log of all past transactions.

## 🛠️ Technology Stack
- **Language**: [Java](https://www.oracle.com/java/)
- **Graphical User Interface**: [Java Swing](https://en.wikipedia.org/wiki/Swing_(Java))
- **Database Management**: [Microsoft SQL Server](https://www.microsoft.com/en-us/sql-server/)
- **Connectivity**: [JDBC (Java Database Connectivity)](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/)

## 📂 Project Structure
- `Code/`: Contains all Java source files (Models, Controllers, and GUI Frames).
- `Database Tables/`: Visual representations of the database schema (AccountHolder, CSR, Manager, and Transactions).
- `Project Report.pdf`: Detailed documentation and analysis of the project.
- `Project Video.mp4`: A demonstration video showing the application in action.

## 🔧 Setup & Installation

### Prerequisites
1.  **Java JDK 8 or higher** installed on your machine.
2.  **Microsoft SQL Server** installed and running.
3.  **SQL Server JDBC Driver** added to your project's build path.

### Database Configuration
1.  Create a SQL Server database named `BankManagementSystem`.
2.  Create tables according to the schemas provided in the `Database Tables/` directory.
3.  Ensure your SQL Server instance allows SQL Server Authentication.

### Source Code Configuration
1.  Open `ConnectionProvider.java`.
2.  Update the connection string with your SQL Server credentials:
    ```java
    String url = "jdbc:sqlserver://localhost:1433;databaseName=BankManagementSystem;user=YOUR_USERNAME;password=YOUR_PASSWORD;...";
    ```

### Execution
1.  Compile all `.java` files in the `bankmanagementsystem` package.
2.  Run the main class: `BankManagementSystem.java`.

## 👥 Project Team
- **FA23-BCS-054**
- **FA23-BCS-058**

---
*Created as part of the Object-Oriented Programming (OOP) course (3rd Semester).*
