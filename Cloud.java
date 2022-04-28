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
        int count = 1;
        for (Lamp l: lamps){
            header += "Lamp "+count+": "+l.getHeader()+", ";
            count++;
        }
        return header;
    }
    public String getState(int channel){
        for (Lamp l: lamps){
            if(l.getChannel()==channel)
               return l.getState();
        }
        return null;  
    }
    private ArrayList<Lamp> lamps; // getting ready for next stages
}
