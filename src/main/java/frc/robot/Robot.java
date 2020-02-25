package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends TimedRobot
{
  public SpeedController m21, m22, m23, m24;

  public DoubleSolenoid solenoid01;
  public DoubleSolenoid solenoid23;
  public Compressor compressor;

  public XboxController controller;
  public GrabberMech grabberMech;
  public DifferentialDrive drivetrain;

  @Override
  public void robotInit()
  {
    {     /* DRIVETRAIN INIT */
      m23 = new WPI_TalonSRX(23);
      m24 = new WPI_TalonSRX(24);
      SpeedControllerGroup tankLeft = new SpeedControllerGroup(m23/*, . . . */);
      SpeedControllerGroup tankRight = new SpeedControllerGroup(m24/*, . . . */);
      drivetrain = new DifferentialDrive(tankLeft, tankRight);
    }

    {     /* GRABBER MECH INIT */
      m21 = new WPI_TalonSRX(21);
      m22 = new WPI_TalonSRX(22);
      m22.setInverted(true);

      solenoid01 = new DoubleSolenoid(0, 1);
      solenoid23 = new DoubleSolenoid(2, 3);
      compressor = new Compressor(0);
      compressor.setClosedLoopControl(true);

      grabberMech = new GrabberMech(solenoid01, solenoid23, m21, m22);
      grabberMech.setLaunchMode(true);
    }

    controller = new XboxController(0);
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
    grabberMech.updateControls(controller);
    drivetrain.arcadeDrive(controller.getY(Hand.kLeft), -controller.getX(Hand.kRight), true);
  }
}
