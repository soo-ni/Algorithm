/**
 * 
 * 20848 kb	
 * 248 ms
 * using DFS (스도쿠문제랑 똑같음)
 * 
 */
package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_17136_색종이붙이기 {
	
	static int[][] map;
	static int answer, N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[10][10];
		N = 0;	// 1의 갯수
		
		int temp;
		for(int i=0; i<10; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<10; j++) {
				temp = Integer.parseInt(st.nextToken());
				if(temp>0) N++;
				map[i][j] = temp;
			}
		}
		
		answer = Integer.MAX_VALUE;
		int[] paper = new int[6];
		dfs(0, paper);
		
		if(answer==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
		
	}
	
	private static boolean check(int row, int col, int width) {
		for(int i=row; i<row+width; i++) {
			for(int j=col; j<col+width; j++) {
				if(i<0||i>9||j<0||j>9) return false;
				if(map[i][j]!=1) return false;
			}
		}
		return true;
	}
	
	private static void changeMap(int row, int col, int width, int result) {
		for(int i=row; i<row+width; i++) {
			for(int j=col; j<col+width; j++) {
				map[i][j] = result;
			}
		}
	}
	
	private static void dfs(int idx, int[] paper) {
		if(idx==100) {
			int cnt = 0;
			for(int i=1; i<6; i++) {
				cnt += paper[i];
			}
			answer = Math.min(cnt, answer);
			
			return;
		}
		
		int row = idx/10;
		int col = idx%10;
		if(map[row][col]!=1) {
			dfs(idx+1, paper);
		}else {
			for(int i=1; i<6; i++) {
				if(paper[i]<5 && check(row, col, i)) {
					changeMap(row, col, i, 2);
					paper[i]+=1;
					dfs(idx+1, paper);
					paper[i]-=1;
					changeMap(row, col, i, 1);
				}
			}
			
		}
	}
	
}
