package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class Robot extends TimedRobot
{
  public SpeedController m21, m22, m23, m24;

  public DoubleSolenoid solenoid01;
  public DoubleSolenoid solenoid23;
  public Compressor compressor;

  public XboxController controller;
  public GrabberMech grabberMech;

  @Override
  public void robotInit()
  {
    m21 = new WPI_TalonSRX(21);
    m22 = new WPI_TalonSRX(22);
    m23 = new WPI_TalonSRX(23);
    m24 = new WPI_TalonSRX(24);

    solenoid01 = new DoubleSolenoid(0, 1);
    solenoid23 = new DoubleSolenoid(2, 3);
    compressor = new Compressor(0);
    compressor.setClosedLoopControl(true);

    controller = new XboxController(0);
    grabberMech = new GrabberMech(solenoid01, solenoid23);
    grabberMech.setLaunchMode(true);
  }

  @Override
  public void robotPeriodic()
  {

  }

  @Override
  public void autonomousInit() 
  {
    
  }

  @Override
  public void autonomousPeriodic()
  {
    
  }

  @Override
  public void teleopPeriodic()
  {
    double speed = controller.getTriggerAxis(Hand.kRight) - controller.getTriggerAxis(Hand.kLeft);
    m21.set(speed);
    m22.set(-speed);

    if (controller.getAButtonReleased())
      grabberMech.toggleLaunchMode();
  }
}
