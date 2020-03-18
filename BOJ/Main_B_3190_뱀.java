/**
 * 13244 kb	
 * 88 ms
 * 문제이해ㅠ dir 바꿔주는거ㅠ 몬일이야~!!
 *
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_B_3190_뱀 {
	
	static int N, K, L, ans;
	static Point[] direction;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());	// NxN
		K = Integer.parseInt(br.readLine());	// 사과 갯수
		map = new int[N][N];	// map
		int x, y;
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken())-1;
			y = Integer.parseInt(st.nextToken())-1;
			map[x][y] = 1;	// 사과 있으면 1
		}
		
		L = Integer.parseInt(br.readLine());	// 방향전환 갯수
		direction = new Point[L];	// 방향 명령
		char c;
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			c = st.nextToken().charAt(0); // 방향 전환 오른쪽이면 +1, 왼쪽이면 -1
			if(c=='D') y=1;	
			else y=-1;
			direction[i] = new Point(x, y); 
		}
		
		ans=0;
		getGame();
		System.out.println(ans);
	}
	
	static int[] dx = {-1, 0, 1, 0};	// 상 우 하 좌
	static int[] dy = {0, 1, 0, -1};
	static ArrayList<Point> snake;
	private static void getGame() {
		snake = new ArrayList<Point>();
		snake.add(new Point(0, 0));	// 처음 시작 좌표
		
		Point cur_dir; 
		int row=0, col=0, length=snake.size(), dir=1;		// 뱀 길이, 초기 방향 1(우측)
		int t=0, cnt=0;
		boolean token;
		while(true) {
			t++;
			token=false;
			row+=dx[dir];
			col+=dy[dir];
			
			if(row<0||col<0||row>=N||col>=N) {	// 벽 만나면 GG
				ans=t;
				return;
			}
			
			for(int j=0; j<length; j++) {	// 자기 자신 만나면 GG
				if(row==snake.get(j).x && col==snake.get(j).y) {
					ans=t;
					return;
				}
			}
			
			snake.add(new Point(row, col));	// 머리 움직이기
			
			if(map[row][col]==1) {	// 사과 먹기
				map[row][col]=0;	// 사과 없애기
				length++;			// 몸길이 늘리기
				token=true;			// 꼬리 안지움
			}
			
			if(!token) snake.remove(0);	// 꼬리 지우기
			
			if(cnt>=L) continue;
			cur_dir = direction[cnt];
			if(t==cur_dir.x) {
				dir += cur_dir.y;
				if(dir<0) dir+=4;
				if(dir>3) dir=0;
				cnt++;
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
