/**
 * 
 * using DFS+DP
 * 14416 kb
 * 104 ms
 * 
 */

package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_1103_게임 {

	static int N, M, answer;
	static int[][] map, dp;
	static boolean[][] visited;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];
		visited = new boolean[N][M];
		
		String s;
		for(int i=0; i<N; i++) {
			s = br.readLine();
			for(int j=0; j<M; j++) {
				if(s.charAt(j)=='H') map[i][j] = 10;
				else map[i][j] = s.charAt(j)-'0';
			}
		}
		
		flag = true;
		answer = 0;
		visited[0][0]=true;
		dfs(0, 0, 0);	// row, col, cnt
		
		System.out.println(answer+1);
		
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void dfs(int x, int y, int cnt) {
		if(!flag) {
			answer = -2;
			return;
		}
		
		answer = Math.max(answer, cnt);
		dp[x][y] = cnt;
		
		int next, nx, ny;
		for(int i=0; i<4; i++) {
			next = map[x][y];
			nx = x+next*dx[i];
			ny = y+next*dy[i];
			
			if(nx<0||ny<0||nx>N-1||ny>M-1) continue;
			if(map[nx][ny]>9) continue;
			if(visited[nx][ny]) {	// 무한으로 돌 때
				flag=false;
				answer=-2;
				return;
			}
			
			if(dp[nx][ny]>cnt) continue;
			visited[nx][ny] = true;
			dfs(nx, ny, cnt+1);
			visited[nx][ny] = false;
		}
	}
	
}
