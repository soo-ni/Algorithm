/**
* 21388	kb
* 352 ms
* CCTV의 방향을 설정할 수 있는 모든 경우의 수 고려(CCTV 최대 개수<9: 가능)
* 
*/

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_B_15683_감시 {
	
	static int N, M, ans, CCTV_num;
	static int[][] map, temp_map;
	static int[] numbers;
	static ArrayList<CCTV> cctv;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 세로
		M = Integer.parseInt(st.nextToken());	// 가로
		map = new int[N][M];	// map
		temp_map = new int[N][M];	// 구역 계산 위한 temp map
		numbers = new int[N];	// n번째 CCTV의 방향
		cctv = new ArrayList<CCTV>();
		
		
		int c;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				c = Integer.parseInt(st.nextToken());
				map[i][j] = c;
				if(c>0 && c<6) {
					cctv.add(new CCTV(i, j, c, 0));	// 행, 열, 번호, 방향
				}
			}
		}
		
		ans = Integer.MAX_VALUE;
		CCTV_num = cctv.size();	// CCTV의 개수
		getDirection(0);	// n번째 CCTV의 방향 정하기, idx
		
		if(ans==Integer.MAX_VALUE) {
			ans=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j]<1) ans++;
				}
			}
		}
		System.out.println(ans);
	}

	private static void getDirection(int idx) {
		if(ans==0) return;
		if(idx==CCTV_num) {	// 모든 CCTV의 방향 정해지면
			// temp_map 초기화
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					temp_map[i][j] = map[i][j];
				}
			}
			
			// temp_map 돌면서 CCTV 종류에따른 temp_map 갱신
			for(int i=0; i<CCTV_num; i++) {
				getZone(i);
			}
			
			// ans 가져오기
			int temp_ans = 0; 
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(temp_map[i][j]<1) temp_ans++;
				}
			}
			if(temp_ans<ans) ans = temp_ans;
			
			return;
		}
		
		for(int i=0; i<4; i++) {
			CCTV c = new CCTV(cctv.get(idx).x, cctv.get(idx).y, cctv.get(idx).num, i);
			cctv.set(idx, c);
			getDirection(idx+1);
		}
	}

	static int[] dx = {0, 1, 0, -1};	// 우 하 좌 상
	static int[] dy = {1, 0, -1, 0};
	private static void getZone(int idx) {
		CCTV current = cctv.get(idx);
		int row = current.x;
		int col = current.y;
		int num = current.num;	// CCTV 종류
		int dir = current.dir;
		
		int x, y;
		switch(num) {
		case 1:
			x=row; y=col;
			while(temp_map[x][y]!=6) {
				if(temp_map[x][y]<1) temp_map[x][y]=7;
				x+=dx[dir];
				y+=dy[dir];
				if(x<0||y<0||x>=N||y>=M) break;
			}
			break;
			
		case 2:	// 2방향(180도)
			for(int i=0; i<4; i++) {
				if(dir%2==0) {
					if(i%2>0) continue;
				}else {
					if(i%2==0) continue;
				}
				x=row; y=col;
				while(temp_map[x][y]!=6) {
					if(temp_map[x][y]<1) temp_map[x][y]=7;
					x+=dx[i];
					y+=dy[i];
					if(x<0||y<0||x>=N||y>=M) break;
				}
			}
			break;
			
		case 3:	// 2방향(직각)
			int start=dir;
			int end=dir+1;
			if(dir+1>3) {
				start=0;end=dir;
			}
			for(int i=start; i<=end; i++) {
				if(dir+1>3) {
					if(i==1||i==2) continue;
				}
				x=row; y=col;
				while(temp_map[x][y]!=6) {
					if(temp_map[x][y]<1) temp_map[x][y]=7;
					x+=dx[i];
					y+=dy[i];
					if(x<0||y<0||x>=N||y>=M) break;
				}
			}
			break;
		
		case 4:	// 3방향
			for(int i=0; i<4; i++) {
				if(i==dir) continue;
				x=row; y=col;
				while(temp_map[x][y]!=6) {
					if(temp_map[x][y]<1) temp_map[x][y]=7;
					x+=dx[i];
					y+=dy[i];
					if(x<0||y<0||x>=N||y>=M) break;
				}
			}
			break;
			
		case 5:	// 4방향
			for(int i=0; i<4; i++) {
				x=row; y=col;
				while(temp_map[x][y]!=6) {
					if(temp_map[x][y]<1) temp_map[x][y]=7;
					x+=dx[i];
					y+=dy[i];
					if(x<0||y<0||x>=N||y>=M) break;
				}
			}
			break;
		}
	}
	
	static class CCTV {
		int x, y, num, dir;
		public CCTV(int x, int y, int num, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}
	}
}
