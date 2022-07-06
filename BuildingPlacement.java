import java.util.LinkedList;
import java.util.Queue;

// "static void main" must be defined in a public class.
public class Main {

    // Time Complexity : 0(HW Permutation n) where H is the height of the lot, W is the width of the lot and n is the no. of buildings to be placed
// Space Complexity : 0(HW)
// Did this code successfully run on Leetcode : not on leetcode
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach


    public static class BuildingPlacement{
        int H, W, n;    //to get height width and no. of buildings
        int minDistance;    //to calculate min distance after the place on buildings
        public BuildingPlacement(int H, int W, int n){  //constructor to initialize all
            this.H = H;
            this.W = W;
            this.n = n;
            minDistance = Integer.MAX_VALUE;
        }

        public int computeDistance(){   //compute the final minimum distance after the placement of the buildings
            int [][] grid = new int[H][W];  //I am taking a grid of H W dimensions to place my buildings
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    grid[i][j] = -1;    //initially filling all with -1 to show that no slot is explored to place a building
                }
            }

            backtracking(grid, n, 0, 0);    //passing the grid, no of buildings to be place and row and column to the backtracking method
            return minDistance; //returning the final distance
        }

        public void bfs(int [][] grid){ //this method computes my current minimum distance of parking lot to the buildings place and compares it with previous min
            boolean[][] visited = new boolean[H][W];    //keeping a track of visited so I don't have same elements coming in again and again
            Queue<int[]> q = new LinkedList<>();    //to store the buildings
            //Look for building to put inside the queue
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    if(grid[i][j] == 0){    //if it is 0, means a building is present
                        q.add(new int[]{i, j}); //adding it to the queue
                        visited[i][j] = true;   //also marking it as visited as buildings are present over here
                    }
                }
            }
            int[][] dirs = {{0,1}, {0, -1}, {1,0}, {-1, 0}};    //to navigate vertically and horizontally
            int distance = 0;   //to compute distance

            while(!q.isEmpty()){
                int size = q.size();    //the level will give the final distance
                for(int i = 0; i < size; i++){  //going over all the elements in the particular level
                    int [] curr = q.poll(); //getting the coordinates of the building
                    for(int [] dir : dirs){ //navigating in all directions
                        int nr = curr[0] + dir[0];
                        int nc = curr[1] + dir[1];  //getting new row and column once going in 1 direction
                        if(nr >= 0 && nc >= 0 && nr < H && nc < W && grid[nr][nc] == -1 && visited[nr][nc] == false){   //if it is in bounds and if it is not visited and if there is a parking lot at that position
                            visited[nr][nc] = true; //then marking it as visited and also means that I have computed the distance from building to that lot as distance is computed by the level
                            q.add(new int[] {nr, nc});  //and adding it to the queue
                        }
                    }
                }
                distance++; //increasing the distance after completing every level
            }
            minDistance = Math.min(minDistance, distance - 1);  //comparing it with current distance and prev distance

        }

        public void backtracking(int [][] grid, int n, int r, int c){
            //base
            if(n == 0){ //if all the buildings are place I call the bfs method to calculate the distance from parking lots
                bfs(grid);
                return;
            }
            //logic
            if(c == W){ //if column reaches the width, it needs to be resent and I need to check options to explore another row and place my buildings
                r++;
                c = 0;
            }
            for(int i = r; i < H; i++){ //going over all the rows and column to place building at every possible combination
                for(int j = c; j < W; j++){
                    //Action
                    grid[i][j] = 0; //placing the building means I am marking it as visited and I have already place a building
                    //Recurse
                    backtracking(grid, n-1, i, j + 1);  //recursively calling backtracking with placing a building at the current spot and increasing my column to place another building at another spot
                    //backtrack
                    grid[i][j] = -1;    //marking it as unvisited when all the options are explored for the current placement of buildings

                }
            }
        }
    }

    public static void main(String[] args) {
        BuildingPlacement bp = new BuildingPlacement(4,4,3);
        System.out.print(bp.computeDistance());
    }
}