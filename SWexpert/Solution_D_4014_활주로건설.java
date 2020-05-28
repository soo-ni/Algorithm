/**
 * 23,544 kb
 * 127 ms
 * using Stack? & simul
 * 50m
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_D_4014_활주로건설 {

	static int T, N, X, ans;
	static int[][] map;
	static Stack<Integer> stack;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());	//test case
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	//map 크기
			X = Integer.parseInt(st.nextToken());	//경사로 길이
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = 0;
			stack = new Stack<Integer>();
			for(int i=0; i<N; i++) {
				stack.clear();
				goRow(i);
				stack.clear();
				goCol(i);
			}
			
			sb.append('#').append(t+1).append(' ').append(ans).append('\n');
		}	//test case end
		
		System.out.println(sb.toString());
	}
	
	private static void goCol(int idx) {
		stack.push(map[0][idx]);
		
		int top, cur, gradient;
		for(int i=1; i<N; i++) {
			top = stack.peek();	//맨 위에 있는 높이
			cur = map[i][idx];	//그 다음에 오는 높이
			
			if(top==cur) {	//같으면 그냥 저장
				stack.push(cur);
			}else if((top-cur)==1) {	//내리막
				gradient=0;
				for(int j=0; j<X; j++) if(i+j<N && cur==map[i+j][idx]) gradient++;	//X길이만큼 똑같아야함
				for(int j=X; j<2*X; j++) if(i+j<N && cur<map[i+j][idx]) return;
				if(gradient!=X) return;
				stack.push(cur);
			}else if((top-cur)==-1) {	//오르막
				gradient=0;
				for(int j=0; j<X; j++) if(!stack.empty() && stack.pop()==top) gradient++;	//X길이만큼 똑같아야함
				if(gradient!=X) return;	//경사로 길이만큼 없으면 return
				stack.push(cur);	//다시 넣어주기
			}else {	//둘다 아니면
				return;
			}
		}
		ans++;
	}

	private static void goRow(int idx) {
		stack.push(map[idx][0]);
		
		int top, cur, gradient;
		for(int i=1; i<N; i++) {
			top = stack.peek();	//맨 위에 있는 높이
			cur = map[idx][i];	//그 다음에 오는 높이
			
			if(top==cur) {	//같으면 그냥 저장
				stack.push(cur);
			}else if((top-cur)==1) {	//내리막
				gradient=0;
				for(int j=0; j<X; j++) if(i+j<N &&cur==map[idx][i+j]) gradient++;	//X길이만큼 똑같아야함
				for(int j=X; j<2*X; j++) if(i+j<N && cur<map[idx][i+j]) return;
				if(gradient!=X) return;
				stack.push(cur);
			}else if((top-cur)==-1) {	//오르막
				gradient=0;
				for(int j=0; j<X; j++) if(!stack.empty() && stack.pop()==top) gradient++;	//X길이만큼 똑같아야함
				if(gradient!=X) return;	//경사로 길이만큼 없으면 return
				stack.push(cur);	//다시 넣어주기
			}else {	//둘다 아니면
				return;
			}
		}
		ans++;
	}
}

