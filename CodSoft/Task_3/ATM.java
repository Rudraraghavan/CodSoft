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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void start() {
        JFrame frame = new JFrame("ATM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout(10, 10));

        JTextArea display = new JTextArea();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 14));
        display.setBackground(new Color(245, 245, 245));
        display.setMargin(new Insets(10, 10, 10, 10));
        frame.add(display, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(panel, BorderLayout.SOUTH);

        JButton checkBalanceButton = createButton("Check Balance");
        JButton depositButton = createButton("Deposit");
        JButton withdrawButton = createButton("Withdraw");
        JButton quitButton = createButton("Quit");

        panel.add(checkBalanceButton);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(quitButton);

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.setText("Current balance: $" + account.getBalance());
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog(frame, "Enter the amount to deposit:");
                try {
                    double amount = Double.parseDouble(amountStr);
                    if (account.deposit(amount)) {
                        display.setText("Successfully deposited $" + amount);
                    } else {
                        display.setText("Deposit amount must be positive.");
                    }
                } catch (NumberFormatException ex) {
                    display.setText("Invalid input. Please enter a valid amount.");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog(frame, "Enter the amount to withdraw:");
                try {
                    double amount = Double.parseDouble(amountStr);
                    if (amount > 0 && account.withdraw(amount)) {
                        display.setText("Successfully withdrew $" + amount);
                    } else {
                        display.setText("Insufficient balance or invalid amount.");
                    }
                } catch (NumberFormatException ex) {
                    display.setText("Invalid input. Please enter a valid amount.");
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Thank you for using the ATM. Goodbye!");
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00); // Initial balance
        ATM atm = new ATM(account);
        atm.start();
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            System.out.println("Initial balance must be non-negative.");
            this.balance = 0;
        }
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
