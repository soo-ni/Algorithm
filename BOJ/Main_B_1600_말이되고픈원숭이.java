/**
 * 62944kb 1012ms	// PriorityQueue
 * 90752kb 616ms	// LinkedList
 * using BFS
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_1600_말이되고픈원숭이 {
	
	static int K, W, H, ans;
	static boolean token;
	static int[][] map;
	static boolean[][][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		K = Integer.parseInt(st.nextToken());	// 뛸 수 있는 횟수
		st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());	// col
		H = Integer.parseInt(st.nextToken());	// row
		map = new int[H][W];	// map
		visited = new boolean[H][W][K+1];	// visited check
		
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans=0; token=false;
		bfs();
		
		if(!token) ans=-1;
		System.out.println(ans);
		
	}

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[] dx_h = {-2, -2, -1, -1, 1, 1, 2, 2};
	static int[] dy_h = {-1, 1, -2, 2, -2, 2, -1, 1};
	private static void bfs() {
		LinkedList<Point> queue = new LinkedList<Point>();
		queue.add(new Point(0, 0, 0, 0));
		visited[0][0][0] = true;
		
		Point current; int nx, ny, x, y, cnt, k;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			cnt = current.cnt;
			k = current.k;
			
			if(x==H-1 && y==W-1) {
				token=true;
				ans=cnt;
				return;
			}
			
			if(k<K) {	// 말처럼 뛰는게 가능할 때
				for(int i=0; i<4; i++) {	// 그냥 상하좌우 움직일 때
					nx = x+dx[i];
					ny = y+dy[i];
					if(nx<0||ny<0||nx>H-1||ny>W-1) continue;
					
					if(!visited[nx][ny][k] && map[nx][ny]<1) {
						queue.add(new Point(nx, ny, cnt+1, k));
						visited[nx][ny][k]=true;
					}
				}
				
				for(int i=0; i<8; i++) {	// 말처럼 뛸 때
					nx = x+dx_h[i];
					ny = y+dy_h[i];
					if(nx<0||ny<0||nx>H-1||ny>W-1) continue;
					
					if(!visited[nx][ny][k+1] && map[nx][ny]<1) {
						queue.add(new Point(nx, ny, cnt+1, k+1));
						visited[nx][ny][k+1]=true;
					}
				}
				
			}else {	// 말처럼 안뛸때
				for(int i=0; i<4; i++) {	// 그냥 상하좌우 움직일 때
					nx = x+dx[i];
					ny = y+dy[i];
					if(nx<0||ny<0||nx>H-1||ny>W-1) continue;
					
					if(!visited[nx][ny][k] && map[nx][ny]<1) {
						queue.add(new Point(nx, ny, cnt+1, k));
						visited[nx][ny][k]=true;
					}
				}
			}
		}
		
	}

	static class Point {
		int x, y, cnt, k;
		public Point(int x, int y, int cnt, int k) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.k = k;
		}
	}
}
