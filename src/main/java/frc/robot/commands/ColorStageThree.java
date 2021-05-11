package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;




public class ColorStageThree extends Command {

        /*final I2C.Port i2cPort = I2C.Port.kOnboard;
        final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
        final ColorMatch m_colorMatcher = new ColorMatch();
        final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
        final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
        final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
        final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);*/
        //private static final int leftDeviceID = 1; 
        //private CANSparkMax colorMotor;
        boolean happyNoise; 
        int colorNumber;
        int count = 0;
        //String colorString = Robot.colorString;

        public ColorStageThree() {
            initialize();
        }
    
      // Called just before this Command runs the first time
      @Override
      protected void initialize()  {       
        happyNoise = false;
        //colorMotor = new CANSparkMax(leftDeviceID, MotorType.kBrushless);
      }

      // Called repeatedly when this Command is scheduled to run
      @Override
      protected void execute() {  
       
        count++; //increment
        System.out.println(count); //print
        colorNumber = 3;
        //FMS STUFF
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        System.out.println(Robot.colorString);
        if(gameData.length() > 0)
        {
            switch (gameData.charAt(0))
            {
                case 'B' :
                    colorNumber = 1;
                    break;
                case 'G' :
                    colorNumber = 2;
                    break;
                case 'R' :
                    colorNumber = 3;
                    break;
                case 'Y' :
                    colorNumber = 4;
                    break;
                default :
                    //This is corrupt data
                    break;
            }
        }
      //Scanner Boi = new Scanner(System.in);
        //System.out.print("WHAT NOMBA U WANT BOIIIIIIII: ");
        
        /*Color detectedColor = m_colorSensor.getColor();
    
        /**
         * Run the color match algorithm on our detected color
         */
        /*String colorString;
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    
        if (match.color == kBlueTarget) {
          colorString = "Blue";
        } 
        else if (match.color == kRedTarget) {
          colorString = "Red";
        } 
        else if (match.color == kGreenTarget) {
          colorString = "Green";
        } 
        else if (match.color == kYellowTarget) {
          colorString = "Yellow";
        } 
        else {
          colorString = "Unknown";
        }
    
        SmartDashboard.putNumber("Red", detectedColor.red);
        SmartDashboard.putNumber("Green", detectedColor.green);
        SmartDashboard.putNumber("Blue", detectedColor.blue);
        SmartDashboard.putString("Detected Color", colorString);*/
        /**
         * Open Smart Dashboard or Shuffleboard to see the color detected by the 
         * sensor.
         */
        //BGRY
        //colorNumber--;
        
            //System.out.println("1 " + colorString);
            RobotMap.colorMotor.set(.05);//spinny 
            //Tank drive needs to be worked on
            RobotMap.drive.tankDrive(-.55,-.55); //pushes against color wheel while spinning 
        switch(colorNumber){
            case(1):
                if(Robot.colorString.equals("Yellow")){
                    RobotMap.colorMotor.set(.025);// when the color sensor = yellow set motor speed to a speed. 
                }
                if(Robot.colorString.equals("Red")){
                    RobotMap.colorMotor.set(0); // when the color sensor = red stop color wheel motor 
                    happyNoise = true; // yay
                //    RobotMap.drive.tankDrive(0,0);
                }
                break;
            
            /*if(colorString.equals("Blue")){
                colorMotor.set(0);
                happyNoise = true;
            }*/
            case(2):
                if(Robot.colorString.equals("Blue")){
                    RobotMap.colorMotor.set(.025); //when the color sensor = blue spin the color wheel motor 
                }
                if(Robot.colorString.equals("Yellow")){
                    RobotMap.colorMotor.set(0); //when the color sensor = yellow stop the color wheel motor 
                    happyNoise = true;
            //        RobotMap.drive.tankDrive(0,0);
                }
                break;
            case(3):
                if(Robot.colorString.equals("Green")){
                    RobotMap.colorMotor.set(.025); //When the color sensor = green spin the color wheel motor 
                }
                if(Robot.colorString.equals("Blue")){
                    RobotMap.colorMotor.set(0); //When the color sensor = blue stop the motor 
                    happyNoise = true;
            //        RobotMap.drive.tankDrive(0,0);
                }
                break;
            case(4):
                if(Robot.colorString.equals("Red")){
                    RobotMap.colorMotor.set(.025); // when the color sensor = red spin the motor 
                }
                if(Robot.colorString.equals("Green")){
                    RobotMap.colorMotor.set(0); //when the color sensor = green stop the motor 
                    happyNoise = true; // yay! you are a master coder 
            //        RobotMap.drive.tankDrive(0,0);
                }
                break; // get out of the code!!! 
            default:
                colorNumber = 2;
                happyNoise = true;
                break;
            }
            if(OI.button012Aux.get()==true){
                RobotMap.colorMotor.set(0); // stop the motor when you press button 12 on the auxiliary stick :)
                happyNoise = true;
            }
            //System.out.println(colorNumber + "Hey, hey, hey, hey, hey, hey, hey, hey Hey, hey, hey, hey, hey, hey, hey, hey  Hey, hey, hey, hey, hey, hey, hey, hey There lived a certain man in Russia long ago He was big and strong, in his eyes a flaming glow Most people looked at him with terror and with fear But to Moscow chicks he was such a lovely dear He could preach the Bible like a preacher Full of ecstasy and fire But he also was the kind of teacher Women would desire Ra ra Rasputin Lover of the Russian queen There was a cat that really was gone Ra ra Rasputin  Russia's greatest love machine It was a shame how he carried on He ruled the Russian land and never mind the Czar  But the kazachok he danced really wunderbar In all affairs of state he was the man to please But he was real great when he had a girl to squeeze For the queen he was no wheeler dealer Though she'd heard the things he'd done She believed he was a holy healer Who would heal her son Ra ra Rasputin Lover of the Russian queen There was a cat that really was gone Ra ra Rasputin Russia's greatest love machine It was a shame how he carried on But when his drinking and lusting And his hunger for power Became known to more and more people The demands to do something About this outrageous man Became louder and louder Hey, hey, hey, hey, hey, hey, hey, hey Hey, hey, hey, hey, hey, hey, hey, hey  Hey, hey, hey, hey, hey, hey, hey, hey  Hey, hey, hey, hey, hey, hey, hey, hey 'This man's just got to go', declared his enemies But the ladies begged, 'don't you try to do it, please' No doubt this Rasputin had lots of hidden charms Though he was a brute, they just fell into his arms Then one night some men of higher standing Set a trap, they're not to blame 'Come to visit us', they kept demanding And he really came Ra ra Rasputin Lover of the Russian queen They put some poison into his wine Ra ra Rasputin Russia's greatest love machine He drank it all and said, 'I feel fine' Ra ra Rasputin Lover of the Russian queen They didn't quit, they wanted his head Ra ra Rasputin Russia's greatest love machine And so they shot him 'til he was dead Oh, those Russians");
            /*if(OI.button002Left.get()){
                colorMotor.set(0);
                happyNoise = true;
            }*/

    }
            
      // Make this return true when this Command no longer needs to run execute()
      @Override
      protected boolean isFinished() {
            return happyNoise;
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