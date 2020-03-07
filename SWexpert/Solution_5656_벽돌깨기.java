/**
* 73,240 kb
* 398 ms
*
*/

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_5656_벽돌깨기 {
	static int T, N, W, H, ans;
	static int[][] wall, temp_wall;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// 구슬횟수
			W = Integer.parseInt(st.nextToken());	// 열
			H = Integer.parseInt(st.nextToken());	// 행
			wall = new int[H][W];
			temp_wall = new int[H][W];		// 조합할때마다 초기화 필요
			visited = new boolean[H][W];		// 벽 부셔졌는지 확인

			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<W; j++) {
					wall[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}
			
			ans = Integer.MAX_VALUE;
			selected = new int[N];
			crushWall(0);	// idx
						
			System.out.println("#"+(t+1)+" "+ans);
			
		}	// test case
	}

	static int[] selected;
	private static void crushWall(int idx) {
		if(ans==0) return;
		if(idx==N) {
			removeWall();
			int sum=0;
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					if(temp_wall[i][j]>0) sum++; 
				}
			}
			
			if(sum<ans) ans=sum;
			return;
		}
		
		for(int i=0; i<W; i++) {
			selected[idx] = i;
			crushWall(idx+1);
		}
	}

	private static void removeWall() {
		int temp;
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				temp = wall[i][j];
				temp_wall[i][j] = temp;				
			}
		}
		
		LinkedList<Point> queue = new LinkedList<Point>();
		int col;		
		for(int idx=0; idx<N; idx++) {
			col = selected[idx];
			
			// 해당 column 맨 위 찾기
			for(int i=0; i<H; i++) {
				if(!visited[i][col] && temp_wall[i][col]>0) {
					visited[i][col] = true;
					queue.offer(new Point(i, col));
					break;
				}
			}
			
			// visited true로 만들기
			Point current; int x, y;
			while(!queue.isEmpty()) {
				current = queue.poll();
				int iter = temp_wall[current.x][current.y]-1;	// 반복 횟수
				for(int dir=0; dir<4; dir++) {
					x = current.x; y = current.y;
					for(int it=0; it<iter; it++) {
						x += dx[dir];
						y += dy[dir];
						
						if(x<0||y<0||x>=H||y>=W) continue;
						if(!visited[x][y] && temp_wall[x][y]>0) {
							visited[x][y] = true;
							queue.offer(new Point(x, y));
						}
					}
				}
			}
			
			// 방문했으면 0으로 만들고 visited 초기화
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					if(visited[i][j]) {
						temp_wall[i][j] = 0;
						visited[i][j] = false;
					}
				}
			}
			
			// 벽돌 내리기
			for(int j = W-1; j>=0; j--) {
	            		for(int i=H-1; i>=0; i--) {
	                		if(temp_wall[i][j] == 0) {
	                    			for(int anx = i-1; anx>=0; anx--) {
	                        			if(temp_wall[anx][j] == 0) continue;
	                        			temp_wall[i][j] = temp_wall[anx][j];
	                        			temp_wall[anx][j] = 0;
	                        			break;
	                    			}
	                		}
	            		}
	        	}
		}
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
