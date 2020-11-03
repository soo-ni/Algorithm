/**
 * 
 * 24904 kb	
 * 312 ms
 * using Two Pointer
 * 
 */
package study_Sep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_1806_부분합 {

	static int N;
	static long S;
	static int[] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Long.parseLong(st.nextToken());
		map = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		int s = 0, e = 0, cnt = Integer.MAX_VALUE; long sum=0;
		while(true) {
			if(sum >= S) {
				cnt = Math.min(cnt, e-s);
				sum -= map[s++];
			}else {
				sum += map[e++];
			}
			
			if(e==N) break;
		}
		
		for(int i=s; i<N; i++) {
			if(sum >= S) {
				cnt = Math.min(cnt, N-i);
			}
			sum -= map[i];
		}
		
		if(cnt==Integer.MAX_VALUE) cnt=0;
		System.out.println(cnt);
	}
}
