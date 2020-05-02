/**
 * 22,440 kb
 * 132 ms
 * using DFS
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_1227_미로2 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static boolean token;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for(int t=0; t<10; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[100][100];
			visited = new boolean[100][100];
			
			String s; 
			for(int i=0; i<100; i++) {
				s = br.readLine();
				for(int j=0; j<100; j++) {
					map[i][j] = s.charAt(j) - '0'; 
				}
			}
			
			token = false;
			dfs(1, 1);
			
			int ans=0;
			if(token) ans=1;
			sb.append("#"+N+" "+ans+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int nx, ny;
	private static void dfs(int row, int col) {
		
		if(map[row][col]==3) {
			token=true;
			return;
		}
		
		for(int i=0; i<4; i++) {
			nx = row+dx[i];
			ny = col+dy[i];
			if(!visited[nx][ny] && map[nx][ny]!=1) {
				visited[row][col] = true;
				dfs(nx, ny);
				visited[row][col] = false;
			}
		}
	}
}

