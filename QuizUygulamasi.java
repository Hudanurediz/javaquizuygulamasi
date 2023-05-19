package quizuygulamasi;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class QuizUygulamasi {

    JFrame frame;
    JRadioButton rb1,rb2,rb3;
    JButton button, buttonNext;
    JLabel soru1 = new JLabel();
    Font font;
    ButtonGroup buttonGroup;
    JFrame parentComponent;
    String answer;
    String randomString;
    int counter = 0;
    static Random random, randomQ;
    ArrayList <String> questions;

    QuizUygulamasi() {
        questions = new ArrayList <String>();
        loadQuestionsFromFile("C:\\Users\\hudan\\OneDrive\\Masaüstü\\javaorn\\sorular.txt");
        runQuiz();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        parentComponent = new JFrame();

        answer = "James Gosling";

        font = new Font(Font.SANS_SERIF, Font.BOLD, 18);

        button = new JButton("Gönder");

        buttonNext = new JButton("Sonraki soru");

        rb1 = new JRadioButton(generateRandomString());
        rb2 = new JRadioButton(generateRandomString());
        rb3 = new JRadioButton(answer);
        

        buttonGroup = new ButtonGroup();
        buttonGroup.add(rb1);
        buttonGroup.add(rb2);
        buttonGroup.add(rb3);

        randomQ = new Random();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        rb1.setBounds((int) (screenWidth * 0.4), (int) (screenHeight * 0.3), 200, 100);
        rb2.setBounds((int) (screenWidth * 0.4), (int) (screenHeight * 0.4), 200, 100);
        rb3.setBounds((int) (screenWidth * 0.4), (int) (screenHeight * 0.5), 200, 100);

        button.setBounds((int) (screenWidth * 0.4), (int) (screenHeight * 0.7), 120, 40);
        button.setBackground(Color.decode("#b27b7a"));
        buttonNext.setBounds((int) (screenWidth * 0.5), (int) (screenHeight * 0.7), 120, 40);
        buttonNext.setBackground(Color.decode("#b27b7a"));

        soru1.setBounds((int) (screenWidth * 0.4), (int) (screenHeight * 0.2), (int) (screenWidth * 0.4), 40);
        soru1.setFont(font);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb1.isSelected()) {
                    if (rb1.getText().equals(answer)) {
                        counter++;
                        JOptionPane.showMessageDialog(parentComponent, "Soruyu dogru cevapladiniz, skorunuz: " + counter);
                    } else {
                        JOptionPane.showMessageDialog(parentComponent, "Hatalı cevap");
                    }
                } else if (rb2.isSelected()) {
                    if (rb2.getText().equals(answer)) {
                        counter++;
                        JOptionPane.showMessageDialog(parentComponent, "Soruyu dogru cevapladiniz, skorunuz: " + counter);
                    } else {
                        JOptionPane.showMessageDialog(parentComponent, "Hatalı cevap");
                    }
                } else if (rb3.isSelected()) {
                    if (rb3.getText().equals(answer)) {
                        counter++;
                        JOptionPane.showMessageDialog(parentComponent, "Soruyu dogru cevapladiniz, skorunuz: " + counter);
                    } else {
                        JOptionPane.showMessageDialog(parentComponent, "Hatalı cevap");
                    }
                } else {
                    JOptionPane.showMessageDialog(parentComponent, "Cevap Seciniz");
                }
            }
        });
        buttonNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (questions.size() > 0) {
                    JOptionPane.showMessageDialog(parentComponent, "Sonraki sayfaya geçtiniz, güncel skorunuz: " + counter);
                    runQuiz();
                } else {
                    JOptionPane.showMessageDialog(parentComponent, "Soru kalmadı, test tamamlandı. Toplam skorunuz: " + counter);
                }
            }
        });

        frame.add(rb3);
        frame.add(rb2);
        frame.add(rb1);
        frame.add(button);
        frame.add(soru1);
        frame.add(buttonNext);

        frame.setSize(screenWidth, screenHeight);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public String generateRandomString() {
        String[] vars = {"Taylor Otwell", "Michael Scott", "John Freeman", "Adam Brown", "David Clark","Marmara","Güneydoğu Anadolu","Ege","1881","1919","1945","Ankara","İstanbul","Adana"};
        random = new Random();
        shuffleArray(vars);
        int index = random.nextInt(vars.length);
        return vars[index];
    }

    public static void shuffleArray(String[] arr) {
        int length = arr.length;
        for (int i = length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public void loadQuestionsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                questions.add(line);
                index++;
                if (index >= 50) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runQuiz() {
        if (questions.size() == 0) {
            JOptionPane.showMessageDialog(parentComponent, "Soru kalmadı, test tamamlandı. Toplam skorunuz: " + counter);
            return;
        }
        random = new Random();
        int randomIndex = random.nextInt(questions.size());
        String question = questions.get(randomIndex);
        questions.remove(randomIndex);
        soru1.setText(question);
        System.out.println("Question: " + question);
    }

    public static void main(String[] args) {
        new QuizUygulamasi();
    }
}
