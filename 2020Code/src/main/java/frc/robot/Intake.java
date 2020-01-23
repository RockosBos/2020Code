package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//Everything associated with robot intake goes here


public class Intake{
    WPI_TalonSRX talon;


   Intake(WPI_TalonSRX inputTalon){
    talon = inputTalon;
   }
   public void RunIntake(double speed){
       talon.set(speed);
   }
}
	
