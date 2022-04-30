package Stage1;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Operator {
    public Operator( Cloud c){
        this.cloud = c;
        lampControl = new ArrayList<LampControl>();
    }
    public void addLampControl(LampControl l){
        lampControl.add(l);
    }
    public void executeCommands(Scanner in, PrintStream out){
        out.println("Time\t" + cloud.getHeaders());
         
        while(in.hasNextInt()){
            time =in.nextInt(); 
            out.println(time+"\t"+cloud.getStates());
            String string=in.next();
            if (!string.equals("L")) {
                out.println("Unexpected device:" + string);
                System.exit(-1);
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
                    String command2 = in.next();
                    switch(command2){
                        case "U":
                            control.upColor(command1);
                        case "D":
                            control.downColor(command1);
                    }
                }
            }
        }
    }
    private double time=0;
    private ArrayList<LampControl> lampControl;
    private Cloud cloud;
}
