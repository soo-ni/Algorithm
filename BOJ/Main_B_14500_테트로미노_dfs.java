package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 33176 kb
 * 836 ms
 * dfs
 * @author soo-ni
 *
 */
public class Main_B_14500_테트로미노_dfs {
	
	static int R, C, answer;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());	// 행
		C = Integer.parseInt(st.nextToken());	// 열
		map = new int[R][C];	// 숫자
		
		int temp;
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
			}
		}
		
		boolean[][] visited = new boolean[R][C];
		answer = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				go(0, i, j, visited, 0);
				extra(i, j);
			}
		}
		
		System.out.println(answer);
	}
	
	static int[][][] tetro = {
			{{0,0},{1,0},{1,1},{2,0}}, {{0,0},{0,1},{-1,1},{1,1}}, 
			{{0,0},{0,1},{0,2},{-1,1}}, {{0,0},{0,1},{0,2},{1,1}}
	};
	private static void extra(int x, int y) {
		int nx, ny, sum;
		for(int i=0; i<4; i++) {
			sum=0;
			for(int j=0; j<4; j++) {
				nx = x+tetro[i][j][0];
				ny = y+tetro[i][j][1];
				if(nx<0||ny<0||nx>R-1||ny>C-1) break;
				sum+=map[nx][ny];
			}
			answer = Math.max(answer, sum);
		}
	}

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void go(int idx, int x, int y, boolean[][] visited, int cnt) {
		if(idx==4) {
			answer = Math.max(answer, cnt);
			return;
		}
		
		int nx, ny;
		for(int i=0; i<4; i++) {
			nx = x+dx[i];
			ny = y+dy[i];
			
			if(nx<0||ny<0||nx>R-1||ny>C-1) continue;
			if(visited[nx][ny]) continue;
			visited[x][y]=true;
			go(idx+1, nx, ny, visited, cnt+map[nx][ny]);
			visited[x][y]=false;
		}
		
	}

}
