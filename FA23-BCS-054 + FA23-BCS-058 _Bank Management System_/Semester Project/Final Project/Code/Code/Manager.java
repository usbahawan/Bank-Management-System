package bankmanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Manager extends Person
{
    private int managerID;
    private String password;

    public Manager(int managerID, String password) {
        this.managerID = managerID;
        this.password = password;
    }
    public Manager() {
        
    }

    public Manager(int managerID, String password, String name, String fatherName, String phNo, String dob, String address,String gender) {
        super(name, fatherName, phNo, dob, address, gender);
        this.managerID = managerID;
        this.password = password;
    }
    
    public int registerCSR(CustomerServiceRepresentative csr){
        int csrID=0;
        try{
            Connection con=ConnectionProvider.getcon();
            PreparedStatement ps=con.prepareStatement("insert into CSR_Details (csrName,csrFatherName,csrPhoneNumber,csrDoB,csrAddress,csrGender,csrCNIC,csrPassword) values (?,?,?,?,?,?,?,?)");
            ps.setString(1,csr.getName());
            ps.setString(2, csr.getFatherName());
            ps.setString(3, csr.getPhNo());
            ps.setString(4,csr.getDob());
            ps.setString(5,csr.getAddress());
            ps.setString(6,csr.getGender());
            ps.setString(7,csr.getCnic());
            ps.setString(8,csr.getPassword());
            ps.executeUpdate();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from CSR_Details where csrCNIC='"+csr.getCnic()+"'");
            if(rs.next())
                csrID= rs.getInt("csrID");
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return csrID;
    }
   
    public boolean deleteCSR(long csrID){
        boolean exist=false;
        try{
            Connection con=ConnectionProvider.getcon();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from CSR_Details where csrID='"+csrID+"'");
            if(rs.next()){
                exist=true;
                int a;
                a = JOptionPane.showConfirmDialog(null,"Are you sure to delete this csr","Select",JOptionPane.YES_NO_OPTION);
                if(a==0){
                   PreparedStatement ps=con.prepareStatement("delete from CSR_Details where csrID='"+csrID+"'");
                   ps.executeUpdate();
                   JOptionPane.showMessageDialog(null, "CSR deleted successfully");
                }
            }
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return exist;
    }
    public boolean login(int managerID, String password){
        boolean exist=false;
        try{
            Connection con=ConnectionProvider.getcon();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from ManagerDetails where manageID='"+managerID+"' and managerPassword='"+password+"'");
            if(rs.next())
                exist=true;
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return exist;

    }
    public ResultSet viewAccountHolder(){
        try{
            Connection con=ConnectionProvider.getcon();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from AccountHolderDetails");
            if(rs.next())
                return rs;
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
