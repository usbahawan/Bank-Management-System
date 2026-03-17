package bankmanagementsystem;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CSR_Work {
    public void createAccount() {
        JFrame createAccount = new JFrame("Create Account");
        createAccount.setSize(800, 700);
        createAccount.setLayout(null);
        createAccount.setLocationRelativeTo(null);
        JLabel L1 = new JLabel(" Name ");
        JLabel L2 = new JLabel(" Father Name ");
        JLabel L3 = new JLabel(" Phone Number ");
        JLabel L4 = new JLabel("Date of Birth");
        JLabel L5 = new JLabel("Address");
        JLabel L6 = new JLabel("Gender");
        JLabel L7 = new JLabel("Cnic");
        JLabel L8 = new JLabel("Account Type");
        JLabel L9 = new JLabel("Account Opening Date");
        JLabel L10=new JLabel("Deposit Amount");
        JLabel L11=new JLabel("PIN");
        JTextField txt_name = new JTextField();
        JTextField txt_fatherName = new JTextField();
        JTextField txt_phoneNumber = new JTextField();
        JDateChooser jdc_dob = new JDateChooser();
        JTextField txt_address = new JTextField();
        JTextField txt_depositAmount = new JTextField();
        JTextField txt_pin = new JTextField();
        JRadioButton R1 = new JRadioButton("Male");
        JRadioButton R2 = new JRadioButton("Female");
        ButtonGroup group1 = new ButtonGroup();
        group1.add(R1);
        group1.add(R2);
        JTextField txt_cnic = new JTextField();
        JRadioButton r_saving = new JRadioButton("Saving");
        JRadioButton r_current = new JRadioButton("Current");
        ButtonGroup group2 = new ButtonGroup();
        group2.add(r_saving);
        group2.add(r_current);
        JDateChooser jdc_accOpeningDate = new JDateChooser();
        JCheckBox jcb_atm = new JCheckBox("ATM");
        JCheckBox jcb_chequeBook = new JCheckBox("Cheque Book");
        JButton create_Account = new JButton("Create Account");
        txt_depositAmount.setVisible(false);
        L10.setVisible(false);

        createAccount.add(L1);
        L1.setBounds(10, 20, 100, 50);
        createAccount.add(L2);
        L2.setBounds(10, 60, 100, 50);
        createAccount.add(L3);
        L3.setBounds(10, 100, 100, 50);
        createAccount.add(L4);
        L4.setBounds(10, 140, 100, 50);
        createAccount.add(L5);
        L5.setBounds(10, 180, 100, 50);
        createAccount.add(L6);
        L6.setBounds(10, 220, 100, 50);
        L7.setBounds(10, 260, 100, 50);
        createAccount.add(L8);
        L8.setBounds(10, 300, 100, 50);
        createAccount.add(L9);
        L9.setBounds(10, 340, 200, 50);
        createAccount.add(L10);
        L10.setBounds(10, 460, 150, 50);
        createAccount.add(L11);
        L11.setBounds(10, 420, 100, 50);

        createAccount.add(txt_name);
        txt_name.setBounds(150, 30, 150, 30);
        createAccount.add(txt_fatherName);
        txt_fatherName.setBounds(150, 70, 150, 30);
        createAccount.add(txt_phoneNumber);
        txt_phoneNumber.setBounds(150, 110, 150, 30);
        createAccount.add(jdc_dob);
        jdc_dob.setBounds(150, 150, 150, 30);
        createAccount.add(txt_address);
        txt_address.setBounds(150, 190, 150, 30);

        createAccount.add(R1);
        R1.setBounds(150, 230, 70, 30);
        createAccount.add(R2);
        R2.setBounds(220, 230, 70, 30);
        createAccount.add(L7);
        createAccount.add(r_saving);
        r_saving.setBounds(150, 310, 70, 30);
        createAccount.add(r_current);
        r_current.setBounds(220, 310, 70, 30);
        createAccount.add(jdc_accOpeningDate);
        jdc_accOpeningDate.setBounds(150, 350, 150, 30);
        createAccount.add(jcb_atm);
        jcb_atm.setBounds(150, 390, 70, 30);
        createAccount.add(jcb_chequeBook);
        jcb_chequeBook.setBounds(220, 390, 100, 30);
        createAccount.add(txt_phoneNumber);
        txt_phoneNumber.setBounds(150, 110, 150, 30);
        createAccount.add(txt_cnic);
        txt_cnic.setBounds(150, 270, 150, 30);
        createAccount.add(txt_pin);
        txt_pin.setBounds(150, 430, 150, 30);
        createAccount.add(txt_depositAmount);
        txt_depositAmount.setBounds(150, 470, 150, 30);
        
        createAccount.add(create_Account);
        create_Account.setBounds(150, 520, 130, 30);
        create_Account.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    String name = txt_name.getText();
                    String fatherName = txt_fatherName.getText();
                    String phNo=txt_phoneNumber.getText();
                    long contactNumber=Long.parseLong(phNo);
                    String dob = sdf.format(jdc_dob.getDate());
                    String address = txt_address.getText();
                    
                    String gender = "";
                    if (R1.isSelected()) 
                        gender = "Male";
                    else if (R2.isSelected()) 
                        gender = "Female";
                    else 
                        gender = "";
                    
                    String cnicNo=txt_cnic.getText();
                    long cnic=Long.parseLong(cnicNo);
                 
                    String accountType="";
                    if(r_saving.isSelected())
                        accountType="saving";
                    else if(r_current.isSelected())
                        accountType="current";
                    else
                        accountType="";
                    
                    String accOpeningDate = sdf.format(jdc_accOpeningDate.getDate());
                                
                    String atm="";
                    if(jcb_atm.isSelected())
                        atm="yes";
                    else 
                        atm="no";
                                
                    String chequeBook="";
                    if(jcb_chequeBook.isSelected())
                        chequeBook="yes";
                    else
                        chequeBook="no";
                    String pin=txt_pin.getText();
                    long PIN=Long.parseLong(pin);

                    if (name.equals(""))
                        JOptionPane.showMessageDialog(null, "Name can't be left empty");
                    else if (fatherName.equals(""))
                        JOptionPane.showMessageDialog(null, "Father name can't be left empty");
                    else if (phNo.length()!=11)
                        JOptionPane.showMessageDialog(null, "Please enter 11 digits Mobile Number");
                    else if (dob == null)
                        JOptionPane.showMessageDialog(null, "Date of birth can't be left empty");
                    else if (address.equals(""))
                        JOptionPane.showMessageDialog(null, "Address can't be left empty");
                    else if (gender.equals(""))
                        JOptionPane.showMessageDialog(null, "Gender is not specified");
                    else if (cnicNo.length()!=13)
                        JOptionPane.showMessageDialog(null, "Please enter 13 digits CNIC Number");
                    else if (accountType.equals("")) 
                        JOptionPane.showMessageDialog(null, "AccountType is not specified");
                    else if (accOpeningDate==null) 
                        JOptionPane.showMessageDialog(null, "Please enter account opening date");
                    else if (atm.equals("no")&&chequeBook.equals("no"))
                        JOptionPane.showMessageDialog(null, "Please select atleast one from ATM or ChequeBook");
                    else if(pin.length()!=4)
                            JOptionPane.showMessageDialog(null, "Pin should be of 4-digits");                
                    else
                    {
                        if(accountType.equalsIgnoreCase("saving")){
                            txt_depositAmount.setVisible(true);
                            L10.setVisible(true);
                            try{
                                long amount=Long.parseLong(txt_depositAmount.getText());
                                if(amount<1000)
                                    JOptionPane.showMessageDialog(null, "Minimum amount should be 1000");
                                else {
                                    AccountHolder ah = new AccountHolder(cnicNo, accountType, accOpeningDate, atm, chequeBook, name, fatherName, phNo, dob, address, gender,pin,amount);
                                    int accountNumber = new CustomerServiceRepresentative().createAccount(ah);
                                    if (accountNumber == 0) {
                                        JOptionPane.showMessageDialog(null, "Account can't be created");
                                        createAccount.dispose();
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Account created successfully. Your account number is " + accountNumber);
                                        createAccount.dispose();
                                    }
                                }
                            }
                            catch(NumberFormatException nfe){
                                JOptionPane.showMessageDialog(null, "Amount should be an integer");
                            }
                        }
                        else {
                            AccountHolder ah = new AccountHolder(cnicNo, accountType, accOpeningDate, atm, chequeBook, name, fatherName, phNo, dob, address, gender,pin,0);
                            int accountNumber = new CustomerServiceRepresentative().createAccount(ah);
                            if (accountNumber == 0) {
                                JOptionPane.showMessageDialog(null, "Account can't be created");
                                createAccount.dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "Account created successfully. Your account number is " + accountNumber);
                                createAccount.dispose();
                            }
                        
                    }
                }
                }
                catch(NumberFormatException nfx){
                JOptionPane.showMessageDialog(null, "Contact number, cnic and pin should be integer");
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Please enter date of birth and account opening date");
                }
            }
        });
        createAccount.setVisible(true);
    }

    public void deleteAccount() {
        JFrame deleteAccount = new JFrame("Delete Account");
        deleteAccount.setSize(800, 700);
        deleteAccount.setLayout(null);
        deleteAccount.setLocationRelativeTo(null);
        JLabel L1 = new JLabel("Please enter Account Number");
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
        
        JButton delete_Account = new JButton("Delete Account");
        
        deleteAccount.add(L1);
        L1.setBounds(40, 60, 400, 50);
        
        deleteAccount.add(txt_accountNumber);
        txt_accountNumber.setBounds(250, 70, 200, 30);
        
        deleteAccount.add(delete_Account);
        delete_Account.setBounds(250, 150, 120, 30);
        delete_Account.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                String accountNumber=String.valueOf(txt_accountNumber.getSelectedItem());
                if(accountNumber.length()!=6)
                    JOptionPane.showMessageDialog(null, "Please enter Account Number of length 6-digits");
                else{
                    boolean status=new CustomerServiceRepresentative().deleteAccount(accountNumber);
                    if(status!=true){
                        JOptionPane.showMessageDialog(null, "Account not found");
                    }
                    else{
                        deleteAccount.dispose();
                    }
                }
        }
        });
        deleteAccount.setVisible(true);
    }

    public void viewAccount() {
        JFrame viewAccount = new JFrame("View Account");
        viewAccount.setSize(800, 700);
        viewAccount.setLayout(null);
        viewAccount.setLocationRelativeTo(null);
        JLabel L0 = new JLabel("Please select account number");
        JLabel L1 = new JLabel(" Name ");
        JLabel L2 = new JLabel(" Father Name ");
        JLabel L3 = new JLabel(" Phone Number ");
        JLabel L4 = new JLabel("Date of Birth");
        JLabel L5 = new JLabel("Address");
        JLabel L6 = new JLabel("Gender");
        JLabel L7 = new JLabel("Cnic");
        JLabel L8 = new JLabel("Account Type");
        JLabel L9 = new JLabel("Account Opening Date");
        JLabel L10 = new JLabel("ATM");
        JLabel L11 = new JLabel("ChequeBook");
        JLabel L12 = new JLabel("PIN");
        JLabel L13 = new JLabel("Balance");
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
        JTextField txt_name = new JTextField();
        JTextField txt_fatherName = new JTextField();
        JTextField txt_phoneNumber = new JTextField();
        JTextField txt_dob = new JTextField();
        JTextField txt_address = new JTextField();
        JTextField txt_gender = new JTextField();
        JTextField txt_cnic = new JTextField();
        JTextField txt_accountType = new JTextField();
        JTextField txt_accOpeningDate = new JTextField();
        JTextField txt_atm = new JTextField();
        JTextField txt_chequeBook = new JTextField();
        JTextField txt_pin = new JTextField();
        JTextField txt_balance = new JTextField();
        
        txt_name.setEditable(false);
        txt_fatherName.setEditable(false);
        txt_phoneNumber.setEditable(false);
        txt_dob.setEditable(false);
        txt_address.setEditable(false);
        txt_gender.setEditable(false);
        txt_cnic.setEditable(false);
        txt_accountType.setEditable(false);
        txt_accOpeningDate.setEditable(false);
        txt_name.setEditable(false);
        txt_atm.setEditable(false);
        txt_chequeBook.setEditable(false);
        txt_pin.setEditable(false);
        txt_balance.setEditable(false);
        
        viewAccount.add(L0);
        L0.setBounds(10, 30, 180, 50);
        viewAccount.add(L1);
        L1.setBounds(10, 70, 100, 50);
        viewAccount.add(L2);
        L2.setBounds(10, 110, 100, 50);
        viewAccount.add(L3);
        L3.setBounds(10, 150, 100, 50);
        viewAccount.add(L4);
        L4.setBounds(10, 190, 100, 50);
        viewAccount.add(L5);
        L5.setBounds(10, 230, 100, 50);
        viewAccount.add(L6);
        L6.setBounds(10, 270, 100, 50);
        viewAccount.add(L7);
        L7.setBounds(10, 310, 100, 50);
        viewAccount.add(L8);
        L8.setBounds(10, 350, 100, 50);
        viewAccount.add(L9);
        L9.setBounds(10, 390, 200, 50);
        viewAccount.add(L10);
        L10.setBounds(10, 430, 200, 50);
        viewAccount.add(L11);
        L11.setBounds(10, 470, 200, 50);
        viewAccount.add(L12);
        L12.setBounds(10, 510, 200, 50);
        viewAccount.add(L13);
        L13.setBounds(10, 550, 200, 50);
        
        viewAccount.add(txt_accountNumber);
        txt_accountNumber.setBounds(200, 40, 150, 30);
        viewAccount.add(txt_name);
        txt_name.setBounds(200, 80, 150, 30);
        viewAccount.add(txt_fatherName);
        txt_fatherName.setBounds(200, 120, 150, 30);
        viewAccount.add(txt_phoneNumber);
        txt_phoneNumber.setBounds(200, 160, 150, 30);
        viewAccount.add(txt_dob);
        txt_dob.setBounds(200, 200, 150, 30);
        viewAccount.add(txt_address);
        txt_address.setBounds(200, 240, 150, 30);
        viewAccount.add(txt_gender);
        txt_gender.setBounds(200, 280, 150, 30);
        viewAccount.add(txt_cnic);
        txt_cnic.setBounds(200, 320, 150, 30);
        viewAccount.add(txt_accountType);
        txt_accountType.setBounds(200, 360, 150, 30);
        viewAccount.add(txt_accOpeningDate);
        txt_accOpeningDate.setBounds(200, 400, 150, 30);
        viewAccount.add(txt_atm);
        txt_atm.setBounds(200, 440, 150, 30);
        viewAccount.add(txt_chequeBook);
        txt_chequeBook.setBounds(200, 480, 150, 30);
        viewAccount.add(txt_pin);
        txt_pin.setBounds(200, 520, 150, 30);
        viewAccount.add(txt_balance);
        txt_balance.setBounds(200, 560, 150, 30);
        
        txt_accountNumber.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
                    try{
                        String accountNumber=String.valueOf(txt_accountNumber.getSelectedItem());
                        Connection con=ConnectionProvider.getcon();
                        Statement st=con.createStatement();
                        ResultSet rs=st.executeQuery("select * from AccountHolderDetails where accountNumber='"+accountNumber+"'");
                        if(rs.next()){
                            AccountHolder ah=new CustomerServiceRepresentative().viewAccount(accountNumber);
                            if(ah==null)
                                JOptionPane.showMessageDialog(null, "Can't fetch details of "+accountNumber);
                            else
                            {
                            txt_name.setText(ah.getName());
                            txt_fatherName.setText(ah.getFatherName());
                            txt_phoneNumber.setText(ah.getPhNo());
                            txt_dob.setText(ah.getDob());
                            txt_address.setText(ah.getAddress());
                            txt_gender.setText(ah.getGender());
                            txt_cnic.setText(ah.getCnic());
                            txt_accountType.setText(ah.getAccountType());
                            txt_accOpeningDate.setText(ah.getAccountOpeningDate());
                            txt_atm.setText(ah.getATM());
                            txt_chequeBook.setText(ah.getChequeBook());
                            txt_pin.setText(ah.getPin());
                            txt_balance.setText(String.valueOf(ah.getBalance()));
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Account not found");
                            viewAccount.dispose();
                        }
                        con.close();
                    
                 }
                 catch(SQLException ex){
                     JOptionPane.showMessageDialog(null, ex);
                 }
            }
        });
        viewAccount.setVisible(true);
    }

    public void depositAmount() {
        JFrame depositAmount = new JFrame("Deposit Amount");
        depositAmount.setSize(800, 700);
        depositAmount.setLayout(null);
        depositAmount.setLocationRelativeTo(null);
        JLabel L1 = new JLabel("Please enter account number");
        JLabel L2 = new JLabel("Enter deposit amount");
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
        
        JTextField txt_amount = new JTextField();
        JButton search = new JButton("Search");
        JButton deposit = new JButton("Deposit Amount");
        
        depositAmount.add(L1);
        L1.setBounds(10, 30, 180, 50);

        depositAmount.add(txt_accountNumber);
        txt_accountNumber.setBounds(200, 40, 150, 30);
        depositAmount.add(L2);
        L2.setBounds(10, 70, 180, 50);
        depositAmount.add(txt_amount);
        txt_amount.setBounds(200, 80, 150, 30);
        depositAmount.add(deposit);
        deposit.setBounds(370, 80, 150, 30);
 
            txt_accountNumber.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                txt_amount.setText("");
                 try{
                     String accountNumber=String.valueOf(txt_accountNumber.getSelectedItem());
                             deposit.addActionListener(new ActionListener() {
                             public void actionPerformed(ActionEvent e) {
                                 long deposit_Amount=Long.parseLong(txt_amount.getText());
                                 if(deposit_Amount==0||deposit_Amount<0){
                                     JOptionPane.showMessageDialog(null, "Please enter amount greater than zero");
                                 }
                                 else{
                                     String transactionType="deposit";
                                     Transaction tr=new Transaction(transactionType,deposit_Amount);
                                     long transactionID=new CustomerServiceRepresentative().depositAmount(tr,accountNumber);
                                     if(transactionID==0)
                                         JOptionPane.showMessageDialog(null, "Can't do transaction at this time");
                                     else{
                                         JOptionPane.showMessageDialog(null,"Transaction successful. Your Transaction ID is: "+transactionID);
                                         depositAmount.dispose();
                                     }
                                 }
                                     
                             }
                            });   
                 }
                 catch(NumberFormatException nfx){
                     JOptionPane.showMessageDialog(null, "Please enter amount as integer");
                 }
            }
        });
        depositAmount.setVisible(true);
    }
}
