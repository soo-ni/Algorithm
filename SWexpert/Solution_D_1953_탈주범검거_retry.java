/**
 * 24,992 kb
 * 136 ms
 * using BFS
 * 방향 전환 잘보기!
 * 
 */

package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_D_1953_탈주범검거 {
	static int T, N, M, R, C, L, ans;
	static int[][] map;
	static boolean[][] visited;
	static LinkedList<Point> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());	//test case
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	//row
			M = Integer.parseInt(st.nextToken());	//col
			R = Integer.parseInt(st.nextToken());	//start row
			C = Integer.parseInt(st.nextToken());	//start col
			L = Integer.parseInt(st.nextToken());	//time
			map = new int[N][M];	//map
			visited = new boolean[N][M];	//visited check
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = 1;
			queue = new LinkedList<Point>();
			queue.add(new Point(R, C));
			visited[R][C]=true;
			for(int i=0; i<L-1; i++) {
				bfs();
			}
		
			sb.append('#').append(t+1).append(' ').append(ans).append('\n');
		}	//test case end
		
		System.out.println(sb.toString());
	}
	
	static int[] dx = {-1, 0, 1, 0};	//상우하좌
	static int[] dy = {0, 1, 0, -1};
	private static void bfs() {
		int size = queue.size();
		
		Point current; int x, y, nx, ny, dir, ndir;
		boolean[] here, next;
		while(size-->0) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			dir = map[x][y];	//현재 파이프 방향
			here = go(dir);		//현재 파이프 방향이 볼 수 있는 방향

			for(int i=0; i<4; i++) {
				if(here[i]) continue;	//내가 볼 수 없다면 continue
				
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0||ny<0||nx>N-1||ny>M-1) continue;
				if(visited[nx][ny]) continue;

				ndir = map[nx][ny];	//다음 파이프의 방향
				if(ndir<1) continue;	//0이면 continue
				
				next = go(ndir);
				if(!next[(i+2)%4]) {	//다음 방향을 볼 수 있어야함
					queue.add(new Point(nx, ny));
					visited[nx][ny]=true;
					ans++;
				}
			}
		}
		
	}

	private static boolean[] go(int dir) {	// 볼 수 없는 방향 true
		boolean[] next = new boolean[4];
		switch(dir) {
		case 2:	next[1]=true; next[3]=true;	break;
		case 3: next[0]=true; next[2]=true; break;
		case 4: next[2]=true; next[3]=true; break;
		case 5: next[0]=true; next[3]=true; break;
		case 6: next[0]=true; next[1]=true; break;
		case 7: next[1]=true; next[2]=true; break;
		}
		return next;
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
