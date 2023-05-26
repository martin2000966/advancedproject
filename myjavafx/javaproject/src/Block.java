public class Block {
    static int numOfBlocks;
    private String blockType ;
    private String blockName ;
    private int  ID ; 
    private  double startX ,startY ,endX,endY ;
    private double width , height ;
    int inpPorts =0, outPorts=0 ; 
    

    Block (String blockType , String blockName  , int ID , double startX ,double startY, double endX , double endY, int inpPorts,int outPorts){
        this.blockName= blockName;
        this.blockType = blockType; 
        this.ID = ID; 
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.width = endX - startX ;
        this.height = endY - startY ;
        this.inpPorts = inpPorts;
        this.outPorts = outPorts;
        numOfBlocks++;

    }
    Block(){
        //matensash te8aiar el width w el height fl setters
        numOfBlocks++;
    }


    //getters
    public String getBLockType (){
        return blockType ;
    }
    public String getBLockName (){
        return blockName ;
    }
    public int getID (){
        return ID ;
    }
    public double getStartX (){
        return startX ;
    }
    public double getStartY (){
        return startY ;
    }
    public double getEndX(){
        return endX ;
    }
    public double getEndY(){
        return endY;
    }

    public double getWidth (){
        return width;
    }
    public double getHeight(){
        return height; 
    }
    public int getInpPorts(){
        return inpPorts;
    }
    public int getOutPorts (){
        return outPorts;
    }
    // setters 
    public void  setBlockType (String s){
        this.blockType = s;
    }
    public void setBlockName(String s){
        this.blockName = s;
    }
    public void setID (int ID){
        this.ID = ID;
    }
    public void setStartX(double x){
        this.startX = x; 
    }
    public void setStartY(double y){
        this.startY = y;
    }
    public void setEndX (double x ){
        this.endX = x;
        width= endX-startX; 
    }
    public void setEndY(double y){
        this.endY = y;
        height = endY-startY;
    }
    public void setInpPorts(int inpPorts){
        this.inpPorts= inpPorts;
    }
    public void setOutPorts(int outPorts){
        this.outPorts= outPorts;
    }
    @Override
    public String toString() {
        return "Block [blockType=" + blockType + ", blockName=" + blockName + ", ID=" + ID + ", startX=" + startX
                + ", startY=" + startY + ", endX=" + endX + ", endY=" + endY + ", width=" + width + ", height=" + height
                + ", inpPorts=" + inpPorts + ", outPorts=" + outPorts + "]\n\n\n";
    }
    

}