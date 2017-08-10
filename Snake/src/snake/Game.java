/* Artur Kwiatkowski Snake */

package snake;

import java.awt.Color;
import java.io.Console;
import java.util.concurrent.TimeUnit;

public class Game {

	public Stage stage;
	public SnakeFrame snakeFrame;
	public Boolean game_over;
	
	public Game() {
		
	}	
	
	public Game(int size_x, int size_y, int speed) {
		stage = new Stage(size_x,size_y,speed);
		snakeFrame = new SnakeFrame();
		game_over = false;
	}
	
	// get direct with forward forbiddance
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
	
	
	// check collisions
	public Boolean checkCollision (Game game) {
		Boolean collision = false;
		int direct = game.stage.snake.direct;
		int head_x = game.stage.snake.head_x;
		int head_y = game.stage.snake.head_y;
		
		//border collisions
		if ((direct == 0) && (game.stage.snake.head_x-1<0))
			collision = true;
		else if ((direct == 1 ) && (game.stage.snake.head_x+2>game.stage.size_x))
			collision = true;
		else if ((direct == 2 ) && (game.stage.snake.head_y-1<0))
			collision = true;
		else if ((direct == 3 ) && (game.stage.snake.head_y+2>game.stage.size_y))
			collision = true;
		
		// body collisions
		else if (direct == 0) {
			for (int i = 2; i < game.stage.snake.length; i++ ) {
				if ((game.stage.x_position_list.get(i) == head_x - 1) && (game.stage.y_position_list.get(i) == head_y))
					collision = true;
			}
		}
		else if (direct == 1) {
			for (int i = 2; i < game.stage.snake.length; i++ ) {
				if ((game.stage.x_position_list.get(i) == head_x + 1) && (game.stage.y_position_list.get(i) == head_y))
					collision = true;
			}
		}
		else if (direct == 2) {
			for (int i = 2; i < game.stage.snake.length; i++ ) {
				if ((game.stage.y_position_list.get(i) == head_y - 1)  && (game.stage.x_position_list.get(i) == head_x))
					collision = true;
			}
		}
		else if (direct == 3) {
			for (int i = 2; i < game.stage.snake.length; i++ ) {
				if ((game.stage.y_position_list.get(i) == head_y + 1) && (game.stage.x_position_list.get(i) == head_x))
					collision = true;
			}
		}
		
		
		return collision;
	}
	
	// change head position
	public Game move (Game game) {
		// check collisions
		if (game.checkCollision(game)) {
			game_over = true;
				System.out.println("Kolizja");
		}
			// change head position
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

		// add x and y position of head to current snake
		game.stage.x_position_list.addFirst(game.stage.snake.head_x);
		game.stage.y_position_list.addFirst(game.stage.snake.head_y);
		
		if ((game.stage.snake.head_x == game.stage.feed_x) && (game.stage.snake.head_y == game.stage.feed_y)) {
			// spawn feed
			game.stage.eatFeed();
			game.paintFeed();
		}
		
		return game;
	}
	
	// paint feed
	public void paintFeed () {
		for (int i = 0 ; i < stage.size_x ; i++) {
			for (int j = 0 ; j < stage.size_y ; j++) {
				if ((i == stage.feed_x) && (j == stage.feed_y)) {
					snakeFrame.snakePanel.buttons_array[i][j].setBackground(Color.BLACK);
				}
			}
		}
	}
	
	public void paintStage () {
		// paint stage one colour
		for (int i = 0 ; i < stage.size_x ; i++) {
			for (int j = 0 ; j < stage.size_y ; j++) {
					snakeFrame.snakePanel.buttons_array[i][j].setBackground(Color.cyan);
			}
		}
		// paint snake
		for (int i = 0 ; i < stage.snake.length ; i++) {
				snakeFrame.snakePanel.buttons_array[stage.x_position_list.get(i)][stage.y_position_list.get(i)].setBackground(Color.RED);			
		}
		// paint feed
		paintFeed();
	}
	
	public static void main(String[] args) {
		// stage 10x10 with speed 1
		Game game = new Game(10,10,1);		
//		System.out.println("game.stage.size_x: " + game.stage.size_x);
//		System.out.println("game.stage.size_y: " + game.stage.size_y);
			
		game.stage.spawnFeed_xy();
		game.paintStage();
		game.paintFeed();
		// moving in loop
		while (true) {
			// speed delay
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			game.move(game);
			game.paintStage();
			game.getDirect(game);
			
			
			if (game.game_over)
				break;
		}
		game.snakeFrame.button.setText("Koniec gry");
}
}

