package bankmanagementsystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginScreen
{
    public void managerLoginFrame(){
        JFrame mLogin =new JFrame("Manager Login Screen");
        mLogin.setLayout(null);
        mLogin.setSize(800,700);
        mLogin.setLocationRelativeTo(null);
        JLabel L1 =new JLabel("Manager ID");
        JLabel L2 =new JLabel("Password");
        JComboBox<String> txt_managerID = new JComboBox<>();
        try {
            Connection con=ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT manageID FROM ManagerDetails");
            while (rs.next()) {
                txt_managerID.addItem(rs.getString("manageID"));
            }

            con.close();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        JPasswordField txt_password=new JPasswordField();
        JButton btn_login=new JButton("Login");
        
        mLogin.add(L1);
        L1.setBounds(250, 190, 100, 50);
        mLogin.add(L2);
        L2.setBounds(250, 240, 100, 50);
        mLogin.add(txt_managerID);
        txt_managerID.setBounds(350, 200, 120, 30);
        mLogin.add(txt_password);
        txt_password.setBounds(350, 250, 120, 30);
        
        mLogin.add(btn_login);
        btn_login.setBounds(350, 320, 80, 30);
        btn_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String man_ID=String.valueOf(txt_managerID.getSelectedItem());
                int managerID=Integer.parseInt(man_ID);
                String password=txt_password.getText();
                boolean exist=new Manager().login(managerID,password);
                if(exist==true){
                    new SecondDashboard().managerDashboardFrame();
                    mLogin.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Username or password is incorrect");
                    txt_password.setText("");
                }
            }
        });
        mLogin.setVisible(true);
    }
    
    public void csrLoginFrame(){
        JFrame csrLogin =new JFrame("CSR Login Screen");
        csrLogin.setLayout(null);
        csrLogin.setSize(800,700);
        csrLogin.setLocationRelativeTo(null);
        JLabel L1 =new JLabel("CSR ID");
        JLabel L2 =new JLabel("Password");
        JComboBox<String> txt_csrID = new JComboBox<>();
        try {
            Connection con=ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT csrID FROM CSR_Details");
            while (rs.next()) {
                txt_csrID.addItem(rs.getString("csrID"));
            }

            con.close();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        JPasswordField txt_csrPassword=new JPasswordField();
        JButton btn_login=new JButton("Login");
        csrLogin.add(L1);
        L1.setBounds(250, 190, 100, 50);
        csrLogin.add(L2);
        L2.setBounds(250, 240, 100, 50);
        csrLogin.add(txt_csrID);
        txt_csrID.setBounds(350, 200, 120, 30);
        csrLogin.add(txt_csrPassword);
        txt_csrPassword.setBounds(350, 250, 120, 30);
        
        csrLogin.add(btn_login);
        btn_login.setBounds(350, 320, 80, 30);
        btn_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    String csr_ID=String.valueOf(txt_csrID.getSelectedItem());
                    int csrID = Integer.parseInt(csr_ID);
                    String password = txt_csrPassword.getText();
                    boolean exist = new CustomerServiceRepresentative().login(csrID, password);
                    if (exist == true) {
                        new SecondDashboard().csrDashboardFrame();
                        csrLogin.dispose();
                    } 
                    else {
                        JOptionPane.showMessageDialog(null, "Username or password is incorrect");
                        txt_csrPassword.setText("");
                    }
            }
        });
        csrLogin.setVisible(true);
    }
    public void userLoginFrame(){
        JFrame userLogin =new JFrame("User Login/Signup Screen");
        userLogin.setSize(800,700);
        userLogin.setLocationRelativeTo(null);
        userLogin.setLayout(null);
        JLabel L1 =new JLabel("Account Number");
        JLabel L2 =new JLabel("PIN");
        JComboBox<String> txt_accountNumber = new JComboBox<>();
        try {
            Connection con=ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT accountNumber FROM AccountHolderDetails");
            while (rs.next()) {
                txt_accountNumber.addItem(rs.getString("accountNumber"));
            }
            con.close();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        JPasswordField txt_pin=new JPasswordField();
        JButton btn_login=new JButton("Login");
        
        userLogin.add(L1);
        L1.setBounds(200, 200, 100, 30);
        userLogin.add(L2);
        L2.setBounds(200, 250, 100, 30);
        
        userLogin.add(txt_accountNumber);
        txt_accountNumber.setBounds(320, 200, 120, 30);
        userLogin.add(txt_pin);
        txt_pin.setBounds(320, 250, 120, 30);
                
        userLogin.add(btn_login);
        btn_login.setBounds(320, 320, 90, 30);
        btn_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String accountNumber=String.valueOf(txt_accountNumber.getSelectedItem());
                    String pin=txt_pin.getText();
                    int PIN=Integer.parseInt(pin);
                    if(pin.length()!=4)
                        JOptionPane.showMessageDialog(null, "Please enter PIN of 4-digits");
                    else{
                        boolean exist=new AccountHolder().login(accountNumber,pin);
                        if(exist==true){
                            new SecondDashboard().userDashboardFrame(accountNumber);
                            userLogin.dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Invalid account number or PIN");
                            txt_pin.setText("");
                        }
                    }
                    }
            catch(NumberFormatException nfx){
                  JOptionPane.showMessageDialog(null, "PIN should be integer");
             }
            }
        });
        userLogin.setVisible(true);
    }
}
