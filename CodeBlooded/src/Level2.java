import processing.core.PApplet;


public class Level2 implements Scene {
    PApplet p;  // Reference to the PApplet instance
    int width;
    int height;

    
    private final int cellSize = 50;  
    private final int rows, cols;
    private Cell[][] grid;
    private boolean[][] visited;
    
    private int characterX = 0;  // Starting position of the character (top-left corner)
    private int characterY = 0;
    private final int characterSize = cellSize;  // Make the character fit inside a cell
    
    String password = "";


    public Level2(PApplet p, int width, int height) {
        this.p = p;
        this.width=width;
        this.width-=width%cellSize;
        this.height=height;
        this.rows = height / cellSize;
        this.cols = width / cellSize;
        this.grid = new Cell[cols][rows];
        this.visited = new boolean[cols][rows];

        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                grid[x][y] = new Cell(x, y);
            }
        }
        grid[0][0].walls[0] = false; // Break the top wall
        grid[0][0].walls[3] = false; // Break the left wall

    }

    int countdown = 60*3;
    public void setup() {
    	p.background(0,100,0);
    	generateMaze(0, 0);
    	
    	String CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    	// Define boundaries for the CPU box in terms of grid indices
    	int cpuStartX = (cols - 4) / 2;  // Since the CPU box is 4 cells wide
    	int cpuEndX = cpuStartX + 4;
    	int cpuStartY = (rows - 4) / 2;  // Since the CPU box is 4 cells tall
    	int cpuEndY = cpuStartY + 4;

    	for(int i=0; i<3; i++) {
    	    int x = (int) (p.random(grid.length));
    	    int y = (int) (p.random(grid[0].length));

    	    // Check if the x,y position is inside the CPU or if it's already a code block
    	    if(grid[x][y].isCodeBlock || (x >= cpuStartX && x < cpuEndX && y >= cpuStartY && y < cpuEndY)) {
    	        i--;  // Decrement the loop counter to try again
    	        continue;
    	    }

    	    grid[x][y].isCodeBlock = true;

    	    for(int j=0; j<3; j++) {
    	        grid[x][y].code += CHAR_SET.charAt((int) (Math.random()*CHAR_SET.length()));
    	    }
    	}

    	
    }

    public void draw() {
    	p.background(0,100,0);
    	drawMaze();
    	if(password.length()==9) {
    		MainSketch.switchScene(new Level3(p, width, height));
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
        
        int y = characterY/cellSize;
        int x = characterX/cellSize;
        if(grid[x][y].isCodeBlock) {
        	password+=grid[x][y].code;
        	grid[x][y].isCodeBlock = false;
        }
        
    }

    // Arrow keys press handlers
    void handleUpPress() {
        if(characterY > 0 && !grid[characterX/cellSize][characterY/cellSize].walls[0]) {
            characterY -= cellSize;
        }
    }

    void handleDownPress() {
        if(characterY < height - cellSize && !grid[characterX/cellSize][characterY/cellSize].walls[2]) {
            characterY += cellSize;
        }
    }

    void handleLeftPress() {
        if(characterX > 0 && !grid[characterX/cellSize][characterY/cellSize].walls[3]) {
            characterX -= cellSize;
        }
    }

    void handleRightPress() {
        if(characterX < width - cellSize && !grid[characterX/cellSize][characterY/cellSize].walls[1]) {
            characterX += cellSize;
        }
    }


    private void generateMaze(int cx, int cy) {
        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };
        shuffleArray(directions);

        visited[cx][cy] = true;

        // Define the CPU block area
        int boxSizeInCells = 4;
        int startX = (cols - boxSizeInCells) / 2;
        int startY = ((rows - boxSizeInCells) / 2)+2;
        int endX = startX + boxSizeInCells;
        int endY = startY + boxSizeInCells;

        // Check if the current cell is inside the CPU block
        if (cx >= startX && cx < endX && cy >= startY && cy < endY) {
            return;
        }

        for (int[] dir : directions) {
            int nx = cx + dir[0];
            int ny = cy + dir[1];

            // Check if the neighboring cell is inside the CPU block
            if (nx >= startX && nx < endX && ny >= startY && ny < endY) {
                continue;
            }

            if (nx >= 0 && ny >= 0 && nx < cols && ny < rows && !visited[nx][ny]) {
                grid[cx][cy].breakWall(dir[0], dir[1]);
                generateMaze(nx, ny);
            }
        }
    }
    private void drawMaze() {
        p.stroke(255, 215, 0);  // Gold color
        p.fill(0, 128, 0);     // Dark green color
        p.strokeWeight(3);

        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                grid[x][y].display();
            }
        }
        
        // Drawing a black box (CPU) in the center
        p.fill(0); // Black color
        p.stroke(255,255,255);
        p.strokeWeight(3);
        int boxSize = cellSize*4; // Size of the CPU box (can be adjusted)
        int centerX = (width - boxSize) / 2;
        int centerY = ((height/cellSize) * cellSize) / 2;
        p.rect(centerX, centerY, boxSize, boxSize);
        
        p.fill(255, 0, 0);
        p.noStroke();
        p.rect(characterX, characterY, characterSize, characterSize);
        
        p.fill(255); // White color for text
        p.textSize(30); // You can adjust this as needed
        p.text(password, centerX+(boxSize/10), centerY+(boxSize/2)+15); // Centering the text in the cell
    }

    class Cell {
        int x, y;
        boolean[] walls = {true, true, true, true};  // Top, Right, Bottom, Left
        boolean isCodeBlock = false;
        String code = "";

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
            
        }

        void breakWall(int dx, int dy) {
            if (dx == 1) {
                walls[1] = false;
                if (x + dx < cols) grid[x + dx][y].walls[3] = false;
            } else if (dx == -1) {
                walls[3] = false;
                if (x + dx >= 0) grid[x + dx][y].walls[1] = false;
            } else if (dy == 1) {
                walls[2] = false;
                if (y + dy < rows) grid[x][y + dy].walls[0] = false;
            } else if (dy == -1) {
                walls[0] = false;
                if (y + dy >= 0) grid[x][y + dy].walls[2] = false;
            }
        }


        void display() {
            int x1 = x * cellSize;
            int y1 = y * cellSize;
            int x2 = x1 + cellSize;
            int y2 = y1 + cellSize;

            if (walls[0]) {
                p.line(x1, y1, x2, y1);
            }
            if (walls[1]) {
                p.line(x2, y1, x2, y2);
            }
            if (walls[2]) {
                p.line(x2, y2, x1, y2);
            }
            if (walls[3]) {
                p.line(x1, y2, x1, y1);
            }
            p.ellipse(x1, y1, 5, 5);  // Circles at line intersections and corners
            if (isCodeBlock) {
                p.fill(255); // White color for text
                p.textSize(20); // You can adjust this as needed
                p.text(code, x1+2, (y1 + cellSize/2)+2); // Centering the text in the cell
            }
        }
    }
    private void shuffleArray(int[][] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = (int) p.random(i + 1);
            int[] temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

	@Override
	public void keyReleased() {
		// TODO Auto-generated method stub
		
	}


}
