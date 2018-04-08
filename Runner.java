import logic.MatrixUpdater;
import model.MatrixOfCells;
import model.State;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Runner {
    private boolean shouldContinue = true;
    public static final int SIZE_OF_BOARD = 10;
    private MatrixOfCells matrixOfCells;

    public Runner() {
        matrixOfCells = new MatrixOfCells(SIZE_OF_BOARD, 50);
    }

    public void run() {
        showCells();
        while (shouldContinue) {
            int answer = JOptionPane.showConfirmDialog(null , "Do you want to see the next generation?");
            switch (answer) {
                case 0:
                    calculateNextGeneration(matrixOfCells);
                    showCells();
                    break;
                case 2:
                case 1:
                    shouldContinue = false;
                    break;

                default:
                    break;
            }
        }
    }


    public void showCells() {
        try {
            MatrixUpdater matrixUpdater = new MatrixUpdater(matrixOfCells, SIZE_OF_BOARD);
            if (!SwingUtilities.isEventDispatchThread()) {
                SwingUtilities.invokeAndWait(matrixUpdater);
                return;
            }
        } catch (InvocationTargetException | InterruptedException ex) {
            System.out.println("Errrr... something went wrong");
        }
    }

    private void calculateNextGeneration(MatrixOfCells matrixOfCells) {
        MatrixOfCells newMatrix = createNextGeneration(matrixOfCells);
        matrixOfCells.setMatrix(newMatrix.getMatrix());
    }

    private MatrixOfCells createNextGeneration(MatrixOfCells matrixOfCells) {
        MatrixOfCells newMatrix = new MatrixOfCells(SIZE_OF_BOARD, 0);
        for (int i = 0 ; i < SIZE_OF_BOARD ; i++) {
            for (int j = 0; j < SIZE_OF_BOARD; j++) {
                int numberOfAliveNeighbours = matrixOfCells.getNumberOfAliveNeighbours(i, j);
                setCell(newMatrix, i, j, matrixOfCells.getCell(i,j).getState(),numberOfAliveNeighbours);
            }
        }
        return newMatrix;
    }

    private void setCell(MatrixOfCells newMatrixOfCells, int x, int y, State currentState, int numberOfAliveNeighbours) {
        if (currentState.equals(State.DEAD)) {
            switch (numberOfAliveNeighbours) {
                case 3:
                    newMatrixOfCells.getCell(x, y).setState(State.ALIVE);
                    break;
                    default:
                        newMatrixOfCells.getCell(x, y).setState(State.DEAD);
                        break;
            }
        } else if (currentState.equals(State.ALIVE)) {
            switch (numberOfAliveNeighbours) {
                case 0:
                case 1:
                case 4:
                    newMatrixOfCells.getCell(x, y).setState(State.DEAD);
                    break;
                case 2:
                case 3:
                    newMatrixOfCells.getCell(x, y).setState(State.ALIVE);
                    break;

                    default:
                        newMatrixOfCells.getCell(x, y).setState(State.DEAD);
                        break;
            }
        }
    }



}
