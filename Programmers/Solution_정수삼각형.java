class Solution {
    static int[][] dp;
    public int solution(int[][] triangle) {
        int N = triangle.length;
        int M = triangle[N-1].length;
        System.out.println(N+" "+M);
        dp = new int[N][M];
        
        dp[0][0]=triangle[0][0];
        dp[1][0]=dp[0][0]+triangle[1][0];
        dp[1][1]=dp[0][0]+triangle[1][1];
        
        int tempL=0, tempR=0;
        for(int i=1; i<N-1; i++) {
            for(int j=0; j<=i; j++) {
                tempL = dp[i][j]+triangle[i+1][j];   // 좌하
                tempR = dp[i][j]+triangle[i+1][j+1];    // 우하
                if(dp[i+1][j] < tempL){
                    dp[i+1][j] = tempL;
                }
                if(dp[i+1][j+1] < tempR){
                    dp[i+1][j+1] = tempR;
                }
            }
        }
        
        // show(N, M);
        int answer = 0;
        for(int i=0; i<M; i++) {
            answer = Math.max(dp[N-1][i], answer);
        }
        return answer;
    }
    
    public void show(int N, int M) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
    }
}
