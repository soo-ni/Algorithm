/**
 * 90308 kb 868	ms :using sort
 * 71080 kb 660 ms :using pq
 * dfs 이용해서 품! 거리계산하는 쪽 다시해보기ㅠㅠ
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_15686_치킨배달 {
	
	static int N, M, ans;
	static int[][] map;
	static boolean[][] visited;
	static Point[] selectedStore;
	static ArrayList<Point> home;
	static ArrayList<Point> store;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 행렬
		M = Integer.parseInt(st.nextToken());	// 최대 선택 가게 개수
		map = new int[N][N];			// 도시 정보
		visited = new boolean[N][N];	// 집 확인
		selectedStore = new Point[M];	// 선택된 치킨집의 좌표
		home = new ArrayList<Point>();	// 집 좌표
		store = new ArrayList<Point>();	// 치킨집 좌표
		
		int temp;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;

				if(temp==1) home.add(new Point(i, j, 0));
				if(temp==2)	store.add(new Point(i, j, 0));
			}
		}
		
		ans=Integer.MAX_VALUE;
		getPath(0, 0);
		System.out.println(ans);
	}

	private static void getPath(int pre, int idx) {
		if(idx==M) {
			// visited 초기화 필요!
			for(int i=0; i<N; i++) {
				Arrays.fill(visited[i], false);
			}
			int sum = getDistance();
			if(ans>sum) ans=sum;
			return;
		}
		
		int len = store.size(); Point p;
		for(int i=pre; i<len; i++) {
			p = store.get(i);
			if(map[p.x][p.y]==2) {
				selectedStore[idx] = p;
				map[p.x][p.y] = 3;
				getPath(i, idx+1);
				map[p.x][p.y] = 2;
			}
		}
	}

	private static int getDistance() {
//		LinkedList<Point> queue = new LinkedList<Point>();
		PriorityQueue<Point> queue = new PriorityQueue<Point>();
		int sum=0, len=home.size(), dis;
		Point cur_home, cur_store;
		for(int i=0; i<len; i++) {
			cur_home = home.get(i);
			for(int j=0; j<M; j++) {
				cur_store = selectedStore[j];
				dis = Math.abs(cur_home.x-cur_store.x)+Math.abs(cur_home.y-cur_store.y); 
				queue.add(new Point(cur_home.x, cur_home.y, dis));
			}
		}
		
//		queue.sort(null);
		while(!queue.isEmpty()) {
			cur_home = queue.poll();
			if(!visited[cur_home.x][cur_home.y]) {
				sum+=cur_home.dis;
				visited[cur_home.x][cur_home.y]=true;
			}
		}
		
		return sum;
	}

	static class Point implements Comparable<Point> {
		int x, y, dis;
		public Point(int x, int y, int dis) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
		@Override
		public int compareTo(Point o) {
			return this.dis-o.dis;
		}
	}
}
