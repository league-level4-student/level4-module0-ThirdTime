package _04_Maze_Maker;


import java.util.ArrayList;

import java.util.Random;

import java.util.Stack;


public class MazeMaker {


	private static int width;

	private static int height;


	private static Maze maze;


	private static Random randGen = new Random();

	private static Stack<Cell> uncheckedCells = new Stack<Cell>();


	public static Maze generateMaze(int w, int h) {

		width = w;

		height = h;

		maze = new Maze(width, height);


		// 4. select a random cell to start

		int randomXValue = randGen.nextInt(maze.cellArr.length);

		int randomYValue = randGen.nextInt(maze.cellArr[0].length);

		Cell startCell = maze.getCell(randomXValue, randomYValue);

		System.out.println("Column: " + randomXValue + " and Row: " + randomYValue);


		// 5. call selectNextPath method with the randomly selected cell

		selectNextPath(startCell);


		return maze;

	}


	// 6. Complete the selectNextPathMethod

	private static void selectNextPath(Cell currentCell) {

		// A. mark cell as visited

		currentCell.setBeenVisited(true);

		// B. Get an ArrayList of unvisited neighbors using the current cell and the

		// method below

		ArrayList<Cell> unvisitedNeighborsArr;

		unvisitedNeighborsArr = getUnvisitedNeighbors(currentCell);


		// C. if has unvisited neighbors,

		if (unvisitedNeighborsArr.size() > 0) {

			// C1. select one at random.

			int randomCell = randGen.nextInt(unvisitedNeighborsArr.size());

			Cell randomNeighbor = unvisitedNeighborsArr.get(randomCell);
			System.out.println("column of new: " + randomNeighbor.getX() + " and row of new: " + randomNeighbor.getY());


			// C2. push it to the stack

			uncheckedCells.push(randomNeighbor);


			// C3. remove the wall between the two cells

			removeWalls(currentCell, randomNeighbor);


			// C4. make the new cell the current cell and mark it as visited

			currentCell = randomNeighbor;

			currentCell.setBeenVisited(true);


			// C5. call the selectNextPath method with the current cell

			selectNextPath(currentCell);


		} else {

			// D. if all neighbors are visited

			if (unvisitedNeighborsArr.isEmpty()) {

				if (!uncheckedCells.empty()) {

					currentCell = uncheckedCells.pop();

					selectNextPath(currentCell);

				}

			}

			// D1. if the stack is not empty


			// D1a. pop a cell from the stack


			// D1b. make that the current cell


			// D1c. call the selectNextPath method with the current cell


		}

	}


	// 7. Complete the remove walls method.

	// This method will check if c1 and c2 are adjacent.

	// If they are, the walls between them are removed.

	private static void removeWalls(Cell c1, Cell c2) {

		

		if (c1.getX() == c2.getX()) {

			if (c1.getY() == c2.getY() - 1) {

				c1.setSouthWall(false);

				c2.setNorthWall(false);

			}


			if (c1.getY() == c2.getY() + 1) {

				c1.setNorthWall(false);

				c2.setSouthWall(false);

			}

		}


		if (c1.getY() == c2.getY()) {

			if (c1.getX() == c2.getX() - 1) {

				c1.setEastWall(false);

				c2.setWestWall(false);

			}


			if (c1.getX() == c2.getX() + 1) {

				c1.setWestWall(false);

				c2.setEastWall(false);

			}

		}

	}


	// 8. Complete the getUnvisitedNeighbors method

	// Any unvisited neighbor of the passed in cell gets added

	// to the ArrayList

	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> allNeighborsArr = new ArrayList<Cell>();

		if (c.hasNorthWall() && c.getY() > 0 && !maze.cellArr[c.getX()][c.getY() - 1].hasBeenVisited()) {
			//north cell
			allNeighborsArr.add(maze.cellArr[c.getX()][c.getY() - 1]);

		}


		if (c.hasSouthWall() && c.getY() < maze.getHeight() - 1 && !maze.cellArr[c.getX()][c.getY() + 1].hasBeenVisited()) {
			//south cell
			allNeighborsArr.add(maze.cellArr[c.getX()][c.getY() + 1]);

		}


		if (c.hasWestWall() && c.getX() > 0 && !maze.cellArr[c.getX() - 1][c.getY()].hasBeenVisited()) {
			//left cell
			allNeighborsArr.add(maze.cellArr[c.getX() - 1][c.getY()]);

		}


		if (c.hasEastWall() && c.getX() < maze.getWidth() - 1 && !maze.cellArr[c.getX() + 1][c.getY()].hasBeenVisited()) {
			//right cell
			allNeighborsArr.add(maze.cellArr[c.getX() + 1][c.getY()]);

		}


		return allNeighborsArr;

	}

}
	
	
	
