import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class teacherDashboard extends JFrame implements ActionListener {
    JButton resultButton, menu, OopTheory, osLab, sda, setDateButton;
    JLabel welcomeDashboardTeacher, error;
    File f1 = new File("OOpquestions.txt");
    File f2 = new File("SDAquestions.txt");
    File f3 = new File("OsLabquestions.txt");
    File osResult = new File("osLabresult.txt");
    File sdaresult = new File("SDAresult.txt");
    File oopresult = new File("result.txt");

    public teacherDashboard() {
        setVisible(true);
        setTitle("Dashboard");
        setSize(900, 500);
        setLayout(null);
        setResizable(false);
        setBackground(Color.white);

        welcomeDashboardTeacher = new JLabel("Welcome To Teacher Dashboard");
        welcomeDashboardTeacher.setBounds(180, 0, 600, 200);
        welcomeDashboardTeacher.setFont(new Font("Anton", Font.BOLD, 35));
        welcomeDashboardTeacher.setForeground(Color.black);
        add(welcomeDashboardTeacher);

        resultButton = new JButton("Result");
        resultButton.setBounds(40, 150, 250, 50);
        resultButton.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        resultButton.setForeground(Color.white);
        resultButton.setBackground(Color.black);
        resultButton.setFocusable(false);
        add(resultButton);

        menu = new JButton("Main Menu");
        menu.setBounds(300, 150, 250, 50);
        menu.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        menu.setForeground(Color.white);
        menu.setBackground(Color.black);
        menu.setFocusable(false);
        add(menu);

        OopTheory = new JButton("Oop(Th)");
        OopTheory.setBounds(560, 150, 250, 50);
        OopTheory.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        OopTheory.setForeground(Color.white);
        OopTheory.setBackground(Color.black);
        OopTheory.setFocusable(false);
        add(OopTheory);

        osLab = new JButton("OS(Lab)");
        osLab.setBounds(40, 250, 250, 50);
        osLab.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        osLab.setForeground(Color.white);
        osLab.setBackground(Color.black);
        osLab.setFocusable(false);
        add(osLab);

        sda = new JButton("SDA(Theory)");
        sda.setBounds(300, 250, 250, 50);
        sda.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        sda.setForeground(Color.white);
        sda.setBackground(Color.black);
        sda.setFocusable(false);
        add(sda);

        setDateButton = new JButton("Set Date");
        setDateButton.setBounds(560, 250, 250, 50);
        setDateButton.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        setDateButton.setForeground(Color.white);
        setDateButton.setBackground(Color.black);
        setDateButton.setFocusable(false);
        add(setDateButton);

        resultButton.addActionListener(this);
        menu.addActionListener(this);
        OopTheory.addActionListener(this);
        osLab.addActionListener(this);
        sda.addActionListener(this);
        setDateButton.addActionListener(this);

        error = new JLabel();
        error.setBounds(250, 400, 400, 30); // Set the bounds of the error label
        error.setForeground(Color.RED);
        error.setFont(new Font("Josefin Sans", Font.BOLD, 18));
        add(error);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(resultButton)) {
            // Handle result button action
            if (!(f1.length() > 0) && !(f2.length() > 0) && !(f3.length() > 0)) {
                error.setText("Quiz has not been set yet");
                return;
            } else if (osResult.length() == 0 && oopresult.length() == 0 && sdaresult.length() == 0) {
                error.setText("Quiz has not been attempted yet");
                return;
            } else {
                new showResultStudent();
            }
        } else if (e.getSource().equals(menu)) {
            new LoginDashboard();
            dispose();
        } else if (e.getSource().equals(OopTheory)) {
            new OopPaperSet();
            dispose();
        } else if (e.getSource().equals(osLab)) {
            new osLabPaperSet();
            dispose();
        } else if (e.getSource().equals(sda)) {
            // Handle DMS(Theory) button action
            new SDA();
            dispose();
        } else if (e.getSource().equals(setDateButton)) {
            // Handle Set Date button action
            new setDate();
            dispose();
        }
    }
}
