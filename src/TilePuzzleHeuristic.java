import java.util.Hashtable;

public class TilePuzzleHeuristic implements IHeuristic {

    @Override
    public double getHeuristic(IProblemState problemState) {
        TilePuzzleState puzzleState = (TilePuzzleState) problemState;
        if (!ASearch.puzzlesHeuristics.containsKey(puzzleState)) {
            int[][] statePuzzle = puzzleState._tilePuzzle;
            int puzzleSize = statePuzzle.length;

            double count = 0;
            double expected = 0;

            for (int row = 0; row < statePuzzle.length; row++) {
                for (int col = 0; col < statePuzzle[row].length; col++) {
                    int value = statePuzzle[row][col];
                    expected++;
                    if (value != 0 && value != expected) {
                        count += value *
                                // Manhattan distance
                                (Math.abs(row - getRow(value, puzzleSize))
                                +
                                Math.abs(col - getCol(value, puzzleSize)));
                    }
                }
            }
            ASearch.puzzlesHeuristics.put(puzzleState, count);
        }
        return ASearch.puzzlesHeuristics.get(puzzleState);
    }

    private int getCol(int value, int N) {
        return (value - 1) % N;
    }

    private int getRow(int value, int N) {
        return (value - 1) / N;
    }
}


