# <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5kpq2ohgTkC8RIfOQ7QAEhjoDgDPaVUkT6A&usqp=CAU" width="70" height="60" /> Dynamic-Programming-Project <br>

### COT5405 Analysis of Algorithm (AOA)

### Team Members Contribution
#### <i> Lohit Bhambri (lohit.bhambri@ufl.edu) </i>
1. Implemented Algorithm 1
2. Implemented Algorithm 3
3. Implemented Algorithm 5
4. Implemented Algorithm 6
5. Report Compilation
6. Analysis of Algorithms (equal contributor)

#### <i> Sharath Bhushan Podila (spodila@ufl.edu) </i>
1. Implemented Algorithm 2
2. Implemented Algorithm 4
3. Implemented MakeFile mechanism and execution in Remote CISE Machines
4. Experimental Comparative Strategy mechanism result generation
5. Analysis of Algorithms (equal contributor)

### Problem 1:
****
#### <i>Algorithm 1:</i>
```aidl
The given code represents a method named getSolution that takes three integer parameters m, n, and h. The method aims to
find the largest rectangular area that can be formed with the minimum height h and the plot data represented in a 2D 
array called plot.

The method first uses two nested for loops to traverse the plot array row-wise and column-wise. For each plot cell that 
has a height greater than or equal to h, it sets the upper bound and lower bound of a rectangle with the current plot 
cell as the top-left corner.

Next, it calls a helper function with the current plot cell's position and the plot array's dimensions. The helper 
function returns the position of the cell with a height less than h that is immediately below and to the right of the 
current plot cell. The lower bound of the rectangle is updated to the position of this cell if it is farther from the 
current plot cell than the current lower bound.

The method calculates the area of the rectangle bounded by the upper and lower bounds and updates the global maximum 
area and its corresponding upper and lower bounds if the local area is greater than the global area.

Finally, the method returns a string representation of the global maximum area's upper and lower bounds. It also prints 
the same information to the console.
```

<u><i>Time Complexity:</i></u><br>
The time complexity of algorithm is O(m<sup>3</sup> n<sup>3</sup>).<br>
We are visiting every cell and at every cell we are checking the possibility of better square by going top and left <br>
of the bottom corner respectively conditionally.

#### <i>Algorithm 2:</i>
```aidl

```
<u><i>Time Complexity:</i></u>


#### <i>Algorithm 3:</i>
```aidl
The problem involves finding the largest square area in a given 2D grid where all the elements of the square are greater
than or equal to a given height 'h'. The solution involves using 2D memorization to compute the solution efficiently.

Here's the algorithmic explanation of the provided pseudocode:
Define a method named "getSolution" that takes in three integer parameters 'm', 'n', and 'h' representing the dimensions
of the 2D grid and the minimum height required for the square area.

Initialize a 2D string array 'memorization2D' of size 'm' x 'n'. This array will be used to store the solution of the 
subproblems that are computed using 2D memorization.

Fill up the last row of the 'memorization2D' array by iterating through each column 'c' of the last row. If the value of
the corresponding element in the original 2D grid is greater than or equal to 'h', set the value of 
'memorization2D[m-1][c]' to the string representation of the row and column indices of the element in the 
format 'row,column'. Otherwise, set the value of 'memorization2D[m-1][c]' to "-1,-1".

Fill up the last column of the 'memorization2D' array by iterating through each row 'r' of the last column. 
If the value of the corresponding element in the original 2D grid is greater than or equal to 'h', set the value of 
'memorization2D[r][n-1]' to the string representation of the row and column indices of the element in the format 
'row,column'. Otherwise, set the value of 'memorization2D[r][n-1]' to "-1,-1".

Compute the solution of the subproblems using 2D memorization. Iterate through each row 'r' of the 'memorization2D' 
array from the second last row to the first row, and within each row, iterate through each column 'c' from the second 
last column to the first column. If the value of the corresponding element in the original 2D grid is greater than or 
equal to 'h', compute the solution of the subproblem for the current position by checking the three adjacent positions 
to the right, bottom, and bottom-right. If any of these adjacent positions has a value of "-1,-1", set the current 
position's value to "-1,-1", indicating that it's not possible to form a square of size 'h' at this position. 
Otherwise, set the current position's value to the same value as the bottom-right adjacent position's value, indicating 
that a square of size 'h' can be formed at this position. Also, check if the current position and the bottom-right 
adjacent position form a larger square by checking the values of the 'global_x_u', 'global_y_u', 'global_x_b', and 
'global_y_b' variables. If they do, update the values of these variables.

Finally, return the string representation of the 'global_x_u', 'global_y_u', 'global_x_b', and 'global_y_b' variables 
as the solution to the problem.
```

<u><i>Time Complexity:</i></u>

The time complexity for this algorithm is O(mn).<br>
Here we are performing bottom-up dynamic programming and using 2D memorization.

<u><i> Analysis of Algorithm:</i></u><br>

memorization2D(r,c) = bottom-right corner/coordinate value<br>
goal = (global_x_u,global_y_u) and (global_x_b,global_y_b) <br>

<i>Bellman-Equation:</i>
```aidl
memorization2D[r][c] will be 

a. (r,c):
        if (r = m-1 && 0<=c<n and plot[r][c]>= h) OR (c = n-1 && 0<=r<m and plot[r][c]< h)
b. -1,-1:
        if(r = m-1 && 0<=c<n and plot[r][c]< h) OR (c = n-1 && 0<=r<m and plot[r][c]< h) OR
        (memorization2D[r+1][c] OR memorization2D[r+1][c+1] OR memorization2D[r][c+1] == -1,-1)
c. memorization2D[r][c]:
         (memorization2D[r+1][c] AND memorization2D[r+1][c+1] AND memorization2D[r][c+1] != -1,-1)
```


### Problem 2:
****
#### <i>Algorithm 4:</i>
```aidl

```

<u><i>Time Complexity:</i></u><br>



#### <i>Algorithm 5:(A & B)</i>
```aidl
Define a method named "getSolution" that takes in three integer parameters "m", "n" and "h" and returns a string.
Create a boolean matrix named "boolMatrix" of size "m x n" and a string matrix named "memorization2D" of size "m x n".

Use a nested for loop to iterate through each row and column of the "plot" array and fill the corresponding element of 
"boolMatrix" with a value of "true" if the value of the plot is greater than or equal to "h", and "false" otherwise.

Use another nested for loop to initialize the last row of the "memorization2D" array with "-1,-1" strings.

Use another nested for loop to initialize the last column of the "memorization2D" array with "-1,-1" strings.

Use two nested for loops starting from the second last row and column of "memorization2D" and going backwards.

Check if the element to the right, bottom and bottom-right of the current element in "boolMatrix" are all "true".

If they are all "true", copy the value of the element in "memorization2D" at the bottom-right to the current element, 
and check if the value is not "-1,-1".

If the value is not "-1,-1", call the "chkForArea" method with the current row, column, and the row and column values 
in the copied element.

If the elements to the right and bottom of the current element are "true" but the element to the bottom-right is "false"
, set the current element in "memorization2D" to the coordinates of the bottom-right element.

If none of the above conditions are satisfied, set the current element in "memorization2D" to "-1,-1".

Use two nested for loops to iterate through the "boolMatrix" and "memorization2D" arrays and print their values.

Use a StringBuilder to append the values of "global_x_u", "global_y_u", "global_x_b" and "global_y_b" plus one to a 
string in the required format.

Return the string.
```

<u><i>Time Complexity:</i></u>

The time complexity for this algorithm is O(mn).<br>
Here we are performing bottom-up dynamic programming and using 2D memorization.

<u><i> Analysis of Algorithm:</i></u><br>

memorization2D(r,c) = bottom-right corner/coordinate value<br>
goal = (global_x_u,global_y_u) and (global_x_b,global_y_b) <br>

<i>Bellman-Equation:</i>
```aidl
memorization2D[r][c] will be 

a. -1,-1:
        if(r = m-1 && 0<=c<n) OR (c = n-1 && 0<=r<m)  OR
        (boolean[r+1][c] OR boolean[r][c+1] == false)
b. memorization2D[r][c]:
         (boolean[r+1][c] AND boolean[r+1][c+1] AND boolean[r][c+1] == true)
b. r+1,c+1:
         (boolean[r+1][c] == true AND boolean[r+1][c+1]== false AND boolean[r][c+1] == true)

```

### Problem 3:
****
#### <i>Algorithm 6:</i>
```aidl
Define a function named getSolution with four integer parameters m, n, h, and k.

Iterate through each element in the two-dimensional array plot using two nested loops for rows and columns.

Check if the current element at plot[r][c] is greater than or equal to the threshold value h.

If true, set the upper and lower bounds for the rectangle as the current element's coordinates.
Call the helper function with parameters r, c, r+1, c+1, m, n, h, and k.
If the returned array from helper has a larger x and y coordinate than the current upper bounds, update the upper bounds
to the new values.
Calculate the local area by taking the difference between the x and y coordinates of the upper and lower bounds, 
respectively, and multiplying them together.

If the local area is greater than the global area, update the global area and the global upper and lower bounds.
Print out the coordinates of the global upper and lower bounds.

Create a StringBuilder object to construct the string to return.

Append the x and y coordinates of the global upper bounds, followed by the x and y coordinates of the global lower 
bounds, each separated by a space.

Return the string representation of the StringBuilder object.
```
<u><i>Time Complexity:</i></u><br>
The time complexity of algorithm is O(m<sup>3</sup> n<sup>3</sup>).<br>
We are visiting every cell and at every cell we are checking the possibility of better square by going top and left <br>
of the bottom corner respectively conditionally and taking care of allowed breeches (k).

#### <i>Algorithm 7:</i>
```aidl

```
<u><i>Time Complexity:</i></u>


<u><i> Analysis of Algorithm:</i></u><br>

memorization2D(r,c) = bottom-right corner/coordinate value<br>
goal = (global_x_u,global_y_u) and (global_x_b,global_y_b) <br>

<i>Bellman-Equation:</i>
```aidl
```
