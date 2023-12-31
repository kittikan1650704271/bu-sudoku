package gui.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Finds a unique solution for the grid.
 *
 * @author masahiro
 * @version 1.0
 */
public class Solution {

    // Solution Attributes
    private Grid grid;
    private int result;
    private int loop;

    /**
     * Constructs a raw grid for solving.
     *
     * @param grid the Sudoku grid to solve for
     * @return the primordial grid
     */
    public Solution solveFor(Grid grid) {
        this.grid = grid;
        this.result = 0;
        this.setLoop(0);
        return this;
    }

    /**
     * Finds a unique solution for this grid using recursion to validate each
     * value.
     *
     * @param emptyCells the empty grid
     * @param numEmpty absolute amount of empty cells
     * @return the true solution
     */
    public int findSolution(List<Cell> emptyCells, int numEmpty) {
        //System.out.println("this is the empty Cells : " + emptyCells.size());
        if (getLoop() < emptyCells.size()) {
            for (int digit : shuffleValues()) {
                if (grid.meetsConstraints(emptyCells.get(getLoop()), digit)) {
                    emptyCells.get(getLoop()).setUserValue(digit);
                    setLoop(getLoop() + 1);
                    if (findSolution(emptyCells, numEmpty) >= numEmpty) {

//                        System.out.println("this is result from solution if true " + result);
                        return result;
                    }
                }
            }
            emptyCells.get(getLoop()).setUserValue(0);
            setLoop(getLoop() - 1);
//            System.out.println("this is result from solution if true empty " + result);
            return result;
        } else {
            setLoop(getLoop() - 1);
//            System.out.println("this is result from solution if else " + result);
            return ++result;
        }

    }

    /**
     * Shuffles the list of possible values.
     *
     * @return a shuffled list of possible values (1-9)
     */
    public List<Integer> shuffleValues() {
        List<Integer> possibleValues = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.shuffle(possibleValues);
        //System.out.println("this is possibleValues : "+possibleValues);
        return possibleValues;
    }

    /**
     * @return the loop counter
     */
    private int getLoop() {
        return loop;
    }

    /**
     * @param loop the loop to set to
     */
    private void setLoop(int loop) {
        this.loop = loop;
    }
}
