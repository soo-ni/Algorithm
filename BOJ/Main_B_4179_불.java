/**
 * 72240 kb	
 * 520 ms
 * using BFS
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_4179_불 {

	static int R, C, ans;
	static char[][] map;
	static boolean[][] visited;
	static boolean token;
	static LinkedList<Point> queue, fire;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());	// row
		C = Integer.parseInt(st.nextToken());	// col
		map = new char[R][C];	// map
		visited = new boolean[R][C];	// visited check
		queue = new LinkedList<Point>();	// J
		fire = new LinkedList<Point>();	// fire
		
		String s; char c;
		for(int i=0; i<R; i++) {
			s = br.readLine();
			for(int j=0; j<C; j++) {
				c = s.charAt(j);
				map[i][j] = c;
				if(c=='F') fire.add(new Point(i, j, 0));
				if(c=='J') {
					queue.add(new Point(i, j, 0));
					visited[i][j]=true;
				}
			}
		}
		
		ans=Integer.MAX_VALUE;
		token=false;
		while(!queue.isEmpty()) {
			bfs_fire();
			bfs_J();
		}
		
		if(!token) System.out.println("IMPOSSIBLE");
		else System.out.println(ans);
		
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void bfs_J() {
		int size = queue.size();
		
		Point current; int x, y, nx, ny, cnt;
		while(size-->0) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			cnt = current.cnt;
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0||ny<0||nx>R-1||ny>C-1) {
					ans = Math.min(ans, cnt+1); token=true;
					continue;
				}
				
				if(!visited[nx][ny] && map[nx][ny]=='.') {
					queue.add(new Point(nx, ny, cnt+1));
					visited[nx][ny]=true;
				}
			}
		}
		
	}

	private static void bfs_fire() {
		int size = fire.size();
		
		Point current; int x, y, nx, ny;
		while(size-->0) {
			current = fire.poll();
			x = current.x;
			y = current.y;
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0||ny<0||nx>R-1||ny>C-1) continue;
				if(map[nx][ny]=='.' || map[nx][ny]=='J') {
					fire.add(new Point(nx, ny, 0));
					map[nx][ny]='F';
				}
			}
			
		}
	}

	static class Point {
		int x, y, cnt;
		public Point(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}
