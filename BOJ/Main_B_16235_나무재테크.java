/**
 * 285784 kb	
 * 1012 ms
 * 깡시뮬 => 문제 잘읽기!
 * 나눠줄 때 모든 값을 더해준 다음에 나눠서 틀렸었음 ㅜㅜ 
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_B_16235_나무재테크 {
	
	static int N, M, K;
	static int[][] S2D2;
	static ArrayList<Point>[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// row, col
		M = Integer.parseInt(st.nextToken());	// tree
		K = Integer.parseInt(st.nextToken());	// time
		S2D2 = new int[N][N];	// 양분
		map = new ArrayList[N][N];	// map
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = new ArrayList<Point>();
				map[i][j].add(new Point(0, 5));	// 0번째 index는 무조건 양분임
			}
		}
		
		// S2D2
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				S2D2[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// tree
		int x, y, age;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken())-1;
			y = Integer.parseInt(st.nextToken())-1;
			age = Integer.parseInt(st.nextToken());
			map[x][y].add(new Point(age, 0));	// 나이 순으로 정렬 가능
		}
		
		for(int k=0; k<K; k++) {
			spring_summer();	// 나이증가, 양분더하기
			fall();		// 번식
			winter();	// 양분
		}
		
		int ans = 0, temp;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				temp = map[i][j].size()-1;
				ans+=temp;
			}
		}
		System.out.println(ans);
	}
	
	private static void winter() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j].set(0, new Point(0, map[i][j].get(0).y+S2D2[i][j]));
			}
		}
	}
	
	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	private static void fall() {
		int len, nx, ny;
		for(int x=0; x<N; x++) {
			for(int y=0; y<N; y++) {
				len=map[x][y].size();
				for(int n=1; n<len; n++) {
					if(map[x][y].get(n).x%5==0) {	// 5의 배수
						for(int i=0; i<8; i++) {
							nx=x+dx[i];
							ny=y+dy[i];
							if(nx<0||ny<0||nx>N-1||ny>N-1) continue;
							map[nx][ny].add(new Point(1, 0));	// 1살짜리 더해주기
						}
					}
				}
			}
		}
	}
	
	private static void spring_summer() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				Collections.sort(map[i][j]);
			}
		}
		
		int feed, temp, len, idx;
		for(int x=0; x<N; x++) {
			for(int y=0; y<N; y++) {
				feed = map[x][y].get(0).y;	// 양분
				len = map[x][y].size();
				idx = len;
				for(int i=1; i<len; i++) {
					temp = map[x][y].get(i).x;
					if(feed-temp>-1) {
						feed-=temp;
						map[x][y].set(i, new Point(temp+1, 0));
					}else {
						idx=i;	// i번부터 지워져야됨
						break;
					}
				}
				
				temp=0;
				for(int i=idx; i<len; i++) {
					temp+=(map[x][y].get(idx).x)/2;	// 다 더한다음에 나누면 안됨 ㅠ
					map[x][y].remove(idx);
				}
				
				map[x][y].set(0, new Point(0, feed+temp));
			}
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
			return this.x-o.x;
		}
	}
}
