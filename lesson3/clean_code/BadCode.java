package com.bad.code2;

public class BadCode2 {

	public static void main(String[] args) {

		Qube qube = new Qube(1d, 1d, 1d, 10d);

		System.out.println("Qube volume: " + qube.getVolume());

		Square square = new Square(1d, 1d, 5d);

		System.out.println("Square perimeter: " + square.getArea());
	}

}
