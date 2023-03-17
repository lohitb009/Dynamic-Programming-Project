import java.util.Arrays;

public class Solution3_Approach1 {

    public int[][] plot;

    public int globalArea=0;

    public int global_x_u=0;
    public int global_y_u=0;

    public int global_x_b=0;
    public int global_y_b=0;

    public int[] helper(int x_th, int y_th, int x_c, int y_c, int m, int n, int h, int k){
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
            } else if (this.plot[x_temp][y_c] < h && k>=0) {
                x_temp -= 1;
                k-=1;
                continue;
            } else {
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
            } else if (this.plot[x_c][y_temp] < h && k>=0) {
                y_temp -= 1;
                k-=1;
                continue;
            } else {
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
        result = this.helper(x_th,y_th,x_c+1,y_c+1,m,n,h,k);
        if(result[0]>x_c && result[1]>y_c){
            return result;
        }else {
            result[0] = x_c;
            result[1] = y_c;
            return result;
        }
    }

    public String getSolution(int m, int n, int h, int k){

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
                    int [] below = this.helper(r,c,r+1,c+1,m,n,h,k);
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

        // create an object of class Solution1_Approach1
        Solution3_Approach1 obj = new Solution3_Approach1();

        // assumption right now
        String line1 = "6 6 10 2";

        // preprocessing step
        String[] line1Split = line1.split("\\s+");
        int m = Integer.parseInt(line1Split[0]);
        int n = Integer.parseInt(line1Split[1]);
        int h = Integer.parseInt(line1Split[2]);
        int k = Integer.parseInt(line1Split[3]);

        // Input lineM
        String[] lineM = new String[]{
                "13 14 13 6 4 1",
                "14 6 14 1 4 7",
                "11 1 12 5 7 2",
                "4 1 6 7 3 1",
                "4 3 6 4 2 1",
                "1 2 3 4 5 6"
        };

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

        System.out.println("final-result = " + obj.getSolution(m,n,h,k));
    }
}
