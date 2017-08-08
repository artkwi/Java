package snake;

import java.io.Console;

public class Game {

	public Stage stage = new Stage();
	public SnakeFrame snakeFrame = new SnakeFrame();
//	public Snake snake = new Snake(); 
	
	
	public static void main(String[] args) {
		Game game = new Game();
		game.stage.size_x=10;
		game.stage.size_y=10;
		game.stage.speed=2;
		game.stage.snake.head_x=1;
		game.stage.snake.head_y=1;
		game.stage.snake_array = new Boolean[10][10];
		
		System.out.println(game.stage.snake_array[2][3]);
	
}
}

