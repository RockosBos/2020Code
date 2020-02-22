
/* 
    __   ___   _       ___   ____       __    __  __ __    ___    ___  _     
   /  ] /   \ | |     /   \ |    \     |  |__|  ||  |  |  /  _]  /  _]| |    
  /  / |     || |    |     ||  D  )    |  |  |  ||  |  | /  [_  /  [_ | |    
 /  /  |  O  || |___ |  O  ||    /     |  |  |  ||  _  ||    _]|    _]| |___ 
/   \_ |     ||     ||     ||    \     |  `  '  ||  |  ||   [_ |   [_ |     |
\     ||     ||     ||     ||  .  \     \      / |  |  ||     ||     ||     |
 \____| \___/ |_____| \___/ |__|\_|      \_/\_/  |__|__||_____||_____||_____|
                                                                             
*/                                                            
   ////fuck       Grape Fruit is a fruit then grapes come along like wtf                                                  
   package frc.robot;

   //import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
   import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
   import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.Robot.Constants;
import frc.robot.Robot.MC;
   
   
   
   //Color Wheel Stuff
   
   public class WheelControl{//wheel position
    int step = 0;
    char color;
    char startingColor;
    char prevColor;
    boolean ready;

    WheelControl(char color){
        this.color = color;
    }

    public void wheelMan(double speed, boolean left, boolean right, WPI_TalonSRX motor){
        if(left)
            motor.set(-speed);
        else if(right)
            motor.set(speed);
        else
            motor.set(0);
    }
    public void wheelMan(double speed, boolean left, boolean right, WPI_VictorSPX motor){
        if(left)
            motor.set(-speed);
        else if(right)
            motor.set(speed);
        else
            motor.set(0);
    }
    
    public void wheelPosition(char color, char desiredColor, double ss, double fs, WPI_TalonSRX motor){
          

            System.out.println(desiredColor);
            //SmartDashboard.putString("Desired Color", desiredColor);
            //SmartDashboard.putString("Current Color", color);

            //  System.out.println(color);
    
        
        if(color != desiredColor){// left negitave right positive 
            //System.out.println("Fuck");
            if(desiredColor == 'G'){///////////////////////////////green///////////////////  
                if(color == 'R')// blue to green
                    motor.set(-ss);
                else if(color == 'G')// yellow to green
                    motor.set(-fs);
                else if(color == 'B')//red to green
                    motor.set(ss);
            } else if(desiredColor == 'R'){///////////////////////////red/////////////////////
                if(color == 'Y')//green to red
                    motor.set(-ss);
                else if(color == 'R')//blue to red
                    motor.set(-fs);
                else if(color == 'G')//yellow to red
                    motor.set(ss);
            } else if(desiredColor == 'Y'){//////////////////////////////yellow/////////////////
                if(color == 'R')//blue to yellow
                    motor.set(ss);
                else if(color == 'B')//red to yellow
                    motor.set(-ss);
                else if(color == 'Y')// green to yellow
                    motor.set(-fs);
            } else if(desiredColor == 'B'){/////////////////////////blue////////////////////////////
                if(color == 'Y')//green to blue
                    motor.set(-ss);
                else if(color == 'G')//yellow to blue
                    motor.set(ss);
                else if(color == 'B')//red to blue
                    motor.set(fs);
            } else motor.set(0.0);
        }
    }   
    
    
    public void wheelPosition(char color, char desiredColor, double ss, double fs, WPI_VictorSPX motor){
          

        System.out.println(desiredColor);
        //SmartDashboard.putString("Desired Color", desiredColor);
        //SmartDashboard.putString("Current Color", color);

        //  System.out.println(color);

    
        if(color != desiredColor){// left negitave right positive 
            if(desiredColor == 'G'){///////////////////////////////green//////////////////
                if(color == 'R')// blue to green
                    motor.set(-ss);
                else if(color == 'G')// yellow to green
                    motor.set(-fs);
                else if(color == 'B')//red to green
                    motor.set(ss);
            } else if(desiredColor == 'R'){///////////////////////////red/////////////////////
                if(color == 'Y')//green to red
                    motor.set(-ss);
                else if(color == 'R')//blue to red
                    motor.set(-fs);
                else if(color == 'G')//yellow to red
                    motor.set(ss);
            } else if(desiredColor == 'Y'){//////////////////////////////yellow/////////////////
                if(color == 'R')//blue to yellow
                    motor.set(ss);
                else if(color == 'B')//red to yellow
                    motor.set(-ss);
                else if(color == 'Y')// green to yellow
                    motor.set(-fs);
            } else if(desiredColor == 'B'){/////////////////////////blue////////////////////////////
                if(color == 'Y')//green to blue
                    motor.set(-ss);
                else if(color == 'G')//yellow to blue
                    motor.set(ss);
                else if(color == 'B')//red to blue
                    motor.set(fs);
            } else motor.set(0.0);
        }
    }

    public void wheelRotation(){
        if(!ready){
            if(startingColor != color && startingColor != 'N'){
                ready = true;
            }
        }
        if(step < 6){
            MC.controlWheelWheel.set(Constants.CONTROL_WHEEL_WHEEL_SPEED);
            if(step == 0){
                startingColor = color;
                step = 1;
                ready = false;
            } 
            if(color == startingColor && ready){
                step = step + 1;
                ready = false;
            }
        } else if(step == 6){
            MC.controlWheelWheel.set(Constants.CONTROL_WHEEL_WHEEL_SPEED * .25);
            if(color == startingColor && ready){
                step = step + 1;
                ready = false;
            }
        } else if(step == 7){
            MC.controlWheelWheel.set(0);
            if(color == startingColor && ready){
                step = step + 1;
                ready = false;
            }
        } 
    }

}
      
       

   
   
