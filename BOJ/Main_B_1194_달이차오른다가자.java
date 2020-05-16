/**
 * 16212 kb	
 * 140 ms
 * using BFS, Bitmasking
 * 
 */

package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_1194_달이차오른다가자 {
	
	static int N, M, ans;
	static boolean[][][] visited;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// row
		M = Integer.parseInt(st.nextToken());	// col
		map = new char[N][M];	// map
		visited = new boolean[N][M][1<<6];	// visited check
		queue = new PriorityQueue<Point>();
		
		String s; char c;
		for(int i=0; i<N; i++) {
			s = br.readLine();
			for(int j=0; j<M; j++) {
				c = s.charAt(j);
				map[i][j] = c;
				
				if(c=='0') {
					queue.add(new Point(i, j, 0, 0));
					visited[i][j][0] = true;
					map[i][j] = '.';
				}
			}
		}
		
		ans=-1;
		bfs();
		
		System.out.println(ans);
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static PriorityQueue<Point> queue;
	private static void bfs() {
		Point current; int x, y, cnt, nx, ny, key; 
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			cnt = current.cnt;
			
			if(map[x][y]=='1') {
				ans = cnt;
				return;
			}
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				key = current.key;
				
				if(nx<0||ny<0||nx>N-1||ny>M-1) continue;
				if(visited[nx][ny][key]) continue;
				
				if(map[nx][ny]=='.' || map[nx][ny]=='1') {
					queue.add(new Point(nx, ny, cnt+1, key));
					visited[nx][ny][key]=true;
				}
				
				if(map[nx][ny]>='a' && map[nx][ny]<='f') {
					key |= (1<<map[nx][ny]-'a');
					queue.add(new Point(nx, ny, cnt+1, key));
					visited[nx][ny][key]=true;
				}
				
				if(map[nx][ny]>='A' && map[nx][ny]<='F') {
					if((key&(1<<(map[nx][ny]-'A')))!=0) {
						queue.add(new Point(nx, ny, cnt+1, key));
						visited[nx][ny][key]=true;
					}
				}
			}
		}
		
	}

	static class Point implements Comparable<Point> {
		int x, y, cnt, key; 
		public Point(int x, int y, int cnt, int key) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.key = key;
		}
		@Override
		public int compareTo(Point o) {
			return this.cnt-o.cnt;
		}
	}
}
