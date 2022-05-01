import java.io.PrintStream;
import java.util.ArrayList;
import java.lang.reflect.Array;
import java.util.Scanner;

public class Operator {
    public Operator( Cloud c){
        this.cloud = c;
        shadeControl = new ArrayList<ShadeControl>();
    }
/*     public void addLampControl(LampControl l){
        lampControl.add(l);
    } */
    public void addShadeControl(ShadeControl rs){
        shadeControl.add(rs);
    }
    public void executeCommands(Scanner in, PrintStream out){
        out.println("Time\t" + cloud.getHeaders());
        while(in.hasNextInt()){
            int commandTime =in.nextInt();
            while (time < commandTime) {
                out.println(time+"\t"+cloud.getStates());
                cloud.advanceTime(delta);
                time+=delta;
            }
            String device=in.next();
            if (!device.equals("C")) {
                out.println("Unexpected device:" + device);
                in.nextLine();
            }else{
                int channel = in.nextInt();
                String command = in.next();
                ShadeControl control=null;
                for(ShadeControl l: shadeControl){
                    if(channel == l.getChannel()){
                        control = l;
                    }
                }
                switch(command){
                    case "U":
                        control.startUp();
                        break;
                    case "D":
                        control.startDown();
                        break;
                    case "S":
                        control.stop();
                        break;
                    default: out.println("Unexpected command: "+ command);
                        System.exit(-1);
                }
            }
        }
        out.println(time+"\t"+cloud.getStates());
    }
    private double time=0;
    /* private ArrayList<LampControl> lampControl; */
    private ArrayList<ShadeControl> shadeControl;
    private Cloud cloud;
    private final double delta=0.1;
}
