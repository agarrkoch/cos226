public class percolationstats {
    double[] opens;
    int iterations;
    double size;
    int t;

    double mean;
    double s2;

    // perform independent trials on an n-by-n grid
    public percolationstats(int n, int trials) {
        iterations = trials;
        size = n;
        t = trials;
        opens = new double[trials];

        for (int i = 0; i < trials; i++) {
            percolation p = new percolation(n);
            while (p.percolates() == false) {
                int x = (int) (Math.random() * (n * n));
                int row = x / n;
                int col = x % n;

                if (p.isOpen(row, col) == false) {
                    p.open(row, col);
                }
            }
            opens[i] = p.numberOfOpenSites() / (size * size);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        mean = 0;
        for (int i = 0; i < iterations; i++) {
            mean = mean + opens[i];
        }
        mean = mean / iterations;
        return (mean);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double temp = 0;

        for (int i = 0; i < t; i++) {
            temp = temp + ((opens[i] - mean) * (opens[i] - mean));
        }

        s2 = temp;
        return (temp / (t - 1));

    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return (mean - ((1.96 * Math.sqrt(s2)) / Math.sqrt(t)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return (mean + ((1.96 * Math.sqrt(s2)) / Math.sqrt(t)));
    }

    // test client (see below)
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        Stopwatch n = new Stopwatch();

        percolationstats ps = new percolationstats(size, trials);

        System.out.printf("mean() = %f", ps.mean());
        System.out.println();
        System.out.printf("stddev() = %f", ps.stddev());
        System.out.println();
        System.out.printf("confidenceLow() = %f", ps.confidenceLow());
        System.out.println();
        System.out.printf("confidenceHigh() = %f", ps.confidenceHigh());
        System.out.println();
        System.out.printf("elapsed time = %f", n.elapsedTime());
    }

}
