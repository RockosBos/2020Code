package frc.robot;

import com.ctre.phoenix.motorcontrol.can.*;


public class MotorController{

    String controllerType;
    public WPI_TalonSRX talon;
    public WPI_VictorSPX victor;

    public void setMotor(double value){
        if(controllerType == "Talon"){
            talon.set(value);
        }
        else if(controllerType == "Victor"){
            victor.set(value);
        }
    }
    
    }


