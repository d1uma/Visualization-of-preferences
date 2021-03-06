package testtree;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import javax.swing.*;
import javax.swing.Timer;
import static testtree.TestTree.compute_perm;
/**
 *
 * @author dina
 */
public class Visualization {
    public static List<Deque<Pair>> c_list;
    public static List<Integer> order=new ArrayList<>();
    public static Node root;
    public static Container c;
    public static boolean check_init=false;
    public static int count=0;
    public static int perm_count=0;
    public static int c_pos[];
    public static List<Integer> tst;
    public static int p_fh[];
    public static boolean zero=true;
    public static VotePanel panel1;
    public static JRadioButton fh;
    public static JRadioButton ns;
    public static JRadioButton mlp;
    public static JFrame frame;
    public static int i=0;
    public static int step=2;
    public static boolean done=false;
    public static boolean prev_fh=false;
    public static boolean prev_ns=false;
    public static boolean prev_mlp=false;
    public static int borda[]={113,103,93,83,73,63,53,43,33,23,13};
    public static int k4[]={120,120,120,120,33,33,33,33,33,33,33};
    public static int k3[]={130,130,130,43,43,43,43,43,43,43,43};
    public int dlt []= {1,1,1,1,1,1,1,1,1,1,1};
    public JLabel b=new JLabel("Voting Method");
    public static JRadioButton chkBorda = new JRadioButton("Borda");
    public static JRadioButton chkK = new JRadioButton("3 Approval");
    public static JRadioButton chkK4 = new JRadioButton("4 Approval");
    private javax.swing.Timer timer;
    private javax.swing.Timer timer2;
    private javax.swing.Timer timer3;
    public static javax.swing.Timer time;
    public static boolean fh_check=true;
    public static boolean mlp_check=false;
    public static boolean ns_check=false;
    public static int []desired;
    protected List<Deque<Pair>>list;
    protected static List<Deque<Pair>>tlist=new ArrayList<>();
    public static List<Integer>cc=new ArrayList<>();
    public static List<Integer>sorted=new ArrayList<>();

    public void init(){
        root=VoteAuPlurielReader.read("/Users/dina/NetBeansProjects/Preference/src/preference/20120621_voteaupluriel.txt");
	c_list = root.serialize();
        VotePanel.position=new int[c_list.size()];
        int l=c_list.size();
        c_pos=new int[l];
	frame = new JFrame("Vote Au Pluriel");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	c = frame.getContentPane();
        JLabel cand = new JLabel("Candidates");
        cand.setForeground(Color.black);
	cand.setFont(new java.awt.Font("Arial", Font.ITALIC | Font.BOLD, 15));
        System.out.println("c_list size "+c_list.size());
        fh = new JRadioButton("Candidate 1");
        ns = new JRadioButton("Candidate 2");
        mlp = new JRadioButton("Candidate 3");
        ButtonGroup group = new ButtonGroup();
        group.add(fh);
        group.add(ns);
        group.add(mlp);
        ButtonGroup group2 = new ButtonGroup();
        group2.add(chkK);
        group2.add(chkK4);
        group2.add(chkBorda);
        fh.setSelected(true);
        fh.setForeground(Color.cyan);
	fh.setFont(new java.awt.Font("Arial", Font.ITALIC | Font.BOLD, 15));
        ns.setForeground(Color.blue);
	ns.setFont(new java.awt.Font("Arial", Font.ITALIC | Font.BOLD, 15));
        mlp.setForeground(Color.MAGENTA.darker());
	mlp.setFont(new java.awt.Font("Arial", Font.ITALIC | Font.BOLD, 15));
        b.setFont(new java.awt.Font("Arial", Font.ITALIC | Font.BOLD, 15));
        chkK.setFont(new java.awt.Font("Arial", Font.ITALIC | Font.BOLD, 15));
        chkK4.setFont(new java.awt.Font("Arial", Font.ITALIC | Font.BOLD, 15));
        chkBorda.setFont(new java.awt.Font("Arial", Font.ITALIC | Font.BOLD, 15));
        for(int k=0;k<c_list.size();k++){
            order.add(k);
        }
        Collections.sort(c_list, new AdvancedCandidateComparator(VoteAuPlurielReader.CANDIDATES[2], root));
        //new CandidateComparator(VoteAuPlurielReader.CANDIDATES[2]));                  
        panel1 = new VotePanel(c_list, VoteAuPlurielReader.CANDIDATES, root.getWeight());
        for(Deque<Pair>pa:c_list){
                 tlist.add(pa);
        }
	c.add(panel1, BorderLayout.CENTER);
        JPanel menu=new JPanel();
        menu.setPreferredSize(new Dimension(150,800));
        menu.setBackground(Color.decode("#eeeeee"));
        c.add(menu,BorderLayout.EAST);
	frame.setPreferredSize(new Dimension(1500, 800));
        b.setBounds(1120,40,120, 50);                  
        chkBorda.setBounds(1120,130,120,50);        
        chkK.setBounds(1120,70,120,50);
        chkK.setSelected(true);
        chkK4.setBounds(1120,100,120,50);
        menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));
        menu.add(cand);
        menu.add(fh);
        menu.add(ns);
        menu.add(mlp);
        menu.add(b);
        menu.add(chkK);
        menu.add(chkK4);
        menu.add(chkBorda);
	frame.pack();
	frame.setVisible(true);
        chkK.addActionListener(new MListener());
        chkK4.addActionListener(new MListener());
        chkBorda.addActionListener(new MListener());
    time=new Timer(15,new ActionListener(){
       public void actionPerformed(ActionEvent ae) {
           if(i==0){//selecting different voting methods before choosing any candidate
           for(int y=0;y<VotePanel.test.size();y++){
                        VotePanel.position[y]=VotePanel.test.get(y);
                      }
           }
           if(chkK4.isSelected()){ //to k4 
            
              switch_method(k4,"k4",time);
           }
           if(chkK.isSelected()){ //to k3
               switch_method(k3,"k3",time); 
           }
           if(chkBorda.isSelected()){//to borda
                switch_method(borda,"borda",time);
           }
       }
       });
    

   timer = new Timer(15, new ActionListener(){ //NS
                        public void actionPerformed(ActionEvent e) {
                            transition(c_pos, timer);
                            if(zero){
                                if(step==2)
                                       check_init=true;
                                if(step==11){
                                    timer.stop();
                                    fh_check=false;
                                    mlp_check=false;
                                }
                                if(step<=10){
                                    swap_columns(step-1,VoteAuPlurielReader.CANDIDATES[0]);
                                    if(fh_check){
                                        c_list=animation(c_list,VoteAuPlurielReader.CANDIDATES[2],VoteAuPlurielReader.CANDIDATES[0],step);
                                        
                                    }
                                    if(mlp_check){
                                         c_list=animation(c_list,VoteAuPlurielReader.CANDIDATES[5],VoteAuPlurielReader.CANDIDATES[0],step);
                                       
                                    }
                                     step++;
                                
                                timer.start();
                                } 
                               }
                        }
                        });
   
   timer3 = new Timer(15, new ActionListener(){ //MLP
                        public void actionPerformed(ActionEvent e) {
                            transition(c_pos, timer3);
                            if(zero){
                                if(step==2)
                                       check_init=true;
                                if(step==11){
                                    timer3.stop();
                                    fh_check=false;
                                    ns_check=false;
                                }
                                if(step<=10){
                                    swap_columns(step-1,VoteAuPlurielReader.CANDIDATES[5]);
                                    if(fh_check){
                                        c_list=animation(c_list,VoteAuPlurielReader.CANDIDATES[2],VoteAuPlurielReader.CANDIDATES[5],step);
                                        
                                    }
                                    if(ns_check){
                                        c_list=animation(c_list,VoteAuPlurielReader.CANDIDATES[0],VoteAuPlurielReader.CANDIDATES[5],step);
                                        
                                    }
                                     step++;
                                
                                timer3.start();
                                } 
                               }
                        }
                        });
timer2 = new Timer(15, new ActionListener(){//FB
                        public void actionPerformed(ActionEvent e) {
                            transition(c_pos, timer2);
                            if(zero){
                                if(step==2)
                                       check_init=true;
                                if(step==11){
                                    timer2.stop();
                                    ns_check=false;
                                    mlp_check=false;
                                }
                                if(step<=10){
                                    swap_columns(step-1,VoteAuPlurielReader.CANDIDATES[2]);
                                    if(ns_check){
                                        c_list=animation(c_list,VoteAuPlurielReader.CANDIDATES[0],VoteAuPlurielReader.CANDIDATES[2],step);
                                      
                                    }
                                    if(mlp_check){
                                        c_list=animation(c_list,VoteAuPlurielReader.CANDIDATES[5],VoteAuPlurielReader.CANDIDATES[2],step);
                                    }
                                     step++;
                                
                                timer2.start();
                                }
                               }
                        }
                        });

  ns.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!timer2.isRunning() && !timer3.isRunning() && !timer.isRunning() && (!prev_ns)){
                i++;
                prev_fh=false;
                prev_mlp=false;
                prev_ns=true;
                ns_check=true;
                step=2;
                check_init=false;
                c_pos=new int[c_list.size()];
                sorted=new ArrayList<>();
                cc=new ArrayList<>();
                desired=new int[VotePanel.test.size()];
                if(i==1){
                      for(int y=0;y<VotePanel.test.size();y++){
                        VotePanel.position[y]=VotePanel.test.get(y);
                      }
                     for(int p=0;p<VotePanel.test.size()-1;p++){
                         VotePanel.init.add(VotePanel.test.get(p+1)-VotePanel.test.get(p));
                     }
                     VotePanel.init.add(VotePanel.last);
                }
                else{
                VotePanel.test=new ArrayList<>();
                    for(int i=0;i<VotePanel.position.length;i++){
                        VotePanel.test.add(VotePanel.position[i]);
                        Collections.sort(VotePanel.test);
                    }
                 }
                    swap_columns(0,VoteAuPlurielReader.CANDIDATES[0]);
                    if(fh_check)
                        c_list=animation(c_list,VoteAuPlurielReader.CANDIDATES[2],VoteAuPlurielReader.CANDIDATES[0],1);
                    if(mlp_check){
                        c_list=animation(c_list,VoteAuPlurielReader.CANDIDATES[5],VoteAuPlurielReader.CANDIDATES[0],1); 
                    }
                   if( !timer.isRunning()){
                         timer.start();
                   }
            }
            }
        });
        fh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!timer2.isRunning() && !timer3.isRunning() && !timer.isRunning() && !prev_fh) {
                    prev_fh = true;
                    prev_mlp = false;
                    prev_ns = false;
                    fh_check = true;
                    step = 2;
                    check_init = false;
                    c_pos = new int[c_list.size()];
                    sorted = new ArrayList<>();
                    cc = new ArrayList<>();
                    desired = new int[VotePanel.test.size()];
                    VotePanel.test = new ArrayList<>();
                    for (int i = 0; i < VotePanel.position.length; i++) {
                        VotePanel.test.add(VotePanel.position[i]);
                        Collections.sort(VotePanel.test);
                    }
                    swap_columns(0, VoteAuPlurielReader.CANDIDATES[2]);
                    if (ns_check) {
                        c_list = animation(c_list, VoteAuPlurielReader.CANDIDATES[0], VoteAuPlurielReader.CANDIDATES[2], 1);
                    }
                    if (mlp_check) {
                        c_list = animation(c_list, VoteAuPlurielReader.CANDIDATES[5], VoteAuPlurielReader.CANDIDATES[2], 1);
                    }

                    if (!timer2.isRunning()) {
                        timer2.start();
                    }
                }
            }
        });
        mlp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!timer2.isRunning() && !timer3.isRunning() && !timer.isRunning() && !prev_mlp) {
                    prev_fh = false;
                    prev_mlp = true;
                    prev_ns = false;
                    i++;
                    mlp_check = true;
                    step = 2;
                    check_init = false;
                    c_pos = new int[c_list.size()];
                    sorted = new ArrayList<>();
                    cc = new ArrayList<>();
                    desired = new int[VotePanel.test.size()];
                    if (i == 1) {
                        for (int y = 0; y < VotePanel.test.size(); y++) {
                            VotePanel.position[y] = VotePanel.test.get(y);
                        }
                        for (int p = 0; p < VotePanel.test.size() - 1; p++) {
                            VotePanel.init.add(VotePanel.test.get(p + 1) - VotePanel.test.get(p));
                        }
                        VotePanel.init.add(VotePanel.last);
                    } else {
                        VotePanel.test = new ArrayList<>();
                        for (int i = 0; i < VotePanel.position.length; i++) {
                            VotePanel.test.add(VotePanel.position[i]);
                            Collections.sort(VotePanel.test);
                        }
                    }
                    swap_columns(0, VoteAuPlurielReader.CANDIDATES[5]);
                    if (fh_check) {
                        c_list = animation(c_list, VoteAuPlurielReader.CANDIDATES[2], VoteAuPlurielReader.CANDIDATES[5], 1);
                    }
                    if (ns_check) {
                        c_list = animation(c_list, VoteAuPlurielReader.CANDIDATES[0], VoteAuPlurielReader.CANDIDATES[5], 1);
                    }
                    if (!timer3.isRunning()) {
                        timer3.start();
                    }
                }
            }
        });
    }

    public static List<Deque<Pair>> animation(List<Deque<Pair>> list1, Candidate c1, Candidate c2,int stepMax){
        List<Deque<Pair>>list2=new ArrayList<>(); 

        for(Deque<Pair>pair:list1){
            list2.add(pair);
        }
        Collections.sort(list2, new OrderComparator(c1,c2, root,stepMax,list2));
        tst=compute_perm(tlist,list2);
        if(check_init){ 
            VotePanel.test=new ArrayList<>();
            for(int i=0;i<VotePanel.position.length;i++){
                VotePanel.test.add(VotePanel.position[i]);
                Collections.sort(VotePanel.test);
            }
        }
        c_pos=compute_pos(VotePanel.test,tst,cc.size()-1); 

        list1=new ArrayList<>();
        for(Deque<Pair>pa:list2){
            list1.add(pa);
        }
            return list1;
    }
    public static int[] compute_pos(List<Integer> initial, List<Integer> perm, int count) {
        int size = 0;
        int t_pos = 0;
        List<Integer> copy = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        List<Integer> cco = new ArrayList<>();
        for (int u = 0; u < cc.size(); u++) {
            copy.add(tst.get(cc.get(u)));
            cco.add(cc.get(u));
        }
        for (int g : copy) {
            c.add(g);
        }
        Collections.sort(copy);
        for (int j = 0; j < c.size(); j++) {
            cc.set(j, cco.get(c.indexOf(copy.get(j))));
        }
        for (int t = 0; t < 1; t++) {
            size = VotePanel.init.get(cc.get(t));
            if (desired[cc.get(t)] != 0) {
                desired[cc.get(t)] = VotePanel.test.get(perm.get(cc.get(t)) - 1) + VotePanel.init.get(perm.indexOf(perm.get(cc.get(t)) - 1));
                for (int y = perm.get(cc.get(t)) + 1; y < initial.size(); y++) {
                    if (!sorted.contains(perm.indexOf(y))) {
                        desired[perm.indexOf(y)] = desired[perm.indexOf(y - 1)] + VotePanel.init.get(perm.indexOf(y - 1));
                    }
                }
                for (int f : cc) {
                    sorted.add(f);
                }
            } else {
                desired[cc.get(t)] = perm.get(cc.get(t));
                for (int y = perm.get(cc.get(t)) + 1; y < initial.size(); y++) {
                    if (!sorted.contains(perm.indexOf(y))) {
                        desired[perm.indexOf(y)] = desired[perm.indexOf(y - 1)] + VotePanel.init.get(perm.indexOf(y - 1));
                    }
                }
                for (int f : cc) {
                    sorted.add(f);
                }
            }
        }
        return desired;
    }
    public static void transition(int array[], Timer t) {
        List<Integer> delta = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            delta.add(9);
        }
        for (int y = 0; y < array.length; y++) {
            if (VotePanel.position[y] == array[y]) {
                delta.set(y, 0);
            }
        }
        for (int x = 0; x < array.length; x++) {
            if (VotePanel.position[x] < array[x]) {
                if (array[x] - VotePanel.position[x] <= 4 && (array[x] - VotePanel.position[x]) != 0) {
                    VotePanel.position[x] += array[x] - VotePanel.position[x];
                } else {
                    VotePanel.position[x] += delta.get(x);
                }
            } else {
                if (VotePanel.position[x] - array[x] <= 4 && (VotePanel.position[x] - array[x]) != 0) {
                    VotePanel.position[x] -= VotePanel.position[x] - array[x];
                } else {
                    VotePanel.position[x] -= delta.get(x);
                }
            }
        }
        zero = true;
        for (int y = 0; y < delta.size(); y++) {
            if (delta.get(y) != 0) {
                zero = false;
            }
        }
        if (zero) {
            for (int u = 0; u < delta.size(); u++) {
                delta.set(u, 4);
            }
        }
        frame.repaint();
    }

    public static void swap_columns(int aa, Candidate c2) {
        cc = new ArrayList<>();
        int m = 0;
        int a = 0;
        for (Deque<Pair> f : tlist) {
            a = 0;
            for (Pair p : f){
                if (p.candidate.equals(c2) && a == aa) {
                    cc.add(m);
                }
                a++;
            }
            m++;
        }

               } 
public void switch_method(int array[], String chosen_method, Timer t){
        for(int y=0;y<array.length;y++){
            if(VotePanel.score[y]==array[y])
                dlt[y]=0;
        }
        for(int x=0;x<array.length;x++){
              if(VotePanel.score[x]<array[x])
                  VotePanel.score[x]+=dlt[x];
              else
                  VotePanel.score[x]-=dlt[x];
            }
            done=true;
            for(int y=0;y<dlt.length;y++){
            if(dlt[y]!=0){
                 done=false;
            }
            }
        if(done){
             for(int u=0;u<dlt.length;u++){
                 dlt[u]=1;
             }
             t.stop();
                       }
        frame.repaint();
}
public static void execute_timer(){
    
}
    public String toString(){
        return "(" + this.list + ")";
    }
    }




