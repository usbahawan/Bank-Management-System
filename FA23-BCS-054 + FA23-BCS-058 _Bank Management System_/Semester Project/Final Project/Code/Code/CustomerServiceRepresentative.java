package bankmanagementsystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CustomerServiceRepresentative extends Person
{
    ArrayList<AccountHolder> ahList=new ArrayList<>();
    public ArrayList<Transaction> transactionList=new ArrayList<>();
    private int csrID;
    private String cnic;
    private String password;

    public CustomerServiceRepresentative() {
      
    }
   
    public CustomerServiceRepresentative(String cnic,int csrID, String password) {
        this.cnic=cnic;
        this.csrID = csrID;
        this.password = password;
    }

    public CustomerServiceRepresentative(String cnic, String password, String name, String fatherName, String phNo, String dob, String address,String gender) {
        super(name, fatherName, phNo, dob, address, gender);
        this.cnic=cnic;
        this.password = password;
    }
    
    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }
    
    public int getCsrID() {
        return csrID;
    }

    public void setCsrID(int csrID) {
        this.csrID = csrID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerServiceRepresentative(int csrID, String password) {
        this.csrID = csrID;
        this.password = password;
    }
    
    public int createAccount(AccountHolder ah){
        int accountNumber=0;
        ahList.add(ah);
        try{
            Connection con=ConnectionProvider.getcon();
            PreparedStatement ps=con.prepareStatement("insert into AccountHolderDetails (name,fatherName,phoneNumber,dob,address,gender,cnic,accountType,accountOpeningDate,atm,chequeBook,pin,balance) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,ah.getName());
            ps.setString(2, ah.getFatherName());
            ps.setString(3, ah.getPhNo());
            ps.setString(4,ah.getDob());
            ps.setString(5,ah.getAddress());
            ps.setString(6,ah.getGender());
            ps.setString(7,ah.getCnic());
            ps.setString(8,ah.getAccountType());
            ps.setString(9,ah.getAccountOpeningDate());
            ps.setString(10,ah.getATM());
            ps.setString(11,ah.getChequeBook());
            ps.setString(12, ah.getPin());
            ps.setLong(13, ah.getBalance());
            ps.executeUpdate();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from AccountHolderDetails where cnic='"+ah.getCnic()+"'");
            if(rs.next()){
                accountNumber=rs.getInt("accountNumber");
                PreparedStatement ps1=con.prepareStatement("insert into Transactions (accountNumber,transactionType,amount) values (?,?,?)");
                ps1.setInt(1,accountNumber);
                ps1.setString(2, "trial");
                ps1.setInt(3, 0);
                ps1.executeUpdate();
            }
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return accountNumber;
    }
    public boolean deleteAccount(String accountNumber){
        boolean status=false;
        try{
            Connection con=ConnectionProvider.getcon();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from AccountHolderDetails where accountNumber='"+accountNumber+"'");
            if(rs.next())
            {
                status=true;
                int a;
                a = JOptionPane.showConfirmDialog(null,"Are you sure to delete this Account","Select",JOptionPane.YES_NO_OPTION);
                if(a==0)
                {
                    PreparedStatement ps1=con.prepareStatement("delete from Transactions where accountNumber='"+accountNumber+"'");
                    PreparedStatement ps2=con.prepareStatement("delete from AccountHolderDetails where accountNumber='"+accountNumber+"'");
                    ps1.executeUpdate();
                    ps2.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Account deleted successfully");
                }
            }
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return status;
    }
    public AccountHolder viewAccount(String accountNumber){
        String name,fatherName,phoneNumber,dob,address,gender,cnic,accountType,accOpeningDate,atm,chequeBook,pin;
        long balance=0;
        try{
            Connection con=ConnectionProvider.getcon();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from AccountHolderDetails where accountNumber='"+accountNumber+"'");
            if(rs.next()){
                name=rs.getString("name");
                fatherName=rs.getString("fatherName");
                phoneNumber=rs.getString("phoneNumber");
                dob=rs.getString("dob");
                address=rs.getString("address");
                gender=rs.getString("gender");
                cnic=rs.getString("cnic");
                accountType=rs.getString("accountType");
                accOpeningDate=rs.getString("accountopeningDate");
                atm=rs.getString("atm");
                chequeBook=rs.getString("chequeBook");
                pin=rs.getString("pin");
                balance=rs.getLong("balance");
                AccountHolder ah=new AccountHolder(cnic,accountType,accOpeningDate,atm,chequeBook,name,fatherName,phoneNumber,dob,address,gender,pin,balance);
                con.close();
                return ah;
            } 
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    public int depositAmount(Transaction tr,String accountNumber){
        int transactionID=0;
        transactionList.add(tr);
        try{
            Connection con=ConnectionProvider.getcon();
            PreparedStatement ps1=con.prepareStatement("insert into Transactions (accountNumber,transactionType,amount) values (?,?,?)");
            ps1.setString(1,accountNumber);
            ps1.setString(2, tr.getTransactionType());
            ps1.setLong(3, tr.getAmount());
            ps1.executeUpdate();
            Statement st1=con.createStatement();
            ResultSet rs1=st1.executeQuery("select accountNumber from AccountHolderDetails where accountNumber='"+accountNumber+"'");
            if(rs1.next()){
                PreparedStatement ps2=con.prepareStatement("UPDATE AccountHolderDetails SET balance = balance + ? WHERE accountNumber ='"+accountNumber+"'");////
                ps2.setLong(1, tr.getAmount());
                ps2.executeUpdate();
            }
            else{
                PreparedStatement ps3=con.prepareStatement("insert into AccountHolderDetails (accountNumber) values (?)");
                ps3.setString(1,accountNumber);
                ps3.executeUpdate();
                PreparedStatement ps4=con.prepareStatement("UPDATE AccountHolderDetails SET balance = balance + ? WHERE accountNumber ='"+accountNumber+"'");////
                ps4.setLong(1, tr.getAmount());
                ps4.executeUpdate();
            }
            Statement st2=con.createStatement();
            ResultSet rs2=st2.executeQuery("select transactionID from Transactions where accountNumber='"+accountNumber+"'");
            if(rs2.next())
                transactionID= rs2.getInt("transactionID");
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return transactionID;
    }
    public boolean login(int csrID, String password){
        try{
            Connection con=ConnectionProvider.getcon();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from CSR_Details where csrID='"+csrID+"' and csrPassword='"+password+"'");
            if(rs.next())
                return true;
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return false;

    }
}
