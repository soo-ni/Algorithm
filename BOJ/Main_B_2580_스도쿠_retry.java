/**
* 19764	kb
* 504 ms
* DFS 돌림!~
*
*/

package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_2580_스도쿠_retry {
	
	static int[][] map;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[9][9];	// 스도쿠 판
		
		int temp;
		for(int i=0; i<9; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<9; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
			}
		}
		
		token=false;
		sb = new StringBuilder();
		getNumber(0);	// idx
		System.out.println(sb.toString());
	}
	
	static boolean token;
	private static void getNumber(int idx) {
		if(token) {
			return;
		}
		if(idx==81) {	// 전체 확인
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					sb.append(map[i][j]).append(' ');
				}
				sb.append('\n');
			}
			
			token=true;
			return;
		}
		
		int row = idx/9;
		int col = idx%9;
		
		if(map[row][col]>0) {
			getNumber(idx+1);
		}else {
			for(int i=1; i<10; i++) {
				if(checkRow(row, col, i) && checkCol(row, col, i) && checkSquare(row, col, i)) {
					map[row][col] = i;
					getNumber(idx+1);
					map[row][col] = 0;
				}
			}
		}
	}
	private static boolean checkSquare(int row, int col, int num) {
		// 사각형 검사
		for(int i=(row/3)*3; i<(row/3)*3+3; i++) {
			for(int j=(col/3)*3; j<(col/3)*3+3; j++) {
				if(i==row && j==col) continue;
				if(map[i][j]==num) return false;
			}
		}
		return true;
	}
	
	private static boolean checkCol(int row, int col, int num) {
		// 세로줄 검사
		for(int i=0; i<9; i++) {
			if(i==row) continue;
			if(map[i][col]==num) return false;
		}
		return true;
	}
	
	private static boolean checkRow(int row, int col, int num) {
		// 가로줄 검사
		for(int i=0; i<9; i++) {
			if(i==col) continue;
			if(map[row][i]==num) return false;
		}
		return true;
	}
}
