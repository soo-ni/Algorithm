/**
 * 13252 kb	
 * 100 ms
 * 완탐 
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_B_17837_새로운게임2 {

	static int N, K;
	static int[][] map;
	static ArrayList<Integer>[][] temp_map;
	static Point[] chess;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 행 열
		K = Integer.parseInt(st.nextToken());	// 말 개수
		map = new int[N][N];			// 체스판 
		temp_map = new ArrayList[N][N];	// 체스판에 놓인 말순서
		chess = new Point[K];			// 체스말 
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				temp_map[i][j] = new ArrayList<Integer>();
			}
		}
		
		int x, y, dir;
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken())-1;	// x좌표
			y = Integer.parseInt(st.nextToken())-1;	// y좌표
			dir = Integer.parseInt(st.nextToken())-1;	// 방향
			chess[i] = new Point(x, y, dir);
			temp_map[x][y].add(i);
		}
		
		int ans = getAns();
		System.out.println(ans);
		
	}
	
	static int[] dx = {0, 0, -1, 1};	// 우 좌 상 하
	static int[] dy = {1, -1, 0, 0};
	static ArrayList<Integer> arr = new ArrayList<Integer>();
	private static int getAns() {
		int t=0;
		int current, x, y, dir, nx, ny, len;
		while(t++<1000) {
			for(int chessNum=0; chessNum<K; chessNum++) {	// chess 수만큼 돌림
				x = chess[chessNum].x;
				y = chess[chessNum].y;
				dir = chess[chessNum].dir;
				
				nx = x+dx[dir];	// next row
				ny = y+dy[dir];	// next col
				
				// 1. 다음 칸이 파란색이거나 경계일 때
				if(nx<0||ny<0||nx>N-1||ny>N-1) {
					if(dir==0) 		dir=1;
					else if(dir==1) dir=0;
					else if(dir==2) dir=3;
					else if(dir==3) dir=2;
					nx = x+dx[dir];
					ny = y+dy[dir];
					chess[chessNum].dir=dir;
					
					if(map[nx][ny]>1) {	
						// 방향 바꿔서 한칸 앞으로 갔는데 파란색인 경우 방향만 바꿔주고 이동하지 않음
						continue;
					}
					
				}else if(map[nx][ny]>1) {
					if(dir==0) 		dir=1;
					else if(dir==1) dir=0;
					else if(dir==2) dir=3;
					else if(dir==3) dir=2;
					nx = x+dx[dir];
					ny = y+dy[dir];
					chess[chessNum].dir=dir;
					
					if(nx<0||ny<0||nx>N-1||ny>N-1||map[nx][ny]>1) {	
						// 방향 바꿔서 한칸 앞으로 갔는데 파란색이거나 경계인 경우 방향만 바꿔주고 이동하지 않음
						continue;
					}
				}
					
				// 2. 다음 칸이 빨간색일 때
				// 쌓여있는 높이에서 마지막까지 순서 바꿔주기
				len 	= temp_map[x][y].size();			// 현재 위치의 말 개수
				current = temp_map[x][y].indexOf(chessNum);	// 쌓여있다면 몇번째 높이
				
				int temp;
				if(map[nx][ny]==1) {
					arr.clear();
					for(int idx=len-1; idx>=current; idx--) {
						arr.add(temp_map[x][y].remove(idx));	// 거꾸로 넣어주기
					}
					temp_map[x][y].addAll(arr);
				}
				
				// 3. 옮겨주기
				// 순서 바꾼 혹은 흰색인 경우 순서 바뀌지 않은 temp_map[][] 애들 다음 위치로 
				// chess 정보 갱신
				// 이 때, 4개이상이면 return
				for(int idx=current; idx<len; idx++) {
					temp = temp_map[x][y].remove(current);
					chess[temp].x = nx;
					chess[temp].y = ny;
					temp_map[nx][ny].add(temp);
				}
				
				if(temp_map[nx][ny].size()>3) return t;
				
			}	// end chess
		}	// end while
		
		return -1;
	}

	static class Point {
		int x, y, dir;	// x, y, 방향, 쌓인순서
		public Point(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}

