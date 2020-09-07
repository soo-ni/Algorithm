/**
 * 
 * 360480 kb
 * 1212 ms
 * bfs
 * 
 */

package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_14442_벽부수고이동하기2 {

	static int N, M, K, answer;
	static boolean[][][] visited;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][K+1];
		
		String s;
		for(int i=0; i<N; i++) {
			s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		
		answer = Integer.MAX_VALUE;
		bfs();
		if(answer==Integer.MAX_VALUE) answer=-1;
		System.out.println(answer);
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static void bfs() {
		LinkedList<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0, 1, 0));
		visited[0][0][0]=true;
		
		Point current; int x, y, nx, ny, cnt, crash;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			cnt = current.cnt;
			crash = current.crash;
			
			if(x==N-1 && y==M-1) {
				answer = Math.min(answer, cnt);
				continue;
			}
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0||ny<0||nx>N-1||ny>M-1) continue;
				
				if(map[nx][ny]==1) {	//막혔을 때
					if(crash<K) {
						if(visited[nx][ny][crash+1]) continue;
						visited[nx][ny][crash+1]=true;
						queue.add(new Point(nx, ny, cnt+1, crash+1));
					}
				}else {
					if(visited[nx][ny][crash]) continue;
					visited[nx][ny][crash]=true;
					queue.add(new Point(nx, ny, cnt+1, crash));
				}
			}
		}
		
	}

	static public class Point {
		int x, y, cnt, crash;
		public Point(int x, int y, int cnt, int crash) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.crash = crash;
		}
	}
}
