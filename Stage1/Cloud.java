package Stage1;
import java.util.ArrayList;

public class Cloud {
    public Cloud() {
        lamps = new ArrayList<Lamp>();
    }
    public void addLamp(Lamp l){
        lamps.add(l);
    }
    public Lamp getLampAtChannel( int channel){
        for (Lamp l: lamps){
            if(l.getChannel()==channel)
               return l;
        }
        return null;
    }
    public void changeLampPowerState(int channel){
        for (Lamp l: lamps){
            if(l.getChannel()==channel)
               l.changePowerState();
        }
    }
    public String getHeaders(){
        String header = "";
        for (Lamp l: lamps){
            header +="L"+l.getHeader()+"R\t";
            header +="L"+l.getHeader()+"G\t";
            header +="L"+l.getHeader()+"B\t";
        }
        return header;
    }
    public String getStates(){
        String state = "";
        for (Lamp l: lamps){
            state+=l.toString();
        }
        return state;  
    }

    public void upColor(int channel, String color){
        for(Lamp l : lamps){
            if(l.getChannel() == channel){
                l.upColor(color);
            }
        }
    }
    public void downColor(int channel, String color){
        for(Lamp l : lamps){
            if(l.getChannel() == channel){
                l.downColor(color);
            }
        }
    }


    private ArrayList<Lamp> lamps; // getting ready for next stagesas
}
