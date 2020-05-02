/**
 * 18600 kb	
 * 180 ms
 * ...깡구현...
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_B_17140_이차원배열과연산 {

	static int r, c, k, ans, rowMax, colMax;
	static int[][] A;
	static boolean token;
	static ArrayList<Point>[] AR, AC;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken())-1;	// 행
		c = Integer.parseInt(st.nextToken())-1;	// 열
		k = Integer.parseInt(st.nextToken());	// 숫자
		A = new int[100][100];
		AR = new ArrayList[100];
		AC = new ArrayList[100];
		for(int i=0; i<100; i++) {
			AR[i] = new ArrayList<Point>();
			AC[i] = new ArrayList<Point>();
		}
		
		for(int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0;
		token = false;
		while(true) {
			getArray();	// 연산

			check();	// 일단 체크, A초기화
			if(token) break;	// 맞으면 ans return
			
			if(rowMax>=colMax) R();
			else C();
			
			ans++;
			if(ans>100) {
				ans=-1; break;
			}
			
		}
		
		System.out.println(ans);
	}
	
	private static void getArray() {
		rowMax=0; colMax=0;
		for(int i=0; i<100; i++) {
			AR[i].clear();
			AC[i].clear();
		}
		
		int temp, len; boolean tokenR, tokenC;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				temp = A[i][j];
				if(temp<1) continue;
				tokenR = false;
				tokenC = false;
				
				if(AR[i].isEmpty()) {
					AR[i].add(new Point(temp, 1));
				}else {
					len = AR[i].size(); 
					for(int m=0; m<len; m++) {
						if(AR[i].get(m).x==temp && !tokenR) {
							AR[i].set(m, new Point(AR[i].get(m).x, AR[i].get(m).y+1));
							tokenR=true;
						}
					}
					if(!tokenR){
						AR[i].add(new Point(temp, 1));
					}
				}
				rowMax = Math.max(rowMax, i);
				
				if(AC[j].isEmpty()) {
					AC[j].add(new Point(temp, 1));
				}else {
					len = AC[j].size();
					for(int m=0; m<len; m++) {
						if(AC[j].get(m).x==temp && !tokenC) {
							AC[j].set(m, new Point(AC[j].get(m).x, AC[j].get(m).y+1));
							tokenC = true;
						}
					}
					if(!tokenC){
						AC[j].add(new Point(temp, 1));
					}
				}
				colMax = Math.max(colMax, j);
           }
		}
	}

	private static void R() {
		for(int i=0; i<100; i++) {
			Collections.sort(AR[i]);
		}
		
		int idx;
		for(int i=0; i<100; i++) {
			if(AR[i].isEmpty()) break;
			idx = 0;
			for(Point p: AR[i]) {
				A[i][idx++] = p.x;
				A[i][idx++] = p.y;
			}
		}
	}

	private static void C() {
		for(int i=0; i<100; i++) {
			Collections.sort(AC[i]);
		}
		
		int idx;
		for(int i=0; i<100; i++) {
			if(AC[i].isEmpty()) break;
			idx = 0;
			for(Point p: AC[i]) {
				A[idx++][i] = p.x;
				A[idx++][i] = p.y;
			}
		}
	}

	private static void check() {
		if(A[r][c]==k) {
			token=true; return;
		}
		for(int i=0; i<100; i++) {
			Arrays.fill(A[i], 0);
		}
	}

	static class Point implements Comparable<Point> {
		int x, y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Point o) {
			int results = this.y-o.y;
			if(results==0) results=this.x-o.x;
			return results;
		}
	}
	
	
}
