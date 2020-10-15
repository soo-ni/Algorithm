/**
 * 
 * using DFS
 * 28712 kb	
 * 168 ms
 * 
 */

package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_B_2210_숫자점프 {
	
	static int[][] map;
	static HashSet<String> set;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[5][5];
		set = new HashSet<String>();
		
		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				String s = "";
				dfs(i, j, s);
			}
		}
		
		System.out.println(set.size());
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void dfs(int x, int y, String s) {
		if(s.length()==6) {
			set.add(s);
			return;
		}
		
		int nx, ny;
		for(int i=0; i<4; i++) {
			nx = x+dx[i];
			ny = y+dy[i];
			
			if(nx<0||ny<0||nx>4||ny>4) continue;
			s = s+String.valueOf(map[nx][ny]);
			dfs(nx, ny, s);
			s = s.substring(0, s.length()-1);
		}
	}

}
