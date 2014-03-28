package others;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 找出最大的连通区域
 * 
 */
public class FindLargestConnectedCell {

    /**
     * 单元格
     */
    static class Cell {

        public int i;

        public int j;

        public Cell(int i, int j) {
            super();
            this.i = i;
            this.j = j;
        }

        public String toString() {
            return String.format("(%d, %d)", i, j);
        }
    }

    /**
     * 连通区域
     */
    static class ConnectedCells {

        public Set<Cell> cells = new HashSet<Cell>();

        public ConnectedCells(Cell c) {
            cells.add(c);
        }

        public void combine(ConnectedCells cc) {
            for (Cell c : cc.cells) {
                cells.add(c);
            }
        }

        public boolean canUpdate(Cell c) {
            boolean can = false;
            for (Cell cell : cells) {
                if (cellAdjacent(cell, c)) {
                    can = true;
                    break;
                }
            }

            return can;
        }

        private boolean cellAdjacent(Cell c1, Cell c2) {
            if (((c1.i == c2.i) && Math.abs(c1.j - c2.j) == 1)
                    || ((c1.j == c2.j) && Math.abs(c1.i - c2.i) == 1)) {
                return true;
            }
            return false;
        }

        public String toString() {
            StringBuffer sb = new StringBuffer(cells.size() + " [");
            String sep = "";
            for (Cell cell : cells) {
                sb.append(sep + cell.toString());
                sep = ",";
            }
            sb.append("]");
            return sb.toString();
        }
    }

    /* 所有候选的连通区域 */
    private List<ConnectedCells> candidates = new ArrayList<ConnectedCells>();

    public void search(int[][] matrix) {
        // iterate over all cells
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    updateCandidate(i, j);
                }
            }
        }

        // find the largest connected section
        int maxSize = -1;
        ConnectedCells maxCC = null;
        for (ConnectedCells cc : candidates) {
            if (cc.cells.size() > maxSize) {
                maxCC = cc;
                maxSize = cc.cells.size();
            }
        }
        System.out.println(maxCC);
    }

    private void updateCandidate(int i, int j) {
        Cell cell = new Cell(i, j);
        List<ConnectedCells> updatedCells = new ArrayList<ConnectedCells>();
        for (ConnectedCells cc : candidates) {
            if (cc.canUpdate(cell)) {
                updatedCells.add(cc);
            }
        }

        if (updatedCells.size() == 0) {
            candidates.add(new ConnectedCells(cell));
        } else {
            // combine the connectedCells
            ConnectedCells cc = new ConnectedCells(cell);
            for (ConnectedCells cells : updatedCells) {
                cc.combine(cells);
                candidates.remove(cells);
            }
            candidates.add(cc);
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[4][4];
        matrix[0] = new int[] { 0, 1, 0, 0 };
        matrix[1] = new int[] { 1, 1, 1, 1 };
        matrix[2] = new int[] { 0, 0, 0, 1 };
        matrix[3] = new int[] { 0, 1, 1, 0 };

        new FindLargestConnectedCell().search(matrix);

    }

}
