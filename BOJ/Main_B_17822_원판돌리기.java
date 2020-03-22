/**
 * 31584 kb 424 ms
 * 23980 kb 192 ms	
 * using bfs
 * => 시간 단축: visited 이용해서 번호 지우는 위치 
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_17822_원판돌리기 {
	
	static int[][] circle;
	static boolean[][] visited;
	static int[][] rotate;
	static int N, M, T, ans, cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 원판 개수
		M = Integer.parseInt(st.nextToken());	// 원판에 적힌 숫자의 개수
		T = Integer.parseInt(st.nextToken());	// 회전 수
		circle = new int[N+1][M];	// 원판
		visited = new boolean[N+1][M];	// 방문 체크
		rotate = new int[T][3];	// 회전
		
		ans=0; cnt=N*M;
		int temp;
		for(int i=1; i<N+1; i++) {	// 원판의 시작 숫자: 1
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				temp = Integer.parseInt(st.nextToken());	// (i, j)번째 원판에 적힌 수
				circle[i][j] = temp;
				ans += temp;
			}
		}
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			rotate[t][0] = Integer.parseInt(st.nextToken());	// 배수
			rotate[t][1] = Integer.parseInt(st.nextToken());	// 방향
			rotate[t][2] = Integer.parseInt(st.nextToken());	// 회전칸
		}

		double avg;	
		for(int t=0; t<T; t++) {
			if(ans<1) continue;
			token=false;	// 숫자가 지워졌는지 확인
			
			// 1. update map
			updateMap(rotate[t][0], rotate[t][1], rotate[t][2]);
			
			// 2. remove number using bfs
			for(int i=1; i<N+1; i++) {
				for(int j=0; j<M; j++) {
					temp = circle[i][j];
					if(temp>0 && !visited[i][j]) {
						queue.add(new Point(i, j));
						bfs(temp);
					}
				}
			}
			
			if(token) {
				removeMap();
				continue;
			}
			
			// 3. 하나도 안지워졌으면 update map using avg
			avg = ans*(1.0)/cnt*(1.0);
			ans = 0;
			for(int i=1; i<N+1; i++) {
				for(int j=0; j<M; j++) {
					temp = circle[i][j];
					
					if(temp<1) continue;
					if(temp<avg) {
						circle[i][j]++;
					}
					if(temp>avg) {
						circle[i][j]--;
					}
					
					ans+=circle[i][j];
				}
			}
		}
			
		System.out.println(ans);
	}

	private static void removeMap() {
		for(int i=1; i<N+1; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j]) {
					circle[i][j]=0;
					visited[i][j]=false;	// visited 초기화
				}
			}
		}
	}

	static boolean token;
	static LinkedList<Point> queue = new LinkedList<Point>();
	static int[] dx = {-1, 1, 0, 0};	// 안쪽, 바깥쪽, 반시계, 시계
	static int[] dy = {0, 0, -1, 1};	
	private static void bfs(int num) {
		int temp_cnt=1, x, y ,s_x, s_y;
		s_x=queue.peek().x; s_y=queue.peek().y;
		visited[s_x][s_y]=true;

		Point current;
		while(!queue.isEmpty()) {
			current = queue.poll();
			
			for(int i=0; i<4; i++) {
				x = current.x;
				y = current.y;
				while(true) {
					x+=dx[i];
					y+=dy[i];
					if(x>N||x<1) break;
					if(y>M-1) y=0;
					if(y<0) y=M-1;
					
					if(!visited[x][y] && circle[x][y]==num) {
						queue.add(new Point(x, y));
						visited[x][y]=true;
						temp_cnt++;
					}else {
						break;
					}
				}
			}
		}
		
		if(temp_cnt>1) {
			token=true;
			cnt-=temp_cnt;
			ans-=temp_cnt*num;
		}else {
			visited[s_x][s_y]=false;
		}
	}

	private static void updateMap(int xi, int di, int ki) {
		for(int i=1; i<N+1; i++) {
			if(i%xi<1) {	// xi의 배수인 원판 찾기
				int last;
				
				if(di<1) {	// 시계 방향인 경우
					for(int k=0; k<ki; k++) {
						last = circle[i][M-1];
						for(int j=M-1; j>0; j--) {
							circle[i][j] = circle[i][j-1];
						}
						circle[i][0] = last;
					}
					
				}else {		// 반시계 방향인 경우
					for(int k=0; k<ki; k++) {
						last = circle[i][0];
						for(int j=0; j<M-1; j++) {
							circle[i][j] = circle[i][j+1];
						}
						circle[i][M-1] = last;
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
