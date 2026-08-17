import java.util.*;

public class FourQueens {

    static int N = 4;

    static int nodesBFS = 0;
    static int nodesDFS = 0;
    static int nodesAStar = 0;

    // Check whether queen can be placed safely
    static boolean isSafe(int[] state, int col) {

        int row = state.length;

        for (int i = 0; i < row; i++) {

            if (state[i] == col)
                return false;

            if (Math.abs(state[i] - col) == Math.abs(i - row))
                return false;
        }

        return true;
    }

    // Conflict value
    static int conflicts(int[] state) {

        int count = 0;

        for (int i = 0; i < state.length; i++) {

            for (int j = i + 1; j < state.length; j++) {

                if (state[i] == state[j])
                    count++;

                if (Math.abs(state[i] - state[j])
                        == Math.abs(i - j))
                    count++;
            }
        }

        return count;
    }

    // Print state
    static void printState(int[] state) {

        System.out.print("[ ");

        for (int x : state)
            System.out.print(x + " ");

        System.out.println("]");
    }

    // Print board
    static void printBoard(int[] state) {

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {

                if (i < state.length && state[i] == j)
                    System.out.print("Q ");

                else
                    System.out.print(". ");
            }

            System.out.println();
        }

        System.out.println();
    }


    // ================= BFS =================

    static int[] BFS() {

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[0]);

        System.out.println("\n========== BFS ==========");

        while (!queue.isEmpty()) {

            int[] current = queue.poll(); 

            nodesBFS++;

            System.out.println("\nState " + nodesBFS + " : ");
            printState(current);

            printBoard(current);

            if (current.length == N) {

                System.out.println("BFS Solution:");
                printBoard(current);

                return current;
            }

            for (int col = 0; col < N; col++) {

                if (isSafe(current, col)) {

                    int[] next =
                            Arrays.copyOf(
                                    current,
                                    current.length + 1
                            );

                    next[current.length] = col;

                    queue.add(next);
                }
            }
        }

        return null;
    }


    // ================= DFS =================

    static int[] DFS(int[] state) {

        nodesDFS++;

        System.out.println("\nState " + nodesDFS + " : ");
        printState(state);

        printBoard(state);

        if (state.length == N) {

            System.out.println("DFS Solution:");
            printBoard(state);

            return state;
        }

        for (int col = 0; col < N; col++) {

            if (isSafe(state, col)) {

                int[] next =
                        Arrays.copyOf(
                                state,
                                state.length + 1
                        );

                next[state.length] = col;

                int[] result = DFS(next);

                if (result != null)
                    return result;
            }
        }

        return null;
    }


    // ================= A* =================

    static class Node {

        int[] state;
        int g;
        int h;
        int f;

        Node(int[] state) {

            this.state = state;

            g = state.length;

            h = conflicts(state);

            f = g + h;
        }
    }


    static int[] AStar() {

        PriorityQueue<Node> pq =
                new PriorityQueue<>(
                        (a, b) -> a.f - b.f
                );

        pq.add(new Node(new int[0]));

        System.out.println("\n========== A* ==========");

        while (!pq.isEmpty()) {

            Node current = pq.poll();

            nodesAStar++;

            System.out.println("\nState " + nodesAStar + " : ");
            printState(current.state);

            printBoard(current.state);

            System.out.println(
                    "g = " + current.g +
                    "  h = " + current.h +
                    "  f = " + current.f
            );

            if (current.state.length == N
                    && current.h == 0) {

                System.out.println("\nA* Solution:");
                printBoard(current.state);

                return current.state;
            }

            for (int col = 0; col < N; col++) {

                if (isSafe(current.state, col)) {

                    int[] next =
                            Arrays.copyOf(
                                    current.state,
                                    current.state.length + 1
                            );

                    next[current.state.length] = col;

                    pq.add(new Node(next));
                }
            }
        }

        return null;
    }


    // ================= MAIN =================

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter N: ");
        N = sc.nextInt();


        // BFS

        long start = System.nanoTime();

        int[] bfsSolution = BFS();

        long end = System.nanoTime();

        long bfsTime = end - start;


        // DFS

        start = System.nanoTime();

        System.out.println("\n========== DFS ==========");

        int[] dfsSolution = DFS(new int[0]);

        end = System.nanoTime();

        long dfsTime = end - start;


        // A*

        start = System.nanoTime();

        int[] astarSolution = AStar();

        end = System.nanoTime();

        long astarTime = end - start;


        // Comparison

        System.out.println("\n========== COMPARISON ==========");

        System.out.println(
                "Algorithm\tStates\tTime(ns)"
        );

        System.out.println(
                "BFS\t\t" +
                nodesBFS +
                "\t" +
                bfsTime
        );

        System.out.println(
                "DFS\t\t" +
                nodesDFS +
                "\t" +
                dfsTime
        );

        System.out.println(
                "A*\t\t" +
                nodesAStar +
                "\t" +
                astarTime
        );
    }
}
