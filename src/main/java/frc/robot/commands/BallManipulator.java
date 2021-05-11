package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.RobotMap;

public class BallManipulator extends Command {
  double distance;
  
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  
  NetworkTableEntry ty = this.table.getEntry("ty");
  
  boolean shooter = false;
  
  Timer testTimer = new Timer();
  
  @Override
  protected void initialize() {
    this.shooter = false;
  }
  
  @Override
  protected void execute() {
    if (OI.button006Aux.get() == true) {
      RobotMap.shootingSolenoid1.set(false);
      RobotMap.indexer.set(0.7D);
      RobotMap.intakeSolenoid1.set(true);
      RobotMap.indexSolenoid1.set(true);
      Timer.delay(0.25D);
      RobotMap.intake.set(0.7D);
    } 
    if (OI.button004Aux.get() == true) {
      RobotMap.shootingSolenoid1.set(false);
      RobotMap.intakeSolenoid1.set(true);
      RobotMap.indexSolenoid1.set(true);
      Timer.delay(0.25D);
      RobotMap.intake.set(-0.7D);
    } 
    if (OI.triggerAux.get() == true) {
      RobotMap.shootingSolenoid1.set(true);
      RobotMap.shooter.set(1.0D);
      Timer.delay(0.65D);
      RobotMap.indexer.set(0.8D);
    } 
    if (OI.button011Aux.get() == true) {
      RobotMap.shootingSolenoid1.set(true);
      RobotMap.shooter.set(0.85D);
      Timer.delay(0.65D);
      RobotMap.indexer.set(0.8D);
    } 
    if (OI.button002Aux.get())
      this.shooter = true; 
    if (OI.button005Aux.get() == true) {
      RobotMap.shootingSolenoid1.set(false);
      RobotMap.intakeSolenoid1.set(true);
      RobotMap.indexer.set(-0.7D);
    } 
    if (OI.button003Aux.get() == true) {
      RobotMap.shootingSolenoid1.set(false);
      RobotMap.intakeSolenoid1.set(true);
      RobotMap.indexer.set(0.7D);
    } 
    if (!OI.button006Aux.get() && !OI.triggerAux.get() && !OI.button005Aux.get() && !OI.button003Aux.get() && !OI.button004Aux.get() && !OI.button011Aux.get()) {
      RobotMap.shooter.set(0.0D);
      RobotMap.indexer.set(0.0D);
      RobotMap.intake.set(0.0D);
      RobotMap.intakeSolenoid1.set(false);
      RobotMap.indexSolenoid1.set(false);
    } 
  }
  
  @Override
  protected boolean isFinished() {
    return this.shooter;
  }
}
