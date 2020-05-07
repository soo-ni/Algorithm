/**
 * 13440 kb	
 * 94 ms
 * using BFS 
 * 
 */

package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_16236_아기상어 {

	static int N, row, col, size, distance, eat;
	static int[][] map;
	static PriorityQueue<Point> queue;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// row, col
		map = new int[N][N];	// map
		queue = new PriorityQueue<Point>();	// for move
		visited = new boolean[N][N];
		
		int temp;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				temp = Integer.parseInt(st.nextToken());
				if(temp==9) {
					row=i; col=j; size=2; distance=0;	// 아기상어 초기위치
					queue.add(new Point(i, j, 0, 0));
					visited[i][j]=true;
					continue;
				}
				map[i][j] = temp;
			}
		}
		
		move();
		
		System.out.println(distance);
	}
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	private static void move() {
		Point current; int x, y, z, dis, eat=0, nx, ny;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			z = current.z;
			dis = current.dis;
			
			if(z>0 && z<size) {
//				System.out.println(x+" "+y+" "+dis);
				row=x; col=y;
				map[x][y]=0;
				distance+=dis;
				eat++;
				if(eat==size) {
					size++; eat=0; 
				}
				
				queue.clear();
				queue.add(new Point(row, col, 0, 0));
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						visited[i][j]=false;
					}
				}
				visited[row][col]=true;
				continue;	// continue 안해주면 distance누적이 아닌 그냥 distance=dis; 하면됨
			}
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				if(nx<0||ny<0||nx>N-1||ny>N-1) continue;
				
				if(!visited[nx][ny] && map[nx][ny]<=size) {
					queue.add(new Point(nx, ny, map[nx][ny], dis+1));
					visited[nx][ny]=true;
				}
			}
		}
		
	}

	static class Point implements Comparable<Point> {
		int x, y, z, dis;	// row, col, size, dis
		public Point(int x, int y, int z, int dis) {	
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.dis = dis;
		}
		@Override
		public int compareTo(Point o) {
			int results = this.dis-o.dis;
			if(results==0) results=this.x-o.x;
			if(results==0) results=this.y-o.y;
			return results;
		}
	}
}
