/**
 * 
 * 217424 kb	
 * 736 ms
 * 
 */

package study_Sep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_2075_N번째큰수 {

	static int N;
	static int[][] map;
	static int[] num;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		num = new int[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Arrays.fill(num, N-1);
		
		int max=0;
		for(int i=0; i<N; i++) {
			max = map[num[0]][0];
			int n=0;
			
			for(int j=0; j<N; j++) {
				if(max<map[num[j]][j]) {
					max = map[num[j]][j];
					n = j;
				}
			}
			
			num[n]--;
		}
		
		System.out.println(max);
	}
}
