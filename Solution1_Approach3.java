import java.util.Arrays;
import java.util.Scanner;

public class Solution1_Approach3 {

    public int[][] plot;

    public int globalArea=0;

    public int global_x_u=0;
    public int global_y_u=0;

    public int global_x_b=0;
    public int global_y_b=0;

    public void chkForArea(int x_u, int y_u, int x_b, int y_b){
        int localArea = (x_b-x_u)*(y_b-y_u);

        if(this.globalArea<localArea){
            this.globalArea = localArea;

            this.global_x_u = x_u;
            this.global_y_u = y_u;

            this.global_x_b = x_b;
            this.global_y_b = y_b;
        }
    }
    public String getSolution(int m, int n, int h){

        // initialize the 2DMemorization Matrix
        String[][] memorization2D = new String[m][n];

        // fill-up the last-row of 2DMemorization
        // r = m-1 by default
        for(int c=0; c<n; c++){
            if(this.plot[m-1][c]>=h){
                String coordinates = Integer.toString(m-1)+","+Integer.toString(c);
                memorization2D[m-1][c] = coordinates;
            }else{
                String coordinates = "-1,-1";
                memorization2D[m-1][c] = coordinates;
            }
        }

        // fill-up the last-col of 2DMemorization
        // c = n-1 by default
        for(int r=0; r<m; r++){
            if(this.plot[r][n-1]>=h){
                String coordinates = Integer.toString(r)+","+Integer.toString(n-1);
                memorization2D[r][n-1] = coordinates;
            }else{
                String coordinates = "-1,-1";
                memorization2D[r][n-1] = coordinates;
            }
        }

        // perform 2D Memorization
        for(int r=m-2; r>-1; r--){
            for(int c=n-2; c>-1; c--){
                if(this.plot[r][c] >= h){
                    // chk 3-directions
                    if(memorization2D[r][c+1] == "-1,-1" || memorization2D[r+1][c] == "-1,-1" || memorization2D[r+1][c+1] == "-1,-1"){
                        memorization2D[r][c] = "-1,-1";
                    }else{
                        memorization2D[r][c] = memorization2D[r+1][c+1];

                        // chk for area
                        String[] belowCoordinates = memorization2D[r][c].split(",");
                        this.chkForArea(r,c,Integer.parseInt(belowCoordinates[0]),Integer.parseInt(belowCoordinates[1]));
                    }
                }else{
                    memorization2D[r][c] = "-1,-1";
                }
            }
        }

        System.out.println("memorization2D = " + Arrays.deepToString(memorization2D));

        System.out.println("upper bound (x_u,y_u) = "+"("+global_x_u+","+global_y_u+")");
        System.out.println("lower bound (x_b,y_b) = "+"("+global_x_b+","+global_y_b+")");

        StringBuilder rtrString = new StringBuilder();
        rtrString.append(Integer.toString(this.global_x_u+1));
        rtrString.append(" ");

        rtrString.append(Integer.toString(this.global_y_u+1));
        rtrString.append(" ");

        rtrString.append(Integer.toString(this.global_x_b+1));
        rtrString.append(" ");

        rtrString.append(Integer.toString(this.global_y_b+1));
        rtrString.append(" ");

        return rtrString.toString();
    }

    public static void main(String[] args) {

        // Input from user for line1
        Scanner sc = new Scanner(System.in);

        // create an object of class Solution1_Approach1
        Solution1_Approach3 obj = new Solution1_Approach3();

        // assumption right now
        //String line1 = "4 4 8";
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
//                "6 10 9 12",
//                "8 8 8 12",
//                "1 10 10 10",
//                "9 10 9 9"
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

        System.out.println("final-result = " + obj.getSolution(m,n,h));
    }
}
