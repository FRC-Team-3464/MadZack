package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotMap {
  public static int 
    leftStickPort = 0,  
    rightStickPort = 1,
    auxPort = 2,

    indexerMotor = 0,

    climbingRight = 4,
    climbingRightC = 5,
    climbingLeft = 6,
    climbingLeftC = 7,
    climbingFront = 2,

   lift = 1,
   encoderA = 0,
   encoderB = 1,
   ePivot = 0,
  
   leftBackMotor = 6,
   leftFrontMotor = 5,
   rightBackMotor = 8,
   rightFrontMotor = 7,
   colorMotorInteger = 4,
   shooterInteger = 2,
   elevatorInteger = 1,
   intakeInteger = 3;
  
  public static CANSparkMax
    intake = new CANSparkMax(intakeInteger, CANSparkMaxLowLevel.MotorType.kBrushless),
    shooter = new CANSparkMax(shooterInteger, CANSparkMaxLowLevel.MotorType.kBrushless),
    colorMotor = new CANSparkMax(colorMotorInteger, CANSparkMaxLowLevel.MotorType.kBrushless),
    elevator = new CANSparkMax(elevatorInteger, CANSparkMaxLowLevel.MotorType.kBrushless),
    leftBack = new CANSparkMax(leftBackMotor, CANSparkMaxLowLevel.MotorType.kBrushless),
    leftFront = new CANSparkMax(leftFrontMotor, CANSparkMaxLowLevel.MotorType.kBrushless),
    rightFront = new CANSparkMax(rightFrontMotor, CANSparkMaxLowLevel.MotorType.kBrushless),
    rightBack = new CANSparkMax(rightBackMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
  
  public static CANEncoder 
    colorEncoder = colorMotor.getEncoder();
  
  public static Spark 
    indexer = new Spark(indexerMotor);
  
  public static Solenoid 
    intakeSolenoid1 = new Solenoid(1),
    indexSolenoid1 = new Solenoid(2),
    shootingSolenoid1 = new Solenoid(3),
    climbingSolenoid1 = new Solenoid(0);
  
  public static DifferentialDrive drive = new DifferentialDrive((SpeedController)new SpeedControllerGroup((SpeedController)leftBack, new SpeedController[] { (SpeedController)leftFront }), (SpeedController)new SpeedControllerGroup((SpeedController)rightBack, new SpeedController[] { (SpeedController)rightFront }));
}

