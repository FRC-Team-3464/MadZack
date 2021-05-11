package frc.robot.commands;



import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.RobotMap;



public class ColorStageTwo extends Command {
//rotation control 
        final I2C.Port i2cPort = I2C.Port.kOnboard;
        final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
        final ColorMatch m_colorMatcher = new ColorMatch();
        //private static final int leftDeviceID = 1; 
        //private CANSparkMax colorMotor;
        //private CANEncoder colorEncoder;

        public ColorStageTwo() {
            initialize();
        }
    
      // Called just before this Command runs the first time
      @Override
      protected void initialize()  {
        //colorMotor = new CANSparkMax(leftDeviceID, MotorType.kBrushless);
        //colorEncoder = colorMotor.getEncoder();
        
        RobotMap.colorEncoder.setPosition(0); // set the encoder to its "home" position or zero 
      }

      // Called repeatedly when this Command is scheduled to run
      @Override
      protected void execute() {
        //NetworkTable Colors = NetworkTableInstance.getDefault().getTable("SmartDashboard");
        //NetworkTableEntry Color = Colors.getEntry("Detected Color");

          System.out.println("Position: "+ RobotMap.colorEncoder.getPosition()); //prints position
          if(RobotMap.colorEncoder.getPosition() < 5){
            RobotMap.colorMotor.set(.07);//runs motor based on encoder position
          }
          if(OI.button012Aux.get()){
            RobotMap.colorMotor.set(0); // brake function for stopping
            RobotMap.colorEncoder.setPosition(30);//sets encoder position to 30
          }
          if(RobotMap.colorEncoder.getPosition() >= 27){
            RobotMap.colorMotor.set(0);//turn off MoToR
          }

    }
            
      // Make this return true when this Command no longer needs to run execute()
      @Override
      protected boolean isFinished() {
            return RobotMap.colorEncoder.getPosition() >= 27;
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