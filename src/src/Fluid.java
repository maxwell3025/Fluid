package src;

import java.util.Arrays;

public class Fluid {
	private float[] xVelocity;
	private float[] yVelocity;
	private float[] mass;
	private int width, height;

	public Fluid(int width, int height) {
		this.width = width;
		this.height = height;
		xVelocity = new float[width * height];
		yVelocity = new float[width * height];
		mass = new float[width * height];
		Arrays.fill(mass, 0.5f);
		for (int i = 0; i < 8196; i++) {
			yVelocity[(width * height) / 2 + i] = 1;
			mass[(width * height) / 2 + i] = 1;
		}
	}

	private float get(int x, int y, float[] array) {
		int xClamped = clamp(x, width);
		int yClamped = clamp(y, height);
		return array[xClamped + yClamped * width];
	}

	private float getInterpolated(float x, float y, float[] array) {
		int xFloor = (int) Math.floor(x);
		int yFloor = (int) Math.floor(y);
		int xCeil = xFloor + 1;
		int yCeil = yFloor + 1;
		float dx = x - xFloor;
		float dy = y - yFloor;
		float oo = get(xFloor, yFloor, array);
		float oi = get(xFloor, yCeil, array);
		float io = get(xCeil, yFloor, array);
		float ii = get(xCeil, yCeil, array);
		return lerp(lerp(oo, io, dx), lerp(oi, ii, dx), dy);
	}

	private void set(int x, int y, float[] array, float value) {
		int xClamped = clamp(x, width);
		int yClamped = clamp(y, height);
		array[xClamped + yClamped * width] = value;
	}

	public void update() {
		advect();
	}

	public float getMass(int x, int y) {
		return get(x, y, mass);
	}

	private void advect() {
		float[] xVelocityNext = new float[width * height];
		float[] yVelocityNext = new float[width * height];
		float[] massNext = new float[width * height];
		for (int i = 0; i < width * height; i++) {
			int x = i % width;
			int y = i / width;
			float xVel = get(x, y, xVelocity) / get(x, y, mass);
			float yVel = get(x, y, yVelocity) / get(x, y, mass);
			float xPos = x - xVel;
			float yPos = y - yVel;
			set(x, y, xVelocityNext, getInterpolated(xPos, yPos, xVelocity));
			set(x, y, yVelocityNext, getInterpolated(xPos, yPos, yVelocity));
			set(x, y, massNext, getInterpolated(xPos, yPos, mass));
		}
		xVelocity = xVelocityNext;
		yVelocity = yVelocityNext;
		mass = massNext;
	}

	private int clamp(int a, int b) {
		if (a > b - 1) {
			return b - 1;
		}
		if (a < 0) {
			return 0;
		}
		return a;

	}

	private float lerp(float a, float b, float i) {
		return a * (1 - i) + b * i;
	}

}
