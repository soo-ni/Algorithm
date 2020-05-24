/**
 * 14788 kb	
 * 104 ms
 * using DFS
 * 14m
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_1012_유기농배추 {
	
	static int T, N, M, K, cnt;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	//col
			M = Integer.parseInt(st.nextToken());	//row
			K = Integer.parseInt(st.nextToken());	//배추
			map = new int[M][N];
			
			int x, y;
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				y = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				map[x][y]=1;
			}
			
			cnt=2;
			for(int i=0; i<M; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]==1) {
						dfs(i, j);
						cnt++;
					}
				}
			}
			
			System.out.println(cnt-2);
		}	//test case end
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void dfs(int row, int col) {
		map[row][col]=cnt;
		
		int nx, ny;
		for(int i=0; i<4; i++) {
			nx = row+dx[i];
			ny = col+dy[i];
			
			if(nx<0||ny<0||nx>M-1||ny>N-1) continue;
			if(map[nx][ny]==1) dfs(nx, ny);
		}
	}

}
