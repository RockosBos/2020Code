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
import com.ctre.phoenix.motorcontrol.NeutralMode;
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
    static final WPI_TalonSRX controlWheelWheel = new WPI_TalonSRX(13);
    static final WPI_TalonSRX lifter1 = new WPI_TalonSRX(11);
    static final WPI_TalonSRX lifter2 = new WPI_TalonSRX(12);
    //static final DigitalInput lineSensor = new DigitalInput(0);
    static final SpeedControllerGroup rightDrive = new SpeedControllerGroup(MC.rightDrive1, MC.rightDrive2);
    static final SpeedControllerGroup leftDrive = new SpeedControllerGroup(MC.leftDrive1, MC.leftDrive2);
}

    static final class Sensors{
        static final DigitalInput lineSensor = new DigitalInput(0);
        static final DigitalInput climbLimitSwitch = new DigitalInput(1);
        
    }
    

    //PIDController rotatePID = new PIDController(.1, 0, 0);

    //Joysticks

    Controller left = new Controller(0);
    Controller right = new Controller(1);

    //Custom class mechanisms
    TheColorSensor colorBoi = new TheColorSensor();
    LimeLight robotLimeLight = new LimeLight();
    WheelControl robotControlWheel = new WheelControl(colorBoi.getColor());
    LED ledStrip = new LED(0, 48);
    String fieldColor = DriverStation.getInstance().getGameSpecificMessage();
    Shooter robotShooter = new Shooter(left, right);
    Intake robotIntake = new Intake(left, right);
    Gyros gyro = new Gyros(MC.rightDrive, MC.leftDrive);

    //RobotGyroscope gyro = new RobotGyroscope();
    
    boolean isOverrideOn = false;
    boolean triggerPrev = false;
    boolean displayAutoDashboard = false;
    boolean displayTeleopDashboard = false;
    boolean firing;
    boolean climb;
    boolean brakeOn;
    boolean upLast;
    boolean climbStop;

    int shotNum = 0;

    //Timers
    Timer timer = new Timer();
    Timer testTimer = new Timer();

    double last = 0;
    

    //Drive Initialization
     
    DifferentialDrive diffDrive = new DifferentialDrive(MC.rightDrive, MC.leftDrive);

    Servo lowerFeedServo = new Servo(6);
    Servo climbServo = new Servo(7);
    Servo leftServo = new Servo(8);
    Servo rightServo = new Servo(9);
     
    Autonomous autonomous = new Autonomous(MC.leftDrive, MC.rightDrive);

    class ToggleLogic{
        boolean currentState = false;
        boolean prevState = false;
        boolean value = false;
    }
    class Constants{
        static final double INTAKE_LIFT_SPEED = .5;
        static final double INTAKE_WHEELS_SPEED = .75;
        static final double UPPER_FEED_INTAKE_SPEED = 0.7;
        static final double UPPER_FEED_SHOOTER_SPEED = 0.6;
        static final double LOWER_FEED_SPEED = -1.0;
        static final double SHOOTER_ROTATE_SLOW_SPEED = 0.1;
        static final double SHOOTER_ROTATE_FAST_SPEED = 0.4;
        static final double SHOOTER_SPEED = -.92;
        static final double CONTROL_WHEEL_ROTATE_SPEED = 0.1;
        static final double CONTROL_WHEEL_WHEEL_SPEED = 0.1;
        static final double LIFTER_SPEED = -0.60;

        static final int LEFT_SERVO_HIGH_GEAR = 50;
        static final int RIGHT_SERVO_HIGH_GEAR = 155;
        static final int CLIMB_SERVO_HOLD = 180;
        static final int CLIMB_SERVO_RELEASE = 90;
        static final int LEFT_SERVO_LOW_GEAR = 120;
        static final int RIGHT_SERVO_LOW_GEAR = 85;
        static final double LIMELIGHT_MAX = 1; 
        static final double LIMELIGHT_SPEED = 0.8;
        static final int LOW_FEED_SERVO_HIGH = 45;
        static final int LOW_FEED_SERVO_LOW = 110;
        
    }

    //double yShooterSpeed = robotLimeLight.ySpeed(robotLimeLight.getY());
    double yShooterSpeed = 1.0;
    

    ToggleLogic servoToggle = new ToggleLogic();
    
     //Autonomous Variables
    private static final String initiationLineMove = "initiationLineMove";
    private static final String moveBackwards = "moveBackwards";
    private static final String centerMoveShoot = "centerMoveShoot";
    private static final String trenchMoveShoot = "trenchMoveShoot";
    private static final String initiationLineTrenchShoot = "initLineTrenchShoot";
    private static final String pushShoot = "pushShoot";
    private Timer autonTimer = new Timer();
    private Timer autonDelayTimer = new Timer();
    private int autonomousStep;
    private double delay;


  private String m_autoSelected;
  private final SendableChooser<String> auto_chooser = new SendableChooser<>();
  private final SendableChooser<Boolean> override_chooser = new SendableChooser<>();
  private final SendableChooser<Boolean> autonDashboard_chooser = new SendableChooser<>();
  private final SendableChooser<Boolean> teleopDashboard_chooser = new SendableChooser<>();

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
        auto_chooser.addOption("Move Backwards", moveBackwards);
        auto_chooser.addOption("Center start, shoot, get balls, shoot", centerMoveShoot);
        auto_chooser.addOption("Trench start, shoot, get balls, shoot", trenchMoveShoot);
        auto_chooser.addOption("initiation line shoot, get balls from trench", initiationLineTrenchShoot);
        auto_chooser.addOption("Shoot then push", pushShoot);

        
        climb = false;

        override_chooser.setDefaultOption("Override Off", false);
        override_chooser.addOption("Override On", true);

        autonDashboard_chooser.setDefaultOption("Auton Dashboard Off", false);
        autonDashboard_chooser.addOption("Auton Dashboard On", true);

        teleopDashboard_chooser.setDefaultOption("Teleop Dashboard Off", false);
        teleopDashboard_chooser.addOption("Teleop Dashboard On", true);

        SmartDashboard.putData("Override", override_chooser);
        SmartDashboard.putData("Auto choices", auto_chooser);


        SmartDashboard.putData("Autonomous Dashboard", autonDashboard_chooser);
        SmartDashboard.putData("Teleop Dashboard", teleopDashboard_chooser);
        

        SmartDashboard.putString("Version", "1.0.4");
        SmartDashboard.putNumber("Set to Gyro Angle", desiredGyroAngle);
        SmartDashboard.putNumber("Autonomous Delay", 0);

        leftServo.setAngle(Constants.LEFT_SERVO_HIGH_GEAR);
        rightServo.setAngle(Constants.RIGHT_SERVO_HIGH_GEAR);
        lowerFeedServo.setAngle(Constants.LOW_FEED_SERVO_LOW);
        climbServo.setAngle(Constants.CLIMB_SERVO_HOLD);

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
        robotLimeLight.getState();
        SmartDashboard.putString("Limelight State", robotLimeLight.getLLState());
        
        isOverrideOn = override_chooser.getSelected();
        displayAutoDashboard = autonDashboard_chooser.getSelected();
        displayTeleopDashboard = teleopDashboard_chooser.getSelected();

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
        autonTimer.start();
        delay = SmartDashboard.getNumber("Autonomous Delay", 0);
        autonDelayTimer.start();
        autonomousStep = 0;
        robotShooter.shootTimer.start();
        // timer.reset();
        autonomous.straightStep = 0;
        
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
            case moveBackwards:
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
                        boolean lineSensorPrevState = Sensors.lineSensor.get();
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
                        if(Sensors.lineSensor.get() && Sensors.lineSensor.get() != lineSensorPrevState){
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
                case centerMoveShoot:
                    //Auto 2 Logic
                    switch (autonomousStep){
                        case 0:
                            robotLimeLight.setMode("ledMode", 0);
                            robotLimeLight.setMode("camMode", 0);  
                            robotShooter.autoAim();
                            robotShooter.autoShoot();
                            if(robotShooter.linePreviousState != Sensors.lineSensor.get() && Sensors.lineSensor.get() == false){
                                shotNum++;
                        }
                            if(shotNum > 2 || autonTimer.get() > 5){
                                autonomousStep = 1;
                                autonTimer.reset();
                        }
                        break;
                        case 1:
                            if(autonTimer.get() < .4){
                                MC.intakeLift.set(-Constants.INTAKE_LIFT_SPEED);
                            }
                            if(autonTimer.get() < 2){
                                autonomous.setDrive(.5, .5);
                                MC.intakeWheels.set(Constants.INTAKE_WHEELS_SPEED);
                            }
                            else{
                                autonomous.stopDrive();
                                MC.intakeWheels.set(0);
                                MC.intakeLift.set(0);
                                autonomousStep++;
                            }

                        break;
                        case 2:
                            robotShooter.shootTimer.reset();
                            shotNum = 0;
                            autonomousStep++;
                        break;
                        case 3:
                            
                            robotLimeLight.setMode("ledMode", 0);
                            robotLimeLight.setMode("camMode", 0);  
                            robotShooter.autoAim();
                            robotShooter.autoShoot();
                            if(robotShooter.linePreviousState != Sensors.lineSensor.get() && Sensors.lineSensor.get() == false){
                                shotNum++;
                            }
                            if(shotNum > 2 || autonTimer.get() > 5){
                                autonomousStep++;
                            }
                        break;
                        case 4:
                            robotShooter.autoShootStop();
                        break;
                    }
                break;
            case trenchMoveShoot:
                    //Auto 3 Logic
                   switch (autonomousStep){
                       case 0:
                            if(shotNum < 3){
                                MC.shooters.set(Constants.SHOOTER_SPEED);
                                robotShooter.autoAim();
                                    if(autonTimer.get() >= 2.5){
                                        autonomous.shootAndFeed();
                                            if(Sensors.lineSensor.get() == true){
                                                shotNum = shotNum + 1;
                                            }                        
                                    }
                            }
                            if(shotNum == 3){
                                autonomousStep = 1;
                                MC.shooters.set(0);
                                
                            }

                       break;

                       case 1:
                            gyro.GyroRotate(180);
                            if(gyro.state == "Good"){
                                autonomousStep = 2;
                                autonTimer.reset();
                                }
                       break;

                       case 2:
                            if(autonTimer.get() <= 3){
                                MC.intakeWheels.set(Constants.INTAKE_WHEELS_SPEED);
                                autonomous.driveForward();                                
                                if(autonTimer.get() > 3){
                                    autonomous.setDrive(0, 0);
                                    autonomousStep = 3;
                                    MC.intakeWheels.set(0);
                                    autonTimer.reset();
                                    autonomous.straightStep = 0;
                                }
                            }
                       break;
                            
                       case 3:
                            if(autonTimer.get() <= 3){
                                autonomous.driveForward(); 
                                MC.shooters.set(Constants.SHOOTER_SPEED);                               
                                    if(autonTimer.get() > 3){
                                        autonomous.setDrive(0, 0);
                                        
                                        MC.intakeWheels.set(0);
                                        shotNum = 0;
                                        autonomous.straightStep = 0;
                                        autonomousStep = 4;
                                        
                                    }
                            }
                       break;
                            
                       case 4:
                            if(shotNum < 3){
                                MC.shooters.set(Constants.SHOOTER_SPEED);
                                robotShooter.autoAim();
                                    if(autonTimer.get() >= 2.5){
                                        autonomous.shootAndFeed();
                                            if(Sensors.lineSensor.get() == true){
                                                shotNum = shotNum + 1;
                                            }                                   
                                    }
                            }           
                            if(shotNum == 3){
                                MC.shooters.set(0);
                            }
                       break;
                   }
                break;
            case initiationLineTrenchShoot:
                   switch(autonomousStep){
                    case 0:
                        
                        robotLimeLight.setMode("ledMode", 0);
                        robotLimeLight.setMode("camMode", 0);  
                        robotShooter.autoAim();
                        robotShooter.autoShoot();
                        if(robotShooter.linePreviousState != Sensors.lineSensor.get() && Sensors.lineSensor.get() == false){
                            shotNum++;
                        }
                        if(autonTimer.get() > 5){
                            autonomousStep++;
                            autonTimer.reset();
                        }

                        break;
                    case 1:
                        robotShooter.autoShootStop();
                        robotLimeLight.setMode("ledMode", 1);
                        robotLimeLight.setMode("camMode", 1);
                        robotIntake.intakeAll();
                        if(autonTimer.get() < 1){
                            MC.intakeLift.set(-Constants.INTAKE_LIFT_SPEED);
                        }
                        else{
                            MC.intakeLift.set(0);
                        }
                        if(autonTimer.get() < 3){
                            autonomous.setDrive(0.76, .8);
                        }
                        else{
                            autonomous.setDrive(0, 0);
                            autonomousStep++;
                            autonTimer.reset();
                        }
                        break;
                    case 2:
                        autonomous.setDrive(0, 0);
                        if(autonTimer.get() < 0.5){
                            autonTimer.reset();
                            autonomousStep++;

                        }
                        break;
                    case 3:
                        robotLimeLight.setMode("ledMode", 0);
                        robotLimeLight.setMode("camMode", 0);
                        robotShooter.shootTimer.reset();
                        robotShooter.autoAim();
                        if(autonTimer.get() < 1){
                            MC.intakeLift.set(Constants.INTAKE_LIFT_SPEED);
                        }
                        if(autonTimer.get() < 3){
                            autonomous.setDrive(-0.76,- .8);
                        }
                        else{
                            autonomousStep++;
                            robotShooter.shootTimer.reset();
                            timer.start();
                        }
                        break;
                    case 4:
                        
                        
                        robotLimeLight.setMode("ledMode", 0);
                        robotLimeLight.setMode("camMode", 0);
                        robotShooter.autoAim();
                        robotShooter.autoShoot();
                        autonomous.setDrive(0, 0);
                        break;
                    

                   }
                    //Auto 4 Logic
                break;
            case pushShoot:
                switch (autonomousStep){
                    case 0:
                        
                        robotLimeLight.setMode("ledMode", 0);
                        robotLimeLight.setMode("camMode", 0);  
                        robotShooter.autoAim();
                        robotShooter.autoShoot();
                        if(robotShooter.linePreviousState != Sensors.lineSensor.get() && Sensors.lineSensor.get() == false){
                            shotNum++;
                        }
                        if(shotNum > 2 || autonTimer.get() > 10){
                            autonomousStep = 1;
                            autonTimer.reset();
                        }
                    break;
                    case 1:
                        robotShooter.autoShootStop();
                        if (autonTimer.get() < 1){
                            autonomous.setDrive(.5, .5);
                        }
                        else{
                            autonomous.setDrive(0, 0);
                            autonTimer.reset();
                            autonomousStep = 2;
                        }
                  
                    break;
                    case 2:
                        if(autonTimer.get() < 1.5){
                            autonomous.setDrive(-.5, -.5);
                        }
                        else{
                            autonomous.setDrive(0, 0);
                        }
                    break;
                  
                    
                }

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
        //Autonomous dashboard values here
        if(displayAutoDashboard){
            SmartDashboard.putString("AutonMode", m_autoSelected);
            SmartDashboard.putNumber("Auton Step", autonomousStep);
            SmartDashboard.putNumber("Auton Timer", autonTimer.get());
            SmartDashboard.putNumber("Number Shot", shotNum);
            SmartDashboard.putString("Limelight State", robotLimeLight.getLLState());
            SmartDashboard.putNumber("Shoot Timer", robotShooter.shootTimer.get());
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
            if(Sensors.lineSensor.get()){
                MC.upperFeed.set(Constants.UPPER_FEED_INTAKE_SPEED);
            }
            else{  
                MC.upperFeed.set(0);
            }
            MC.lowerFeed.set(Constants.LOWER_FEED_SPEED);
            MC.intakeWheels.set(Constants.INTAKE_WHEELS_SPEED);
            ledStrip.changeLEDState("SolidYellow");
        }
        else{
            
            if(left.L3){
                robotIntake.outtakeAll();
            }
            else{
                robotIntake.stopAllIntake();
            }
        }
        if(left.R3){
            MC.intakeLift.set(Constants.INTAKE_LIFT_SPEED);
            upLast = true;
            brakeOn = false;
        }
        else if(left.R6){
            MC.intakeLift.set(-Constants.INTAKE_LIFT_SPEED);
            upLast = false;
            brakeOn = false;
        }
        else if(upLast){
            
            MC.intakeLift.set(robotIntake.BRAKE);
            brakeOn = true;
        }
        else{
            brakeOn = false;
            MC.intakeLift.set(0);
        }
        

        
    }
    else{ //Manual Intake Logic
        robotIntake.operateManually();   
    }
    //outake//
    

    //////servo logic
    
    if(right.Trigger){
        leftServo.setAngle(Constants.LEFT_SERVO_LOW_GEAR);  //Low Gear 
          rightServo.setAngle(Constants.RIGHT_SERVO_LOW_GEAR);  //Low Gear
          ledStrip.changeLEDState("SolidBlue");
    }
    else{
      servoToggle.currentState = right.L1;
      if(right.toggleButton(servoToggle)){
          leftServo.setAngle(Constants.LEFT_SERVO_LOW_GEAR);  //Low Gear 
          rightServo.setAngle(Constants.RIGHT_SERVO_LOW_GEAR);  //Low Gear
          ledStrip.changeLEDState("SolidBlue");

      }
      else{
          leftServo.setAngle(Constants.LEFT_SERVO_HIGH_GEAR);   //high gear
          rightServo.setAngle(Constants.RIGHT_SERVO_HIGH_GEAR); //high gear
      }
    }

    if(right.R2){
        lowerFeedServo.setAngle(Constants.LOW_FEED_SERVO_HIGH);
    }
    else{
        lowerFeedServo.setAngle(Constants.LOW_FEED_SERVO_LOW);
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

  robotControlWheel.wheelRotation();

    //color wheel start
    if(!isOverrideOn){ //Auto Color Wheel Logic
        if(right.BottomFace){
            //robotControlWheel.wheelPosition(colorBoi.getColor(), fieldColor.charAt(0), 0.5, 0.7, controlWheelWheel);
        }
    }
    else{   //Manual Color Wheel Logic
      robotControlWheel.wheelMan(1, right.povLeft, right.povRight, MC.controlWheelWheel);
    }

    
    robotShooter.manRotate(-Constants.SHOOTER_ROTATE_FAST_SPEED);
    
  
  
    //trigger logic
    
    if(!left.BottomFace){
        if(!isOverrideOn){
            if(left.Trigger){
                if(!triggerPrev){
                    timer.start();
                }
                
                MC.shooters.set(Constants.SHOOTER_SPEED);
                ledStrip.changeLEDState("SolidGreen");
                
                robotShooter.autoAim();
                if(timer.get() > 2.5){
                    robotShooter.autoShoot();
                }
                triggerPrev = true;
            }
            else{
                if(!left.L3){
                    robotIntake.stopAllIntake();
                }
                MC.shooters.set(0);
                robotShooter.manRotate(-Constants.SHOOTER_ROTATE_FAST_SPEED);
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
    if(isOverrideOn){
        climbStop = false;
    }
    else{
        if(Sensors.climbLimitSwitch.get()){
            climbStop = true;
        }
        else{
            climbStop = false;
        }
    }
    if(right.R3){
        climbServo.setAngle(Constants.CLIMB_SERVO_RELEASE);
        climb = true;
    }
    else{
        climbServo.setAngle(Constants.CLIMB_SERVO_HOLD);
    }
    
    
       
    if(right.L3 && climb && !climbStop){
            MC.lifter1.set(Constants.LIFTER_SPEED);
            MC.lifter2.set(Constants.LIFTER_SPEED);
            ledStrip.changeLEDState("SolidPurple");
            
    }
        
    else if(right.L6 && climb){
            MC.lifter1.set(-Constants.LIFTER_SPEED);
            MC.lifter2.set(-Constants.LIFTER_SPEED);
            ledStrip.changeLEDState("SolidPurple");
    }
    else{
            MC.lifter1.set(0);
            MC.lifter2.set(0);
    }
    
    
    if(isOverrideOn){
        ledStrip.changeLEDState("SolidRed");
    }
    ledStrip.setLED();
    
    last = testTimer.get();


    
    //Teleop Dashboard Value
    if(displayTeleopDashboard){
        SmartDashboard.putNumber("Dif", testTimer.get() - last);
        SmartDashboard.putNumber("Current State", testTimer.get());
        SmartDashboard.putNumber("Previous State", last);
        SmartDashboard.putBoolean("Left_R2", left.R2);
        SmartDashboard.putBoolean("Left_R3", left.R3);
        SmartDashboard.putBoolean("Left_R5", left.R5);
        SmartDashboard.putBoolean("Left_R6", left.R6);
        SmartDashboard.putBoolean("Line Sensor", Sensors.lineSensor.get());
        SmartDashboard.putBoolean("Trigger", right.Trigger);
        SmartDashboard.putString("Limelight State", robotLimeLight.getLLState());
        SmartDashboard.putNumber("Timer", timer.get());
        SmartDashboard.putBoolean("Servo", climb);
        SmartDashboard.putBoolean("R3", right.R3);
        SmartDashboard.putBoolean("L3", right.L3);
        SmartDashboard.putBoolean("Is Brake On", brakeOn);
    }
} 

  @Override
    public void testPeriodic() {
       
    }
}
