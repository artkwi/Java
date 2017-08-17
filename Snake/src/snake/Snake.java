package snake;

public class Snake {
	private int head_x;
	private int head_y;
	private int length;
	private int direct;

	public Snake() {
		head_x = 2;
		head_y = 3;
		length = 4;
		direct = 3;
	}
	
	public void growSnake() {
		this.length++;
	}

	public int getHead_x() {
		return head_x;
	}

	public void setDownHead_x() {
		this.head_x--;
	}
	public void setUpHead_x() {
		this.head_x++;
	}

	public int getHead_y() {
		return head_y;
	}

	public void setDownHead_y() {
		this.head_y--;
	}
	public void setUpHead_y() {
		this.head_y++;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}
}
