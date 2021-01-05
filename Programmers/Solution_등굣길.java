import java.util.Arrays;
class Solution {
    static final long MOD = 1000000007;
    static long[][] dp;
    static boolean[][] visited;
    public int solution(int m, int n, int[][] puddles) {
        dp = new long[n+1][m+1];
        visited = new boolean[n+1][m+1];        
        for(int i=0; i<puddles.length; i++) {
            visited[puddles[i][1]][puddles[i][0]]=true;
        }
        
        dp[1][1]=1;
        for(int i=1; i<n+1; i++) {
            for(int j=1; j<m+1; j++) {
                if(i==1 && j==1) continue;
                if(visited[i][j]) continue;
                dp[i][j] = (dp[i][j-1]+dp[i-1][j])%MOD; // 왼쪽, 위쪽
            }
        }
        
        //show(m, n);
        return (int) (dp[n][m]%MOD);
    }
    
    public void show(int m, int n) {
        for(int i=1; i<n+1; i++) {
            for(int j=1; j<m+1; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
    }
}
