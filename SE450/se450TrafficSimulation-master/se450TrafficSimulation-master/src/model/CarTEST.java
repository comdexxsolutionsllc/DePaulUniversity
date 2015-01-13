package model;

import properties.PropertyBag;
import model.Data.Orientation;
import random.Util;
import junit.framework.Assert;
import junit.framework.TestCase;

@SuppressWarnings("deprecation")
public class CarTEST extends TestCase {
	
	PropertyBag propertyBag = PropertyBag.makePropertyBag();
	Car c1 = new Car(Orientation.EW);
	Road r1 = new Road();
	Road r2 = new Road();
	
	public CarTEST(String name) {
		super(name);
	}

	public void testConstructorAndAttributes() {
		c1 = new Car(Orientation.EW);
		r1 = new Road();
		Assert.assertEquals(c1.getCurrentRoad(),null);
		
		Assert.assertTrue(c1.getBrakeDistance() <= propertyBag.getCarBrakeDistanceMax());
		Assert.assertTrue(c1.getBrakeDistance() >= propertyBag.getCarBrakeDistanceMin());
		Assert.assertTrue(c1.getLength() <= propertyBag.getCarLengthMax());
		Assert.assertTrue(c1.getLength() >= propertyBag.getCarLengthMin());
		Assert.assertTrue(c1.getMaxVelocity() <= propertyBag.getCarMaxVelocityMax());
		Assert.assertTrue(c1.getMaxVelocity() >= propertyBag.getCarMaxVelocityMin());
		Assert.assertTrue(c1.getStopDistance() <= propertyBag.getCarBrakeDistanceMax());
		Assert.assertTrue(c1.getStopDistance() >= propertyBag.getCarStopDistanceMin());
		Assert.assertEquals(c1.getFrontPosition(), 0.0);
		Assert.assertEquals(c1.getTimeStep(), propertyBag.getTimeStep());
	}
	
	public void testCurrentRoad() {
		c1 = new Car(Orientation.EW);
		r1 = new Road();
		Assert.assertEquals(c1.getCurrentRoad(),null);
		r1.accept(c1, 0.0);
		Assert.assertEquals(r1, c1.getCurrentRoad());
		c1.setCurrentRoad(r2);
		Assert.assertEquals(r2, c1.getCurrentRoad());
	}
	
	public void testPositionsAndLength() {
		c1 = new Car(Orientation.EW);
		r1 = new Road();
		r1.accept(c1,0.0);
		Assert.assertTrue(Util.isEquals(-c1.getBackPosition(), c1.getLength()));
		Assert.assertTrue(Util.isEquals(c1.getFrontPosition(), 0.0));
		c1.setFrontPosition(15.0);
		Assert.assertTrue(Util.isEquals(c1.getFrontPosition(), 15.0));
	}
	
	public void testVelocity() {
		c1 = new Car(Orientation.EW);
		r1 = new Road();
		Sink s1 = new Sink();
		r1.setNextRoad(s1);
		r1.accept(c1, c1.getFrontPosition());
		c1.run(1);
		Assert.assertTrue(c1.getFrontPosition() > 0);	
	}
}
