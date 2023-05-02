import java.util.Arrays;

public class Algorithm6a {

    int global_x_u = -1;
    int global_y_u = -1;

    int global_x_b = -1;
    int global_y_b = -1;

    int global_max_area = -1;

    public void helper(int m, int n, int h, int k, int [][] plot){

        // Step 1: initialize a plot validation matrix
        // for valid -- 0
        // for invalid -- 1
        int [][] validationPlot = new int[m][n];
        for(int r=0; r<m; r++){
            for(int c=0; c<n; c++){
                if(plot[r][c] <= h){
                    validationPlot[r][c] = 1;
                }else{
                    validationPlot[r][c] = 0;
                }
            }
        }
        System.out.println("validation-plot =:\t"+ Arrays.deepToString(validationPlot));

        // Step 2: perform prefixSum on the rows of the validationPlot
        // rowPrefix sum validation
        /*
        This will provide me with the number of invalid plots in the row rectangle
        starting from (r,0) to (r,j) where j<n
         */
        /*
        prefixSum[r][c] = prefixSum[r][c-1]+validationPlot[r][c]
         */
        int[][] prefixSum = validationPlot.clone();
        for(int r=0; r<m; r++){
            for(int c=0; c<n; c++){
                if(c==0){
                    continue;
                }else{
                    prefixSum[r][c] = prefixSum[r][c-1]+validationPlot[r][c];
                }
            }
        }
        System.out.println("prefix-sum-rows =:\t"+ Arrays.deepToString(prefixSum));

        // Step3: perform prefixSum on the cols of the prefixSum done for the rows
        // area prefixSum validation
        /*
        This will provide me with the number of invalid plot's in the rectangle starting
        from topCorner of (0,0) and bottomCorner of (r,c)
         */
        /*
        prefixSum[r][c] = prefixSum[r-1][c]+prefixSum[r][c]
         */
        for(int r=1; r<m; r++){
            for(int c=0; c<n; c++){
                if(c==0){
                    continue;
                }else{
                    prefixSum[r][c] += prefixSum[r-1][c];
                }
            }
        }
        System.out.println("prefix-sum-cols-rectangle=:\t"+ Arrays.deepToString(prefixSum));

        // now find the largest possible area for the square
        // you need to check the value of k i.e. k should not breech threshold

        for(int r=1; r<m; r++){
            for(int c=1; c<n; c++){

                // my bottom corners are r and c
                int currentPrefixSumValue = prefixSum[r][c];
                boolean flag = true;

                int top_x = 0;
                int top_y = 0;

                // set top corners value's
                if(r>c){
                    top_x = r-c;
                    top_y = 0;
                    if(top_x != 0){
                        currentPrefixSumValue -= prefixSum[top_x-1][c];
                    }
                }else{
                    top_x = 0;
                    top_y = c-r;
                    if(top_y != 0){
                        currentPrefixSumValue -= prefixSum[r][top_y-1];
                    }
                }

                while(flag){
                    // have valid plots
                    if(currentPrefixSumValue <= k){
                        int localArea = (r-top_x+1)*(c-top_y+1);
                        if(localArea>=this.global_max_area){
                            this.global_max_area = localArea;

                            // upper corner
                            this.global_x_u = top_x;
                            this.global_y_u = top_y;

                            // bottom corner
                            this.global_x_b = r;
                            this.global_y_b = c;
                        }
                        // this is my largest possible area with (r,c) as my bottom, no need to check further as size will reduce
                        flag = false;
                        continue;
                    }

                    // break the loop condition
                    if(top_x+1 == r && top_y+1 == c){
                        flag = false;
                    }else{

                        /*System.out.println("\ntop_x:\t"+top_x);
                        System.out.println("top_y:\t"+top_y);
                        System.out.println("r:\t"+r);
                        System.out.println("c:\t"+c);*/

                        // for left area
                        int oldArea = 0;
                        if(top_y != 0){
                            oldArea = prefixSum[r][top_y-1];
                        }
                        int newArea = prefixSum[r][top_y];
                        int topDownStripArea = newArea-oldArea;

                        // for top area for "gap"
                        int topLftRtStrip = prefixSum[top_x][c];
                        int topRtStipGap = newArea-topLftRtStrip;

                        currentPrefixSumValue -= (topDownStripArea+topRtStipGap);
                        top_x += 1;
                        top_y += 1;
                    }
                    continue;
                }
            }
        }

        System.out.println("top-corners:=\t"+"("+this.global_x_u+","+this.global_y_u+")");
        System.out.println("bottom-corners:=\t"+"("+this.global_x_b+","+this.global_y_b+")");
    }

    public static void main(String[] args) {
        Algorithm6a obj = new Algorithm6a();

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

        int[][] plot = new int[m][n];
        for(int r=0; r<m; r++){
            String[] lineMSplit = lineM[r].split("\\s");
            for(int c=0; c<n; c++){
                plot[r][c] = Integer.parseInt(lineMSplit[c]);
            }
        }

        obj.helper(m,n,h,k,plot);
    }
}
