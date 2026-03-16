package bankmanagementsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ApplicationDashboard
{
    public void setDashboard(){
        JFrame dashboard =new JFrame("Main Dashboard");
        JLabel L1 =new JLabel("AU Bank Management System");
        dashboard.setLayout(null);
        dashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
        dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color offWhite= new Color(250,249,246);
        dashboard.getContentPane().setBackground(offWhite);

        JButton btn_managerLogin=new JButton("Manager Login");
        JButton btn_csrLogin=new JButton("CSR Login");
        JButton btn_userLogin=new JButton("User Login");
        JButton btn_exit=new JButton("Exit");
        
        L1.setBounds(430, 50, 550, 50);
        dashboard.add(L1);
        L1.setFont(new Font("Times New Roman",Font.BOLD,40));
        //L1.setForeground(Color.BLACK);
        //L1.setBounds(-100, 0, dashboard.getWidth(), 80);

        btn_managerLogin.setBounds(600, 200, 150,50);
        dashboard.add(btn_managerLogin);
        btn_managerLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginScreen().managerLoginFrame();
            }
        });
        
        dashboard.add(btn_csrLogin);
        btn_csrLogin.setBounds(600, 300, 150,50);
        btn_csrLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginScreen().csrLoginFrame();
            }
        });
        
        dashboard.add(btn_userLogin);
        btn_userLogin.setBounds(600, 400, 150,50);
        btn_userLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginScreen().userLoginFrame();
            }
        });
        
        dashboard.add(btn_exit);
        btn_exit.setBounds(600, 500, 150,50);
        btn_exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a;
                a = JOptionPane.showConfirmDialog(null,"Do you want to close application","Select",JOptionPane.YES_NO_OPTION);
                if(a==0)
                System.exit(0);
            }
        });
        
        dashboard.setVisible(true);
    }
    
}
