import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Operator {
    public Operator( Cloud c){
        this.cloud = c;
        lampControl = new ArrayList<LampControl>();
    }
    public void addLampControl(LampControl l){
        lampControl.add(l);
    }
    public void executeCommands(Scanner in, PrintStream out) throws IOException{
        File file=new File("./Domotic.csv");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Time\t" + cloud.getHeaders()+"\n");
        while(in.hasNextInt()){
            time =in.nextInt();
            String string=in.next();
            if (string.equals("C")) {
                bw.write("Unexpected device:" + string+"\n");
                in.nextLine();
            }else{
                int channel = in.nextInt();
                LampControl control=null;
                for(LampControl l: lampControl){
                    if(channel == l.getChannel()){
                        control = l;
                    }
                }
                String command1 = in.next();
                if(command1.equals("N") || command1.equals("F")){
                    control.pressPower();
                }else{
                    in.next();
  /*                   String command2 = in.next();
                    switch(command2){
                        case "U":
                            control.upColor(command1);
                            break;
                        case "D":
                            control.downColor(command1);
                            break;
                    } */
                }
            }
            bw.write(time+"\t"+cloud.getStates()+"\n");
        }
        bw.close();
    }
    private double time=0;
    private ArrayList<LampControl> lampControl;
    private Cloud cloud;
}
