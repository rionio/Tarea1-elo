import java.nio.file.ClosedDirectoryStreamException;

public class LightSensor {
    public LightSensor(Cloud c){
        cloud=c;
    }
    public void Observe(int channel){
        if(cloud.getLampsRGB(channel)>512){
            cloud.shutDownRollers(channel);
        }
    }
    private Cloud cloud;
}
