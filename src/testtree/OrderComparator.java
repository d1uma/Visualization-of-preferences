package testtree;


import java.util.*;
import static testtree.TestTree.compute_perm;
import static testtree.TestTree.c_list;
import static testtree.TestTree.tst;

public class OrderComparator implements Comparator<Deque<Pair>> {
    private Candidate cand1;
    private Candidate cand2;
    private Node root;
    private int step;
    private int stepMax;
    private List<Deque<Pair>> list;
    private List<Deque<Pair>> list_copy=new ArrayList<>();
    public OrderComparator(Candidate c1,Candidate c2, Node r, int s, List<Deque<Pair>> list) {
	this.cand1 = c1;
        this.cand2=c2;
	this.root = r;
        this.stepMax = s;
        this.list=list;
    }


    @Override public int compare(Deque<Pair> order1, Deque<Pair> order2) {
	Iterator<Pair> iter1 = order1.iterator();
	Iterator<Pair> iter2 = order2.iterator();
        step=0; //iterating through each candidate in an order
        boolean found=false;
	while (iter1.hasNext() && iter2.hasNext()) {
	    Candidate c1 = iter1.next().candidate;
	    Candidate c2 = iter2.next().candidate;
            step++;
            found=false;
	if (c1.equals(this.cand2) && step<=stepMax) { //sorted by JLM before stepMax
		if (c2.equals(this.cand2) && step<=stepMax) { // if o2 doesn't have JLM before stepMax put c1 to the left
                    found=true;
		    return this.advancedComparison(order1, order2); //otherwise sort them by weight
		}
                found=true;
		return -1;
	    }
      
          if(c2.equals(this.cand2) && step<=stepMax){
              found=true;
                return 1;
            }
	}
        	while (iter1.hasNext() ) {
                found=false;
	    Candidate c1 = iter1.next().candidate;
            step++;
	    if (c1.equals(this.cand2)&& step<=stepMax) {
                found=true;
		return -1;
	    }
	}
	while (iter2.hasNext()) {
            found=false;
	    Candidate c2 = iter2.next().candidate;
            step++;
	    if (c2.equals(this.cand2) && step<=stepMax) {
               // System.out.println("cont. jlm c2");
                found=true;
		return 1;
	    }
	}
        Iterator<Pair> iter3 = order1.iterator();
	Iterator<Pair> iter4 = order2.iterator();
       
        step=0;
        while (iter3.hasNext() && iter4.hasNext()) {
            
            Candidate c1 = iter3.next().candidate;
	    Candidate c2 = iter4.next().candidate;
            step++;
           if(!found){
            if(c1.equals(this.cand1)){
                if(c2.equals(this.cand1)){
                   // System.out.println("fh simi ");
                    return this.advancedComparison(order1, order2);
                }
               // System.out.println("fh c1");
                return -1;
            }
        }
          if(!found){
              if(c2.equals(this.cand1)){
                //System.out.println("fh c2");
                    return 1;
            }
          }
    }
        while(iter3.hasNext()){
          Candidate c1 = iter3.next().candidate;
          if(!found){
              if(c1.equals(this.cand1)){
                  return -1;
              }
          }
        }
        while(iter4.hasNext()){
          Candidate c2 = iter4.next().candidate;
          if(!found){
              if(c2.equals(this.cand1)){
                  return 1;
              }
          }
        }
	return this.advancedComparison(order1, order2);
    }

    private int advancedComparison(Deque<Pair> order1, Deque<Pair> order2) {
        //System.out.println("equal order 1"+order1);
        //System.out.println("equals order 2 "+order2);
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
               // System.out.println("n1 larger weight "+n1+" n2 smaller "+n2);
		return -1;
	    }
	    if (n1.getWeight() < n2.getWeight()) {
               // System.out.println("n1 smaller weight "+n1+" n2 bigger weight "+n2);
		return 1;
	    }
	}
	return 0;
    }
}


