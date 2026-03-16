package bankmanagementsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class UserWork
{
    public void withdrawAmount(String accountNumber){
        JFrame amountWithdraw =new JFrame("WITHDRAW AMOUNT");
        amountWithdraw.setLayout(null);
        amountWithdraw.setSize(800, 700);
        amountWithdraw.setLocationRelativeTo(null);
        JLabel L1 = new JLabel("Enter Withdrawl Amount");
        JTextField txt_amount = new JTextField();
        JButton btn_withdraw=new JButton("Withdraw");
        
        amountWithdraw.add(L1);
        L1.setBounds(10, 30, 180, 50);
        amountWithdraw.add(txt_amount);
        txt_amount.setBounds(180, 40, 150, 30);
        
        amountWithdraw.add(btn_withdraw);
        btn_withdraw.setBounds(180, 100, 100, 30);
        btn_withdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                     String amount=txt_amount.getText();
                     long withdrawlAmount=Long.parseLong(amount);
                     if(withdrawlAmount<=0)
                         JOptionPane.showMessageDialog(null, "Withdrawl amount should be greater than zero");
                     else{
                         try{
                             Connection con=ConnectionProvider.getcon();
                             Statement st=con.createStatement();
                             ResultSet rs=st.executeQuery("select * from AccountHolderDetails where accountNumber='"+accountNumber+"'");
                             if(rs.next()){
                                 String accountType=rs.getString("accountType");
                                 if(accountType.equalsIgnoreCase("saving")){
                                         long balance=rs.getLong("balance");
                                         if((balance-withdrawlAmount)<1000){
                                             JOptionPane.showMessageDialog(null, "Minimum balance of thousand should be maintained for saving account");
                                         }
                                         else {
                                             String transactionType = "withdraw";
                                             Transaction tr = new Transaction(transactionType, withdrawlAmount);
                                             long transactionID = new AccountHolder().withdrawAmount(tr, accountNumber);
                                             if (transactionID != 0) {
                                                 JOptionPane.showMessageDialog(null, "Transaction successful. Your Transaction ID is: " + transactionID);
                                             }
                                         }
                                     
                                 }
                                 else {
                                     String transactionType = "withdraw";
                                     Transaction tr = new Transaction(transactionType, withdrawlAmount);
                                     long transactionID = new AccountHolder().withdrawAmount(tr, accountNumber);
                                     if (transactionID != 0) {
                                         JOptionPane.showMessageDialog(null, "Transaction successful. Your Transaction ID is: " + transactionID);
                                     }
                                 }
                                 
                             }
                         }
                         catch(Exception ex){
                             JOptionPane.showMessageDialog(null, e);
                         }
                     }
                }
                catch(NumberFormatException nfx){
                    JOptionPane.showMessageDialog(null, "Please enter amount");
                }
            }
        });
         
        amountWithdraw.setVisible(true);
    }
    public void viewTransactionHistory(String accountNumber){
        JFrame viewHistory = new JFrame("View Transaction History");
        viewHistory.setLayout(null);
        viewHistory.setSize(800, 700);
        viewHistory.setLocationRelativeTo(null);
        JLabel L1 = new JLabel("Account Number");
        JLabel L2 = new JLabel("Current Balance");
        JTextField txt_accountNumber = new JTextField();
        JTextField txt_balance = new JTextField();
        txt_accountNumber.setEditable(false);
        txt_balance.setEditable(false);
        
        viewHistory.add(L1);
        L1.setBounds(100, 30, 100, 30);
        viewHistory.add(txt_accountNumber);
        txt_accountNumber.setBounds(200, 30, 80, 30);
        
        viewHistory.add(L2);
        L2.setBounds(500, 30, 150, 30);
        viewHistory.add(txt_balance);
        txt_balance.setBounds(600, 30, 100, 30);
        txt_accountNumber.setText(accountNumber);

        String[] columnNames = {"Transaction ID", "Transaction Type", "Amount","Date/Time"};

        DefaultTableModel dtm = new DefaultTableModel(columnNames, 0);
        JTable transactionTable = new JTable(dtm);

       JScrollPane scrollPane = new JScrollPane(transactionTable);
       scrollPane.setBounds(100, 100, 600, 300);
       viewHistory.add(scrollPane);
        try {
            ResultSet rs1=new AccountHolder().viewTransactonHistory(accountNumber);
            if(rs1!=null){
            while (rs1.next()) {
                String ID = rs1.getString("transactionID");
                String type = rs1.getString("transactionType");
                String amount = rs1.getString("amount");
                String date = rs1.getString("transactionDate");
                dtm.addRow(new Object[]{ID, type, amount,date});
            }
            }
            long balance=new AccountHolder().viewBalance(accountNumber);
            if(balance!=-1)
                txt_balance.setText(balance+"Rs/-"); 
        }
        catch (NullPointerException ne){
            JOptionPane.showMessageDialog(null,"No transactions exist");
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        viewHistory.setVisible(true);
    }
    public void viewBalance(String accountNumber){
        JFrame viewBalance = new JFrame("View Balance");
        viewBalance.setSize(800, 700);
        viewBalance.setLocationRelativeTo(null);
        viewBalance.setLayout(null);
        JLabel L1 = new JLabel("Balance");
        JTextField txt_balance = new JTextField();
        txt_balance.setEditable(false);
        txt_balance.setText("********");
        
        viewBalance.add(L1);
        L1.setBounds(300, 80, 100, 30);
        viewBalance.add(txt_balance);
        txt_balance.setBounds(400, 80, 80, 30);
        String balance=Long.toString(new AccountHolder().viewBalance(accountNumber));
        
        txt_balance.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if((Integer.parseInt(balance))!=-1)
                    txt_balance.setText(balance+"Rs/-");
            }
            public void mouseExited(MouseEvent e) {
                txt_balance.setText("********");
            }
        });
        viewBalance.setVisible(true);
    }
}
