package frc.robot;

/*
 ______     __  __     ______     ______     ______   ______     ______    
/\  ___\   /\ \_\ \   /\  __ \   /\  __ \   /\__  _\ /\  ___\   /\  == \   
\ \___  \  \ \  __ \  \ \ \/\ \  \ \ \/\ \  \/_/\ \/ \ \  __\   \ \  __<   
 \/\_____\  \ \_\ \_\  \ \_____\  \ \_____\    \ \_\  \ \_____\  \ \_\ \_\ 
  \/_____/   \/_/\/_/   \/_____/   \/_____/     \/_/   \/_____/   \/_/ /_/                                                                            
*/


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.Robot.Constants;



//Everything assosiated with the shooter goes here (Shooter, Feeder, Turret)
public class Shooter{

    Controller left;
    Controller right;
    WPI_TalonSRX shooter;
    WPI_TalonSRX shooterRotate;
    WPI_TalonSRX upperFeed;
    WPI_TalonSRX lowerFeed;

    

    Shooter(Controller left, Controller right, WPI_TalonSRX shooter, WPI_TalonSRX shooterRotate, WPI_TalonSRX upperFeed, WPI_TalonSRX lowerFeed){
        this.right = right;
        this.left = left;
        this.shooter = shooter;
        this.shooterRotate = shooterRotate;
        this.upperFeed = upperFeed;
        this.lowerFeed = lowerFeed;
     
    }


    public void manFire(double speed){
        if(left.Trigger)
            shooter.set(speed);
        else shooter.set(0);
    }
    

    public void manFire(double speed, double offset){
        if(left.Trigger)
            shooter.set(speed);
        else shooter.set(offset);
    }
    
    public void manRotate(double speed){
        if(left.LeftFace)
            shooterRotate.set(-speed);
        else if(left.RightFace)
            shooterRotate.set(speed);
        else shooterRotate.set(0.0);
    }


    //////////////////////Auto-Shooter//////////////////////////////////

    public void autoShoot(){
        
        shooter.set(-1);
        
        if(LimeLight.limelightState == "stop"){
            upperFeed.set(1);
            lowerFeed.set(.80);
        }
        else{
            upperFeed.set(0);
            lowerFeed.set(0);
        }
          
    }

    public void autoAim(){
        switch(LimeLight.limelightState){
            case "fastRight":
                shooterRotate.set(-Constants.SHOOTER_ROTATE_FAST_SPEED);
                break;
            case "fastLeft":
                shooterRotate.set(Constants.SHOOTER_ROTATE_FAST_SPEED);
                break;
            case "slowLeft":
                shooterRotate.set(Constants.CONTROL_WHEEL_ROTATE_SPEED);
                break;
            case "slowRight":
                shooterRotate.set(-Constants.SHOOTER_ROTATE_SLOW_SPEED);
                break;
            default:
                shooterRotate.set(0);
                
        }
    }

    public void autoShootStop(){
            shooter.set(0);
            upperFeed.set(0);
            lowerFeed.set(0);
            shooterRotate.set(0);
    }
}


