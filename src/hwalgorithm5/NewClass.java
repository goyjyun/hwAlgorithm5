package hwalgorithm5;

import hwalgorithm5.HwAlgorithm5.edge;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Yo
 */
public class NewClass {
    
    /**
     * @param args the command line arguments
     */
    static public class node
    {
        int id;
        double data;
    }
    static public class edge
    {
        int id;
        double data;
        int pre;
        int next;
    }
    static public class adjlist 
    {
        ArrayList<node> nodelist;
        ArrayList<edge> edgelist;
        
        public ArrayList getnodelist(){
            
            return this.nodelist;
        }
        public ArrayList getedgelist(){
            
            return this.edgelist;
        }
        public void show(int nodenum,int edgenum ){
           for(int i=0;i<nodenum;i++){
           
                System.out.printf("%d ->",nodelist.get(i).id);
                for(int j=0;j<edgenum;j++){
                    if(edgelist.get(j).pre == nodelist.get(i).id)
                        //System.out.printf("%d ",edgelist.get(i).pre);
                        System.out.printf("%d(%f) ",edgelist.get(j).next,edgelist.get(j).data);
                }
                System.out.printf(" \n");
            }
        }
     /*   public int checkre(edge e,int edgenum,int nodenum,int[][] arraynum)
        {
           //if(arraynum==null)return e;
           Random rand = new Random();
           
           
           for(int p=0; p<nodenum+1 ;p++)
           {
               if(e.next == edgelist.get(p).next)
               {
                   do{
                       e.next = rand.nextInt(nodenum)+1;
                   }while(e.next<p);
                   break;
               }                        
            }
           
           return e.next;
        }*/
        public void dijkstra(double [][]weight)
        {
            double road = 10000;
            int [] visited = new int [nodelist.size()];
            int [] show = new int [nodelist.size()];
            for(int i = 0 ; i < visited.length  ; i++)
            {
                visited[i] = -1;
            }
            int small = 0;
            for(int i = 0 ; i < nodelist.size() ;i++)
            {
                for( int j = 0 ; j < edgelist.size() ;j++)
                {
                    if(edgelist.get(j).pre-1 == i)
                    {
                        weight[i][edgelist.get(j).next -1 ] = edgelist.get(j).data;
                    }
                    if(i == j)
                    {
                        weight[i][j] = 0;                                   
                    }
                }
            }
            visited[0] = 0;
            for(int i = 1 ; i < nodelist.size() ; i++ )
            {
                road = 10000;
                for(int j = 0 ; j < nodelist.size() ; j++ )
                {
                    if(weight[0][j] > weight[i][j] && i !=j )                 //如果是同點到同點不能跑
                    {
                        weight[0][j] = weight[i][j] + weight[0][i];           //如有更小的值則替換
                    }
                    if(road > weight[0][j] && visited[j] != 0 )
                    {
                        road = weight[0][j];
                        small = j;
                    }
                }
                visited[small] = 0;
                show[i] = small;
            }
            for(int j=0;j<show.length;j++){
                System.out.printf("%d ",show[j] + 1);
            }
        }
        
        public void rand(int nodenum,int edgenum )
        {
            Random rand = new Random();
            HashSet set = new HashSet(); 
            int i , j ;
            //int[][]  arraynum= new int[nodenum][nodenum-1] ;
            nodelist = new ArrayList<node>();
            edgelist = new ArrayList<edge>();            
            //for(i=0;i<nodenum;i++){               
           // }
            // if(arraynum==null)System.out.printf("ZZ%d ZZZ ",arraynum[1][2]);
           // System.out.printf(" \n");
            
            for(i = 0 ; i < nodenum ; i++)
            {
                node n = new node();
                n.id = i+1;
                //n.data = rand.nextInt(nodenum+100)+1;
                //n.data = rand.nextDouble()+(double)rand.nextInt(100)+1;
                //System.out.printf("BBB%f AAA ",n.data);
                //System.out.printf(" \n");
                nodelist.add(n);
            }            
            int edgenumuse;            
            edgenumuse = edgenum;
            int  a;
            for(i = 0 ; i < nodenum && edgenumuse>0; i++){
                
                if(edgenum==nodenum*(nodenum-1)){                    
                    /*do{                        
                         a = rand.nextInt(edgenumuse)+1;
                         edgenumuse = edgenumuse/10;
                    }while(edgenumuse/a <100);*/
                    a = nodenum;
                    int rdm[] = new int[nodenum];  
                    for(int q=0;q<nodenum-1 ;q++)
                    {  
                        int pv = -1;  
                        do  
                        {  
                            pv = rand.nextInt(nodenum)+1;  
                        }while(!set.add(pv));  
                        rdm[i] = pv;
                         //System.out.printf("Hashtable size = "+ht.size());
                        //System.out.printf("rdm[i] = %d\n",rdm[i]);
                    }  
                    
                    
                    for( j = 0 ; j < nodenum-1; j++)
                    {
                        edge e = new edge();
                        e.next = rand.nextInt(nodenum)+1;                        
                        e.pre = i+1;
                        e.next = rdm[j+1];
                       // for(; e.next == e.pre ;)
                        //{
                         //e.next = rand.nextInt(nodenum)+1;                        
                       // }                 
                        
                        /*if(!edgelist.isEmpty()){
                            for(int p=0; p<edgelist.size() ;p++)
                            {
                                for(;e.next == edgelist.get(p).next || e.next==e.pre ;)
                                {
                                    //for(; e.next==e.pre ;){
                                        e.next = rand.nextInt(nodenum)+1;
                                        p = 0;
                                   // }
                                }                        
                             }
                        }*/
                        e.data = rand.nextDouble()+(double)rand.nextInt(100)+1;
                        e.id =e.pre;
                        edgelist.add(e);
                    }                    
                }                
                else {
                    if(i+1!=nodenum){
                        /*a=0;
                        while(a==0){
                            System.out.printf("AAA%d AAA ",a);
                            System.out.printf(" \n");
                        }*/                        
                        if(edgenum/nodenum>=10){
                            a = rand.nextInt(edgenum/nodenum+9)+1;                       
                            while((edgenumuse-a)>(nodenum-1)*(nodenum-i-1)){
                                a = rand.nextInt(edgenum/nodenum+9)+1;
                                //System.out.printf("BBB%d AAA ",a);
                                //System.out.printf(" \n");
                            }
                            while(edgenumuse-a<0){
                                a = rand.nextInt(edgenum/nodenum+9)+1;
                               // System.out.printf("AAA%d AAA ",a);
                               // System.out.printf(" \n");
                            }                      
                        }
                        else{
                            a = rand.nextInt(nodenum)+1;                       
                            while((edgenumuse-a)>(nodenum-1)*(nodenum-i-1)){
                                a = rand.nextInt(nodenum)+1;
                               // System.out.printf("BBB%d AAA ",a);
                               // System.out.printf(" \n");
                            }
                            while(edgenumuse-a<0){
                                a = rand.nextInt(nodenum)+1;
                              //  System.out.printf("AAA%d AAA ",a);
                              //  System.out.printf(" \n");
                            } 
                        }    
                       /*  while(edgenum -a < nodenum*(i+1) && a< edgenumuse -(nodenum-i-1)*nodenum) 
                        {
                            a = rand.nextInt(nodenum)+1;
                        }*/                        
                       /* do{
                            a = rand.nextInt(nodenum)+1;
                            //System.out.printf("%d X ",a);
                            //System.out.printf(" \n");
                            System.out.printf("QQ%dQQ",edgenumuse -(nodenum-i-1-1)*nodenum);
                            System.out.printf(" \n");
                        }while( (a>= edgenumuse -(nodenum-i-1-1)*nodenum) || a==1 );*/                      
                       // System.out.printf("YYY%d Y ",a);
                        //System.out.printf(" \n");
                    }
                    else {    
                        a=edgenumuse;
                        //System.out.printf("XXX%d X ",a);
                        //System.out.printf(" \n");
                    }                    
                    edgenumuse = edgenumuse-a;
                    for( j = 0 ; j < a; j++)
                    {
                        edge e = new edge();
                        e.next = rand.nextInt(nodenum)+1;
                        e.pre = i+1;
                        if(!edgelist.isEmpty()){
                            for(int p=0; p<edgelist.size() ;p++)
                            {
                                for(;e.next == edgelist.get(p).next || e.next==e.pre ;)
                                {
                                    //for(; e.next==e.pre ;){
                                        e.next = rand.nextInt(nodenum)+1;
                                   // }
                                }                        
                             }
                        }
                        e.data = rand.nextDouble()+(double)rand.nextInt(100)+1;
                        e.id =e.pre;
                        edgelist.add(e);
                    }
                }
            } 
        }
    }
   
   
    
    
    public static void main(String[] args) {
        // TODO code application logic here
       //int nodenum=5,edgenum=3;
       long st = System.currentTimeMillis();
       long ut ;  
       System.out.println("Enter nodenum:");
       Scanner sc = new Scanner(System.in);
       int nodenum = sc.nextInt();
       System.out.println("Enter edgenum:");
       Scanner sc2 = new Scanner(System.in);
       int edgenum = sc2.nextInt();
       double [][]weight = new double [nodenum][nodenum]; 
       for( int i = 0 ; i< nodenum; i++ )
       {
           for( int j = 0 ; j< nodenum; j++ )
           {
               weight[i][j] = 10000;
           }
       }
       adjlist adj = new adjlist();
       adj.rand(nodenum,edgenum);
       
       adj.show(nodenum, edgenum);
       st = System.currentTimeMillis();  
      // adj.dijkstra(weight);
       ut = System.currentTimeMillis()-st;  
       System.out.printf("\n Spending time=%d ms\n\n", ut);  
        
       Hashtable<String,Integer> ht = new Hashtable<String,Integer>();
       for(int i = 0;ht.size() < 10;i++)
          ht.put(((int)(Math.random()*10+1))+"",0);
       
       Enumeration tmp = ht.keys();
       System.out.println("Hashtable size = "+ht.size());
       
       for (Enumeration e = tmp; e.hasMoreElements();)
          System.out.print(e.nextElement()+" ");
       
       
    }
    
}
