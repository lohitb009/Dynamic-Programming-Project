import java.util.Arrays;
import java.util.Scanner;

public class Algorithm4 {

    public int[][] mat;
    public int[][] columns;
    public int[][] sqr;

    public void setColumnsMatrix(int m, int n, int h, int r, int c){

        // base-case
        if(r == m && c!=n){
            return;
        } else if (r!=m && c==n) {
            this.setColumnsMatrix(m,n,h,r+1,0);
            return;
        }
        // logic-case
        if(this.mat[r][c]>=h){
            if(r==0){
                this.columns[r][c] = 1;
            }else{
                this.columns[r][c] = this.columns[r-1][c]+1;
            }
        }
        this.setColumnsMatrix(m,n,h,r,c+1);
        return;
    }

    public void setSquareMatrix(int m, int n, int h, int r, int c){
        // base-case
        if(r == m && c!=n){
            return;
        } else if (r!=m && c==n) {
            this.setSquareMatrix(m,n,h,r+1,0);
            return;
        }
        // logic-case
        if(this.mat[r][c]>=h){
            if(r==0 || c==0){
                this.sqr[r][c] = 1;
            }else{
                int top = this.sqr[r-1][c];
                int left = this.sqr[r][c-1];
                int topLeft = this.sqr[r-1][c-1];
                this.sqr[r][c] = 1+Math.min(top,Math.min(left,topLeft));
            }
        }
        this.setSquareMatrix(m,n,h,r,c+1);
        return;
    }
    public String getSolution(int m, int n, int h, int mat[][]) {

        this.mat = mat;
        this.columns = new int[m][n];

        // set the columnsMatrix
        this.setColumnsMatrix(m,n,h,0,0);

        //set the square matrix
        this.sqr = new int[m][n];
        this.setSquareMatrix(m,n,h,0,0);

        // minimum possible square area is 2
        int maxLen = 2;
        int bottomRighti = 1;
        int bottomRightj = 1;


        for (int i = 2; i < m; i++) {
            for (int j = 2; j < n; j++) {

                // chk for left
                int lowerRow = 0;
                int lowerCol = j - 1;
                while (lowerCol >= 0 && this.mat[i][lowerCol] >= h) {
                    lowerRow++;
                    lowerCol--;
                }

                int sideLength = Math.min(this.columns[i - 1][j], lowerRow);
                int squareSize = this.sqr[i - 1][j - 1];

                int maxSide = 0;

                // fill-up the maximal possible sq size
                if (sideLength < squareSize) {
                    maxSide = sideLength + 2;
                } else {

                    int upperRow = 0;
                    int upperCol = j - 1;

                    int x_bound = i - 1 - squareSize;
                    int y_bound = j - 1 - squareSize;

                    while (x_bound >= 0 && upperCol >= 0 && this.mat[y_bound][upperCol] >= h) {
                        upperRow+=1;
                        upperCol-=1;
                    }

                    if (x_bound >= 0 && y_bound >= 0 && upperRow >= squareSize && this.columns[i - 1][y_bound] >= squareSize) {
                        maxSide = squareSize + 2;
                    } else {
                        maxSide = squareSize + 1;
                    }
                }

                if (maxSide > maxLen) {
                    maxLen = maxSide;
                    bottomRighti = i + 1;
                    bottomRightj = j + 1;
                }

            }
        }

        // return the coordinates
        StringBuilder rtrString = new StringBuilder();
        rtrString.append(Integer.toString(bottomRighti - maxLen + 1));
        rtrString.append(" ");

        rtrString.append(Integer.toString(bottomRightj - maxLen + 1));
        rtrString.append(" ");

        rtrString.append(Integer.toString(bottomRighti));
        rtrString.append(" ");

        rtrString.append(Integer.toString(bottomRightj));
        rtrString.append(" ");

        return rtrString.toString();
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

        // check the values
        System.out.println("\nm = " + m);
        System.out.println("n = " + n);
        System.out.println("h = " + h);
        System.out.println("plot = " + Arrays.deepToString(p));

        Algorithm4 obj = new Algorithm4();
        System.out.println("final-result = " + obj.getSolution(m,n,h,p));
    }
}
