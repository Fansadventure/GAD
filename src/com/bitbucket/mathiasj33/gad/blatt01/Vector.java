package com.bitbucket.mathiasj33.gad.blatt01;

public class Vector {
	private int x;
	private int y;
	
	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector add(Vector other) {
		return new Vector(x + other.x, y + other.y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Vector negate() {
		return new Vector(-x, -y);
	}
	
	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
