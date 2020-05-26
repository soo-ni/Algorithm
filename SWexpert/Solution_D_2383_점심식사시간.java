/**
 * 25,200 kb
 * 144 ms
 * using DFS and simul
 * 50m
 * 차량정비소랑 비슷!
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_D_2383_점심식사시간 {

	static int TC, N, P, ans;
	static ArrayList<Point> person, stair;
	static int[] selected;
	static Point[] S1, S2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(st.nextToken());
		
		for(int tc=0; tc<TC; tc++) {
			N = Integer.parseInt(br.readLine());	//map 크기
			person = new ArrayList<Point>();	//사람 위치
			stair = new ArrayList<Point>();	//계단 위치
			
			int temp;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					temp = Integer.parseInt(st.nextToken());
					
					if(temp<1) continue;
					if(temp==1) {	//사람일 때
						person.add(new Point(i, j, 0));
					}
					if(temp>1) {	//계단일 때
						stair.add(new Point(i, j, temp));
					}
				}
			}
			
			P = person.size();	//사람 수
			selected = new int[P];	//어느 계단으로 갈지
			S1 = new Point[3];	//계단에 있는 사람 최대3명
			S2 = new Point[3];	//계단에 있는 사람 최대3명
			ans = Integer.MAX_VALUE;
			dfs(0);	//idx
			
			sb.append('#').append(tc+1).append(' ').append(ans).append('\n');
		}
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int idx) {
		if(idx==P) {
			int sum = go();
			ans = Math.min(ans, sum);
			return;
		}
		
		for(int i=0; i<2; i++) {
			selected[idx]=i;
			dfs(idx+1);
		}
	}

	private static int go() {
		Point[] line = new Point[P];
		LinkedList<Point> Q_S1 = new LinkedList<Point>();
		LinkedList<Point> Q_S2 = new LinkedList<Point>();
		
		int num, dis, time=Integer.MAX_VALUE;
		for(int i=0; i<P; i++) {
			num = selected[i];
			dis = Math.abs(person.get(i).x-stair.get(num).x) + Math.abs(person.get(i).y-stair.get(num).y);	//도착시간
			time = Math.min(time, dis);
			line[i] = new Point(num, num, dis);	//계단 번호, 계단 번호, 시간
		}
		
		Arrays.sort(line, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return o1.start-o2.start;
			}
		});
		
		Point current; int cnt=0, idx=0;
		while(true) {
			if(cnt==P) break;	//모든 사람이 빠져나갔을 때
			for(int i=idx; i<P; i++) {	//사람 계단에 줄세우기
				if(line[i].start==time) {	//시간에 다다르면
					if(line[i].x<1) {
						Q_S1.add(line[i]);	//1번 계단
					}else {	
						Q_S2.add(line[i]);	//2번 계단
					}
				}else {
					idx=i; break;
				}
			}
			
			for(int i=0; i<3; i++) {	//1번 계단 3명 확인
				if(S1[i]!=null) {
					current = S1[i];
					if((time-current.start)==stair.get(0).start) {	//다 내려갔다면
						S1[i] = null;
						cnt++;
					}
				}
				
				if(!Q_S1.isEmpty() && S1[i]==null) {	//계단 비어있으면
					current = Q_S1.poll();
					S1[i] = new Point(current.x, current.y, time);
				}
			}
			
			for(int i=0; i<3; i++) {	//2번 계단 3명 확인
				if(S2[i]!=null) {
					current = S2[i];
					if((time-current.start)==stair.get(1).start) {	//다 내려갔다면
						S2[i] = null;
						cnt++;
					}
				}
				
				if(!Q_S2.isEmpty() && S2[i]==null) {	//계단 비어있으면
					current = Q_S2.poll();
					S2[i] = new Point(current.x, current.y, time);
				}
			}
			
			time++;
		}
		
		return time;
	}

	static class Point {
		int x, y, start;
		public Point(int x, int y, int start) {
			super();
			this.x = x;
			this.y = y;
			this.start = start;
		}
	}
}
