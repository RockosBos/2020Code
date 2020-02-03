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
    int red = 100;
    int green = 100;
    int blue = 100;

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
        led.start();
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

    public void ledRGB(int r, int g, int b){ //Same as Solid color function but with RGB instead of HSV
        for (var i = 0; i < ledBuffer.getLength(); i++){
            ledBuffer.setRGB(i, r, g, b);

        }
        led.setData(ledBuffer);
        led.start();
    }

    

}