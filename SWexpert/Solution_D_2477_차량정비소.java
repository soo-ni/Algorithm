/**
 * 39,632 kb
 * 451 ms
 * 깡시뮬...
 * 3h...
 * 흠....ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Solution_D_2477_차량정비소 {
	
	static int TC, N, M, K, A, B, ans;
	static int[] timeA, timeB, timeK;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(st.nextToken());
		
		for(int tc=0; tc<TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	//접수창구
			M = Integer.parseInt(st.nextToken());	//정비창구
			K = Integer.parseInt(st.nextToken());	//고객수
			A = Integer.parseInt(st.nextToken());	//지갑잃은고객 접수창구번호
			B = Integer.parseInt(st.nextToken());	//지갑잃은고객 정비창구번호
			timeA = new int[N+1];	//접수창구 걸리는 시간
			timeB = new int[M+1];	//정비창구 걸리는 시간
			timeK = new int[K+1];	//도착 순서
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=1; i<N+1; i++) timeA[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=1; i<M+1; i++) timeB[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=1; i<K+1; i++) timeK[i] = Integer.parseInt(st.nextToken());
			
			Point[] userA = new Point[N+1];	//접수창구 사람
			Point[] userB = new Point[M+1];	//정비창구 사람
			
			PriorityQueue<Point> tableA = new PriorityQueue<Point>(new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					return o1.no-o2.no;
				}
			});
			PriorityQueue<Point> tableB = new PriorityQueue<Point>(new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int result = o1.start-o2.start;
					if(result==0) result=o1.a-o2.a;
					return result;
				}
			});
			
			ans=0;
			int cnt=0; int time=0; int idx=1;
			while(true) {
				if(cnt==K) break;	//모두 마쳤을 때
				for(int i=idx; i<K+1; i++) {	//도착순서와 같으면
					if(timeK[i]==time) {
						tableA.add(new Point(i, time, 0, 0));	//A번줄에 넣기
						idx=i;
					}
				}
				
				Point cur;
				for(int i=1; i<N+1; i++) {
					if(userA[i]!=null) {	//userA[i]에 사람이 있다면
						cur = userA[i];
						if((time-cur.start)==timeA[cur.a]) {	//현재 시간에서 시작한 시간이 다됐다면
							userA[i]=null;	//접수창구에서 빼서
							tableB.add(new Point(cur.no, time, cur.a, 0));	//정비창구로 넣어주기
						}
					}

					if(!tableA.isEmpty() && userA[i]==null) {	//userA[i]가 비어있다면
						cur = tableA.poll();
						userA[i] = new Point(cur.no, time, i, 0);	//tableA에서 꺼내서 고객 넣어주기
					}
				}
				
				for(int i=1; i<M+1; i++) {
					if(userB[i]!=null) {
						cur = userB[i];
						if((time-cur.start)==timeB[cur.b]) {	//현재 시간에서 시작한 시간이 다됐다면
							userB[i]=null;	//접수창구에서 빼서
							if(cur.a==A && cur.b==B) {	//어느 창구를 들렸는지 확인
								ans+=cur.no;
							}
							cnt++;	//빠져나온 고객
						}
					}
					
					if(!tableB.isEmpty() && userB[i]==null) {	//userB[i]가 비어있다면
						cur = tableB.poll();
						userB[i] = new Point(cur.no, time, cur.a, i);	//tableB에서 꺼내서 고객 넣어주기
					}
				}
				
				time++;
			}
			
			if(ans<1) ans=-1;
			sb.append('#').append(tc+1).append(' ').append(ans).append('\n');
		}	//test case end
		
		System.out.println(sb.toString());
	}
	
	static class Point {
		int no, start, a, b;
		public Point(int no, int start, int a, int b) {
			super();
			this.no = no;
			this.start = start;
			this.a = a;
			this.b = b;
		}
	}
}
