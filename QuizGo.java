import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class QuizGo extends JFrame implements ActionListener {
    JLabel welcomeText, errorLabel;
    JButton oopTheory, osLabButton, SDAbttn, backButton;
    File Oopfile = new File("OOpquestions.txt");
    File osLabFile = new File("OsLabquestions.txt");
    File SdaFile = new File("SDAquestions.txt");
    File oopresult = new File("result.txt");
    File sdaResult = new File("SDAresult.txt");
    File osResult = new File("osLabresult.txt");
   QuizGo() {
        setVisible(true);
        setSize(900, 500);
        setResizable(false);
        setLayout(null);

        welcomeText = new JLabel("Welcome to Student Dashboard");
        welcomeText.setForeground(Color.black);
        welcomeText.setBounds(260, 66, 400, 30);
        welcomeText.setFont(new Font("Josefin Sans", Font.BOLD, 22));
        add(welcomeText);

        oopTheory = new JButton("OOP(TH)");
        oopTheory.setBounds(165, 150, 250, 50);
        oopTheory.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        oopTheory.setForeground(Color.white);
        oopTheory.setBackground(Color.black);
        oopTheory.setFocusable(false);
        add(oopTheory);

        osLabButton = new JButton("OS(LAB)");
        osLabButton.setBounds(455, 150, 250, 50);
        osLabButton.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        osLabButton.setForeground(Color.white);
        osLabButton.setBackground(Color.black);
        osLabButton.setFocusable(false);
        add(osLabButton);

        SDAbttn = new JButton("SOFTWARE DESIGN AND ARCHITECTURE");
        SDAbttn.setBounds(130, 250, 650, 50);
        SDAbttn.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        SDAbttn.setForeground(Color.white);
        SDAbttn.setBackground(Color.black);
        SDAbttn.setFocusable(false);
        add(SDAbttn);

        backButton = new JButton("Back");
        backButton.setBounds(30, 30, 100, 30);
        backButton.setFont(new Font("Josefin Sans", Font.BOLD, 16));
        backButton.setForeground(Color.white);
        backButton.setBackground(Color.black);
        backButton.setFocusable(false);
        add(backButton);

        oopTheory.addActionListener(this);
        osLabButton.addActionListener(this);
        SDAbttn.addActionListener(this);
        backButton.addActionListener(this);

        errorLabel = new JLabel();
        errorLabel.setText("");
        errorLabel.setBounds(280, 400, 600, 50);
        errorLabel.setFont(new Font("Josefin Sans", Font.BOLD, 20));
        errorLabel.setForeground(Color.red);
        add(errorLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(oopTheory)) {
            if (Oopfile.length() == 0) {
                errorLabel.setText("OOP quiz has not been set yet");
                errorLabel.setBounds(230, 400, 600, 50);
                return;
            }
            else if(oopresult.length()>0){
                errorLabel.setText("You have already attempted the OOP quiz");
                errorLabel.setBounds(230, 400, 600, 50);
                return;
            }
             else {
                new oopStudent();
                dispose();
            }
        } else if (e.getSource().equals(osLabButton)) {
            if (osLabFile.length() == 0) {
                errorLabel.setText("OS quiz has not been set yet");
                errorLabel.setBounds(230, 400, 600, 50);
                return;
            }
            else if(osResult.length()>0){
                errorLabel.setText("You have already attempted the OS quiz");
                errorLabel.setBounds(230, 400, 600, 50);
                return;
            }
            else {
                new osLabStudent();
                dispose();
            }
        } else if (e.getSource().equals(SDAbttn)) {
            if (SdaFile.length() == 0) {
                errorLabel.setText("SDA quiz has not been set yet");
                errorLabel.setBounds(230, 400, 600, 50);
                return;
            }
            else if(sdaResult.length()>0){
                errorLabel.setText("You have already attempted the SDA quiz");
                errorLabel.setBounds(230, 400, 600, 50);
                return;
            } 
            else {
                new SDAstuddent();
                dispose();
            }
        } else if (e.getSource().equals(backButton)) {
            // Handle back button action
            // Add the appropriate code to go back to the previous screen or close the window
            new studentDashboard();
            dispose();
        }
    }
}

