import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Enemy implements Comparable<Enemy> {
	int x, y, dis;
	
	
	Enemy(int x, int y, int dis){
		this.x = x;
		this.y = y;
		this.dis = dis;
	}
	
	@Override
	public int compareTo(Enemy o) {
		int result = this.dis-o.dis;
		if(result==0) result=this.y-o.y;
		return result;
	}
}

public class Main_B_17135_캐슬디펜스 {
	
	static int N, M, D, max_point, enemy;
	static int[][] ground;
	static boolean[][] org_destroy, destroy, updateDestroy;
	static boolean[] selected;
	static int[] numbers = new int[3];	// 궁수 idx
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		selected = new boolean[M];
		ground = new int[N][M];
		destroy = new boolean[N][M];
		org_destroy = new boolean[N][M];
		updateDestroy = new boolean[N][M];
		
		int isEnemy;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				isEnemy = Integer.parseInt(st.nextToken());
				ground[i][j] = isEnemy;
				
				if(isEnemy==1) {
					org_destroy[i][j] = true; // 적의 생사
					destroy[i][j] = true; // 적의 생사
				}
			}
		}
		
		max_point = 0;
//		getEnemy(0);	// priority queue 사용
		getEnemyUsingNP();	// next permutation 사용
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
	
	static PriorityQueue<Enemy> queue;
	private static int getPoint() {

		boolean temp;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				temp = org_destroy[i][j];
				destroy[i][j] = temp;	
				updateDestroy[i][j] = temp;		
			}			
		}
		
		int kill = 0;
		int time = N;
		queue = new PriorityQueue<Enemy>();
		while(time-->0) {
			for(int no=0; no<3; no++) {
				int distance;
				for(int i=time; i>=0; i--) {
					for(int j=0; j<M; j++) {
						if(destroy[i][j]) {
							distance = Math.abs(j-numbers[no]) + Math.abs(time-i);
							if(distance<D) {
								queue.offer(new Enemy(i, j, distance));
							}
						}
					}
				}	// distance 계산
				
				if(queue.isEmpty()) continue;
				
				Enemy e = queue.poll();
				if(updateDestroy[e.x][e.y]) {
					updateDestroy[e.x][e.y] = false;
					kill++;
				}
				queue.clear();
				
			}	// 궁수
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					temp = updateDestroy[i][j];
					destroy[i][j] = temp;		
				}			
			}
						
		}
		
		return kill;
	}

	
	
	private static void getEnemyUsingNP() {
		/*
		 * next permutation 이용: 궁수 자리 선택
		 */
		
		int[] p = new int[M];
		p[M-1]=p[M-2]=p[M-3]=1;
		
		int n, point;
		do{
			n=0;
			for(int i=0; i<M; i++) {
				if(p[i]==1) numbers[n++]=i;
			}
			
			point = getPoint();	// 공격한 적 수 구하기
			if(max_point<point)
				max_point = point;
			
			
		}while(nextPermutation(p));
	}
	
	public static boolean nextPermutation(int[] p) {
		int len = p.length;
		
		// 1. i-1찾기
		int i = len-1;
		while(i>0 && p[i-1]>=p[i]) {
			--i;
		}
		if(i==0) return false;	// i가 가장 큼
			
		// 2. 교환할 j찾기
		int j = len-1;
		while(p[i-1]>=p[j]) {
			--j;
		}
		
		// 3. i-1과 j 교환하기
		int temp;
		temp = p[i-1];
		p[i-1] = p[j];
		p[j] = temp;
		
		// 4. i이후 정렬하기
		int k = len-1;
		while(i<=k) {
			temp = p[i];
			p[i] = p[k];
			p[k] = temp;
			k--; i++;
		}		
		
		return true;
	}
}





