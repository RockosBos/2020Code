package frc.robot;

import frc.robot.Robot.Constants;
import frc.robot.Robot.MC;
import frc.robot.Robot.Sensors;

//Everything associated with robot intake goes here

/*
 _____ _   _ _____ ___   _   __ _____ 
|_   _| \ | |_   _/ _ \ | | / /|  ___|
  | | |  \| | | |/ /_\ \| |/ / | |__  
  | | | . ` | | ||  _  ||    \ |  __| 
 _| |_| |\  | | || | | || |\  \| |___ 
 \___/\_| \_/ \_/\_| |_/\_| \_/\____/ 
                                      
                                      

*/



public class Intake{

    public void intakeAll(){
        if(Sensors.lineSensor.get() == false){
            MC.upperFeed.set(Constants.UPPER_FEED_INTAKE_SPEED);
        }
        MC.lowerFeed.set(Constants.LOWER_FEED_SPEED);
        MC.intakeWheels.set(Constants.INTAKE_WHEELS_SPEED);
    }
    public void stopAllIntake(){

    }
    public void outtakeAll(){

    }
    





    /*public void intakeBall(boolean button, double speed, WPI_TalonSRX motor){
        if(button){
            motor.set(-speed);
        } else motor.set(0);
    }
    public void intakeBall(boolean button, double speed, WPI_VictorSPX motor){
        if(button){
            motor.set(-speed);
        } else motor.set(0);
    }

    public void intakeBall(boolean button, double speed, double offset, WPI_TalonSRX motor){
        if(button){
            motor.set(-speed);
            System.out.println("ahhhh");
        } else motor.set(-offset); System.out.println("-ahhhh");
    }

    public void intakeBall(boolean button, double speed, double offset, WPI_VictorSPX motor){
        if(button){
            motor.set(-speed);
        } else motor.set(-offset);
    }*/
}
	
