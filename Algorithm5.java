import java.util.Arrays;
import java.util.Scanner;

public class Algorithm5 {
    public int[][] plot;

//    public int globalArea=-1;
//
//    public int global_x_u=-1;
//    public int global_y_u=-1;
//
//    public int global_x_b=-1;
//    public int global_y_b=-1;

//    public void chkForArea(int x_u, int y_u, int x_b, int y_b){
//        int localArea = (x_b-x_u)*(y_b-y_u);
//
//        if(this.globalArea<localArea){
//            this.globalArea = localArea;
//
//            this.global_x_u = x_u;
//            this.global_y_u = y_u;
//
//            this.global_x_b = x_b;
//            this.global_y_b = y_b;
//        }
//    }

    public String getSolution(int m, int n, int h){

        // setup up a boolean matrix
        int[][] memorizationBoolean = new int[m][n];

        for(int r=0; r<m; r++){
            for(int c=0; c<n; c++){
                if(this.plot[r][c]>=h){
                    memorizationBoolean[r][c] = 1;
                }else{
                    memorizationBoolean[r][c] = 0;
                }
            }
        }

        // setup three dp matrix
        int[][] memorizationLeft = new int[m][n];
        int[][] memorizationTopLeft = new int[m][n];
        int[][] memorizationTop = new int[m][n];
        int[][] memorizationFinal = new int[m][n];

        // base-case; assign row 0th and col 0th values default
        // for column
        for(int r=0; r<m; r++){
            memorizationLeft[r][0] = memorizationBoolean[r][0];
            memorizationTopLeft[r][0] = memorizationBoolean[r][0];
            memorizationTop[r][0] = memorizationBoolean[r][0];
            memorizationFinal[r][0] = 1;
        }
        // for row
        for(int c=0; c<n; c++){
            memorizationLeft[0][c] = memorizationBoolean[0][c];
            memorizationTopLeft[0][c] = memorizationBoolean[0][c];
            memorizationTop[0][c] = memorizationBoolean[0][c];
            memorizationFinal[0][c] = 1;
        }

        // for memorizationFinal; assign row 2 and col 2 area of 2
        // for col
        for(int r=0; r<m; r++){
            memorizationFinal[r][1] = 2;
        }
        // for row
        for(int c=0; c<n; c++){
            memorizationFinal[1][c] = 2;
        }

        // perform memorization in left,TopLeft,Top
        for(int r=1; r<m; r++){
            for(int c=1; c<n; c++){
                if(memorizationBoolean[r][c] == 0){
                    memorizationLeft[r][c] = 0;
                    memorizationTopLeft[r][c] = 0;
                    memorizationTop[r][c] = 0;
                }else{
                    memorizationLeft[r][c] = 1+Math.min(Math.min(memorizationLeft[r-1][c-1],memorizationLeft[r-1][c]),Math.max(1,memorizationLeft[r][c-1]));
                    memorizationTopLeft[r][c] = 1+Math.min(Math.min(memorizationTopLeft[r-1][c],memorizationTopLeft[r][c-1]),Math.max(1,memorizationTopLeft[r-1][c-1]));
                    memorizationTop[r][c] = 1+Math.min(Math.min(memorizationTop[r-1][c-1],memorizationTop[r][c-1]),Math.max(1,memorizationTop[r-1][c]));
                }
            }
        }

        // perform the memorization on final matrix
        for(int r=2; r<m; r++){
            for(int c=2; c<n; c++){
                // Final[i][j] = 1+min(LB[i][j-1], LT[i-1][j-1], RT[i-1][j])
                memorizationFinal[r][c] = 1+Math.min(Math.min(memorizationLeft[r][c-1],memorizationTopLeft[r-1][c-1]),memorizationTop[r-1][c]);
            }
        }

        // get the maximal possible plot
        int maxSize = -1;

        int global_x_u=0;
        int global_y_u=0;

        int global_x_b=0;
        int global_y_b=0;

        for(int r=0; r<m; r++){
            for(int c=0; c<n; c++){
                if(memorizationFinal[r][c] < maxSize){
                    continue;
                }
                maxSize = memorizationFinal[r][c];
                global_x_u = r-maxSize+1;
                global_y_u = c-maxSize+1;

                global_x_b=r;
                global_y_b=c;
            }
        }
        System.out.println("\nupper bound (x_u,y_u) = "+"("+global_x_u+","+global_y_u+")");
        System.out.println("lower bound (x_b,y_b) = "+"("+global_x_b+","+global_y_b+")");

        StringBuilder rtrString = new StringBuilder();
        rtrString.append(Integer.toString(global_x_u+1));
        rtrString.append(" ");

        rtrString.append(Integer.toString(global_y_u+1));
        rtrString.append(" ");

        rtrString.append(Integer.toString(global_x_b+1));
        rtrString.append(" ");

        rtrString.append(Integer.toString(global_y_b+1));
        rtrString.append(" ");

        return rtrString.toString();
    }

    public static void main(String[] args) {

        // Input from user for line1
        Scanner sc = new Scanner(System.in);

        // create an object of class Algorithm1
        Algorithm5 obj = new Algorithm5();

        // assumption right now
//        String line1 = "6 6 10";
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

//        String[] lineM = new String[]{
//                "4 1 4 6 3 19",
//                "2 4 1 1 5 11",
//                "2 1 4 4 5 13",
//                "8 5 1 4 12 13",
//                "3 5 4 11 13 15",
//                "1 5 3 4 16 1"
//        };


        obj.plot = new int[m][n];
        for(int r=0; r<m; r++){
            String[] lineMSplit = lineM[r].split("\\s");
            for(int c=0; c<n; c++){
                obj.plot[r][c] = Integer.parseInt(lineMSplit[c]);
            }
        }

        // check the values
        System.out.println("m = " + m);
        System.out.println("n = " + n);
        System.out.println("h = " + h);
        System.out.println("plot = " + Arrays.deepToString(obj.plot));

        System.out.println("\nfinal-result = " + obj.getSolution(m,n,h));
    }
}
