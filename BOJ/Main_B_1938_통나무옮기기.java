/**
 * 
 * 13556 kb	
 * 100 ms
 * bfs
 * 
 */

package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main_B_1938_통나무옮기기 {
	
	static int N, answer;
	static char[][] map;
	static boolean[][][] visited;
	static Point start, end;
	static int[] dx = {-1, 0, 1, 0, 0};	// 상좌하우 회전
	static int[] dy = {0, -1, 0, 1, 0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// NxN
		map = new char[N][N];
		visited = new boolean[N][N][2];
		
		String s; char c;
		for(int i=0; i<N; i++) {
			s = br.readLine();
			for(int j=0; j<N; j++) {
				c = s.charAt(j);
				map[i][j] = c;
				
				if(c=='B') start = new Point(i, j, 0, 0);
				if(c=='E') end = new Point(i, j, 0, 0);
			}
		}
		
		int nx, ny, nnx, nny;
		for(int i=0; i<2; i++) {
			nx = start.x+dx[i];
			ny = start.y+dy[i];
			nnx = end.x+dx[i];
			nny = end.y+dy[i];
			if(isRange(nx, ny) && map[nx][ny]=='B') {
				if(i==0) start = new Point(nx, ny, 0, 0);
				else start = new Point(nx, ny, 1, 0);
			}
			if(isRange(nnx, nny) && map[nnx][nny]=='E') {
				if(i==0) end = new Point(nnx, nny, 0, 0);
				else end = new Point(nnx, nny, 1, 0);
			}
		}
		
		answer = Integer.MAX_VALUE;
		bfs();
		if(answer==Integer.MAX_VALUE) answer=0;
		System.out.println(answer);
	}
	
	private static void bfs() {
		LinkedList<Point> queue = new LinkedList<>();
		queue.add(start);
		visited[start.x][start.y][start.dir]=true;
		
		Point current; int x, y, nx, ny, dir, cnt; 
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			dir = current.dir;
			cnt = current.cnt;
			
			if(x==end.x && y==end.y) {
				answer = Math.min(answer, cnt);
			}
			
			for(int i=0; i<5; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(i<4) {
					if((dir%2) != (i%2)) {
						if((dir%2) == 0) {	// 세로
							if(!checkLR(x, y, i, dir)) continue;	// visited && 1이 있으면
						}else {	// 가로
							if(!checkUD(x, y, i, dir)) continue;	// visited && 1이 있으면
						}
					}else {
						if(visited[nx][ny][dir]) continue;			// 회전아닐때는 그냥 확인
						if(!isRange(nx+dx[i], ny+dy[i])) continue;
						if(map[nx+dx[i]][ny+dy[i]]=='1') continue;
					}
					queue.add(new Point(nx, ny, dir, cnt+1));
					visited[nx][ny][dir]=true;
					
				}else {
					int ddir = (dir+1)%2;
					if(visited[nx+dx[i]][ny+dy[i]][ddir]) continue;	// 회전때는 돌린적이 있는지 확인
					if(isSquare(nx, ny)) {
						queue.add(new Point(nx, ny, ddir, cnt+1));
						visited[nx][ny][ddir]=true;
					}
				}
				
			}
		}
		
	}
	
	private static boolean checkLR(int row, int col, int ndir, int dir) {
		for(int i=row-1; i<row+2; i++) {
			if(!isRange(i, col+dy[ndir])) return false;
			if(i==row) {
				if(visited[i][col+dy[ndir]][dir]) return false;
			}
			if(map[i][col+dy[ndir]]=='1') return false;
		}
		return true;
	}
	
	private static boolean checkUD(int row, int col, int ndir, int dir) {
		for(int i=col-1; i<col+2; i++) {
			if(!isRange(row+dx[ndir], i)) return false;
			if(i==col) {
				if(visited[row+dx[ndir]][i][dir]) return false;
			}
			if(map[row+dx[ndir]][i]=='1') return false;
		}
		return true;
	}

	private static boolean isSquare(int row, int col) {
		for(int i=row-1; i<row+2; i++) {
			for(int j=col-1; j<col+2; j++) {
				if(!isRange(i, j)) return false;
				if(map[i][j]=='1') return false;
			}
		}
		
		return true;
	}
	
	private static boolean isRange(int row, int col) {
		if(row<0||col<0||row>N-1||col>N-1) return false;
		return true;
	}

	static class Point {
		int x, y, dir, cnt;
		public Point(int x, int y, int dir, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}
	}
}
