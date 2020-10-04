package com.bad.code2;

public class Qube implements Shape3D {

	private Double x;
	private Double y;
	private Double z;
	private Double edgeSize;

	public Qube(Double centerX, Double centerY, Double centerZ, Double s) {
		
		this.x = centerX;
		this.y = centerY;
		this.z = centerZ;
		this.edgeSize = s;
	}

	/**
	 * Возвращает центральную точку фигуры по оси Х
	 * 
	 * @return центральная точка фигуры по оси Х
	 */
	
	@Override
	public Double getX() {
		return x;
	}

	/**
	 * Возвращает центральную точку фигуры по оси Y
	 * 
	 * @return центральная точка фигуры по оси Y
	 */
	
	@Override
	public Double getY() {
		return y;
	}

	/**
	 * Возвращает центральную точку фигуры по оси Z
	 * 
	 * @return центральная точка фигуры по оси Z
	 */
	
	@Override
	public Double getZ() {
		return z;
	}

	/**
	 * Возвращает значение объема фигуры
	 * 
	 * @return значение объема фигуры
	 */
	
	@Override
	public Double getVolume() {
		return Math.pow(edgeSize, 3);
	}

}
