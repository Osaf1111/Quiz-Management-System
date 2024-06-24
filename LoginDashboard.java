import javax.swing.JButton; //Jbutton is a class inside swing which is inside the javax
import javax.swing.JFrame; //JFrame is a class inside swing which is inside the javax
import javax.swing.JLabel; //JLabel is a class inside swing which is inside the javax
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.Color; //this is for color
import java.awt.Font; //this is for font
import java.awt.event.ActionEvent; //these are event listeners
import java.awt.event.ActionListener; //these are event listeners

public class LoginDashboard extends JFrame implements ActionListener {
    // here we are declaring our objects
    JLabel labelOption;
    JButton studentButton;
    JButton teacherButton;

    public LoginDashboard() {
        setVisible(true);
        setTitle("Quiz Management System");
        setSize(900, 500);
        setLayout(null); // this will disable the by default layout and you will have to set custom
                         // layout by setting bounds
        setResizable(false); // this is to disable the screen resizable, full screen
        setBackground(Color.white);
        // here we are initializing our objects and giving properties to our JLabel
        // object "labelOption"
        labelOption = new JLabel("PLEASE CONFIRM YOUR OPTION");
        labelOption.setBounds(250, 50, 600, 120);
        labelOption.setFont(new Font("Josefin Sans", Font.BOLD, 29));
        labelOption.setForeground(Color.black);
        add(labelOption); // till we do not add our label it will not be visible on the screen (window or
                          // frame)

        // here we are initializing our objects and giving properties to our JButton
        // object "teacherButton"
        teacherButton = new JButton("Teacher");
        teacherButton.setBounds(400, 150, 150, 50);
        teacherButton.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        teacherButton.setForeground(Color.white);
        teacherButton.setBackground(Color.black);
        teacherButton.setFocusable(false);
        add(teacherButton);
        teacherButton.addActionListener(this);
        // this will enable the functionality to our button what will happen if
        // the button is clicked or triggered, this refers to the current object

        // here we are initializing our objects and giving properties to our JButton
        // object "studentButton"
        studentButton = new JButton("Student");
        studentButton.setBounds(400, 230, 150, 50);
        studentButton.setFont(new Font("Josefin Sans", Font.BOLD, 28));
        studentButton.setForeground(Color.white);
        studentButton.setBackground(Color.black);
        studentButton.setFocusable(false);
        add(studentButton);
        studentButton.addActionListener(this);
        // this will enable the functionality to our button what will happen if
        // the button is clicked or triggered, this refers to the current object
    }

    // in the ActionListener there is a function defined which is actionPerformed
    // which we override in order to give
    // our customized and desired functionality and definition to the action
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(studentButton)) {
            new StudentLogin();
            dispose();
            // here is the constructor of StudentLogin class is being called when we are
            // clicking on student button
            // dispose will close the last window and will open the new window for us
            // for e.g in this case StudentLogin class will be loaded and displayed and
            // LoginDashboard class will be hidden

        } else if (e.getSource().equals(teacherButton)) {
            new LoginTeacher();
            dispose();
            // here is the constructor of LoginTeacher class is being called when we are
            // clicking on teacher button
            // dispose will close the last window and will open the new window for us
            // for e.g in this case LoginTeacher class will be loaded and displayed and
            // LoginDashboard class will be hidden
        }
    }
}
