import java.util.*;

public class AlphaBetaPruning {

    static ArrayList<Integer>[] graph;
    static int[] utility;
    static boolean[] terminal;

    // ALPHA-BETA-SEARCH
    static int alphaBetaSearch(int state) {

        return maxValue(
                state,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE
        );
    }


    // MAX-VALUE
    static int maxValue(int state, int alpha, int beta) {

        // Terminal test
        if (terminal[state]) {
            return utility[state];
        }

        int v = Integer.MIN_VALUE;

        // For each action / child
        for (int child : graph[state]) {

            v = Math.max(
                    v,
                    minValue(child, alpha, beta)
            );

            // Beta cutoff
            if (v >= beta) {
                return v;
            }

            // Update alpha
            alpha = Math.max(alpha, v);
        }

        return v;
    }


    // MIN-VALUE
    static int minValue(int state, int alpha, int beta) {

        // Terminal test
        if (terminal[state]) {
            return utility[state];
        }

        int v = Integer.MAX_VALUE;

        // For each action / child
        for (int child : graph[state]) {

            v = Math.min(
                    v,
                    maxValue(child, alpha, beta)
            );

            // Alpha cutoff
            if (v <= alpha) {
                return v;
            }

            // Update beta
            beta = Math.min(beta, v);
        }

        return v;
    }


    // MAIN
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Number of nodes
        int n = sc.nextInt();

        graph = new ArrayList[n + 1];
        utility = new int[n + 1];
        terminal = new boolean[n + 1];

        // Initialize graph
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Read connections
        System.out.println("Enter the connections (-1 -1 to end):");

        while (true) {

            int u = sc.nextInt();
            int v = sc.nextInt();

            if (u == -1 && v == -1) {
                break;
            }

            graph[u].add(v);
        }

        // Read utility values for terminal nodes
        System.out.println("Enter utility values:");

        for (int i = 1; i <= n; i++) {

            if (graph[i].isEmpty()) {

                terminal[i] = true;

                utility[i] = sc.nextInt();
            }
        }

        // Start Alpha-Beta Search from root
        int result = alphaBetaSearch(1);

        System.out.println("MAX Value: " + result);

        sc.close();
    }
}