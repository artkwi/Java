package snake;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Stage {
	public Snake snake = new Snake();
	private int size_x;
	private int size_y;
	private int speed;
	private int feed_x;
	private int feed_y;
	private LinkedList<Integer> x_position_list;
	private LinkedList<Integer> y_position_list;
	
	
	public Stage(int size_x, int size_y, int speed) {
		this.size_x = size_x;
		this.size_y = size_y;
		this.speed = speed;
		// where is snake
		this.x_position_list = new LinkedList<Integer>();
		this.y_position_list = new LinkedList<Integer>();
		x_position_list.addFirst(snake.getHead_x());
		y_position_list.addFirst(snake.getHead_y());
		x_position_list.addLast(snake.getHead_x());
		y_position_list.addLast(snake.getHead_y()-1);
		x_position_list.addLast(snake.getHead_x());
		y_position_list.addLast(snake.getHead_y()-2);
		x_position_list.addLast(snake.getHead_x());
		y_position_list.addLast(snake.getHead_y()-3);
	
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
			for (int i = 0; i < snake.getLength(); i++) {
				if ((feed_x == x_position_list.get(i)) && (feed_y == y_position_list.get(i))) {
					empty_place = false;
				}
					
			}
		}
	}

	public int getSize_x() {
		return size_x;
	}

	public void setSize_x(int size_x) {
		this.size_x = size_x;
	}

	public int getSize_y() {
		return size_y;
	}

	public void setSize_y(int size_y) {
		this.size_y = size_y;
	}

	public int getFeed_x() {
		return feed_x;
	}

	public void setFeed_x(int feed_x) {
		this.feed_x = feed_x;
	}

	public int getFeed_y() {
		return feed_y;
	}

	public void setFeed_y(int feed_y) {
		this.feed_y = feed_y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public LinkedList<Integer> getX_position_list() {
		return x_position_list;
	}

	public void setX_position_list(LinkedList<Integer> x_position_list) {
		this.x_position_list = x_position_list;
	}

	public LinkedList<Integer> getY_position_list() {
		return y_position_list;
	}

	public void setY_position_list(LinkedList<Integer> y_position_list) {
		this.y_position_list = y_position_list;
	}
}
