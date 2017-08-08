package snake;

import java.awt.Color;
import java.io.Console;
import java.util.concurrent.TimeUnit;

public class Game {

	public Stage stage;
	public SnakeFrame snakeFrame;
	
	public Game() {
		
	}
	public Game(int size_x, int size_y, int speed) {
		stage = new Stage(size_x,size_y,speed);
		snakeFrame = new SnakeFrame();
	}
	
	public Game move (Game game) {
		// right
		if (game.stage.snake.direct==0) {
			game.stage.snake.head_x++;
			game.stage.snake_array[game.stage.snake.head_x][game.stage.snake.head_y] = true;
		}
		return game;
	}
	
	
	public static void main(String[] args) {
		Game game = new Game(10,10,1);
		// create boolean array
//		game.stage.snake_array = new Boolean[10][10];
		// set true cell in boolean array that contain head
		game.stage.snake_array[game.stage.snake.head_x][game.stage.snake.head_y] = true;
		System.out.println(game.stage.snake_array[9][9]);
		
		System.out.println("game.stage.size_x: " + game.stage.size_x);
		System.out.println("game.stage.size_y: " + game.stage.size_y);
		while (true) {
			for (int i = 0 ; i < game.stage.size_x ; i++) {
				for (int j = 0 ; j < game.stage.size_y ; j++) {
					if (game.stage.snake_array[i][j] == true) {
						game.snakeFrame.snakePanel.buttons_array[i][j].setBackground(Color.RED);
					}
				}
			}
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			game = game.move(game);
		}
}
}

