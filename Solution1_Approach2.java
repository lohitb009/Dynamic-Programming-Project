import java.util.Arrays;
import java.util.Scanner;

public class Solution1_Approach2 {

    public static int[] findLargestSquareArea(int[][] p, int h) {
        int m = p.length;
        int n = p[0].length;
        int[][] s = new int[m][n];

        // initialize s[i][j] to be the minimum number of trees that must be planted
        // in the square with top-left corner at (i,j)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                s[i][j] = p[i][j];
                if (i > 0) s[i][j] += s[i-1][j];
                if (j > 0) s[i][j] += s[i][j-1];
                if (i > 0 && j > 0) s[i][j] -= s[i-1][j-1];
            }
        }

        // iterate over all square sizes, starting with the largest
        int[] boundingIndices = null;
        for (int size = Math.min(m, n); size >= 1; size--) {
            // iterate over all possible square positions
            for (int i = 0; i <= m - size; i++) {
                for (int j = 0; j <= n - size; j++) {
                    // calculate the sum of the minimum number of trees that must be
                    // planted in the square
                    int sum = s[i+size-1][j+size-1];
                    if (i > 0) sum -= s[i-1][j+size-1];
                    if (j > 0) sum -= s[i+size-1][j-1];
                    if (i > 0 && j > 0) sum += s[i-1][j-1];

                    // check if the square satisfies the condition
                    if (sum >= h * size * size) {
                        boundingIndices = new int[]{i, j, i+size-1, j+size-1};
                        return boundingIndices;
                    }
                }
            }
        }

        return boundingIndices;
    }

    public static void main(String[] args) {

        // Input from user for line1
        Scanner sc = new Scanner(System.in);

        //String line1 = "5 5 2";
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

        int[][] p = new int[m][n];
        for(int r=0; r<m; r++){
            String[] lineMSplit = lineM[r].split("\\s");
            for(int c=0; c<n; c++){
                p[r][c] = Integer.parseInt(lineMSplit[c]);
            }
        }

//        int[][] p = {{1, 1, 0, 2, 2},
//                {1, 1, 1, 2, 3},
//                {1, 1, 1, 1, 1},
//                {0, 1, 1, 1, 1},
//                {0, 0, 0, 0, 0}};
//        int h = 2;

        // check the values
        System.out.println("\nm = " + m);
        System.out.println("n = " + n);
        System.out.println("h = " + h);
        System.out.println("plot = " + Arrays.deepToString(p));

        Solution1_Approach2 obj = new Solution1_Approach2();
        int[] boundingIndices = obj.findLargestSquareArea(p, h);

        if (boundingIndices == null) {
            System.out.println("\nNo square area satisfies the condition.");
        } else {
            System.out.println("\nBounding indices of largest square area:");
            // top-left corner
            int topLeft_x = boundingIndices[0]+1;
            int topLeft_y = boundingIndices[1]+1;

            // bottom-right corner
            int bottomRight_x = boundingIndices[2]+1;
            int bottomRight_y = boundingIndices[3]+1;

//            System.out.println("Top left: (" + boundingIndices[0] + ", " + boundingIndices[1] + ")");
//            System.out.println("Bottom right: (" + boundingIndices[2] + ", " + boundingIndices[3] + ")");

            StringBuilder rtrString = new StringBuilder();
            rtrString.append(Integer.toString(topLeft_x));
            rtrString.append(" ");

            rtrString.append(Integer.toString(topLeft_y));
            rtrString.append(" ");

            rtrString.append(Integer.toString(bottomRight_x));
            rtrString.append(" ");

            rtrString.append(Integer.toString(bottomRight_y));
            rtrString.append(" ");

            System.out.println("final-result = " + rtrString.toString());
        }
    }
}
