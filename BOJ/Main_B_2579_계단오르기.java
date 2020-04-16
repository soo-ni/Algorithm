package study;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class Main_B_2579_계단오르기 {
	static int N;
	static int[] arr;
	static Vector<Integer> score = new Vector<Integer>();
	static int dp[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[301];
		dp = new int[301];
		
		for(int i=0; i<N; i++)
			arr[i] = sc.nextInt();
		
		Arrays.fill(dp, 0);
		dp[0]=arr[0];
		
		dp[0] = arr[0];
		dp[1] = Math.max(arr[0]+arr[1], arr[1]);
		dp[2] = Math.max(arr[0]+arr[2], arr[1]+arr[2]);
		
		for(int i=3; i<N; i++) {
			dp[i] = Math.max(dp[i-2]+arr[i], dp[i-3]+arr[i-1]+arr[i]);
		}
		
		System.out.print(dp[N-1]);
		
		sc.close();
	}
}
