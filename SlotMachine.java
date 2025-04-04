package view;

import model.Symbol;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SlotMachineView extends JFrame {
    private JLabel[] reelLabels;
    private JButton spinButton;
    private JTextField betField;
    private JLabel resultLabel;

    public SlotMachineView(int numReels) {
        setTitle("🎰 Tragamonedas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        reelLabels = new JLabel[numReels];
        for (int i = 0; i < numReels; i++) {
            reelLabels[i] = new JLabel("❔", SwingConstants.CENTER);
            reelLabels[i].setFont(new Font("SansSerif", Font.BOLD, 36));
            topPanel.add(reelLabels[i]);
        }

        JPanel centerPanel = new JPanel();
        betField = new JTextField(5);
        spinButton = new JButton("Girar 🎲");
        centerPanel.add(new JLabel("Apuesta: $"));
        centerPanel.add(betField);
        centerPanel.add(spinButton);

        resultLabel = new JLabel(" ");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);
    }

    public void updateReels(List<Symbol> symbols) {
        for (int i = 0; i < reelLabels.length; i++) {
            reelLabels[i].setText(symbols.get(i).toString());
        }
    }

    public void showWinnings(int amount) {
        if (amount > 0) {
            resultLabel.setText("¡Ganaste $" + amount + "!");
        } else {
            resultLabel.setText("¡Sigue intentando!");
        }
    }

    public String getBetAmount() {
        return betField.getText();
    }

    public JButton getSpinButton() {
        return spinButton;
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
