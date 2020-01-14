package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.ColorSensorV3;

public class ColorSensor{
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);


    public String getColor(){

        final Color detectedColor = colorSensor.getColor();

    
        if ((detectedColor.red < 0.35 && detectedColor.red > 0.3)
            && (detectedColor.green < .6 && detectedColor.green > .53)
            && (detectedColor.blue < 0.13 && detectedColor.blue > .12))
        {
            return "Yellow";
        }
        else if((detectedColor.red < 0.55 && detectedColor.red > 0.5)
            && (detectedColor.green < .36 && detectedColor.green > .32)
            && (detectedColor.blue < 0.14 && detectedColor.blue > .13))
        {
            return "Red";
        }
        else if((detectedColor.red < 0.18 && detectedColor.red > 0.16)
            && (detectedColor.green < .58 && detectedColor.green > .56)
            && (detectedColor.blue < 0.27 && detectedColor.blue > .25))
        {
            return "Green";
        }
        else if((detectedColor.red < 0.125 && detectedColor.red > 0.115)
            && (detectedColor.green < .43 && detectedColor.green > .410)
            && (detectedColor.blue < 0.48 && detectedColor.blue > .44))
        {
            return "Blue";
        }
        else{
            return "None";
        }
    
    }
}