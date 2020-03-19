/**
* 18480 KB
* 176 ms
*
*/

package study_0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Zelda implements Comparable<Zelda>{
	int x, y, rupee;
	Zelda(int x, int y, int rupee){
		this.x=x; this.y=y; this.rupee=rupee;
	}
	@Override
	public int compareTo(Zelda o) {
		return this.rupee-o.rupee;
	}
}

public class Main_B_4485_녹색옷입은애가젤다지 {
	
	static int T, N, min;
	static int[][] cave;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int num=1;
		while(true) {						
			N = Integer.parseInt(br.readLine());
			if(N==0) break;
			
			cave = new int[N+2][N+2];
			visited = new boolean[N+2][N+2];
			for(int i=0; i<N+2; i++)
				Arrays.fill(cave[i], -1);
			
			for(int i=1; i<N+1; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j=1; j<N+1; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
					visited[i][j] = true;
				}
			}
			
			min = 0;
			findPath(1, 1);
			
			sb.append("Problem ").append(num++).append(": ").append(min).append('\n');
		}	// test case
		
		System.out.println(sb.toString());
	}

	private static void findPath(int row, int col) {
		PriorityQueue<Zelda> queue = new PriorityQueue<Zelda>();
		queue.offer(new Zelda(row, col, cave[row][col]));
		visited[row][col]=false;
		
		Zelda current;
		while(!queue.isEmpty()) {
			current = queue.poll();
			if(current.x==N && current.y==N) {
				min = current.rupee;
				break;
			}
					
			int x, y;
			for(int i=0; i<4; i++) {
				x = current.x + dx[i];
				y = current.y + dy[i];
				
				if(visited[x][y]) {
					queue.offer(new Zelda(x, y, current.rupee+cave[x][y]));
					visited[x][y]=false;
				}
			}
			
		}
		
		
	}
}
