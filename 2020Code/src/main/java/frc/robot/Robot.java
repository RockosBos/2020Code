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
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;

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
    DigitalInput lineSensor = new DigitalInput(0);
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
    Shooter robotShooter = new Shooter();
    Intake robotIntake = new Intake();

    RobotGyroscope gyro = new RobotGyroscope();
    
    boolean leftS;
    boolean rightS;
    boolean isOverrideOn = false;

    Timer timer = new Timer();

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

     ToggleLogic servoToggle = new ToggleLogic();
    

  private static final String initiationLineMove = "initiationLineMove";
  private static final String auto1 = "Auton 1";
  private static final String auto2 = "Auton 2";
  private static final String auto3 = "Auton 3";
  private static final String auto4 = "Auton 4";
  private static final String auto5 = "Auton 5";
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
        leftServo.setAngle(50);
        rightServo.setAngle(0);
        SmartDashboard.putString("Version", "1.0.3");
        SmartDashboard.putNumber("Set to Gyro Angle", desiredGyroAngle);
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
    }

  /**
   * This function is called periodically during autonomous.
   */
    @Override
    public void autonomousPeriodic() {
        switch (m_autoSelected) {
        //list of diffrent scenerios//
        case auto1:
                //Auto 1 Logic
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
            // Put default auto code here //
            break;
        }
    }

  /**
   * This function is called periodically during operator control.
   */
  @Override
    public void teleopPeriodic() {
        ledStrip.pulse(0);
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
            System.out.println("bottom face pressed");
            if(lineSensor.get()){
                upperFeed.set(.70);
            }
            else{  
                upperFeed.set(0);
            }
            lowerFeed.set(1);
            intakeWheels.set(1);
        }
        else{
            lowerFeed.set(0);
            upperFeed.set(0);
            intakeWheels.set(0);
        }
        if(left.R3){
            intakeLift.set(.5);
        }
        else if(left.R6){
            intakeLift.set(-.5);
        }
        else{
            intakeLift.set(0);
        }

        
    }
    else{ //Manual Intake Logic
        
        if(left.Trigger){
            System.out.println("trigger");
            shooters.set(1);
            switch(LimeLight.limelightState){
                case "fastRight":
                    shooterRotate.set(-.2);
                    break;
                case "fastLeft":
                    shooterRotate.set(.2);
                    break;
                case "slowLeft":
                    shooterRotate.set(.1);
                    break;
                case "slowRight":
                    shooterRotate.set(-.1);
                    break;
                default:
                    shooterRotate.set(0);
            }
        }
        if(left.BottomFace){
            intakeWheels.set(.8);
        } 
        else{
            intakeWheels.set(0);
        }

        if(left.R3){
            intakeLift.set(.5);
        }
        else if(left.R6){
            intakeLift.set(-.5);
        }
        else{
            intakeLift.set(0);
        }

        if(left.R2){
            lowerFeed.set(-0.8);
        }
        else if(left.R5){
            lowerFeed.set(0.8);
        }
        else{
            lowerFeed.set(0);
        }
    
        if(left.R1){
            upperFeed.set(-0.30);
        }
        else if(left.R4){
            upperFeed.set(0.30);
        }
        else{
            upperFeed.set(0);
        }
    }


    //////servo logic
    servoToggle.currentState = right.R3;
    if(right.toggleButton(servoToggle)){

        leftServo.setAngle(120);  //Low Gear 
        rightServo.setAngle(85);  //Low Gear

    }
    else{
        leftServo.setAngle(50);   //high gear
        rightServo.setAngle(155); //high gear
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
            robotControlWheel.wheelPosition(colorBoi.getColor(), fieldColor.charAt(0), 0.5, 0.7, controlWheelWheel);
        }
    }
    else{   //Manual Color Wheel Logic

    }

    ////climb logic
  
    //trigger logic
    if(!left.BottomFace){
        if(!isOverrideOn){
            if(left.Trigger){
                timer.start();
                System.out.println("trigger");
                shooters.set(1);
                switch(LimeLight.limelightState){
                    case "fastRight":
                        shooterRotate.set(-.2);
                        break;
                    case "fastLeft":
                        shooterRotate.set(.2);
                        break;
                    case "slowLeft":
                        shooterRotate.set(.1);
                        break;
                    case "slowRight":
                        shooterRotate.set(-.1);
                        break;
                    default:
                        shooterRotate.set(0);
                }
                if(LimeLight.limelightState == "stop" && timer.get() > 1){
                    upperFeed.set(1);
                    lowerFeed.set(.30);
                }
                else{
                    upperFeed.set(0);
                    lowerFeed.set(0);
                }
            
            
            }
            else{
                shooters.set(0);
                upperFeed.set(0);
                lowerFeed.set(0);
                shooterRotate.set(0);
                timer.stop();
                timer.reset();
            }
        }   
        else{
            robotShooter.manFire(left.Trigger, .8, shooters);
            robotShooter.manRotate(left.LeftFace, left.RightFace, -0.5, shooterRotate);
        }
    }

    //Climb Logic//

    if(right.L6){
        lifter.set(.1);
    }
    else{
        lifter.set(0);
    }

    //Climber side to side motor code//

    if(right.L5){
        liftRotate.set(.2);
    }
    else if(right.L4){
        liftRotate.set(-.2);
    }
    else{
        liftRotate.set(0);
    }

   
    //Update Smartdashboard Values
    SmartDashboard.putBoolean("Left_R2", left.R2);
    SmartDashboard.putBoolean("Left_R3", left.R3);
    SmartDashboard.putBoolean("Left_R5", left.R5);
    SmartDashboard.putBoolean("Left_R6", left.R6);
    SmartDashboard.putBoolean("Line Sensor", lineSensor.get());
    SmartDashboard.putBoolean("Trigger", right.Trigger);
    SmartDashboard.putString("Limelight State", robotLimeLight.limelightState);

} 

  @Override
    public void testPeriodic() {
        double error = desiredGyroAngle - gyro.gyro.getAngle();
        double p = 100;
        double speed = error / p;
        desiredGyroAngle = SmartDashboard.getNumber("Set To Gyro Angle", -1);
        SmartDashboard.putNumber("Error:", error);

        if(error > 100){
            leftDrive.set(-1);
            rightDrive.set(1);
        }
        else if(error < -100){
            leftDrive.set(1);
            rightDrive.set(-1);
        }
        else{
            leftDrive.set(speed);
            rightDrive.set(-speed);
        }
    }
}
