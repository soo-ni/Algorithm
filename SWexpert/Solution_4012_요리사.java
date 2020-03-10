/**
* 31,740 kb
* 153 ms
*
*/

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012_요리사 {
	
	static int T, N, half_N, ans;
	static int[][] synergy;
	static boolean[] selected;
	static int[] selected_A, selected_B;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// 재료 수
			half_N = N/2;
			synergy = new int[N][N];	// 시너지
			selected = new boolean[N];	// 선택된 요리
			selected_A = new int[N/2];	// 선택된 A요리 번호
			selected_B = new int[N/2];	// 선택된 B요리 번호
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = Integer.MAX_VALUE;
			selected[0]=true;
			selected_A[0]=0;
			getAns(1);	// idx
			
			System.out.println("#"+(t+1)+" "+ans);
			
		}	// test case
	}

	private static void getAns(int idx) {
		if(ans==0) return;
		if(idx==half_N) {
			int cnt_a=0, cnt_b=0;
			for(int i=0; i<N; i++) {
				if(selected[i]) selected_A[cnt_a++]=i;
				else selected_B[cnt_b++]=i;
			}

			int a=0, b=0, x, y;
			for(int i=0; i<half_N; i++) {
				for(int j=0; j<half_N; j++) {
					x=selected_A[i];
					y=selected_A[j];
					a+=synergy[x][y];
				}
			}			
			
			for(int i=0; i<half_N; i++) {
				for(int j=0; j<half_N; j++) {
					x=selected_B[i];
					y=selected_B[j];
					b+=synergy[x][y];
				}
			}
			
			ans = Math.min(ans, Math.abs(a-b));
			return;
		}
		
		int m=selected_A[idx-1];
		for(int i=m; i<N; i++) {
			if(!selected[i]) {
				selected[i]=true;
				selected_A[idx]=i;
				getAns(idx+1);
				selected[i]=false;
			}
		}
		
	}
}
