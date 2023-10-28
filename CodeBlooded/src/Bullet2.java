import processing.core.*;

public class Bullet2 {
    PApplet parent;
    float x, y;
    float speed = 5;
    float angle;

    Bullet2(PApplet p, float startX, float startY, float targetX, float targetY) {
        this.parent = p;
        this.x = startX;
        this.y = startY;

        // Calculate angle to target (mouse position)
        this.angle = PApplet.atan2(targetY - startY, targetX - startX);
    }

    void update() {
        x += PApplet.cos(angle) * speed;
        y += PApplet.sin(angle) * speed;
    }

    void display() {
        parent.stroke(255);
        parent.fill(255);
        parent.ellipse(x, y, 5, 5);
    }
}