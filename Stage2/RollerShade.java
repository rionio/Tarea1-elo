public class RollerShade extends DomoticDevice {
    public RollerShade (int channel, double alpha, double length) {
        super(nextId++, channel);
        motor = new Motor(alpha);
        ShadeTotalLength = length;
        this.length = length;
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
    public void advanceTime(double delta){
        motor.advanceTime(delta);
    }
    public String getHeader(){
        String s = "RS" + getId();
        return s;
    }
    public String toString(){
        String s = String.valueOf(Math.round((length/ShadeTotalLength)*100)) + "%";
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
        public void advanceTime(double delta){
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
        private double alpha;   
        private MotorState state;
        private static final double RADIUS=0.04;
    }

    private Motor motor;
    private double length;
    private final double ShadeTotalLength;
    private static int nextId;
}
