/**
 * 39264 kb	
 * 604 ms
 * 그냥 시뮬 시간 줄이기..ㅠㅠ (평균200ms대 흠..)
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_12100_2048Easy {
	static int N, ans;
	static int[] selected;
	static int[][] map, A;
	static ArrayList<Integer>[] ARC;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// NxN
		selected = new int[5];	// selected direction
		map = new int[N][N];	// map
		A = new int[N][N];		// temp map
		ARC = new ArrayList[N];	// for row, col
		for(int i=0; i<N; i++) {
			ARC[i] = new ArrayList<Integer>();
		}
		
		int temp;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
			}
		}
		
		ans=0;
		dfs(0);	// idx, current map
		System.out.println(ans);
	}
	
	private static void dfs(int idx) {
		if(idx==5) {
			int sum = go();
			ans = Math.max(ans, sum);
			return;
		}
		
		for(int i=0; i<4; i++) {
			selected[idx]=i;
			dfs(idx+1);
		}
	}
	
	private static int go() {
		for(int i=0; i<N; i++) {	// init
			for(int j=0; j<N; j++) {
				A[i][j] = map[i][j];
			}
		}
		
		int dir;
		for(int t=0; t<5; t++) {
			dir = selected[t];
			switch(dir) {
			case 0: 
			case 1: 
				up_down(dir);
				break;
			case 2: 
			case 3: 
				left_right(dir);
				break;
			}
		}
		
		int max=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				max = Math.max(max, A[i][j]);
			}
		}
		
		return max;
	}

	private static void left_right(int dir) {
		for(int i=0; i<N; i++) {	// init
			ARC[i].clear();
		}
		
		int temp;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(dir<3)	temp=A[i][j];
				else 		temp=A[i][N-j-1];
				
				if(temp<1) continue;	// 빈칸은 더할필요 없음
				ARC[i].add(temp);
			}
		}
		
		int len, cur, next, cur_idx, next_idx;
		for(int row=0; row<N; row++) {
			len = ARC[row].size();
			cur_idx=0; 
			
			while(cur_idx<len-1) {
				cur = ARC[row].get(cur_idx);	// 현재 idx 값
				next_idx = cur_idx+1;			// 다음 idx
				next = ARC[row].get(next_idx);	// 다음 idx 값
				if(cur==next) {	// 값이 같으면
					ARC[row].set(cur_idx, 0);	// 0으로 셋팅
					ARC[row].set(next_idx, next*2);	// 다음값 세팅
					cur_idx = next_idx+1;	// 현재 idx 업데이트
					
				}else {	// 값이 다르면
					cur_idx = next_idx;	// 현재 idx 업데이트
				}
			}
		}
		
		// A 업데이트
		for(int i=0; i<N; i++) {	// init
			Arrays.fill(A[i], 0);
		}
		int idx;
		for(int row=0; row<N; row++) {
			len = ARC[row].size();
			idx=0;
			for(int i=0; i<len; i++) {
				temp = ARC[row].get(i);
				if(temp<1) continue;
				
				if(dir<3)	A[row][idx++] = temp;
				else		A[row][N-(idx++)-1] = temp;
			}
		}
	}

	private static void up_down(int dir) {
		for(int i=0; i<N; i++) {	// init
			ARC[i].clear();
		}
		
		int temp;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(dir<1)	temp=A[i][j];
				else 		temp=A[N-i-1][j];
				
				if(temp<1) continue;	// 빈칸은 더할필요 없음
				ARC[j].add(temp);
			}
		}
		
		int len, cur, next, cur_idx, next_idx;
		for(int col=0; col<N; col++) {
			len = ARC[col].size();
			cur_idx=0; 
			
			while(cur_idx<len-1) {
				cur = ARC[col].get(cur_idx);	// 현재 idx 값
				next_idx = cur_idx+1;			// 다음 idx
				next = ARC[col].get(next_idx);	// 다음 idx 값
				if(cur==next) {	// 값이 같으면
					ARC[col].set(cur_idx, 0);	// 0으로 셋팅
					ARC[col].set(next_idx, next*2);	// 다음값 세팅
					cur_idx = next_idx+1;	// 현재 idx 업데이트
					
				}else {	// 값이 다르면
					cur_idx = next_idx;	// 현재 idx 업데이트
				}
			}
			
		}
		
		// A 업데이트
		for(int i=0; i<N; i++) {	// init
			Arrays.fill(A[i], 0);
		}
		int idx;
		for(int col=0; col<N; col++) {
			len = ARC[col].size();
			idx=0;
			for(int i=0; i<len; i++) {
				temp = ARC[col].get(i);
				if(temp<1) continue;
				
				if(dir<1)	A[idx++][col] = temp;
				else		A[N-(idx++)-1][col] = temp;
			}
		}
	}
	
}
