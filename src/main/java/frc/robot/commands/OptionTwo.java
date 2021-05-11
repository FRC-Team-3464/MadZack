
package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class OptionTwo extends Command {

    edu.wpi.first.networktables.NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    Timer Timer = new Timer();
    //private static final int leftDeviceID = 1; 
    //private CANSparkMax MOTORS;
    boolean roadster = false;
    Timer testTimer = new Timer();

    public OptionTwo() {
      initialize();
      }
    
      // Called just before this Command runs the first time
      @Override
      protected void initialize() {
        roadster = false;
        //MOTORS = new CANSparkMax(leftDeviceID, MotorType.kBrushless);
      }
    
      // Called repeatedly when this Command is scheduled to run
      @Override
      protected void execute() {
       // m_myRobot.setSafetyEnabled(false);

        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setDouble(3);
        //if(Timer.get() >= 0 && Timer.get() <=4);   //edu.wpi.first.wpilibj.Timer.delay(1);
          System.out.println("Targeting...");
          while(tx.getDouble(0.0)>2){
          RobotMap.drive.tankDrive(.7,0);
            //m_myRobot.tankDrive(.8,0);
          }
          while(tx.getDouble(0.0)<-2){
          RobotMap.drive.tankDrive(0,.7);
            //m_myRobot.tankDrive(0,.8);
          } 
          NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setDouble(1);
          //edu.wpi.first.wpilibj.Timer.delay(.25);
          System.out.println("Done Targeting");
         
          Timer.start();
          while(Timer.get() <=3){
            if(tx.getDouble(0.0) > 0){
            /*  edu.wpi.first.wpilibj.Timer.delay(.75);
              MOTORS.set(.7);
              edu.wpi.first.wpilibj.Timer.delay(1.5);
              MOTORS.set(0);
              Timer.stop();
              */
              RobotMap.shooter.set(1);
              edu.wpi.first.wpilibj.Timer.delay(.25);
              RobotMap.indexer.set(.8);
              RobotMap.indexSolenoid1.set(false);

            }
          }
       //     Timer.reset();

           while(Timer.get() <= 5){
              System.out.println("DRIVE");
              RobotMap.drive.tankDrive(.5,.5); // full send robot off of thing line 
              roadster = true;
                //m_myRobot.tankDrive(.8,0);
        }
     
           /* testTimer.start();
            while (testTimer.get() <=2){
              System.out.println("GO GO");
               RobotMap.drive.tankDrive(0.5, 0.5); 
               testTimer.stop();*/
              

        
          }
            
            //}
      
    
      // Make this return true when this Command no longer needs to run execute()
      @Override
      protected boolean isFinished() {
          return false;
      }
    
      // Called once after isFinished returns true
      @Override
      protected void end() {

      }
    
      // Called when another command which requires one or more of the same
      // subsystems is scheduled to run
      @Override
      protected void interrupted() {
      }
}