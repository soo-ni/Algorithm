/**
*
* 14680	KB
* 112 ms
*
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_2630_색종이만들기 {

	static int N, white, blue;
	static int[][] paper;
	static boolean[][] cut;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		paper = new int[N][N];
		cut = new boolean[N][N];
		for(int i=0; i<N; i++)
			Arrays.fill(cut[i], true);
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		white = 0; 
		blue = 0;
		cutPaper(0, 0, N);
		System.out.println(white);
		System.out.println(blue);

	}

	private static void cutPaper(int row, int col, int n) {
		boolean token=true;
		int color=paper[row][col];

		L: for(int i=row; i<row+n; i++) {
			for(int j=col; j<col+n; j++) {
				if(paper[i][j]!=color) {
					token=false; 
					break L;
				}
			}				
		}
		
		if(token) {			
			if(color==0) white++;
			else blue++;	
			
			return;
			
		}else {
			cutPaper(row, col, n/2);
			cutPaper(row, col+n/2, n/2); 
			cutPaper(row+n/2, col, n/2);
			cutPaper(row+n/2, col+n/2, n/2);
		}		
		
		
	}
	
}
