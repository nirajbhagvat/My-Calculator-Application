import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI {
    private JFrame frame;
    private JTextField display;
    private JPanel panel;
    private JButton[] buttons;
    private String[] buttonLabels = {
            "AC", "(", ")", "/",
            "7", "8", "9", "X",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "←", "0", ".", "="
    };

    private String currentInput = "";

    public void createAndShowGUI() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLocationRelativeTo(null);

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 26));
        display.setPreferredSize(new Dimension(300, 50));
        frame.add(display, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10)); // Grid layout with 5 rows and 4 columns

        buttons = new JButton[20];
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
            buttons[i].addActionListener(new ButtonClickListener());
            panel.add(buttons[i]);
        }

        frame.add(panel);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("AC")) {
                currentInput = "";
                display.setText("");
            } else if (command.equals("←")) {
                if (!currentInput.isEmpty()) {
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                    display.setText(currentInput);
                }
            } else if (Character.isDigit(command.charAt(0)) || command.equals(".")) {
                currentInput += command;
                display.setText(currentInput);
            } else if (command.equals("=")) {
                try {
                    // Replace 'X' with '*' for multiplication in the input
                    currentInput = currentInput.replace("X", "*");
                    double result = CalculatorOperations.calculate(currentInput);
                    display.setText(String.valueOf(result));
                    currentInput = String.valueOf(result);
                } catch (Exception ex) {
                    display.setText("Expression Error");
                    currentInput = "";
                    System.out.println("Exception: " + ex.getMessage());
                }
            } else {
                currentInput += command;
                display.setText(currentInput);
            }
        }
    }
}
