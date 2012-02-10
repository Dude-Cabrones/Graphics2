package impl;

import java.awt.Color;

public class MyColor extends java.awt.Color {
	public MyColor(int r, int g, int b) {
		super(r, g, b);
		// TODO Auto-generated constructor stub
	}
	public MyColor( Color c ) {
		super(c.getRed(), c.getGreen(), c.getBlue());
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MyColor mix( MyColor color, float ratio ) {
		if( ratio > 1 ) {
			ratio = 1;
		}
		return new MyColor( 
			(int)( this.getRed() * (1.0F-ratio) + color.getRed() * ratio ),
			(int)( this.getGreen() * (1.0F-ratio) + color.getGreen() * ratio ),
			(int)( this.getBlue() * (1.0F-ratio) + color.getBlue() * ratio )
		);
	}
	public MyColor mix( MyColor color ) {
		return mix( color, 0.5F );
	}
	public MyColor add( MyColor color ) {
		int red = this.getRed() + color.getRed();
		if( red > 255 ) red = 255;
		int green = this.getGreen() + color.getGreen();
		if( green > 255 ) green = 255;
		int blue = this.getBlue() + color.getBlue();
		if( blue > 255 ) blue = 255;

		return new MyColor(red, green, blue);
	}
	public MyColor sub( MyColor color ) {
		int red = this.getRed() - color.getRed();
		if( red < 0 ) red = 0;
		int green = this.getGreen() - color.getGreen();
		if( green < 0 ) green = 0;
		int blue = this.getBlue() - color.getBlue();
		if( blue < 0 ) blue = 0;

		return new MyColor(red, green, blue);
	}
	public MyColor dim( float val ) {
		if( val > 1 ) val = 1;
		if( val < 0 ) val = 0;
		int red = (int)(this.getRed() * val);
		int green = (int)(this.getGreen() * val);
		int blue = (int)(this.getBlue() * val);

		return new MyColor(red, green, blue);
	}
}
