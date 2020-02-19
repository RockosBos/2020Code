package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;



class Autonomous{
    SpeedControllerGroup leftDrive;
    SpeedControllerGroup rightDrive;
    ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kMXP);
    Timer timer = new Timer();
    //double driveValue 

    Autonomous(SpeedControllerGroup leftDrive, SpeedControllerGroup rightDrive){
        
        this.leftDrive = leftDrive;
        this.rightDrive = rightDrive;
        gyro.calibrate();

        
    }


    
    public void setDrive(double leftSpeed, double rightSpeed){   
       // if()
        
        leftDrive.set(leftSpeed);
        rightDrive.set(rightSpeed);
    }

    public void turnDegrees(double degrees){
        
    }
    


}