package model;

import java.util.*;

public class MatrixOfCells {



    private List<List<Cell>> matrix = Collections.emptyList();
    private int size;

    public MatrixOfCells(int size, int numberOfAliveCells) {
        this.size = size;
        matrix = new ArrayList<>();
        for (int i=0 ; i< size; i++) {
            List<Cell> cellList = new ArrayList<>();
            for (int j=0; j<size; j++) {
                cellList.add(new Cell(State.DEAD));
            }
            matrix.add(i, cellList);
        }
        Random random = new Random();
        for (int i=0; i< numberOfAliveCells; i++) {
            Cell tempCell = getCell(random.nextInt(10), random.nextInt(10));
            if (tempCell != null) {
                tempCell.setState(State.ALIVE);
            }
        }
    }

    public Cell getCell(int x,int y) {
        if (x <= -1 || x >= size ) {
            return null;
        }
        else if (y <= -1 || size <= y ) {
            return null;
        }
        return matrix.get(x).get(y);
    }

    public int getNumberOfAliveNeighbours(int x, int y) {
        int aliveCounter = 0;
        for (int i = -1 ; i < 2; i=i+2){
            Cell tempCell = getCell(x + i, y);
            aliveCounter = getAliveCounter(aliveCounter, tempCell);
            tempCell = getCell(x, y + i);
            aliveCounter = getAliveCounter(aliveCounter, tempCell);
            tempCell = getCell(x + i , y + i);
            aliveCounter = getAliveCounter(aliveCounter, tempCell);
            tempCell = getCell(x - i , y + i);
            aliveCounter = getAliveCounter(aliveCounter, tempCell);
        }
        return aliveCounter;
    }

    private int getAliveCounter(int aliveCounter, Cell tempCell) {
        if (tempCell != null) {
            if (tempCell.getState().equals(State.ALIVE)) {
                aliveCounter++;
            }
        }
        return aliveCounter;
    }



    public List<List<Cell>> getMatrix() {
        return matrix;
    }

    public void setMatrix(List<List<Cell>> matrix) {
        this.matrix = matrix;
    }
}
