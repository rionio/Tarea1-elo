import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Stage4 {
    public static void main(String [] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java Stage4 <configurationFile.txt>");
            System.exit(-1);
        }
        Scanner in = new Scanner( new File(args[0]));//args[0]
        //System.out.println("File: " + args[0]);
        Cloud cloud = new Cloud();
        int numRoller=in.nextInt(); 
        int numLamps=in.nextInt();
        int numShadeControl=in.nextInt();
        int numLampsControl=in.nextInt();
        in.nextLine();
        for(int n=0; n<numRoller; n++){
            double alfa = Double.parseDouble(in.next());
            double length = Double.parseDouble(in.next());
            int channel = in.nextInt();
            RollerShade roller = new RollerShade(channel, alfa, length);
            cloud.addRollerShade(roller);
        }

        for(int n=0; n<numLamps; n++){
            int lampChannel = in.nextInt();
            Lamp lamp = new Lamp(lampChannel);
            cloud.addLamp(lamp);
        }
        Operator operator = new Operator(cloud);

        for(int n=0;n<numShadeControl;n++){
            int shadeChannel = in.nextInt();
            ShadeControl shadeControl = new ShadeControl(shadeChannel, cloud);
            operator.addShadeControl(shadeControl);
        }

        for(int n=0; n<numLampsControl; n++){
            int lampChannel = in.nextInt();
            LampControl lampControl = new LampControl(lampChannel, cloud);
            operator.addLampControl(lampControl);    
        }
        operator.executeCommands(in, System.out);
    }
}
