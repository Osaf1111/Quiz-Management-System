import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class oopStudent extends JFrame implements ActionListener {
    ArrayList<String> QuestionOfOop = new ArrayList<>();
    ArrayList<Boolean> AnswerOfOop = new ArrayList<>();
    ArrayList<Boolean> AnswerStudent = new ArrayList<>();
    JLabel QuestionLabel, errorLabelStudent, scoreLabel;
    JRadioButton truebttn, falsebttn;
    ButtonGroup answeGroupStudent;
    JButton nextBttn, scoreButton, backBttn;
    int i = 0, Count = 0;
    int abc = 1;

    oopStudent() {
        setVisible(true);
        setSize(900, 500);
        setLayout(null);
        setResizable(false);

        try (BufferedReader br = new BufferedReader(new FileReader("OOpquestions.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                QuestionOfOop.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("Oopanswers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                boolean answer = Boolean.parseBoolean(line);
                AnswerOfOop.add(answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // System.out.println(QuestionOfOop);
        // System.out.println(AnswerOfOop);
        backBttn = new JButton("Back");
        backBttn.setBounds(400, 250, 80, 30);
        backBttn.setForeground(Color.white);
        backBttn.setBackground(Color.BLACK);
        backBttn.setFocusable(false);
        StudentQuiz();
    }

    void StudentQuiz() {

        QuestionLabel = new JLabel(abc + ". " + QuestionOfOop.get(i));
        QuestionLabel.setBounds(40, 70, 500, 100);
        QuestionLabel.setFont(new Font("Josefin Sans", Font.BOLD, 20));
        truebttn = new JRadioButton("True");
        falsebttn = new JRadioButton("False");
        truebttn.setBounds(60, 180, 100, 30);
        falsebttn.setBounds(170, 180, 100, 30);
        truebttn.setFocusable(false); // Disable focus
        falsebttn.setFocusable(false);
        add(truebttn);
        add(falsebttn);
        answeGroupStudent = new ButtonGroup();
        answeGroupStudent.add(truebttn);
        answeGroupStudent.add(falsebttn);
        this.add(QuestionLabel);

        nextBttn = new JButton("Next");
        nextBttn.setBounds(100, 250, 80, 30);
        nextBttn.setForeground(Color.WHITE);
        nextBttn.setBackground(Color.black);
        nextBttn.setFocusable(false);
        nextBttn.addActionListener(this);
        add(nextBttn);
        abc++;
        i++;
        errorLabelStudent = new JLabel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(nextBttn)) {
            if (i == QuestionOfOop.size()) {
                if (!truebttn.isSelected() && !falsebttn.isSelected()) {
                    errorLabelStudent.setText("Not Selected");
                    errorLabelStudent.setBounds(280, 400, 600, 50);
                    errorLabelStudent.setFont(new Font("Josefin Sans", Font.BOLD, 28));
                    errorLabelStudent.setForeground(Color.red);
                    add(errorLabelStudent);
                    return;
                }

                if (truebttn.isSelected()) {
                    AnswerStudent.add(true);
                } else if (falsebttn.isSelected()) {
                    AnswerStudent.add(false);
                }
                remove(truebttn);
                remove(falsebttn);
                remove(errorLabelStudent);
                remove(nextBttn);
                remove(QuestionLabel);
                revalidate();
                repaint();
                scoreButton = new JButton("Score");
                scoreButton.setBounds(400, 240, 120, 40);
                scoreButton.setForeground(Color.white);
                scoreButton.setBackground(Color.black);
                scoreButton.setFont(new Font("Josefin Sans", Font.BOLD, 20));
                scoreButton.setFocusable(false);

                scoreButton.addActionListener(this);
                add(scoreButton);
                revalidate();
                repaint();
                // System.out.println(AnswerStudent);
            } else {
                if (!truebttn.isSelected() && !falsebttn.isSelected()) {
                    errorLabelStudent.setText("Not Selected");
                    errorLabelStudent.setBounds(280, 400, 600, 50);
                    errorLabelStudent.setFont(new Font("Josefin Sans", Font.BOLD, 28));
                    errorLabelStudent.setForeground(Color.red);
                    add(errorLabelStudent);
                    return;
                }

                if (truebttn.isSelected()) {
                    AnswerStudent.add(true);
                } else if (falsebttn.isSelected()) {
                    AnswerStudent.add(false);
                }
                remove(truebttn);
                remove(falsebttn);
                remove(errorLabelStudent);
                remove(nextBttn);
                remove(QuestionLabel);
                StudentQuiz();
                revalidate();
                repaint();
            }
        } else if (e.getSource().equals(scoreButton)) {
            remove(scoreButton);
            revalidate();
            repaint();

            for (int j = 0; j < AnswerStudent.size(); j++) {
                if (AnswerStudent.get(j).equals(AnswerOfOop.get(j))) {
                    Count++;
                }
            }
            scoreLabel = new JLabel();
            scoreLabel.setText("Your Score is " + Count + " / " + AnswerOfOop.size());
            scoreLabel.setFont(new Font("Josefin Sans", Font.BOLD, 25));
            scoreLabel.setForeground(Color.BLACK);
            scoreLabel.setBounds(350, 50, 500, 230);
            add(scoreLabel);
            backBttn.addActionListener(this);
            add(backBttn);
            writeScoreToFile(Count);
        } else if (e.getSource().equals(backBttn)) {
           new QuizGo();
           dispose();

        }
    }

    private void writeScoreToFile(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt", true))) {
            int k = AnswerOfOop.size();
            writer.write(" OOP(Th) Score: " + score + " out of " + k);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
}