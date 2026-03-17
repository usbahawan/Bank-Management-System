# 🏦 Bank Management System

A robust, role-based desktop application designed to streamline banking operations for Managers, Customer Service Representatives (CSRs), and Account Holders. This system provides a secure and intuitive interface for managing accounts, processing transactions, and maintaining financial records.

## 🚀 Key Features

### 👔 Manager Dashboard
- **CSR Management:** Register new Customer Service Representatives and remove inactive ones.
- **Account Oversight:** View a comprehensive list of all account holders and their detailed profiles.
- **System Administration:** Monitor overall bank operations and user activity.

### 🎧 Customer Service Representative (CSR) Dashboard
- **Account Creation:** Efficiently open new Savings or Current accounts with integrated validation.
- **Data Management:** Search, view, and update account holder details.
- **Transaction Processing:** Handle physical cash deposits for customers securely.
- **Account Maintenance:** Close accounts upon customer request.

### 👥 Account Holder (User) Dashboard
- **Self-Service Banking:** Securely log in to manage personal finances.
- **Balance Inquiry:** Real-time access to current account balances with privacy-focused viewing.
- **Withdrawals:** Process withdrawal requests with automated minimum balance checks.
- **Transaction History:** View a detailed log of all past deposits and withdrawals with timestamps.

## 🛠️ Technology Stack

- **Lanuage:** Java (JDK 8+)
- **GUI Framework:** Java Swing & AWT (for a responsive desktop experience)
- **Database:** Microsoft SQL Server
- **Connectivity:** JDBC (Java Database Connectivity)
- **Utilities:** `JDateChooser` for intuitive date selection.

## 📋 Database Schema Overview

The system utilizes a relational database structure with the following key tables:
- `AccountHolderDetails`: Stores personal information, account types, and balances.
- `CSR_Details`: Stores credentials and profiles for Customer Service Representatives.
- `ManagerDetails`: Stores administrator credentials.
- `Transactions`: Records every financial movement with unique IDs and timestamps.

## ⚙️ Installation & Setup

### Prerequisites
- Java Development Kit (JDK) installed and configured.
- Microsoft SQL Server setup and running.
- `mssql-jdbc` driver added to the project classpath.

### Configuration
1. **Database Setup:** Execute the SQL scripts provided in the `Database Tables` directory to initialize the required tables.
2. **Connection String:** Update the `ConnectionProvider.java` file with your local SQL Server credentials:
   ```java
   String url = "jdbc:sqlserver://localhost:1433;databaseName=BankManagementSystem;user=YOUR_USERNAME;password=YOUR_PASSWORD;";
   ```
3. **Run Application:** Execute `BankManagementSystem.java` to launch the main dashboard.

## 👥 Contributors
- **Muhammad Huzaifa** - Lead Developer

---
*Developed as a Semester Project for the Object-Oriented Programming (OOP) course.*
