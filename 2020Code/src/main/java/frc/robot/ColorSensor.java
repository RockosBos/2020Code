package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;

public class ColorSensor {
    private final static I2C.Port i2cPort = I2C.Port.kOnboard;
    private final static ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);

    public String getColor() {

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
    */
    }
}