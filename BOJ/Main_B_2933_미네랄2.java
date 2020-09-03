/**
 * 
 * 39964 kb
 * 244 ms
 * bfs+시뮬
 * 쓰레기같은 Main_B_2933_미네랄과 비교해볼것 ㅎ
 * 
 */
package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_2933_미네랄2 {

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int R, C, N;
	static char[][] map;
	static int[][] mineral;
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

		mineral = new int[R][C];
		go(command);
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
	}
	
	private static void go(ArrayList<Integer> command) {
		int com;
		for(int n=0; n<N; n++) {
			// 1. 부수기
			// 2. visited, mineral init
			// 3. bfs 돌아서 cluster가 땅에 닿지 않으면(!flag) down
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
			
			int nx, ny, num=1; 
			boolean flag=false;	
			for(int i=0; i<4; i++) {
				nx = here.x+dx[i];
				ny = here.y+dy[i];
				if(nx<0||ny<0||nx>R-1||ny>C-1) continue;
				if(mineral[nx][ny]>0) continue;
				if(map[nx][ny]=='x') {
					flag = bfs(nx, ny, num);
					if(!flag) {
						down(num);
						break;
					}
					num++;
				}
			}
		}
		
	}

	private static void down(int num) {
		int min = Integer.MAX_VALUE; int temp;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(mineral[i][j]==num) {
					temp = check(i, j);
					if(temp>0)	min = Math.min(min, temp);
				}
			}
		}
		for(int i=R-1; i>-1; i--) {
			for(int j=0; j<C; j++) {
				if(mineral[i][j]==num) {
					map[i+min][j]='x';
					map[i][j]='.';
				}
			}
		}
	}

	private static void initArray() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				mineral[i][j]=0;
			}
		}
	}

	private static boolean bfs(int row, int col, int num) {
		LinkedList<Point> queue = new LinkedList<>();
		queue.add(new Point(row, col));
		mineral[row][col]=num;
		
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
				if(mineral[nx][ny]>0) continue;
				
				if(map[nx][ny]=='x') {
					queue.add(new Point(nx, ny));
					mineral[nx][ny]=num;
				}
			}
		}
		
		return flag;
	}

	private static int check(int nx, int ny) {
		int gap=0;
		for(int i=nx+1; i<R; i++) {
			if(map[i][ny]=='x') {
				if(mineral[nx][ny]!=mineral[i][ny])
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
