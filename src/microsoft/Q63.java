package microsoft;
// Unique Paths II
public class Q63 {

//    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//		int m = obstacleGrid.length;
//		int n = obstacleGrid[0].length;
//		int[] dp = new int[m];
//		dp[0] = 1;	//assume 1th no obstacle
//		//begin from 0 not 1 (Q62 begin from 1)
//		for(int i = 0; i < n; i++){
//			for(int j = 0; j < m; j++){
//				if(obstacleGrid[j][i] == 1)
//					dp[j] = 0;	//if obstacle, change value of dp as 0
//				else if(j > 0)
//					dp[j] += dp[j-1];
//			}
//		}
//		return dp[m-1];
//	}
        /*method3*/
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int width = obstacleGrid[0].length;
            int[] dp = new int[width];
            dp[0] = 1;

            for(int[] row : obstacleGrid){
                for(int j = 0; j < width; j++){
                    if(row[j] == 1)
                        dp[j] = 0;
                    else if(j > 0)
                        dp[j] += dp[j-1];
                }
            }
            return dp[width-1];
        }
}
