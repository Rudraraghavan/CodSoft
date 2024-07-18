import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Task_2_GUI {
    private JFrame frame;
    private JTextField subjectField;
    private JTextArea marksArea;
    private JTextArea resultArea;

    public Task_2_GUI() {
        // Frame setup
        frame = new JFrame("Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout(10, 10));

        // Title
        JLabel titleLabel = new JLabel("Grade Calculator", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 1, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Subject input
        JLabel subjectLabel = new JLabel("Number of subjects:");
        subjectField = new JTextField();
        inputPanel.add(subjectLabel);
        inputPanel.add(subjectField);

        // Marks input
        JLabel marksLabel = new JLabel("Enter marks (comma separated):");
        marksArea = new JTextArea(3, 20);
        marksArea.setLineWrap(true);
        marksArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(marksArea);
        inputPanel.add(marksLabel);
        inputPanel.add(scrollPane);

        // Calculate button
        JButton calculateButton = createButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());
        inputPanel.add(calculateButton);

        // Result area
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 16));
        resultArea.setBackground(new Color(245, 245, 245));
        resultArea.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane resultScrollPane = new JScrollPane(resultArea);

        // Adding components to frame
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(resultScrollPane, BorderLayout.SOUTH);

        // Display frame
        frame.setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String subjectText = subjectField.getText();
            String marksText = marksArea.getText();
            
            try {
                int numberOfSubjects = Integer.parseInt(subjectText.trim());
                String[] marksStrings = marksText.split(",");
                if (marksStrings.length != numberOfSubjects) {
                    resultArea.setText("Error: Number of subjects does not match the number of marks entered.");
                    return;
                }
                
                int[] marks = new int[numberOfSubjects];
                int totalMarks = 0;
                
                for (int i = 0; i < numberOfSubjects; i++) {
                    marks[i] = Integer.parseInt(marksStrings[i].trim());
                    if (marks[i] < 0 || marks[i] > 100) {
                        resultArea.setText("Error: Marks should be between 0 and 100.");
                        return;
                    }
                    totalMarks += marks[i];
                }
                
                double averagePercentage = (double) totalMarks / numberOfSubjects;
                char grade;
                
                if (averagePercentage >= 90) {
                    grade = 'A';
                } else if (averagePercentage >= 80) {
                    grade = 'B';
                } else if (averagePercentage >= 70) {
                    grade = 'C';
                } else if (averagePercentage >= 60) {
                    grade = 'D';
                } else {
                    grade = 'F';
                }
                
                resultArea.setText(String.format("Total Marks: %d\nAverage Percentage: %.2f%%\nGrade: %c",
                                                  totalMarks, averagePercentage, grade));
            } catch (NumberFormatException ex) {
                resultArea.setText("Error: Please enter valid numbers.");
            }
        }
    }
    
    public static void main(String[] args) {
        new Task_2_GUI();
    }
}
