package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controller {
    

    public int joyStickSlot;

    public Joystick js;

    public boolean Trigger;

    public boolean BottomFace;

    public boolean LeftFace;

    public boolean RightFace;
    //(Left Side)
    public boolean L1;
    public boolean L2;
    public boolean L3;
    public boolean L6;
    public boolean L5;
    public  boolean L4;
    //(Right SIDE)
    public boolean R1;
    public boolean R2;
    public boolean R3;
    public boolean R4;
    public boolean R5;
    public boolean R6;

    //Class Functions--------------------------------------
    Controller(int joystickPort){
        js = new Joystick(joystickPort);
        
    }
    public boolean getTrigger(){
        setTrigger(js.getRawButton(1));
        return Trigger;
    }
    public void setTrigger(boolean value){
         Trigger = value;
    }
    public boolean getR1() {
        return R1;
    }
    public void updateValues(){
        Trigger = js.getRawButton(1);

        BottomFace = js.getRawButton(2);
    
        LeftFace = js.getRawButton(3);
    
        RightFace = js.getRawButton(4);
        //(Left Side)
        L1 = js.getRawButton(5);
        L2 = js.getRawButton(6);
        L3 = js.getRawButton(7);
        L6 = js.getRawButton(8);
        L5 = js.getRawButton(9);
        L4 = js.getRawButton(10);
        //(Right SIDE)
        R1 = js.getRawButton(13);
        R2 = js.getRawButton(12);
        R3 = js.getRawButton(11);
        R4 = js.getRawButton(14);
        R5 = js.getRawButton(15);
        R6 = js.getRawButton(16);
    }
}

