/**
*
* 122456 KB
* 600 ms
*
*/

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_7576_토마토 {

	static int N, M, day, que_size, temp;
	static int[][] box;
	static boolean[][] visited;
	static Queue<Point> queue = new LinkedList<Point>();
	static int[][] direction = {
			{1, 0}, {-1, 0}, {0, 1}, {0, -1}
	};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		box = new int[N+2][M+2];
		visited = new boolean[N+2][M+2];
		for(int i=0; i<N+2; i++)
			Arrays.fill(box[i], -1);		
				
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<M+1; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if(box[i][j]==1) {
					queue.offer(new Point(i, j));
					visited[i][j] = true;					
				}
			}
		}
		
		day = 0;
		temp = 0;
		que_size = queue.size();
		getTomato();
		
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<M+1; j++) {
				if(box[i][j]==0) {
					day=0;
					break;
				}
			}
		}
		
		System.out.println(day-1);
		
	}

	private static void getTomato() {
		Point p = new Point();
		int x, y;
		while(!queue.isEmpty()) {
			p = queue.poll();
			que_size--;
			for(int i=0; i<4; i++) {
				x = p.x+direction[i][0];
				y = p.y+direction[i][1];
				
				if(box[x][y]==0 && visited[x][y]==false) {
					queue.offer(new Point(x, y));
					box[x][y] = 1;
					visited[x][y] = true;
					temp++;
				}
			}
			
			if(que_size==0) {
				day++;
				que_size=temp;
				temp=0;
			}
		}
	}
	

}
