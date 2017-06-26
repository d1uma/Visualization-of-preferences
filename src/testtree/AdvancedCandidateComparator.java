package testtree;

import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;

public class AdvancedCandidateComparator implements Comparator<Deque<Pair>> {
    private Candidate candidate;
    private Node root;
    public AdvancedCandidateComparator(Candidate c, Node r) {
	this.candidate = c;
	this.root = r;
    }

    @Override public int compare(Deque<Pair> order1, Deque<Pair> order2) {
       // System.out.println("order 1 "+order1);
       // System.out.println("order 2 "+order2);
	Iterator<Pair> iter1 = order1.iterator();
	Iterator<Pair> iter2 = order2.iterator();
	while (iter1.hasNext() && iter2.hasNext()) {
	    Candidate c1 = iter1.next().candidate;
	    Candidate c2 = iter2.next().candidate;
	    if (c1.equals(this.candidate)) {
		if (c2.equals(this.candidate)) {
		    return this.advancedComparison(order1, order2); //когда они одинаковы
		}
		return -1;//acscending order
	    }
	    if (c2.equals(this.candidate)) {//когда с2 это кандидат которого ищем
		return 1;
	    }
	}
	while (iter1.hasNext()) {
	    Candidate c1 = iter1.next().candidate;//когда с1 кандидат когорого ищем
	    if (c1.equals(this.candidate)) {
		return -1;
	    }
	}
	while (iter2.hasNext()) {
	    Candidate c2 = iter2.next().candidate;
	    if (c2.equals(this.candidate)) {
		return 1;
	    }
	}
	return this.advancedComparison(order1, order2);
    }

    private int advancedComparison(Deque<Pair> order1, Deque<Pair> order2) {
	Iterator<Pair> iter1 = order1.iterator();
	Iterator<Pair> iter2 = order2.iterator();
	Node n1 = this.root;
	Node n2 = this.root;
	while (iter1.hasNext() && iter2.hasNext()) {
	    Candidate c1 = iter1.next().candidate;
	    Candidate c2 = iter2.next().candidate;
	    n1 = n1.getChild(c1);
	    n2 = n2.getChild(c2);
	    if (n1.getWeight() > n2.getWeight()) {
                //System.out.println("n1 larger weight "+n1+" n2 smaller "+n2);
		return -1;
	    }
	    if (n1.getWeight() < n2.getWeight()) {
                //System.out.println("n1 smaller weight "+n1+" n2 bigger weight "+n2);
		return 1;
	    }
	}
	return 0;
    }
}

