@FunctionalInterface
interface NumberSum {
  float sum(float a, float b);
}

  public class Main {

    public static void main(String[] args) {
      NumberSum middle = (a, b) -> a + b / 2;
      System.out.println(middle.sum(4, 3) + " - it's middle! Hell yeah!");

    }
  }

