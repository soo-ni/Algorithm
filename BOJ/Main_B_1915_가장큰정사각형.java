/**
 * 
 * 28128 kb	
 * 388 ms
 * using DP
 * 
 */

package study_Sep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_1915_가장큰정사각형 {
	
	static int N, M, answer;
	static int[][] map, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N+1][M+1];
		
		String s;
		for(int i=0; i<N; i++) {
			s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		
		answer = 0;
		int temp;
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<M+1; j++) {
				if(map[i-1][j-1]>0) {	// 1일때
					temp = Math.min(dp[i-1][j-1], dp[i-1][j]);	// 왼위 위
					dp[i][j] = Math.min(temp, dp[i][j-1]) + 1;	//왼
					answer = Math.max(answer, dp[i][j]);
				}
			}
		}
		
		System.out.println(answer*answer);
	}

}
