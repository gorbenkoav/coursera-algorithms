import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;


public class Solver {

    private Stack<Board> solution;
    private int moves;
    private boolean isSolvable;

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("Initial board must not be null");
        }

        MinPQ<BoardNode> mainQueue = new MinPQ<>();
        mainQueue.insert(new BoardNode(0, null, initial));

        MinPQ<BoardNode> twinQueue = new MinPQ<>();
        twinQueue.insert(new BoardNode(0, null, initial.twin()));

        BoardNode finalNode;
        while (!mainQueue.isEmpty()) {
            finalNode = solve(mainQueue);
            if (finalNode == null) {
                if (solve(twinQueue) != null) {
                    break;
                }
            } else {
                solution = new Stack<>();
                BoardNode item = finalNode;
                do {
                    solution.push(item.board());
                    moves++;
                    item = item.prevNode();
                }
                while (item != null);
                moves--;
                isSolvable = true;
                break;
            }

        }
    }

    private BoardNode solve(MinPQ<BoardNode> queue) {
        BoardNode minBoardNode = queue.delMin();
        if (minBoardNode.board().isGoal()) {
            return minBoardNode;
        } else {
            boolean isFirstMove = queue.isEmpty();
            for (Board neighbor : minBoardNode.board().neighbors()) {
                if (isFirstMove || !neighbor.equals(minBoardNode.prevNode().board())) {
                    queue.insert(new BoardNode(minBoardNode.moves() + 1, minBoardNode, neighbor));
                }
            }
        }
        return null;
    }


    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        return isSolvable() ? moves : -1;
    }

    public Iterable<Board> solution() {
        return solution;
    }

    private class BoardNode implements Comparable<BoardNode> {
        private final int moves;
        private final BoardNode prevNode;
        private final Board board;
        private final int priority;

        BoardNode(int moves, BoardNode prevNode, Board board) {
            this.moves = moves;
            this.prevNode = prevNode;
            this.board = board;
            priority = moves + board.manhattan();
        }

        int moves() {
            return moves;
        }

        BoardNode prevNode() {
            return prevNode;
        }

        Board board() {
            return board;
        }

        int priority() {
            return priority;
        }

        @Override
        public int compareTo(BoardNode other) {
            if (priority() == other.priority()) {
                return 0;
            }
            return priority() < other.priority() ? -1 : 1;
        }
    }


}