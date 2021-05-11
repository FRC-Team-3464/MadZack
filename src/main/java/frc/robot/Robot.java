package frc.robot;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.commands.ColorStageThree;
import frc.robot.commands.OptionOne;
import frc.robot.commands.OptionTwo;
import java.util.Objects;

public class Robot extends TimedRobot {
  private Integer iCount = Integer.valueOf(0);
  
  Timer testTimer = new Timer();
  
  Timer Timer = new Timer();
  
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  
  private final PixyI2C ballFinder = new PixyI2C();
  
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(this.i2cPort);
  
  private final ColorMatch m_colorMatcher = new ColorMatch();
  
  private final Color kBlueTarget = ColorMatch.makeColor(0.143D, 0.427D, 0.429D);
  
  private final Color kGreenTarget = ColorMatch.makeColor(0.197D, 0.561D, 0.24D);
  
  private final Color kRedTarget = ColorMatch.makeColor(0.561D, 0.232D, 0.114D);
  
  private final Color kYellowTarget = ColorMatch.makeColor(0.361D, 0.524D, 0.113D);
  
  public static String colorString;
  
  public static ColorStageThree CoThree = new ColorStageThree();
  
  public static OI m_oi;
  
  Command m_autonomousCommand;
  
  Command m_optionOne = (Command)new OptionOne();
  
  SendableChooser<Command> m_chooser = new SendableChooser();
  
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  
  NetworkTableEntry tx = this.table.getEntry("tx");
  
  NetworkTableEntry ty = this.table.getEntry("ty");
  
  NetworkTableEntry ta = this.table.getEntry("ta");
  
  @Override
  public void robotInit() {
    m_oi = new OI();
    this.m_chooser.setDefaultOption("Auto 1", this.m_optionOne);
    this.m_chooser.addOption("Auto 2", new OptionTwo());
    SmartDashboard.putData("Auto mode", (Sendable)this.m_chooser);
    SmartDashboard.putData("Auto mode", (Sendable)this.m_chooser);
    Objects.requireNonNull(CameraServer.getInstance());
    (new Thread(CameraServer.getInstance()::startAutomaticCapture)).start();
  }
  
  @Override
  public void robotPeriodic() {
    double limex = this.tx.getDouble(0.0D);
    double limey = this.ty.getDouble(0.0D);
    double limeArea = this.ta.getDouble(0.0D);
    SmartDashboard.putNumber("LimelightX", limex);
    SmartDashboard.putNumber("LimelightY", limey);
    SmartDashboard.putNumber("LimelightArea", limeArea);
    this.iCount = Integer.valueOf(this.iCount.intValue() + 1);
    this.iCount = Integer.valueOf(this.iCount.intValue() % 100);
    if (this.iCount.intValue() == 0) {
      this.ballFinder.PollPixy();
      SmartDashboard.putString("Pixy str", this.ballFinder.getPixyString());
    } 
  }
  
  @Override
  public void disabledInit() {}
  
  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }
  
  @Override
  public void autonomousInit() {
    this.m_autonomousCommand = (Command)this.m_chooser.getSelected();
    if (this.m_autonomousCommand != null)
      this.m_autonomousCommand.start(); 
    if (this.m_autonomousCommand == null)
      System.out.println("PICK AN AUTO"); 
  }
  
  @Override
  public void autonomousPeriodic() {
    this.Timer.start();
    while (this.Timer.get() <= 3.0D && this.Timer.get() >= 0.0D) {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setDouble(3.0D);
      if (this.tx.getDouble(0.0D) > 2.0D) {
        RobotMap.drive.tankDrive(0.5D, -0.1D);
      } else if (this.tx.getDouble(0.0D) < -2.0D) {
        RobotMap.drive.tankDrive(-0.1D, 0.5D);
      } 
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setDouble(1.0D);
    } 
    while (this.Timer.get() <= 5.0D && this.Timer.get() >= 3.0D) {
      RobotMap.shooter.set(1.0D);
      Timer.delay(0.75D);
      RobotMap.indexer.set(0.8D);
      Timer.delay(3.0D);
      RobotMap.indexer.set(0.0D);
      RobotMap.shooter.set(0.0D);
    } 
    this.Timer.stop();
  }
  
  @Override
  public void teleopInit() {
    this.m_colorMatcher.addColorMatch(this.kBlueTarget);
    this.m_colorMatcher.addColorMatch(this.kGreenTarget);
    this.m_colorMatcher.addColorMatch(this.kRedTarget);
    this.m_colorMatcher.addColorMatch(this.kYellowTarget);
    if (this.m_autonomousCommand != null)
      this.m_autonomousCommand.cancel(); 
  }
  
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    RobotMap.drive.tankDrive(OI.rightStick.getY() * 0.65D, OI.leftStick.getY() * 0.65D);
    if (OI.button005Left.get())
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setDouble(3.0D); 
    if (OI.button006Left.get())
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setDouble(1.0D); 
    Color detectedColor = this.m_colorSensor.getColor();
    ColorMatchResult match = this.m_colorMatcher.matchClosestColor(detectedColor);
    if (match.color == this.kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == this.kRedTarget) {
      colorString = "Red";
    } else if (match.color == this.kGreenTarget) {
      colorString = "Green";
    } else if (match.color == this.kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    } 
    System.out.println("0 " + colorString);
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
    double degree_small = -0.02D;
    double min_speed = 0.095D;
    if (OI.triggerLeft.get()) {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setDouble(3.0D);
      double tx_value = this.tx.getDouble(0.0D);
      if (this.tx.getDouble(0.0D) > 2.0D) {
        RobotMap.drive.tankDrive(degree_small * tx_value + min_speed, degree_small * tx_value - min_speed);
      } else if (this.tx.getDouble(0.0D) < -2.0D) {
        RobotMap.drive.tankDrive(degree_small * tx_value - min_speed, degree_small * tx_value + min_speed);
      } 
    } 
  }
  
  @Override
  public void testPeriodic() {}
}
