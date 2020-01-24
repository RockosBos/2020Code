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
    public void fireMan(boolean trigger, double speed, WPI_TalonSRX motor){
        if(trigger)
            motor.set(speed);
        else
            motor.set(0);
    }
    
    public void fireMan(boolean trigger, double speed, WPI_VictorSPX motor){
        if(trigger)
            motor.set(speed);
        else
            motor.set(0);
    }

    public void fireMan(boolean trigger, double speed, double offset, WPI_TalonSRX motor){
        if(trigger)
            motor.set(speed);
        else
            motor.set(offset);
    }
    
    public void fireMan(boolean trigger, double speed, double offset, WPI_VictorSPX motor){
        if(trigger)
            motor.set(speed);
        else
            motor.set(offset);
    }
}

// public class 
