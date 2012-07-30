package com.poetry.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name="image" )
public class ImageAnalysis
{
	
	@Id
	protected String id;
	
	protected double red;
	
	protected double green;
	
	protected double blue;
	
	protected double intensity;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the red
	 */
	public double getRed() {
		return red;
	}

	/**
	 * @param red the red to set
	 */
	public void setRed(double red) {
		this.red = red;
	}

	/**
	 * @return the green
	 */
	public double getGreen() {
		return green;
	}

	/**
	 * @param green the green to set
	 */
	public void setGreen(double green) {
		this.green = green;
	}

	/**
	 * @return the blue
	 */
	public double getBlue() {
		return blue;
	}

	/**
	 * @param blue the blue to set
	 */
	public void setBlue(double blue) {
		this.blue = blue;
	}

	/**
	 * @return the intensity
	 */
	public double getIntensity() {
		return intensity;
	}

	/**
	 * @param intensity the intensity to set
	 */
	public void setIntensity(double intensity) {
		this.intensity = intensity;
	}
	
	@Override
	public String toString() {
		return "Image[" + red + ", " + green + ", " + blue + ":" + intensity + "]";
	}

}
