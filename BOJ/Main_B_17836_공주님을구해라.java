/**
 * 
 * 17960 kb	
 * 212 ms
 * using BFS
 * 
 */

package study_Dec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_17836_공주님을구해라 {
	
	static int N, M, T, answer;
	static int[][] map;
	static boolean[][][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][2];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = Integer.MAX_VALUE;
		bfs();
		
		if(answer>T) System.out.println("Fail");
		else System.out.println(answer);
		
	}

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static PriorityQueue<Point> queue;
	private static void bfs() {
		queue = new PriorityQueue<>();
		queue.add(new Point(0, 0, 0, 0));
		visited[0][0][0] = true;
		
		Point current; int x, y, nx, ny, cnt, gram;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			cnt = current.cnt;
			gram = current.gram;
			
			if(x==N-1 && y==M-1) {
				answer = Math.min(answer, cnt);
			}
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				if(nx<0||ny<0||nx>N-1||ny>M-1) continue;
				
				if(gram==0) {
					if(visited[nx][ny][gram]) continue;
					if(map[nx][ny]==1) continue;
					if(map[nx][ny]==2) {
						queue.add(new Point(nx, ny, cnt+1, 1));
						visited[nx][ny][1] = true;
					}else {
						queue.add(new Point(nx, ny, cnt+1, gram));
						visited[nx][ny][gram] = true;
					}
				}else {
					if(visited[nx][ny][gram]) continue;
					queue.add(new Point(nx, ny, cnt+1, gram));
					visited[nx][ny][gram] = true;
				}
			}
		}
	}

	static class Point implements Comparable<Point> {
		int x, y, cnt, gram;
		public Point(int x, int y, int cnt, int gram) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.gram = gram;
		}
		@Override
		public int compareTo(Point o) {
			return this.cnt-o.cnt;
		}
	}
}
