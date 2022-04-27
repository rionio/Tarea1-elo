public class Lamp {
    public Lamp (int channel){
        r=255;
        g=255;
        b=255;
        state=LampState.OFF;
        this.channel=channel;
    }
    {
        id=nextId++;
    }
    public int getChannel(){
        return channel;
    }
    public void changePowerState(){
        if(state == LampState.ON)
           state = LampState.OFF;
        else
            state = LampState.ON;
        
    }
    public String getHeader(){
        String id=Integer.toString(this.id);
        return id;    
    }
    public String toString(){
        if (state==LampState.ON)
            return ""+r+"\t"+g+"\t"+b;
        else
            return "0\t0\t0";
    }
    public String getState(){
        String state = String.valueOf(this.state);
        return state;
    }
    private int channel;
    private short r,g,b;
    private LampState state;
    private final int id;  // to conform lamp's header
    private static int nextId=0;
}
