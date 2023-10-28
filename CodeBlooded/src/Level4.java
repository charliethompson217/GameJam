import java.util.ArrayList;
import processing.core.*;

public class Level4 implements Scene {
    PApplet p;  // Reference to the PApplet instance
    Player player;
    Boolean gameOver = false;
    int width;
    int height;

    PImage playerHelicopter;
    PImage background;
    PImage target;
    PImage block1;
    PImage block2;
    PImage block3;
    PImage block4;
    PGraphics backgroundGraphics;
    
    Target targets[];
    GrumpyBlock blocks[];
    ArrayList<Bullet> bullets = new ArrayList<>();
    
    boolean shooting = false;

    public Level4(PApplet p, int width, int height) {
        this.p = p;
        this.width = width;
        this.height = height;
        playerHelicopter = p.loadImage("level1Player.png");
        background = p.loadImage("level3background.png");
        target = p.loadImage("HotAirBalloon.png");
        block1 = p.loadImage("block1.png");
        block2 = p.loadImage("block2.png");
        block3 = p.loadImage("block3.png");
        block4 = p.loadImage("block4.png");
    }

    int countdown = 60 * 3;

    public void setup() {
        player = new Player(p, 0, height / 2 - playerHelicopter.height / 2, width, height, playerHelicopter, 0.1, 0.1);

        p.background(255, 0, 0);
        backgroundGraphics = p.createGraphics(width, height);
        backgroundGraphics.beginDraw();
        backgroundGraphics.image(background, 0, 0);
        backgroundGraphics.endDraw();

        targets = new Target[5];
        for (int i = 0; i < targets.length; i++) {
            int speed = 4 + i * 2;
            if (i % 2 == 0) speed *= -1;
            targets[i] = new Target(p, i * (width / 6) + (width / 10), height / 2, width, height, target, 0.1, 0.1, speed);
        }

        int blocksPerHeight = height / 80;
        blocks = new GrumpyBlock[blocksPerHeight * 4];
        for (int i = 0; i < blocksPerHeight; i++) {
            blocks[i * 4] = new GrumpyBlock(p, block1, (width / 6) * 1, i * 80);
            blocks[i * 4 + 1] = new GrumpyBlock(p, block2, (width / 6) * 2, i * 80);
            blocks[i * 4 + 2] = new GrumpyBlock(p, block3, (width / 6) * 3, i * 80);
            blocks[i * 4 + 3] = new GrumpyBlock(p, block4, (width / 6) * 4, i * 80);
        }
    }


    int frame = 0;

    public void draw() {
        frame++;
        p.background(255, 0, 0);
        p.image(backgroundGraphics, 0, 0);
        player.Update(gameOver);
        
        int c = 0;
        for (int i = 0; i < targets.length; i++) {
            if (targets[i] != null) targets[i].Update();
            else c++;
        }
        
        if (c == targets.length) gameOver = true;
        
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            bullet.Update();
            if (bullet.x > width) {
                bullets.remove(i);
            }
            
            int count = 0;
            for (int j = 0; j < targets.length; j++) {
                Target currentTarget = targets[j];
                if (currentTarget != null) {
                    if (bullet.collidesWith(currentTarget.x, currentTarget.y, currentTarget.width, currentTarget.height)) {
                        bullets.remove(i);
                        targets[j] = null;
                        break;
                    }
                } else {
                    count++;
                }
            }
            
            if (count == targets.length) {
                gameOver = true;
            }
            
            for (int j = 0; j < blocks.length; j++) {
                GrumpyBlock currentBlock = blocks[j];
                if (!currentBlock.hit) {
                    if (bullet.collidesWith(currentBlock.x, currentBlock.y, currentBlock.width, currentBlock.height)) {
                        bullets.remove(i);
                        currentBlock.hit = true;
                        break;
                    }
                }
            }
        }
        
        if (shooting && frame % 20 == 0) {
            Bullet newBullet = new Bullet(p, player.x, player.y + 30);
            bullets.add(newBullet);
        }
        
        for (int i = 0; i < blocks.length; i++) {
            blocks[i].Update();
        }
        
        for (GrumpyBlock currentBlock : blocks) {
            if (player.collidesWith(currentBlock.x, currentBlock.y, currentBlock.width, currentBlock.height) && !currentBlock.hit) {
                currentBlock.hit = true;
                gameOver = true;
                break;
            }
        }
        
        for (int i = 0; i < targets.length; i++) {
            if (targets[i] != null && player.collidesWith(targets[i].x, targets[i].y, targets[i].width, targets[i].height)) {
                targets[i] = null;
                break;
            }
        }

        if (gameOver) {
            countdown--;
            p.fill(255, 255, 255);
            p.textSize(128);
            p.textAlign(PApplet.CENTER, PApplet.CENTER);
            p.text("Player Lost", width / 2, height / 2);
            if (countdown == 0) {
                MainSketch.switchScene(new Level4(p, width, height));
            }
        }
        
        if (player.gameWon) {
            MainSketch.switchScene(new Level1(p, width, height));
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
                case ' ':
                    shooting = true;
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
                case ' ':
                    shooting = false;
                    break;
            }
        }
    }

    void handleLeftPress() {
    	player.xVelocity=-3;
    }

    void handleRightPress() {
    	player.xVelocity=3;
    }
    void handleUpPress() {
        player.yVelocity=-3;
    }

    void handleDownPress() {
    	player.yVelocity=3;
    }
    
    void handleLeftRelease() {
    	player.xVelocity=0;
    }

    void handleRightRelease() {
    	player.xVelocity=0;
    }

    void handleUpRelease() {
    	player.yVelocity=0;
    }

    void handleDownRelease() {
    	player.yVelocity=0;
    }
}
