package frc.robot;


import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
//import edu.wpi.first.wpilibj.interfaces.Gyro;

class RobotGyroscope{
    public ADXRS450_Gyro gyro;

    RobotGyroscope(){
        gyro =  new ADXRS450_Gyro(SPI.Port.kMXP);
        gyro.calibrate();
    }

    public void resetGyro(){
        gyro.reset();
    }

}