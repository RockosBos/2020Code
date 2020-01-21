/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//Test to Push
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
<<<<<<< HEAD
=======
import frc.robot.ColorSensor; //This is our color sensor class, 
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.*;

//without this the program will not add that other file, we need to do this with all other files.
>>>>>>> cc28847281b2cd414a4c359c36ebccdbb2efbe0e

import frc.robot.Controller;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    ColorSensor colorBoi = new ColorSensor();

<<<<<<< HEAD
    

    
    
=======
    // DifferentialDrive diffDrive = new DifferentialDrive(rightDrive, leftDrive);
    // SpeedControllerGroup rightDrive = new SpeedControllerGroup(rightDrive1.victor, rightDrive2.talon);
    //  SpeedControllerGroup leftDrive = new SpeedControllerGroup(leftDrive1.talon, leftDrive2.victor);

    
    // DifferentialDrive diffDrive;
    Controller right = new Controller(0);
    Controller left = new Controller(1);
    //We do need to add in motor controllers if we want to run the bot.
>>>>>>> cc28847281b2cd414a4c359c36ebccdbb2efbe0e
  
  
    //leftDrive1.talon.setInverted(true);
    

    

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    
    //read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    
    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    //System.out.println(colorBoi.getColor());
    
    
    
    
   
    } 

    /* YELLOW
    Red = 0.314453125
    Green = 0.56127929296875
    Blue = 0.1240234375 
    */ 

    /* Red
    Red: 0.527099609375
    Green: 0.340576171875
    Blue: 0.13232421875
    */
    /* Green
    Red: 0.16259765625
    Green: 0.577392578125
    Blue: 0.260009765625
    */
    /* Blue
    Red: 0.126953125
    Green: 0.4248046875
    Blue: 0.448486328125
    /*

    

  }//Nick likes ducks that eat pickles
  


  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  // diffDrive.arcadeDrive(right.js.getRawAxis(1), right.js.getRawAxis(0));
    //Motor();


  }
  @Override
  public void testPeriodic() {
  }
}
