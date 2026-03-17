package bankmanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AccountHolder extends Person
{
    private String cnic;
    private long accountNumber;
    private String pin;
    private String accountType;
    private String accountOpeningDate;
    private String ATM;
    private String chequeBook;
    private long balance;
    ArrayList<Transaction> transactionList=new ArrayList<>();

    public AccountHolder() {
        
    }

    public AccountHolder(String cnic, String accountType, String accountOpeningDate, String ATM, String chequeBook, String name, String fatherName, String phNo, String dob, String address,String gender,String pin,long balance) {
        super(name, fatherName, phNo, dob, address, gender);
        this.cnic=cnic;
        this.accountType = accountType;
        this.accountOpeningDate = accountOpeningDate;
        this.ATM = ATM;
        this.chequeBook = chequeBook;
        this.pin=pin; 
        this.balance=balance;
    }
    
     public long withdrawAmount(Transaction tr,String accountNumber){
         transactionList.add(tr);
         long transactionID = 0;
         try{
             long amount=tr.getAmount();
             Connection con=ConnectionProvider.getcon();
             Statement st1=con.createStatement();
             ResultSet rs1=st1.executeQuery("select * from AccountHolderDetails where accountNumber='"+accountNumber+"'");////
             if(rs1.next()){
                long balance=rs1.getLong("balance");
                if((balance-amount)<0)
                    JOptionPane.showMessageDialog(null, "Not enough balance. Your current balance is: "+balance);
                else{
                    PreparedStatement ps1=con.prepareStatement("insert into Transactions (accountNumber,transactionType,amount) values (?,?,?)");
                    ps1.setString(1,accountNumber);
                    ps1.setString(2, tr.getTransactionType());
                    ps1.setLong(3, amount);
                    ps1.executeUpdate();
                    
                    PreparedStatement ps2=con.prepareStatement("UPDATE AccountHolderDetails SET balance = balance- ? WHERE accountNumber ='"+accountNumber+"'");////
                    ps2.setLong(1, amount);
                    ps2.executeUpdate();
                    
                    Statement st2=con.createStatement();
                    ResultSet rs2=st2.executeQuery("select transactionID from Transactions where accountNumber='"+accountNumber+"'");
                    if(rs2.next()){
                       transactionID=rs2.getLong("transactionID");
                    }
                }
            }
             else{
                 JOptionPane.showMessageDialog(null, "You have not deposited any amount yet");
             }
             con.close();
         }
         catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
         
        return transactionID;
    }
    public ResultSet viewTransactonHistory(String accountNumber){
        try{
            Connection con=ConnectionProvider.getcon();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM Transactions where accountNumber='"+accountNumber+"'");
            if(rs.next())
                return rs;
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    public long viewBalance(String accountNumber){
        long balance=-1;
        try{
            Connection con=ConnectionProvider.getcon();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("SELECT balance FROM AccountHolderDetails where accountNumber='"+accountNumber+"'");///
            if(rs.next())
                balance=rs.getLong("balance");
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return balance;
    }
    public boolean login(String accountNumber,String pin){
        boolean exist=false;
        try{
            Connection con=ConnectionProvider.getcon();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from AccountHolderDetails where accountNumber='"+accountNumber+"'and pin='"+pin+"'");
            if(rs.next())
                exist=true;
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        return exist;
    }
      
    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }
    
    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountOpeningDate() {
        return accountOpeningDate;
    }

    public void setAccountOpeningDate(String accountOpeningDate) {
        this.accountOpeningDate = accountOpeningDate;
    }

    public String getATM() {
        return ATM;
    }

    public void setATM(String ATM) {
        this.ATM = ATM;
    }

    public String getChequeBook() {
        return chequeBook;
    }

    public void setChequeBook(String chequeBook) {
        this.chequeBook = chequeBook;
    }
    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
