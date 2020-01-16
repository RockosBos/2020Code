package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.Controller;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

 public class Motor{
    WPI_TalonSRX left = new WPI_TalonSRX(0);
    //Controller left = new Controller(0);
     Controller right = new Controller(1);
     MotorController leftDrive1 = new MotorController("Talon", 1);
     MotorController leftDrive2 = new MotorController("Victor", 2);
     MotorController rightDrive1 = new MotorController("Victor", 3);
     MotorController rightDrive2 = new MotorController("Talon", 4);
     MotorController intakeLift = new MotorController("Talon", 5);
     MotorController intakeWheels = new MotorController("Talon", 6);
     MotorController lowerFeed = new MotorController("Talon", 7);
     MotorController upperFeed = new MotorController("Talon", 8);
     MotorController shooters = new MotorController("Talon", 9);
     MotorController controlWheelRotate = new MotorController("Talon", 10);
     MotorController controlWheel = new MotorController("Talon", 11);
     MotorController liftRotate = new MotorController("Talon", 12);
     MotorController lifter = new MotorController("Talon", 13);
    

     SpeedControllerGroup rightDrive = new SpeedControllerGroup(rightDrive1.victor, rightDrive2.talon);
     SpeedControllerGroup leftDrive = new SpeedControllerGroup(leftDrive1.talon, leftDrive2.victor);
     DifferentialDrive diffDrive = new DifferentialDrive(rightDrive, leftDrive);

    // Motor(){
        //diffDrive.arcadeDrive(right.js.getRawAxis(1), right.js.getRawAxis(0));
    //}












}