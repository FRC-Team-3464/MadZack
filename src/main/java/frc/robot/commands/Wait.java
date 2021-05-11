/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Wait extends Command {

  private Timer waitTimer;
  private double waitTime;

  public Wait(double time) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    waitTimer = new Timer();
    waitTime = time;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    waitTimer.reset();
    waitTimer.start();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return waitTimer.get() >= waitTime;
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
