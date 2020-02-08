package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


public class Climb{

    public void climber(double speed, boolean up, boolean down, WPI_TalonSRX motor){
        if(up)
            motor.set(speed);
        else if(down)
            motor.set(-speed);
        else
            motor.set(0.0);
    }

    public void climber(double speed, boolean up, boolean down, WPI_VictorSPX motor){
        if(up)
            motor.set(speed);
        else if(down)
            motor.set(-speed);
        else
            motor.set(0.0);
    }

    public void climbSlide(double speed, boolean left, boolean right, WPI_TalonSRX motor){
        if(left)
            motor.set(speed);
        else if(right)
            motor.set(-speed);
        else motor.set(0);
    }

    public void climbSlide(double speed, boolean left, boolean right, WPI_VictorSPX motor){
        if(left)
            motor.set(speed);
        else if(right)
            motor.set(-speed);
        else motor.set(0);
    }
}