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
        shadeControl = new ArrayList<ShadeControl>();
        lampControl = new ArrayList<LampControl>();
    }
    public void addLampControl(LampControl l){
        lampControl.add(l);
    }
    public void addShadeControl(ShadeControl rs){
        shadeControl.add(rs);
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
            int commandTime =in.nextInt();
            DecimalFormat decimalFormat= new DecimalFormat("#.#"); 
            
            while (time < commandTime) {
                String timeTrun = decimalFormat.format(time);
                bw.write(timeTrun+"\t"+cloud.getStates()+"\n");
                cloud.advanceTime(delta);
                time+=delta;
            }
            String device=in.next();
            if (!device.equals("C")) {
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
                            break;
                        case "D":
                            control.downColor(command1);
                            break;
                    }
                }
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
                    default: bw.write("Unexpected command: "+ command+"\n");
                        System.exit(-1);
                }
            }
        }
        bw.close();
    }
    private float time=0;
    private ArrayList<LampControl> lampControl;
    private ArrayList<ShadeControl> shadeControl;
    private Cloud cloud;
    private final float delta=0.1f;
}
