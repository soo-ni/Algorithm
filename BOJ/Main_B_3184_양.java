/**
 * 
 * 20000 kb	
 * 176 ms
 * using BFS
 * 
 */

package study_Sep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_3184_양 {
	
	static int R, C, ansO, ansV;
	static char[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		
		String s;
		for(int i=0; i<R; i++) {
			s = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		ansO=0; ansV=0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(!visited[i][j] && map[i][j]!='#') {
					bfs(i, j);
				}
			}
		}
		
		System.out.println(ansO+" "+ansV);
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void bfs(int row, int col) {
		Point current; int x, y, nx, ny, o=0, v=0;
		
		LinkedList<Point> queue = new LinkedList<>();
		queue.add(new Point(row, col));
		visited[row][col]=true;
		if(map[row][col]=='o') o+=1;
		else if(map[row][col]=='v') v+=1;
		
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0||ny<0||nx>R-1||ny>C-1) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny]=='#') continue;
				if(map[nx][ny]=='v') {	// 늑대
					visited[nx][ny]=true;
					queue.add(new Point(nx, ny));
					v+=1;
				}else if(map[nx][ny]=='o'){	// 양
					visited[nx][ny]=true;
					queue.add(new Point(nx, ny));
					o+=1;
				}else {
					visited[nx][ny]=true;
					queue.add(new Point(nx, ny));
				}
			}
		}
		
		if(v>=o) {
			ansV += v;
		}else {
			ansO += o;
		}
	}

	static class Point {
		int x, y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
