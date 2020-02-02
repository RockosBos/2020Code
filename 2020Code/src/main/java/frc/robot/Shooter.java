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

    WPI_TalonSRX shooter;
    WPI_TalonSRX liftRotate;

    WPI_VictorSPX shooterV;
    WPI_VictorSPX liftRotateV;

    Shooter(WPI_TalonSRX shooter, WPI_TalonSRX rotate){
        this.shooter = shooter;
        this.liftRotate = rotate;
    }
    Shooter(WPI_VictorSPX shooter, WPI_VictorSPX rotate){
        this.shooterV = shooter;
        this.liftRotateV = rotate;
    }
    Shooter(WPI_TalonSRX shooter, WPI_VictorSPX rotate){
        this.shooter = shooter;
        this.liftRotateV = rotate;
    }
    Shooter(WPI_VictorSPX shooter, WPI_TalonSRX rotate){
        this.shooterV = shooter;
        this.liftRotate = rotate;
    }

    public void manFire(boolean trigger, double speed){
        if(trigger)
            shooter.set(speed);
        else shooter.set(0);
    }
    

    public void manFire(boolean trigger, double speed, double offset){
        if(trigger)
            shooter.set(speed);
        else shooter.set(offset);
    }
    
    public void manRotate(boolean left, boolean right, double speed){
        if(left)
            liftRotate.set(-speed);
        else if(right)
            liftRotate.set(speed);
        else liftRotate.set(0.0);
    }


    public void manRotate(boolean left, boolean right, double lSpeed, double rSpeed){
        if(left)
            liftRotate.set(-lSpeed);
        else if(right)
            liftRotate(lSpeed);
        else liftRotate.set(0.0);
    }

    //////////////////////Auto-Shooter//////////////////////////////////

    // public void autoFire(boolean trigger, double speed, WPI_TalonSRX motor){
    //     if trigger
    // }
}


