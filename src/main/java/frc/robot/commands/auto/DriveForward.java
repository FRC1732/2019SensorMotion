/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class DriveForward extends PIDCommand {
	
	private final Robot robot;
	
	private PIDController angle;
	
	public DriveForward(Robot robot, double distance) {
		super("DriveForward", 0.005, 0, 0, 0.2);
		getPIDController().setF(0.0);
		getPIDController().setSetpoint(distance);
		getPIDController().setAbsoluteTolerance(0.5);
		getPIDController().setOutputRange(-1, 1);
		getPIDController().setContinuous(false);
		
		angle = new PIDController(0.005, 0, 0, new PIDSource() {
			
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				
			}
			
			@Override
			public double pidGet() {
				if (robot.getLimelight().getRange() > 70) {
					return 0;
				}
				return Math.tan(Math.toRadians(-robot.getLimelight().getXAngle())) * robot.getLimelight().getRange();
			}
			
			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
		}, (d) -> {
		});
		angle.setF(0.0);
		angle.setOutputRange(-0.3, 0.3);
		angle.setContinuous(false);
		angle.setSetpoint(0.0);
		
		SmartDashboard.putData("AngleTurning", angle);
		this.robot = robot;
		requires(robot.getDrivetrain());
	}
	
	@Override
	protected double returnPIDInput() {
		return robot.getLimelight().getRange();
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println("Drive to distance " + getPIDController().getSetpoint() + " from the vision target");
		System.out.println("Init drive forward");
		// System.out.printf("%f -> %f\n", returnPIDInput(), 0.0);
		getPIDController().reset();
		getPIDController().enable();
		angle.reset();
		angle.enable();
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		// double error = getError();
		// // error*= error * error;
		// double deltaT = timer.get() - lastT;
		// double deltaE = error - lastE;
		// lastT = timer.get();
		// lastE = error;
		// double pid = D * (deltaE / deltaT) + I * inter.addSection(error, deltaT) + P
		// * error + F * Math.signum(error);
		// System.out.printf("%f -> %f\n", error, pid);
	}
	
	@Override
	protected void usePIDOutput(double pid) {
		pid = (Math.signum(pid) * 0.16) + pid;
		// double angle = robot.getLimelight().getXAngle() * 0.001;
		robot.getDrivetrain().set(pid + angle.get(), pid - angle.get());
		
		System.out.printf("%03.5f -> %04.4f @ angle: %04.4f\n", returnPIDInput(), pid, angle.get());
	}
	
	private int time = 0;
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if (getPIDController().onTarget()) {
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
		angle.disable();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		robot.getDrivetrain().stop();
		angle.disable();
	}
}
