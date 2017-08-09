package snake;

import java.util.Arrays;
import java.util.LinkedList;

public class Stage {
	public Snake snake = new Snake();
	public int size_x;
	public int size_y;
	public int speed;
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
}
