package com.example.demo.model;

public class RGBColor {

	private int redNumber;

	private int greenNumber;

	private int blueNumber;

	public RGBColor() {
	}

	public RGBColor(int redNumber, int greenNumber, int blueNumber) {
		this.redNumber = redNumber;
		this.greenNumber = greenNumber;
		this.blueNumber = blueNumber;
	}

	public int getRedNumber() {
		return redNumber;
	}

	public void setRedNumber(int redNumber) {
		this.redNumber = redNumber;
	}

	public int getGreenNumber() {
		return greenNumber;
	}

	public void setGreenNumber(int greenNumber) {
		this.greenNumber = greenNumber;
	}

	public int getBlueNumber() {
		return blueNumber;
	}

	public void setBlueNumber(int blueNumber) {
		this.blueNumber = blueNumber;
	}

}
