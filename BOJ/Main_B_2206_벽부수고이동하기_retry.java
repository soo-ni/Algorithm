/**
 * 160648 kb	
 * 588 ms
 * using BFS
 * 
 */

package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_2206_벽부수고이동하기_retry {
	static int N, M, ans;
	static int[][] map;
	static boolean[][][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// row
		M = Integer.parseInt(st.nextToken());	// col
		map = new int[N][M];	// map
		visited = new boolean[N][M][2];			// visited check: wall X / wall O
		
		String s; char c;
		for(int i=0; i<N; i++) {
			s = br.readLine();
			for(int j=0; j<M; j++) {
				c = s.charAt(j);
				map[i][j] = c-'0';
			}
		}
		
		token=false;
		ans = Integer.MAX_VALUE;
		queue = new LinkedList<Point>();
		bfs();
		if(!token) ans=-1; 
		System.out.println(ans);
	}

	static boolean token;
	static LinkedList<Point> queue;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void bfs() {
		queue.add(new Point(0, 0, 1, 1));
		visited[0][0][0]=true;
		visited[0][0][1]=true;
		
		Point current; int x, y, wall, cnt, nx, ny;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			wall = current.wall;
			cnt = current.cnt;
			
			if(x==N-1 && y==M-1) {
				ans = Math.min(ans, cnt);
				token=true;
			}
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0||ny<0||nx>N-1||ny>M-1) continue;
				
				if(wall>0) {	// 아직 안부심
					if(!visited[nx][ny][0] && map[nx][ny]==0) { // 안부시고 벽안만났을때
						queue.add(new Point(nx, ny, 1, cnt+1));
						visited[nx][ny][0] = true;
					}
					
					if(!visited[nx][ny][0] && map[nx][ny]==1) {	// 안부시고 벽만났을때
						queue.add(new Point(nx, ny, 0, cnt+1));
						visited[nx][ny][1] = true;
					}
					
				}else {	// 부셨음
					if(!visited[nx][ny][1] && map[nx][ny]==0) { // 부시고 벽안만났을때
						queue.add(new Point(nx, ny, 0, cnt+1));
						visited[nx][ny][1] = true;
					}
				}
			}
			
		}
		
	}

	static class Point {
		int x, y, wall, cnt;
		public Point(int x, int y, int wall, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.wall = wall;
			this.cnt = cnt;
		}
	}
}
