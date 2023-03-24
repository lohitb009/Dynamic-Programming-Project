import java.util.Arrays;
import java.util.Scanner;

public class Solution1_Approach1 {
    public int[][] plot;

    public int globalArea=0;

    public int global_x_u=0;
    public int global_y_u=0;

    public int global_x_b=0;
    public int global_y_b=0;

    public int[] helper(int x_th, int y_th, int x_c, int y_c, int m, int n, int h){
        int [] result = new int[2];
        // base-case
        if(x_c == m || y_c == n){
            result[0] = -1;
            result[1] = -1;
            return result;
        }

        // logic-case

        // chk for top
        boolean top = true;
        int x_temp = x_c;
        while(x_temp >= x_th){
            if(this.plot[x_temp][y_c] >= h){
                x_temp -= 1;
                continue;
            }else {
                top = false;
                break;
            }
        }

        // base-chk for top
        if(top == false){
            result[0] = -1;
            result[1] = -1;
            return result;
        }

        // chk for left
        boolean left = true;
        int y_temp = y_c;
        while(y_temp >= y_th){
            if(this.plot[x_c][y_temp] >= h){
                y_temp -= 1;
                continue;
            }else {
                left = false;
                break;
            }
        }

        // base-chk for left
        if(left == false){
            result[0] = -1;
            result[1] = -1;
            return result;
        }

        // recursion
        result = this.helper(x_th,y_th,x_c+1,y_c+1,m,n,h);
        if(result[0]>x_c && result[1]>y_c){
            return result;
        }else {
            result[0] = x_c;
            result[1] = y_c;
            return result;
        }
    }

    public String getSolution(int m, int n, int h){

        for(int r=0; r<m; r++){
            for(int c=0; c<n; c++){
                if(this.plot[r][c] >= h){

                    // set upper bound
                    int x_u = r;
                    int y_u = c;

                    // set lower bound
                    int x_b = r;
                    int y_b = c;

                    // call the helper function
                    int [] below = this.helper(r,c,r+1,c+1,m,n,h);
                    if(below[0]>r && below[1]>c){
                        x_b = below[0];
                        y_b = below[1];
                    }

                    // calculate area
                    int localArea = (x_b-x_u)*(y_b-y_u);

                    // if new area is greater, update the upper and lower bounds respectively
                    if(this.globalArea<localArea){
                        this.globalArea = localArea;

                        this.global_x_u = x_u;
                        this.global_y_u = y_u;

                        this.global_x_b = x_b;
                        this.global_y_b = y_b;
                    }
                }
            }
        }

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
        Solution1_Approach1 obj = new Solution1_Approach1();

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
