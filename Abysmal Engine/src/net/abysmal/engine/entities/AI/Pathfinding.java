package net.abysmal.engine.entities.AI;

import java.util.ArrayList;
import net.abysmal.engine.maths.Vector;

public class Pathfinding {

	int width;
	Node[] nodes;
	ArrayList<Node> open = new ArrayList<Node>(), closed = new ArrayList<Node>(), pathNodes = new ArrayList<Node>();
	public ArrayList<Integer> pathIndexes = new ArrayList<Integer>();
	Node goal;
	public static int[][] costs = new int[100000][3];

	public static Pathfinding findPath(int startIndex, int endIndex, int[] weights, boolean[] traversable, int width) {
		Node[] nodes = new Node[weights.length];
		Node end = new Node(weights[endIndex], endIndex, width);
		Node start = new Node(weights[startIndex], startIndex, end, width);
		for (int i = 0; i < weights.length; i++) {
			nodes[i] = new Node(weights[i], i, traversable[i], end, width);
		}
		return new Pathfinding(width, start, end, nodes);

	}

	public Pathfinding(int width, Node start, Node goal, Node[] nodes) {
		this.width = width;
		this.goal = goal;
		open.add(start);
		this.nodes = nodes;
		while (true) {
			Node current = open.get(0);
			for (Node s:open) {
				if (s.tCost < current.tCost || (s.tCost == current.tCost && s.hCost < current.hCost)) {

					current = s;
				} else {
				}
			}
			open.remove(current);
			closed.add(current);
			if (current.id == goal.id) {
				while (current.id != start.id) {
					pathNodes.add(current);
					current = current.parent;
				}
				pathIndexes.add(start.id);
				Pathfinding.costs[0] = new int[] {start.gCost, start.hCost, start.tCost};
				for (int i = pathNodes.size() - 1; i >= 0; i--) {
					pathIndexes.add(pathNodes.get(i).id);
					Pathfinding.costs[pathNodes.size()-i] = new int[] {pathNodes.get(i).gCost, pathNodes.get(i).hCost, pathNodes.get(i).tCost};
//					System.out.println(Pathfinding.costs[pathNodes.get(i)]);
				}
				break;
			}

			for (Node n:getNeighbor(current)) {
				if (null == n) continue;
				if (!n.traversable) continue;

				boolean isin = false;
				
				for (Node c:closed)
					if (c.id == n.id) {
						isin = true;
						break;
					}
				
				if (isin) continue;

				int[] costs = n.calculateCosts(current, goal, width);
				int pos = -1;
				for (Node c:open)
					if (c.id == n.id) {
						pos = open.indexOf(c);
						break;
					}

				if (!open.contains(n) && pos == -1) {
					n.parent = current;
					open.add(n);
					n.gCost = costs[0];
					n.hCost = costs[1];
					n.tCost = costs[2];
				} else if (open.get(pos).tCost > costs[2]) {
					n.parent = current;
				}
			}
		}
	}

	Node[] getNeighbor(Node n) {
		Node a = n.id > width ? nodes[n.id - width]:null;
		Node b = n.id % width != 0 ? nodes[n.id - 1]:null;
		Node c = width - 1 != n.id % width ? nodes[n.id + 1]:null;
		Node d = (n.id + width) < nodes.length ? nodes[n.id + width]:null;
		if (a == null && b == null && c == null && d == null) {
			System.err.println("No neighbors!");
			System.exit(0);
		}
		return new Node[] { a, b, c, d };
	}
}

class Node {

	// TODO consider changing weight to HugeInteger. Probably not needed
	Node parent;
	int hCost, gCost = 1, weight, tCost, id;
	boolean traversable;

	public Node(int weight, int id, int width) {
		this(weight, id, null, width);
	}

	public Node(int weight, int id, Node end, int width) {
		this(weight, id, true, end, width);
	}

	public Node(int weight, int id, boolean traversable, Node end, int width) {
		this.weight = weight;
		this.id = id;
		this.traversable = traversable;
		int[] costs = calculateCosts(parent, end, width);
		gCost = costs[0];
		hCost = costs[1];
		tCost = costs[2];
	}

	int[] calculateCosts(Node p, Node end, int width) {
		int gc = weight;
		if (p != null) gc = (int)(p.gCost + weight);
		int hc = calculateHeuristicCost(end, width);
		int tc = gc + hc;

		
		int[] costs = {gc, hc, tc};
//		Pathfinding.costs[id] = costs;
		return costs;
	}

	int calculateHeuristicCost(Node end, int width) {
		if (end == null) return 0;
		Vector curPos = new Vector(id % width, (int) (id / width));
		Vector endPos = new Vector(end.id % width, (int) (end.id / width));

		return (int) (Math.abs(curPos.x - endPos.x) + Math.abs(curPos.y - endPos.y));
	}
}