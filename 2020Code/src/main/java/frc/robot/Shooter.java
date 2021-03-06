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
import frc.robot.Robot.Sensors;
import edu.wpi.first.wpilibj.Timer;





//Everything assosiated with the shooter goes here (Shooter, Feeder, Turret)
public class Shooter{

    Controller left;
    Controller right;
    Timer shootTimer = new Timer();

    double prevTimer;
    static final double SHOOT_DELAY = 0.5;
    boolean linePreviousState = true;

    Shooter(Controller left, Controller right){
        this.left = left;
        this.right = right;
        shootTimer.start();
     
    }


    public void manFire(double speed){
        if(left.Trigger)
            MC.shooters.set(speed);
        else MC.shooters.set(0);
    }
    
    
    public void manRotate(double speed){
        if(left.LeftFace)
            MC.shooterRotate.set(speed);
        else if(left.RightFace)
            MC.shooterRotate.set(-speed);
        else MC.shooterRotate.set(0.0);
    }


    //////////////////////Auto-Shooter//////////////////////////////////

    public void autoShoot(){
        
        MC.shooters.set(-1);
        
        if(LimeLight.limelightState == "stop"){
            if(!Sensors.lineSensor.get() && Sensors.lineSensor.get() != linePreviousState){
                prevTimer = shootTimer.get();
            }
            System.out.println(shootTimer.get() - prevTimer);
            if(shootTimer.get() - prevTimer < SHOOT_DELAY){
                MC.upperFeed.set(0);
                MC.lowerFeed.set(Constants.LOWER_FEED_SPEED);
            }
            else{
                MC.upperFeed.set(Constants.UPPER_FEED_SHOOTER_SPEED);
                MC.lowerFeed.set(Constants.LOWER_FEED_SPEED);
            }
        }
        else{
            MC.upperFeed.set(0);
            MC.lowerFeed.set(0);
        }
        linePreviousState = Sensors.lineSensor.get();
          
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