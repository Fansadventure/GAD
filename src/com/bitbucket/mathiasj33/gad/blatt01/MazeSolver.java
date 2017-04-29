package com.bitbucket.mathiasj33.gad.blatt01;

import java.util.HashMap;
import java.util.Random;

public class MazeSolver {
	private boolean[][] maze;
	private int width;
	private int height;
	private static final HashMap<Vector, Vector> RIGHT_VECTORS = new HashMap<Vector, Vector>() {
		{
			put(new Vector(1,0), new Vector(0,1));
			put(new Vector(0,1), new Vector(-1,0));
			put(new Vector(-1,0), new Vector(0,-1));
			put(new Vector(0,-1), new Vector(1,0));
		}
	};
	
	public MazeSolver(boolean[][] maze) {
		this.maze = maze;
		this.width = maze.length;
		this.height = maze[0].length;
	}
	
	public void walk(int x, int y, int direction) { //Immer vorgehen, wenn wand direkt oder er berührt grade keine Wand: Sonderfall
		boolean[][] path = new boolean[width][height];
		Vector startPos = new Vector(1,0);
		Vector pos = startPos;
		Vector target = new Vector(width-1, height-2);
		Vector dir = new Vector(0,1);
		
		setPathAndDraw(pos, path);
		boolean startPosAlreadyVisited = false;
		
		while(!pos.equals(target)) {
			if(pos.equals(startPos) && startPosAlreadyVisited) { //position already visited
				System.out.println("Could not reach the target. Exiting.");
				return;
			}
			if(isWallInFront(pos, dir)) {
				dir = getLeftVector(dir);
			}
			else if(!touchingWallInFront(pos, dir)) {
				pos = pos.add(dir);
				setPathAndDraw(pos, path);
				dir = getRightVector(dir);
			}
			else {
				pos = pos.add(dir);
				setPathAndDraw(pos, path);
			}
			startPosAlreadyVisited = true;
		}
		
		System.out.println("Reached the target.");
	}
	
	private void setPathAndDraw(Vector pos, boolean[][] path) {
		path[pos.getX()][pos.getY()] = true;
		Maze.draw(pos.getX(), pos.getY(), maze, path);
	}
	
	private boolean isWallInFront(Vector pos, Vector dir) {
		Vector newPos = pos.add(dir);
		return maze[newPos.getX()][newPos.getY()];
	}
	
	private boolean touchingWallInFront(Vector pos, Vector dir) {
		Vector newPos = pos.add(dir).add(getRightVector(dir));
		return maze[newPos.getX()][newPos.getY()];
	}
	
	private Vector getLeftVector(Vector dir) {
		return getRightVector(dir).negate();
	}
	
	private Vector getRightVector(Vector dir) {
		return RIGHT_VECTORS.get(dir);
	}
	
	public static void main(String... args) {
		Random random = new Random();
		MazeSolver solver = new MazeSolver(Maze.generateMaze(random.nextInt(8) + 3, random.nextInt(8) + 3));
		solver.walk(0, 0, 0);
	}
}
