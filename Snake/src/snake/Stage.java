package snake;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Stage {
	public Snake snake = new Snake();
	public int size_x;
	public int size_y;
	public int speed;
	public int feed_x;
	public int feed_y;
//	Boolean [][]snake_array;
	LinkedList<Integer> x_position_list;
	LinkedList<Integer> y_position_list;
	
	
	public Stage(int size_x, int size_y, int speed) {
		this.size_x = size_x;
		this.size_y = size_y;
		this.speed = speed;
		// where is snake
		this.x_position_list = new LinkedList<Integer>();
		this.y_position_list = new LinkedList<Integer>();
		x_position_list.addFirst(snake.head_x);
		y_position_list.addFirst(snake.head_y);
		x_position_list.addLast(snake.head_x);
		y_position_list.addLast(snake.head_y-1);
		x_position_list.addLast(snake.head_x);
		y_position_list.addLast(snake.head_y-2);
		x_position_list.addLast(snake.head_x);
		y_position_list.addLast(snake.head_y-3);
	
	}
	
	public void eatFeed() {
		snake.growSnake();
		this.spawnFeed_xy();
	}
	
	// check if head found feed
	public void ifFeed() {
		
	}
	
	// spawn new feed
	public void spawnFeed_xy() {
		Boolean empty_place = false;
		Random random = new Random();
		
		while(!empty_place) {
			feed_x = random.nextInt(size_x-1);
			feed_y = random.nextInt(size_y-1);
			System.out.println("x: " + feed_x);
			System.out.println("y: " + feed_y);
			empty_place = true;
			for (int i = 0; i < snake.length; i++) {
				if ((feed_x == x_position_list.get(i)) && (feed_y == y_position_list.get(i))) {
					empty_place = false;
				}
					
			}
		}
	}
}
