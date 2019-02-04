/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.util.Config;

/**
 * Add your docs here.
 */
public class Limelight extends Subsystem implements Sendable {
  private final double heightDiffrence = 7.75;
  private final double angleCorrection = 4.2891533288190184377210661681454 - 5.074424743652344;
  
  private double yAngle;
  private NetworkTableEntry yAngleNet;
  
  private double x1;
  private NetworkTableEntry x1Net;
  private double y1;
  private NetworkTableEntry y1Net;
  private double a1;
  private NetworkTableEntry a1Net;
  
  private double x2;
  private NetworkTableEntry x2Net;
  private double y2;
  private NetworkTableEntry y2Net;
  private double a2;
  private NetworkTableEntry a2Net;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  public double targetHorizontal() {
    return (x1 + x2) / 2;
  }
  
  public double targetVertical() {
    return (y1 + y2) / 2;
  }
  
  public Limelight(Config c) {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    x1Net = table.getEntry("tx0");
    y1Net = table.getEntry("ty0");
    a1Net = table.getEntry("ta0");
    x2Net = table.getEntry("tx1");
    y2Net = table.getEntry("ty1");
    a2Net = table.getEntry("ta1");
    yAngleNet = table.getEntry("ty");
  }
  
  @Override
  public void periodic() {
    x1 = x1Net.getDouble(0);
    y1 = y1Net.getDouble(0);
    a1 = a1Net.getDouble(0);
    x2 = x2Net.getDouble(0);
    y2 = y2Net.getDouble(0);
    a2 = a2Net.getDouble(0);
    yAngle = yAngleNet.getDouble(0);
  }
  
  public double getRange() {
    return heightDiffrence / Math.tan(Math.toRadians(yAngle + angleCorrection));
  }
  
  public double getAngle() {
    return yAngle;
  }
  
  @Override
  public void initDefaultCommand() {
  }
  
  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("Limelight");
    builder.addDoubleProperty("Distance", this::getRange, null);
    builder.addDoubleProperty("Angle", this::getAngle, null);
  }
}
