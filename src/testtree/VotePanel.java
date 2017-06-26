package testtree;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import static testtree.Visualization.c_list;



public class VotePanel extends JPanel {
    public static List<Integer>init=new ArrayList<>();
    public static JRadioButton fh=new JRadioButton("FH");
    public static JRadioButton ns=new JRadioButton("NS");
    public static int score []={130,130,130,43,43,43,43,43,43,43,43};
   // public static boolean fh_check=true;
   // public static boolean jlm_check=false;
    public static boolean check_init=false;
   // public static List<Integer>list1=new ArrayList<>();
    public static List<Integer>test=new ArrayList<>();
   // public static List<Integer>list2=new ArrayList<>();
    private List<Deque<Pair>> profile;
    private Candidate[] candidates;
    private double count;
    public static final Color BG_COLOR = Color.decode("#eeeeee");
    public static int cs=0;
    public static int last=0;
   // public static int fh_c=0;
    public static int fin=0;
    public static int position [];//{0,122,245,327,349,370,452,934,955,976,1028};
    public static int column=0;
    public static int level=0;
    public VotePanel(List<Deque<Pair>> list, Candidate[] cand, double count) {
	this.profile = list;
	this.candidates = cand;
	this.count = count;
    }
    @Override
    public void paintComponent(Graphics g) {
        column=0;
        cs=cs+1;
        super.paintComponent(g);
	int nbCandidates = this.candidates.length;
	int width = this.getWidth()-100;
	int height = this.getHeight();
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, width, height);
	double cc = 0.0;
	double currentX = 0;
	double currentY = 0;
	for (Deque<Pair> order: this.profile) {
            level=0;
	    currentY = 0;
	    double w = order.getLast().weight;
            if(!check_init){
            test.add((int)currentX);
            }
            if(order.equals(this.profile.get(this.profile.size()-1))){
                last=(int) (w * width / count)+1;//i don't know how to get last 
                fin=(int) currentX+(int) (w * width / count) + 1;
            }
	    for (Pair p: order) {
		Candidate c = p.candidate;
                level++;
               // System.out.println("c name "+c.name+" lev "+level);
                 g.setColor(c.getColor());
                 if(cs<=4){
                    g.fillRect((int) currentX, (int) currentY, (int) (w * width / count) + 1, score[level-1]);//(int) (height / nbCandidates));
                    }
                 else{
                     g.fillRect(position[column],(int) currentY, (int) (w * width / count) + 1, score[level-1]);//(int) (height / nbCandidates));
                 }
		currentY += score[level-1];//(height / nbCandidates);    
            }
	    currentX += w * width / count;
	    cc += w;
            column++;
	
    }
            //if(!Visualization.done){
            for(int pt=test.size()-c_list.size()-1;pt>=0;pt--){
                test.remove(pt);
            //}
            }
            //System.out.println("test "+test);
            //System.out.println("init "+init);
	g.dispose();
    }
    
}

