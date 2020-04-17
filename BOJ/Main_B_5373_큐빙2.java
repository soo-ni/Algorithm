/**
 * 31304 kb	
 * 248 ms
 * 진짜 풀기싫음 ㅠ
 * 전개도 다시보기..
 *
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_5373_큐빙2 {

	static char[][][] cube;
	static int T, N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(st.nextToken());	// test case
		
		for(int t=0; t<T; t++) {
			cube = new char[6][3][3];		// UDFBLR, 3x3 square, init cube
			for(int i=0; i<3; i++) {
				Arrays.fill(cube[0][i], 'w');
				Arrays.fill(cube[1][i], 'y');
				Arrays.fill(cube[2][i], 'r');
				Arrays.fill(cube[3][i], 'o');
				Arrays.fill(cube[4][i], 'g');
				Arrays.fill(cube[5][i], 'b');
			}
			
			N = Integer.parseInt(br.readLine());	// 회전 수
			st = new StringTokenizer(br.readLine(), " ");
			String s; char section, rotate;
			for(int i=0; i<N; i++) {
				s = st.nextToken();
				section = s.charAt(0);
				rotate = s.charAt(1);
				
				if(rotate=='-') {
					rotation(section, rotate);
					rotation(section, rotate);
					rotation(section, rotate);
				}else {
					rotation(section, rotate);
				}
			}
			
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					sb.append(cube[0][i][j]);
				}
				sb.append('\n');
			}
		}	// test case end
		
		System.out.println(sb.toString());
	}

	static char[][] temp = new char[3][3];
	private static void rotation(char section, char rotate) {
		int sec=0;
		switch(section) {
		case 'U': sec=0; break;
		case 'D': sec=1; break;
		case 'F': sec=2; break;
		case 'B': sec=3; break;
		case 'L': sec=4; break;
		case 'R': sec=5; break;
		}
		
		char tempVal;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				tempVal=cube[sec][i][j];
				temp[i][j]=tempVal;
			}
		}
		
		// 1. 회전 방향으로 해당 면(sec) 위치 바꾸기
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				cube[sec][i][j] = temp[2-j][i];
			}
		}
	
		// 2. 나머지 section 방향 바꾸기
		if(sec==0) {	// UP
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					tempVal=cube[2][i][j];
					temp[i][j]=tempVal;
				}
			}

			for(int i=0; i<3; i++) 
				cube[2][0][i] = cube[5][0][i];
			for(int i=0; i<3; i++)
				cube[5][0][i] = cube[3][2][2-i];
			for(int i=0; i<3; i++)
				cube[3][2][2-i] = cube[4][0][i];
			for(int i=0; i<3; i++)
				cube[4][0][i] = temp[0][i];
		}
		else if(sec==1) {	// down
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					tempVal=cube[2][i][j];
					temp[i][j]=tempVal;
				}
			}
			
			for(int i=0; i<3; i++)    
				cube[2][2][i] = cube[4][2][i];
			for(int i=0; i<3; i++)    
				cube[4][2][i] = cube[3][0][2-i];
			for(int i=0; i<3; i++)    
				cube[3][0][2-i] = cube[5][2][i];
			for(int i=0; i<3; i++)    
				cube[5][2][i] = temp[2][i];
		}
		else if(sec==2) {	// front
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					tempVal=cube[5][i][j];
					temp[i][j]=tempVal;
				}
			}
			
			for(int i=0; i<3; i++)    
				cube[5][i][0] = cube[0][2][i];
			for(int i=0; i<3; i++)    
				cube[0][2][i] = cube[4][2-i][2];
			for(int i=0; i<3; i++)    
				cube[4][2-i][2] = cube[1][0][2-i];
			for(int i=0; i<3; i++)    
				cube[1][0][2-i] = temp[i][0];
		}
		else if(sec==3) {	// back
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					tempVal=cube[5][i][j];
					temp[i][j]=tempVal;
				}
			}
			
			for(int i=0; i<3; i++)    
				cube[5][i][2] = cube[1][2][2-i];
			for(int i=0; i<3; i++)    
				cube[1][2][2-i] = cube[4][2-i][0];
			for(int i=0; i<3; i++)    
				cube[4][2-i][0] = cube[0][0][i];
			for(int i=0; i<3; i++)    
				cube[0][0][i] = temp[i][2];
		}
		else if(sec==4) {	// left
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					tempVal=cube[0][i][j];
					temp[i][j]=tempVal;
				}
			}
			
			for(int i=0; i<3; i++)    
				cube[0][i][0] = cube[3][i][0];
			for(int i=0; i<3; i++)    
				cube[3][i][0] = cube[1][i][0];
			for(int i=0; i<3; i++)    
				cube[1][i][0] = cube[2][i][0];
			for(int i=0; i<3; i++)    
				cube[2][i][0] = temp[i][0];
		}
		else if(sec==5) {	// right
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					tempVal=cube[0][i][j];
					temp[i][j]=tempVal;
				}
			}
			
			for(int i=0; i<3; i++)    
				cube[0][i][2] = cube[2][i][2];
			for(int i=0; i<3; i++)    
				cube[2][i][2] = cube[1][i][2];
			for(int i=0; i<3; i++)    
				cube[1][i][2] = cube[3][i][2];
			for(int i=0; i<3; i++)    
				cube[3][i][2] = temp[i][2];
		}
	}

}
