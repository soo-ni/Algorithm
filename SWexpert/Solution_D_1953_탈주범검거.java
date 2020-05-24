/**
 * 25,372 kb
 * 131 ms
 * using BFS + 시뮬
 * 50m
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_D_1953_탈주범검거 {

	static int T, N, M, R, C, L;
	static int[][] map;
	static boolean[][] visited;
	static LinkedList<Point> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(st.nextToken());	//test case
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	//세로
			M = Integer.parseInt(st.nextToken());	//가로
			R = Integer.parseInt(st.nextToken());	//맨홀초기 세로위치
			C = Integer.parseInt(st.nextToken());	//맨홀초기 가로위치
			L = Integer.parseInt(st.nextToken());	//소요시간
			map = new int[N][M];	//터널구조
			visited = new boolean[N][M];	//방문체크
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<M; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			queue = new LinkedList<Point>();
			queue.add(new Point(R, C, map[R][C]));
			visited[R][C]=true;
			int size=0;
			for(int i=0; i<L-1; i++) {
				size = queue.size();
				bfs(size);
			}
			
			int ans=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(visited[i][j]) ans++;
				}
			}
			
			sb.append('#').append(t+1).append(' ').append(ans).append('\n');
		}	//test case end
		
		System.out.println(sb.toString());
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void bfs(int size) {
		Point current; int x, y, dir;
		while(size-->0) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			dir = current.dir;
			
			switch(dir) {
				case 1: up(x, y); down(x, y); left(x, y); right(x, y); break;
				case 2: up(x, y); down(x, y); break;
				case 3: left(x, y); right(x, y); break;
				case 4: up(x, y); right(x, y); break;
				case 5: down(x, y); right(x, y); break;
				case 6: left(x, y); down(x, y); break;
				case 7: up(x, y); left(x, y); break;
			}
		}
	}

	private static void right(int x, int y) {
		int nx, ny, dir;
		nx = x+dx[3];
		ny = y+dy[3];
		
		if(nx<0||ny<0||nx>N-1||ny>M-1) return;
		if(visited[nx][ny]) return;
		
		dir = map[nx][ny];
		
		if(dir==1 || dir==3 || dir==6 || dir==7) {
			queue.add(new Point(nx, ny, dir));
			visited[nx][ny]=true;
		}
	}

	private static void left(int x, int y) {
		int nx, ny, dir;
		nx = x+dx[2];
		ny = y+dy[2];
		
		if(nx<0||ny<0||nx>N-1||ny>M-1) return;
		if(visited[nx][ny]) return;
		
		dir = map[nx][ny];
		
		if(dir==1 || dir==3 || dir==4 || dir==5) {
			queue.add(new Point(nx, ny, dir));
			visited[nx][ny]=true;
		}
	}

	private static void down(int x, int y) {
		int nx, ny, dir;
		nx = x+dx[1];
		ny = y+dy[1];
		
		if(nx<0||ny<0||nx>N-1||ny>M-1) return;
		if(visited[nx][ny]) return;
		
		dir = map[nx][ny];
		
		if(dir==1 || dir==2 || dir==4 || dir==7) {
			queue.add(new Point(nx, ny, dir));
			visited[nx][ny]=true;
		}
	}

	private static void up(int x, int y) {
		int nx, ny, dir;
		nx = x+dx[0];
		ny = y+dy[0];
		
		if(nx<0||ny<0||nx>N-1||ny>M-1) return;
		if(visited[nx][ny]) return;
		
		dir = map[nx][ny];
		
		if(dir==1 || dir==2 || dir==5 || dir==6) {
			queue.add(new Point(nx, ny, dir));
			visited[nx][ny]=true;
		}
	}

	static class Point {
		int x, y, dir;
		public Point(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}
