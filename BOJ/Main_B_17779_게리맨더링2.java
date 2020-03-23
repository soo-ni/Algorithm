/**
 * 22992 kb
 * 416 ms
 * 좌표 생각 잘 하 기!
 * 
 */


package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_17779_게리맨더링2 {

	static int N, ans, row, col, d1, d2;
	static int[][] map;
	static int[] population;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// map 크기
		map = new int[N+1][N+1];	// 인구수
		population = new int[5];
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<N+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 모든 x, y 순회하면서 할 수 있는 d1, d2의 크기 알아내기
		ans = Integer.MAX_VALUE;
		for(int i=1; i<N-1; i++) {		// 아래쪽 2칸은 불가능
			for(int j=2; j<N; j++) {	// 맨 왼쪽, 맨 오른쪽 제외하기
				row=i; col=j;
				dfs(0);					// distance 2개 고르기
			}
		}
		System.out.println(ans);
	}

	private static void dfs(int idx) {
		if(idx==2) {
			if(col-d1<1) return;
			if(col+d2>N) return;
			if(row+d1+d2>N) return;

			int gap = getGap();
			if(gap<ans) ans=gap;
			
			return;
		}
		
		if(idx<1) {
			for(int i=1; i<N; i++) {
				d1=i;
				dfs(idx+1);
			}
		}else {
			for(int i=1; i<N; i++) {
				d2=i;
				dfs(idx+1);
			}
		}
	}

	private static int getGap() {
		Arrays.fill(population, 0);

		int x1=0, x2=0, y1=0, y2=0;
		for(int i=1; i<N+1; i++) {
			if(i>row-1) {
				if(x1>0 && x1<d1+1) y1--;
				if(x1>d1 && x1<d1+d2+1) y1++;
				if(x2>0 && x2<d2+1) y2++;
				if(x2>d2 && x2<d1+d2+1) y2--;
				x1++; x2++;
			} 
			
			for(int j=1; j<N+1; j++) {
								
				if(i>row-1 && i<row+d1+d2+1 && j>col+y1-1 && j<col+y2+1) {
					population[4]+=map[i][j];
//					map[i][j]=4;
					continue;
				}
				
				if(i>0 && i<(row+d1) && j>0 && j<(col+1)) {
					population[0]+=map[i][j];
//					map[i][j]=0;
					continue;
				}
				
				if(i>0 && i<(row+d2+1) && j>col && j<N+1) {
					population[1]+=map[i][j];
//					map[i][j]=1;
					continue;
				}
				
				if(i>row+d1-1 && i<N+1 && j>0 && j<col-d1+d2) {
					population[2]+=map[i][j];
//					map[i][j]=2;
					continue;
				}
				
				if(i>row+d2 && i<N+1 && j>col-d1+d2-1 && j<N+1) {
					population[3]+=map[i][j];
//					map[i][j]=3;
					continue;
				}
				
			}
			
		}
		
		int min=Integer.MAX_VALUE;
		int max=Integer.MIN_VALUE;
		for(int i=0; i<5; i++) {
			min = Math.min(min, population[i]);
			max = Math.max(max, population[i]);
		}
		
		return max-min;
	}
	
}
