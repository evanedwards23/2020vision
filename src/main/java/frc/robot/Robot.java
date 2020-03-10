package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends TimedRobot
{
  public SpeedController m21, m22, m23, m24;
  public SpeedController m31, m32, s0, s1;

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
      m21 = new WPI_TalonSRX(21);
      m22 = new WPI_TalonSRX(22);
      SpeedControllerGroup tankLeft = new SpeedControllerGroup(m21, m22);

      m23 = new WPI_TalonSRX(23);
      m24 = new WPI_TalonSRX(24);
      SpeedControllerGroup tankRight = new SpeedControllerGroup(m23, m24);

      drivetrain = new DifferentialDrive(tankLeft, tankRight);
    }

    {     /* GRABBER MECH INIT */
      m31 = new WPI_VictorSPX(31);
      m31.setInverted(true);
      m32 = new WPI_VictorSPX(32);
      GrabberMotor launcher0 = new GrabberMotor(m31, 0L, 1.0, -0.4, 1.0);
      GrabberMotor launcher1 = new GrabberMotor(m32, 0L, 1.0, -0.4, 1.0);

      s0 = new Spark(0);
      s1 = new Spark(1);
      GrabberMotor windowAgitator = new GrabberMotor(s0, 250L, 1.0, -1.0, 1.0);
      GrabberMotor collector = new GrabberMotor(s1, 0L, 1.0, -1.0, 0.0);

      /*solenoid01 = new DoubleSolenoid(0, 1);
      solenoid23 = new DoubleSolenoid(2, 3);
      compressor = new Compressor(0);
      compressor.setClosedLoopControl(true);*/

      grabberMech = new GrabberMech(solenoid01, solenoid23, launcher0, launcher1, 
          windowAgitator, collector);
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
    drivetrain.arcadeDrive(-controller.getY(Hand.kLeft), controller.getX(Hand.kRight), true);
  }
}
