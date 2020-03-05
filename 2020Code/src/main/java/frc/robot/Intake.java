package frc.robot;

import frc.robot.Robot.Constants;
import frc.robot.Robot.MC;
import frc.robot.Robot.Sensors;
import edu.wpi.first.wpilibj.Timer;

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

    Controller left;
    Controller right;
    Timer intakeTimer = new Timer();
    double setFeedServoHighTime = 2;
    double setFeedServoLowTime = 0.5;
    double currentCycleTime;
    double timerPrevState;

    Intake(Controller left, Controller right){
        this.left = left;
        this.right = right;
        intakeTimer.start();
    }

    public void intakeAll(){
        setCurrentCycleTime();
        if(Sensors.lineSensor.get() == false){
            MC.upperFeed.set(Constants.UPPER_FEED_INTAKE_SPEED);
        }
        MC.lowerFeed.set(Constants.LOWER_FEED_SPEED);
        MC.intakeWheels.set(Constants.INTAKE_WHEELS_SPEED);
        if(currentCycleTime < setFeedServoHighTime){
            
        }
        else if(currentCycleTime < setFeedServoLowTime){

        }
        
    }
    public void stopAllIntake(){
        MC.upperFeed.set(0);
        MC.lowerFeed.set(0);
        MC.intakeWheels.set(0);
    }
    public void outtakeAll(){
        MC.upperFeed.set(-Constants.UPPER_FEED_INTAKE_SPEED);
        MC.lowerFeed.set(-Constants.LOWER_FEED_SPEED);
        MC.intakeWheels.set(-Constants.INTAKE_WHEELS_SPEED);
    }
    public void operateManually(){
        if(left.BottomFace){
            MC.intakeWheels.set(Constants.INTAKE_WHEELS_SPEED);
        } 
        else{
            MC.intakeWheels.set(0);
        }

        if(left.R3){
            MC.intakeLift.set(Constants.INTAKE_LIFT_SPEED);
        }
        else if(left.R6){
            MC.intakeLift.set(-Constants.INTAKE_LIFT_SPEED);
        }
        else{
            MC.intakeLift.set(0);
        }

        if(left.R2){
            MC.lowerFeed.set(-Constants.LOWER_FEED_SPEED);
        }
        else if(left.R5){
            MC.lowerFeed.set(Constants.LOWER_FEED_SPEED);
        }
        else{
            MC.lowerFeed.set(0);
        }
    
        if(left.R1){
            MC.upperFeed.set(-Constants.UPPER_FEED_INTAKE_SPEED);
        }
        else if(left.R4){
            MC.upperFeed.set(Constants.UPPER_FEED_INTAKE_SPEED);
        }
        else{
            MC.upperFeed.set(0);
        }
    }

    public void setCurrentCycleTime(){
        currentCycleTime = intakeTimer.get() - timerPrevState;
    }
}
	
