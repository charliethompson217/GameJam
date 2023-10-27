import processing.core.*;

public class Level1 implements Scene {
    PApplet p;  // Reference to the PApplet instance
    Player player;

    public Level1(PApplet p) {
        this.p = p;
        player = new Player(p, 0, 225);
    }

    public void setup() {
    	p.background(255,0,0);
        player =  new Player(p, 0,225);
    }

    public void draw() {
    	p.background(255,0,0);
    	player.Update();
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
}
