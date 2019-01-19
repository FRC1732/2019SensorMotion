/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.util.Config;

/**
 * Add your docs here.
 */
public class PhotoSensor extends Subsystem implements Sendable {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private DigitalInput di;
  private boolean inverted = false;
  private final double transform = 1;

  public PhotoSensor(Config c, String sensor) {
    di = new DigitalInput(c.getInt("sensors." + sensor + ".port"));
    inverted = c.getBool("sensors." + sensor + ".inverted");
  }

  public boolean getOn() {
    if (inverted) {
      return !di.get();
    } else {
      return di.get();
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("Digital Input");
    builder.addBooleanProperty("Value", this::getOn, null);
  }
}
