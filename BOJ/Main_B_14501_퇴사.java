/**
* 12980	kb
* 76 ms
*
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_14501_퇴사 {
	
	static int N;
	static int[][] answer;
	static int[] dp = new int[25];

	public static void main(String[] args) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		answer = new int[N+1][2];
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			answer[i][0] = Integer.parseInt(st.nextToken());
			answer[i][1] = Integer.parseInt(st.nextToken());
		}
		
		dp[0] = 0;
		
		for(int n=N; n>0; n--) {
			if(n+answer[n][0]<=N+1) {
				dp[n] = answer[n][1] + dp[n+answer[n][0]];
				for(int j=1; j<5; j++) {
					int y = answer[n][1] + dp[n+answer[n][0]+j];				
					dp[n] = Math.max(dp[n], y);					
				}
			}
		}

		int max=0;
		for(int i=0; i<N+1; i++) {
			System.out.println(dp[i]);
			if(max<dp[i])
				max=dp[i];
		}
		
		System.out.println(max);
	}
}
