/**
* 13424 KB
* 88 ms
*
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_1697_숨바꼭질 {
	static int N, K;
	static int[] dp = new int[100001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		Arrays.fill(dp, 0);

		int a, b;
		for(int i=0; i<=K; i++) {
			if(i<N) {
				dp[i] = N-i;
				continue;
			}			
			
			if(i==N) {
				dp[i] = 0;
				continue;
			}
			
			if(i%2==0) {
				a = dp[i/2] + 1;				
			}else {
				a = Math.min(dp[(i-1)/2], dp[(i+1)/2]) + 2;				
			}
			
			b = dp[i-1]+1;

			dp[i] = Math.min(a, b);
		}		
		
		System.out.println(dp[K]);
		
	}
	
}
