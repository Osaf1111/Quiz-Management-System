import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class osLabPaperSet extends JFrame implements ActionListener {
    JLabel askQuestionNoOsLab, invalidOsLab, questionNumberOsLab, doneLabelOsLab, errorLabelOsLab;
    JTextField questionInputOsLab, questionSetOsLab;
    JButton okForQuestionNoSaveOsLab, nextButtonOsLab, uploadOsLab;
    int count, counter = 0;
    JRadioButton trueButtonOsLab, falseButtonOsLab;
    ButtonGroup answerGroupOsLab;
    ArrayList<String> osLabQuestions = new ArrayList<>();
    ArrayList<Boolean> osLabAnswers = new ArrayList<>();

    osLabPaperSet() {
        osLabQuestions.clear();
        osLabAnswers.clear();
        setVisible(true);
        setSize(900, 500);
        setLayout(null);
        setResizable(false);
        askQuestionNoOsLab = new JLabel("Enter How Many Questions You Should Save?");
        askQuestionNoOsLab.setBounds(280, 100, 400, 50);
        add(askQuestionNoOsLab);

        questionInputOsLab = new JTextField();
        questionInputOsLab.setBounds(320, 170, 200, 30);
        add(questionInputOsLab);

        okForQuestionNoSaveOsLab = new JButton("OK");
        okForQuestionNoSaveOsLab.setBounds(350, 240, 120, 40);
        okForQuestionNoSaveOsLab.setForeground(Color.white);
        okForQuestionNoSaveOsLab.setBackground(Color.black);
        okForQuestionNoSaveOsLab.setFont(new Font("Josefin Sans", Font.BOLD, 20));
        okForQuestionNoSaveOsLab.setFocusable(false);
        add(okForQuestionNoSaveOsLab);
        okForQuestionNoSaveOsLab.addActionListener(this);

        invalidOsLab = new JLabel("");
        invalidOsLab.setBounds(310, 320, 300, 30);
    }

    public void questionSetOsLab() {
        questionNumberOsLab = new JLabel("Q" + (counter + 1) + ".");
        questionNumberOsLab.setBounds(40, 70, 100, 100);
        questionNumberOsLab.setFont(new Font("Josefin Sans", Font.BOLD, 30));

        add(questionNumberOsLab);
        questionSetOsLab = new JTextField();
        questionSetOsLab.setBounds(140, 100, 200, 30);
        add(questionSetOsLab);
        trueButtonOsLab = new JRadioButton("True");
        falseButtonOsLab = new JRadioButton("False");
        trueButtonOsLab.setBounds(140, 150, 100, 30);
        falseButtonOsLab.setBounds(240, 150, 100, 30);
        add(trueButtonOsLab);
        add(falseButtonOsLab);
        answerGroupOsLab = new ButtonGroup();
        answerGroupOsLab.add(trueButtonOsLab);
        answerGroupOsLab.add(falseButtonOsLab);
        nextButtonOsLab = new JButton("Next");
        nextButtonOsLab.setBounds(150, 240, 120, 40);
        nextButtonOsLab.setForeground(Color.white);
        nextButtonOsLab.setBackground(Color.black);
        nextButtonOsLab.setFont(new Font("Josefin Sans", Font.BOLD, 20));
        nextButtonOsLab.setFocusable(false);
        add(nextButtonOsLab);
        okForQuestionNoSaveOsLab.addActionListener(this);
        nextButtonOsLab.addActionListener(this);
        errorLabelOsLab = new JLabel();
        counter++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(okForQuestionNoSaveOsLab)) {
            String input = questionInputOsLab.getText();
            try {
                count = Integer.parseInt(input);
                remove(invalidOsLab);
                remove(okForQuestionNoSaveOsLab);
                remove(askQuestionNoOsLab);
                remove(questionInputOsLab);
                revalidate();
                repaint();
                questionSetOsLab();
            } catch (NumberFormatException ex) {
                invalidOsLab.setText("You Might Have Entered an Invalid Input");
                invalidOsLab.setForeground(Color.RED);
                add(invalidOsLab);
                revalidate();
                repaint();
            }
        } else if (e.getSource().equals(nextButtonOsLab)) {
            if (counter == count) {
                if (questionSetOsLab.getText().isEmpty()) {
                    errorLabelOsLab.setText("Enter a question, please");
                    errorLabelOsLab.setBounds(150, 350, 200, 30);
                    errorLabelOsLab.setForeground(Color.red);
                    add(errorLabelOsLab);

                    revalidate();
                    repaint();
                    return;
                } else if (!trueButtonOsLab.isSelected() && !falseButtonOsLab.isSelected()) {
                    errorLabelOsLab.setText("Select an option, please");
                    errorLabelOsLab.setBounds(150, 350, 200, 30);
                    errorLabelOsLab.setForeground(Color.red);
                    add(errorLabelOsLab);
                    revalidate();
                    repaint();
                    return;
                }

                if (trueButtonOsLab.isSelected()) {
                    osLabAnswers.add(true);
                } else if (falseButtonOsLab.isSelected()) {
                    osLabAnswers.add(false);
                }

                osLabQuestions.add(questionSetOsLab.getText());
                remove(questionNumberOsLab);
                remove(questionSetOsLab);
                remove(trueButtonOsLab);
                remove(falseButtonOsLab);
                remove(nextButtonOsLab);
                remove(errorLabelOsLab);
                revalidate();
                repaint();
                doneLabelOsLab = new JLabel("Paper Has Been Set");
                doneLabelOsLab.setFont(new Font("Josefin Sans", Font.BOLD, 25));
                doneLabelOsLab.setForeground(Color.BLACK);
                doneLabelOsLab.setBounds(350, 50, 500, 230);
                uploadOsLab = new JButton("Upload");
                uploadOsLab.setBounds(400, 240, 120, 40);
                uploadOsLab.setForeground(Color.white);
                uploadOsLab.setBackground(Color.black);
                uploadOsLab.setFont(new Font("Josefin Sans", Font.BOLD, 20));
                uploadOsLab.setFocusable(false);
                add(uploadOsLab);
                uploadOsLab.addActionListener(this);
                add(doneLabelOsLab);
                revalidate();
                repaint();
            } else {
                if (questionSetOsLab.getText().isEmpty()) {
                    errorLabelOsLab.setText("Enter a question, please");
                    errorLabelOsLab.setBounds(150, 350, 200, 30);
                    errorLabelOsLab.setForeground(Color.red);
                    add(errorLabelOsLab);
                    revalidate();
                    repaint();
                    return;
                } else if (!trueButtonOsLab.isSelected() && !falseButtonOsLab.isSelected()) {
                    errorLabelOsLab.setText("Select an option, please");
                    errorLabelOsLab.setBounds(150, 350, 200, 30);
                    errorLabelOsLab.setForeground(Color.red);
                    add(errorLabelOsLab);
                    revalidate();
                    repaint();
                    return;
                }

                if (trueButtonOsLab.isSelected()) {
                    osLabAnswers.add(true);
                } else if (falseButtonOsLab.isSelected()) {
                    osLabAnswers.add(false);
                }

                osLabQuestions.add(questionSetOsLab.getText());
                remove(questionNumberOsLab);
                remove(questionSetOsLab);
                remove(trueButtonOsLab);
                remove(falseButtonOsLab);
                remove(nextButtonOsLab);
                remove(errorLabelOsLab);
                questionSetOsLab();
                revalidate();
                repaint();
            }
        } else if (e.getSource().equals(uploadOsLab)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("OsLabquestions.txt"))) {
                for (int i = 0; i < osLabQuestions.size(); i++) {
                    writer.write(osLabQuestions.get(i));
                    writer.newLine();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("OsLabanswers.txt"))) {
                for (int i = 0; i < osLabAnswers.size(); i++) {
                    writer.write(String.valueOf(osLabAnswers.get(i)));
                    writer.newLine();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                Files.deleteIfExists(Paths.get("osLabresult.txt"));
                 Files.createFile(Paths.get("osLabresult.txt")); // Create a new empty file
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            new teacherDashboard();
            dispose();
        }
    }
}
