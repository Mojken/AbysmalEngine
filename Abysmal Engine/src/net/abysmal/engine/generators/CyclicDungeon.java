package net.abysmal.engine.generators;

import java.util.ArrayList;

public class CyclicDungeon {

	Cycle main = new Cycle();
// main = c.GenerateBaseCycle(sizes, lengths, directions);
}

class Cycle {

	ArrayList<Node> nodes;
	ArrayList<Path> paths;

	public Cycle(Node[] nodes, Path[] paths) {
		for (Node n:nodes)
			this.nodes.add(n);
		for (Path p:paths)
			this.paths.add(p);
	}

	public Cycle() {}

	public void GenerateBaseCycle(int[] sizes, int[] lengths, int[] directions) {
		nodes.add(Node.CreateStartNode(sizes[0]));
		nodes.add(Node.CreateEndNode(sizes[1]));
		paths.add(new Path(lengths[0], directions[0], nodes.get(0), nodes.get(1)));
		paths.add(new Path(lengths[1], directions[1], nodes.get(0), nodes.get(1)));
	}

	public Cycle AddCycle(Path a, Path b, int[] sizes, int[] lengths, int[] directions, int[] places) {
		Node ia = Node.CreateIntersection(sizes[0]), ib = Node.CreateIntersection(sizes[1]);
		Path l = new Path(lengths[0], directions[0], ia, ib);
		ia.c = l;
		ib.c = l;
		AddNode(a, ia, places[0]);
		AddNode(b, ib, places[0]);
		return new Cycle(new Node[] { ia, ib }, new Path[] { l });
	}

	public void AddNode(Path p, Node n, int place) {
		nodes.add(n);
		paths.add(p.AddNode(n, place));
	}

}

class Node {

	int size;
	Path a, b, c;
	boolean start = false, end = false, intersection = false;

	public Node(int size) {
		this.size = size;
	}

	public static Node CreateStartNode(int size) {
		Node n = new Node(size);
		n.start = true;
		return n;
	}

	public static Node CreateEndNode(int size) {
		Node n = new Node(size);
		n.end = true;
		return n;
	}

	public static Node CreateIntersection(int size) {
		Node n = new Node(size);
		n.intersection = true;
		return n;
	}

}

class Path {

	int length;
	/** For one-way paths. 0 is default, 1 is clockwise and 2 is counter-clockwise **/
	int direction;
	Node a, b;

	public Path(int lenght, int direction, Node a, Node b) {
		this.length = lenght;
		this.direction = direction;
		this.a = a;
		this.b = b;
	}

	public Path(int length, Node a, Node b) {
		this.length = length;
		this.direction = 0;
		this.a = a;
		this.b = b;
	}

	public Path AddNode(Node n, int place) {
		if (place < length) {
			Path np = new Path(place, a, n);
			n.b = this;
			n.a = np;
			if (a.a == this) {
				a.a = np;
			} else if (a.b == this) {
				a.b = np;
			} else a.c = np;
			this.a = n;
			length -= place;
			return np;
		} return null;
	}
}