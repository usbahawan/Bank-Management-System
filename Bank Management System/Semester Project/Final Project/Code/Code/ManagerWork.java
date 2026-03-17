package bankmanagementsystem;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ManagerWork {
    public void csrAddFrame(){
        JFrame csrAdd =new JFrame("Add CSR");
        csrAdd.setSize(800,700);
        csrAdd.setLayout(null);
        csrAdd.setLocationRelativeTo(null);
        JLabel L1 =new JLabel(" Name ");
        JLabel L2 =new JLabel(" Father Name ");
        JLabel L3 =new JLabel(" Phone Number ");
        JLabel L4 =new JLabel("Date of Birth");
        JLabel L5 =new JLabel("Address");
        JLabel L6 =new JLabel("Gender");
        JLabel L7 =new JLabel("CNIC");
        JLabel L8 =new JLabel("Password");
        JTextField txt_name =new JTextField();
        JTextField txt_fatherName =new JTextField();
        JTextField txt_phoneNumber =new JTextField();
        JDateChooser jdc_dob = new JDateChooser();
        JTextField txt_address =new JTextField();
        JRadioButton R1=new JRadioButton("Male");
        JRadioButton R2=new JRadioButton("Female");
        ButtonGroup group = new ButtonGroup();
        group.add(R1);
        group.add(R2);
        JTextField txt_cnic =new JTextField();
        JTextField txt_password =new JTextField();
        
        JButton submit=new JButton("Submit");
        
        csrAdd.add(L1);
        L1.setBounds(10, 20, 100,50);
        csrAdd.add(L2);
        L2.setBounds(10, 60, 100,50);
        csrAdd.add(L3);
        L3.setBounds(10, 100, 100,50);
        csrAdd.add(L4);
        L4.setBounds(10, 140, 100,50);
        csrAdd.add(L5);
        L5.setBounds(10, 180, 100,50);
        csrAdd.add(L6);
        L6.setBounds(10, 220, 100,50);
        csrAdd.add(L7);
        L7.setBounds(10, 260, 100,50);
        csrAdd.add(L8);
        L8.setBounds(10, 300, 100,50);
        
        
        csrAdd.add(txt_name);
        txt_name.setBounds(150, 30, 150,30);
        csrAdd.add(txt_fatherName);
        txt_fatherName.setBounds(150, 70, 150,30);
        csrAdd.add(txt_phoneNumber);
        txt_phoneNumber.setBounds(150, 110, 150,30);
        csrAdd.add(jdc_dob);
        jdc_dob.setBounds(150, 150, 150,30);
        csrAdd.add(txt_address);
        txt_address.setBounds(150, 190, 150,30);
        
        csrAdd.add(R1);
        R1.setBounds(150, 230, 70,30);
        csrAdd.add(R2);
        R2.setBounds(220, 230, 70,30);
        
        csrAdd.add(txt_cnic);
        txt_cnic.setBounds(150, 270, 150,30);
        csrAdd.add(txt_password);
        txt_password.setBounds(150, 310, 150,30);
        
        csrAdd.add(submit);
        submit.setBounds(150, 380, 100, 30);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try{
                    String name=txt_name.getText();
                    String fatherName=txt_fatherName.getText();
                    String phNo=txt_phoneNumber.getText();
                    long contactNumber=Long.parseLong(phNo);
                    String dob =sdf.format(jdc_dob.getDate());
                    String address=txt_address.getText();
                    String gender="";
                    
                    if(R1.isSelected())
                       gender="Male";
                    else if(R2.isSelected())
                       gender="Female";
                    else
                       gender="";    
                    
                    String cnicNo=txt_cnic.getText();
                    long cnic=Long.parseLong(cnicNo);
                    String password=txt_password.getText();
                    
                    if(name.equals(""))
                        JOptionPane.showMessageDialog(null, "Name can't be left empty");
                    else if(fatherName.equals(""))
                        JOptionPane.showMessageDialog(null, "Father name can't be left empty");
                    else if (phNo.length()!=11)
                        JOptionPane.showMessageDialog(null, "Please enter 11 digits Mobile Number");
                    else if(dob==null)
                        JOptionPane.showMessageDialog(null, "Date of birth can't be left empty");
                    else if(address.equals(""))
                        JOptionPane.showMessageDialog(null, "Address can't be left empty");
                    else if(gender.equals(""))
                        JOptionPane.showMessageDialog(null, "Gender is not specified");
                    else if (cnicNo.length()!=13)
                        JOptionPane.showMessageDialog(null, "Please enter 13 digits CNIC Number");
                    else if(password.equals(""))
                        JOptionPane.showMessageDialog(null, "Password can't be left empty");
                    else{
                        CustomerServiceRepresentative csr=new CustomerServiceRepresentative(cnicNo, password, name, fatherName, phNo, dob, address, gender); //////////////////////////
                        int csrID=new Manager().registerCSR(csr);
                        if(csrID==0){
                            JOptionPane.showMessageDialog(null, "CSR can't be added");
                            csrAdd.dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "CSR is added with CSR ID as "+csrID);
                            csrAdd.dispose();
                        }
                    }
                }
                catch(NumberFormatException nfx){
                JOptionPane.showMessageDialog(null, "Contact number and cnic should be integer");
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Please enter date of birth");
                }
            }
        });
        csrAdd.setVisible(true); 
    }
    
    public void csrDeleteFrame(){
        JFrame csrDelete =new JFrame("Delete CSR");
        csrDelete.setSize(800,700);
        csrDelete.setLayout(null);
        csrDelete.setLocationRelativeTo(null);
        JLabel L1 =new JLabel("Please enter CSR ID to delete");
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
        JButton delete=new JButton("Delete");
        
        csrDelete.add(L1);
        L1.setBounds(200, 80, 200,30);
        csrDelete.add(txt_csrID);
        txt_csrID.setBounds(380, 80, 150,30);
        csrDelete.add(delete);
        delete.setBounds(380, 160, 100,30);
        
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    String csrID=String.valueOf(txt_csrID.getSelectedItem());
                    long csr_ID=Long.parseLong(csrID);
                boolean exist=new Manager().deleteCSR(csr_ID);
                if(exist!=true){
                    JOptionPane.showMessageDialog(null, "CSR not found");
                }
                else
                    csrDelete.dispose();
            }
        });
        csrDelete.setVisible(true); 
    }
    public void viewAccounts(){
        JFrame viewAccounts = new JFrame("View Accounts");
        viewAccounts.setSize(1300,700);
        viewAccounts.setLayout(null);
        viewAccounts.setLocationRelativeTo(null);

        String[] columnNames = {"Account Number", "Name","Phone Number","Date of Birth","Address","Gender","CNIC NO","Account Type","Opening Date","ChequeBook","ATM","Balance"};

        DefaultTableModel dtm = new DefaultTableModel(columnNames, 0);
        JTable viewAccountsTable = new JTable(dtm);

       JScrollPane scrollPane = new JScrollPane(viewAccountsTable);
       scrollPane.setBounds(18,30,1250,580);
       viewAccounts.add(scrollPane);
       try {
            ResultSet rs=new Manager().viewAccountHolder();
            if(rs!=null){
            while (rs.next()) {
                String accNo = rs.getString("accountNumber");
                String name = rs.getString("name");
                String phNo = rs.getString("phoneNumber");
                String dob = rs.getString("dob");
                String address = rs.getString("address");
                String gender = rs.getString("gender");
                String cnic = rs.getString("cnic");
                String accountType = rs.getString("accountType");
                String openedDate = rs.getString("accountopeningDate");
                String atm = rs.getString("atm");
                String chequeBook = rs.getString("chequeBook");
                long balance = rs.getLong("balance");
                dtm.addRow(new Object[]{accNo,name,phNo,dob,address,gender,cnic,accountType,openedDate,atm,chequeBook,balance});
                }
            }
            else 
                JOptionPane.showMessageDialog(null,"No accounts found");
        }
        catch (SQLException se){
            JOptionPane.showMessageDialog(null,se);
        }
        viewAccounts.setVisible(true);
    }
}
