import javafx.beans.property.FloatProperty;

public class RollerShade extends DomoticDevice {
    public RollerShade (int channel, double alpha, double length) {
        super(nextId++, channel);
        motor = new Motor(alpha);
        ShadeTotalLength = 0;
        this.length = 0;
    }
    public void startUp(){
        motor.turnUp();
    }
    public void startDown(){
        motor.turnDown();
    }
    public void stop(){
        motor.stop();
    }
    public void advanceTime(float delta){
        motor.advanceTime(delta);
    }
    public String getHeader(){
        String s = "RS" + getId();
        return s;
    }
    public String toString(){
        String s;
        if(motor.getState() == MotorState.UPWARD)
            s="-100";
        else if(motor.getState() == MotorState.DOWNWARD)
            s="100";
        else 
            s = "0";
        return s;
    }
    private class Motor {  //nested class, Motor is only used within RollerShade.
        public Motor (double a){
            alpha = a;
            state = MotorState.STOPPED;
        }
        public void turnUp(){
            state = MotorState.UPWARD;
        }
        public void turnDown(){
            state = MotorState.DOWNWARD;
        }
        public void stop(){
            state = MotorState.STOPPED;
        }
        public void advanceTime(float delta){
            double increment = alpha*delta*RADIUS;
            switch (state) {
                case STOPPED:
                    length += 0;
                    break;
                case DOWNWARD:
                    if ((length+increment)<=ShadeTotalLength){
                        length += increment;
                    }else length +=0;
                    break;
                case UPWARD:
                    if ((length-increment) >=0){
                        length -= increment;
                    }
                    break;
            }
        }
        public MotorState getState(){
            return state;
        }
        private double alpha;   
        private MotorState state;
        private static final double RADIUS=0.04;
    }

    private Motor motor;
    private double length;
    private final double ShadeTotalLength;
    private static int nextId;
}
