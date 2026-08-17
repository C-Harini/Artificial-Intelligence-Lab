import java.util.*;

public class MapColoring {

    static int[][] graph;
    static int[] color;
    static String[] colors;
    static int n, m;

    
    static boolean isSafe(int state, int c) {

        for (int i = 0; i < n; i++) {

            
            if (graph[state][i] == 1 && color[i] == c) {
                return false;
            }
        }

        return true;
    }

    
    static boolean solve(int state) {

       
        if (state == n) {
            return true;
        }

        
        for (int c = 0; c < m; c++) {

           
            if (isSafe(state, c)) {

             
                color[state] = c;

                
                if (solve(state + 1)) {
                    return true;
                }

       
                color[state] = -1;
            }
        }

      
        return false;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

       
        System.out.print("Enter the number of states: ");
        n = sc.nextInt();

        
        System.out.print("Enter the number of colors: ");
        m = sc.nextInt();

      
        colors = new String[m];

        System.out.print("Enter the colors: ");

        for (int i = 0; i < m; i++) {
            colors[i] = sc.next();
        }

        
        graph = new int[n][n];

        System.out.println("Enter the connections (-1 for end):");

        while (true) {

            int a = sc.nextInt();

            if (a == -1) {
                break;
            }

            int b = sc.nextInt();

            
            a--;
            b--;

         
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        
        color = new int[n];

        
        Arrays.fill(color, -1);

      
        boolean result = solve(0);


        if (result) {

            System.out.println("Output:");

            for (int i = 0; i < n; i++) {
                System.out.println((i + 1) + " - " + colors[color[i]]);
            }

        } else {
            System.out.println("No solution exists.");
        }

        sc.close();
    }
}