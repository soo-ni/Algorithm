/**
 * 
 * 21456 kb
 * 248 ms
 * 시뮬
 * 
 */
package study_Dec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_19237_어른상어 {

	static int N, M, K, cnt;
	static int[][][] map, direction;
	static Shark[] sharks;
	static int[] dx = {-1, 1, 0, 0};	// 상 하 좌 우
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 격자
		M = Integer.parseInt(st.nextToken());	// 상어 수
		K = Integer.parseInt(st.nextToken());	// 이동
		cnt = M;	// 상어 수 줄이기
		map = new int[N][N][2];	// 0: 상어번호, 1: 남은 이동수
		direction = new int[M][4][4];	// 상어번호, 우선순위 방향
		sharks = new Shark[M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j][0] = -1;
			}
		}
		
		int temp;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j][0] = temp-1;
				
				if(temp>0) {
					map[i][j][1] = K;
					sharks[temp-1] = new Shark(i, j, 0);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			sharks[i] = new Shark(sharks[i].x, sharks[i].y, Integer.parseInt(st.nextToken())-1);
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<4; j++) {
				st = new StringTokenizer(br.readLine());
				direction[i][j][0] = Integer.parseInt(st.nextToken())-1;
				direction[i][j][1] = Integer.parseInt(st.nextToken())-1;
				direction[i][j][2] = Integer.parseInt(st.nextToken())-1;
				direction[i][j][3] = Integer.parseInt(st.nextToken())-1;
			}
		}
		
		int t=0; boolean flag=false;
		while(t++<1000) {
			// 이동하기 + 냄새 뿌리기
			move();
			// 일단 냄새 줄이기
			smell();

			if(cnt==1) {
				flag = true;
				break;
			}
		}
		
		if(!flag) System.out.println(-1);
		else System.out.println(t);
	}
	
	private static void smell() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j][1]>1) {
					map[i][j][1]--;
				}else if(map[i][j][1] == 1) {
					map[i][j][0]=-1;
					map[i][j][1]--;
				}
			}
		}
		Shark current; int x, y;
		for(int i=0; i<M; i++) {
			current = sharks[i];
			x = current.x;
			y = current.y;
			if(x<0) continue;
			map[x][y][1]=K;
		}
	}

	private static void move() {
		int[][] temp = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				temp[i][j] = map[i][j][0];
			}
		}
		
		Shark current; int x, y, nx, ny, d, nd; boolean flag, wrap;
		for(int i=0; i<M; i++) {
			current = sharks[i];
			x = current.x;
			y = current.y;
			d = current.d;
			if(x<0) continue;
			
			flag=false; wrap=false;
			for(int dir=0; dir<4; dir++) {
				nd = direction[i][d][dir];
				nx = x+dx[nd];
				ny = y+dy[nd];
				if(nx<0||ny<0||nx>N-1||ny>N-1) continue;
				if(map[nx][ny][0]<0) {
					map[nx][ny][0] = i;
					map[nx][ny][1] = K;
					sharks[i] = new Shark(nx, ny, nd);
					flag=true;
					break;
				}
				if(temp[nx][ny]<0 && map[nx][ny][1]==K) {
					wrap=true;
					break;
				}
			}
			
			if(flag) continue;
			if(wrap) {
				sharks[i] = new Shark(-1, -1, -1);
				cnt--;	// 겹침
				continue;
			}
			
			for(int dir=0; dir<4; dir++) {
				nd = direction[i][d][dir];
				nx = x+dx[nd];
				ny = y+dy[nd];
				if(nx<0||ny<0||nx>N-1||ny>N-1) continue;
				if(map[nx][ny][0]==i) {
					map[nx][ny][0] = i;
					map[nx][ny][1] = K;
					sharks[i] = new Shark(nx, ny, direction[i][d][dir]);
					flag=true;
					break;
				}
			}
			if(!flag) {
				sharks[i] = new Shark(-1, -1, -1);
				cnt--;	// 갈데 없음 !
			}
		}
	}

	static class Shark {
		int x, y, d;
		public Shark(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
}
