/**
 * 39024 kb	
 * 488 ms
 * 
 */

package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_3025_돌던지기 {

	static int R, C, N;
	static char[][] map;
	static int[] command;
	static int[][] next;
	static int[] col;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		R = Integer.parseInt(st.nextToken());	// row
		C = Integer.parseInt(st.nextToken());	// col
		map = new char[R][C];	// map
		next = new int[R][C];	// C로 던졌을 때 R높이인 경우의 y좌표
		col = new int[C];	// 장애물 위치
		Arrays.fill(col, R-1);
		
		String s; char c;
		for(int i=0; i<R; i++) {
			s = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		for(int i=0; i<C; i++) {
			next[0][i] = i;
			col[i] = 1;
			go(i);
		}
		
		N = Integer.parseInt(br.readLine());
		command = new int[N];	// command
		for(int i=0; i<N; i++) {
			command[i] = Integer.parseInt(br.readLine())-1;	// col
		}
		
		int current;
		for(int i=0; i<N; i++) {
			current = command[i];
			map[col[current]-1][next[col[current]-1][current]] = 'O';
			
			for(int j=0; j<C; j++) {
				go(j);
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
	}
	
	private static void go(int current) {
		int ny;
		while(true) {
			ny = next[col[current]-1][current];
			
			if(col[current]>1 && map[col[current]-1][ny]!='.') {	// 화산탄 쌓인 다음 맵 갱신
				col[current]-=1; 
				continue;
			}
			
			if(col[current]==R) break;	// 바닥 만나는 경우
			if(map[col[current]][ny]=='X') break;	// 벽 만나는 경우
			if(map[col[current]][ny]=='.') {	// 빈 공간인 경우
				next[col[current]++][current] = ny;	// 다음 행도 여전히 똑같은 열에서 가능
			}else {	// 화산탄 만나는 경우
				if(ny>0 && map[col[current]][ny-1]=='.' && map[col[current]-1][ny-1]=='.') {	// 좌하, 좌 빈 경우
					next[col[current]++][current] = ny-1;	// 다음 행은 좌측열으로
				}else if(ny+1<C && map[col[current]][ny+1]=='.' && map[col[current]-1][ny+1]=='.') {	// 우하, 우 빈 경우
					next[col[current]++][current] = ny+1;	// 다음 행은 우측열으로
				}else {	// 비어있지 않다면
					break;
				}
			}
			
		}
	}

}
