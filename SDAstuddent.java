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

public class SDAstuddent extends JFrame implements ActionListener {
    ArrayList<String> QuestionOfSDA = new ArrayList<>();
    ArrayList<Boolean> AnswerOfSDA = new ArrayList<>();
    ArrayList<Boolean> AnswerStudentSDA = new ArrayList<>();
    JLabel QuestionLabel, errorLabelStudent, scoreLabel;
    JRadioButton truebttnsda, falsebttnsda;
    ButtonGroup answeGroupStudentsda;
    JButton nextBttnsda, scoreButtonsda, backBttnsda;
    int i = 0, Count = 0;
    int abc = 1;

    SDAstuddent() {
        setVisible(true);
        setSize(900, 500);
        setLayout(null);
        setResizable(false);

        try (BufferedReader br = new BufferedReader(new FileReader("SDAquestions.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                QuestionOfSDA.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("SDAanswers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                boolean answer = Boolean.parseBoolean(line);
                AnswerOfSDA.add(answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(QuestionOfSDA);
        System.out.println(AnswerOfSDA);
        backBttnsda = new JButton("Back");
        backBttnsda.setBounds(400, 250, 80, 30);
        backBttnsda.setForeground(Color.white);
        backBttnsda.setBackground(Color.black);
        backBttnsda.setFocusable(false);
        StudentQuizSDA();
    }

    void StudentQuizSDA() {

        QuestionLabel = new JLabel(abc + ". " + QuestionOfSDA.get(i));
        QuestionLabel.setBounds(40, 70, 500, 100);
        QuestionLabel.setFont(new Font("Josefin Sans", Font.BOLD, 20));
        truebttnsda = new JRadioButton("True");
        falsebttnsda = new JRadioButton("False");
        truebttnsda.setBounds(60, 180, 100, 30);
        falsebttnsda.setBounds(170, 180, 100, 30);
        truebttnsda.setFocusable(false); // Disable focus
        falsebttnsda.setFocusable(false);
        add(truebttnsda);
        add(falsebttnsda);
        answeGroupStudentsda = new ButtonGroup();
        answeGroupStudentsda.add(truebttnsda);
        answeGroupStudentsda.add(falsebttnsda);
        this.add(QuestionLabel);

        nextBttnsda = new JButton("Next");
        nextBttnsda.setBounds(100, 250, 80, 30);
        nextBttnsda.setForeground(Color.WHITE);
        nextBttnsda.setBackground(Color.BLACK);
        nextBttnsda.setFocusable(false);
        nextBttnsda.addActionListener(this);
        add(nextBttnsda);
        abc++;
        i++;
        errorLabelStudent = new JLabel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(nextBttnsda)) {
            if (i == QuestionOfSDA.size()) {
                if (!truebttnsda.isSelected() && !falsebttnsda.isSelected()) {
                    errorLabelStudent.setText("Not Selected");
                    errorLabelStudent.setBounds(280, 400, 600, 50);
                    errorLabelStudent.setFont(new Font("Josefin Sans", Font.BOLD, 28));
                    errorLabelStudent.setForeground(Color.red);
                    add(errorLabelStudent);
                    return;
                }

                if (truebttnsda.isSelected()) {
                    AnswerStudentSDA.add(true);
                } else if (falsebttnsda.isSelected()) {
                    AnswerStudentSDA.add(false);
                }
                remove(truebttnsda);
                remove(falsebttnsda);
                remove(errorLabelStudent);
                remove(nextBttnsda);
                remove(QuestionLabel);
                revalidate();
                repaint();
                scoreButtonsda = new JButton("Score");
                scoreButtonsda.setBounds(400, 240, 120, 40);
                scoreButtonsda.setForeground(Color.white);
                scoreButtonsda.setBackground(Color.black);
                scoreButtonsda.setFont(new Font("Josefin Sans", Font.BOLD, 20));
                scoreButtonsda.setFocusable(false);

                scoreButtonsda.addActionListener(this);
                add(scoreButtonsda);
                revalidate();
                repaint();
                System.out.println(AnswerStudentSDA);
            } else {
                if (!truebttnsda.isSelected() && !falsebttnsda.isSelected()) {
                    errorLabelStudent.setText("Not Selected");
                    errorLabelStudent.setBounds(280, 400, 600, 50);
                    errorLabelStudent.setFont(new Font("Josefin Sans", Font.BOLD, 28));
                    errorLabelStudent.setForeground(Color.red);
                    add(errorLabelStudent);
                    return;
                }

                if (truebttnsda.isSelected()) {
                    AnswerStudentSDA.add(true);
                } else if (falsebttnsda.isSelected()) {
                    AnswerStudentSDA.add(false);
                }
                remove(truebttnsda);
                remove(falsebttnsda);
                remove(errorLabelStudent);
                remove(nextBttnsda);
                remove(QuestionLabel);
                StudentQuizSDA();
                revalidate();
                repaint();
            }
        } else if (e.getSource().equals(scoreButtonsda)) {
            remove(scoreButtonsda);
            revalidate();
            repaint();

            for (int j = 0; j < AnswerStudentSDA.size(); j++) {
                if (AnswerStudentSDA.get(j).equals(AnswerOfSDA.get(j))) {
                    Count++;
                }
            }
            scoreLabel = new JLabel();
            scoreLabel.setText("Your Score is " + Count + " / " + AnswerOfSDA.size());
            scoreLabel.setFont(new Font("Josefin Sans", Font.BOLD, 25));
            scoreLabel.setForeground(Color.BLACK);
            scoreLabel.setBounds(350, 50, 500, 230);
            add(scoreLabel);
            backBttnsda.addActionListener(this);
            add(backBttnsda);
            writeScoreToFileSDA(Count);
        } else if (e.getSource().equals(backBttnsda)) {
            new QuizGo();
            dispose();

        }
    }

    private void writeScoreToFileSDA(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("SDAresult.txt", true))) {
            int k = AnswerOfSDA.size();
            writer.write(" SDA Score: " + score + " out of " + k);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
