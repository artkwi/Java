/* Artur Kwiatkowski Snake */

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
	
	public Game getDirect (Game game) {
		if ((game.snakeFrame.key_pressed == 'w') && (game.stage.snake.direct != 1))
			game.stage.snake.direct=0;
		if ((game.snakeFrame.key_pressed == 's') && (game.stage.snake.direct != 0))
			game.stage.snake.direct=1;
		if ((game.snakeFrame.key_pressed == 'a') && (game.stage.snake.direct != 3))
			game.stage.snake.direct=2;
		if ((game.snakeFrame.key_pressed == 'd') && (game.stage.snake.direct != 2))
			game.stage.snake.direct=3;
		return game;
	}
	
	// check collisions with borders
	public Boolean checkCollision (Game game) {
		Boolean collision = false;
		int direct = game.stage.snake.direct;
		if ((direct == 0) && (game.stage.snake.head_x-1<0))
			collision = true;
		if ((direct == 1 ) && (game.stage.snake.head_x+2>game.stage.size_x))
			collision = true;
		if ((direct == 2 ) && (game.stage.snake.head_y-1<0))
			collision = true;
		if ((direct == 3 ) && (game.stage.snake.head_y+2>game.stage.size_y))
			collision = true;
		
		return collision;
	}
	
	// change head position
	public Game move (Game game) {
		if (game.checkCollision(game))
			System.out.println("Kolizja");
		else {
			// down
			if (game.stage.snake.direct==0) {
				game.stage.snake.head_x--;
			}
			// up
			if (game.stage.snake.direct==1) {
				game.stage.snake.head_x++;
			}
			// left
			if (game.stage.snake.direct==2) {
				game.stage.snake.head_y--;
			}
			// right
			if (game.stage.snake.direct==3) {
				game.stage.snake.head_y++;
			}
		}

		// set position of head
//		game.stage.snake_array[game.stage.snake.head_x][game.stage.snake.head_y] = true;
		// add x and y position of head to current snake
		game.stage.x_position_list.addFirst(game.stage.snake.head_x);
		game.stage.y_position_list.addFirst(game.stage.snake.head_y);
//		game.stage.snake.length++;
		System.out.println("x: " + stage.x_position_list.getFirst());
		System.out.println("y: " + stage.y_position_list.getFirst());
		
		return game;
	}
	
	
	public static void main(String[] args) {
		// stage 10x10 with speed 1
		Game game = new Game(10,10,1);		
//		System.out.println("game.stage.size_x: " + game.stage.size_x);
//		System.out.println("game.stage.size_y: " + game.stage.size_y);
			
		// moving in loop
		while (true) {
			// paint stage one colour
			for (int i = 0 ; i < game.stage.size_x ; i++) {
				for (int j = 0 ; j < game.stage.size_y ; j++) {
						game.snakeFrame.snakePanel.buttons_array[i][j].setBackground(Color.cyan);
				}
			}
			// paint snake
			for (int i = 0 ; i < game.stage.snake.length ; i++) {
					game.snakeFrame.snakePanel.buttons_array[game.stage.x_position_list.get(i)][game.stage.y_position_list.get(i)].setBackground(Color.RED);			
			}
			
			// delay
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			game.getDirect(game);
			game.move(game);
		}
}
}

