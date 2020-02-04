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
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.DigitalInput;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {


  //Motor Controllers
    WPI_TalonSRX leftDrive1 = new WPI_TalonSRX(1);
    WPI_VictorSPX rightDrive1 = new WPI_VictorSPX(2);
    WPI_VictorSPX leftDrive2 = new WPI_VictorSPX(3);
    WPI_TalonSRX rightDrive2 = new WPI_TalonSRX(4);
    WPI_TalonSRX intakeLift = new WPI_TalonSRX(5);
    WPI_TalonSRX intakeWheels = new WPI_TalonSRX(6);
    WPI_TalonSRX upperFeed = new WPI_TalonSRX(7);
    WPI_TalonSRX lowerFeed = new WPI_TalonSRX(8);
    WPI_TalonSRX shooters = new WPI_TalonSRX(9);
    WPI_TalonSRX shooterRotate = new WPI_TalonSRX(10);
    WPI_TalonSRX controlWheelRotate = new WPI_TalonSRX(11);
    WPI_TalonSRX controlWheelWheel = new WPI_TalonSRX(12);
    WPI_TalonSRX liftRotate = new WPI_TalonSRX(13);
    WPI_TalonSRX lifter = new WPI_TalonSRX(14);
    Shooter robotShooter = new Shooter(shooters, shooterRotate);
    Intake robotIntake = new Intake();
    ToggleLogic servoToggle = new ToggleLogic();  
    //Joysticks

    Controller left = new Controller(0);
    Controller right = new Controller(1);

    //Custom class mechanisms
    TheColorSensor colorBoi = new TheColorSensor();
    LimeLight robotLimeLight = new LimeLight();
    WheelControl robotControlWheel = new WheelControl();
    LED ledStrip = new LED(5, 5);
    String fieldColor = DriverStation.getInstance().getGameSpecificMessage();
    DigitalInput feedSensor = new DigitalInput(0);
    

    //Drive Initialization
     SpeedControllerGroup rightDrive = new SpeedControllerGroup(rightDrive1, rightDrive2);
     SpeedControllerGroup leftDrive = new SpeedControllerGroup(leftDrive1, leftDrive2);
     DifferentialDrive diffDrive = new DifferentialDrive(rightDrive, leftDrive);
     Servo leftServo = new Servo(8);
     Servo rightServo = new Servo(9);

     class ToggleLogic{
        boolean currentState = false;
        boolean prevState = false;
        boolean value = false;
    }
    

    private static final String kDefaultAuto = "Default";
    private static final String kCustomAuto = "My Auto";
    private String m_autoSelected;
    private final SendableChooser<String> m_chooser = new SendableChooser<>();

    private boolean override = false;
  

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    rightServo.setAngle(0);
    leftServo.setAngle(0);
   // ledStrip.ledRGB(255, 0, 0);
    SmartDashboard.putNumber("Servo 8 Value", leftServo.getAngle());
    SmartDashboard.putNumber("Servo 9 Value", rightServo.getAngle());  }

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

    @Override
    public void disabledPeriodic(){
        ledStrip.rainbow();
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

    override = SmartDashboard.getBoolean("Override", false);
    ledStrip.pulse(0);
    right.updateValues(); //updates Joystick Class variables
    left.updateValues();
  /*-----------------------------------------------------
      Drive Logic
  ------------------------------------------------------*/
  diffDrive.arcadeDrive(right.js.getRawAxis(1), right.js.getRawAxis(0));
  
  /*----------------------------------------------------
      Intake Logic
  ----------------------------------------------------*/
  if (right.BottomFace){
    
  
  }
  else{
    
  }
  /*----------------------------------------------------
    Limelight Logic
  ------------------------------------------------------*/
      //Limelight Update
      
      robotLimeLight.displayData();
  
  if(robotLimeLight.getX() < -2){
      robotLimeLight.limelightState = "TooFarLeft";
  } else if(robotLimeLight.getY() > 2){
      robotLimeLight.limelightState = "TooFarRight";
  } else{
      robotLimeLight.limelightState = "readyToShoot";
  } 

  //color wheel start
  // if(right.R6)
  //   robotControlWheel.wheelPosition(colorBoi.getColor(), fieldColor.charAt(0), controlWheelWheel);

  //lime light on 
  if(right.Trigger){
      robotLimeLight.setMode("ledMode", 0);
      robotLimeLight.setMode("camMode", 0);
  }
   else{
      robotLimeLight.setMode("ledMode", 1);
      robotLimeLight.setMode("camMode", 1);
   }


  //robot fires the ball manually
  robotShooter.manFire(left.Trigger, 1.0);

  //robot will spin the shooter manually
  robotShooter.manRotate(right.LeftFace, right.RightFace, .5);

  //robot pulls in the balls
  robotIntake.intakeBall(left.BottomFace, -1, intakeWheels);

  if(left.R2){
    lowerFeed.set(-0.2);
  }
  else if(left.R5){
    lowerFeed.set(0.2);
  }
  else{
   lowerFeed.set(0);
  }

  if(left.R1){
    upperFeed.set(0.23);
  }
  else if(left.R4){
    upperFeed.set(-0.23);
  }
  else{
   upperFeed.set(0);
  }
   
  

  //robotShooter.manRotate(left.R1, left.R2, 0.2, shooterRotate);
  
  if(left.R3)
   intakeLift.set(0.5);
  else if(left.R6)
   intakeLift.set(-0.5);
  else
   intakeLift.set(0);

   SmartDashboard.putBoolean("Left_R2", left.R2);
   SmartDashboard.putBoolean("Left_R3", left.R3);
   SmartDashboard.putBoolean("Left_R5", left.R5);
   SmartDashboard.putBoolean("Left_R6", left.R6);
  
  SmartDashboard.putBoolean("Trigger", right.Trigger);

  // //intake
  // if(left.BottomFace){
  //   intakeWheels.set(1.0);
  //   lowerFeed.set(0.2);
  //   if(feedSensor.get())
  //     upperFeed.set(0.23);
  //   else upperFeed.set(0);

  // } else{
  //   intakeWheels.set(0.0);
  //   lowerFeed.set(0.0);
  //   upperFeed.set(0.0);
  // }
    

  //servoToggle.currentState = right.R3;
  if(right.toggleButton(servoToggle)){
  leftServo.setAngle(90);
  rightServo.setAngle(90);
  ledStrip.solid(120);
  System.out.println("on");

   }
  else{
    leftServo.setAngle(180);
    rightServo.setAngle(180);
    ledStrip.solid(60);
    System.out.println("off");
    
  }
  leftServo.setAngle(SmartDashboard.getNumber("Servo 8 Value", 0));
  rightServo.setAngle(SmartDashboard.getNumber("Servo 9 Value", 0));
   
  } 

  @Override
  public void testPeriodic() {


      
      
  }
}
