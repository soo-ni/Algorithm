/**
 * 15256 kb -> 15252kb
 * 1752 ms -> 420 ms
 * 다른사람 답 참고한 코드
 * 
 * getLadder() dfs돌 때, 
 * i가 row일 때 부터(row이전은 이미 확인했기때문에 다시 돌 필요가 없음) 돌게 고친 후 시간 단축
 * 
 */

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_15684_사다리조작_참고 {
	
	static int N, M, H, ans, sum;
	static boolean[][] visited;
	static int[] ladder;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 사다리 봉 개수
		M = Integer.parseInt(st.nextToken());	// 사다리 가로줄의 갯수
		H = Integer.parseInt(st.nextToken());	// 사다리 높이
		visited = new boolean[H][N+2];	// 사다리 있는지 확인(맨 왼쪽때문에 +1)
		ladder = new int[N+1];	// 해당 줄에 몇개의 사다리 있는지 확인
		
		int a, b;	// a: 가로줄 위치, b: 시작 사다리 줄 번호
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken())-1;	// 1번부터 시작
			b = Integer.parseInt(st.nextToken());	// 1번부터 시작
			
			visited[a][b] = true;
			ladder[b]++;
		}
		
		ans=Integer.MAX_VALUE;
		sum=0;
		token=false;
		for(int i=0; i<=3; i++) {
			sum=i;
			getLadder(0, 1, 0);	// idx: N-1개까지 돌기
			if(token) break;
		}
		if(ans>3 || ans==Integer.MAX_VALUE) ans=-1;
		System.out.println(ans);
	}

	static boolean token;
	private static void getLadder(int row, int col, int idx) {
		if(token) return;
		if(idx==sum) {
			// 어떻게 확인할지 생각하기
			if(!checkLadder()) return;
			if(sum<ans) ans=sum;
			token=true;
			return;
		}
		
		for(int i=row; i<H; i++) {
			for(int j=1; j<N; j++) {
				if(!visited[i][j] && !visited[i][j-1] && !visited[i][j+1]) {
					visited[i][j] = true;	
					getLadder(i, j, idx+1);
					visited[i][j] = false;
				}
				
			}
		}
	}
	private static boolean checkLadder() {
		int row, col;
		for(int i=1; i<N+1; i++) {
			row = 0; col = i;
			while(row<H) {
				if(visited[row][col]) {
					col++;
				}else if(visited[row][col-1]) {
					col--;
				}
				row++;
			}
			
			if(col!=i) return false;
		}
		
		return true;
	}

}
