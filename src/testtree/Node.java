package testtree;

import testtree.Pair;
import testtree.Candidate;
import java.util.HashMap;
import java.util.Map;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class Node {
    //private Node father;
    private double weight;
    private Map<Candidate, Node> children;

    public Node(Node f) {
	//this.father = f; no need of it
	this.weight = 0;
	this.children = new HashMap<Candidate, Node>();
    }

    public double getWeight() {
	return this.weight;
    }

    public Node getChild(Candidate c) {
	return this.children.get(c);
    }
    public void addVote(Deque<Candidate> vote, double w) {
	this.weight += w;
        // Node child=null;
	if (!vote.isEmpty()) {
	    Candidate first = vote.pollFirst();
           
	    Node child = this.children.get(first); 
	    if (child == null) { //if there is no such candidate with children in the 
		child = new Node(this);
		this.children.put(first, child);
	    }
	    child.addVote(vote, w);
	}
    }

    public List<Deque<Pair>> serialize() { //transforms tree into list of weighted orders
	List<Deque<Pair>> list = new ArrayList<Deque<Pair>>();
	for (Map.Entry<Candidate, Node> e: this.children.entrySet()) {
	    List<Deque<Pair>> orders = e.getValue().serialize();
	    if (orders.isEmpty()) {
		Deque<Pair> current = new LinkedList<Pair>();
		current.add(new Pair(e.getKey(), e.getValue().getWeight()));
		list.add(current);
	    } else {
		for (Deque<Pair> current: orders) {
		    current.addFirst(new Pair(e.getKey(), e.getValue().getWeight()));
		    list.add(current);
		}
	    }
	}
	return list;
    }
    
    @Override
    public String toString() {
	return this.toString("");
    }
    
    public String toString(String prefix) {
	String s = " " + this.weight + "\n";
	for (Map.Entry<Candidate, Node> e: this.children.entrySet()) {
	    s += prefix + e.getKey() + e.getValue().toString(prefix + " | ");
	}
	return s;
    }
}

