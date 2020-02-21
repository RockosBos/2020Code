package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot.Constants;
import frc.robot.Robot.MC;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;



class Autonomous{
    SpeedControllerGroup leftDrive;
    SpeedControllerGroup rightDrive;
    ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kMXP);
    Timer timer = new Timer();
    //double driveValue 
    Gyros gyros = new Gyros(MC.rightDrive, MC.leftDrive);
    double gyroAngle;
    double straightLowerVariance;
    double straightUpperVarience;
    
    
    

    Autonomous(SpeedControllerGroup leftDrive, SpeedControllerGroup rightDrive){
        
        this.leftDrive = leftDrive;
        this.rightDrive = rightDrive;
        gyro.calibrate();

        
    }


    
    public void setDrive(double leftSpeed, double rightSpeed){   
       // if()
        
        leftDrive.set(leftSpeed);
        rightDrive.set(-rightSpeed);
    }
    public void driveForward(double speed, double variance){
        gyroAngle = gyros.gyro.getAngle();
        straightUpperVarience = gyroAngle + variance;
        straightLowerVariance = gyroAngle - variance;
        
        if(gyros.gyro.getAngle() < straightLowerVariance){
            setDrive(speed + .5, speed);
        }
        else if(gyros.gyro.getAngle() > straightLowerVariance){
            setDrive(speed, speed + .5);
        }
        else{
            setDrive(speed, speed);
        }

    }

    public void turnDegrees(double degrees){
        gyros.GyroRotate(degrees);
        
    }

    public void turnOffShootAndFeed(){
        MC.intakeWheels.set(0);
        MC.upperFeed.set(0);
        MC.lowerFeed.set(0);
    }

    public void shootAndFeed(){
        MC.shooters.set(Constants.SHOOTER_SPEED);
        MC.upperFeed.set(Constants.UPPER_FEED_INTAKE_SPEED);
        MC.lowerFeed.set(Constants.LOWER_FEED_SPEED);
    }
    


}