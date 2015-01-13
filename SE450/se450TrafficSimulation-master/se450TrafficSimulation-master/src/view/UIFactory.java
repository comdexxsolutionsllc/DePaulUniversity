package view;

public class UIFactory {
  private UIFactory() {}
  static private UI _UI = new TextUI();
  static public UI ui () {
    return _UI;
  }
}
