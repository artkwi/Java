/* Artur Kwiatkowski Snake */

package snake;

import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

import com.sun.prism.Image;

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
	public void getDirect (Game game) {
		if ((game.snakeFrame.key_pressed == 'w') && (game.stage.snake.direct != 1))
			game.stage.snake.direct=0;
		if ((game.snakeFrame.key_pressed == 's') && (game.stage.snake.direct != 0))
			game.stage.snake.direct=1;
		if ((game.snakeFrame.key_pressed == 'a') && (game.stage.snake.direct != 3))
			game.stage.snake.direct=2;
		if ((game.snakeFrame.key_pressed == 'd') && (game.stage.snake.direct != 2))
			game.stage.snake.direct=3;
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
		// load graphic of feed, resize it to button size
		ImageIcon icon_worm = new ImageIcon("worm.png");
		java.awt.Image img_worm = icon_worm.getImage() ; 
		java.awt.Image new_img_worm = img_worm.getScaledInstance(snakeFrame.snakePanel.buttons_array[0][0].getSize().width, snakeFrame.snakePanel.buttons_array[0][0].getSize().height,  java.awt.Image.SCALE_SMOOTH ) ;
		icon_worm = new ImageIcon(new_img_worm);
		// paint feed
		for (int i = 0 ; i < stage.size_x ; i++) {
			for (int j = 0 ; j < stage.size_y ; j++) {
				if ((i == stage.feed_x) && (j == stage.feed_y)) {
//					snakeFrame.snakePanel.buttons_array[i][j].setBackground(Color.BLACK);
					snakeFrame.snakePanel.buttons_array[i][j].setIcon(icon_worm);
				}
			}
		}
	}
	
	public void paintStage () {
		// paint stage one colour
		ImageIcon icon_grass = new ImageIcon("grass.jpg");
		for (int i = 0 ; i < stage.size_x ; i++) {
			for (int j = 0 ; j < stage.size_y ; j++) {
				snakeFrame.snakePanel.buttons_array[i][j].setIcon(icon_grass);;
			}
		}
		// paint snake
		ImageIcon icon_snake = new ImageIcon("snake.jpg");
		ImageIcon icon_snake_head;
		String string_snake_head = "snake.jpg";
		for (int i = 0 ; i < stage.snake.length ; i++) {
			// pain body
			snakeFrame.snakePanel.buttons_array[stage.x_position_list.get(i)][stage.y_position_list.get(i)].setIcon(icon_snake);
			// paint head depend on moving
			if (i==0) {
				// default
				icon_snake_head = new ImageIcon(string_snake_head);
				if (stage.snake.direct==0) {
					icon_snake_head = new ImageIcon("snake_head_0.jpg");
				}
				// up
				if (stage.snake.direct==1) {
					icon_snake_head = new ImageIcon("snake_head_1.jpg");
				}
				// left
				if (stage.snake.direct==2) {
					icon_snake_head = new ImageIcon("snake_head_2.jpg");
				}
				// right
				if (stage.snake.direct==3) {
					icon_snake_head = new ImageIcon("snake_head_3.jpg");
				}
				snakeFrame.snakePanel.buttons_array[stage.x_position_list.get(i)][stage.y_position_list.get(i)].setIcon(icon_snake_head);
			}
		}
		// paint feed
		paintFeed();
	}
	
	public static void main(String[] args) {
		// new game - stage 10x10 with speed 1
		Game game = new Game(10,10,1);		
		
		// feed appears in List
		game.stage.spawnFeed_xy();
		
		// paint stage and feed at beginning
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
			// get direct from keyboard
			game.getDirect(game);
			// paint game again
			game.move(game);
			game.paintStage();
			
			// leave loop if game is over
			if (game.game_over)
				break;
		}

}
}

