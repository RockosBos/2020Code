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
    Shooter(Controller left, Controller right, WPI_TalonSRX shooter, WPI_TalonSRX shooterRotate )){
    
    }
    public void manFire(boolean trigger, double speed, WPI_TalonSRX motor){
        if(trigger)
            motor.set(speed);
        else motor.set(0);
    }
    
    public void manFire(boolean trigger, double speed, WPI_VictorSPX motor){
        if(trigger)
            motor.set(speed);
        else motor.set(0);
    }

    public void manFire(boolean trigger, double speed, double offset, WPI_TalonSRX motor){
        if(trigger)
            motor.set(speed);
        else motor.set(offset);
    }
    
    public void manFire(boolean trigger, double speed, double offset, WPI_VictorSPX motor){
        if(trigger)
            motor.set(speed);
        else motor.set(offset);
    }


    public void manRotate(boolean left, boolean right, double speed, WPI_TalonSRX motor){
        if(left)
            motor.set(-speed);
        else if(right)
            motor.set(speed);
        else motor.set(0.0);
    }

    public void manRotate(boolean left, boolean right, double speed, WPI_VictorSPX motor){
        if(left)
            motor.set(-speed);
        else if(right)
            motor.set(speed);
        else motor.set(0.0);
    }

    public void manRotate(boolean left, boolean right, double lSpeed, double rSpeed, WPI_TalonSRX motor){
        if(left)
            motor.set(-lSpeed);
        else if(right)
            motor.set(lSpeed);
        else motor.set(0.0);
    }

    public void manRotate(boolean left, boolean right, double lSpeed, double rSpeed, WPI_VictorSPX motor){
        if(left)
            motor.set(-rSpeed);
        else if(right)
            motor.set(rSpeed);
        else motor.set(0.0);
    }


    //////////////////////Auto-Shooter//////////////////////////////////

    // public void autoFire(boolean trigger, double speed, WPI_TalonSRX motor){
    //     if trigger
    // }
}


