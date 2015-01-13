package project.world;

/**
 * @author Joshua S Abbott <joshuastevenabbott@gmail.com>
 * 
 */
public interface TimeServer {
	public double currentTime();

	public void enqueue(double waketime, Agent thing);

	public void run(double duration);

	public void reset();
}
