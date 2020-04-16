/**
 * 12952 kb	
 * 72 ms
 * using DP
 * 
 */


package study;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_11727_2n타일링2 {
	
	static int n;
	static final int P = 10007;
	static long[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dp = new long[1001];
		
		dp[0]=1; dp[1]=1; dp[2]=3;
		for(int i=3; i<n+1; i++) {
			dp[i] = (dp[i-2]*2+dp[i-1])%P;
		}
		
		System.out.println(dp[n]);
	}

}
