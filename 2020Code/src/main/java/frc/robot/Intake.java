package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

//Everything associated with robot intake goes here

/*
 _____ _   _ _____ ___   _   __ _____ 
|_   _| \ | |_   _/ _ \ | | / /|  ___|
  | | |  \| | | |/ /_\ \| |/ / | |__  
  | | | . ` | | ||  _  ||    \ |  __|
 _| |_| |\  | | || | | || |\  \| |___ 
 \___/\_| \_/ \_/\_| |_/\_| \_/\____/ 
                                      
                                      

*/



public class Intake{
    public void intakeBall(boolean button, double speed, WPI_TalonSRX motor){
        if(button){
            motor.set(-speed);
        } else motor.set(0);
    }
    public void intakeBall(boolean button, double speed, WPI_VictorSPX motor){
        if(button){
            motor.set(-speed);
        } else motor.set(0);
    }

    public void intakeBall(boolean button, double speed, double offset, WPI_TalonSRX motor){
        if(button){
            motor.set(-speed);
        } else motor.set(-offset);
    }

    public void intakeBall(boolean button, double speed, double offset, WPI_VictorSPX motor){
        if(button){
            motor.set(-speed);
        } else motor.set(-offset);
    }
}
	
