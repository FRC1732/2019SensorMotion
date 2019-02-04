/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Robot;
import frc.robot.util.Integrator;

public class DriveForward extends Command implements Sendable {
	
	public final double TOLERANCE = 0.5;
	public double P = 0.00000005;// 0.0000001
	public double I = 0;
	public double D = 0;
	public double F = 0.160;
	private double distance;
	private final Robot robot;
	
	public DriveForward(Robot robot, double distance) {
		this.distance = distance;
		this.robot = robot;
		requires(robot.getDrivetrain());
		this.inter = new Integrator();
		timer = new Timer();
	}
	
	private Integrator inter;
	private Timer timer;
	private double lastT;
	private double lastE;
	
	private double getError() {
		return robot.getLimelight().getRange() - distance;
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println("Init Drivestraight");
		System.out.printf("%f -> %f\n", getError(), 0.0);
		inter.reset();
		timer.reset();
		timer.start();
		lastT = 0;
		lastE = getError();
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double error = getError();
		// error*= error * error;
		double deltaT = timer.get() - lastT;
		double deltaE = error - lastE;
		lastT = timer.get();
		lastE = error;
		double pid = D * (deltaE / deltaT) + I * inter.addSection(error, deltaT) + P * error + F * Math.signum(error);
		System.out.printf("%f -> %f\n", error, pid);
		robot.getDrivetrain().set(clamp(pid), clamp(pid));
		
		System.out.println("e:" + getError() + ", p:" + pid);
	}
	
	private double clamp(double d) {
		if (d > 1) {
			return 1;
		} else if (d < -1) {
			return -1;
		} else {
			return d;
		}
	}
	
	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("PIDController");
		builder.setSafeState(this::reset);
		builder.addDoubleProperty("p", this::getP, this::setP);
		builder.addDoubleProperty("i", this::getI, this::setI);
		builder.addDoubleProperty("d", this::getD, this::setD);
		builder.addDoubleProperty("f", this::getF, this::setF);
		builder.addDoubleProperty("setpoint", this::getSetpoint, this::setSetpoint);
		builder.addBooleanProperty("enabled", this::isEnabled, this::setEnabled);
	}
	
	private void reset() {
	}
	
	private double getP() {
		return P;
	}
	
	private double getI() {
		return I;
	}
	
	private double getD() {
		return D;
	}
	
	private double getF() {
		return F;
	}
	
	private void setP(double p) {
		P = p;
	}
	
	private void setI(double i) {
		I = i;
	}
	
	private void setD(double d) {
		D = d;
	}
	
	private void setF(double f) {
		F = f;
	}
	
	private double getSetpoint() {
		return distance;
	}
	
	private void setSetpoint(double a) {
		distance = a;
	}
	
	private boolean isEnabled() {
		return false;
	}
	
	private void setEnabled(boolean a) {
	}
	
	private int time = 0;
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if (Math.abs(getError()) < TOLERANCE) {
			time++;
		} else {
			time = 0;
		}
		return time >= 2;
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end() {
		robot.getDrivetrain().stop();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		robot.getDrivetrain().stop();
	}
}
