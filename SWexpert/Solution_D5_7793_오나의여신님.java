/**
* 23,972 kb
* 109 ms
*
*/

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Point {
	int x, y;
	Point(int x, int y){
		this.x=x; this.y=y;
	}
}
public class Solution_D5_7793_오나의여신님 {
	
	static int T, N, M, ans;
	static boolean token;
	static char[][] map, temp_map;
	static boolean[][] visited, visited_d;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static LinkedList<Point> queue, queue_d;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// row
			M = Integer.parseInt(st.nextToken());	// col
			map = new char[N][M];					// map
			visited = new boolean[N][M];			// 수연 visited check
			visited_d = new boolean[N][M];			// 악마 visited check
			queue = new LinkedList<Point>();		// 수연이 옮기기
			queue_d = new LinkedList<Point>();		// 악마 옮기기
			
			String s; cnt=0; cnt_d=0;
			for(int i=0; i<N; i++) {
				s = br.readLine();
				for(int j=0; j<M; j++) {
					map[i][j] = s.charAt(j);

					if(map[i][j]=='S') {
						cnt++;
						visited[i][j]=true;
						queue.offer(new Point(i, j));
					}
					
					if(map[i][j]=='*') {
						cnt_d++;
						visited_d[i][j]=true;
						queue_d.offer(new Point(i, j));
					}
				}
			}			
			
			ans = 0;
			token = true;
			while(true) {	
				devil();	
				if(!token) break;
				
				god();
				if(!token) break;
			}			
			
			if(ans<0) {
				System.out.println("#"+(t+1)+" GAME OVER");
			}else {
				System.out.println("#"+(t+1)+" "+ans);
			}
			
		}	// test case
		
	}

	static int cnt_d;	// 악마가 이동할 수 있는 위치 갯수
	private static void devil() {
		Point current; 
		int num=0;
		while(cnt_d-->0) {
			current = queue_d.poll();
			
			int x, y;
			for(int i=0; i<4; i++) {
				x = current.x+dx[i];
				y = current.y+dy[i];
				
				if(x<0||y<0||x>=N||y>=M) {
					continue;
				}
				
				if(map[x][y]=='S') {
					if(cnt==0) {
						token = false;
						ans = -1;
						return;
					}
				}
				
				if(!visited_d[x][y] && map[x][y]!='X' && map[x][y]!='D') {
					num++;
					map[x][y] = '*';
					visited_d[x][y] = true;
					queue_d.offer(new Point(x, y));
				}
			}				
		}
		cnt_d=num;
	}

	static int cnt;	// 수연이가 이동할 수 있는 위치 갯수
	public static void god() {	
		Point current;
		int num=0;
		while(cnt-->0) {
			current = queue.poll();
			
			int x, y;
			for(int i=0; i<4; i++) {
				x = current.x+dx[i];
				y = current.y+dy[i];
				
				if(x<0||y<0||x>=N||y>=M) {
					continue;
				}
				
				if(map[x][y]=='D') {
					token = false;
					ans++;
					return;
				}		
				
				if(map[x][y]=='.' && !visited[x][y]) {
					num++;
					map[x][y]='S';
					visited[x][y]=true;
					queue.offer(new Point(x, y));
				}
			}
		}
		cnt=num;
		if(cnt==0) {
			token = false;
			ans = -1;
			return;
		}
		ans++;
	}
}
