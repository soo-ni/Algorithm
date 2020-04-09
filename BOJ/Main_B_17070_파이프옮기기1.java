/**
 * 17248 kb
 * 324 ms
 * using dfs
 * 문제 잘 읽기!
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_17070_파이프옮기기1 {
	
	static int N, ans;
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 격자 판 수
		map = new int[N][N];			// map
		visited = new boolean[N][N];	// 방문 체크
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0;
		dfs(0, 0, 0);
		System.out.println(ans);
	}

	static int[] dx = {0, 1, 1};	// 우 우하 하
	static int[] dy = {1, 1, 0};
	private static void dfs(int row, int col, int dir) {	// dir: 0(가로), 1(대각선), 2(세로)
		if((row+dx[dir])==N-1 && (col+dy[dir])==N-1) {	// 한쪽 끝이 마지막 이라면
			ans++;
			return;
		}
		
		visited[row][col] = true;
		
		int nx, ny;
		nx = row+dx[dir];	// 일단 가는 방향대로 옮기기
		ny = col+dy[dir];	// 일단 가는 방향대로 옮기기
		
		switch(dir) {
		case 0:
			for(int i=0; i<2; i++) {
				if(nx+dx[i]>N-1||ny+dy[i]>N-1||map[nx+dx[i]][ny+dy[i]]>0) {
					continue;
				}
				if(!visited[nx][ny]) {
					if(i==1) if(!checkSquare(nx, ny)) continue;
					dfs(nx, ny, i);
				}
			}
			break;
			
		case 1:
			for(int i=0; i<3; i++) {
				if(nx+dx[i]>N-1||ny+dy[i]>N-1||map[nx+dx[i]][ny+dy[i]]>0) {
					continue;
				}
				if(!visited[nx][ny]) {
					if(i==1) if(!checkSquare(nx, ny)) continue;
					dfs(nx, ny, i);
				}
			}
			break;
		
		case 2:
			for(int i=1; i<3; i++) {
				if(nx+dx[i]>N-1||ny+dy[i]>N-1||map[nx+dx[i]][ny+dy[i]]>0) {
					continue;
				}
				if(!visited[nx][ny]) {
					if(i==1) if(!checkSquare(nx, ny)) continue;
					dfs(nx, ny, i);
				}
			}
			break;
		}
		
		visited[row][col] = false;
		
	}
	
	private static boolean checkSquare(int x, int y) {	// 대각선방향일 때 벽있는지 확인
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				if(map[x+i][y+j]>0) return false;
			}
		}
		
		return true;
	}
}
