/**
 * 37816 kb 576 ms
 * 37504 kb 204 ms
 * 배열 돌면서 확인하지 않고 미리 빈칸개수 세어놓기
 * using bfs
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_17142_연구소3_retry {
	
	static int N, M, virus_cnt, ans, totalZero;
	static int[][] map;
	static boolean[][] visited;
	static boolean[] selected;
	static ArrayList<Point> virus;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 행, 열
		M = Integer.parseInt(st.nextToken());	// 선택할 바이러스 개수
		map = new int[N][N];	// 연구소 상태
		visited = new boolean[N][N];	// 연구소 방문 체크
		virus = new ArrayList<Point>();	// 바이러스 좌표
		totalZero = 0;	// 빈칸 개수
		
		int temp;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				temp = Integer.parseInt(st.nextToken());
				if(temp==2) {
					virus.add(new Point(i, j));
				}
				if(temp==1) temp=3;
				if(temp==0) totalZero++;
				map[i][j] = temp;
			}
		}
		
		virus_cnt=virus.size();
		selected = new boolean[virus_cnt];
		
		if(totalZero<1) {
			ans=0;
		}else {
			ans=Integer.MAX_VALUE;
		}
		dfs(0, 0);	// 선택한 숫자, idx
		
		if(ans==Integer.MAX_VALUE) ans=-1;
		
		System.out.println(ans);
	}
	
	static LinkedList<Point> queue = new LinkedList<Point>();
	private static void dfs(int pre, int idx) {
		if(ans<1) return;
		if(idx==M) {
			// visited 초기화
			for(int i=0; i<N; i++) {
				Arrays.fill(visited[i], false);
			}
			
			// bfs 수행 전 바이러스 담기
			for(int i=0; i<virus_cnt; i++) {
				if(selected[i]) {
					Point p = virus.get(i);
					queue.add(p);
					visited[p.x][p.y]=true;
				}
			}
			bfs();

			return;
		}
		
		for(int i=pre; i<virus_cnt; i++) {
			if(!selected[i]) {
				selected[i]=true;
				dfs(i, idx+1);
				selected[i]=false;
			}
		}
	}

	static int[] dx = {-1, 1, 0, 0};	// 상 하 좌 우
	static int[] dy = {0, 0, -1, 1};
	private static void bfs() {
		int cnt = queue.size();
		int temp_cnt=0;
		int temp_ans=0;
		int total = totalZero;
		Point current; int x, y;
		while(cnt-->0) {
			current = queue.poll();
			
			for(int i=0; i<4; i++) {
				x = current.x+dx[i];
				y = current.y+dy[i];
				
				if(x<0||y<0||x>=N||y>=N) continue;
				
				if(!visited[x][y] && map[x][y]<3) {
					queue.add(new Point(x, y));
					visited[x][y]=true;
					if(map[x][y]<1)	total--;
					temp_cnt++;
				}
			}

			if(cnt>0) continue;
			
			cnt=temp_cnt;
			temp_cnt=0;
			temp_ans++;
			
			if(temp_ans>ans) {
				queue.clear();
				return;
			}
			
			if(total<1) {
				ans=temp_ans;
				queue.clear();
				return;
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
