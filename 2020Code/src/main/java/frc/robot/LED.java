package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;

class LED{
    private AddressableLED led;
    private int stripLength;
    private Timer ledTimer;
    private AddressableLEDBuffer ledBuffer;

    LED(int port, int length){
        led = new AddressableLED(port);
        stripLength = length;
        ledBuffer = new AddressableLEDBuffer(length);
        led.setLength(ledBuffer.getLength());
        led.setData(ledBuffer);
        led.start();
    }

    public void pattern(String patternName, int hue, int saturation, int value){
        switch(patternName){
            case "Solid": 
                for(int i = 0; i < ledBuffer.getLength(); i++){
                    ledBuffer.setHSV(i, hue, saturation, value);
                }
                break;
            case "Blink":
                if(ledTimer.get() == 0){
                    ledTimer.start();
                }
                
                if(ledTimer.get() < 0.5){
                    for(int i = 0; i < ledBuffer.getLength(); i++){
                        ledBuffer.setHSV(i, hue, saturation, value);
                    }
                }
                else if(ledTimer.get() < 1){
                    for(int i = 0; i < ledBuffer.getLength(); i++){
                        ledBuffer.setHSV(i, 0, 0, 0);
                    }
                }
                else{
                    ledTimer.reset();
                    ledTimer.stop();
                }
                break;
            case "Off":
            
            default:
                for(int i = 0; i < ledBuffer.getLength(); i++){
                    ledBuffer.setHSV(i, 0, 0, 0);
                }
        }
    }


}