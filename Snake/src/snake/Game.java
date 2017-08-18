/* Artur Kwiatkowski Snake */

package snake;

import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

import com.sun.prism.Image;

public class Game {

	public Stage stage;
	public SnakeFrame snakeFrame;
	public Boolean game_over;
	public Boolean game_start;
	private  int score;
	
	public Game() {
		
	}	
	
	public Game(int size_x, int size_y, int speed) {
		stage = new Stage(size_x,size_y,speed);
		snakeFrame = new SnakeFrame();
		game_over = false;
		game_start = false;
		score = 0;
	}
	
	// get direct with forward forbiddance
	public void getDirect (Game game) {
		if ((game.snakeFrame.key_pressed == 'w') && (game.stage.snake.getDirect() != 1))
			game.stage.snake.setDirect(0);
		if ((game.snakeFrame.key_pressed == 's') && (game.stage.snake.getDirect() != 0))
			game.stage.snake.setDirect(1);
		if ((game.snakeFrame.key_pressed == 'a') && (game.stage.snake.getDirect() != 3))
			game.stage.snake.setDirect(2);
		if ((game.snakeFrame.key_pressed == 'd') && (game.stage.snake.getDirect() != 2))
			game.stage.snake.setDirect(3);
	}
	
	
	// check collisions
	public Boolean checkCollision (Game game) {
		Boolean collision = false;
		int direct = game.stage.snake.getDirect();
		int head_x = game.stage.snake.getHead_x();
		int head_y = game.stage.snake.getHead_y();
		
		//border collisions
		if ((direct == 0) && (game.stage.snake.getHead_x()-1<0))
			collision = true;
		else if ((direct == 1 ) && (game.stage.snake.getHead_x()+2>game.stage.getSize_x()))
			collision = true;
		else if ((direct == 2 ) && (game.stage.snake.getHead_y()-1<0))
			collision = true;
		else if ((direct == 3 ) && (game.stage.snake.getHead_y()+2>game.stage.getSize_y()))
			collision = true;
		
		// body collisions
		else if (direct == 0) {
			for (int i = 2; i < game.stage.snake.getLength(); i++ ) {
				if ((game.stage.getX_position_list().get(i) == head_x - 1) && (game.stage.getY_position_list().get(i) == head_y))
					collision = true;
			}
		}
		else if (direct == 1) {
			for (int i = 2; i < game.stage.snake.getLength(); i++ ) {
				if ((game.stage.getX_position_list().get(i) == head_x + 1) && (game.stage.getY_position_list().get(i) == head_y))
					collision = true;
			}
		}
		else if (direct == 2) {
			for (int i = 2; i < game.stage.snake.getLength(); i++ ) {
				if ((game.stage.getY_position_list().get(i) == head_y - 1)  && (game.stage.getX_position_list().get(i) == head_x))
					collision = true;
			}
		}
		else if (direct == 3) {
			for (int i = 2; i < game.stage.snake.getLength(); i++ ) {
				if ((game.stage.getY_position_list().get(i) == head_y + 1) && (game.stage.getX_position_list().get(i) == head_x))
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
			if (game.stage.snake.getDirect()==0) {
				game.stage.snake.setDownHead_x();;
			}
			// up
			if (game.stage.snake.getDirect()==1) {
				game.stage.snake.setUpHead_x();;
			}
			// left
			if (game.stage.snake.getDirect()==2) {
				game.stage.snake.setDownHead_y();
			}
			// right
			if (game.stage.snake.getDirect()==3) {
				game.stage.snake.setUpHead_y();;
			}
		}

		// add x and y position of head to current snake
		game.stage.getX_position_list().addFirst(game.stage.snake.getHead_x());
		game.stage.getY_position_list().addFirst(game.stage.snake.getHead_y());
		
		if ((game.stage.snake.getHead_x() == game.stage.getFeed_x()) && (game.stage.snake.getHead_y() == game.stage.getFeed_y())) {
			// spawn feed
			game.stage.eatFeed();
			game.paintFeed();
			game.setScore(getScore()+1);
			game.snakeFrame.snakePanelInformation.setLscore(game.getScore());
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
		for (int i = 0 ; i < stage.getSize_x() ; i++) {
			for (int j = 0 ; j < stage.getSize_y() ; j++) {
				if ((i == stage.getFeed_x()) && (j == stage.getFeed_y())) {
					snakeFrame.snakePanel.buttons_array[i][j].setIcon(icon_worm);
				}
			}
		}
	}
	
	public void paintStage () {
		// paint stage one colour
		ImageIcon icon_grass = new ImageIcon("grass.jpg");
		for (int i = 0 ; i < stage.getSize_x() ; i++) {
			for (int j = 0 ; j < stage.getSize_y() ; j++) {
				snakeFrame.snakePanel.buttons_array[i][j].setIcon(icon_grass);;
			}
		}
		// paint snake
		ImageIcon icon_snake = new ImageIcon("snake.jpg");
		ImageIcon icon_snake_head;
		String string_snake_head = "snake.jpg";
		for (int i = 0 ; i < stage.snake.getLength(); i++) {
			// pain body
			snakeFrame.snakePanel.buttons_array[stage.getX_position_list().get(i)][stage.getY_position_list().get(i)].setIcon(icon_snake);
			// paint head depend on moving
			if (i==0) {
				// default
				icon_snake_head = new ImageIcon(string_snake_head);
				if (stage.snake.getDirect()==0) {
					icon_snake_head = new ImageIcon("snake_head_0.jpg");
				}
				// up
				if (stage.snake.getDirect()==1) {
					icon_snake_head = new ImageIcon("snake_head_1.jpg");
				}
				// left
				if (stage.snake.getDirect()==2) {
					icon_snake_head = new ImageIcon("snake_head_2.jpg");
				}
				// right
				if (stage.snake.getDirect()==3) {
					icon_snake_head = new ImageIcon("snake_head_3.jpg");
				}
				snakeFrame.snakePanel.buttons_array[stage.getX_position_list().get(i)][stage.getY_position_list().get(i)].setIcon(icon_snake_head);
			}
		}
		// paint feed
		paintFeed();
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public static void main(String[] args) {
		// new game - stage 10x10 with speed 1
		Game game = new Game(10,10,1);	
		
		// loop waiting for start game
		while (!game.snakeFrame.snakePanelStartGame.getGame_start()) {
			
		};
		game.snakeFrame.startGame();
		// feed appears in List
		game.stage.spawnFeed_xy();
		
		// paint stage and feed at beginning
		game.paintStage();
		game.paintFeed();
		// moving in loop
		while (true) {	
			// speed delay
			try {
				TimeUnit.MILLISECONDS.sleep(1000/game.snakeFrame.snakePanelStartGame.getSpeed());
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

