/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testtree;

import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;

public class CandidateComparator implements Comparator<Deque<Pair>> {
    private Candidate candidate;

    public CandidateComparator(Candidate c) {
	this.candidate = c;
    }

    @Override public int compare(Deque<Pair> order1, Deque<Pair> order2) {
	Iterator<Pair> iter1 = order1.iterator();
	Iterator<Pair> iter2 = order2.iterator();
	while (iter1.hasNext() && iter2.hasNext()) {
	    double c1 = iter1.next().weight;//order1.getFirst().weight;
	    double c2 = iter2.next().weight;//order2.getFirst().weight;
	    if(c1>c2) return 0;
            if(c2>c1) return 0;
	
    }
        return 0;
    }
}
	


