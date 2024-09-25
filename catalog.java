import java.util.List;

public class ShamirSecretSharing {

    public static int findConstantTerm(List<Point> points) {
        int k = points.size();
        int m = k - 1;

        // Calculate Lagrange basis polynomials
        double[][] L = new double[k][m + 1];
        for (int i = 0; i < k; i++) {
            L[i][0] = 1;
            for (int j = 1; j <= m; j++) {
                L[i][j] = 1;
                for (int l = 0; l < k; l++) {
                    if (l != i) {
                        L[i][j] *= (points.get(i).x - points.get(l).x) / (points.get(i).x - points.get(l).x - points.get(i).x);
                    }
                }
            }
        }

        // Reconstruct the polynomial
        double[] coefficients = new double[m + 1];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j <= m; j++) {
                coefficients[j] += points.get(i).y * L[i][j];
            }
        }

        // Extract the constant term
        return (int) coefficients[0];
    }

    public static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        List<Point> points = List.of(
                new Point(1, 2),
                new Point(2, 3),
                new Point(3, 5)
        );

        int constantTerm = findConstantTerm(points);
        System.out.println("Constant term: " + constantTerm);
    }
}