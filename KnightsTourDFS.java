import java.util.Scanner;

public class KnightsTourDFS {

    static int N;
    static boolean[][] visited;
    static boolean[][] blocked;

    // Knight moves
    static int[] row = {-2,-2,-1,-1,1,1,2,2};
    static int[] col = {-1,1,-2,2,-2,2,-1,1};

    static void dfs(int x, int y) {

        visited[x][y] = true;
        System.out.println("(" + x + "," + y + ")");

        for (int i = 0; i < 8; i++) {
            int nx = x + row[i];
            int ny = y + col[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < N &&
                !visited[nx][ny] &&
                !blocked[nx][ny]) {

                dfs(nx, ny);
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter board size (N): ");
        N = sc.nextInt();

        visited = new boolean[N][N];
        blocked = new boolean[N][N];

        // Block the center cell (only if N is odd)
        if (N % 2 == 1) {
            blocked[N / 2][N / 2] = true;
        }

        System.out.println("\nDFS Traversal:");
        dfs(0, 0);

        sc.close();
    }
}   