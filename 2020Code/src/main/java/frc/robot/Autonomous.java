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
    double lowestVar;
    double lowVar;
    double uppestVar;
    double upVar;
    int straightStep = 0;
    final double STRAIGHTSPEED = .25;
    final double CLOSEVARIANCE = .025;
    final double FARVARAINCE = .05;
    
    Autonomous(SpeedControllerGroup leftDrive, SpeedControllerGroup rightDrive){
        
        this.leftDrive = leftDrive;
        this.rightDrive = rightDrive;
        gyro.calibrate();

        
    }


    
    public void setDrive(double leftSpeed, double rightSpeed){   
       
        
        leftDrive.set(-leftSpeed);
        rightDrive.set(rightSpeed);
    }
    public void driveForward(){
        switch(straightStep){
            case 0:
                gyroAngle = gyro.getAngle();
                uppestVar = gyroAngle + 10;
                upVar = gyroAngle + 5;
                lowestVar = gyroAngle - 10;
                lowVar = gyroAngle - 5;
                straightStep++;
            break;
            case 1:
                if(gyro.getAngle() > uppestVar){
                    setDrive(STRAIGHTSPEED, STRAIGHTSPEED + FARVARAINCE);
                }
                else if(gyro.getAngle() > upVar){
                    setDrive(STRAIGHTSPEED, STRAIGHTSPEED + CLOSEVARIANCE);
                }
                else if(gyro.getAngle() < lowestVar){
                    setDrive(STRAIGHTSPEED + FARVARAINCE, STRAIGHTSPEED);
                }
                else if(gyro.getAngle() < lowVar){
                    setDrive(STRAIGHTSPEED + CLOSEVARIANCE, STRAIGHTSPEED);
                }
                else{
                    setDrive(STRAIGHTSPEED, STRAIGHTSPEED);
                }
            break;
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

    public void stopDrive(){
        setDrive(0, 0);
    }
    


}