import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.io.FileWriter;
import java.io.IOException;

public class setDate extends JFrame implements ActionListener {
    JTextField dateInput;
    JLabel dateLabel, invalidDate;
    JButton submButton;

    setDate() {
        setVisible(true);
        setSize(900, 500);
        setLayout(null);
        setResizable(false);

        dateLabel = new JLabel("Enter Date (yyyy-MM-dd):");
        dateLabel.setBounds(140, 80, 400, 50);
        add(dateLabel);
        dateInput = new JTextField();
        dateInput.setBounds(320, 90, 200, 30);
        add(dateInput);

        submButton = new JButton("OK");
        submButton.setBounds(315, 200, 120, 40);
        submButton.setForeground(Color.white);
        submButton.setBackground(Color.black);
        submButton.setFont(new Font("Josefin Sans", Font.BOLD, 20));
        submButton.setFocusable(false);
        submButton.addActionListener(this); // Add ActionListener to the button
        add(submButton);

        invalidDate = new JLabel();
        invalidDate.setForeground(Color.red);
        invalidDate.setFont(new Font("Josefin Sans", Font.BOLD, 15));
        add(invalidDate);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(submButton)) {
            String inputDate = dateInput.getText();
            boolean isValidDate = checkDate(inputDate);
            if (inputDate.isEmpty()) {
                invalidDate.setText("Date is not Set");
                invalidDate.setBounds(350, 125, 300, 30);
                return;
            }
            if (!isValidDate) {
                invalidDate.setText("Format Of Date is not Appropriate");
                invalidDate.setBounds(300, 125, 300, 30);
                return;
            }
            if (isValidDate) {
                invalidDate.setText("");
                LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                System.out.println("Selected Date: " + date);

                // Write input to file
                writeToFile("date.txt", inputDate);

                new teacherDashboard();
                dispose();
            } else {
                invalidDate.setText("Invalid Format of Date");
                invalidDate.setBounds(300, 360, 300, 30);
            }
        }
    }

    private boolean checkDate(String inputDate) {
        try {
            LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void writeToFile(String fileName, String content) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
