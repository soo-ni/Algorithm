/**
 * 
 * using BFS
 * 14240 kb	
 * 128 ms
 * 
 */

package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_6087_레이저통신 {
	
	static int W, H, answer;
	static char[][] map;
	static int[][] visited;
	static Point start, end;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		visited = new int[H][W];
		start = new Point(-1, 0, 0, 0);
		
		String s;
		for(int i=0; i<H; i++) {
			s = br.readLine();
			for(int j=0; j<W; j++) {
				map[i][j] = s.charAt(j);
				visited[i][j] = 10000;
				if(map[i][j]=='C') {
					if(start.x==-1)	start = new Point(i, j, -1, 0);	// 처음 방향은 -1
					else end = new Point(i, j, -1, 0);
				}
			}
		}
		
		answer = Integer.MAX_VALUE;
		bfs();
		
		if(answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
		
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void bfs() {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		queue.add(start);
		visited[start.x][start.y]=0;
		
		Point current; int x, y, nx, ny, dir, cnt;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			dir = current.dir;
			cnt = current.cnt;
			
			if(x==end.x && y==end.y) {
				answer = cnt;
				break;
			}
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0||ny<0||nx>H-1||ny>W-1) continue;
				if(map[nx][ny] == '*') continue;
				
				if(dir == i || dir == -1) {	// 방향 같은경우 (-1은 처음)
					if(visited[nx][ny] >= cnt) {
						visited[nx][ny] = cnt;
						queue.add(new Point(nx, ny, i, cnt));
					}
				}else {
					if(visited[nx][ny] >= cnt+1) {
						visited[nx][ny] = cnt+1;
						queue.add(new Point(nx, ny, i, cnt+1));
					}
				}
			}
		}
		
	}
	
	static class Point implements Comparable<Point> {
		int x, y, dir, cnt;
		public Point(int x, int y, int dir, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Point o) {
			return this.cnt-o.cnt;
		}
	}

}
