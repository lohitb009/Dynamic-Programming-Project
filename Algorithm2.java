import java.util.Arrays;
import java.util.Scanner;

public class Algorithm2 {

    public int[] checkBoundary(int[][] plotMat, int i, int j, int m, int n, int h) {
        int[] result = new int[3];
        int count = 1;
        int[] leftCorner = {i, j};
        int[] rightCorner = {i, j};
        boolean flag = true;
        while(count+i < m && count+j < n) {
            int x = i+count;
            int y = j+count;
            for (int row = i; row <= x; row++) {
                if (plotMat[row][y] < h) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                for (int col = j; col <= y; col++) {
                    if (plotMat[x][col] < h) {
                        flag = false;
                        break;
                    }
                }
            }
            if (!flag) {
                break;
            } else {
                rightCorner[0] = i+count;
                rightCorner[1] = j+count;
            }
            count++;
        }
        result[0] = leftCorner[0];
        result[1] = leftCorner[1];
        result[2] = count;
        return result;
    }

    public int[] getMaxPossibleBoundary(int m, int n, int h, int[][] plotMat) {
        int currentMax = 0;
        int[] currentLeftCorner = {0, 0};
        int[] currentRightCorner = {0, 0};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (plotMat[i][j] >= h) {
                    int[] result = checkBoundary(plotMat, i, j, m, n, h);
                    int count = result[2];
                    if (count > currentMax) {
                        currentMax = count;
                        currentLeftCorner[0] = result[0];
                        currentLeftCorner[1] = result[1];
                        currentRightCorner[0] = result[0] + count - 1;
                        currentRightCorner[1] = result[1] + count - 1;
                    }
                }
            }
        }
        int[] result = {currentMax, currentLeftCorner[0], currentLeftCorner[1], currentRightCorner[0], currentRightCorner[1]};
        return result;
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

        int[][] plotMat = new int[m][n];
        for(int r=0; r<m; r++){
            String[] lineMSplit = lineM[r].split("\\s");
            for(int c=0; c<n; c++){
                plotMat[r][c] = Integer.parseInt(lineMSplit[c]);
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
        System.out.println("plot = " + Arrays.deepToString(plotMat));

        Algorithm2 obj = new Algorithm2();
        int[] result = obj.getMaxPossibleBoundary(m, n, h, plotMat);

//        System.out.println("Total is " + result[0]);
//        System.out.println("left corner : (" + result[1] + ", " + result[2] + ") right corner : (" + result[3] + ", " + result[4] + ")");

        StringBuilder rtrString = new StringBuilder();
        rtrString.append(Integer.toString(result[1]+1));
        rtrString.append(" ");

        rtrString.append(Integer.toString(result[2]+1));
        rtrString.append(" ");

        rtrString.append(Integer.toString(result[3]+1));
        rtrString.append(" ");

        rtrString.append(Integer.toString(result[4]+1));
        rtrString.append(" ");

        System.out.println("final-result = " + rtrString.toString());
    }
}
