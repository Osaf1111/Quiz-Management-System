import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class StudentLogin extends JFrame implements ActionListener {
    JLabel loginPrint, userLabel, passLabel, errJLabel;
    JTextField T1, T2;
    JButton SIGNIN, backBttnMenu;
    File fSDA = new File("SDAquestions.txt");
    File fos = new File("Osquestions.txt");
    File fosLab = new File("osLabquestions.txt");
    File foop = new File("OOpquestions.txt");

    StudentLogin() {
        setVisible(true);
        setSize(900, 500);
        setResizable(false);
        setLayout(null);

        loginPrint = new JLabel("Welcome to the Student Login Form ");
        loginPrint.setBounds(250, 20, 400, 100);
        loginPrint.setFont(new Font("Josefin Sans", Font.BOLD, 22));
        loginPrint.setForeground(Color.BLACK);
        add(loginPrint);

        userLabel = new JLabel("ID:");
        userLabel.setBounds(250, 100, 100, 30);
        userLabel.setFont(new Font("Josefin Sans", Font.BOLD, 18));
        userLabel.setForeground(Color.black);
        add(userLabel);

        passLabel = new JLabel("Password:");
        passLabel.setBounds(250, 180, 100, 30);
        passLabel.setFont(new Font("Josefin Sans", Font.BOLD, 18));
        passLabel.setForeground(Color.black);
        add(passLabel);

        T1 = new JTextField();
        T1.setBounds(360, 100, 200, 30);
        add(T1);

        T2 = new JTextField();
        T2.setBounds(360, 180, 200, 30);
        add(T2);

        SIGNIN = new JButton("SIGN IN");
        SIGNIN.setBounds(370, 250, 150, 50);
        SIGNIN.setFont(new Font("Josefin Sans", Font.BOLD, 18));
        SIGNIN.setForeground(Color.white);
        SIGNIN.setBackground(Color.black);
        SIGNIN.setFocusable(false);
        add(SIGNIN);
        SIGNIN.addActionListener(this);
        backBttnMenu = new JButton("Back");
        backBttnMenu.setBounds(40, 40, 80, 40);
        backBttnMenu.setFont(new Font("Josefin Sans", Font.BOLD, 18));
        backBttnMenu.setForeground(Color.white);
        backBttnMenu.setBackground(Color.black);
        backBttnMenu.setFocusable(false);
        add(backBttnMenu);
        backBttnMenu.addActionListener(this);
        errJLabel = new JLabel();
        errJLabel.setForeground(Color.red);
        errJLabel.setFont(new Font("Josefin Sans", Font.BOLD, 15));
        errJLabel.setBounds(270, 280, 400, 30);
        add(errJLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(SIGNIN)) {
            String id = T1.getText();
            String pass = T2.getText();
            if (id.isEmpty()) {
                errJLabel.setBounds(360, 130, 200, 30);
                errJLabel.setText("Please fill the ID");
                return;
            } else if (pass.isEmpty()) {
                errJLabel.setText("Please fill the Password");
                errJLabel.setBounds(360, 210, 260, 30);
                return;
            } else {
                if (id.equals("hadeed") && pass.equals("abc123")) {
                    UserName(id); // here we are calling the UserName function which is defined below to write
                                  // user's name into file
                    new studentDashboard();
                    dispose();
                } else {
                    errJLabel.setText("Invalid ID or Password");
                    errJLabel.setBounds(370, 295, 220, 50);
                    return;
                }
            }
        } else if (e.getSource().equals(backBttnMenu)) {
            new LoginDashboard();
            dispose();
        }
    }

    private void UserName(String username) {
        try {
            FileWriter filwriteId = new FileWriter("Username.txt");
            filwriteId.write(username);
            filwriteId.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
