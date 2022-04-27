import javafx.collections.ListChangeListener.Change;

public class LampControl {
    public LampControl(int channel, Cloud c){
        //???
    }
    public void pressPower(){
        cloud.changeLampPowerState(channel);
    }
    public int getChannel(){
        //????
    }
    //???
    private Cloud cloud;
    private int channel;
}
