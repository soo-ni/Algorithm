/**
 * 13020 kb	
 * 80 ms
 * 다시풀기 ㅠ 
 *
 */

package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_6987_월드컵 {
	
	static int[][] play;
	static Point[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		play = new int[6][3];
		list = new Point[15];
		int idx=0;
		for(int i=0; i<6; i++) {
			for(int j=i; j<6; j++) {
				if(i==j) continue;
				list[idx++]=new Point(i, j);
			}
		}
		
		boolean check; int temp1, temp2, temp3, win, lose, draw;
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<6; j++) Arrays.fill(play[j], 0);
			
			token=false; check=false; win=0; draw=0; lose=0; 
			for(int j=0; j<6; j++) {
				temp1 = Integer.parseInt(st.nextToken());
				temp2 = Integer.parseInt(st.nextToken());
				temp3 = Integer.parseInt(st.nextToken());
				win+=temp1;
				draw+=temp2;
				lose+=temp3;
				
				play[j][0] = temp1;
				play[j][1] = temp2;
				play[j][2] = temp3;
				
				if(temp1+temp2+temp3!=5) check=true;
			}
			
			dfs(0, win, draw, lose);
			if(token && !check) sb.append(1+" ");
			else sb.append(0+" ");
		}
		
		System.out.println(sb.toString());
		
	}
	
	static boolean token;
	private static void dfs(int idx, int win, int draw, int lose) {
		if(token) return;
		if(idx==15) {
			token=true; return;
		}
		
		int A=list[idx].x;
		int B=list[idx].y;
		
		if(win>0) {
			if(play[A][2]>0 && play[B][0]>0) {
				play[A][2]--; play[B][0]--;
				dfs(idx+1, win-1, draw, lose-1);
				play[A][2]++; play[B][0]++;
			}
		}
		
		if(lose>0) {
			if(play[A][0]>0 && play[B][2]>0) {
				play[A][0]--; play[B][2]--;
				dfs(idx+1, win-1, draw, lose-1);
				play[A][0]++; play[B][2]++;
			}
		}
		
		if(draw>0) {
			if(play[A][1]>0 && play[B][1]>0) {
				play[A][1]--; play[B][1]--;
				dfs(idx+1, win, draw-2, lose);
				play[A][1]++; play[B][1]++;
			}
		}
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

