package testtree;
import java.io.FileReader;
import java.util.*;
import java.awt.Color;

public class VoteAuPlurielReader {
    private static final int BASE_CANDIDATE = 71;

    public static final Candidate[] CANDIDATES = {
	new Candidate("NS", Color.BLUE),
	new Candidate("FH",Color.green.darker()),
	new Candidate("FB", Color.cyan),
	new Candidate("JLM", Color.yellow),
	new Candidate("EJ", Color.green),
	new Candidate("MLP",Color.MAGENTA.darker()),
	new Candidate("NDA",Color.getHSBColor(0.76f, 1.0f, 0.8f)),
	new Candidate("NA", Color.red),
	new Candidate("PP", Color.orange),
	new Candidate("JC", Color.pink)
    };

    public static final Candidate END_CANDIDATE = new Candidate("Void", VotePanel.BG_COLOR);//denotes end of order
    
    private static final double[] COEFFS = {3.9218, 1., 0.669792, 0.421266, 0.172225, 6.6233, 1.52754, 1.77965, 1.03766, 1.01156};


    private static void parseLine(String s, Node n) {
	Scanner sc = new Scanner(s);
	sc.next();
	TreeSet<Pair> pQueue = new TreeSet<Pair>(new Comparator<Pair>() { //Objects are stored in a sorted and ascending order
		public int compare(Pair p, Pair q) {
		    return (int) (p.weight - q.weight);//returns positive value is p>q asc order
		}
	    });
	int k = 1;
	for (; k < 11; k++) { //читаем 10 чисел в строчке начиная с 1 колонки
	    int val = Integer.parseInt(sc.next());
	    if (val > 0 && val <= CANDIDATES.length) {
		pQueue.add(new Pair(CANDIDATES[k - 1], val)); //показывает какой кандидат на какой позиции
	    }		
	}
	pQueue.add(new Pair(END_CANDIDATE, 1000));

	Deque<Candidate> list = new LinkedList<Candidate>();
	for (Pair p: pQueue) {
	    list.add(p.candidate);
	}
	for (; k < 21; k++) {
	    sc.next();
	}
	int val = Integer.parseInt(sc.next()) - BASE_CANDIDATE;
	for (; k < 39; k++) {
	    sc.next();
	}
	boolean valid = (sc.next().equals("1"));
	if (valid && val >= 0 && val < CANDIDATES.length) {
	    double coeff = COEFFS[val];
            //System.out.println("old "+n);
            //System.out.println("list "+list);
	    n.addVote(list, coeff);
            //System.out.println("new "+n.toString());
	}
    }

   public static Node read(String fileName) {
        Node root = new Node(null);
	try {
	    Scanner input = new Scanner(new FileReader(fileName));
//k<12390
	    for (int k = 0; k < 20; k++) {		
                parseLine(input.nextLine(), root);
	    }
	} catch(Exception e) { // No comment please...
	    e.printStackTrace();
	}
	return root;
    }
}

