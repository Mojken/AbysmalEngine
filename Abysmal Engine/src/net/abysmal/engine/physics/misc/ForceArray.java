package net.abysmal.engine.physics.misc;

import net.abysmal.engine.physics.Gravity;
import net.abysmal.engine.physics.VectorPhysics;

public class ForceArray {

	double walkForce, sprintForce, crouchForce, maxWalkSpeed, maxSprintSpeed, maxCrouchSpeed, jumpForce;

	public ForceArray(double walkForce, double sprintForce, double crouchForce, double maxWalkSpeed, double maxSprintSpeed, double maxCrouchSpeed, double jumpForce) {
		this.walkForce = walkForce;
		this.sprintForce = sprintForce;
		this.crouchForce = crouchForce;
		this.maxWalkSpeed = maxWalkSpeed;
		this.maxSprintSpeed = maxSprintSpeed;
		this.maxCrouchSpeed = maxCrouchSpeed;
		this.jumpForce = jumpForce;
	}

	public double[] getMovementSpeeds() {
		double[] result = { maxWalkSpeed, maxSprintSpeed, maxCrouchSpeed };
		if (!result.equals(null)) return result;
		System.err.println("MovementSpeeds not set up yet!");
		System.exit(0);
		return new double[] {};
	}

	public double[] getMovementForces() {
		double[] result = { walkForce, sprintForce, crouchForce };
		return result;
	}
	
	public double getJumpForce() {
		return jumpForce;
	}

	public static ForceArray generateGenericForceArray(float mass) {
		return new ForceArray(VectorPhysics.fma(mass, 5), VectorPhysics.fma(mass, 7), VectorPhysics.fma(mass, .2), 1.5, 6.7, .5, VectorPhysics.fma(mass, Math.sqrt(2 * Gravity.gravity * 8000)));
	}
}