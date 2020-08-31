package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 33700 kb
 * 496 ms
 * 그냥 때려박기
 * @author soo-ni
 *
 */
public class Main_B_14500_테트로미노 {

	static int[][][] tetromino = {
			{{0,0},{0,1},{0,2},{0,3}}, {{0,0},{1,0},{2,0},{3,0}}, {{0,0},{0,1},{1,0},{1,1}},	// 123
			{{0,0},{1,0},{2,0},{2,1}}, {{0,0},{0,1},{0,2},{1,0}}, {{0,0},{1,0},{1,1},{1,2}},	// 456
			{{0,0},{1,0},{2,0},{0,1}}, {{0,0},{0,1},{-1,1},{-2,1}}, {{0,0},{0,1},{0,2},{-1,2}},	// 789
			{{0,0},{0,1},{0,2},{1,2}}, {{0,0},{0,1},{1,1},{2,1}}, {{0,0},{1,0},{1,1},{2,1}},	// 101112
			{{0,0},{-1,0},{-1,1},{-2,1}}, {{0,0},{0,1},{-1,1},{-1,2}}, {{0,0},{0,1},{1,1},{1,2}},	// 131415
			{{0,0},{1,0},{1,1},{2,0}}, {{0,0},{0,1},{-1,1},{1,1}}, {{0,0},{0,1},{0,2},{-1,1}},	// 161718
			{{0,0},{0,1},{0,2},{1,1}}
	};
	static int R, C;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());	// 행
		C = Integer.parseInt(st.nextToken());	// 열
		map = new int[R][C];	// 숫자
		
		int temp;
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
			}
		}
		
		int answer = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				temp = go(i, j);
				answer = Math.max(answer, temp);
			}
		}
		
		System.out.println(answer);
	}

	private static int go(int x, int y) {
		int nx, ny, ans=0, sum;
		int lengthT = tetromino.length;
		for(int i=0; i<lengthT; i++) {
			sum=0;
			for(int j=0; j<4; j++) {
				nx = x+tetromino[i][j][0];
				ny = y+tetromino[i][j][1];
				
				if(nx<0||ny<0||nx>R-1||ny>C-1) {
					sum=0;
					break;
				}
				sum += map[nx][ny];
			}
			ans = Math.max(ans, sum);
		}
		
		return ans;
	}
}
