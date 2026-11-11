package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class IntakeHW
{

   // Name used for the hardware map and general ID.
   private String m_name;

   // Save a copy of the OpMode to gain access to telemetry, hardwareMap and controllers
   private OpMode m_OpMode = null;

   // The DcMotor that drives the intake.
   private DcMotor m_intake;


   /**
    * Constructor
    * @param opMode Robot OpMode object
    * @param name Name of the Mapped hardware device
    */
   public IntakeHW(OpMode opMode, String name)
   {
      //super(opMode);
      this.m_name = name;
      this.m_OpMode = opMode;
      init();
   }

   /**
    * Init the hardware device
    */
   public void init()
   {
      m_OpMode.telemetry.log().add("Adding: ", m_name);
      m_intake = m_OpMode.hardwareMap.get(DcMotor.class, m_name);

      m_intake.setDirection(DcMotorSimple.Direction.REVERSE);
   }

   /////////////////////////////////
   ///  Interface Controls
   /////////////////////////////////



   public void intake()
   {
      m_intake.setPower(-1);
   }
   public void outtake()
   {
      m_intake.setPower(1);
   }

   public void stop()
   {
      m_intake.setPower(0);
   }
   public double getPower()
   {
      return m_intake.getPower();
   }


   /////////////////////////////////
   ///  Commands Functions
   /////////////////////////////////


}
