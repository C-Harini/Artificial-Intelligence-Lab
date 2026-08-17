import java.util.Scanner;

public class VacuumCleaner {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = sc.nextInt();

        int[][] room = new int[n][n];

        System.out.println("Enter tile values (0-Clean, 1-Dirty):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                room[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter initial row (0-" + (n - 1) + "): ");
        int row = sc.nextInt();

        System.out.print("Enter initial column (0-" + (n - 1) + "): ");
        int col = sc.nextInt();

        while (true) {

            // Check whether all rooms are clean
            boolean allClean = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (room[i][j] == 1) {
                        allClean = false;
                        break;
                    }
                }
                if (!allClean)
                    break;
            }

            if (allClean)
                break;

            // Suck dirt
            if (room[row][col] == 1) {
                System.out.println("Suck at (" + row + "," + col + ")");
                room[row][col] = 0;
                continue;
            }

            // Check Left
            if (col > 0 && room[row][col - 1] == 1) {
                col--;
                System.out.println("Move Left");
                continue;
            }

            // Check Right
            if (col < n - 1 && room[row][col + 1] == 1) {
                col++;
                System.out.println("Move Right");
                continue;
            }

            // Check Up
            if (row > 0 && room[row - 1][col] == 1) {
                row--;
                System.out.println("Move Up");
                continue;
            }

            // Check Down
            if (row < n - 1 && room[row + 1][col] == 1) {
                row++;
                System.out.println("Move Down");
                continue;
            }

            // Default movement (Snake Pattern)
            if (row % 2 == 0) { // Even row: move right
                if (col < n - 1) {
                    col++;
                    System.out.println("Move Right");
                } else if (row < n - 1) {
                    row++;
                    System.out.println("Move Down");
                } else {
                    row = 0;
                    col = 0;
                    System.out.println("Return to Start");
                }
            } else { // Odd row: move left
                if (col > 0) {
                    col--;
                    System.out.println("Move Left");
                } else if (row < n - 1) {
                    row++;
                    System.out.println("Move Down");
                } else {
                    row = 0;
                    col = 0;
                    System.out.println("Return to Start");
                }
            }
        }

        System.out.println("All rooms are clean.");

        sc.close();
    }
}