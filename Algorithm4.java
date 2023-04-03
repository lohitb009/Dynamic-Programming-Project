import java.util.Arrays;
import java.util.Scanner;

public class Algorithm4 {
    public static int[] largestSquareArea(int[][] p, int h) {
        int m = p.length;
        int n = p[0].length;
        int[][] dp = new int[m][n];
        int maxArea = 0;
        int maxX = 0;
        int maxY = 0;

        // Compute dp[i][j] for each plot (i, j) in the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (p[i][j] < h) {
                    dp[i][j] = 0;
                } else {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                    }
                }

                // Update maxArea and (maxX, maxY) if dp[i][j] is larger
                if (dp[i][j] > maxArea) {
                    maxArea = dp[i][j];
                    maxX = i;
                    maxY = j;
                }
            }
        }

        // Compute the bounding indices of the largest square area
        int minX = maxX - maxArea + 1;
        int minY = maxY - maxArea + 1;

        return new int[]{minX, minY, maxX, maxY};
    }

    public static void main(String[] args) {

        // Input from user for line1
        Scanner sc = new Scanner(System.in);

        //String line1 = "6 6 10";
        System.out.println("Enter m<space>n<space>h:");
        String line1 = sc.nextLine();

        // preprocessing step
        String[] line1Split = line1.split("\\s+");
        int m = Integer.parseInt(line1Split[0]);
        int n = Integer.parseInt(line1Split[1]);
        int h = Integer.parseInt(line1Split[2]);

        // Input lineM
        System.out.println("Enter 'm' pair's i.e. (n1<space>n2<space>n3..n):");
        String[] lineM = new String[m];
        for(int i=0; i<m; i++){
            lineM[i] = sc.nextLine();
        }

        // Input lineM
//        String[] lineM = new String[]{
//                "4 1 4 6 3 9",
//                "2 4 11 16 5 1",
//                "2 12 41 14 5 3",
//                "8 5 15 4 2 3",
//                "3 5 4 1 3 5",
//                "1 5 3 4 6 1"
//        };

        int[][] p = new int[m][n];
        for(int r=0; r<m; r++){
            String[] lineMSplit = lineM[r].split("\\s");
            for(int c=0; c<n; c++){
                p[r][c] = Integer.parseInt(lineMSplit[c]);
            }
        }

//        int[][] p = {{2, 3, 1, 2}, {1, 2, 2, 1}, {4, 1, 3, 2}};
//        int h = 2;

        // check the values
        System.out.println("\nm = " + m);
        System.out.println("n = " + n);
        System.out.println("h = " + h);
        System.out.println("plot = " + Arrays.deepToString(p));

        int[] result = largestSquareArea(p, h);
        System.out.println("\nBounding indices of largest square area: (" + result[0] + ", " + result[1] + "), (" + result[2] + ", " + result[3] + ")");
    }
}
