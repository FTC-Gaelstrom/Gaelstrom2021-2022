package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Hardware{
    //Create Drivetrain Motors
    public DcMotor frontRightMotor = null;
    public DcMotor frontLeftMotor = null;
    public DcMotor backRightMotor = null;
    public DcMotor backLeftMotor = null;

    //Create Shooter Motors
    public DcMotor shooterFrontMotor = null;
    public DcMotor shooterBackMotor = null;

    //Create Loader Servo
    public Servo loaderServo = null;

    //Additional Variables
    HardwareMap hardwareMap = null;
    public ElapsedTime runtime = new ElapsedTime();

    public Hardware(HardwareMap hwMap) {
        initialize(hwMap);
    }

    private void initialize(HardwareMap hwMap){
        hardwareMap = hwMap;

        //Connect Drive Train Motors
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class,"frontLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class,"backRightMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class,"backLeftMotor");

        //Connect Shooter Motors
        shooterFrontMotor = hardwareMap.get(DcMotor.class,"shooterFrontMotor");
        shooterBackMotor  = hardwareMap.get(DcMotor.class,"shooterBackMotor");

        //Connect Loader Servo
        loaderServo = hardwareMap.get(Servo.class,"loaderServo");

        //Set Up Motor Direction
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        shooterFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        shooterBackMotor.setDirection(DcMotor.Direction.REVERSE);

        //Set Motor Mode  (For now we will run it without an encoder, but when we do stop_and_reset_encoder for each motor and then run_using_encoder for each motor)
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //Set ZERO POWER BEHAVIOR for Drive Train as BRAKE so that the motors stop turning
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Set ZERO POWER BEHAVIOR for Shooters as FREESPIN so that the motor continue to spin freely
        shooterFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //Set Motors to use no power
        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        shooterFrontMotor.setPower(0);
        shooterBackMotor.setPower(0);

// I'm here!

    }
}
