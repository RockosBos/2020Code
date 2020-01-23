package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//Color Wheel Stuff

public class Wheel{
    public void wheelPosition(String color, String desiredColor, WPI_TalonSRX motor){
        if(color != desiredColor){// left negitave right positive
            if(desiredColor == "green"){
                if(color == "green")
                    motor.set(0.0);
                else if(color == "blue")// blue to green
                    motor.set(-0.5);
                else if(color == "yellow")// yellow to green
                    motor.set(-0.7);
                else if(color == "red")
                    motor.set(.5);
            }
        }
    }
}

