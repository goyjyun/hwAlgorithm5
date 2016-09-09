package hwalgorithm5;

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
public class HwAlgorithm5 {
    
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
            //Random rand = new Random();
            
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
            
            int  a;
            int edgenumuse;            
            edgenumuse = edgenum;
            for(i = 0 ; i < nodenum && edgenumuse>0; i++){
                HashSet set = new HashSet(); 
                Random rand = new Random();
                int rdm[] = new int[nodenum];  
                for(int q=0;q<nodenum ;q++)
                {  
                    int pv = -1;  
                    do{  
                        pv = rand.nextInt(nodenum)+1;  
                    }while(!set.add(pv));  
                    rdm[i] = pv;                
                }
                if(edgenum==nodenum*(nodenum-1)){
                    for( j = 0 ; j < edgenum; j++)
                    {
                        edge e = new edge();
                        //e.id = i + j+1;
                        System.out.printf("AA%dAA",rdm[j]);
                        e.next = rdm[j+1];
                        e.pre = rand.nextInt(nodenum)+1;
                        e.id =e.pre;
                        e.data = rand.nextDouble()+(double)rand.nextInt(100)+1;

                        edgelist.add(e);  
                    }
                }         
                else {
                    if(i+1!=nodenum){
                        if(edgenum/nodenum>=10){
                            a = rand.nextInt(edgenum/nodenum+9)+1;                       
                            while((edgenumuse-a)>(nodenum-1)*(nodenum-i-1)){
                                a = rand.nextInt(edgenum/nodenum+9)+1;
                            }
                            while(edgenumuse-a<0){
                                a = rand.nextInt(edgenum/nodenum+9)+1;
                            }                      
                        }
                        else{
                            a = rand.nextInt(nodenum)+1;                       
                            while((edgenumuse-a)>(nodenum-1)*(nodenum-i-1)){
                                a = rand.nextInt(nodenum)+1;
                            }
                            while(edgenumuse-a<0){
                                a = rand.nextInt(nodenum)+1;
                            } 
                        }
                    }
                    else {    
                        a=edgenumuse;
                    }                    
                    edgenumuse = edgenumuse-a;
                    for( j = 0 ; j < a; j++)
                    {
                        edge e = new edge();
                        e.next = rdm[j];
                        e.pre = i+1;                       
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
        
       
       
       
    }
    
}
