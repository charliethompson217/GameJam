import processing.core.*;

public class Zombie{
    PApplet parent;

    int x;
    int y;

    float r;
    float g;
    float b;

    int height;
    int width;

    int xVelocity;
    int yVelocity;

    int minX;
    int maxX;
    int minY;
    int maxY;

    float radarAngle = 0;  // initial angle for the radar line
    float radarLength = 200;  // length of the radar line
    float radarSpeed = (float) 0.05;  // how fast the radar line rotates
    
    PImage drone;

    Zombie(PApplet p, int x, int y, int maxX, int maxY, PImage drone){
        this.parent = p;
        this.drone=drone;

        this.x=x;
        this.y=y;

        this.r=255;
        this.g=255;
        this.b=255;

        this.height = (int) (0.05 * maxY);
        this.width = (int) (0.05 * maxY);

        this.xVelocity=0;
        this.yVelocity=0;

        this.minX=0;
        this.minY=0;
        this.maxX=maxX;
        this.maxY=maxY;
    }

    void Update(Boolean gameOver, Player player) {
        parent.fill(r, g, b);
        parent.image(drone, x, y);
        updateRadar(player);

        if(!gameOver) {
            x+=xVelocity;
            y+=yVelocity;
            if(y<minY || y>maxY-height) {
                yVelocity*=-1;
            }
            if(x<minX || x>maxX-width) {
                xVelocity*=-1;
            }
        }
    }

    void updateRadar(Player player) {

        float startX = x + width/2; // Center X
        float startY = y + height/2; // Center Y

        float endX = startX + radarLength * PApplet.cos(radarAngle);
        float endY = startY + radarLength * PApplet.sin(radarAngle);

        
        parent.stroke(0, 255, 0);  // Green color for radar line
        parent.strokeWeight(3);
        parent.line(startX, startY, endX, endY);

        // Update radar angle
        radarAngle += radarSpeed;

        if (radarIntersectsPlayer(endX, endY, player)) {
            float dx = player.x - x;
            float dy = player.y - y;
            float distance = PApplet.dist(x, y, player.x, player.y);

            xVelocity = (int) (2 * (dx / distance));
            yVelocity = (int) (2 * (dy / distance));
        }
    }

    boolean radarIntersectsPlayer(float endX, float endY, Player player) {
    	float startX = x + width/2; // Center X
        float startY = y + height/2; // Center Y

    	
        // Top edge
        if (lineIntersectsLine(startX, startY, endX, endY, player.x, player.y, player.x + player.width, player.y)) return true;
        // Bottom edge
        if (lineIntersectsLine(startX, startY, endX, endY, player.x, player.y + player.height, player.x + player.width, player.y + player.height)) return true;
        // Left edge
        if (lineIntersectsLine(startX, startY, endX, endY, player.x, player.y, player.x, player.y + player.height)) return true;
        // Right edge
        if (lineIntersectsLine(startX, startY, endX, endY, player.x + player.width, player.y, player.x + player.width, player.y + player.height)) return true;

        return false;
    }

    boolean lineIntersectsLine(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
        float det = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (det == 0) {
            return false; // lines are parallel
        }

        float lambda = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / det;
        float gamma = ((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / det;
        return (0 < lambda && lambda < 1) && (0 < gamma && gamma < 1);
    }

}
