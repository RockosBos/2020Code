/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.cameraserver.CameraServer;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();


  Spark leftDrive = new Spark(0);
  Spark rightDrive = new Spark(1);

  DifferentialDrive diffDrive = new DifferentialDrive(leftDrive, rightDrive);

  Joystick controller = new Joystick(0);
  
  XboxController xbox = new XboxController(0);
  Gyros Gyroscope = new Gyros(leftDrive, rightDrive);
  DigitalInput lineDetect = new DigitalInput(2);
  double desiredGyroAngle = 0.0;
  LimeLight robotLimeLight = new LimeLight();

  
  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    leftDrive.set(0);
    rightDrive.set(0);
    xbox.setRumble(RumbleType.kLeftRumble, 0);
    xbox.setRumble(RumbleType.kRightRumble, 0);
    SmartDashboard.putNumber("Set To Gyro Angle", 0); 
    CameraServer.getInstance().startAutomaticCapture();
    
    
  }
  @Override
  public void disabledInit() {
    xbox.setRumble(RumbleType.kLeftRumble, 0);
    xbox.setRumble(RumbleType.kRightRumble, 0);
    
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
  }

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

   /* diffDrive.arcadeDrive(xbox.getRawAxis(1), xbox.getRawAxis(0));
    if (xbox.getRawAxis(1) != 0 || xbox.getRawAxis(0) != 0){
      xbox.setRumble(RumbleType.kLeftRumble, 1);
      xbox.setRumble(RumbleType.kRightRumble, 1);
    }
    if (xbox.getRawAxis(1) == 0 || xbox.getRawAxis(0) == 0){
      xbox.setRumble(RumbleType.kLeftRumble, 0);
      xbox.setRumble(RumbleType.kRightRumble, 0);
    }

    
    if (xbox.getXButton()){
        Gyroscope.GyroRotate(-90);
    }
    if (xbox.getAButton()){
        Gyroscope.GyroRotate(180);
    }
    if (xbox.getBButton()){
        Gyroscope.GyroRotate(360);
    }
    if(xbox.getYButton()){
        Gyroscope.gyro.reset();
    }*/
    diffDrive.arcadeDrive(controller.getRawAxis(1), controller.getRawAxis(0));
    SmartDashboard.putNumber("Gyro Value", Gyroscope.gyro.getAngle());
    SmartDashboard.putString("Gyro State", Gyroscope.state);
    SmartDashboard.putBoolean("Line Detector", lineDetect.get());
    robotLimeLight.displayData();
   /*double error = desiredGyroAngle - Gyroscope.gyro.getAngle();
    double p = 125;
    double speed = error / 2 * p;
    double errorThreshold = 2.5;
    SmartDashboard.putNumber("Error:", error);
    desiredGyroAngle = SmartDashboard.getNumber("Set To Gyro Angle", -1);
    SmartDashboard.putNumber("Gyro Angle", Gyroscope.gyro.getAngle());
    if(error > p){
        leftDrive.set(0.5);
        rightDrive.set(0.5);
    }
    else if(error < -p){
        leftDrive.set(-0.5);
        rightDrive.set(-0.5);
    }
    else if(error <  desiredGyroAngle + errorThreshold && error > desiredGyroAngle - errorThreshold){
      leftDrive.set(0);
      rightDrive.set(0);
    }
    else{
        leftDrive.set(speed);
        rightDrive.set(speed);
    }
    */
  } 
  

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    double error = desiredGyroAngle - Gyroscope.gyro.getAngle();
    double p = 100;
    double speed = error / p;
    SmartDashboard.putNumber("Error:", error);
    desiredGyroAngle = SmartDashboard.getNumber("Set To Gyro Angle", -1);

    if(error > 100){
        leftDrive.set(1);
        rightDrive.set(1);
    }
    else if(error < -100){
        leftDrive.set(-1);
        rightDrive.set(-1);
    }
    else{
        leftDrive.set(speed);
        rightDrive.set(speed);
    }

  }
}
