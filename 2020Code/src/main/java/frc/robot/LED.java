package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;

class LED{
    private AddressableLED led;
    private Timer ledTimer;
    private AddressableLEDBuffer ledBuffer;
    private int rainbowFirstPixelHue;
    private int prevValue = 0;
    private int currentValue = 0;
    private char state = 'I';
    private String ledState = "";

    LED(int port, int length){
        led = new AddressableLED(port);
        ledBuffer = new AddressableLEDBuffer(length);
        led.setLength(ledBuffer.getLength());
        led.setData(ledBuffer);
        led.start();
    }

    public void solid(int color){
        for(int i = 0; i < ledBuffer.getLength(); i++){
            ledBuffer.setHSV(i, color, 255, 128);
        }
        led.setData(ledBuffer);
        
    }

    public void blink(int color, double duration){
        if(ledTimer.get() == 0){
            ledTimer.start();
        }
        else if(ledTimer.get() < duration){
            for(int i = 0; i < ledBuffer.getLength(); i++){
                ledBuffer.setHSV(i, color, 255, 128);
            }
        }
        else if(ledTimer.get() < duration * 2){
            for(int i = 0; i < ledBuffer.getLength(); i++){
                ledBuffer.setHSV(i, color, 255, 0);
            }
        }
        else{
            ledTimer.stop();
            ledTimer.reset();
        }
        
    }

    public void pulse(int color){
        if(state == 'I'){
        
            currentValue = (prevValue + 1);
            for (var i = 0; i < ledBuffer.getLength(); i++) {

                ledBuffer.setHSV(i, color, 255, currentValue);
          }
          if(currentValue == 128){
              prevValue = currentValue;
              state = 'D';
          }
        }
        if(state == 'D'){
            currentValue = (prevValue - 1);
            for (var i = 0; i < ledBuffer.getLength(); i++) {

                ledBuffer.setHSV(i, color, 255, currentValue);
          }
          if(currentValue == 0){
              state = 'I';
          }
        }
          // Increase by to make the rainbow "move"
          rainbowFirstPixelHue += 3;
          // Check bounds
          rainbowFirstPixelHue %= 180;
          led.setData(ledBuffer);
          prevValue = currentValue;

    }

    public void setWhite(){
        for(int i = 0; i < ledBuffer.getLength(); i++){
            ledBuffer.setHSV(i, 0, 0, 100);
        }
        led.setData(ledBuffer);
        
    }

    public void setOff(){
        for(int i = 0; i < ledBuffer.getLength(); i++){
            ledBuffer.setHSV(i, 0, 0, 0);
        }
        led.setData(ledBuffer);

    }






    public void rainbow(){
        
    for (var i = 0; i < ledBuffer.getLength(); i++) {
        
        final var hue = (rainbowFirstPixelHue + (i * 180 / ledBuffer.getLength())) % 180;
        // Set the value
        ledBuffer.setHSV(i, hue, 255, 128);
      }
      // Increase by to make the rainbow "move"
      rainbowFirstPixelHue += 3;
      // Check bounds
      rainbowFirstPixelHue %= 180;
      led.setData(ledBuffer);
    }
    void changeLEDState(String state){
        ledState = state;
    }
    void setLED(){
        //Set LED
    switch(ledState){
        case "SolidRed":
            solid(0);
            break;
        case "SolidYellow":
            solid(40);
            break;   
        case "SolidBlue":
            solid(120);
            break;
        case "SolidGreen":
            solid(40);
            break;
        case "SolidPurple":
            solid(200);
            break;
        case "BlinkRed":
            blink(0, 0.5);
            break;
        case "BlinkYellow":
            blink(40, 0.5);
            break;   
        case "BlinkBlue":
            blink(120, 0.5);
            break;
        case "BlinkGreen":
            blink(40, 0.5);
            break;
        case "BlinkPurple":
            blink(200, 0.5);
            break;
        default:
            setWhite();
            break;
    }
    }
   

}