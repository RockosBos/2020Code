
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

        if(color == "R")
            color = "red";
        else if(color == "G")
            color = "red";
        else if(color == "Y")
            color = "yellow";
        else if(color == "B")
            color = "blue";

        
        if(color != desiredColor){// left negitave right positive
            if(desiredColor == "green"){///////////////////////////////green///////////////////
                if(color == "green")//green to green
                    motor.set(0.0);
                else if(color == "blue")// blue to green
                    motor.set(-ss);
                else if(color == "yellow")// yellow to green
                    motor.set(-fs);
                else if(color == "red")//red to green
                    motor.set(ss);
            } else if(desiredColor == "red"){///////////////////////////red/////////////////////
                if(color == "red")//red to red
                    motor.set(0.0);
                else if(color == "green")//green to red
                    motor.set(-ss);
                else if(color == "blue")//blue to red
                    motor.set(-fs);
                else if(color == "yellow")//yellow to red
                    motor.set(ss);
            } else if(desiredColor == "yellow"){//////////////////////////////yellow/////////////////
                if(color == "yellow")//yellow to yellow
                    motor.set(0.0);
                else if(color == "blue")//blue to yellow
                    motor.set(ss);
                else if(color == "red")//red to yellow
                    motor.set(-ss);
                else if(color == "green")// green to yellow
                    motor.set(-fs);
            } else if(desiredColor == "blue"){/////////////////////////blue////////////////////////////
                if(color == "blue")//blue to blue
                    motor.set(0);
                else if(color == "green")//green to blue
                    motor.set(-ss);
                else if(color == "yellow")//yellow to blue
                    motor.set(ss);
                else if(color == "red")//red to blue
                    motor.set(fs);
            } else motor.set(0.0);

        }
    }


    public void wheelPosition(String color, String desiredColor, WPI_VictorSPX motor){
        double ss = 0.5;//slow speed
        double fs = 0.7;//fast speed

        if(color == "R")
            color = "red";
        else if(color == "G")
            color = "green";
        else if(color == "Y")
            color = "yellow";
        else if(color == "B")
            color = "blue";

        
        if(color != desiredColor){// left negitave right positive
            if(desiredColor == "green"){///////////////////////////////green///////////////////
                if(color == "green")//green to green
                    motor.set(0.0);
                else if(color == "blue")// blue to green
                    motor.set(-ss);
                else if(color == "yellow")// yellow to green
                    motor.set(-fs);
                else if(color == "red")//red to green
                    motor.set(ss);
            } else if(desiredColor == "red"){///////////////////////////red/////////////////////
                if(color == "red")//red to red
                    motor.set(0.0);
                else if(color == "green")//green to red
                    motor.set(-ss);
                else if(color == "blue")//blue to red
                    motor.set(-fs);
                else if(color == "yellow")//yellow to red
                    motor.set(ss);
            } else if(desiredColor == "yellow"){//////////////////////////////yellow/////////////////
                if(color == "yellow")//yellow to yellow
                    motor.set(0.0);
                else if(color == "blue")//blue to yellow
                    motor.set(ss);
                else if(color == "red")//red to yellow
                    motor.set(-ss);
                else if(color == "green")// green to yellow
                    motor.set(-fs);
            } else if(desiredColor == "blue"){/////////////////////////blue////////////////////////////
                if(color == "blue")//blue to blue
                    motor.set(0);
                else if(color == "green")//green to blue
                    motor.set(-ss);
                else if(color == "yellow")//yellow to blue
                    motor.set(ss);
                else if(color == "red")//red to blue
                    motor.set(fs);
            } else motor.set(0.0);

        }
    }
}

