/**
 * 13820 kb	
 * 136 ms
 * using DFS
 * 23m
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_1405_미친로봇 {
	static int N;
	static double ans;
	static double[] map;
	static boolean[][] visited = new boolean[29][29];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	//이동 횟수
		map = new double[4];	//동서남북 확률
		for(int i=0; i<29; i++) {
			for(int j=0; j<29; j++) {
				visited[i][j]=false;
			}
		}
		
		int temp;
		for(int i=0; i<4; i++) {
			temp = Integer.parseInt(st.nextToken());
			map[i] = temp/100.0;
		}
		
		ans=0.0;
		dfs(14, 14, 1.0, 0);	//x, y, p, idx 
		
		System.out.println(ans);
	}
	
	static int[] dx = {0, 0, 1, -1};	//동서남북
	static int[] dy = {1, -1, 0, 0};
	private static void dfs(int x, int y, double p, int idx) {
		if(idx==N) {
			ans+=p;
			return;
		}
		
		visited[x][y]=true;
		
		int nx, ny;
		for(int i=0; i<4; i++) {
			nx = x+dx[i];
			ny = y+dy[i];
			if(!visited[nx][ny]) {
				dfs(nx, ny, p*map[i], idx+1);
			}
		}
		
		visited[x][y]=false;
	}
}
