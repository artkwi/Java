package snake;

import java.util.Arrays;

public class Stage {
	public Snake snake = new Snake();
	public int size_x;
	public int size_y;
	public int speed;
	Boolean [][]snake_array;
	
	public Stage(int size_x, int size_y, int speed) {
		this.size_x = size_x;
		this.size_y = size_y;
		this.speed = speed;
		snake_array = new Boolean[size_x][size_y];
		for (Boolean[] row: snake_array) {
			Arrays.fill(row,false);	
		}
		
	}
}
