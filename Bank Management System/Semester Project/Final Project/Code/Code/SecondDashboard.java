package bankmanagementsystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SecondDashboard {
    public void managerDashboardFrame(){
        JFrame managerDashboard =new JFrame("Dashboard");
        managerDashboard.setLayout(null);
        managerDashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JButton btn_addCSR=new JButton("Add CSR");
        JButton btn_deleteCSR=new JButton("Delete CSR");
        JButton btn_viewAccounts=new JButton("View Accounts");
        JButton btn_logout=new JButton("Log Out");
        //JButton btn_signup=new JButton("Sign up");
        
        
        managerDashboard.add(btn_addCSR);
        btn_addCSR.setBounds(550, 100, 200, 50);
        btn_addCSR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 new ManagerWork().csrAddFrame();
            }
        });
        
        managerDashboard.add(btn_deleteCSR);
        btn_deleteCSR.setBounds(550, 200, 200, 50);
        btn_deleteCSR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 new ManagerWork().csrDeleteFrame();
            }
        });
        
        managerDashboard.add(btn_viewAccounts);
        btn_viewAccounts.setBounds(550, 300, 200, 50);
        btn_viewAccounts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 new ManagerWork().viewAccounts();
            }
        });
        
        managerDashboard.add(btn_logout);
        btn_logout.setBounds(550, 400, 200, 50);
        btn_logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a;
                a = JOptionPane.showConfirmDialog(null,"Are you sure to log out","Select",JOptionPane.YES_NO_OPTION);
                if(a==0){
                   new LoginScreen().managerLoginFrame();
                   managerDashboard.dispose();
                }
            }
        });
        managerDashboard.setVisible(true);
    }
     
     public void csrDashboardFrame(){
        JFrame csrDashboard =new JFrame("Customer Service Representative Dashboard");
        csrDashboard.setLayout(null);
        csrDashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JButton btn_createAccount=new JButton("Create Account");
        JButton btn_deleteAccount=new JButton("Delete Account");
        JButton btn_viewAccount=new JButton("View Account");
        JButton btn_depositAmount=new JButton("Deposit Amount");
        JButton btn_logout=new JButton("Log Out");
        
        csrDashboard.add(btn_createAccount);
        btn_createAccount.setBounds(550, 100, 200, 50);
        btn_createAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 new CSR_Work().createAccount();
            }
        });
        
        csrDashboard.add(btn_deleteAccount);
        btn_deleteAccount.setBounds(550, 200, 200, 50);
        btn_deleteAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 new CSR_Work().deleteAccount();
            }
        });
        
        csrDashboard.add(btn_viewAccount);
        btn_viewAccount.setBounds(550, 300, 200, 50);
        btn_viewAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 new CSR_Work().viewAccount();
            }
        });
        
        csrDashboard.add(btn_depositAmount);
        btn_depositAmount.setBounds(550, 400, 200, 50);
        btn_depositAmount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 new CSR_Work().depositAmount();
            }
        });
        
        csrDashboard.add(btn_logout);
        btn_logout.setBounds(550, 500, 200, 50);
        btn_logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 int a;
                 a = JOptionPane.showConfirmDialog(null, "Are you sure to log out", "Select", JOptionPane.YES_NO_OPTION);
                 if (a == 0) {
                     new LoginScreen().csrLoginFrame();
                     csrDashboard.dispose();
                }
            }
        });
        csrDashboard.setVisible(true);
     }
     public void userDashboardFrame(String accountNumber){
        JFrame userDashboard =new JFrame("User Dashboard");
        userDashboard.setLayout(null);
        userDashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
        userDashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton btn_withdraw=new JButton("Withdraw Amount");
        JButton btn_viewTransaction=new JButton("View Transaction History");
        JButton btn_viewBalance=new JButton("View Balance");
        JButton btn_logout=new JButton("Log Out");
        
        
        userDashboard.add(btn_withdraw);
        btn_withdraw.setBounds(550, 100, 200, 50);
        btn_withdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 new UserWork().withdrawAmount(accountNumber);
            }
        });
        
        userDashboard.add(btn_viewTransaction);
        btn_viewTransaction.setBounds(550, 200, 200, 50);
        btn_viewTransaction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 new UserWork().viewTransactionHistory(accountNumber);
            }
        });
        userDashboard.add(btn_viewBalance);
        btn_viewBalance.setBounds(550, 300, 200, 50);
        btn_viewBalance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 new UserWork().viewBalance(accountNumber);
            }
        });
        userDashboard.add(btn_logout);
        btn_logout.setBounds(550, 400, 200, 50);
         btn_logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a;
                a = JOptionPane.showConfirmDialog(null,"Are you sure to log out","Select",JOptionPane.YES_NO_OPTION);
                if(a==0){
                   new LoginScreen().userLoginFrame();
                   userDashboard.dispose();
                }
            }
        });
        userDashboard.setVisible(true);
     }
    
}
