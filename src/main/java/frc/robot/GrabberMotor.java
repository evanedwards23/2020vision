package frc.robot;

import java.time.Duration;
import java.time.Instant;

import edu.wpi.first.wpilibj.SpeedController;

class GrabberMotor
{
    public SpeedController controller;
    public long coldStart;

    public double scale;
    public double intakeScale;
    public double launchScale;

    private Instant lastZeroFrame = Instant.now();

    public GrabberMotor(SpeedController controller, long coldStart, double scale,
            double intakeScale, double launchScale)
    {
        this.controller = controller;
        this.coldStart = coldStart;

        this.scale = scale;
        this.intakeScale = intakeScale;
        this.launchScale = launchScale;
    }

    public void set(double speed, boolean launchMode)
    {
        if (speed == 0)
            lastZeroFrame = Instant.now();
        else
        {
            long delta = Duration.between(lastZeroFrame, Instant.now()).toMillis();

            if (delta > coldStart)
            {
                controller.set(speed * scale * (launchMode ? launchScale : intakeScale));
                return;
            }
        }

        controller.set(0);
    }
}