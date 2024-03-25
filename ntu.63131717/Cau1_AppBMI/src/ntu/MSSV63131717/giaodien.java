package ntu.MSSV63131717;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class giaodien extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    giaodien frame = new giaodien();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public giaodien() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 477);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.RED);
        panel.setBounds(0, 0, 434, 61);
        contentPane.add(panel);

        JLabel lblNewLabel = new JLabel("Tinh chi so BMI");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBackground(Color.WHITE);
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 84, 434, 239);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Height:");
        lblNewLabel_1.setBounds(94, 24, 46, 14);
        panel_1.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Weight:");
        lblNewLabel_2.setBounds(94, 74, 46, 14);
        panel_1.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("BMI:");
        lblNewLabel_3.setBounds(94, 126, 46, 14);
        panel_1.add(lblNewLabel_3);

        textField = new JTextField();
        textField.setBounds(186, 21, 147, 20);
        panel_1.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(186, 71, 147, 20);
        panel_1.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(186, 123, 147, 20);
        panel_1.add(textField_2);

        JButton btnNewButton = new JButton("Exit");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnNewButton.setBounds(335, 186, 89, 23);
        panel_1.add(btnNewButton);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
            }
        });
        btnClear.setBounds(236, 186, 89, 23);
        panel_1.add(btnClear);

        JButton btnCalculate = new JButton("Calculate");
        btnCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	double height = Double.parseDouble(textField.getText());
                    double weight = Double.parseDouble(textField_1.getText());
                    double bmi = weight / (height * height);
                    textField_2.setText(String.format("%.2f", bmi)); // Display BMI

                    String message = "";
                    if (bmi < 18.5) {
                        message = ("Underweight");
                    } else if (bmi < 25) {
                    	message =("Normal weight");
                    } else if (bmi < 30) {
                    	message =("Overweight");
                    } else {
                    	message =("Obesity");
                    }
                    // Show the message dialog
                    JLabel label = new JLabel(message);
                    label.setFont(new Font("Arial", Font.PLAIN, 16));
                    JOptionPane.showMessageDialog(null, label, "BMI Status", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numeric values for height and weight.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCalculate.setBounds(137, 186, 89, 23);
        panel_1.add(btnCalculate);
    }
}
