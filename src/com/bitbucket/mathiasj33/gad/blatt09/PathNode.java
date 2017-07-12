package com.bitbucket.mathiasj33.gad.blatt09;

public class PathNode {
	public AVLTreeNode node;
	public Direction parentDecision;
	
	public PathNode(AVLTreeNode node, Direction parentDecision) {
		this.node = node;
		this.parentDecision = parentDecision;
	}
}
