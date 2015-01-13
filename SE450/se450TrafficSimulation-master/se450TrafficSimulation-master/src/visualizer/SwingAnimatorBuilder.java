package visualizer;


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import visualizer.AnimatorBuilder;
import model.CarAcceptor;
import model.RoadEnd;
import model.Vehicle;
import visualizer.Animator;
import visualizer.SwingAnimator;
import visualizer.SwingAnimatorPainter;

/** 
 * A class for building Animators.
 */
public class SwingAnimatorBuilder implements AnimatorBuilder {
  MyPainter _painter;
  public SwingAnimatorBuilder() {
    _painter = new MyPainter();
  }
  public Animator getAnimator() {
    if (_painter == null) { throw new IllegalStateException(); }
    Animator returnValue = new SwingAnimator(_painter, "Traffic Simulator",
                                             VP.displayWidth, VP.displayHeight, VP.displayDelay);
    _painter = null;
    return returnValue;
  }
  private static double skipInit = VP.gap;
  private static double skipRoad = VP.gap + MP.roadLength;
  private static double skipCar = VP.gap + VP.elementWidth;
  private static double skipRoadCar = skipRoad + skipCar;
  public void addLight(RoadEnd d, int i, int j) {
    double x = skipInit + skipRoad + j*skipRoadCar;
    double y = skipInit + skipRoad + i*skipRoadCar;
    Translator t = new TranslatorWE(x, y, MP.carLength, VP.elementWidth, VP.scaleFactor);
    _painter.addLight(d,t);
  }
  public void addHorizontalRoad(CarAcceptor l, int i, int j, boolean eastToWest) {
    double x = skipInit + j*skipRoadCar;
    double y = skipInit + skipRoad + i*skipRoadCar;
    Translator t = eastToWest ? new TranslatorEW(x, y, MP.roadLength, VP.elementWidth, VP.scaleFactor)
                              : new TranslatorWE(x, y, l.getEndPosition(), VP.elementWidth, VP.scaleFactor);
    _painter.addRoad(l,t);
  }
  public void addVerticalRoad(CarAcceptor l, int i, int j, boolean southToNorth) {
    double x = skipInit + skipRoad + j*skipRoadCar;
    double y = skipInit + i*skipRoadCar;
    Translator t = southToNorth ? new TranslatorSN(x, y, MP.roadLength, VP.elementWidth, VP.scaleFactor)
                                : new TranslatorNS(x, y, l.getEndPosition(), VP.elementWidth, VP.scaleFactor);
    _painter.addRoad(l,t);
  }


  /** Class for drawing the Model. */
  private static class MyPainter implements SwingAnimatorPainter {

    /** Pair of a model element <code>x</code> and a translator <code>t</code>. */
    private static class Element<T> {
      T x;
      Translator t;
      Element(T x, Translator t) {
        this.x = x;
        this.t = t;
      }
    }
    
    private List<Element<CarAcceptor>> _roadElements;
    private List<Element<RoadEnd>> _lightElements;
    MyPainter() {
      _roadElements = new ArrayList<Element<CarAcceptor>>();
      _lightElements = new ArrayList<Element<RoadEnd>>();
    }    
    void addLight(RoadEnd d, Translator t) {
      _lightElements.add(new Element<RoadEnd>(d,t));
    }
    void addRoad(CarAcceptor x, Translator t) {
      _roadElements.add(new Element<CarAcceptor>(x,t));
    }
    
    public void paint(Graphics g) {
      // This method is called by the swing thread, so may be called
      // at any time during execution...

      // First draw the background elements
      for (Element<RoadEnd> e : _lightElements) {
        if (e.x.getLight().getState()) {
          g.setColor(Color.BLUE);
        } else {
          g.setColor(Color.YELLOW);
        }
        XGraphics.fillOval(g, e.t, 0, 0, MP.carLength, VP.elementWidth);
      }
      g.setColor(Color.BLACK);
      for (Element<CarAcceptor> e : _roadElements) {
        XGraphics.fillRect(g, e.t, 0, 0, MP.roadLength, VP.elementWidth);
      }
      
      // Then draw the foreground elements
      for (Element<CarAcceptor> e : _roadElements) {
        // iterate through a copy because e.x.getCars() may change during iteration...
        for (Vehicle d : e.x.getCars().toArray(new Vehicle[0])) {
          g.setColor(d.getColor());
          XGraphics.fillOval(g, 
        		  e.t, 
        		  //(MP.roadLength * d.getRoadSegmentsTraversed()) + 
        		  ((d.getFrontPosition() * (MP.roadLength / d.getCurrentRoad().getEndPosition())) - d.getLength() * (MP.roadLength / d.getCurrentRoad().getEndPosition())), 
        		  0, 
        		  d.getLength(), 
        		  VP.elementWidth);
        }
      }
    }
  }
}
