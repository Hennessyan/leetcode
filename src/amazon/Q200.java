package amazon;
// Number of Islands
public class Q200 {

    //https://leetcode.com/problems/number-of-islands/solution/
    /*method1 DFS*/
    //TC: O(m*n)
    //SC: O(m*n) worst case O(M×N) in case that the grid map is filled with lands where DFS goes by M×N deep.
//	public int numIslands(char[][] grid) {
//		if(grid == null || grid.length == 0){
//			return 0;
//		}
//		int r = grid.length, c = grid[0].length;
//		int numOfLand = 0;
//		for(int i = 0; i < r; i++){
//			for(int j = 0; j < c; j++){
//				if(grid[i][j] == '1'){
//					numOfLand++;
//					dfs(grid, i, j);
//				}
//			}
//		}
//		return numOfLand;
//    }
//	private void dfs(char[][] grid, int i, int j){
//		int r = grid.length, c = grid[0].length;
//		if(i < 0 || j < 0 || i >= r || j >= c || grid[i][j] == '0'){
//			return;
//		}
//		grid[i][j] = '0';
//		dfs(grid, i + 1, j);
//		dfs(grid, i - 1, j);
//		dfs(grid, i, j + 1);
//		dfs(grid, i, j - 1);
//	}
    /*method2 BFS*/
    //Time complexity  : O(M×N)
    //Space complexity : O(min(M,N)) because in worst case where the grid is filled with lands, the size of queue can grow up to min(M,N).
//	public int numIslands(char[][] grid) {
//		if(grid == null || grid.length == 0){
//			return 0;
//		}
//		int r = grid.length, c = grid[0].length;
//		int numOfLand = 0;
//
//		int[] axisX = {-1,0,1,0};
//		int[] axisY = {0,-1,0,1};
//
//		for(int i = 0; i < r; i++){
//			for(int j = 0; j < c; j++){
//				if(grid[i][j] == '1'){
//					numOfLand++;
//					grid[i][j] = '0';
//					Queue<Integer> queue = new LinkedList<>();
//					queue.offer(i*c+j);
//					while(!queue.isEmpty()){
//						int val = queue.poll();
//						for(int k = 0; k < 4; k++){
//							int x = val / c + axisX[k];
//							int y = val % c + axisY[k];
//							if(x >= 0 && x < r && y >= 0 && y < c && grid[x][y] == '1'){
//								grid[x][y] = '0';
//								queue.add(x*c + y);
//							}
//						}
//					}
//				}
//			}
//		}
//		return numOfLand;
//	}
    /*method3 union find*/
    //TC & SC: O(M×N)
    //Union operation takes essentially constant time1 when UnionFind is implemented with both path compression and union by rank.
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        int nr = grid.length;
        int nc = grid[0].length;
        int[] X = {-1, 0, 1, 0};
        int[] Y = {0, -1, 0, 1};
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if(grid[r][c] == '1'){
                    grid[r][c] = '0';
                    for(int k = 0; k < 4; k++){
                        int x = r + X[k];
                        int y = c + Y[k];
                        if(x >= 0 && y >= 0 && x < nr && y < nc && grid[x][y] == '1'){
                            //注意：这里不能执行grid[x][y] = '0'命令,因为它会使得属于统一union的部分无法连接！！！
                            //grid[x][y] = '0';
                            uf.union(r*nc+c, x*nc+y);
                        }
                    }
                }
            }
        }
        return uf.getCount();
    }

    class UnionFind{
        int count; // # of connected components
        int[] parent;
        int[] rank;
        public UnionFind(char[][] grid){	// for problem 200
            count = 0;
            parent = new int[grid.length * grid[0].length];
            rank = new int[grid.length * grid[0].length];
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid[0].length; j++){
                    if(grid[i][j] == '1'){
                        parent[i * grid[0].length + j] = i * grid[0].length + j;
                        ++count;
                    }
                    rank[i * grid[0].length + j] = 0;
                }
            }
        }
        //同q130的find方法
        public int find(int i){	 // path compression
            if(parent[i] == i){
                return i;
            }
            parent[i] = find(parent[i]);	//可以直接
            return parent[i];				//return find(parent[i]);
        }

//        public int find(int n) {
//            while(n != parents[n]) {
//                parents[n] = parents[parents[n]];
//                n = parents[n];
//            }
//            return n;
//        }

        public void union(int i, int j){	// union with rank
            int r1 = find(i);
            int r2 = find(j);
            if(r1 != r2){
                if(rank[r1] > rank[r2]){
                    parent[r2] = r1;
                }else if(rank[r1] < rank[r2]){
                    parent[r1] = r2;
                }else{
                    parent[r2] = r1;
                    rank[r1]++;
                }
                count--;//注意这个要放在if条件里边
            }
        }
        public int getCount(){
            return count;
        }
    }

}
