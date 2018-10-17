/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
<<<<<<< HEAD
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;
import frc.robot.commands.TankDrive;
import frc.robot.commands.auto.DriveStraight;
import frc.robot.commands.auto.Scare;
import frc.robot.commands.auto.TurnToVisionTarget;
import frc.robot.subsystems.DistanceSensor;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.PhotoSensor;
import frc.robot.util.Config;
=======
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.config.Config;
import frc.robot.subsystems.DriveTrain;
>>>>>>> First commit

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
<<<<<<< HEAD
	private final Config config = Config.createConfig();
	private final double ROBOT_WIDTH = config.getDouble("robot.width");
	private final double ROBOT_LENGTH = config.getDouble("robot.length");
	private final DriveTrain drivetrain = new DriveTrain(config);
	private final Intake intake = new Intake(config);
	private final DistanceSensor distance = new DistanceSensor(config, "distance");
	private final PhotoSensor frontPhoto = new PhotoSensor(config, "frontPhoto");
	private final PhotoSensor middlePhoto = new PhotoSensor(config, "middlePhoto");
	private final PhotoSensor backPhoto = new PhotoSensor(config, "backPhoto");
	private final Limelight limelight = new Limelight(config);
	private final NavX navx = new NavX(config);
	private OI oi;

	public double getROBOT_WIDTH() {
		return this.ROBOT_WIDTH;
	}

	public double getROBOT_LENGTH() {
		return this.ROBOT_LENGTH;
	}

	public DriveTrain getDrivetrain() {
		return this.drivetrain;
	}

	public NavX getNavx() {
		return this.navx;
	}

	public Intake getIntake() {
		return this.intake;
	}

	public Limelight getLimelight() {
		return this.limelight;
	}

	public PhotoSensor getFrontPhotoSensor() {
		return this.frontPhoto;
	}

	public PhotoSensor getMiddlePhotoSensor() {
		return this.middlePhoto;
	}

	public PhotoSensor getBackPhotoSensor() {
		return this.backPhoto;
	}

	public OI getOi() {
		return this.oi;
	}

	private Command auton;

=======
	public static final Config config = new Config("testConfig");
	public static final DriveTrain drivetrain = new DriveTrain(config);
	public static OI oi;
	
>>>>>>> First commit
	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI(config);
		reset();
<<<<<<< HEAD
		// auton = new ArcTurn(this, 60, -90, false);
		auton = new DriveStraight(this, 100);
		SmartDashboard.putData(navx);
		SmartDashboard.putData((DriveStraight) auton);
		SmartDashboard.putData("Drivetrain", drivetrain);
		SmartDashboard.putData("Distance", distance);
		SmartDashboard.putData("Front Photo", frontPhoto);
		SmartDashboard.putData("Middle Photo", middlePhoto);
		SmartDashboard.putData("Back Photo", backPhoto);
		System.out.println("Hello, World!");

		oi.bindCommandWhile("intakeIn", new IntakeIn(this));
		oi.bindCommandWhile("intakeOut", new IntakeOut(this));
	}

=======
	}
	
>>>>>>> First commit
	/**
	 * This function is called every robot packet, no matter the mode. Use this for
	 * items like diagnostics that you want ran during disabled, autonomous,
	 * teleoperated and test.
	 *
	 * <p>
	 * This runs after the mode specific periodic functions, but before LiveWindow
	 * and SmartDashboard integrated updating.
	 */
	@Override
	public void robotPeriodic() {
	}
<<<<<<< HEAD

=======
	
>>>>>>> First commit
	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {
		reset();
	}
<<<<<<< HEAD

=======
	
>>>>>>> First commit
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
<<<<<<< HEAD

=======
	
>>>>>>> First commit
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		reset();
<<<<<<< HEAD
		// new DriveStraight(100).start();
		// auton.start();
		new TurnToVisionTarget(this).start();
	}

=======
	}
	
>>>>>>> First commit
	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
<<<<<<< HEAD

	@Override
	public void teleopInit() {
		reset();
		new TankDrive(this).start();
	}

=======
	
	@Override
	public void teleopInit() {
		reset();
	}
	
>>>>>>> First commit
	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
<<<<<<< HEAD

=======
	
>>>>>>> First commit
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
<<<<<<< HEAD

	private void reset() {
		Scheduler.getInstance().removeAll();
		navx.zero();
		drivetrain.zero();
=======
	
	private void reset() {
		Scheduler.getInstance().removeAll();
>>>>>>> First commit
	}
}
