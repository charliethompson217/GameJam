import processing.core.*;

public class Level1 implements Scene {
    PApplet p;  // Reference to the PApplet instance
    Player player;
    Zombie zombies[];
    Boolean gameOver = false;
    int width;
    int height;
    int numZombies = 10;
    PImage drone;
    PImage playerHelicopter;
    PImage background;
    PImage end;
    PGraphics backgroundGraphics;

    public Level1(PApplet p, int width, int height) {
        this.p = p;
        this.width=width;
        this.height=height;
        drone = p.loadImage("drone.png");
        playerHelicopter = p.loadImage("level1Player.png");
        background = p.loadImage("level1background.png");
        end = p.loadImage("computer.png");
    }

    int countdown = 60*3;
    public void setup() {
    	player = new Player(p, 0, 225, width, height, playerHelicopter, 0.061, 0.083);
    	p.background(255,0,0);
    	backgroundGraphics = p.createGraphics(width, height);
    	backgroundGraphics.beginDraw();
    	backgroundGraphics.image(background, 0, 0, width, height);
    	backgroundGraphics.endDraw();
    	
    	zombies = new Zombie[numZombies];
    	int sectionWidth = width / numZombies;

    		
		int playerOffSet = (int) (0.083*width);
		
		for (int i = 0; i < numZombies; i++) {
			int direction = 1;
			if(i%2==0) {
				direction = -1;
			}
			
			
	        int startX = i * sectionWidth;
	        int endX = startX + sectionWidth;

	        // Choose a random position within this section for the x-coordinate
	        int zombieX = (int) p.random(startX, endX - (int) (0.05 * width) ); 
	        // Choose a random position for the y-coordinate
	        int zombieY = (int) p.random(0, height - (int) (0.05 * height));  
	        zombies[i] = new Zombie(p, zombieX+playerOffSet, zombieY, width, height, drone);
	        zombies[i].yVelocity = 3*direction;
	    }
    	
    }

    public void draw() {
    	p.background(255,0,0);
    	p.image(backgroundGraphics, 0, 0);
    	player.Update(gameOver);
    	for (int i=0; i<numZombies; i++) {
    		zombies[i].Update(gameOver, player);
    		if (collidesWith(player, zombies[i])) {
                // Handle collision here (e.g., end the game, reduce player's health, etc.)
                gameOver=true;
            }
    	}
    	p.fill(0, 0, 0);
    	p.image(end, width-(int) (0.08 * height), height/2, (int) (0.08 * height), (int) (0.08 * height));
    	if (player.x + player.width > (width-(int) (0.08 * height)) && player.y + player.height > (height/2) && player.y < height/2 + ((int) (0.08 * height))) {
    	    MainSketch.switchScene(new Level2(p, width, height));
    	}
    	if(gameOver) {
    		countdown--;
    		p.fill(255, 255, 255);
    		p.textSize(128);
    		p.textAlign(PApplet.CENTER, PApplet.CENTER); // sets the text alignment to center
    		p.text("Player Lost", width/2, height/2);
    		if(countdown == 0) {
    	        MainSketch.switchScene(new Level1(p, width, height));
    	    }
    	}
    	
    }

    public void keyPressed() {
        if (p.key == PApplet.CODED) {
            switch (p.keyCode) {
                case PApplet.UP:
                    handleUpPress();
                    break;
                case PApplet.DOWN:
                    handleDownPress();
                    break;
                case PApplet.LEFT:
                    handleLeftPress();
                    break;
                case PApplet.RIGHT:
                    handleRightPress();
                    break;
            }
        } else {
            switch (p.key) {
                case 'W':
                case 'w':
                    handleUpPress();
                    break;
                case 'S':
                case 's':
                    handleDownPress();
                    break;
                case 'A':
                case 'a':
                    handleLeftPress();
                    break;
                case 'D':
                case 'd':
                    handleRightPress();
                    break;
            }
        }
    }

    public void keyReleased() {
        if (p.key == PApplet.CODED) {
            switch (p.keyCode) {
                case PApplet.UP:
                    handleUpRelease();
                    break;
                case PApplet.DOWN:
                    handleDownRelease();
                    break;
                case PApplet.LEFT:
                    handleLeftRelease();
                    break;
                case PApplet.RIGHT:
                    handleRightRelease();
                    break;
            }
        } else {
            switch (p.key) {
                case 'W':
                case 'w':
                    handleUpRelease();
                    break;
                case 'S':
                case 's':
                    handleDownRelease();
                    break;
                case 'A':
                case 'a':
                    handleLeftRelease();
                    break;
                case 'D':
                case 'd':
                    handleRightRelease();
                    break;
            }
        }
    }

    // Arrow keys press handlers
    void handleUpPress() {
        player.yVelocity=-2;
    }

    void handleDownPress() {
    	player.yVelocity=2;
    }

    void handleLeftPress() {
    	player.xVelocity=-2;
    }

    void handleRightPress() {
    	player.xVelocity=2;
    }

    // Arrow keys release handlers
    void handleUpRelease() {
    	player.yVelocity=0;
    }

    void handleDownRelease() {
    	player.yVelocity=0;
    }

    void handleLeftRelease() {
    	player.xVelocity=0;
    }

    void handleRightRelease() {
    	player.xVelocity=0;
    }
    boolean collidesWith(Player player, Zombie zombie) {
        // Calculate the reduced hitbox dimensions and position for the player
        float hitboxWidth = player.width / 3.0f;
        float hitboxHeight = player.height / 3.0f;
        
        float hitboxX = player.x + (player.width - hitboxWidth) / 2.0f; // centered horizontally
        float hitboxY = player.y+10; // at the top

        // Adjust the collision checks using the smaller hitbox dimensions and position
        return hitboxX < zombie.x + zombie.width &&
               hitboxX + hitboxWidth > zombie.x &&
               hitboxY < zombie.y + zombie.height &&
               hitboxY + hitboxHeight > zombie.y;
    }


}
