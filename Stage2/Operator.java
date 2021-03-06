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
    }
/*     public void addLampControl(LampControl l){
        lampControl.add(l);
    } */
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
                bw.write("Unexpected device:" + device+"\n");
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
                    default: bw.write("Unexpected command: "+ command+"\n");
                        System.exit(-1);
                }
            }
        }
        bw.write(time+"\t"+cloud.getStates()+"\n");
        bw.close();
    }
    private double time=0;
    /* private ArrayList<LampControl> lampControl; */
    private ArrayList<ShadeControl> shadeControl;
    private Cloud cloud;
    private final double delta=0.1;
}
