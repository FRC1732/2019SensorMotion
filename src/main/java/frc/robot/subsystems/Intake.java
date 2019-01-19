/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.util.Config;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  public static final String PATH = "intake";
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private VictorSPX left;
  private VictorSPX right;

  public Intake(Config config) {
    left = config.createVictor(PATH + ".left");
    right = config.createVictor(PATH + ".right");
  }

  public void enable(boolean in) {
    if (in) {
      left.set(ControlMode.PercentOutput, 1);
      right.set(ControlMode.PercentOutput, 1);
    } else {
      left.set(ControlMode.PercentOutput, -1);
      right.set(ControlMode.PercentOutput, -1);
    }
  }

  public void disable() {
    left.set(ControlMode.PercentOutput, 0);
    right.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
