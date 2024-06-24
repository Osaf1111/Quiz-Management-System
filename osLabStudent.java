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
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class osLabStudent extends JFrame implements ActionListener {
    ArrayList<String> QuestionOfOsLab = new ArrayList<>();
    ArrayList<Boolean> AnswerOfOsLab = new ArrayList<>();
    ArrayList<Boolean> AnswerStudentosLab = new ArrayList<>();
    JLabel QuestionLabel, errorLabelStudent, scoreLabel;
    JRadioButton truebttnosLab, falsebttnosLab;
    ButtonGroup answeGroupStudentosLab;
    JButton nextBttnOslab, scoreButtonOslab, backBttnOslab;
    int i = 0, Count = 0;
    int abc = 1;

    osLabStudent() {
        setVisible(true);
        setSize(900, 500);
        setLayout(null);
        setResizable(false);

        try (BufferedReader br = new BufferedReader(new FileReader("OsLabquestions.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                QuestionOfOsLab.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("OsLabanswers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                boolean answer = Boolean.parseBoolean(line);
                AnswerOfOsLab.add(answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(QuestionOfOsLab);
        System.out.println(AnswerOfOsLab);
        backBttnOslab = new JButton("Back");
        backBttnOslab.setBounds(400, 250, 80, 30);
        backBttnOslab.setForeground(Color.white);
        backBttnOslab.setBackground(Color.black);
        backBttnOslab.setFocusable(false);
        StudentQuizosLab();
    }

    void StudentQuizosLab() {
        QuestionLabel = new JLabel(abc + ". " + QuestionOfOsLab.get(i));
        QuestionLabel.setBounds(40, 70, 500, 100);
        QuestionLabel.setFont(new Font("Josefin Sans", Font.BOLD, 20));
        truebttnosLab = new JRadioButton("True");
        falsebttnosLab = new JRadioButton("False");
        truebttnosLab.setBounds(60, 180, 100, 30);
        falsebttnosLab.setBounds(170, 180, 100, 30);
        truebttnosLab.setFocusable(false); // Disable focus
        falsebttnosLab.setFocusable(false);
        add(truebttnosLab);
        add(falsebttnosLab);
        answeGroupStudentosLab = new ButtonGroup();
        answeGroupStudentosLab.add(truebttnosLab);
        answeGroupStudentosLab.add(falsebttnosLab);
        this.add(QuestionLabel);

        nextBttnOslab = new JButton("Next");
        nextBttnOslab.setBounds(100, 250, 80, 30);
        nextBttnOslab.setForeground(Color.WHITE);
        nextBttnOslab.setBackground(Color.black);
        nextBttnOslab.setFocusable(false);
        nextBttnOslab.addActionListener(this);
        add(nextBttnOslab);
        abc++;
        i++;
        errorLabelStudent = new JLabel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(nextBttnOslab)) {
            if (i == QuestionOfOsLab.size()) {
                if (!truebttnosLab.isSelected() && !falsebttnosLab.isSelected()) {
                    errorLabelStudent.setText("Not Selected");
                    errorLabelStudent.setBounds(280, 400, 600, 50);
                    errorLabelStudent.setFont(new Font("Josefin Sans", Font.BOLD, 28));
                    errorLabelStudent.setForeground(Color.red);
                    add(errorLabelStudent);
                    return;
                }

                if (truebttnosLab.isSelected()) {
                    AnswerStudentosLab.add(true);
                } else if (falsebttnosLab.isSelected()) {
                    AnswerStudentosLab.add(false);
                }
                remove(truebttnosLab);
                remove(falsebttnosLab);
                remove(errorLabelStudent);
                remove(nextBttnOslab);
                remove(QuestionLabel);
                revalidate();
                repaint();
                scoreButtonOslab = new JButton("Score");
                scoreButtonOslab.setBounds(400, 240, 120, 40);
                scoreButtonOslab.setForeground(Color.white);
                scoreButtonOslab.setBackground(Color.black);
                scoreButtonOslab.setFont(new Font("Josefin Sans", Font.BOLD, 20));
                scoreButtonOslab.setFocusable(false);

                scoreButtonOslab.addActionListener(this);
                add(scoreButtonOslab);
                revalidate();
                repaint();
                System.out.println(AnswerStudentosLab);
            } else {
                if (!truebttnosLab.isSelected() && !falsebttnosLab.isSelected()) {
                    errorLabelStudent.setText("Not Selected");
                    errorLabelStudent.setBounds(280, 400, 600, 50);
                    errorLabelStudent.setFont(new Font("Josefin Sans", Font.BOLD, 28));
                    errorLabelStudent.setForeground(Color.red);
                    add(errorLabelStudent);
                    return;
                }

                if (truebttnosLab.isSelected()) {
                    AnswerStudentosLab.add(true);
                } else if (falsebttnosLab.isSelected()) {
                    AnswerStudentosLab.add(false);
                }
                remove(truebttnosLab);
                remove(falsebttnosLab);
                remove(errorLabelStudent);
                remove(nextBttnOslab);
                remove(QuestionLabel);
                StudentQuizosLab();
                revalidate();
                repaint();
            }
        } else if (e.getSource().equals(scoreButtonOslab)) {
            remove(scoreButtonOslab);
            revalidate();
            repaint();

            for (int j = 0; j < AnswerStudentosLab.size(); j++) {
                if (AnswerStudentosLab.get(j).equals(AnswerOfOsLab.get(j))) {
                    Count++;
                }
            }
            scoreLabel = new JLabel();
            scoreLabel.setText("Your Score is " + Count + " / " + AnswerOfOsLab.size());
            scoreLabel.setFont(new Font("Josefin Sans", Font.BOLD, 25));
            scoreLabel.setForeground(Color.BLACK);
            scoreLabel.setBounds(350, 50, 500, 230);
            add(scoreLabel);
            backBttnOslab.addActionListener(this);
            add(backBttnOslab);
            writeScoreToFileOslab(Count);
        } else if (e.getSource().equals(backBttnOslab)) {
            new QuizGo();
            dispose();
        }
    }

    private void writeScoreToFileOslab(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("osLabresult.txt", true))) {
            int k = AnswerOfOsLab.size();
            writer.write(" OS Lab Score: " + score + " out of " + k);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
}
