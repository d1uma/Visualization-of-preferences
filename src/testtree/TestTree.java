package testtree;
import java.util.Deque;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class TestTree {
    public static boolean start=true;
    public static int count=0;
    public static int perm_count=0;
    public static Visualization vis=new Visualization();
    //public static int fh_pos []={0,147,295,394,420,445,544,1124,1149,1175,1237};
  //  public static int fh_pos []={0,122,245,327,349,370,452,934,955,976,1028}; //3 4 10 5 6 7 9 1 2 0 8 
    //public static int jlm_pos []={0,51,72,94,216,339,360,382,464,516,997};
    //used //public static int jlm_pos []={94,216,997,339,360,382,516,51,72,0,464};
    public static int c_pos[];//{52,174,297,379,401,422,504,986,1007,0,1028};
   // public static int h[]={96, 216,338,421,443,464,546,52,74,0,1028};
   // 2nd step public static int jlm_pos[]={74,196,319,401,423,444,526,1008,52,0,1028};
    

    public static List<Integer> tst;
    public static int p_fh[];
    public static boolean zero=true;
    public static VotePanel panel1;
    public static JRadioButton fh;
    public static JRadioButton ns;
    public static JRadioButton mlp;
    public static JRadioButton pp;
    public static JFrame frame;
    public static List<Deque<Pair>> c_list;
    public static List<Integer> order=new ArrayList<>();
    public static List<Deque<Pair>> ns_list;
    public static Node root;
    public static Container c;
    public static boolean check_init=false;
    public static void main(String[] args) {
        vis.init();
        
    }
        public static List<Integer> compute_perm(List<Deque<Pair>> fh_list, List<Deque<Pair>> jlm_list){
        List<Integer> perm=new ArrayList<>();
        for(Deque<Pair> cur_fh:fh_list){
            perm.add(jlm_list.indexOf(cur_fh));
        }
          return perm;
    }
}