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
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Controller;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    WPI_TalonSRX left =new WPI_TalonSRX(0);
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

  
  
    //leftDrive1.talon.setInverted(true);
    

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);

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
    String color;
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

    final Color detectedColor = colorSensor.getColor();
    final double IR = colorSensor.getIR();
    
    //System.out.println("Red: " + detectedColor.red);
    //System.out.println("Green: "+ detectedColor.green);
    //System.out.println("Blue: "+ detectedColor.blue);
    //System.out.println("IR: "+ IR);
    
    if ((detectedColor.red < 0.35 && detectedColor.red > 0.3)
     && (detectedColor.green < .6 && detectedColor.green > .53)
      && (detectedColor.blue < 0.13 && detectedColor.blue > .12))
      {
      System.out.println("Yellow");
    }
    else if((detectedColor.red < 0.55 && detectedColor.red > 0.5)
     && (detectedColor.green < .36 && detectedColor.green > .32)
      && (detectedColor.blue < 0.14 && detectedColor.blue > .13))
      {
      System.out.println("Red");
    }
    else if((detectedColor.red < 0.18 && detectedColor.red > 0.16)
     && (detectedColor.green < .58 && detectedColor.green > .56)
      && (detectedColor.blue < 0.27 && detectedColor.blue > .25))
      {
      System.out.println("Green");
    }
    else if((detectedColor.red < 0.125 && detectedColor.red > 0.115)
     && (detectedColor.green < .43 && detectedColor.green > .410)
      && (detectedColor.blue < 0.48 && detectedColor.blue > .44))
      {
      System.out.println("Blue");
    }
    else{
      System.out.println("Not Recognized");
    }
    
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

    

  }//Nick likes ducks
  


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
    diffDrive.arcadeDrive(right.js.getRawAxis(1), right.js.getRawAxis(0));


  }
  @Override
  public void testPeriodic() {
  }
}
