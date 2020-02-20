package frc.robot;

import edu.wpi.first.networktables.NetworkTable; 
import edu.wpi.first.networktables.NetworkTableEntry; 
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LimeLight{

    private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    private NetworkTableEntry tx = table.getEntry("tx"); 
    private NetworkTableEntry ty = table.getEntry("ty"); 
    private NetworkTableEntry ta = table.getEntry("ta");
    public static String limelightState = "";
    public boolean crosshairIsFound = false;
    

    public double getX(){
        return tx.getDouble(0.0);
    }
    public double getY(){
        return ty.getDouble(0.0);
    }
    public double getArea(){
        return ta.getDouble(0.0);
    }
    public boolean isTargetFound(){
        if(getX() == 0 && getY() == 0){
            return false;
        }
        return true;

    }
    public void setMode(String tableEntry, int value){
        /****************************************
         Table Entry Types
         "ledMode" - changes state of led's
         Values:
            0 - Default from pipeline (preset)
            1 - Force off
            2 - Force Blink
            3 - Force on
        "camMode" - Changes Camera mode
        Values:
            0 - Vision Processing
            1 - Driver camera (Increases exposure, operates like normal camera)
        "pipeline" - Sets current pipeline
        Values:
            0 - Default pipeline
            (1-9) - Custom set pipelines
        "snapshot" - takes snapshot while in operation
        Values:
            0 - stop snapshots
            1 - take two(2) snapshots per second
         
         ***************************************/
        table.getEntry(tableEntry).setNumber(value);
    }

    public String getState(){
        if(getX() == 0.0 && getY() == 0.0){
            limelightState = "Not Found";
        }
        else{

            if(getX() < -4){
                limelightState = "fastLeft";
            } else if(getX() > 4){
                limelightState = "fastRight";
            } else if(getX() < - 2){
                limelightState = "slowLeft";
            } else if(getX() > 2){
                limelightState = "slowRight";
            } else{
                limelightState = "stop";
            }
        }
        return limelightState;
    }
    public void displayData(){
        SmartDashboard.putNumber("LimelightX", getX()); 
        SmartDashboard.putNumber("LimelightY", getY()); 
        SmartDashboard.putNumber("LimelightArea", getArea());
        SmartDashboard.putNumber("LEDMode", table.getEntry("ledMode").getDouble(-1));
        SmartDashboard.putNumber("CamMode", table.getEntry("camMode").getDouble(-1));
        SmartDashboard.putNumber("Pipeline", table.getEntry("pipeline").getDouble(-1));
    }
}
