package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Intake{
    WPI_TalonSRX talon;


   Intake(WPI_TalonSRX inputTalon){
    talon = inputTalon;
   }
   public void RunIntake(double speed){
       talon.set(speed);
   }
}
	
