package utils;

public class Pair { 
  public final int x; 
  public final int y; 
  
  public Pair(int x, int y) { 
    this.x = x; 
    this.y = y; 
  }
  
  @Override
  public String toString() {
      return "(" + x + "," + y + ")";
  }

  @Override
  public boolean equals(Object other) {
      if (other == null) {
          return false;
      }
      if (other == this) {
          return true;
      }
      if (!(other instanceof Pair)){
          return false;
      }
      Pair other_ = (Pair) other;
      return other_.x == this.x && other_.y == this.y;
  }
}