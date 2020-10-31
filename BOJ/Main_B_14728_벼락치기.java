package study_Sep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_14728_벼락치기 {

	static int N, T;
	static int[][] map;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N+1][2];
		dp = new int[100000];
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=N; i>-1; i--) {
			for(int j=T; j>map[i][0]-1; j--) {
				dp[j] = Math.max(dp[j], dp[j-map[i][0]]+map[i][1]);
			}
		}
		
		System.out.println(dp[T]);
	}
}
