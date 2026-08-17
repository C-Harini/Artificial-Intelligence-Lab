import java.util.*;

public class LocalBeamSearch {

    static int n;
    static int k;

    // Calculate conflicts in a state
    static int conflicts(int[] state) {

        int count = 0;

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                // Same column
                if (state[i] == state[j]) {
                    count++;
                }

                // Same diagonal
                if (Math.abs(state[i] - state[j]) == Math.abs(i - j)) {
                    count++;
                }
            }
        }

        return count;
    }

    // Generate successors of one state
    static ArrayList<int[]> generateSuccessors(int[] state) {

        ArrayList<int[]> successors = new ArrayList<>();

        for (int row = 0; row < n; row++) {

            for (int col = 1; col <= n; col++) {

                if (state[row] != col) {

                    int[] next = Arrays.copyOf(state, n);

                    next[row] = col;

                    successors.add(next);
                }
            }
        }

        return successors;
    }

    // Convert state into String
    static String convertString(int[] state) {

        String s = "";

        for (int x : state) {
            s += x + " ";
        }

        return s;
    }

    // Print one state
    static void printState(int[] state) {

        for (int x : state) {
            System.out.print(x + " ");
        }

        System.out.println();
    }

    // Check whether solution is reached
    static boolean isGoal(int[] state) {

        return conflicts(state) == 0;
    }

    // Local Beam Search
    static void localBeamSearch(ArrayList<int[]> beam) {

        int level = 1;

        while (true) {

            ArrayList<int[]> successors = new ArrayList<>();

            // Generate successors from all beam states
            for (int[] state : beam) {

                ArrayList<int[]> temp =
                        generateSuccessors(state);

                successors.addAll(temp);
            }

            // Remove duplicate states
            HashSet<String> visited = new HashSet<>();
            ArrayList<int[]> unique = new ArrayList<>();

            for (int[] state : successors) {

                String key = convertString(state);

                if (!visited.contains(key)) {

                    visited.add(key);
                    unique.add(state);
                }
            }

            // Sort according to conflict value
            unique.sort((a, b) ->
                    conflicts(a) - conflicts(b)
            );

            // Select best k states
            ArrayList<int[]> newBeam = new ArrayList<>();

            for (int i = 0;
                 i < unique.size() && i < k;
                 i++) {

                newBeam.add(unique.get(i));
            }

            // Print current level
            System.out.println(
                    "\nLevel " + level +
                    " successor:"
            );

            for (int[] state : newBeam) {

                System.out.print(
                        "(conflicts : " +
                        conflicts(state) +
                        ") "
                );

                printState(state);
            }

            // Check for solution
            for (int[] state : newBeam) {

                if (isGoal(state)) {

                    System.out.println(
                            "\nSolution found:"
                    );

                    printState(state);

                    return;
                }
            }

            // Move to next beam
            beam = newBeam;

            level++;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input
        System.out.print("Enter the matrix size: ");
        n = sc.nextInt();

        System.out.print("Enter the beam size: ");
        k = sc.nextInt();

        ArrayList<int[]> beam = new ArrayList<>();

        // Read initial states
        for (int i = 0; i < k; i++) {

            int[] state = new int[n];

            System.out.print(
                    "Enter the initial state " +
                    (i + 1) + ": "
            );

            for (int j = 0; j < n; j++) {
                state[j] = sc.nextInt();
            }

            beam.add(state);
        }

        // Start Local Beam Search
        System.out.println("\nThe solution is:");

        localBeamSearch(beam);
    }
} 

// ## Algorithm – Local Beam Search

// **Step 1:** Start the program and read the matrix size `N` and beam size `K`.

// **Step 2:** Read the `K` initial states and store them in the beam.

// **Step 3:** Calculate the conflict value of each state.

// **Step 4:** Generate all possible successor states from the current beam states by moving each queen to another column.

// **Step 5:** Calculate the conflict value for every generated successor state.

// **Step 6:** Remove duplicate successor states and sort them according to their conflict values.

// **Step 7:** Select the best `K` states with the lowest conflict values and make them the new beam.

// **Step 8:** Check whether any selected state has a conflict value of `0`.

// **Step 9:** If the conflict value is not `0`, repeat Steps 4–8 using the new beam.

// **Step 10:** If a state with conflict value `0` is found, display the sequence of successor states and the final solution, then stop.
