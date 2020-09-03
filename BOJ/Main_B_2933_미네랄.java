/**
 * 
 * 40652 kb
 * 260 ms
 * bfs+시뮬
 * 난잡 그자체 ㅎ ㅠ ㅎ
 * 
 */
package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_2933_미네랄 {
	
	static int R, C, N;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		R = Integer.parseInt(st.nextToken());	// row
		C = Integer.parseInt(st.nextToken());	// col
		map = new char[R][C];	// map
		
		String s;
		for(int i=0; i<R; i++) {
			s = br.readLine();
			map[i] = s.toCharArray();
		}
		
		N = Integer.parseInt(br.readLine());	// command 수
		st = new StringTokenizer(br.readLine());
		ArrayList<Integer> command = new ArrayList<>();	// command
		
		int temp;
		for(int i=0; i<N; i++) {
			temp = Integer.parseInt(st.nextToken());
			command.add(R-temp);
		}

		mineral = new int[R][C][2];
		visited = new boolean[R][C];
		go(command);
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
	}
	
	static boolean[][] visited;
	static int[][][] mineral;
	private static void go(ArrayList<Integer> command) {
		int com;
		for(int n=0; n<N; n++) {
			// 1. bfs 돌려서 클러스터 확인 (이 때 mineral에 각 열에서 가장 작은  넣기)
			// 2. command 수행
			// 3. visited 초기화
			com = command.get(n);
			Point here = new Point(-1, -1);
			if(n%2==0) {
				for(int col=0; col<C; col++) {
					if(map[com][col]=='x') {
						here = new Point(com, col);
						break;
					}
				}
			}else {
				for(int col=C-1; col>-1; col--) {
					if(map[com][col]=='x') {
						here = new Point(com, col);
						break;
					}
				}
			}
			
			if(here.x<0) continue;	// 부순게 없으면 넘어가기
			map[here.x][here.y]='.';
			
			initArray();
			
			int nx, ny, num=1, temp=0; 
			boolean flag=false;	
			for(int i=0; i<4; i++) {
				nx = here.x+dx[i];
				ny = here.y+dy[i];
				if(nx<0||ny<0||nx>R-1||ny>C-1) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny]=='x') {
					flag = bfs(new Point(nx, ny), num);
					if(!flag) {
						temp=num;
						break;
					}
					num++;
				}
			}
			
			if(temp>0) {
				down(temp);
			}
			
		}
		
	}

	private static void down(int num) {
		int min = Integer.MAX_VALUE; int temp;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(mineral[i][j][0]==num) {
					temp = check(i, j);
					if(temp>0)	min = Math.min(min, temp);
				}
			}
		}
		for(int i=R-1; i>-1; i--) {
			for(int j=0; j<C; j++) {
				if(mineral[i][j][0]==num) {
					map[i+min][j]='x';
					map[i][j]='.';
				}
			}
		}
	}

	private static void initArray() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				visited[i][j]=false;
				Arrays.fill(mineral[i][j], 0);
			}
		}
	}

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	private static boolean bfs(Point start, int num) {
		LinkedList<Point> queue = new LinkedList<>();
		visited[start.x][start.y]=true;
		queue.add(start);
		mineral[start.x][start.y][0]=num;
		mineral[start.x][start.y][1]=check(start.x, start.y);
		
		boolean flag=false;
		Point current; int x, y, nx, ny;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			if(x==R-1) flag=true;
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				if(nx<0||ny<0||nx>R-1||ny>C-1) continue;
				if(visited[nx][ny]) continue;
				
				if(map[nx][ny]=='x') {
					visited[nx][ny]=true;
					queue.add(new Point(nx, ny));
					mineral[nx][ny][0]=num;
//					mineral[nx][ny][1]=check(nx, ny);
				}
			}
		}
		
		return flag;
	}

	private static int check(int nx, int ny) {
		int gap=0;
		for(int i=nx+1; i<R; i++) {
			if(map[i][ny]=='x') {
				if(mineral[nx][ny][0]!=mineral[i][ny][0])
					return gap;
				else
					return 0;
			}
			gap++;
		}
		return gap;
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
