package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 14280 kb
 * 136 ms
 * 시뮬? 다시 확인해보기
 * @author soo-ni
 *
 */
public class Main_B_14499_주사위굴리기 {
	
	static int R, C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());	// 행
		C = Integer.parseInt(st.nextToken());	// 열
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		Point start = new Point(x, y);	// 시작 점
		int K = Integer.parseInt(st.nextToken());	// command 개수
		
		int[] dice = new int[6];	// 주사위
		int[][] map = new int[R][C];	// 지도

		int temp;
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
			}
		}
		
		LinkedList<Integer> command = new LinkedList<>();	// 주사위 이동
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			temp = Integer.parseInt(st.nextToken());
			command.add(temp-1);
		}
		
		ArrayList<Integer> answer = go(map, start, command, dice);
		for(int i: answer) System.out.println(i);
	}

	static int[] dx = {0, 0, -1, 1};	//동 서 북 남
	static int[] dy = {1, -1, 0, 0};
	private static ArrayList<Integer> go(int[][] map, Point current, LinkedList<Integer> command, int[] dice) {
		int dir, x, y, nx, ny; 
		ArrayList<Integer> answer = new ArrayList<>();
		while(!command.isEmpty()) {
			dir = command.poll();	// 굴릴 방향
			x = current.x;
			y = current.y;

			nx = x+dx[dir];
			ny = y+dy[dir];
			if(nx<0||ny<0||nx>R-1||ny>C-1) continue;	// 넘어가면 무시하기
			
			dice = rotate(dir, dice); // 주사위 굴리기
			if(map[nx][ny]>0) {	// 바닥이 0이 아닐 때
				dice[5] = map[nx][ny];
				map[nx][ny] = 0;
			}else {	// 바닥이 0일 때
				map[nx][ny] = dice[5];
			}
			current = new Point(nx, ny);
			answer.add(dice[0]);	// 주사위 윗면
		}
		
		return answer;
	}

	private static int[] rotate(int dir, int[] dice) {
		int[] temp = new int[6];
		for(int i=0; i<6; i++) {
			temp[i] = dice[i];
		}
		
		switch(dir) {
		case 0:
			temp[0]=dice[3];
			temp[2]=dice[0];
			temp[5]=dice[2];
			temp[3]=dice[5];
			break;
		case 1:
			temp[0]=dice[2];
			temp[2]=dice[5];
			temp[5]=dice[3];
			temp[3]=dice[0];
			break;
		case 2:
			temp[0]=dice[4];
			temp[1]=dice[0];
			temp[5]=dice[1];
			temp[4]=dice[5];
			break;
		case 3:
			temp[0]=dice[1];
			temp[1]=dice[5];
			temp[5]=dice[4];
			temp[4]=dice[0];
			break;
		}
		
		for(int i=0; i<6; i++) {
			dice[i] = temp[i];
		}
		
		return dice;
	}

	public static class Point {
		int x, y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
