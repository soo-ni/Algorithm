/**
 * 63,276 kb, 414 ms
 * 18,708 kb, 125 ms
 * using BFS, DFS
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_D_1952_수영장 {

	static int T, ans;
	static int[] pay, month;
	static PriorityQueue<Point> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(st.nextToken());	//test case
		pay = new int[4];	//이용권
		month = new int[13];	//일수
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<4; i++) {
				pay[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=1; i<13; i++) {
				month[i] = Integer.parseInt(st.nextToken());
			}
			
			queue = new PriorityQueue<Point>();
			ans = Integer.MAX_VALUE;
//			bfs();
			dfs(1, 1, 0);
			ans = Math.min(ans, pay[3]);	//1년권이랑 마지막으로 비교
			
			sb.append('#').append(t+1).append(' ').append(ans).append('\n');
		}	//test case end
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int start, int end, int money) {
		if(end>12) {
			ans = Math.min(ans, money);
			return;
		}
		
		dfs(end, end+1, money+month[end]*pay[0]);
		dfs(end, end+1, money+pay[1]);
		dfs(end, end+3, money+pay[2]);
	}
	
	private static void bfs() {
		queue.add(new Point(1, 2, month[1]*pay[0]));	//1일권
		queue.add(new Point(1, 2, pay[1]));	//1개월권
		queue.add(new Point(1, 4, pay[2]));	//3개월권
		
		Point current; int start, end, money;
		while(!queue.isEmpty()) {
			current = queue.poll();
			start = current.start;	//이전 달
			end = current.end;		//이제 시작할 달
			money = current.money;	//현재 이용권금액
			
			if(end>12) {
				ans = Math.min(ans, money);
			}
			
			if(end<13) {
				queue.add(new Point(end, end+1, money+month[end]*pay[0]));	//1일권
				queue.add(new Point(end, end+1, money+pay[1]));	//1달권
				queue.add(new Point(end, end+3, money+pay[2]));	//3달권
			}
		}
	}

	static class Point implements Comparable<Point> {
		int start, end, money;
		public Point(int start, int end, int money) {
			super();
			this.start = start;
			this.end = end;
			this.money = money;
		}
		@Override
		public int compareTo(Point o) {
			return this.money-o.money;
		}
	}
}
