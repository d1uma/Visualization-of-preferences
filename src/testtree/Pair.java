package testtree;


import testtree.Candidate;

public class Pair {
    public Candidate candidate;
    public double weight;

    public Pair(Candidate c, double w) {
	this.candidate = c;
	this.weight = w;
    }
    public boolean equals(Object o){
        if (o instanceof Pair  && (this.weight==((Pair) o).weight)) {
	    return ((Pair) o).candidate.equals(this.candidate);
	}
       return false;
    }

    @Override
    public String toString() {
	return "(" + this.candidate + ", " + this.weight + ")";
    }
}

