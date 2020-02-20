package frc.robot;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.controller.*;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Gyros{

    public double speed = 0.5;
    public boolean isFound = true;
    SpeedControllerGroup left;
    SpeedControllerGroup right;
    double lowerVariance;
    double upperVariance;
    double VARIANCE = 6;
    private static final SPI.Port kGyroPort = SPI.Port.kOnboardCS0;
    public ADXRS450_Gyro gyro = new ADXRS450_Gyro(kGyroPort);
    public String state = "";
    SpeedControllerGroup leftMotors = new SpeedControllerGroup(, );
    TalonSRX left1motor;
    TalonSRX right2motor;
    VictorSPX left2motor;
    VictorSPX right1motor;

    public Gyros(TalonSRX left1, VictorSPX left2, VictorSPX right1, TalonSRX right2){
        left1motor = left1;
        right2motor = right2;
        left2motor
        gyro.calibrate();
    }

    public void GyroRotate(double value){
        isFound = false;
        lowerVariance = value - VARIANCE;
        upperVariance = value + VARIANCE;
        if(gyro.getAngle() <= lowerVariance){
            left.set(speed);
            right.set(speed);
            state = "Turn right";
        }
        else if(gyro.getAngle() > upperVariance){
            left.set(-speed);
            right.set(-speed);
            state = "Turn Left";
        }
        else{
            left.set(0);
            right.set(0);
            isFound = true;
            state = "Good";
        }
    }
}