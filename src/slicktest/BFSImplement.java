package slicktest;

//import java.util.Comparator;
//import java.util.PriorityQueue;
//import java.util.StringTokenizer;
import java.util.*;

public class BFSImplement {
//response="I:P0:2,6;4,8;8,1;0,3;2,7;7,1:9,3;6,8;7,4;1,3;3,2;5,7;3,6;5,8;4,2;8,7:0,4;1,7;8,6;6,2;6,7;9,2;7,2;5,4;6,3;3,1#";
    
    Vertex[][] grid;
    PriorityQueue<Vertex> vertexQue;

    public BFSImplement() {
        this.grid = new Vertex[20][20];
        VertexComparator vComp=new VertexComparator();
        this.vertexQue=new PriorityQueue<Vertex>(10,vComp);
        
        for(int a=0;a<20;a++){
            for(int b=0;b<20;b++){
                Vertex vtx=new Vertex(b,a);
                vtx.setColour(-1);
                grid[a][b]=new Vertex(b,a);
            }
        }
    }
    
    
    public void setGrid(String str,String cellType){
        StringTokenizer str1=new StringTokenizer(str,";");
        StringTokenizer str2;
        int tokens=str1.countTokens();
        int rw,cl;
        
        for(int a=0;a<tokens;a++){
            str2=new StringTokenizer(str1.nextToken(),",");
            cl=Integer.parseInt(str2.nextToken());
            rw=Integer.parseInt(str2.nextToken());
            //this.grid[(Integer.parseInt(str2.nextToken()))][(Integer.parseInt(str2.nextToken()))].invalid=true;
            this.grid[rw][cl].invalid=true;
            this.grid[rw][cl].testVal=cellType;
        }
        
//        for(int a=0;a<20;a++){
//            for(int b=0;b<20;b++){
//                System.out.print(grid[a][b].testVal+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }
    
    public void setTree(int sX,int sY,int eX,int eY,Vector<Vertex> path){
        grid[sY][sX].setColour(0);
        grid[sY][sX].setDistance(0);
        vertexQue.add(grid[sY][sX]);
        
        while(!vertexQue.isEmpty()){
            Vertex vtx=vertexQue.poll();
            //System.out.println(vtx.xCord+","+vtx.yCord);
            
            //check left child
            if(vtx.xCord-1>=0){
                if(grid[vtx.yCord][vtx.xCord-1].cost>1+calculateHuristic(vtx.xCord,vtx.yCord, eX, eY)+grid[vtx.yCord][vtx.xCord].getDistance() &&(!grid[vtx.yCord][vtx.xCord-1].invalid)){
                    grid[vtx.yCord][vtx.xCord-1].setDistance(1+grid[vtx.yCord][vtx.xCord].getDistance());
                    grid[vtx.yCord][vtx.xCord-1].cost=1+calculateHuristic(vtx.xCord,vtx.yCord, eX, eY)+grid[vtx.yCord][vtx.xCord].getDistance();
                    grid[vtx.yCord][vtx.xCord-1].setColour(0);
                    grid[vtx.yCord][vtx.xCord-1].setPredicessor(vtx);
                    vertexQue.add(grid[vtx.yCord][vtx.xCord-1]);
                }
            }
            //check right child
            if(vtx.xCord+1<=19){
              if(grid[vtx.yCord][vtx.xCord+1].cost>1+calculateHuristic(vtx.xCord,vtx.yCord, eX, eY)+grid[vtx.yCord][vtx.xCord].getDistance() &&(!grid[vtx.yCord][vtx.xCord+1].invalid)){
                    grid[vtx.yCord][vtx.xCord+1].setDistance(1+grid[vtx.yCord][vtx.xCord].getDistance());
                    grid[vtx.yCord][vtx.xCord+1].cost=1+calculateHuristic(vtx.xCord,vtx.yCord, eX, eY)+grid[vtx.yCord][vtx.xCord].getDistance();
                    grid[vtx.yCord][vtx.xCord+1].setColour(0);
                    grid[vtx.yCord][vtx.xCord+1].setPredicessor(vtx);
                    vertexQue.add(grid[vtx.yCord][vtx.xCord+1]);
                } 
            }
            //check up child
            if(vtx.yCord-1>=0){
                if(grid[vtx.yCord-1][vtx.xCord].cost>1+calculateHuristic(vtx.xCord,vtx.yCord, eX, eY)+grid[vtx.yCord][vtx.xCord].getDistance() &&(!grid[vtx.yCord-1][vtx.xCord].invalid)){
                    grid[vtx.yCord-1][vtx.xCord].setDistance(1+grid[vtx.yCord][vtx.xCord].getDistance());
                    grid[vtx.yCord-1][vtx.xCord].cost=1+calculateHuristic(vtx.xCord,vtx.yCord, eX, eY)+grid[vtx.yCord][vtx.xCord].getDistance();
                    grid[vtx.yCord-1][vtx.xCord].setColour(0);
                    grid[vtx.yCord-1][vtx.xCord].setPredicessor(vtx);
                    vertexQue.add(grid[vtx.yCord-1][vtx.xCord]);
                }
            }
            //check down child
            if(vtx.yCord+1<=19){
                if(grid[vtx.yCord+1][vtx.xCord].cost>1+calculateHuristic(vtx.xCord,vtx.yCord, eX, eY)+grid[vtx.yCord][vtx.xCord].getDistance() &&(!grid[vtx.yCord+1][vtx.xCord].invalid)){
                    grid[vtx.yCord+1][vtx.xCord].setDistance(1+grid[vtx.yCord][vtx.xCord].getDistance());
                    grid[vtx.yCord+1][vtx.xCord].cost=1+calculateHuristic(vtx.xCord,vtx.yCord, eX, eY)+grid[vtx.yCord][vtx.xCord].getDistance();
                    grid[vtx.yCord+1][vtx.xCord].setColour(0);
                    grid[vtx.yCord+1][vtx.xCord].setPredicessor(vtx);
                    vertexQue.add(grid[vtx.yCord+1][vtx.xCord]);
                }
            }
            vtx.setColour(1);
        }
        
        Vertex vt=grid[eY][eX];
        do{
            //System.out.println(vt.xCord+","+vt.yCord);
            path.add(vt);
            grid[vt.yCord][vt.xCord].testVal="P";
            vt=vt.getPredicessor();
        }
        while(!(vt.xCord==sX&&vt.yCord==sY));
        
        for(int a=0;a<20;a++){
            for(int b=0;b<20;b++){
                System.out.print(grid[a][b].testVal+" ");
            }
            System.out.println();
        }
    }
    
    public int calculateHuristic(int sX,int sY,int eX,int eY){
        return Math.abs(sX-eX)+Math.abs(sY-eY);
    }
    
    
    

//    public static void main(String[] args) {
//        BFSImplement bfsI=new BFSImplement();
//        bfsI.setGrid("I:P0:2,6;4,8;8,1;0,3;2,7;7,1;9,3;6,8;7,4;1,3;3,2;5,7;3,6;5,8;4,2;8,7;0,4;1,7;8,6;6,2;8,7;9,2;7,2;5,4;6,3;3,1#"/*#"*/);
//        bfsI.setTree(0,0,7,6);
//    }
}


class VertexComparator implements Comparator<Vertex>{
    
    @Override
    public int compare(Vertex o1, Vertex o2) {
        if(o2.cost<o1.cost){
            return 1;
        }
        else if(o2.cost>o1.cost){
            return -1;
        }
        else{
            return 0;
        }
    }
}
