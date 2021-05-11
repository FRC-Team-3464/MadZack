package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.BallManipulator;
import frc.robot.commands.Elevator;

public class OI {
  public static Joystick
  leftStick, // 
  rightStick, // 
  auxStick; // 

public static JoystickButton 
  triggerRight, // 
  triggerLeft, //
  triggerAux, // 

  button002Right, // 
  button003Right, // 
  button004Right, // 
  button005Right, // 
  button006Right, //
  button007Right, // 
  button008Right, // 
  button009Right, // 
  button010Right, // 
  button011Right, //
  button012Right, //

  button002Aux, // 
  button003Aux, // 
  button004Aux, // 
  button005Aux, // 
  button006Aux, // 
  button007Aux, // 
  button008Aux, // 
  button009Aux, // 
  button010Aux, // 
  button011Aux, // 
  button012Aux, //
  

  button002Left, // 
  button003Left, // 
  button004Left, // 
  button005Left, // 
  button006Left, // 
  button007Left, // 
  button008Left, // 
  button009Left, // 
  button010Left, // 
  button011Left, //
  button012Left; // 
  
  public OI() {
    leftStick = new Joystick(RobotMap.leftStickPort);
    rightStick = new Joystick(RobotMap.rightStickPort);
    auxStick = new Joystick(RobotMap.auxPort);


    triggerLeft = new JoystickButton(leftStick, 1);
    triggerRight = new JoystickButton(rightStick, 1);
    triggerAux = new JoystickButton(auxStick, 1);
    button002Right = new JoystickButton(rightStick, 2);  
    button003Right = new JoystickButton(rightStick, 3); 
    button004Right = new JoystickButton(rightStick, 4); 
    button005Right = new JoystickButton(rightStick, 5); 
    button006Right = new JoystickButton(rightStick, 6); 
    button007Right = new JoystickButton(rightStick, 7); 
    button008Right = new JoystickButton(rightStick, 8); 
    button009Right = new JoystickButton(rightStick, 9); 
    button010Right = new JoystickButton(rightStick, 10); 
    button011Right = new JoystickButton(rightStick, 11); 
    button012Right = new JoystickButton(rightStick, 12); 


    button002Aux = new JoystickButton(auxStick, 2);  
    button003Aux = new JoystickButton(auxStick, 3); 
    button004Aux = new JoystickButton(auxStick, 4); 
    button005Aux = new JoystickButton(auxStick, 5); 
    button006Aux = new JoystickButton(auxStick, 6); 
    button007Aux = new JoystickButton(auxStick, 7); 
    button008Aux = new JoystickButton(auxStick, 8); 
    button009Aux = new JoystickButton(auxStick, 9); 
    button010Aux = new JoystickButton(auxStick, 10); 
    button011Aux = new JoystickButton(auxStick, 11); 
    button012Aux = new JoystickButton(auxStick, 12); 

    button002Left = new JoystickButton(leftStick, 2);  
    button003Left = new JoystickButton(leftStick, 3); 
    button004Left = new JoystickButton(leftStick, 4); 
    button005Left = new JoystickButton(leftStick, 5); 
    button006Left = new JoystickButton(leftStick, 6); 
    button007Left = new JoystickButton(leftStick, 7); 
    button008Left = new JoystickButton(leftStick, 8); 
    button009Left = new JoystickButton(leftStick, 9); 
    button010Left = new JoystickButton(leftStick, 10); 
    button011Left = new JoystickButton(leftStick, 11); 
    button012Left = new JoystickButton(leftStick, 12); 

    button002Aux.whenPressed(new BallManipulator()); 
    triggerLeft.whenPressed(new Elevator());
  }
}
