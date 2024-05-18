package T_05_DesignPatterns;

public interface Matrix {
    double get(int r, int c);
    Matrix transpose();
    Matrix inverse();
    Matrix add(Matrix that);
    Matrix multiply(double that);
    Matrix multiply(Matrix that);
    // other stuff

    final class DenseMatrix implements Matrix {
        public DenseMatrix(double[][] elements) {

        }

        @Override
        public double get(int r, int c) {
            return 0;
        }

        @Override
        public Matrix transpose() {
            return null;
        }

        @Override
        public Matrix inverse() {
            return null;
        }

        @Override
        public Matrix add(Matrix that) {
            return null;
        }

        @Override
        public Matrix multiply(double that) {
            return null;
        }

        @Override
        public Matrix multiply(Matrix that) {
            return null;
        }
    }

    final class SparseMatrix implements Matrix {
        public SparseMatrix(double[][] elements) {
        }

        @Override
        public double get(int r, int c) {
            return 0;
        }

        @Override
        public Matrix transpose() {
            return null;
        }

        @Override
        public Matrix inverse() {
            return null;
        }

        @Override
        public Matrix add(Matrix that) {
            return null;
        }

        @Override
        public Matrix multiply(double that) {
            return null;
        }

        @Override
        public Matrix multiply(Matrix that) {
            return null;
        }
    }

    /*
        Design Pattern: General Builder ------------------------------------------------------------
            construct a generic instance and then decide at build time which implementation needs to
            be returned.
     */
    final class Builder {
        private final double[][] elements;

        public Builder(int r, int c) {
            elements = new double[r][c];
        }

        public double get(int r, int c) {
            return elements[r][c];
        }

        public void set(int r, int c, double v) {
            elements[r][c] = v;
        }

        public double density() {
            int count = 0;
            for (double[] row : elements) {
                for (double value : row) {
                    if (value != 0) count++;
                }
            }

            return (double) count / (double) (elements.length * elements[0].length);
        }

        public Matrix build() {
            return density() > 0.5 ? new DenseMatrix(elements) : new SparseMatrix(elements);
        }
    }
}