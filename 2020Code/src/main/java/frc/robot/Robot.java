/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Nick has Turbo Gay   */
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
//import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.cameraserver.CameraServer;



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
  static final class MC{
    static final WPI_TalonSRX leftDrive1 = new WPI_TalonSRX(1);
    static final WPI_VictorSPX rightDrive1 = new WPI_VictorSPX(2);
    static final WPI_VictorSPX leftDrive2 = new WPI_VictorSPX(3);
    static final WPI_TalonSRX rightDrive2 = new WPI_TalonSRX(4);
    static final WPI_TalonSRX intakeLift = new WPI_TalonSRX(5);
    static final WPI_TalonSRX intakeWheels = new WPI_TalonSRX(6);
    static final WPI_TalonSRX upperFeed = new WPI_TalonSRX(7);
    static final WPI_TalonSRX lowerFeed = new WPI_TalonSRX(8);
    static final WPI_TalonSRX shooters = new WPI_TalonSRX(9);
    static final WPI_TalonSRX shooterRotate = new WPI_TalonSRX(10);
    static final WPI_TalonSRX controlWheelRotate = new WPI_TalonSRX(11);
    static final WPI_TalonSRX controlWheelWheel = new WPI_TalonSRX(12);
    static final WPI_TalonSRX liftRotate = new WPI_TalonSRX(13);
    static final WPI_VictorSPX lifter = new WPI_VictorSPX(14);
    static final DigitalInput lineSensor = new DigitalInput(0);
}
    DigitalInput lineSensor = new DigitalInput(0);

    //PIDController rotatePID = new PIDController(.1, 0, 0);

    //Joysticks

    Controller left = new Controller(0);
    Controller right = new Controller(1);

    //Custom class mechanisms
    TheColorSensor colorBoi = new TheColorSensor();
    LimeLight robotLimeLight = new LimeLight();
    WheelControl robotControlWheel = new WheelControl();
    LED ledStrip = new LED(0, 48);
    String fieldColor = DriverStation.getInstance().getGameSpecificMessage();
    Climb robotClimb = new Climb();
    Shooter robotShooter = new Shooter(left, right);
    Intake robotIntake = new Intake();

    //RobotGyroscope gyro = new RobotGyroscope();
    
    boolean leftS;
    boolean rightS;
    boolean isOverrideOn = false;
    boolean triggerPrev = false;

    int shotNum = 0;

    boolean firing;

    Timer timer = new Timer();
    Timer testTimer = new Timer();

    double last = 0;
    

    //Drive Initialization
     SpeedControllerGroup rightDrive = new SpeedControllerGroup(MC.rightDrive1, MC.rightDrive2);
     SpeedControllerGroup leftDrive = new SpeedControllerGroup(MC.leftDrive1, MC.leftDrive2);
     DifferentialDrive diffDrive = new DifferentialDrive(rightDrive, leftDrive);
     Servo leftServo = new Servo(8);
     Servo rightServo = new Servo(9);
     
     Autonomous autonomous = new Autonomous(leftDrive, rightDrive);

     class ToggleLogic{
        boolean currentState = false;
        boolean prevState = false;
        boolean value = false;
     }
     class Constants{
         static final double INTAKE_LIFT_SPEED = 1.0;
         static final double INTAKE_WHEELS_SPEED = 1.0;
         static final double UPPER_FEED_INTAKE_SPEED = 0.7;
         static final double UPPER_FEED_SHOOTER_SPEED = 0.8;
         static final double LOWER_FEED_SPEED = -1.0;
         static final double SHOOTER_ROTATE_SLOW_SPEED = 0.1;
         static final double SHOOTER_ROTATE_FAST_SPEED = 0.2;
         static final double SHOOTER_SPEED = 1;
         static final double CONTROL_WHEEL_ROTATE_SPEED = 0.1;
         static final double CONTROL_WHEEL_WHEEL_SPEED = 0.1;
         static final double LIFT_ROTATE_SPEED = 0.1;
         static final double LIFTER_SPEED = 0.1;
         static final int LEFT_SERVO_HIGH_GEAR = 50;
         static final int RIGHT_SERVO_HIGH_GEAR = 155;
         static final int LEFT_SERVO_LOW_GEAR = 120;
         static final int RIGHT_SERVO_LOW_GEAR = 85;
     }

     ToggleLogic servoToggle = new ToggleLogic();
    
     //Autonomous Variables
    private static final String initiationLineMove = "initiationLineMove";
    private static final String auto1 = "Auton 1";
    private static final String auto2 = "Auton 2";
    private static final String auto3 = "Auton 3";
    private static final String auto4 = "Auton 4";
    private static final String auto5 = "Auton 5";
    private Timer autonTimer = new Timer();
    private Timer autonDelayTimer = new Timer();
    private int autonomousStep;
    private double delay;


  private String m_autoSelected;
  private final SendableChooser<String> auto_chooser = new SendableChooser<>();
  private final SendableChooser<Boolean> override_chooser = new SendableChooser<>();

  boolean stateBoi;

  //Test only
  double desiredGyroAngle = 0;
  


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
        auto_chooser.setDefaultOption("Move from Initiation line", initiationLineMove);
        auto_chooser.addOption("Auto 1", auto1);
        auto_chooser.addOption("Auto 2", auto2);
        auto_chooser.addOption("Auto 3", auto3);
        auto_chooser.addOption("Auto 4", auto4);
        auto_chooser.addOption("Auto 5", auto5);
        override_chooser.setDefaultOption("Override Off", false);
        override_chooser.addOption("Override On", true);
        SmartDashboard.putData("Override", override_chooser);
        SmartDashboard.putData("Auto choices", auto_chooser);
        leftServo.setAngle(Constants.LEFT_SERVO_HIGH_GEAR);
        rightServo.setAngle(Constants.RIGHT_SERVO_HIGH_GEAR);
        SmartDashboard.putString("Version", "1.0.3");
        SmartDashboard.putNumber("Set to Gyro Angle", desiredGyroAngle);
        SmartDashboard.putNumber("Autonomous Delay", 0);
        //rotatePID.setSetpoint(0);
        testTimer.start();
        CameraServer.getInstance().startAutomaticCapture();
        
        
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
      //empty
      
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
        m_autoSelected = auto_chooser.getSelected();
        // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
        System.out.println("Auto selected: " + m_autoSelected);

        delay = SmartDashboard.getNumber("Autonomous Delay", 0);
        autonDelayTimer.start();
        autonomousStep = 0;
    }


  /**
   * This function is called periodically during autonomous.
   */
    @Override
    public void autonomousPeriodic() {
        double slowDrive = .25;
        if(autonDelayTimer.get() > delay){
            autonDelayTimer.stop();
            switch (m_autoSelected) {
            //list of diffrent scenerios//
            case auto1:
                    //Auto 1 Logic
                switch(autonomousStep){
                case 0:
                    autonTimer.start();
                    autonomousStep = 1;
                    break;
                case 1:
                    if(autonTimer.get() < 2.5){
                        MC.shooters.set(Constants.SHOOTER_SPEED);
                    }
                    else {
                        boolean lineSensorPrevState = lineSensor.get();
                        if(shotNum < 3){
                            robotShooter.autoAim();
                            robotShooter.autoShoot();
                            robotLimeLight.setMode("ledMode", 0);
                            robotLimeLight.setMode("camMode", 0);
                        }
                        else{
                            robotShooter.autoShootStop();
                            robotLimeLight.setMode("ledMode", 1);
                            robotLimeLight.setMode("camMode", 1);
                            autonomousStep = 2;
                        }
                        if(lineSensor.get() && lineSensor.get() != lineSensorPrevState){
                            shotNum++;
                        }
                       
                    }
                    
                case 2:
                    if(autonTimer.get() < 5){
                        autonomous.setDrive(slowDrive, slowDrive);
                    } else{
                        autonomous.setDrive(0,0);
                        ledStrip.changeLEDState("SolidWhite");
                        ledStrip.changeLEDState("SolidRed");
                    }
                    
                    
                }
                break;
            case auto2:
                    //Auto 2 Logic
                break;
            case auto3:
                    //Auto 3 Logic
                break;
            case auto4:
                    //Auto 4 Logic
                break;
            case auto5:
                    //Auto 5 Logic
                break;
            case initiationLineMove:
                default:
                if(autonomousStep == 0){
                    autonTimer.start();
                    autonomous.setDrive(.25, .25);
                    if(autonTimer.get() > 5){
                        autonomousStep = 1;
                    }
                }
                else if(autonomousStep == 1){
                    autonomous.setDrive(0, 0);
                    autonTimer.stop();
                }
                else{
                    autonomous.setDrive(0, 0);
                    autonTimer.stop();
                }

                // Put default auto code here //
                break;
            }
        }
    }

  /**
   * This function is called periodically during operator control.
   */
  @Override
    public void teleopPeriodic() { 
        ledStrip.changeLEDState("SolidWhite");
        
        right.updateValues(); //This updates Controller Values DO NOT REMOVE!!!
        left.updateValues();
        isOverrideOn = override_chooser.getSelected();
    /*-----------------------------------------------------
        Drive Logic
    ------------------------------------------------------*/
    diffDrive.arcadeDrive(-right.js.getRawAxis(1), -right.js.getRawAxis(0));
  
    /*----------------------------------------------------
        Intake Logic
    ----------------------------------------------------*/
    if(!isOverrideOn){ //Auto Intake Logic
        if(left.BottomFace){
            //System.out.println("bottom face pressed");
            if(lineSensor.get()){
                MC.upperFeed.set(Constants.UPPER_FEED_INTAKE_SPEED);
            }
            else{  
                MC.upperFeed.set(0);
            }
            MC.lowerFeed.set(Constants.LOWER_FEED_SPEED);
            MC.intakeWheels.set(Constants.INTAKE_WHEELS_SPEED);
        }
        else{
            MC.lowerFeed.set(0);
            MC.upperFeed.set(0);
            MC.intakeWheels.set(0);
        }
        if(left.R3){
            MC.intakeLift.set(Constants.INTAKE_LIFT_SPEED);
        }
        else if(left.R6){
            MC.intakeLift.set(-Constants.INTAKE_LIFT_SPEED);
        }
        else{
            MC.intakeLift.set(0);
        }

        
    }
    else{ //Manual Intake Logic
        

        //  System.out.println(rotatePID.calculate(robotLimeLight.getX()));
        
        if(left.BottomFace){
            MC.intakeWheels.set(Constants.INTAKE_WHEELS_SPEED);
        } 
        else{
            MC.intakeWheels.set(0);
        }

        if(left.R3){
            MC.intakeLift.set(Constants.INTAKE_LIFT_SPEED);
        }
        else if(left.R6){
            MC.intakeLift.set(-Constants.INTAKE_LIFT_SPEED);
        }
        else{
            MC.intakeLift.set(0);
        }

        if(left.R2){
            MC.lowerFeed.set(-.8);
        }
        else if(left.R5){
            MC.lowerFeed.set(.8);
        }
        else{
            MC.lowerFeed.set(0);
        }
    
        if(left.R1){
            MC.upperFeed.set(-.3);
        }
        else if(left.R4){
            MC.upperFeed.set(.3);
        }
        else{
            MC.upperFeed.set(0);
        }
    }


    //////servo logic
    servoToggle.currentState = right.R3;
    if(right.toggleButton(servoToggle)){

        leftServo.setAngle(Constants.LEFT_SERVO_LOW_GEAR);  //Low Gear 
        rightServo.setAngle(Constants.RIGHT_SERVO_LOW_GEAR);  //Low Gear
        ledStrip.changeLEDState("SolidBlue");

    }
    else{
        leftServo.setAngle(Constants.LEFT_SERVO_HIGH_GEAR);   //high gear
        rightServo.setAngle(Constants.RIGHT_SERVO_HIGH_GEAR); //high gear
    }

    /*----------------------------------------------------
        Limelight Logic
    ------------------------------------------------------*/
    //Limelight Update
      
    robotLimeLight.displayData();
    robotLimeLight.getState();
    if(left.Trigger){
        robotLimeLight.setMode("ledMode", 0);
        robotLimeLight.setMode("camMode", 0);            
    }
    else{
        robotLimeLight.setMode("ledMode", 1);
        robotLimeLight.setMode("camMode", 1);
    }

    //color wheel start
    if(!isOverrideOn){ //Auto Color Wheel Logic
        if(right.BottomFace){
            //robotControlWheel.wheelPosition(colorBoi.getColor(), fieldColor.charAt(0), 0.5, 0.7, controlWheelWheel);
        }
    }
    else{   //Manual Color Wheel Logic
        
    }

    
    robotShooter.manRotate(Constants.SHOOTER_ROTATE_FAST_SPEED);
    
    ////climb logic
  
    //trigger logic
    if(!left.BottomFace){
        if(!isOverrideOn){
            if(left.Trigger){
                if(!triggerPrev){
                    timer.start();
                }
                ///System.out.println("trigger");
                MC.shooters.set(Constants.SHOOTER_SPEED);
                ledStrip.changeLEDState("SolidYellow");
                switch(LimeLight.limelightState){
                    case "fastRight":
                        MC.shooterRotate.set(-Constants.SHOOTER_ROTATE_FAST_SPEED);
                        break;
                    case "fastLeft":
                        MC.shooterRotate.set(Constants.SHOOTER_ROTATE_FAST_SPEED);
                        break;
                    case "slowLeft":
                        MC.shooterRotate.set(Constants.SHOOTER_ROTATE_SLOW_SPEED);
                        break;
                    case "slowRight":
                        MC.shooterRotate.set(-Constants.SHOOTER_ROTATE_SLOW_SPEED);
                        break;
                    default:
                        MC.shooterRotate.set(0);
                        ledStrip.changeLEDState("SolidGreen");
                        
                }
                if(LimeLight.limelightState == "stop" && timer.get() > 2.5){
                    MC.upperFeed.set(1);
                    MC.lowerFeed.set(.80);
                }
                else{
                    MC.upperFeed.set(0);
                    MC.lowerFeed.set(0);
                }
            
                triggerPrev = true;
            }
            else{
                MC.shooters.set(0);
                MC.upperFeed.set(0);
                MC.lowerFeed.set(0);
                MC.shooterRotate.set(0);
                timer.stop();
                timer.reset();
                triggerPrev = false;
            }
        }   
        else{
            robotShooter.manFire(Constants.SHOOTER_SPEED);
            robotShooter.manRotate(Constants.SHOOTER_ROTATE_FAST_SPEED);
        }
    }
   
    //Climb Logic//

    if(right.L6){
        MC.lifter.set(Constants.LIFTER_SPEED);
    }
    else{
        MC.lifter.set(0);
    }

    //Climber side to side motor code//

    if(right.L5){
        MC.liftRotate.set(.2);
    }
    else if(right.L4){
        MC.liftRotate.set(-.2);
    }
    else{
        MC.liftRotate.set(0);
    }
    ledStrip.setLED();
    
    SmartDashboard.putNumber("Dif", testTimer.get() - last);
    SmartDashboard.putNumber("Current State", testTimer.get());
    SmartDashboard.putNumber("Previous State", last);
    last = testTimer.get();

    //Update Smartdashboard Values
    SmartDashboard.putBoolean("Left_R2", left.R2);
    SmartDashboard.putBoolean("Left_R3", left.R3);
    SmartDashboard.putBoolean("Left_R5", left.R5);
    SmartDashboard.putBoolean("Left_R6", left.R6);
    SmartDashboard.putBoolean("Line Sensor", lineSensor.get());
    SmartDashboard.putBoolean("Trigger", right.Trigger);
    SmartDashboard.putString("Limelight State", robotLimeLight.getLLState());
    SmartDashboard.putNumber("Timer", timer.get());
} 

  @Override
    public void testPeriodic() {
       
    }
}
