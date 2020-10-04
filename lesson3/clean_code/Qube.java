package com.bad.code2;

public class Qube {

	Square square;
	
    public Qube(double s) {
        
    	//в конструкторе определяем базовые фигуры
    	square = new Square(s);
    }

    //расчет объема на основании базовой фигуры
    public Double getVolume() {
        return Math.pow(square.getEdge(), 3);
    }
}
