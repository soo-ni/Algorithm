package study_Aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 
 * 16312 kb
 * 112 ms
 * @author soo-ni
 *
 */
public class Main_9207_페그솔리테어 {

	static int ansNum, ansCnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());	// test case
		
		for(int t=0; t<T; t++) {
			if(t!=0) br.readLine();	//첫줄 버리기
			
			char[][] map = new char[5][9];	// map
			ArrayList<Point> pin = new ArrayList<Point>();	// pin 위치
			
			char c;
			for(int i=0; i<5; i++) {
				String s = br.readLine();
				for(int j=0; j<9; j++) {
					c = s.charAt(j);
					map[i][j] = c;
					
					if(c=='o') pin.add(new Point(i, j));
				}
			}
			
			int size = pin.size();
			ansNum=Integer.MAX_VALUE;
			ansCnt=Integer.MAX_VALUE;
			dfs(0, size, map);
			
			System.out.println(ansNum+" "+ansCnt);
		} // test case end
		
	}
	
	private static void dfs(int idx, int size, char[][] map) {
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		int nx, ny, nnx, nny; boolean flag = false;
		for(int i=0; i<5; i++) {
			for(int j=0; j<9; j++) {
				
				if(map[i][j]=='o') {	// 핀이 걸리면
					for(int dir=0; dir<4; dir++) {
						nx = i+dx[dir];
						ny = j+dy[dir];
						nnx = i+dx[dir]*2;
						nny = j+dy[dir]*2;
						
						if(nx<0||ny<0||nx>4||ny>8) continue;
						if(nnx<0||nny<0||nnx>4||nny>8) continue;
						if(map[nx][ny]=='o' && map[nnx][nny]=='.') {	//옆에 핀이 있고, 그 옆이 비어있으면
							flag=true;
							map[i][j]='.';
							map[nx][ny]='.';
							map[nnx][nny]='o';
							dfs(idx+1, size-1, map);
							map[i][j]='o';
							map[nx][ny]='o';
							map[nnx][nny]='.';
						}
					}
					
				}
			}
		}
		
		if(!flag) {
			if(size<ansNum) {
				ansNum=size;
				ansCnt=idx;
			}else if(size==ansNum) {
				ansCnt=idx;
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
