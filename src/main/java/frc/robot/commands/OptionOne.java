
package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class OptionOne extends Command {

    edu.wpi.first.networktables.NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    Timer Timer = new Timer(); // initalize a new timer 
    //private static final int leftDeviceID = 1; 
    //private CANSparkMax MOTORS;
    boolean roadster = false;

    public OptionOne() {
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

        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setDouble(3); // turn on the limelight LED's
        //if(Timer.get() >= 0 && Timer.get() <=4);   //edu.wpi.first.wpilibj.Timer.delay(1);
          System.out.println("Targeting...");
          while(tx.getDouble(0.0)>2){ // while the value of the vision area turn the drive train to realign. 
          RobotMap.drive.tankDrive(.7,0);
            //m_myRobot.tankDrive(.8,0);
          }
          while(tx.getDouble(0.0)<-2){
          RobotMap.drive.tankDrive(0,.7);// while the value of the vision area turn the drive train to realign. 
            //m_myRobot.tankDrive(0,.8);
          } 
          NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setDouble(1); // turn off the limelight 
          //edu.wpi.first.wpilibj.Timer.delay(.25);
          System.out.println("Done Targeting");
         
          Timer.start(); //start the timer 
          while(Timer.get() <=3){ // while the timer is less than or equal to 3 seconds 
            if(tx.getDouble(0.0) > 0){ // when the x value of the calculated is greated than 0. 
              RobotMap.shooter.set(1); // crank that shoota boi full send!
              edu.wpi.first.wpilibj.Timer.delay(.25); //set a quarter second delay
              RobotMap.indexer.set(.8); //spin the indexer at 64%
              RobotMap.indexSolenoid1.set(false); // set the solenoid to its home position or off state
              roadster = true;
            }
          }
            Timer.reset(); // restart the timer 
        /*  Timer.start();
        while(Timer.get() <= 2){
              System.out.println("DRIVE");
              RobotMap.drive.tankDrive(.5,.5);
                //m_myRobot.tankDrive(.8,0);
        }*/
     
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