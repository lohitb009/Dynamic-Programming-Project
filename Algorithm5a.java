import java.util.Arrays;
import java.util.Scanner;

public class Algorithm5a {
    public int[][] plot;

    public int[][] memorizationBoolean;
    public int[][] memorizationLeft;
    public int[][] memorizationTopLeft;
    public int[][] memorizationTop;
    public int[][] memorizationFinal;

    public void memorizationTopDirections(int m, int n, int r, int c){

        // base-case
        if(r == m && c != n){
            return;
        } else if (r!=m && c == n) {
            this.memorizationTopDirections(m, n, r + 1, 1);
            return;
        }

        // logic-case
        if(this.memorizationBoolean[r][c] == 0){
            this.memorizationLeft[r][c] = 0;
            this.memorizationTopLeft[r][c] = 0;
            this.memorizationTop[r][c] = 0;
        }else{
            this.memorizationLeft[r][c] = 1+Math.min(Math.min(this.memorizationLeft[r-1][c-1],this.memorizationLeft[r-1][c]),Math.max(1,this.memorizationLeft[r][c-1]));
            this.memorizationTopLeft[r][c] = 1+Math.min(Math.min(this.memorizationTopLeft[r-1][c],this.memorizationTopLeft[r][c-1]),Math.max(1,this.memorizationTopLeft[r-1][c-1]));
            this.memorizationTop[r][c] = 1+Math.min(Math.min(this.memorizationTop[r-1][c-1],this.memorizationTop[r][c-1]),Math.max(1,this.memorizationTop[r-1][c]));
        }
        this.memorizationTopDirections(m,n,r,c+1);
        return;
    }

    public void memorizationFinalMatrix(int m, int n, int r, int c){
        // base-case
        if(r == m && c != n){
            return;
        } else if (r!=m && c == n) {
            this.memorizationFinalMatrix(m, n, r + 1, 1);
            return;
        }
        // logic-case
        this.memorizationFinal[r][c] = 1+Math.min(Math.min(this.memorizationLeft[r][c-1],this.memorizationTopLeft[r-1][c-1]),this.memorizationTop[r-1][c]);
        this.memorizationFinalMatrix(m, n, r, c+1);
        return;
    }
    public String getSolution(int m, int n, int h){

        // setup up a boolean matrix
        this.memorizationBoolean = new int[m][n];

        for(int r=0; r<m; r++){
            for(int c=0; c<n; c++){
                if(this.plot[r][c]>=h){
                    this.memorizationBoolean[r][c] = 1;
                }else{
                    this.memorizationBoolean[r][c] = 0;
                }
            }
        }

        // setup three dp matrix
        this.memorizationLeft = new int[m][n];
        this.memorizationTopLeft = new int[m][n];
        this.memorizationTop = new int[m][n];
        this.memorizationFinal = new int[m][n];

        // base-case; assign row 0th and col 0th values default
        // for column
        for(int r=0; r<m; r++){
            this.memorizationLeft[r][0] = this.memorizationBoolean[r][0];
            this.memorizationTopLeft[r][0] = this.memorizationBoolean[r][0];
            this.memorizationTop[r][0] = this.memorizationBoolean[r][0];
            this.memorizationFinal[r][0] = 1;
        }
        // for row
        for(int c=0; c<n; c++){
            this.memorizationLeft[0][c] = this.memorizationBoolean[0][c];
            this.memorizationTopLeft[0][c] = this.memorizationBoolean[0][c];
            this.memorizationTop[0][c] = this.memorizationBoolean[0][c];
            this.memorizationFinal[0][c] = 1;
        }

        // for memorizationFinal; assign row 2 and col 2 area of 2
        // for col
        for(int r=0; r<m; r++){
            this.memorizationFinal[r][1] = 2;
        }
        // for row
        for(int c=0; c<n; c++){
            this.memorizationFinal[1][c] = 2;
        }

        // perform memorization in left,TopLeft,Top
        this.memorizationTopDirections(m,n,1,1);

        // perform the memorization on final matrix
        this.memorizationFinalMatrix(m,n,2,2);

        // get the maximal possible plot
        int maxSize = -1;

        int global_x_u=0;
        int global_y_u=0;

        int global_x_b=0;
        int global_y_b=0;

        for(int r=0; r<m; r++){
            for(int c=0; c<n; c++){
                if(this.memorizationFinal[r][c] < maxSize){
                    continue;
                }
                maxSize = this.memorizationFinal[r][c];
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
        Algorithm5a obj = new Algorithm5a();

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
