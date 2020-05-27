/**
 * 22,792 kb
 * 386 ms
 * using DFS (pre 꼭 넣기!!!)
 * 1h 10m
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_D_2112_보호필름 {

	static int T, D, W, K;
	static int[][] map;
	static Stack<Integer> stack;
	static boolean token;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());	//test case
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());	//두께
			W = Integer.parseInt(st.nextToken());	//너비
			K = Integer.parseInt(st.nextToken());	//성능
			map = new int[D][W];	//필름
			
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int ans=0;
			token = false;

			int[] selected = new int[D];
			Arrays.fill(selected, 2);
			go(selected);	//확인

			for(int k=0; k<D; k++) {
				if(token) {
					ans=k; break;
				}
				
				if(k<1) continue;
				dfs(0, 0, selected, k);
			}
			
			if(ans==0) ans=1;
			sb.append('#').append(t+1).append(' ').append(ans-1).append('\n');
		}	//test case end
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int pre, int idx, int[] selected, int k) {
		if(token) return;		
		if(idx==k) {
//			for(int i: selected) {
//				System.out.print(i+" ");
//			}
			go(selected);
			return;
		}
		
		for(int i=pre; i<D; i++) {
			for(int j=0; j<2; j++) {
				if(selected[i]==2) {
					selected[i]=j;
					dfs(i, idx+1, selected, k);
					selected[i]=2;
				}
			}
		}
		
	}

	private static void go(int[] selected) {
		boolean check = false;
		int temp, sum=1, cur;
		for(int j=0; j<W; j++) {
			sum=1; check=false;
			
			if(selected[0]>1)	cur = map[0][j];
			else				cur = selected[0];
			
			temp = cur;
			for(int i=1; i<D; i++) {
				if(selected[i]>1)	cur = map[i][j];
				else				cur = selected[i];
				
				if(temp==cur) {
					sum++;
					if(sum==K) {
						check=true; break;
					}
				}else {
					temp=cur;
					sum=1;
				}
			}
			
			if(!check) break;
		}
		
		if(check) token=true;
	}
}
