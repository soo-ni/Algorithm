/**
* 39,524 kb
* 194 ms
*
*/

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Solution_D4_1868_파핑파핑지뢰찾기 {
	
	static int T, N, ans;
	static char[][] map;
	static boolean[][] visited;
	static LinkedList<Point>queue = new LinkedList<Point>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];	// map
			visited = new boolean[N][N];	// bfs 체크
			
			String s; char c;
			for(int i=0; i<N; i++) {
				s = br.readLine();
				for(int j=0; j<N; j++) {
					c = s.charAt(j); 
					map[i][j] = c;
					
					if(c=='*') visited[i][j]=true;
				}
			}
			
			ans = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(checkBomb(i, j)==0 && !visited[i][j]) {
						queue.offer(new Point(i, j));
						visited[i][j]=true;
						ans++;
						getBomb();
					}
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j]) ans++;
				}
			}
			
			System.out.println("#"+(t+1)+" "+ans);
		}	// test case
		
	}
	
	private static void getBomb() {
		Point current;
		while(!queue.isEmpty()) {
			current = queue.poll();
			
			int x, y;
			for(int i=0; i<8; i++) {
				x = current.x+dx[i];
				y = current.y+dy[i];
				
				if(x<0||y<0||x>=N||y>=N) continue;
				if(!visited[x][y]) {
					visited[x][y]=true;
					
					if(checkBomb(x, y)==0) {
						queue.offer(new Point(x, y));
					}
				}
				
			} // 8방탐색
			
		}	// while
	}

	static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};	// 8방향 확인
	static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
	private static int checkBomb(int row, int col) {
		int x, y, sum=0;
		for(int i=0; i<8; i++) {
			x = row+dx[i];
			y = col+dy[i];
			
			if(x<0||y<0||x>=N||y>=N) continue;
			if(map[x][y]=='*') sum++;
		}
		
		return sum;
	}

	static class Point {
		int x, y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
