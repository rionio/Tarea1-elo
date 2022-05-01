import java.util.ArrayList;

public class Cloud {
    public Cloud() {
        /* lamps = new ArrayList<Lamp>(); */
        rollerShades = new ArrayList<RollerShade>();
    }
/*     public void addLamp(Lamp l){
        lamps.add(l);
    } */
    public void addRollerShade(RollerShade rs){
        rollerShades.add(rs);
    }
    public void advanceTime(double delta){
        for (DomoticDevice dd: rollerShades) {
            RollerShade rs =(RollerShade)dd;
            rs.advanceTime(delta);
        }
    }
    private String getDomoticDeviceAtChannel( ArrayList<DomoticDevice> devices, int channel){
        String id = "";
        for(DomoticDevice dv: devices){
            if(dv.getChannel() == channel){
                id+= dv.getId()+"\t";
            }
        }
        return id;
    }
    public void startShadeUp(int channel){
        for (RollerShade rs: rollerShades) {
            if (rs.getChannel() == channel) {
                rs.startUp();
            }
        }
    }
    public void startShadeDown(int channel){
        for (RollerShade rs: rollerShades) {
            if (rs.getChannel() == channel) {
                rs.startDown();
            }
        }
    }
    public void stopShade(int channel){
        for (RollerShade rs: rollerShades) {
            if (rs.getChannel() == channel) {
                rs.stop();
            }
        }
    }

/*     public Lamp getLampAtChannel( int channel){
        for (Lamp l: lamps){
            if(l.getChannel()==channel)
               return l;
        }
        return null;
    } */
/*     public void changeLampPowerState(int channel){
        for (Lamp l: lamps){
            if(l.getChannel()==channel)
               l.changePowerState();
        }
    } */
    public String getHeaders(){
        String header = "";
        for(DomoticDevice rs: rollerShades){
            header+=rs.getHeader()+"\t";
        }
/*         for (Lamp l: lamps){
            header +="L"+l.getHeader()+"R\t";
            header +="L"+l.getHeader()+"G\t";
            header +="L"+l.getHeader()+"B\t";
        } */
        return header;
    }
    public String getStates(){
        //String lamp_state = "";
        String roller_state = "";
        /* for(DomoticDevice l: lamps){
            lamp_state+=l.toString()+"\t";
        } */
        for (DomoticDevice rs: rollerShades){
            roller_state+=rs.toString()+"\t";
        }
        return roller_state +"\t";  
    }

    /* public void upColor(int channel, String color){
        for(Lamp l : lamps){
            if(l.getChannel() == channel){
                l.upColor(color);
            }
        }
    } */
   /*  public void downColor(int channel, String color){
        for(Lamp l : lamps){
            if(l.getChannel() == channel){
                l.downColor(color);
            }
        }
    } */
    
    //private ArrayList<Lamp> lamps;
    private ArrayList<RollerShade> rollerShades; // getting ready for next stagesas
}
