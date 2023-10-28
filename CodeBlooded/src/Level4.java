import java.util.ArrayList;

import processing.core.*;

public class Level4 implements Scene {
    PApplet p;  // Reference to the PApplet instance

    int width;
    int height;

    PImage background;

    PGraphics backgroundGraphics;

    
    boolean shooting = false;

    public Level4(PApplet p, int width, int height) {
        this.p = p;
        this.width=width;
        this.height=height;
        background = p.loadImage("level3background.png");
    }

    int countdown = 60*3;
    public void setup() {
    	
    	p.background(255,0,0);
    	backgroundGraphics = p.createGraphics(width, height);
    	backgroundGraphics.beginDraw();
    	backgroundGraphics.image(background, 0, 0);
    	backgroundGraphics.endDraw();
    }
    int frame=0;
    public void draw() {
    	frame++;
    	p.background(255,0,0);
    	p.image(backgroundGraphics, 0, 0);
    	
        
    	
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
                case ' ':
                	shooting=true;
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
                case ' ':
                	shooting=false;
                	break;
            }
        }
    }

    void handleUpPress() {
        
    }

    void handleDownPress() {
    	
    }

    void handleUpRelease() {
    	
    }

    void handleDownRelease() {
    	
    }
    
    

}
