package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class GrabberMech
{
    public DoubleSolenoid leftAct, rightAct;
    public GrabberMotor[] intakes;

    public boolean launchMode = false;

    public GrabberMech(DoubleSolenoid leftAct, DoubleSolenoid rightAct, GrabberMotor ... intakes)
    {
        this.leftAct = leftAct;
        this.rightAct = rightAct;
        this.intakes = intakes;
    }

    public void updateControls(XboxController controller)
    {
        double speed = controller.getTriggerAxis(Hand.kRight) - controller.getTriggerAxis(Hand.kLeft);
        setIntakeSpeed(speed);
        if (controller.getAButtonReleased())
          toggleLaunchMode();
    }

    public boolean toggleLaunchMode()
    {
        setLaunchMode(!launchMode);
        return launchMode;
    }

    public void setLaunchMode(boolean launchMode)
    {
        this.launchMode = launchMode;
        //leftAct.set(launchMode ? Value.kReverse : Value.kForward);
        //rightAct.set(launchMode ? Value.kReverse : Value.kForward);
    }

    public void setIntakeSpeed(double speed)
    {
        for (GrabberMotor intake : intakes)
            intake.set(speed, launchMode);
    }
}