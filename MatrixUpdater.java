package logic;

import model.MatrixOfCells;
import model.State;

import javax.swing.*;
import java.awt.*;

public class MatrixUpdater implements Runnable {

    private MatrixOfCells matrixOfCells;
    private int size;
    private JFrame frame;

    public MatrixUpdater(MatrixOfCells matrixOfCells, int size) {
        this.matrixOfCells = matrixOfCells;
        this.size = size;
    }

    @Override
    public void run() {
        frame = new JFrame();
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(size, size));
        for (int i=0 ; i < size ; i++) {
            for (int j=0 ;j < size ; j++) {
                JLabel jLabel = new JLabel();
                jLabel.setPreferredSize(new Dimension(50,50));
                jLabel.setOpaque(true);
                if (matrixOfCells.getCell(i, j).getState().equals(State.DEAD)) {
                    jLabel.setBackground(new Color(0x99501C));
                    jLabel.setText("Dead");
                } else {
                    jLabel.setBackground(new Color(0x2C992D));
                    jLabel.setText("Alive");
                }
                jLabel.setVisible(true);
                jPanel.add(jLabel);
            }
        }
        jPanel.setVisible(true);
        frame.add(jPanel);
        frame.setVisible(true);
    }
}
