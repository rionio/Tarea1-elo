package Stage1;
public class LampControl {
    public LampControl(int channel, Cloud c){
        this.channel = channel;
        this.cloud = c;
        
    }
    public void pressPower(){
        cloud.changeLampPowerState(channel);
    }
    public int getChannel(){
        return channel;
    }
    public void upColor(String color){
        cloud.upColor(channel, color);
    }
    public void downColor(String color){
        cloud.downColor(channel, color);
    }
    private Cloud cloud;
    private int channel;
}
