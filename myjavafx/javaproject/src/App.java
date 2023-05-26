import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.stage.FileChooser.ExtensionFilter;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App extends Application {
    static ArrayList<Block> blockArray = new ArrayList <Block>();
    static ArrayList<Arrow> lineArray = new ArrayList<Arrow>();


    //function to return the index of the block in the block array with respect to the given ID
    static int getBlockIndex(int ID){
        for (int i = 0; i < blockArray.size(); i++) {
            if (blockArray.get(i).getID() == ID) {
                return i;
            }
            
        }
        return -1;
    }

    

    //function to adjust the y coord of PINSS
    static double adjPin (double ytop  , double yButtom , int Pin){
        switch (Pin) {
            case 1:
            return (ytop+yButtom)/2 ;
            
            case 2:
            return ytop + ((yButtom-ytop)/4);
            
            case 3:
            return ytop + ((yButtom-ytop)/4)*3;
            
            default:
            return (ytop+yButtom)/2 ;
        }
    }

    //function to determine the direction of the arrow tip (bases lel yemin wala el shemal)
    static Polygon drawArrowTip(double x , double y ,Block block){
        if(x == block.getStartX()){
            return Draw.createtriangle(x, y);    //da biersem wa7ed bases lel yemin 
        }
        else {
            return Draw.createtrianglel(x, y);  
        }
        
    }


    @Override
    public void start(Stage stage) throws IOException {
        


        //creating pane
        GridPane p=new GridPane();
        p.setAlignment(Pos.CENTER);
        p.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        p.setHgap(5.5);
        p.setVgap(5.5);


        //add label
        Label l=new Label("Choose a file:");
        p.add(l,0,0);
        GridPane.setHalignment(l, HPos.CENTER);

        //add buttons
        Button btOK = new Button("OK");
        GridPane.setHalignment(btOK, HPos.CENTER);
        Button btcancel = new Button("Cancel");
        p.add(btOK,0,1);
        p.add(btcancel,1,1);

        GridPane.setHalignment(btOK, HPos.CENTER);
        GridPane.setHalignment(btcancel, HPos.CENTER);


        //creating scene
        Scene scene = new Scene(p, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


        //handler for btcancel
        btcancel.setOnAction((ActionEvent e)->{
            stage.close();

          });

        //handler for btOK
        btOK.setOnAction((ActionEvent e)->{

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("mdl files", "*.mdl");
            fileChooser.getExtensionFilters().add(extFilter);
            File f=fileChooser.showOpenDialog(stage);



            // check if the user has selected a file
            if (f != null) {
                // get the path of the selected file
                String filePath = f.getPath();
                System.out.println("Selected file path: " + filePath);
                stage.close();


                //second stage
                Pane pane = new Pane();
                Stage st = new Stage();


                //creating blocks and their texts(should be put in a for loop)
            /*    Rectangle s2 = Draw.createsquare(1040, 209, 1070-1040, 241-209);
                Text t2=new Text(1040,241+20,"Add");
                t2.setTextAlignment(TextAlignment.CENTER);

                Rectangle s3 = Draw.createsquare(780, 200, 810-780, 230-200);
                Text t3=new Text(780,230+20,"Constant");
                t3.setTextAlignment(TextAlignment.CENTER);

                Rectangle s = Draw.createsquare(935, 200, 965-935, 230-200);
                Text t=new Text(935,230+20,"Saturation");
                t.setTextAlignment(TextAlignment.CENTER);

                Rectangle s4 = Draw.createsquare(1130, 209, 1160-1130, 241-209);
                Text t4=new Text(1130,241+20,"Scope");
                t4.setTextAlignment(TextAlignment.CENTER);

                Rectangle s5 = Draw.createsquare(1040, 283, 1075-1040, 317-283);
                Text t5=new Text(1040,317+20,"Unit Delay");
                t5.setTextAlignment(TextAlignment.CENTER);*/
                try {

                    String fileName = filePath;
                    File file = new File(fileName);
                    Scanner extract = new Scanner(file);
                    String tempString="anything" ;
                    

                    while (!extract.nextLine().contains("<System>") ){}

                    while (!tempString.contains("/System>") ){
                        tempString = extract.nextLine();
                        //reading the blocks 
                        if (tempString.contains("<Block")){
                            Block myb = new Block();
                            myb.setBlockType(tempString.substring(tempString.indexOf("lockType=")+10, tempString.indexOf("Name")-2));
                            myb.setBlockName(tempString.substring(tempString.indexOf("Name=")+6, tempString.indexOf("SID")-2));
                            myb.setID(Integer.parseInt(tempString.substring(tempString.indexOf("SID=")+5, tempString.indexOf(">")-1)));
                            while (!tempString.contains("</Block>")){
                                tempString = extract.nextLine();
                                if(tempString.contains("Position")){
                                    tempString = tempString.substring(tempString.indexOf("[")+1,tempString.indexOf("]"));
                                    String [] numString =tempString.replaceAll("\\s+", "").split(",");
                                    //int[] numbers = new int[numString.length];
                                    myb.setStartX(Integer.parseInt(numString[0]));
                                    myb.setStartY(Integer.parseInt(numString[1]));
                                    myb.setEndX(Integer.parseInt(numString[2]));
                                    myb.setEndY(Integer.parseInt(numString[3]));

                                }

                            }
                            blockArray.add(myb);
                        }
                        //reading the lines
                        if(tempString.contains("<Line")){
                            Arrow myl = new Arrow ();
                            while(!tempString.contains("</Line>")){
                                tempString = extract.nextLine();
                                if (tempString.contains("Src")){
                                    myl.setSrc(Integer.parseInt(tempString.substring(tempString.indexOf("Src")+5,tempString.indexOf("#"))));
                                }
                                if (tempString.contains("Dst")&& myl.isBranch()==false){
                                    myl.setDst(Integer.parseInt(tempString.substring(tempString.indexOf("Dst")+5,tempString.indexOf("#"))));
                                    myl.setDstPin(Integer.parseInt(tempString.substring(tempString.indexOf(":")+1,tempString.indexOf("</"))));
                                }
                                if (tempString.contains("<Branch>")){
                                    myl.setBranch();
                                    while (!tempString.contains("</Branch>")){
                                        tempString = extract.nextLine();
                                        if (tempString.contains("Dst")){
                                            if (myl.getB1dst() == 0 ){
                                                myl.setB1dst(Integer.parseInt(tempString.substring(tempString.indexOf("Dst")+5,tempString.indexOf("#"))));
                                                myl.setB1Pin(Integer.parseInt(tempString.substring(tempString.indexOf(":")+1,tempString.indexOf("</"))));
                                            }else {
                                                myl.setB2dst(Integer.parseInt(tempString.substring(tempString.indexOf("Dst")+5,tempString.indexOf("#"))));
                                                myl.setB2Pin(Integer.parseInt(tempString.substring(tempString.indexOf(":")+1,tempString.indexOf("</"))));
                                            }
                                        }
        
                                    }
                                }
                            }
                            lineArray.add(myl);
                        }
                    }


                }catch (Exception m){
                    m.getMessage();
                    System.exit(0);
                }


                for (int i = 0; i < blockArray.size(); i++)
                {

                    Rectangle s= Draw.createsquare(blockArray.get(i).getStartX(), blockArray.get(i).getStartY(), blockArray.get(i).getWidth(), blockArray.get(i).getHeight());
                    Text t=new Text(blockArray.get(i).getStartX(),blockArray.get(i).getEndY()+20,blockArray.get(i).getBLockName());
                    t.setTextAlignment(TextAlignment.CENTER);
                    pane.getChildren().addAll(s,t);


                 }
                    double tempXSrc =0;
                    double tempXDst = 0;
                    int tempSrcIndex =0;
                    int tempDstIndex =0;
                    int tempPinBranch = 0;
                    double checkPX=0;
                    double checkPY=0;
                  for (int i =0 ; i<lineArray.size();i++){
                    tempSrcIndex = getBlockIndex(lineArray.get(i).getSrc());
                    if (!lineArray.get(i).isBranch()){
                        tempDstIndex = getBlockIndex(lineArray.get(i).getDst());
                        if (blockArray.get(tempDstIndex).getStartX()-blockArray.get(tempSrcIndex).getEndX()<3){ //3ashan fi special case el dst dakhel 3ala yemin el block 
                            tempXSrc = blockArray.get(tempSrcIndex).getStartX();
                        }else {
                            tempXSrc = blockArray.get(tempSrcIndex).getEndX();
                        }
                        pane.getChildren().add(Draw.createline(tempXSrc , blockArray.get(tempSrcIndex).getStartY()+blockArray.get(tempSrcIndex).getHeight()/2 , blockArray.get(tempDstIndex).getStartX()-10 , blockArray.get(tempSrcIndex).getStartY()+blockArray.get(tempSrcIndex).getHeight()/2));
                        pane.getChildren().add(Draw.createline(blockArray.get(tempDstIndex).getStartX()-10, blockArray.get(tempSrcIndex).getStartY()+blockArray.get(tempSrcIndex).getHeight()/2, blockArray.get(tempDstIndex).getStartX()-10, adjPin(blockArray.get(tempDstIndex).getStartY(), blockArray.get(tempDstIndex).getEndY(), lineArray.get(i).getDstPin())));
                        pane.getChildren().add(Draw.createline(blockArray.get(tempDstIndex).getStartX()-10, adjPin(blockArray.get(tempDstIndex).getStartY(), blockArray.get(tempDstIndex).getEndY(), lineArray.get(i).getDstPin()), blockArray.get(tempDstIndex).getStartX(), adjPin(blockArray.get(tempDstIndex).getStartY(), blockArray.get(tempDstIndex).getEndY(), lineArray.get(i).getDstPin())));
                        pane.getChildren().add(drawArrowTip(blockArray.get(tempDstIndex).getStartX(), adjPin(blockArray.get(tempDstIndex).getStartY(), blockArray.get(tempDstIndex).getEndY(), lineArray.get(i).getDstPin()), blockArray.get(tempDstIndex)));
                    }else {

                        
                        for (int x =0 ; x<2 ;x++){//3ashan mara branch one w mara le branch 2
                            if (x==0){
                                tempDstIndex = getBlockIndex(lineArray.get(i).getB2dst());
                                tempPinBranch = lineArray.get(i).getB2Pin();
                                tempXSrc = blockArray.get(tempSrcIndex).getEndX();
                                pane.getChildren().add(Draw.createline(tempXSrc , blockArray.get(tempSrcIndex).getStartY()+blockArray.get(tempSrcIndex).getHeight()/2 , blockArray.get(tempDstIndex).getStartX()-10 , blockArray.get(tempSrcIndex).getStartY()+blockArray.get(tempSrcIndex).getHeight()/2));
                                checkPX = blockArray.get(tempDstIndex).getStartX()-10 ;
                                checkPY =blockArray.get(tempSrcIndex).getStartY()+blockArray.get(tempSrcIndex).getHeight()/2;

                            }else {
                                tempDstIndex = getBlockIndex(lineArray.get(i).getB1dst());
                                tempPinBranch = lineArray.get(i).getB1Pin();
                            }


                            if (blockArray.get(tempDstIndex).getStartX()<blockArray.get(tempSrcIndex).getEndX()){ //3ashan fi special case el dst dakhel 3ala yemin el block 
                                tempXDst = blockArray.get(tempDstIndex).getEndX();
                            }else {
                                tempXDst = blockArray.get(tempDstIndex).getStartX();
                            }
                            
                        pane.getChildren().add(Draw.createline(checkPX, checkPY, checkPX, adjPin(blockArray.get(tempDstIndex).getStartY(), blockArray.get(tempDstIndex).getEndY(),tempPinBranch)));//el nozoul
                        
                        pane.getChildren().add(Draw.createline(checkPX, adjPin(blockArray.get(tempDstIndex).getStartY(), blockArray.get(tempDstIndex).getEndY(),tempPinBranch), tempXDst, adjPin(blockArray.get(tempDstIndex).getStartY(), blockArray.get(tempDstIndex).getEndY(),tempPinBranch)));
                        pane.getChildren().add(drawArrowTip(tempXDst, adjPin(blockArray.get(tempDstIndex).getStartY(), blockArray.get(tempDstIndex).getEndY(), tempPinBranch), blockArray.get(tempDstIndex)));
                            
                        }

                    }
                 }



                
                Scene sc=new Scene(pane,600,600);
                st.setScene(sc);
                st.show();
            }
            else
            { System.out.println("No file selected!");
              }

        });

    }



    public static void main(String[] args) throws Exception{
        launch();
    }

}