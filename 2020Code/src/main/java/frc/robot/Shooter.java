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


//Everything assosiated with the shooter goes here (Shooter, Feeder, Turret)
public class Shooter{

    Controller left;
    Controller right;
    WPI_TalonSRX shooter;
    WPI_TalonSRX shooterRotate;
    

    Shooter(Controller left, Controller right, WPI_TalonSRX shooter, WPI_TalonSRX shooterRotate){
     this.right = right;
     this.left = left;
     this.shooter = shooter;
     this.shooterRotate = shooterRotate;
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
        if(!triggerPrev){
            timer.start();
        }
        shooter.set(-1);
        ledStrip.changeLEDState("SolidYellow");
        switch(LimeLight.limelightState){
            case "fastRight":
                shooterRotate.set(-Constants.SHOOTER_ROTATE_FAST_SPEED);
                break;
            case "fastLeft":
                shooterRotate.set(Constants.SHOOTER_ROTATE_FAST_SPEED);
                break;
            case "slowLeft":
                shooterRotate.set(Constants.SHOOTER_ROTATE_SLOW_SPEED);
                break;
            case "slowRight":
                shooterRotate.set(-Constants.SHOOTER_ROTATE_SLOW_SPEED);
                break;
            default:
                shooterRotate.set(0);
                ledStrip.changeLEDState("SolidGreen");
                
        }
        if(LimeLight.limelightState == "stop" && timer.get() > 2.5){
            upperFeed.set(1);
            lowerFeed.set(.80);
        }
        else{
            upperFeed.set(0);
            lowerFeed.set(0);
        }
    
        triggerPrev = true;        
    }

    public void autoShootStop(){
        shooters.set(0);
            upperFeed.set(0);
            lowerFeed.set(0);
            shooterRotate.set(0);
            timer.stop();
            timer.reset();
            triggerPrev = false;
    }
}


