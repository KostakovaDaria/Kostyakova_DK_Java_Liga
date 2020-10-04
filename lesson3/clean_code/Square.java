package com.bad.code2;

public class Square implements Shape2D {

	private Double x;
	private Double y;
	private Double edgeSize;

	public Square(Double x, Double y, Double edgeSize) {

		this.x = x;
		this.y = y;
		this.edgeSize = edgeSize;
	}

	/**
	 * Возвращает центральную точку фигуры по оси Х
	 * 
	 * @return центральная точка по оси Х
	 */

	@Override
	public Double getX() {
		return x;
	}

	/**
	 * Возвращает центральную точку фигуры по оси Y
	 * 
	 * @return центральная точка по оси Y
	 */
	
	@Override
	public Double getY() {
		return y;
	}

	/**
	 * Возвращает значение стороны фигуры
	 * 
	 * @return значение стороны фигуры
	 */
	
	public Double getEdge() {
		return edgeSize;
	}

	/**
	 * Возвращает площадь фигуры
	 * 
	 * @return площадь фигуры
	 */
	
	@Override
	public Double getArea() {
		return edgeSize * edgeSize;
	}
}
