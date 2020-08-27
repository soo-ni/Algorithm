package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 13704 kb	
 * 100 ms
 * @author soo-ni
 *
 */
public class Main_2234_성곽 {

	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());	// 열
		N = Integer.parseInt(st.nextToken());	// 행
		
		int[][] map = new int[N][M];	// map
		int[][] room = new int[N][M];	// room 구분
		ArrayList<Integer> roomNum = new ArrayList<Integer>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int roomCnt=1;
		int maxRoom=0, temp;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(room[i][j]==0) {
					temp = bfs(i, j, map, room, roomCnt);
					maxRoom = Math.max(maxRoom, temp);
					roomNum.add(temp);
					roomCnt++;
				}
			}
		}
		
		int here=0, nx, ny;
		int maxRoomC=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				here=room[i][j];
				
				for(int dir=0; dir<4; dir++) {
					nx = i+dx[dir];
					ny = j+dy[dir];
					if(nx<0||ny<0||nx>N-1||ny>M-1) continue;
					if(room[nx][ny]!=here) {
						maxRoomC = Math.max(maxRoomC, roomNum.get(here-1)+roomNum.get(room[nx][ny]-1));
					}
				}
			}
		}
		
		System.out.println(roomCnt-1);
		System.out.println(maxRoom);
		System.out.println(maxRoomC);
	}
	
	static int[] dx = {0, -1, 0, 1};	// 서 북 동 남
	static int[] dy = {-1, 0, 1, 0};
	private static int bfs(int row, int col, int[][] map, int[][] room, int roomCnt) {
		LinkedList<Point> queue = new LinkedList<Point>();
		queue.add(new Point(row, col));
		room[row][col]=roomCnt;
		
		int cnt=1;
		Point current;	int x, y, nx, ny;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0||ny<0||nx>N-1||ny>M-1) continue;
				
				if((map[x][y]&(1<<i))>0) continue;
				if(room[nx][ny]==0) {
					queue.add(new Point(nx, ny));
					room[nx][ny]=roomCnt;
					cnt++;
				}
			}
		}
		
		return cnt;
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
