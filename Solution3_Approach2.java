import java.util.Arrays;

public class Solution3_Approach2 {
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
    public String getSolution(int m, int n, int h, int k){

        // initialize a boolean matrix
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

        // initialize a coordinates dp matrix of size m,n & k
        String [][][] memorization3D = new String[m][n][k+1];

        // fill-up the row
        // r = 0
        for(int c=0; c<n; c++){
            String coordinates = "0"+","+Integer.toString(c);
            if(boolMatrix[0][c] == true){
                memorization3D[0][c][0] = coordinates;
            }else{
                memorization3D[0][c][1] = coordinates;
            }
        }

        // fill-up the col
        // c = 0
        for(int r=0; r<n; r++){
            String coordinates = Integer.toString(r)+","+"0";
            if(boolMatrix[r][0] == true){
                memorization3D[r][0][0] = coordinates;
            }else{
                memorization3D[r][0][1] = coordinates;
            }
        }

        // perform memorization3D
        for(int r=1; r<m; r++){
            for(int c=1; c<n; c++){
                int kCurr = 0;

                if(boolMatrix[r][c] == false){
                    kCurr+=1;
                }

                // for top
                int kTop = Integer.MAX_VALUE;
                for(int i=0; i<k+1; i++){
                    if(memorization3D[r-1][c][i]!=null){
                        kTop = i;
                        break;
                    }
                }
                // for left
                int kLeft = Integer.MAX_VALUE;
                for(int i=0; i<k+1; i++){
                    if(memorization3D[r][c-1][i]!=null){
                        kLeft = i;
                        break;
                    }
                }
                // for top-left
                int kTopLeft = Integer.MAX_VALUE;
                for(int i=0; i<k+1; i++){
                    if(memorization3D[r-1][c-1][i]!=null){
                        kTopLeft = i;
                        break;
                    }
                }

                // calculate distance threshold
                int dThreshold = Math.min(r,c);
                if(dThreshold == 1){
                    if(boolMatrix[r-1][c] == false){
                        //top
                        kCurr+=1;
                    }
                    if(boolMatrix[r][c-1] == false){
                        //left
                        kCurr+=1;
                    }
                    if(boolMatrix[r-1][c-1] == false){
                        //top-left
                        kCurr+=1;
                    }
                } else if (kTop == Integer.MAX_VALUE || kLeft == Integer.MAX_VALUE || kTopLeft == Integer.MAX_VALUE) {
                    // no valid response found
                    continue;
                } else{
                    // if diagonal is invalid --- aka false
                    if(boolMatrix[r-1][c-1] == false){
                        kCurr += kLeft+kTop-kTopLeft;
                    }else{
                        // if diagonal is valid --- aka true
                        kCurr += kLeft+kTop;
                    }
                }
                // memorization 3D
                if(kCurr<=k){
                    // copy diagonal coordinates
                    String[] upCoordinates = memorization3D[r-1][c-1][kTopLeft].split(",");
                    memorization3D[r][c][kCurr] = upCoordinates[0].toString()+","+upCoordinates[1].toString();
                    //chk for area
                    this.chkForArea(Integer.parseInt(upCoordinates[0]),Integer.parseInt(upCoordinates[1]),r,c);
                }
            }
        }

        // print memorization3D matrix
        System.out.println("\nmemorization3D =:\t");
        for(int r=0; r<m; r++){
            System.out.println(Arrays.deepToString(memorization3D[r]));
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
        Solution3_Approach2 obj = new Solution3_Approach2();

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
