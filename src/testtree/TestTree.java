package testtree;
import java.util.Deque;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class TestTree {
    public static boolean start=true;

    public static Visualization vis=new Visualization();

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