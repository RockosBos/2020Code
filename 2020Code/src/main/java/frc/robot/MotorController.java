package frc.robot;

import com.ctre.phoenix.motorcontrol.can.*;


public class MotorController{

    String controllerType;
    public WPI_TalonSRX talon;
    public WPI_VictorSPX victor;

    MotorController(final String name, final int canID){
        controllerType = name.toUpperCase();
        if(controllerType == "Talon"){
            talon = new WPI_TalonSRX(canID);
        }
        else if (controllerType == "Victor"){
            victor = new WPI_VictorSPX(canID);

        }
        else{
            System.out.print("Not Valid Motor Controller" + canID);
        }

    }
    public void setMotor(double value){
        if(controllerType == "Talon"){
            talon.set(value);
        }
        else if(controllerType == "Victor"){
            victor.set(value);
        }
    }
    
    }


