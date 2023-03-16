import java.util.Arrays;

public class Solution2_Approach2 {
    public int[][] plot;

    public int globalArea=-1;

    public int global_x_u=-1;
    public int global_y_u=-1;

    public int global_x_b=-1;
    public int global_y_b=-1;

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

        // initialize a boolean matrix & co-ordinates matrix
        boolean[][] boolMatrix = new boolean[m][n];
        // fill-up the boolean matrix
        for(int r=0; r<m; r++){
            for (int c=0; c<n; c++){
                if(this.plot[r][c]>=h){
                    boolMatrix[r][c] = true;
                }else{
                    boolMatrix[r][c] = false;
                }
            }
        }

        // initialize 2D memorization matrix
        String[][] memorization2D = new String[m][n];
        // fill-up the last row with (-1,-1) string
        // r=m-1 by default
        for(int c=0; c<n; c++){
            memorization2D[m-1][c] = "-1,-1";
        }

        // fill-up the last col with (-1,-1) string
        // c=n-1 by default
        for(int r=0; r<n; r++){
            memorization2D[r][n-1] = "-1,-1";
        }

        // perform memorization
        for(int r=m-2; r>-1; r--){
            for(int c=n-2; c>-1; c--){
                // chk right,bottom & bottom-right
                // all true, copy coordinates from the diagonal
                if(boolMatrix[r][c+1]==true && boolMatrix[r+1][c] == true && boolMatrix[r+1][c+1] == true){
                    memorization2D[r][c] = memorization2D[r+1][c+1]; //copy value from bottom-right
                    if(memorization2D[r][c] != "-1,-1"){
                        // chk for area
                        String[] belowCoordinates = memorization2D[r][c].split(",");
                        this.chkForArea(r,c,Integer.parseInt(belowCoordinates[0]),Integer.parseInt(belowCoordinates[1]));
                    }
                }
                // right & bottom are true, but diagonal is false
                else if (boolMatrix[r][c+1]==true && boolMatrix[r+1][c] == true && boolMatrix[r+1][c+1] == false) {
                    memorization2D[r][c] = Integer.toString(r+1)+","+Integer.toString(c+1); // just copy bottom-right coordinates
                    // area won't be checked here because we have sq with all 4 corners only, nothing else
                }
                // nothing satisfied conditionally
                else {
                    memorization2D[r][c] = "-1,-1";
                }
            }
        }

        System.out.println("\nbooleanMatrix = ");
        for(int r=0; r<m; r++){
            System.out.println(Arrays.toString(boolMatrix[r]));
        }
        System.out.println("\nmemorization2D = ");
        for(int r=0; r<m; r++){
            System.out.println(Arrays.toString(memorization2D[r]));
        }

        System.out.println("\nupper bound (x_u,y_u) = "+"("+global_x_u+","+global_y_u+")");
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
        Solution2_Approach2 obj = new Solution2_Approach2();

        // assumption right now
        String line1 = "6 6 10";

        // preprocessing step
        String[] line1Split = line1.split("\\s+");
        int m = Integer.parseInt(line1Split[0]);
        int n = Integer.parseInt(line1Split[1]);
        int h = Integer.parseInt(line1Split[2]);

        // Input lineM
        String[] lineM = new String[]{
                "4 1 4 6 3 9",
                "2 4 11 16 5 1",
                "2 12 41 14 5 3",
                "8 5 15 4 2 3",
                "3 5 4 1 3 5",
                "1 5 3 4 6 1"
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

        System.out.println("\nfinal-result = " + obj.getSolution(m,n,h));
    }
}
