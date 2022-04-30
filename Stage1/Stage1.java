import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Stage1 {
    public static void main(String [] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java Stage1 <configurationFile.txt>");
            System.exit(-1);
        }
        Scanner in = new Scanner( new File(args[0]));//
        //System.out.println("File: " + args[0]);
        Cloud cloud = new Cloud();
        // reading <#_de_cortinas> <#_de_lámparas> <#_controles_cortinas> <#_controles_lámparas>
        in.nextInt();  // skip number of roller shades
        int numLamps = in.nextInt();
        in.nextInt();  // skip number of roller shade controls
        int numLampsControl = in.nextInt();
        // skipping <alfa0> <length0> <canal0> … <alfaN_1> <lengthN_1> <canalN_1>
        in.nextLine();
        in.nextLine();
        // creating lamps according to <canal0>…..<canalL_1> for just one lamp
        String channels = in.nextLine();
        for(int n=0; n<channels.length(); n++){
            char a = channels.charAt(n);
            int lampChannel = Character.getNumericValue(a);
            if(a != ' '){
                Lamp lamp = new Lamp(lampChannel);
                cloud.addLamp(lamp);        
            }
        }
        // skipping creation of roller shade's controls at <canal0>
        in.nextLine();
        // creating just one lamp's control at <canal0>
        String str = in.nextLine();
        Operator operator = new Operator(cloud);
        for(int n=0; n<str.length(); n++){
            char a = str.charAt(n);
            int channel = Character.getNumericValue(a);
            if(a != ' '){
                LampControl lampControl = new LampControl(channel, cloud);
                operator.addLampControl(lampControl);        
            }
        }
        operator.executeCommands(in, System.out);
    }
}
