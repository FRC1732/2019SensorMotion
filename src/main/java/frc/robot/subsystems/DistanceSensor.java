/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.util.Config;

/**
 * Add your docs here.
 */
public class DistanceSensor extends Subsystem implements Sendable {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private AnalogInput ai;
  private final double transform;

  public DistanceSensor(Config c, String sensor) {
    ai = new AnalogInput(c.getInt("sensors." + sensor + ".port"));
    transform = c.getDouble("sensors." + sensor + ".transform");
  }

  public double getDist() {
    return ai.getValue() * transform;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("Analog Input");
    builder.addDoubleProperty("Value", this::getDist, null);
  }
}
