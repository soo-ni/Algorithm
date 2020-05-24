/**
 * 13624 kb
 * 80 ms
 * using DFS
 * 13m
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_10026_적록색약 {
	static int N;
	static char[][] map, mapRG;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	//크기
		map = new char[N][N];	//일반
		mapRG = new char[N][N];	//색약
		
		String s; char c;
		for(int i=0; i<N; i++) {
			s = br.readLine();
			for(int j=0; j<N; j++) {
				c = s.charAt(j);
				map[i][j] = c;
				mapRG[i][j] = c;
				if(c=='G') mapRG[i][j]='R';
			}
		}
		
		int cnt=0, cntRG=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]!='.') {
					dfs(i, j, map[i][j]);
					cnt++;
				}
				
				if(mapRG[i][j]!='.') {
					dfsRG(i, j, mapRG[i][j]);
					cntRG++;
				}
			}
		}
		
		System.out.println(cnt+" "+cntRG);
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void dfs(int x, int y, char color) {
		map[x][y]='.';
		
		int nx, ny;
		for(int i=0; i<4; i++) {
			nx = x+dx[i];
			ny = y+dy[i];
			
			if(nx<0||ny<0||nx>N-1||ny>N-1) continue;
			if(map[nx][ny]==color) dfs(nx, ny, color);
		}
	}
	
	private static void dfsRG(int x, int y, char color) {
		mapRG[x][y]='.';
		
		int nx, ny;
		for(int i=0; i<4; i++) {
			nx = x+dx[i];
			ny = y+dy[i];
			
			if(nx<0||ny<0||nx>N-1||ny>N-1) continue;
			if(mapRG[nx][ny]==color) dfsRG(nx, ny, color);
		}
	}
}
