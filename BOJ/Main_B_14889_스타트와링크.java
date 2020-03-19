/**
* 17868 kb
* 844 ms
*
*/

package study_0204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_14889_스타트와링크 {
	
	static int N, min;
	static int[][] point;
	static boolean[] selected;
	static int[] number;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		point = new int[N][N];
		selected = new boolean[N];
		number = new int[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				point[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		min = Integer.MAX_VALUE;
		getPoint(0);
		System.out.print(min);
		
	}

	private static void getPoint(int idx) {
		int m=0;
		if(idx>1)
			m=number[idx-1];
			
		if(idx==N/2) {
			int A=0, B=0, num=N/2;
			for(int i=0; i<N; i++) {
				if(!selected[i])
					number[num++] = i;
			}
			
			for(int i=0; i<N/2; i++) {
				for(int j=0; j<N/2; j++) {
					if(i==j) continue;
					A += point[number[i]][number[j]];
				}
			}
			for(int i=N/2; i<N; i++) {
				for(int j=N/2; j<N; j++) {
					if(i==j) continue;
					B += point[number[i]][number[j]];
				}
			}
			
			int gap = Math.abs(A-B);
			if(min>gap)	min=gap;
			else return;
			
			return;
		}
		
		for(int i=m; i<N; i++) {
			if(!selected[i]) {
				number[idx] = i;
				selected[i] = true;
				getPoint(idx+1);
				selected[i] = false;
			}
		}
	}
}
