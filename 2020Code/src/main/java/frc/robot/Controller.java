package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controller {
    

    public int joyStickSlot;
    
    public Joystick js = new Joystick(joyStickSlot);

    public boolean Trigger = js.getRawButton(1);

    public  boolean BottomFace = js.getRawButton(2);

    public boolean LeftFace = js.getRawButton(3);

    public boolean RightFace = js.getRawButton(4);
    //(Left Side)
    public boolean L1 = js.getRawButton(5);
    public boolean L2 = js.getRawButton(6);
    public boolean L3 = js.getRawButton(7);
    public boolean L6 = js.getRawButton(8);
    public boolean L5 = js.getRawButton(9);
    public  boolean L4 = js.getRawButton(10);
    //(Right SIDE)
    public boolean R1 = js.getRawButton(13);
    public boolean R2 = js.getRawButton(12);
    public boolean R3 = js.getRawButton(11);
    public boolean R4 = js.getRawButton(14);
    public boolean R5 = js.getRawButton(15);
    public boolean R6 = js.getRawButton(16);

    //Class Functions--------------------------------------

    Controller(int value){
        joyStickSlot = value;
    }

    /*public boolean getTrigger(){
        setTrigger(js.getRawButton(1));
        return Trigger;
    }
    public void setTrigger(boolean value){
         Trigger = value;
    }*/
}

