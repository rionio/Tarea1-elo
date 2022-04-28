import javafx.collections.ListChangeListener.Change;

public class LampControl {
    public LampControl(int channel, Cloud c){
        this.channel = channel;
        cloud = c;
    }
    public void pressPower(){
        cloud.changeLampPowerState(channel);
    }
    public int getChannel(){
        return channel;
    }

    private Cloud cloud;
    private int channel;
}
