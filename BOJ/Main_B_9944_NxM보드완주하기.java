/**
 * 
 * 16484 kb	
 * 448 ms
 * dfs
 * 
 */
package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_9944_NxM보드완주하기 {
	
	static int N, M, answer, blank;
	static char[][] map;
	static boolean[][] visited;
	static int[][] step;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		StringTokenizer st;
		
		int T = 1;
		String input;
		while((input=br.readLine())!=null) {
			sb = new StringBuilder();
			st = new StringTokenizer(input, " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			visited = new boolean[N][M];
			step = new int[N][M];
			blank=0;
			
			String s; char c;
			for(int i=0; i<N; i++) {
				s = br.readLine();
				for(int j=0; j<M; j++) {
					c = s.charAt(j);
					map[i][j] = c;
					if(c=='.') blank++;
				}
			}
			
			answer = Integer.MAX_VALUE;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j]=='.') {
						for(int dir=0; dir<4; dir++) {
							visited[i][j]=true;
							dfs(i, j, dir, 1, 1);
							visited[i][j]=false;
						}
					}
				}
			}
			
			if(answer>1000000) answer=-1;
			else if(blank==1) answer=0;
			sb.append("Case ").append(T++).append(": ").append(answer);
			System.out.println(sb.toString());
		}
		
	}
	
	private static boolean check(int nx, int ny) {
		if(nx<0||ny<0||nx>N-1||ny>M-1) return false;
		if(visited[nx][ny]) return false;
		if(map[nx][ny]=='*') return false;
		return true;
	}
	
	private static void dfs(int x, int y, int dir, int idx, int cnt) {
		if(idx==blank) {
			answer = Math.min(answer, cnt);
			return;
		}
		
		int nx, ny;
		nx = x+dx[dir];
		ny = y+dy[dir];
		if(check(nx, ny)) {
			visited[nx][ny]=true;
			dfs(nx, ny, dir, idx+1, cnt);
			visited[nx][ny]=false;
			
		}else {
			for(int i=0; i<4; i++) {
				if(i==dir) continue;
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(!check(nx, ny)) continue;
				
				visited[nx][ny]=true;
				dfs(nx, ny, i, idx+1, cnt+1);
				visited[nx][ny]=false;
			}
		}
		
	}

}
