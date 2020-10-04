package com.bad.code2;

public class Square implements Shape{

    private Double edgeSize; 
    
    public Square(Double edgeSize) {
       
    	this.edgeSize = edgeSize;
    }
    
    public Double getEdge()
    {
    	return edgeSize;
    }
    
    @Override
    public Double getArea() {
        return edgeSize * edgeSize; 
    }

	@Override
	public Double getPerimeter() {
		return 4 * edgeSize;
	}

}
