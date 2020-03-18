/*
* 17016	kb
* 232 ms
* 답보고 한 코드
* dfs는 똑같은데 visited배열로 스도쿠 판 번호 관리
*
*/

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_2580_스도쿠_참고 {
	
	static boolean[][] visitedRow, visitedCol, visitedSquare;
	static int[][] map;
	
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[9][9];
		visitedRow = new boolean[9][10];	// 가로 확인
		visitedCol = new boolean[9][10];	// 세로 확인
		visitedSquare = new boolean[9][10];	// 정사각형 확인
		
		int temp;
		for(int i=0; i<9; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<9; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				
				if(temp>0) {
					visitedRow[i][temp] = true;
					visitedCol[j][temp] = true;
					visitedSquare[(i/3)*3+j/3][temp] = true;
				}
			}
		}
		
		token=false;
		sb = new StringBuilder();
		getMap(0);
		
		
	}

	static boolean token;
	private static void getMap(int idx) {
		int row = idx/9;
		int col = idx%9;
		
		if(idx==81) {
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					sb.append(map[i][j]).append(' ');
				}
				sb.append('\n');
			}
			
			System.out.println(sb.toString());
			token=true;
		}
		if(token) return;
		
		if(map[row][col]>0) {
			getMap(idx+1);
		}else {
			for(int i=1; i<10; i++) {
				if(!visitedRow[row][i] && !visitedCol[col][i] && !visitedSquare[(row/3)*3+col/3][i]) {
					visitedRow[row][i] = true;
					visitedCol[col][i] = true;
					visitedSquare[(row/3)*3+col/3][i] = true;
					map[row][col]=i;

					getMap(idx+1);
					
					map[row][col]=0;
					visitedRow[row][i] = false;
					visitedCol[col][i] = false;
					visitedSquare[(row/3)*3+col/3][i] = false;
				}
			}
		}
	}
}
