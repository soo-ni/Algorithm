/**
 * 23,300 kb
 * 132 ms
 * simul
 * 1h40m
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_D_5644_무선충전 {
	
	static int T, M, BC_N, ans;
	static ArrayList<Integer> moveA, moveB;
	static int[][] BC;
	static int[] dx = {0, -1, 0, 1, 0};	//가마니, 상 우 하 좌
	static int[] dy = {0, 0, 1, 0, -1};
	static Point A, B;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(st.nextToken());	//test case
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine()," ");
			M = Integer.parseInt(st.nextToken());	//이동 시간
			BC_N = Integer.parseInt(st.nextToken());	//BC 갯수
			moveA = new ArrayList<Integer>();	//A의 이동 위치
			moveB = new ArrayList<Integer>();	//B의 이동 위치
			A = new Point(1, 1);	//A의 현재 위치
			B = new Point(10, 10);	//B의 현재 위치
			BC = new int[BC_N][4];	//X, Y, C, P
			
			st = new StringTokenizer(br.readLine()," ");
			for(int i=0; i<M; i++) moveA.add(Integer.parseInt(st.nextToken()));
			st = new StringTokenizer(br.readLine()," ");
			for(int i=0; i<M; i++) moveB.add(Integer.parseInt(st.nextToken()));
			
			for(int i=0; i<BC_N; i++) {
				st = new StringTokenizer(br.readLine()," ");
				BC[i][1] = Integer.parseInt(st.nextToken());
				BC[i][0] = Integer.parseInt(st.nextToken());
				BC[i][2] = Integer.parseInt(st.nextToken());
				BC[i][3] = Integer.parseInt(st.nextToken());
			}
			
			ans=0;
			go();
			
			sb.append('#').append(t+1).append(' ').append(ans).append('\n');
		}	//test case end
		
		System.out.println(sb.toString());
	}
	
	
	private static void go() {
		int time=-1;
		
		int distanceA, distanceB, sum, charge, BC_A, BC_B; 
		ArrayList<Integer> selectedA = new ArrayList<Integer>();
		ArrayList<Integer> selectedB = new ArrayList<Integer>();
		while(time++<M) {
			//1. 초기 위치 부터 확인 
			selectedA.clear();	selectedB.clear();
			for(int i=0; i<BC_N; i++) {	//BC 개수 돌면서 distance 안에 들어오는지 확인
				distanceA = Math.abs(A.x-BC[i][0]) + Math.abs(A.y-BC[i][1]);
				distanceB = Math.abs(B.x-BC[i][0]) + Math.abs(B.y-BC[i][1]);
				
				if(distanceA<=BC[i][2]) selectedA.add(i);	//A에 넣기
				if(distanceB<=BC[i][2]) selectedB.add(i);	//B에 넣기
			}
			
			//2. 해당 안에 걸리는 것 중 최대값 확인
			sum=0;
			if(selectedA.size()==0 || selectedB.size()==0) {
				for(int i=0; i<selectedA.size(); i++) {
					BC_A = selectedA.get(i);	//a선택 기지국
					charge = BC[BC_A][3];
					sum = Math.max(sum, charge);
				}
				for(int i=0; i<selectedB.size(); i++) {
					BC_B = selectedB.get(i);	//b선택 기지국
					charge = BC[BC_B][3];
					sum = Math.max(sum, charge);
				}
			}else {
				for(int i=0; i<selectedA.size(); i++) {
					for(int j=0; j<selectedB.size(); j++) {
						BC_A = selectedA.get(i);	//a선택 기지국
						BC_B = selectedB.get(j);	//b선택 기지국
						
						if(BC_A==BC_B) {	//기지국 같은 경우
							charge = BC[BC_A][3];
						}else {	//같지 않은 경우
							charge = BC[BC_A][3] + BC[BC_B][3];
						}
						sum = Math.max(sum, charge);
					}
				}
			}
			ans+=sum;
			
			//3. A, B 이동시키기
			if(time==M) continue;
			A = new Point(A.x+dx[moveA.get(time)], A.y+dy[moveA.get(time)]);
			B = new Point(B.x+dx[moveB.get(time)], B.y+dy[moveB.get(time)]);
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
