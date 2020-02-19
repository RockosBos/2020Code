package frc.robot;

/*
 ______     __  __     ______     ______     ______   ______     ______    
/\  ___\   /\ \_\ \   /\  __ \   /\  __ \   /\__  _\ /\  ___\   /\  == \   
\ \___  \  \ \  __ \  \ \ \/\ \  \ \ \/\ \  \/_/\ \/ \ \  __\   \ \  __<   
 \/\_____\  \ \_\ \_\  \ \_____\  \ \_____\    \ \_\  \ \_____\  \ \_\ \_\ 
  \/_____/   \/_/\/_/   \/_____/   \/_____/     \/_/   \/_____/   \/_/ /_/                                                                            
*/


//import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.Robot.Constants;
import frc.robot.Robot.MC;





//Everything assosiated with the shooter goes here (Shooter, Feeder, Turret)
public class Shooter{

    Controller left;
    Controller right;

    Shooter(Controller left, Controller right){
        this.left = left;
        this.right = right;
     
    }


    public void manFire(double speed){
        if(left.Trigger)
            MC.shooters.set(speed);
        else MC.shooters.set(0);
    }
    
    
    

    public void manFire(double speed, double offset){
        if(left.Trigger)
            MC.shooters.set(speed);
        else MC.shooters.set(offset);
    }
    
    public void manRotate(double speed){
        if(left.LeftFace)
            MC.shooterRotate.set(-speed);
        else if(left.RightFace)
            MC.shooterRotate.set(speed);
        else MC.shooterRotate.set(0.0);
    }


    //////////////////////Auto-Shooter//////////////////////////////////

    public void autoShoot(){
        
        MC.shooters.set(-1);
        
        if(LimeLight.limelightState == "stop"){
            MC.upperFeed.set(1);
            MC.lowerFeed.set(.80);
        }
        else{
            MC.upperFeed.set(0);
            MC.lowerFeed.set(0);
        }
          
    }

    public void autoAim(){
        switch(LimeLight.limelightState){
            case "fastRight":
                MC.shooterRotate.set(-Constants.SHOOTER_ROTATE_FAST_SPEED);
                break;
            case "fastLeft":
                MC.shooterRotate.set(Constants.SHOOTER_ROTATE_FAST_SPEED);
                break;
            case "slowLeft":
                MC.shooterRotate.set(Constants.CONTROL_WHEEL_ROTATE_SPEED);
                break;
            case "slowRight":
                MC.shooterRotate.set(-Constants.SHOOTER_ROTATE_SLOW_SPEED);
                break;
            default:
                MC.shooterRotate.set(0);
                
        }
    }

    public void autoShootStop(){
            MC.shooters.set(0);
            MC.upperFeed.set(0);
            MC.lowerFeed.set(0);
            MC.shooterRotate.set(0);
    }
}


