package project.world;

/**
 * @author Joshua S Abbott <joshuastevenabbott@gmail.com>
 * 
 */
public interface CarAcceptor {
	public boolean accept(Car c, double frontPosition);

	public double distanceToObstacle(Car c, double fromPosition);

}
