package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot.ToggleLogic;

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

    public int pov;
    public boolean povUp;
    public boolean povUpRight;
    public boolean povRight;
    public boolean povDownRight;
    public boolean povDown;
    public boolean povDownLeft;
    public boolean povLeft;
    public boolean povUpLeft;
    
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
        // POV
        pov = js.getPOV(0);
        updatePOV();
    }

    public boolean toggleButton(ToggleLogic toggle){
        if(toggle.currentState && !toggle.prevState){
            if(toggle.value){
                toggle.value = false;
            }
            else{
                toggle.value = true;
            }
            
        }
        toggle.prevState = toggle.currentState;
        return toggle.value;
    }
    public void updatePOV(){
        if(pov == 0){
            povUp = true;
        }
        else{
            povUp = false;
        }
        if(pov == 45){
            povUpRight = true;
        }
        else{
            povUpRight = false;
        }
        if(pov == 90){
            povRight = true;
        }
        else{
            povRight = false;
        }
        if(pov == 135){
            povDownRight = true;
        }
        else{
            povDownRight = false;
        }
        if(pov == 180){
            povDown = true;
        }
        else{
            povDown = false;
        }
        if(pov == 225){
            povDownLeft = true;
        }
        else{
            povDownLeft = false;
        }
        if(pov == 270){
            povLeft = true;
        }
        else{
            povLeft = false;
        }
        if(pov == 315){
            povUpLeft = true;
        }
        else{
            povUpLeft = false;
        }
    }
}

