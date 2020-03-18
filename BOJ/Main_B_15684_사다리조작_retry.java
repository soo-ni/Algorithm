/**
 * 15276 kb	
 * 404 ms
 * dfs로 풀었음
 * 
 */

package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_15684_사다리조작_retry {
	
	static int N, M, H, ans, sum;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 세로선 개수(col)
		M = Integer.parseInt(st.nextToken());	// 사다리 개수
		H = Integer.parseInt(st.nextToken());	// 가로선 개수(row)
		visited = new boolean[H+1][N+2];	// 사다리 놓여져있는지 체크
		
		int a, b;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());	// 가로 번호(row)
			b = Integer.parseInt(st.nextToken());	// 시작하는 사다리 번호(col)
			visited[a][b] = true;
		}
		
		token=false;
		for(int i=0; i<4; i++) {
			if(token) break;
			sum=i;	// 놓여야할 사다리 개수
			getLadder(1, 1, 0);	// idx: 놓인 사다리 개수
		}
		if(!token) ans=-1;
		System.out.println(ans);
	}
	
	static boolean token;
	private static void getLadder(int row, int col, int idx) {
		if(token) return;
		if(idx==sum) {	// 사다리가 다 놓였다면
			if(!checkLadder()) return;
			token=true;
			ans=sum;
			return;
		}
		
		for(int i=row; i<H+1; i++) {
			for(int j=1; j<N; j++) {
				if(!visited[i][j] && !visited[i][j-1] && !visited[i][j+1]) {	// 현재 위치, 왼쪽, 오른쪽 사다리가 없어야함
					visited[i][j]=true;
					getLadder(i, j, idx+1);
					visited[i][j]=false;
				}
			}
		}
	}
	
	private static boolean checkLadder() {
		int row, col;
		for(int i=1; i<N+1; i++) {
			row=1; col=i;
			while(row<H+1) {
				if(visited[row][col]) {	// 오른쪽
					col++;
				}else if(visited[row][col-1]) {	// 왼쪽
					col--;
				}
				row++;
			}
			if(col!=i) return false;
		}
		return true;
	}
}
