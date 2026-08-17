import java.util.*;

public class BFS_8_puzzle {

    static class State {
        int[][] board;
        State parent;
        String move;

        State(int[][] board, State parent, String move) {
            this.board = board;
            this.parent = parent;
            this.move = move;
        }
    }

    static String goal = "123456780";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[][] initial = new int[3][3];

        System.out.println("Enter Initial State (0 for blank):");

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                initial[i][j] = sc.nextInt();

        if (!isSolvable(initial)) {
            System.out.println("Goal Not Reachable (Unsolvable Puzzle)");
            return;
        }

        Queue<State> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();

        State start = new State(copy(initial), null, "START");
        queue.offer(start);
        visited.add(convertString(initial));

        while (!queue.isEmpty()) {

            State current = queue.poll();

            if (convertString(current.board).equals(goal)) {
                System.out.println("\nGoal Reached!\n");

                Stack<State> path = new Stack<>();

                while (current != null) {
                    path.push(current);
                    current = current.parent;
                }

                while (!path.isEmpty()) {
                    State s = path.pop();
                    System.out.println("Move : " + s.move);
                    printBoard(s.board);
                }
                return;
            }

            int[] pos = findZero(current.board);
            int r = pos[0];
            int c = pos[1];

            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};
            String[] moves = {"UP", "DOWN", "LEFT", "RIGHT"};

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < 3 && nc >= 0 && nc < 3) {

                    int[][] next = copy(current.board);

                    next[r][c] = next[nr][nc];
                    next[nr][nc] = 0;

                    String key = convertString(next);

                    if (!visited.contains(key)) {
                        visited.add(key);
                        queue.offer(new State(next, current, moves[i]));
                    }
                }
            }
        }

        System.out.println("Goal Not Reachable");
    }

    static String convertString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board)
            for (int x : row)
                sb.append(x);
        return sb.toString();
    }

    static int[][] copy(int[][] board) {
        int[][] temp = new int[3][3];
        for (int i = 0; i < 3; i++)
            System.arraycopy(board[i], 0, temp[i], 0, 3);
        return temp;
    }

    static int[] findZero(int[][] board) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == 0)
                    return new int[]{i, j};
        return null;
    }

    static void printBoard(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    static boolean isSolvable(int[][] board) {
        int[] arr = new int[9];
        int k = 0;
        for (int[] row : board)
            for (int x : row)
                arr[k++] = x;

        int inv = 0;
        for (int i = 0; i < 9; i++)
            for (int j = i + 1; j < 9; j++)
                if (arr[i] != 0 && arr[j] != 0 && arr[i] > arr[j])
                    inv++;

        return inv % 2 == 0;
    }
}
