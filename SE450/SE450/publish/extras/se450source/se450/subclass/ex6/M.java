package subclass.ex6;

public class M {
  public static void main(String[] argv) {
    (new B()).m();
  }
}
class A {
  void p() {
    System.out.println("A.p: " + _x); // which _x?
  }
  final int _x = 42;
}
class B extends A {
  void m() {
    this.p();
    System.out.println("B.m: " + _x); // which _x?
  }
  final int _x = 27;
}
