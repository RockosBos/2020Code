
/* 
    __   ___   _       ___   ____       __    __  __ __    ___    ___  _     
   /  ] /   \ | |     /   \ |    \     |  |__|  ||  |  |  /  _]  /  _]| |    
  /  / |     || |    |     ||  D  )    |  |  |  ||  |  | /  [_  /  [_ | |    
 /  /  |  O  || |___ |  O  ||    /     |  |  |  ||  _  ||    _]|    _]| |___ 
/   \_ |     ||     ||     ||    \     |  `  '  ||  |  ||   [_ |   [_ |     |
\     ||     ||     ||     ||  .  \     \      / |  |  ||     ||     ||     |
 \____| \___/ |_____| \___/ |__|\_|      \_/\_/  |__|__||_____||_____||_____|
                                                                             
*/                                                            
   ////fuck                                                         
package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


//Color Wheel Stuff

public class ControlWheel{//wheel position
    public void wheelPosition(String color, String desiredColor, WPI_TalonSRX motor){
        double ss = 0.5;//slow speed
        double fs = 0.7;//fast speed

          System.out.println(desiredColor);
          SmartDashboard.putString("Desired Color", desiredColor);
          SmartDashboard.putString("Current Color", color);
 b
          //  System.out.println(color);
    
        
        if(color != desiredColor){// left negitave right positive
            if(desiredColor == "G"){///////////////////////////////green///////////////////
                System.out.println("ahh");
                if(color == "B")// blue to green
                    motor.set(-ss);
                else if(color == "Y")// yellow to green
                    motor.set(-fs);
                else if(color == "R")//red to green
                    motor.set(ss);
            } else if(desiredColor == "R"){///////////////////////////red/////////////////////
                 if(color == "G")//green to red
                    motor.set(-ss);
                else if(color == "B")//blue to red
                    motor.set(-fs);
                else if(color == "Y")//yellow to red
                    motor.set(ss);
            } else if(desiredColor == "Y"){//////////////////////////////yellow/////////////////
                if(color == "B")//blue to yellow
                    motor.set(ss);
                else if(color == "R")//red to yellow
                    motor.set(-ss);
                else if(color == "G")// green to yellow
                    motor.set(-fs);
            } else if(desiredColor == "B"){/////////////////////////blue////////////////////////////
                if(color == "G")//green to blue
                    motor.set(-ss);
                else if(color == "Y")//yellow to blue
                    motor.set(ss);
                else if(color == "R")//red to blue
                    motor.set(fs);
            } 

        }
    }


    
}
