package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class FlywheelHW
{

   String m_name;

   public OpMode m_OpMode = null;

   public double m_power = 0;


   private DcMotor m_flywheel;


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

}
