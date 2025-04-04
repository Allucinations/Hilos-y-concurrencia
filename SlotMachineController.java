package controller;

import model.*;
import view.SlotMachineView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SlotMachineController {
    private SlotMachine model;
    private SlotMachineView view;

    public SlotMachineController(SlotMachine model, SlotMachineView view) {
        this.model = model;
        this.view = view;

        view.getSpinButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSpin();
            }
        });
    }

    private void handleSpin() {
        try {
            int betAmount = Integer.parseInt(view.getBetAmount());
            if (betAmount <= 0) {
                view.showMessage("Introduce una apuesta válida.");
                return;
            }

            Bet bet = new Bet(betAmount);

            Thread gameThread = new Thread(() -> {
                model.spinReels();

                try {
                    Thread.sleep(1000); // Simular tiempo de giro
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                List<Symbol> result = model.getCurrentSymbols();
                int winnings = model.calculateWinnings(bet);

                SwingUtilities.invokeLater(() -> {
                    view.updateReels(result);
                    view.showWinnings(winnings);
                });
            });

            gameThread.start();

        } catch (NumberFormatException ex) {
            view.showMessage("Por favor introduce un número válido.");
        }
    }
}
