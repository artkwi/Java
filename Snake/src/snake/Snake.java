package snake;

public class Snake {
	public int head_x;
	public int head_y;
	public int length;
	public int direct;

	public Snake() {
		head_x = 2;
		head_y = 3;
		length = 4;
		direct = 3;
	}
	
	public void growSnake() {
		this.length++;
	}
}
