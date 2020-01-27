
/* 
    __   ___   _       ___   ____       __    __  __ __    ___    ___  _     
   /  ] /   \ | |     /   \ |    \     |  |__|  ||  |  |  /  _]  /  _]| |    
  /  / |     || |    |     ||  D  )    |  |  |  ||  |  | /  [_  /  [_ | |    
 /  /  |  O  || |___ |  O  ||    /     |  |  |  ||  _  ||    _]|    _]| |___ 
/   \_ |     ||     ||     ||    \     |  `  '  ||  |  ||   [_ |   [_ |     |
\     ||     ||     ||     ||  .  \     \      / |  |  ||     ||     ||     |
 \____| \___/ |_____| \___/ |__|\_|      \_/\_/  |__|__||_____||_____||_____|
                                                                             
*/                                                            
                                                            
package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


//Color Wheel Stuff

public class ControlWheel{//wheel position
    public void wheelPosition(String color, String desiredColor, WPI_TalonSRX motor){
        double ss = 0.5;//slow speed
        double fs = 0.7;//fast speed

       

        
        if(color != desiredColor){// left negitave right positive
            if(desiredColor == "G"){///////////////////////////////green///////////////////
                if(color == "G")//green to green
                    motor.set(0.0);
                else if(color == "B")// blue to green
                    motor.set(-ss);
                else if(color == "Y")// yellow to green
                    motor.set(-fs);
                else if(color == "R")//red to green
                    motor.set(ss);
            } else if(desiredColor == "R"){///////////////////////////red/////////////////////
                if(color == "R")//red to red
                    motor.set(0.0);
                else if(color == "G")//green to red
                    motor.set(-ss);
                else if(color == "B")//blue to red
                    motor.set(-fs);
                else if(color == "Y")//yellow to red
                    motor.set(ss);
            } else if(desiredColor == "Y"){//////////////////////////////yellow/////////////////
                if(color == "Y")//yellow to yellow
                    motor.set(0.0);
                else if(color == "B")//blue to yellow
                    motor.set(ss);
                else if(color == "R")//red to yellow
                    motor.set(-ss);
                else if(color == "G")// green to yellow
                    motor.set(-fs);
            } else if(desiredColor == "B"){/////////////////////////blue////////////////////////////
                if(color == "B")//blue to blue
                    motor.set(0);
                else if(color == "G")//green to blue
                    motor.set(-ss);
                else if(color == "Y")//yellow to blue
                    motor.set(ss);
                else if(color == "R")//red to blue
                    motor.set(fs);
            } else motor.set(0.0);

        }
    }


    public void wheelPosition(String color, String desiredColor, WPI_VictorSPX motor){
        double ss = 0.5;//slow speed
        double fs = 0.7;//fast speed

        if(color == "R")
            color = "R";
        else if(color == "G")
            color = "G";
        else if(color == "Y")
            color = "Y";
        else if(color == "B")
            color = "B";

        
        if(color != desiredColor){// left negitave right positive
            if(desiredColor == "G"){///////////////////////////////green///////////////////
                if(color == "G")//green to green
                    motor.set(0.0);
                else if(color == "B")// blue to green
                    motor.set(-ss);
                else if(color == "Y")// yellow to green
                    motor.set(-fs);
                else if(color == "R")//red to green
                    motor.set(ss);
            } else if(desiredColor == "R"){///////////////////////////red/////////////////////
                if(color == "R")//red to red
                    motor.set(0.0);
                else if(color == "G")//green to red
                    motor.set(-ss);
                else if(color == "B")//blue to red
                    motor.set(-fs);
                else if(color == "Y")//yellow to red
                    motor.set(ss);
            } else if(desiredColor == "Y"){//////////////////////////////yellow/////////////////
                if(color == "Y")//yellow to yellow
                    motor.set(0.0);
                else if(color == "B")//blue to yellow
                    motor.set(ss);
                else if(color == "R")//red to yellow
                    motor.set(-ss);
                else if(color == "G")// green to yellow
                    motor.set(-fs);
            } else if(desiredColor == "B"){/////////////////////////blue////////////////////////////
                if(color == "B")//blue to blue
                    motor.set(0);
                else if(color == "G")//green to blue
                    motor.set(-ss);
                else if(color == "Y")//yellow to blue
                    motor.set(ss);
                else if(color == "R")//red to blue
                    motor.set(fs);
            } else motor.set(0.0);

        }
    }
}

