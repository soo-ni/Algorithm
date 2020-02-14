import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Enemy {
	int x;
	int y;
	
	Enemy(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Main_B_17135_캐슬디펜스 {
	
	static int N, M, D, max_point, enemy;
	static int[][] ground;
	static boolean[][] destroy, updateDestroy;
	static boolean[] selected;
	static int[] numbers = new int[3];
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		selected = new boolean[M];
		ground = new int[N][M];
		destroy = new boolean[N][M];
		updateDestroy = new boolean[N][M];
		
		int isEnemy;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				isEnemy = Integer.parseInt(st.nextToken());
				ground[i][j] = isEnemy;
				
				if(isEnemy==1) destroy[i][j] = true; // 적의 생사
			}
		}
		
		max_point = 0;
		getEnemy(0);
		System.out.println(max_point);
		
	}
	
	private static void getEnemy(int idx) {	// 궁수자리 선택하기		
		if(idx==3) {	// 궁수자리 선택완료
			int point = getPoint();	// 공격한 적 수 구하기
			if(max_point<point)
				max_point = point;
			return;
		}
			
		int m = 0;
		if(idx>0) {
			m = numbers[idx-1]; 			
		}
		
		for(int i=m; i<M; i++) {
			if(!selected[i]) {
				numbers[idx] = i;
				selected[i] = true;
				getEnemy(idx+1);
				selected[i] = false;
			}
		}
		
	}

	private static int getPoint() {
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				updateDestroy[i][j] = destroy[i][j];
		
		int[][] hash = new int[3][2];
		int kill = 0;
		int time = N;
		while(time-->0) {
			/* enemy가 살아있으면
			 * 가장 짧은 거리의 i, j를 받음	
			 * 적공격완료: i, j를 false로 만들어주기
			 * set.clear();
			 * */
			int distance, min_dis;
			Enemy p;
			for(int no=0; no<3; no++) {
				p = new Enemy(100, 100);
				min_dis=100;
				
				for(int i=time; i>=0; i--) {
					for(int j=0; j<M; j++) {
						if(updateDestroy[i][j]) {
							distance = (time-i) + Math.abs(j-numbers[no]);
							if(distance<D) {
								if(min_dis>distance) {
									min_dis = distance;
									p.x=i; p.y=j;
								}else if(min_dis==distance){
									if(j<p.y) {
										p.x=i; p.y=j;										
									}
								}
							}							
						}						
					}
				}
				
				if(p.x!=100) {
					hash[no][0] = p.x;
					hash[no][1] = p.y;
					System.out.print(p.x+" "+p.y+" ");
				}
					
			}
			System.out.println();
			for(int i=0; i<3; i++) {
				if(updateDestroy[hash[i][0]][hash[i][1]]) {
					updateDestroy[hash[i][0]][hash[i][1]] = false;
					kill++;
				}
			}
		}
		
		return kill;
	}
}
