// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {
  double heading;

  private static final int kFrontLeftChannel = 5;
  private static final int kRearLeftChannel = 6;
  private static final int kFrontRightChannel = 7;
  private static final int kRearRightChannel = 8;

  //private static final int elevatorChannel = 5;

  //private static final int rightArmChannel = 6;
  //private static final int leftArmChannel = 7;

  private static final int kJoystickChannel = 1;

  //private PWMSparkMax elevacionMotor = new PWMSparkMax(elevatorChannel);

  //private PWMSparkMax righMotorArm = new PWMSparkMax(rightArmChannel);
  //private PWMSparkMax leftMotorArm = new PWMSparkMax(leftArmChannel);

  private MecanumDrive m_robotDrive;
  private XboxController m_stick;
  private ADXRS450_Gyro gryro;
  //private Timer time = new Timer();



  @Override
  public void robotInit() {
    //DriveTrain Declaration
    PWMSparkMax frontLeft = new PWMSparkMax(kFrontLeftChannel);
    PWMSparkMax rearLeft = new PWMSparkMax(kRearLeftChannel);

    PWMSparkMax frontRight = new PWMSparkMax(kFrontRightChannel);
    PWMSparkMax rearRight = new PWMSparkMax(kRearRightChannel);
    frontRight.setInverted(true);
    rearRight.setInverted(true);

    gryro = new ADXRS450_Gyro();


    // Invert the right side motors.
    // You may need to change or remove this to match your robot.

    m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
    m_stick = new XboxController(kJoystickChannel);

    gryro.calibrate();

  }

  @Override
  public void autonomousInit() {
    gryro.reset();
  }

  /*
  @Override
  public void autonomousPeriodic() {
    if(time.hasElapsed(3)){
      m_robotDrive.driveCartesian(0.5, 0, 0, gryro.getAngle());
    }
    else{
      m_robotDrive.driveCartesian(0.5, 0.5, 0.5, gryro.getAngle());
    }
  }
  */

  @Override
  public void teleopPeriodic() {
    gryro.reset();
    // Use the joystick X axis for lateral movement, Y axis for forward
    // movement, and Z axis for rotation.
    m_robotDrive.driveCartesian(m_stick.getRawAxis(0), m_stick.getRawAxis(1), m_stick.getRawAxis(2), gryro.getAngle());

    if(m_stick.getYButton())
    {
      gryro.calibrate();
    }

    /*
    if(m_stick.getRightBumperPressed()){
      PullArm();
    }
    else if(m_stick.getRightBumperReleased()){
      StopArm();
    }
    */

    
    /*
    if(m_stick.getRightTriggerAxis() > 0.3)
    {
      elevacionMotor.set(m_stick.getRightTriggerAxis()*0.5);
    }
    if(m_stick.getLeftTriggerAxis() > 0.3)
    {
      elevacionMotor.set(m_stick.getLeftTriggerAxis()*(-0.5));
    }
    */

  }

  /*
  public void PullArm(){
    righMotorArm.set(1);
    leftMotorArm.set(-1);
  }

  public void StopArm(){
    righMotorArm.stopMotor();
    leftMotorArm.stopMotor();
  }

  public void ElevatorClimbUp(){
    elevacionMotor.set(1);
  }

  public void ElevatorClimbDown(){
    elevacionMotor.set(-1);
  }

  public void ElevatorStopClimb(){
    elevacionMotor.stopMotor();
  }
  */
}
