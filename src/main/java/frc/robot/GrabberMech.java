package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class GrabberMech
{
    public DoubleSolenoid leftAct, rightAct;

    public boolean launchMode = false;

    public GrabberMech(DoubleSolenoid leftAct, DoubleSolenoid rightAct)
    {
        this.leftAct = leftAct;
        this.rightAct = rightAct;
    }

    public boolean toggleLaunchMode()
    {
        setLaunchMode(!launchMode);
        return launchMode;
    }

    public void setLaunchMode(boolean launchMode)
    {
        this.launchMode = launchMode;
        leftAct.set(launchMode ? Value.kReverse : Value.kForward);
        rightAct.set(launchMode ? Value.kReverse : Value.kForward);
    }
}