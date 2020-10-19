package study;

import java.util.PriorityQueue;

public class Solution_단속카메라 {
	
	
	public static void main(String[] args) {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		int[][] routes = {
				{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}
		};
		for(int i=0; i<routes.length; i++) {
			queue.add(new Point(routes[i][0], routes[i][1]));
		}
		
		int answer = 0;
		int end = queue.poll().y;
		Point current;
		int x, y;
		while(!queue.isEmpty()) {
			current = queue.poll();
			x = current.x;
			y = current.y;
			
			if(end>y) end = y;
			if(!queue.isEmpty() && end<queue.peek().x) {
				answer += 1;
				end = queue.peek().y;
			}
		}
		
		System.out.println(answer+1);
	}
	
	static class Point implements Comparable<Point> {
		int x, y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Point o) {
			int result = this.x-o.x;
			if(result==0) result = this.y-o.y;
			return result;
		}
	}
}
