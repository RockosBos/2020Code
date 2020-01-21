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

import frc.robot.Controller;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.networktables.NetworkTable; 
import edu.wpi.first.networktables.NetworkTableEntry; 
import edu.wpi.first.networktables.NetworkTableInstance;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    public ColorSensor colorBoi = new ColorSensor();

    WPI_TalonSRX left = new WPI_TalonSRX(0);
    //Controller left = new Controller(0);
     Controller right = new Controller(1);
     WPI_TalonSRX leftDrive1 = new WPI_TalonSRX(1);
     WPI_VictorSPX leftDrive2 = new WPI_VictorSPX(2);
     WPI_VictorSPX rightDrive1 = new WPI_VictorSPX(3);
     WPI_TalonSRX rightDrive2 = new WPI_TalonSRX(4);
     WPI_TalonSRX intakeLift = new WPI_TalonSRX(5);
     WPI_TalonSRX intakeWheels = new WPI_TalonSRX(6);
     WPI_TalonSRX lowerFeed = new WPI_TalonSRX(7);
     WPI_TalonSRX upperFeed = new WPI_TalonSRX(8);
     WPI_TalonSRX shooters = new WPI_TalonSRX(9);
     WPI_TalonSRX controlWheelRotate = new WPI_TalonSRX(10);
     WPI_TalonSRX controlWheel = new WPI_TalonSRX(11);
     WPI_TalonSRX liftRotate = new WPI_TalonSRX(12);
     WPI_TalonSRX lifter = new WPI_TalonSRX(13);

     Intake Robotintake = new Intake(intakeLift);
     
     
    

     SpeedControllerGroup rightDrive = new SpeedControllerGroup(rightDrive1, rightDrive2);
     SpeedControllerGroup leftDrive = new SpeedControllerGroup(leftDrive1, leftDrive2);
     DifferentialDrive diffDrive = new DifferentialDrive(rightDrive, leftDrive);
    
     NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

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
<<<<<<< HEAD
    diffDrive.arcadeDrive(right.js.getRawAxis(1), right.js.getRawAxis(0));
    
    //limelight turn boi
  
=======
  diffDrive.arcadeDrive(right.js.getRawAxis(1), right.js.getRawAxis(0));
  
  if (right.BottomFace){
    
    Robotintake.RunIntake(.5);

  }
  else{
    Robotintake.RunIntake(0);
  }
  /*if(right.Trigger){
      table.getEntry("camMode").setNumber(0);
      table.getEntry("ledMode").setNumber(2);
  }
  else{
      table.getEntry("camMode").setNumber(1);
      table.getEntry("ledMode").setNumber(0);
  }*/
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
>>>>>>> f6f2a911f991c09dbe6c8da08224fad9e2c83010
   
    


  }
  @Override
  public void testPeriodic() {
  }
}
