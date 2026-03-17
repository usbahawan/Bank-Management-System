package bankmanagementsystem;
public class Person
{
    private String name;
    private String fatherName;
    private String phNo;
    private String dob;
    private String address;
    private String gender;

    
    public Person(){
        
    }

    
    public Person(String name, String fatherName, String phNo, String dob, String address, String gender) {
        this.name = name;
        this.fatherName = fatherName;
        this.phNo = phNo;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
