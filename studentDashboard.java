import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class studentDashboard extends JFrame implements ActionListener {
    JButton attemptButton, resultButton, mainMenu;
    JLabel welcomeLabel;
    JLabel errorLabel;
    String user;
    File f1 = new File("OOpquestions.txt");
    File f2 = new File("SDAquestions.txt");
    File f3 = new File("OsLabquestions.txt");
    File osResult = new File("osLabresult.txt");
    File sdaresult = new File("SDAresult.txt");
    File oopresult = new File("result.txt");

    studentDashboard() {
        setVisible(true);
        setLayout(null);
        setSize(900, 500);
        setResizable(false);

        welcomeLabel = new JLabel();
        welcomeLabel.setForeground(Color.black);
        welcomeLabel.setFont(new Font("Josefin Sans", Font.BOLD, 24));
        welcomeLabel.setBounds(250, 100, 400, 30); // Set the bounds of the welcomeLabel
        add(welcomeLabel);

        attemptButton = new JButton("Attempt");
        attemptButton.setBounds(360, 150, 220, 40); // Set the bounds of the attemptButton
        attemptButton.setForeground(Color.white);
        attemptButton.setBackground(Color.black);
        attemptButton.setFont(new Font("Josefin Sans", Font.BOLD, 20));
        attemptButton.setFocusable(false);
        add(attemptButton);
        attemptButton.addActionListener(this);

        resultButton = new JButton("Result");
        resultButton.setBounds(360, 200, 220, 40); // Set the bounds of the resultButton
        resultButton.setForeground(Color.white);
        resultButton.setBackground(Color.black);
        resultButton.setFont(new Font("Josefin Sans", Font.BOLD, 20));
        resultButton.setFocusable(false);
        add(resultButton);
        resultButton.addActionListener(this);
        
        mainMenu = new JButton("Main Menu");
        mainMenu.setBounds(360, 250, 220, 40);
        mainMenu.setForeground(Color.white);
        mainMenu.setBackground(Color.black);
        mainMenu.setFont(new Font("Josefin Sans", Font.BOLD, 20));
        mainMenu.setFocusable(false);
        add(mainMenu);
        mainMenu.addActionListener(this);

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(new Font("Josefin Sans", Font.BOLD, 18));
        errorLabel.setBounds(250, 400, 400, 30); // Set the bounds of the errorLabel
        add(errorLabel);

        // Retrieve the username from the "username.txt" file
        File usernameFile = new File("username.txt");
        try {
            BufferedReader usernameReader = new BufferedReader(new FileReader(usernameFile));
            user = usernameReader.readLine();
            usernameReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (user != null) {
            welcomeLabel.setText("Welcome to Dashboard, " + user);
        } else {
            welcomeLabel.setText("Welcome to Dashboard");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(attemptButton)) {

            File dateFile = new File("date.txt");
            // File timeFile = new File("time.txt");

            if (!dateFile.exists()) {
                errorLabel.setText("Date is not set");
                return;
            }

            try {
                BufferedReader dateReader = new BufferedReader(new FileReader(dateFile));
                String dateString = dateReader.readLine();
                dateReader.close();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dateFromFile = dateFormat.parse(dateString);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                Date currentDate = calendar.getTime();

                if (dateFromFile.equals(currentDate)) {
                    // Check if the quiz files exist
                    new QuizGo();
                    dispose();
                } else {
                    errorLabel.setText("Quiz has not been set for today yet");
                    errorLabel.setBounds(330, 400, 450, 30); 
                }
            } catch (IOException | ParseException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource().equals(resultButton)) {
            if (!f1.exists() && !f2.exists() && !f3.exists()) {
                errorLabel.setText("Quiz is not set");
                errorLabel.setBounds(300, 400, 450, 30); 
                return;
            } else if (osResult.length() == 0 && oopresult.length() == 0 && sdaresult.length() == 0) {
                errorLabel.setText("Quiz not attempted yet");
                errorLabel.setBounds(330, 400, 450, 30); 
                return;
            } else {
                new showResultStudent();
            }
        }
        else if(e.getSource().equals(mainMenu)){
            new LoginDashboard();
            dispose();
        }
    }

}
