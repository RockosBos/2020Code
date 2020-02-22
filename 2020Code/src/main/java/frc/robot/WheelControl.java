
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
   
   
   
   //Color Wheel Stuff
   
   public class WheelControl{//wheel position
       
    int step = 0;

    char startingColor;

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

    public void wheelRotation(char color, double speed, WPI_TalonSRX motor ){
        if(step == 0){
            startingColor = color;
            motor.set(1.0);
            step = 1;
        } else if(step == 1){
            if(startingColor == color){
                motor.set(1.0);
                step = 2;
            }
        } else if(step == 2){
            if(startingColor == color){
                motor.set(1.0);
                step = 3;
            }
        } else if(step == 3){
            if(startingColor == color){
                motor.set(1.0);
                step = 4;
            }
        } else if(step == 4){
            if(startingColor == color){
                motor.set(1.0);
                step = 5;
            }
        } else if(step == 5){
            if(startingColor == color){
                motor.set(1.0);
                step = 6;
            }
        } else if(step == 6){
            if(startingColor == color){
                motor.set(.25);
                step = 7;
            }
        } else if(step == 7){
            if(startingColor == color){
                motor.set(0);
                step = 8;
            }
        } 
    }

}
      
       

   
   
