package _03_Conways_Game_of_Life;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;

	private Timer timer;

	//1. Create a 2D array of Cells. Do not initialize it.
	Cell [] [] cellArr;


	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;

//2. Calculate the cell size.
		cellSize = h / cellsPerRow;
//3. Initialize the cell array to the appropriate size.
		cellArr = new Cell [cellsPerRow] [cellsPerRow];
//3. Iterate through the array and initialize each cell.
//   Don't forget to consider the cell's dimensions when
//   passing in the location.
		for(int cellRow = 0; cellRow < cellArr.length; cellRow++){
			for(int cellCol = 0; cellCol < cellArr.length; cellCol++){
					cellArr[cellRow][cellCol] = new Cell(cellRow * cellSize, cellCol * cellSize, cellSize);
			}
		}
		randomizeCells();
	}

	public void randomizeCells() {
		Random randomObject = new Random();
		for(int row = 0; row< cellArr.length; row++) {
			for(int column = 0; column< cellArr.length; column++) {
				int rand = randomObject.nextInt(2);
				if(rand == 1) {
					cellArr[row][column].isAlive = true;
				} else {
					cellArr[row][column].isAlive = false;
				}
			}
		}
//4. Iterate through each cell and randomly set each
//   cell's isAlive member to true of false

		repaint();
	}

	public void clearCells() {
//5. Iterate through the cells and set them all to dead.
		for(Cell cellRow []: cellArr){
			for(Cell cellCol: cellRow) {
				cellCol.isAlive = false;
			}
		}
		repaint();
	}

	public void startAnimation() {
		timer.start();
	}

	public void stopAnimation() {
		timer.stop();
	}

	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}

	@Override
	public void paintComponent(Graphics g) {
//6. Iterate through the cells and draw them all
		for(int i = 0; i < cellArr.length-1; i++) {
			for(int j = 0; j < cellArr[i].length-1; j++) {
				cellArr[i][j].draw(g);
			}
		}

// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

	//advances world one step
	public void step() {
//7. iterate through cells and fill in the livingNeighbors array
// . using the getLivingNeighbors method.
		int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];
		for(int x = 0; x<livingNeighbors.length; x++) {
			for(int y = 0; y<livingNeighbors.length; y++) {
				livingNeighbors[x][y] = getLivingNeighbors(x,y);
			}
		}
//8. check if each cell should live or die
		for(int row = 0; row< cellArr.length; row++) {
			for(int column = 0; column< cellArr.length; column++) {
				System.out.println(cellArr[row][column].isAlive);
				cellArr[row][column].liveOrDie(getLivingNeighbors(row, column));
			}
		}



		repaint();
	}

	//9. Complete the method.
//   It returns an int of 8 or less based on how many
//   living neighbors there are of the
//   cell identified by x and y
	public int getLivingNeighbors(int x, int y){
		int totalNumNeighbors = 0;
		for(int i = x-1; i < x+1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (j > 0 && i > 0 && i < cellArr.length && j < cellArr[0].length) {
					System.out.println(cellArr[i][j].isAlive);
					if (cellArr[i][j].isAlive) {
						totalNumNeighbors = totalNumNeighbors + 1;
					}
				}
			}
		}
		//if(cellArr[x][y].isAlive){
	//		totalNumNeighbors -= 1;
		//}
		return totalNumNeighbors;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
//10. Use e.getX() and e.getY() to determine
//    which cell is clicked. Then toggle
//    the isAlive variable for that cell.
		cellArr[e.getX() / cellSize][e.getY() / cellSize].isAlive = !cellArr[e.getX() / cellSize][e.getY() / cellSize].isAlive;

		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();
	}
}
