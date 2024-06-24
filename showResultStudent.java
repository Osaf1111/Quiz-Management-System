import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class showResultStudent extends JFrame {
    ArrayList<String> oopResultShowStudent = new ArrayList<>();
    ArrayList<String> sdaResultShowStudent = new ArrayList<>();
    // ArrayList<String> osResultShowStudent = new ArrayList<>();
    ArrayList<String> osLabResultShowStudent = new ArrayList<>();
    ArrayList<String> StudentCombine = new ArrayList<>();
    JLabel resultShoWoSlab, printerosLab;
    String abc;

    showResultStudent() {

        try {
            FileReader read = new FileReader("Username.txt");
            Scanner s = new Scanner(read);
            while (s.hasNextLine()) {
                abc = s.nextLine();
                System.out.println(abc);
            }
            s.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int l = abc.length();
        // l++;

        try {
            FileReader oslABRead = new FileReader("osLabresult.txt");// file opened
            Scanner osLABScanner = new Scanner(oslABRead); // scanner class constructor's parameter is object of
                                                           // fileReader "oslABRead"
            while (osLABScanner.hasNextLine()) {
                String result = osLABScanner.nextLine();
                osLabResultShowStudent.add(result); // this will add the result of oS lab in the arraylist
                                                    // "osLabResultShowStudent"
            }
            osLABScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FileReader sDARead = new FileReader("SDAresult.txt");
            Scanner sDAScanner = new Scanner(sDARead);
            while (sDAScanner.hasNextLine()) {
                String result = sDAScanner.nextLine();
                sdaResultShowStudent.add(result);
            }
            sDAScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FileReader oopRead = new FileReader("result.txt");
            Scanner oopScanner = new Scanner(oopRead);
            while (oopScanner.hasNextLine()) {
                String result = oopScanner.nextLine();
                oopResultShowStudent.add(result);
            }
            oopScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // System.out.println(sdaResultShowStudent);
        // System.out.println(oopResultShowStudent);
        // System.out.println(osResultShowStudent);
        System.out.println(osLabResultShowStudent);
        for (int j = 0; j < sdaResultShowStudent.size(); j++) {

            StudentCombine.add(sdaResultShowStudent.get(j));
        }
        for (int i = 0; i < oopResultShowStudent.size(); i++) {
            // if (oopResultShowStudent.get(i).startsWith(abc)) {

            // if (l == oopResultShowStudent.get(i).length()) {
            // oopResultShowStudent.set(i, abc + " OOP Paper Not Attempt");
            // }
            // }
            StudentCombine.add(oopResultShowStudent.get(i));

        }

        for (int i = 0; i < osLabResultShowStudent.size(); i++) {
            // if (osLabResultShowStudent.get(i).startsWith(abc)) {
            // if (l == osLabResultShowStudent.get(i).length()) {
            // osLabResultShowStudent.set(i, abc + " Os Lab Paper Not Attempt");
            // }
            // }
            StudentCombine.add(osLabResultShowStudent.get(i));

        }

        setVisible(true);
        setSize(900, 500);
        setLayout(null);
        setResizable(false);

        resultShoWoSlab = new JLabel("Result Of student " + abc);
        resultShoWoSlab.setBounds(230, 0, 600, 200);
        resultShoWoSlab.setFont(new Font("Anton", Font.BOLD, 35));
        resultShoWoSlab.setForeground(Color.blue);
        add(resultShoWoSlab);
        for (int i = 0, y = 130; i < StudentCombine.size(); i++, y += 30) {

            printerosLab = new JLabel(StudentCombine.get(i));

            printerosLab.setBounds(40, y, 600, 30);
            printerosLab.setFont(new Font("Anton", Font.BOLD, 20));
            add(printerosLab);
        }
    }
}