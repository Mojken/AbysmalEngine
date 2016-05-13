package net.abysmal.engine.physics.misc;

import net.abysmal.engine.physics.VectorPhysics;

public class ForceArray {

	double walkForce, sprintForce, crouchForce, maxWalkSpeed, maxSprintSpeed, maxCrouchSpeed;

	public ForceArray(double walkForce, double sprintForce, double crouchForce, double maxWalkSpeed, double maxSprintSpeed, double maxCrouchSpeed) {
		this.walkForce = walkForce;
		this.sprintForce = sprintForce;
		this.crouchForce = crouchForce;
		this.maxWalkSpeed = maxWalkSpeed;
		this.maxSprintSpeed = maxSprintSpeed;
		this.maxCrouchSpeed = maxCrouchSpeed;
	}

	public double[] getMovementSpeeds() {
		double[] result = { maxWalkSpeed, maxSprintSpeed, maxCrouchSpeed };
		return result;
	}

	public double[] getMovementForces() {
		double[] result = { walkForce, sprintForce, crouchForce };
		return result;
	}

	public static ForceArray generateGenericForceArray(int mass) {
		return new ForceArray(VectorPhysics.fma(mass, 5), VectorPhysics.fma(mass, 7), VectorPhysics.fma(mass, .2), 1.5, 6.7, .5);
	}
}