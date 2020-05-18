/**
 * 14072 kb	
 * 92 ms
 * using DFS
 * 흠 .. 다시풀기
 * 
 */

package study_May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_B_15997_승부예측2 {

	static HashMap<String, Integer> player;
	static ArrayList<Point> score;
	static StringBuilder sb;
	static double[] percent, ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		sb = new StringBuilder();
		player = new HashMap<String, Integer>();	// 국가 이름
		score = new ArrayList<Point>();	// 경기
		percent = new double[4];	// 승률
		ans = new double[4];	// 최종 확률
		
		for(int i=0; i<4; i++) {
			player.put(st.nextToken(), i);
		}
		
		String A, B; double win, draw, lose;
		for(int i=0; i<6; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			A = st.nextToken();
			B = st.nextToken();
			win = Double.parseDouble(st.nextToken());
			draw = Double.parseDouble(st.nextToken());
			lose = Double.parseDouble(st.nextToken());
			
			score.add(new Point(player.get(A), player.get(B), win, draw, lose));
		}
		
		go(0, 1.0);
		for(double d: ans) sb.append(d).append('\n');
		System.out.println(sb.toString());
	}
	
	static Pair[] play = new Pair[4];
	private static void go(int idx, double p) {
		if(idx==6) {	// 6경기 완료
			for(int i=0; i<4; i++) {
				play[i] = new Pair(i, percent[i]);
			}
			Arrays.sort(play);
			
			int a=play[0].x, b=play[1].x, c=play[2].x, d=play[3].x;
			double aS=play[0].cnt, bS=play[1].cnt, cS=play[2].cnt, dS=play[3].cnt;
			
			if(bS!=cS) {	// 1, 2등 확실할 때
				ans[c]+=p; ans[d]+=p;
			}else if(aS==bS && cS==dS) {	// 1,2,3,4 다같을때
				ans[a]+=p/2.0; ans[b]+=p/2.0;
				ans[c]+=p/2.0; ans[d]+=p/2.0;
			}else if(aS==bS) {	// 2,3,4 같을 때
				ans[a]+=p/3.0; ans[b]+=p/3.0;
				ans[c]+=p/3.0; ans[d]+=p;
			}else if(cS==dS) {	// 1,2,3 같을 때
				ans[b]+=p*2.0/3.0; ans[c]+=p*2.0/3.0;
				ans[d]+=p*2.0/3.0;
			}else {
				ans[b]+=p/2.0; ans[c]+=p/2.0;
				ans[d]+=p;
			}
			
//			System.out.println();
			return;
		}
		
		// A win
		percent[score.get(idx).x]+=3.0;
		go(idx+1, p*score.get(idx).win);
		percent[score.get(idx).x]-=3.0;
		
		// A, B draw
		percent[score.get(idx).x]+=1.0;
		percent[score.get(idx).y]+=1.0;
		go(idx+1, p*score.get(idx).draw);
		percent[score.get(idx).x]-=1.0;
		percent[score.get(idx).y]-=1.0;
		
		// A lose
		percent[score.get(idx).y]+=3.0;
		go(idx+1, p*score.get(idx).lose);
		percent[score.get(idx).y]-=3.0;
	}

	static class Pair implements Comparable<Pair> {
		int x; double cnt;
		public Pair(int x, double cnt) {
			super();
			this.x = x;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Pair o) {
			return (int) (this.cnt-o.cnt);
		}
	}
	static class Point {
		int x, y;
		double win, draw, lose;
		public Point(int x, int y, double win, double draw, double lose) {
			super();
			this.x = x;
			this.y = y;
			this.win = win;
			this.draw = draw;
			this.lose = lose;
		}
	}
}
