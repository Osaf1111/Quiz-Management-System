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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

interface oopQuestionSet {
    void QuestionSet();
}

class OopPaperSet extends JFrame implements ActionListener, oopQuestionSet {
    JLabel askQuestionNo, invalid, questionNumber, DoneLable, errorLabel;
    JTextField questionInput, questionSet;
    JButton okForQuestionNoSave, nextButtonTo, UploadOop;
    int Count, Counter = 0;
    JRadioButton trueButton, falseButton;
    ButtonGroup answerGroup;
    ArrayList<String> questions = new ArrayList<>();
    ArrayList<Boolean> answers = new ArrayList<>();

    OopPaperSet() {
        questions.clear();
        answers.clear();
        setVisible(true);
        setSize(900, 500);
        setLayout(null);
        setResizable(false);

        askQuestionNo = new JLabel("How many question you want to add to quiz?");
        askQuestionNo.setBounds(280, 100, 400, 50);
        add(askQuestionNo);

        questionInput = new JTextField();
        questionInput.setBounds(320, 170, 200, 30);
        add(questionInput);

        okForQuestionNoSave = new JButton("OK");
        okForQuestionNoSave.setBounds(350, 240, 120, 40);
        okForQuestionNoSave.setForeground(Color.white);
        okForQuestionNoSave.setBackground(Color.black);
        okForQuestionNoSave.setFont(new Font("Josefin Sans", Font.BOLD, 20));
        okForQuestionNoSave.setFocusable(false);
        add(okForQuestionNoSave);
        okForQuestionNoSave.addActionListener(this);

        invalid = new JLabel("");
        invalid.setBounds(310, 320, 200, 30);

    }

    public void QuestionSet() {
        questionNumber = new JLabel("Q" + (Counter + 1) + ".");
        questionNumber.setBounds(40, 70, 100, 100);
        questionNumber.setFont(new Font("Josefin Sans", Font.BOLD, 30));

        add(questionNumber);
        questionSet = new JTextField();
        questionSet.setBounds(140, 100, 200, 30);
        add(questionSet);
        trueButton = new JRadioButton("True");
        falseButton = new JRadioButton("False");
        trueButton.setBounds(140, 150, 100, 30);
        falseButton.setBounds(240, 150, 100, 30);
        add(trueButton);
        add(falseButton);
        answerGroup = new ButtonGroup();
        answerGroup.add(trueButton);
        answerGroup.add(falseButton);
        nextButtonTo = new JButton("next");
        nextButtonTo.setBounds(150, 240, 120, 40);
        nextButtonTo.setForeground(Color.white);
        nextButtonTo.setBackground(Color.black);
        nextButtonTo.setFont(new Font("Josefin Sans", Font.BOLD, 20));
        nextButtonTo.setFocusable(false);
        add(nextButtonTo);
        okForQuestionNoSave.addActionListener(this);
        nextButtonTo.addActionListener(this);
        errorLabel = new JLabel();
        Counter++;
    }

    private void clearResultFile() {
        try {
            Files.write(Paths.get("result.txt"), new byte[0]);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(okForQuestionNoSave)) {
            String input = questionInput.getText();
            try {
                Count = Integer.parseInt(input);
                remove(invalid);
                remove(okForQuestionNoSave);
                remove(askQuestionNo);
                remove(questionInput);
                revalidate();
                repaint();
                QuestionSet();
            } catch (NumberFormatException ex) {
                invalid.setText("You Might Have Entered an Invalid Input");
                invalid.setBounds(310, 320, 300, 30);
                invalid.setForeground(Color.RED);
                add(invalid);
                revalidate();
                repaint();
            }
        } else if (e.getSource().equals(nextButtonTo)) {
            if (Counter == Count) {
                if (questionSet.getText().isEmpty()) {
                    errorLabel.setText("Enter a question, please");
                    errorLabel.setBounds(150, 350, 200, 30);
                    errorLabel.setForeground(Color.red);
                    add(errorLabel);

                    revalidate();
                    repaint();
                    return;
                } else if (!trueButton.isSelected() && !falseButton.isSelected()) {
                    errorLabel.setText("Select an option, please");
                    errorLabel.setBounds(150, 350, 200, 30);
                    errorLabel.setForeground(Color.red);
                    add(errorLabel);
                    revalidate();
                    repaint();
                    return;
                }

                if (trueButton.isSelected()) {
                    answers.add(true);
                } else if (falseButton.isSelected()) {
                    answers.add(false);
                }

                questions.add(questionSet.getText());
                remove(questionNumber);
                remove(questionSet);
                remove(trueButton);
                remove(falseButton);
                remove(nextButtonTo);
                remove(errorLabel);
                revalidate();
                repaint();
                DoneLable = new JLabel("Paper Has Been Set");
                DoneLable.setFont(new Font("Josefin Sans", Font.BOLD, 25));
                DoneLable.setForeground(Color.BLACK);
                DoneLable.setBounds(350, 50, 500, 230);
                UploadOop = new JButton("Upload");

                UploadOop.setBounds(400, 240, 120, 40);
                UploadOop.setForeground(Color.white);
                UploadOop.setBackground(Color.black);
                UploadOop.setFont(new Font("Josefin Sans", Font.BOLD, 20));
                UploadOop.setFocusable(false);
                add(UploadOop);
                UploadOop.addActionListener(this);

                add(DoneLable);
                revalidate();
                repaint();
            } else {
                if (questionSet.getText().isEmpty()) {
                    errorLabel.setText("Enter a question, please");
                    errorLabel.setBounds(150, 350, 200, 30);
                    errorLabel.setForeground(Color.red);
                    add(errorLabel);
                    revalidate();
                    repaint();
                    return;
                } else if (!trueButton.isSelected() && !falseButton.isSelected()) {
                    errorLabel.setText("Select an option, please");
                    errorLabel.setBounds(150, 350, 200, 30);
                    errorLabel.setForeground(Color.red);
                    add(errorLabel);
                    revalidate();
                    repaint();
                    return;
                }

                if (trueButton.isSelected()) {
                    answers.add(true);
                } else if (falseButton.isSelected()) {
                    answers.add(false);
                }

                questions.add(questionSet.getText());

                remove(questionNumber);
                remove(questionSet);
                remove(trueButton);
                remove(falseButton);
                remove(nextButtonTo);
                remove(errorLabel);

                QuestionSet();

                revalidate();
                repaint();
            }
        } else if (e.getSource().equals(UploadOop)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("OOpquestions.txt"))) {
                for (int i = 0; i < questions.size(); i++) {
                    writer.write(questions.get(i));
                    writer.newLine();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Oopanswers.txt"))) {
                for (int i = 0; i < answers.size(); i++) {
                    writer.write(String.valueOf(answers.get(i)));
                    writer.newLine();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            clearResultFile(); // Clear the result.txt file

            new teacherDashboard();
            dispose();
        }
    }
}
