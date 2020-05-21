/**
 * 18024 kb	
 * 172 ms
 * 50m
 * using Dijkstra
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_6593_상범빌딩 {

	static final int INF = Integer.MAX_VALUE;
	static int L, R, C, len, row, col;
	static int[][][] dijkstra;
	static char[][][] building;
	static PriorityQueue<Point> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		building = new char[30][30][30];
		dijkstra = new int[30][30][30];
		
		while(true) {
			st = new StringTokenizer(br.readLine(), " ");
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			if(L<1 && R<1 && C<1) break;
			
			for(int k=0; k<L; k++) {
				for(int i=0; i<R; i++) {
					for(int j=0; j<C; j++) {
						dijkstra[k][i][j]=INF;
					}
				}
			}
			
			queue = new PriorityQueue<Point>();
			String s; char c;
			for(int k=0; k<L; k++) {
				for(int i=0; i<R; i++) {
					s = br.readLine();
					for(int j=0; j<C; j++) {
						c = s.charAt(j);
						building[k][i][j] = c;
						
						if(c=='S') {
							dijkstra[k][i][j]=0;
							queue.add(new Point(i, j, k, 0));
						}
						
						if(c=='E') {
							len=k; row=i; col=j;
						}
					}
				}
				br.readLine();	// 빈 줄
			}
			
			go();
			if(dijkstra[len][row][col]==INF) sb.append("Trapped!\n");
			else sb.append("Escaped in ").append(dijkstra[len][row][col]).append(" minute(s).\n");
			
		}
		
		System.out.println(sb.toString());
	}
	
	static int[] dx = {-1, 1, 0, 0, 0, 0};
	static int[] dy = {0, 0, -1, 1, 0, 0};
	static int[] dz = {0, 0, 0, 0, -1, 1};
	private static void go() {
		Point current; int x, y, z, cnt, nx, ny, nz;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			z = current.z;
			cnt = current.cnt;
			
			for(int i=0; i<6; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				nz = z+dz[i];
				
				if(nx<0||ny<0||nz<0||nx>R-1||ny>C-1||nz>L-1) continue;
				if(building[nz][nx][ny]=='#') continue;
				
				if(dijkstra[nz][nx][ny]>cnt+1) {
					dijkstra[nz][nx][ny]=cnt+1;
					queue.add(new Point(nx, ny, nz, cnt+1));
				}
			}
			
		}
		
	}

	static class Point implements Comparable<Point> {
		int x, y, z, cnt;
		public Point(int x, int y, int z, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Point o) {
			return this.cnt-o.cnt;
		}
	}
}
