
/* 
    __   ___   _       ___   ____       __    __  __ __    ___    ___  _     
   /  ] /   \ | |     /   \ |    \     |  |__|  ||  |  |  /  _]  /  _]| |    
  /  / |     || |    |     ||  D  )    |  |  |  ||  |  | /  [_  /  [_ | |    
 /  /  |  O  || |___ |  O  ||    /     |  |  |  ||  _  ||    _]|    _]| |___ 
/   \_ |     ||     ||     ||    \     |  `  '  ||  |  ||   [_ |   [_ |     |
\     ||     ||     ||     ||  .  \     \      / |  |  ||     ||     ||     |
 \____| \___/ |_____| \___/ |__|\_|      \_/\_/  |__|__||_____||_____||_____|
                                                                             
*/                                                            
   ////fuck                                                         
package frc.robot; import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX; public class WheelControl{public void wheelPosition(char color, char desiredColor, WPI_TalonSRX motor){ double ss = 0.5; double fs = 0.7; System.out.println(desiredColor); if(color != desiredColor){ if(desiredColor == 'G'){ if(color == 'B') motor.set(-ss); else if(color == 'Y') motor.set(-fs); else if(color == 'R') motor.set(ss);} else if(desiredColor == 'R'){ if(color == 'G') motor.set(-ss); else if(color == 'B') motor.set(-fs); else if(color == 'Y') motor.set(ss); } else if(desiredColor == 'Y'){ if(color == 'B') motor.set(ss); else if(color == 'R') motor.set(-ss); else if(color == 'G') motor.set(-fs); } else if(desiredColor == 'B'){ if(color == 'G')motor.set(-ss);else if(color == 'Y') motor.set(ss); else if(color == 'R')motor.set(fs);} else motor.set(0.0);}}}

