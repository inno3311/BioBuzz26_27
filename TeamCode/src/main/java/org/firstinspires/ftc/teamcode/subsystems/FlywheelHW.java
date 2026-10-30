package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

//import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.Sorter;
public class FlywheelHW
{

   @Configurable
   public static class FlyWheelConfigurables {
      @Sorter(sort = 0)
      public static double kP = 0.0;

      @Sorter(sort = 1)
      public static double kI = 0.0;

      @Sorter(sort = 2)
      public static double kD = 0.0;
   }

   String m_name;

   public OpMode m_OpMode = null;

   public double m_power = 0;


   private DcMotorEx m_flywheel;


   public FlywheelHW(OpMode opMode, String name)
   {
      //super(opMode);
      this.m_name = name;
      this.m_OpMode = opMode;
      init();
   }

   public void init()
   {
      m_OpMode.telemetry.log().add("Adding: ", m_name);
      m_flywheel = m_OpMode.hardwareMap.get(DcMotorEx.class, m_name);

      m_flywheel.setDirection(DcMotorSimple.Direction.REVERSE);
   }

   public void shoot()
   {
      m_flywheel.setPower(1);
   }

   public void increase_power()
   {
      m_power = m_power + 0.1;
      m_flywheel.setPower(m_power);
   }

   public void decrease_power()
   {
      m_power = m_power - 0.1;
      m_flywheel.setPower(m_power);
   }

   public void stop()
   {
      m_flywheel.setPower(0);
   }

   public void set_power(double power)
   {
      m_power = power;
      m_flywheel.setPower(m_power);
   }

   public double getPower()
   {
      return m_power;
   }

   public double getVelocity() { return m_flywheel.getVelocity();}

//   public double setVelocity(double vel) {
//      m_flywheel.setVelocity(1400);
//   }

}
