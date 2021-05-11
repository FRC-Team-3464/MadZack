package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.RobotMap;

public class Elevator extends Command {
  boolean Boio = false;
  
  Timer testTimer = new Timer();
  
  DigitalInput topLimitSwitch = new DigitalInput(1);
  
  DigitalInput bottomLimitSwitch = new DigitalInput(0);
  
  @Override
  protected void initialize() {
    RobotMap.climbingSolenoid1.set(true);
    Timer.delay(0.125D);
    this.Boio = false;
  }
  
  @Override
  protected void execute() {
    if (OI.button006Right.get() == true) {
      RobotMap.elevator.set(-0.5D);
      if (!this.bottomLimitSwitch.get())
        RobotMap.elevator.set(0.0D); 
    } 
    if (OI.button005Right.get() == true) {
      RobotMap.elevator.set(0.5D);
      if (!this.topLimitSwitch.get()) {
        RobotMap.elevator.set(0.0D);
        RobotMap.climbingSolenoid1.set(false);
        this.Boio = true;
      } 
    } 
    if (!OI.button005Right.get() && !OI.button006Right.get())
      RobotMap.elevator.set(0.0D); 
    if (OI.button003Right.get() == true)
      RobotMap.climbingSolenoid1.set(false); 
    if (OI.button004Right.get() == true)
      RobotMap.climbingSolenoid1.set(true); 
  }
  
  @Override
  protected boolean isFinished() {
    return this.Boio;
  }
  
  @Override
  protected void end() {}
}
