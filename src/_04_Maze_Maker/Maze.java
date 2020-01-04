

package _04_Maze_Maker;

import java.awt.Graphics;


public class Maze {

	//1. Create a 2D array of cells. Don't initialize it.

	Cell [][] cellArr;

	private int width;

	private int height;


	public Maze(int w, int h) {

		this.width = w;

		this.height = h;


		//2. Initialize the cells using the width and height varibles

		cellArr = new Cell [width][height];

		//3. Iterated through each cell and initialize it

		for(int i = 0; i < cellArr.length; i++) {

			for(int j = 0; j < cellArr[0].length; j++) {

				cellArr[i][j] = new Cell(i,j);

			}

		}

		//   using i and j as the location

		

	}


	//4. This method iterates through the cells and draws them

	public void draw(Graphics g) {

		for(int i = 0; i < cellArr.length; i++) {

			for(int j = 0; j < cellArr[0].length; j++) {

				cellArr[i][j].draw(g);

			}

		}

	}

	

	//4b. This method returns the selected cell.

	public Cell getCell(int x, int y){

		return  cellArr[x][y];

	}

	

	public int getWidth() {

		return width;

	}


	public int getHeight() {

		return height;

	}

}