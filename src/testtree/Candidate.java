package testtree;

import java.awt.Color;

public class Candidate {
    public String name;
    private Color color;
    
    public Candidate(String n, Color c) {
	this.color = c;
	this.name = n;
    }

    public Candidate(String n) {
	this(n, Color.BLACK);
    }

    public Color getColor() {
	return this.color;
    }
    
    @Override
    public String toString() {
	return this.name;
    }

    @Override
    public int hashCode() {
	return this.name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
	if (o instanceof Candidate) {
                                        //==
	    return ((Candidate) o).name.equals(this.name);
	}
	return false;
    }
}
