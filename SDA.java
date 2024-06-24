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

public class SDA extends JFrame implements ActionListener {
    JLabel askQuestionNoSda, invalidSda, questionNumberSda, doneLabelSda, errorLabelSda;
    JTextField questionInputSda, questionSetSda;
    JButton okForQuestionNoSave, nextButtonSda, uploadSda;
    int count, counter = 0;
    JRadioButton trueButtonSda, falseButtonSda;
    ButtonGroup answerGroup;
    ArrayList<String> sdaQuestions = new ArrayList<>();
    ArrayList<Boolean> sdaAnswers = new ArrayList<>();

    SDA() {
        sdaQuestions.clear();
        sdaAnswers.clear();
        setVisible(true);
        setSize(900, 500);
        setLayout(null);
        setResizable(false);
        askQuestionNoSda = new JLabel("Enter How Many Questions You Should Save?");
        askQuestionNoSda.setBounds(280, 100, 400, 50);
        add(askQuestionNoSda);

        questionInputSda = new JTextField();
        questionInputSda.setBounds(320, 170, 200, 30);
        add(questionInputSda);

        okForQuestionNoSave = new JButton("OK");
        okForQuestionNoSave.setBounds(350, 240, 120, 40);
        okForQuestionNoSave.setForeground(Color.white);
        okForQuestionNoSave.setBackground(Color.black);
        okForQuestionNoSave.setFont(new Font("Josefin Sans", Font.BOLD, 20));
        okForQuestionNoSave.setFocusable(false);
        add(okForQuestionNoSave);
        okForQuestionNoSave.addActionListener(this);

        invalidSda = new JLabel("");
        invalidSda.setBounds(310, 320, 300, 30);
    }

    public void questionSetSda() {
        questionNumberSda = new JLabel("Q" + (counter + 1) + ".");
        questionNumberSda.setBounds(40, 70, 100, 100);
        questionNumberSda.setFont(new Font("Josefin Sans", Font.BOLD, 30));

        add(questionNumberSda);
        questionSetSda = new JTextField();
        questionSetSda.setBounds(140, 100, 200, 30);
        add(questionSetSda);
        trueButtonSda = new JRadioButton("True");
        falseButtonSda = new JRadioButton("False");
        trueButtonSda.setBounds(140, 150, 100, 30);
        falseButtonSda.setBounds(240, 150, 100, 30);
        add(trueButtonSda);
        add(falseButtonSda);
        answerGroup = new ButtonGroup();
        answerGroup.add(trueButtonSda);
        answerGroup.add(falseButtonSda);
        nextButtonSda = new JButton("Next");
        nextButtonSda.setBounds(150, 240, 120, 40);
        nextButtonSda.setForeground(Color.white);
        nextButtonSda.setBackground(Color.black);
        nextButtonSda.setFont(new Font("Josefin Sans", Font.BOLD, 20));
        nextButtonSda.setFocusable(false);
        add(nextButtonSda);
        nextButtonSda.addActionListener(this);
        errorLabelSda = new JLabel();
        counter++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(okForQuestionNoSave)) {
            String input = questionInputSda.getText();
            try {
                count = Integer.parseInt(input);
                remove(invalidSda);
                remove(okForQuestionNoSave);
                remove(askQuestionNoSda);
                remove(questionInputSda);
                revalidate();
                repaint();
                questionSetSda();
            } catch (NumberFormatException ex) {
                invalidSda.setText("You Might Have Entered an Invalid Input");
                invalidSda.setForeground(Color.RED);
                add(invalidSda);
                revalidate();
                repaint();
            }
        } else if (e.getSource().equals(nextButtonSda)) {
            if (counter == count) {
                if (questionSetSda.getText().isEmpty()) {
                    errorLabelSda.setText("Enter a question, please");
                    errorLabelSda.setBounds(150, 350, 200, 30);
                    errorLabelSda.setForeground(Color.RED);
                    add(errorLabelSda);

                    revalidate();
                    repaint();
                    return;
                } else if (!trueButtonSda.isSelected() && !falseButtonSda.isSelected()) {
                    errorLabelSda.setText("Select an option, please");
                    errorLabelSda.setBounds(150, 350, 200, 30);
                    errorLabelSda.setForeground(Color.RED);
                    add(errorLabelSda);
                    revalidate();
                    repaint();
                    return;
                }

                if (trueButtonSda.isSelected()) {
                    sdaAnswers.add(true);
                } else if (falseButtonSda.isSelected()) {
                    sdaAnswers.add(false);
                }

                sdaQuestions.add(questionSetSda.getText());
                remove(questionNumberSda);
                remove(questionSetSda);
                remove(trueButtonSda);
                remove(falseButtonSda);
                remove(nextButtonSda);
                remove(errorLabelSda);
                revalidate();
                repaint();

                doneLabelSda = new JLabel("Paper Has Been Set");
                doneLabelSda.setFont(new Font("Josefin Sans", Font.BOLD, 25));
                doneLabelSda.setForeground(Color.BLACK);
                doneLabelSda.setBounds(350, 50, 500, 230);

                uploadSda = new JButton("Upload");
                uploadSda.setBounds(400, 240, 120, 40);
                uploadSda.setForeground(Color.white);
                uploadSda.setBackground(Color.black);
                uploadSda.setFont(new Font("Josefin Sans", Font.BOLD, 20));
                uploadSda.setFocusable(false);
                uploadSda.addActionListener(this);

                add(doneLabelSda);
                add(uploadSda);
                revalidate();
                repaint();
            } else {
                if (questionSetSda.getText().isEmpty()) {
                    errorLabelSda.setText("Enter a question, please");
                    errorLabelSda.setBounds(150, 350, 200, 30);
                    errorLabelSda.setForeground(Color.RED);
                    add(errorLabelSda);
                    revalidate();
                    repaint();
                    return;
                } else if (!trueButtonSda.isSelected() && !falseButtonSda.isSelected()) {
                    errorLabelSda.setText("Select an option, please");
                    errorLabelSda.setBounds(150, 350, 200, 30);
                    errorLabelSda.setForeground(Color.RED);
                    add(errorLabelSda);
                    revalidate();
                    repaint();
                    return;
                }

                if (trueButtonSda.isSelected()) {
                    sdaAnswers.add(true);
                } else if (falseButtonSda.isSelected()) {
                    sdaAnswers.add(false);
                }

                sdaQuestions.add(questionSetSda.getText());

                remove(questionNumberSda);
                remove(questionSetSda);
                remove(trueButtonSda);
                remove(falseButtonSda);
                remove(nextButtonSda);
                remove(errorLabelSda);

                questionSetSda();

                revalidate();
                repaint();
            }
        } else if (e.getSource().equals(uploadSda)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("SDAquestions.txt"))) {
                for (int i = 0; i < sdaQuestions.size(); i++) {
                    writer.write(sdaQuestions.get(i));
                    writer.newLine();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("SDAanswers.txt"))) {
                for (int i = 0; i < sdaAnswers.size(); i++) {
                    writer.write(String.valueOf(sdaAnswers.get(i)));
                    writer.newLine();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("SDAresult.txt"))) {
                writer.write("");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            new teacherDashboard();
            dispose();
        }
    }
}
