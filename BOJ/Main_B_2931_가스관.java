package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 13092 kb
 * 76 ms
 * bfs + 시뮬
 * 방향 쓰는거 다시 확인해보기!
 * 
 * @author soo-ni
 *
 */
public class Main_B_2931_가스관 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());	// 행
		int C = Integer.parseInt(st.nextToken());	// 열
		char[][] map = new char[R][C];
		Point start = new Point(0, 0);
		
		String s; char c;
		for(int i=0; i<R; i++) {
			s = br.readLine();
			for(int j=0; j<C; j++) {
				c = s.charAt(j);
				map[i][j] = c;
				
				if(c=='M') start = new Point(i, j);
			}
		}
		
		// start부터 시작해서 쭉쭉 넘어가기
		Point answer = bfs(map, start, R, C);
		System.out.println(answer.x+" "+answer.y+" "+answer.dir);
	}
	
	static int[] dx = {-1, 0, 1, 0};	//상 우 하 좌
	static int[] dy = {0, 1, 0, -1};
	private static Point bfs(char[][] map, Point start, int R, int C) {
		// queue
		LinkedList<Point> queue = new LinkedList<>();
		queue.add(start);

		// visited 
		boolean[][] visited = new boolean[R][C];
		visited[start.x][start.y]=true;
		
		Point answer = new Point(-1, -1, '0');
		Point current; int x, y, nx, ny;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			boolean[] dir = getDir(map[x][y]);	//파이프 방향
			
			for(int i=0; i<4; i++) {
				nx = x+dx[i];
				ny = y+dy[i];
				
				if(nx<0||ny<0||nx>R-1||ny>C-1) continue;
				if(visited[nx][ny]) continue;
				
				boolean flag=false;	// 파이프 끊김 확인
				if(map[x][y]!='M' && dir[i]) {
					if(map[nx][ny]=='.') flag=true;
				}
				
				// 파이프가 끊겼으면 answer에 행, 렬 저장
				// 아니면 queue에 그냥 add
				if(flag) {
					char pipe = getPipe(nx, ny, current, map, R, C);
					answer = new Point(nx+1, ny+1, pipe);
					queue.clear();
					break;
				}else {
					if(map[nx][ny]!='.') {
						queue.add(new Point(nx, ny));
						visited[nx][ny]=true;
					}
				}
			}	// 사방탐색 end
		}
		
		return answer;
	}
	
	public static boolean[] getDir(char c) {
		boolean[] dir = new boolean[4];
		int num = c-'0';
		for(int i=0; i<4; i++) {
			switch(c) {
			case '|':
				if(i%2==0) dir[i]=true;
				break;
			case '-':
				if(i%2==1) dir[i]=true;
				break;
			case '+':
				dir[i]=true;
				break;
			case '1':
			case '3':
				if(num==i || (num+1)%4==i) dir[i]=true;
				break;
			case '2':
			case '4':
				if((num-2)==i || (num-1)==i) dir[i]=true;
				break;
			}
		}
		return dir;
	}
	
	static char[] pipeArr = {'|', '-', '+', '1', '2', '3', '4'};
	private static char getPipe(int x, int y, Point pre, char[][] map, int R, int C) {
		boolean[] dir = new boolean[4];
		boolean[] dirPre;
		int nx, ny;
		for(int i=0; i<4; i++) {
			dirPre = new boolean[4];
			nx = x+dx[i];
			ny = y+dy[i];
			if(nx<0||ny<0||nx>R-1||ny>C-1) continue;
			
			dirPre = getDir(map[nx][ny]);
			if(dirPre[(i+2)%4]) {
				dir[i]=true;
			}
		}
		
		char ans = 0;
		for(Character c: pipeArr) {
			dirPre = new boolean[4];
			dirPre = getDir(c);
			if(Arrays.equals(dirPre, dir)) {
				ans=c;
			}
		}
		return ans;
	}

	static class Point {
		int x, y; char dir;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		public Point(int x, int y, char dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}

