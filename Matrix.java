import java.util.Random;

public class Matrix
{
    public static final int DEFAULT_COMPLEXIFICATION = 5;

    Double[] values;
    public int m;
    public int n;

    /**
     * Constructs a default matrix (the zero matrix of size m x n)
     * @param m number of rows
     * @param n number of columns
     */
    public Matrix(int m, int n)
    {
        this.m = m;
        this.n = n;
        values = new Double[m * n];
        for (int i = 0; i < values.length; i++)
        {
            values[i] = 0.0;
        }
    }

    /**
     * Generates the n x n identity matrix
     * @param n size of the identity matrix
     * @return
     */
    public static Matrix identity(int n)
    {
        Matrix m = new Matrix(n, n);
        for (int i = 0; i < n; i++)
        {
            m.setValue(i, i, 1.0);
        }
        return m;
    }


    /**
     * Generates a random matrix of size m x n. The entries are picked at random, but will all be integers
     * @param m number f rows
     * @param n number of columns
     * @return matrix
     */
    public static Matrix random(int m, int n)
    {
        Random r = new Random();
        Matrix mtx = new Matrix(m, n);
        for (int i = 0; i < m * n; i++)
        {
            mtx.setValue(i, (double)r.nextInt(10) -r.nextInt(10));
        }
        return mtx;
    }

    /** 
     * Generates a random, non singular (invertible) square matrix by applying random row operations
     * @param m size of produced matrix
     */
    public static Matrix randomNonSingular(int m)
    {
        return randomNonSingular(m, DEFAULT_COMPLEXIFICATION);
    }

    /** 
     * Generates a random, non singular square matrix by applying random row operations
     * @param m - size of the generated matrix
     * @param stepBound - the maximum number of steps to use
     */
    public static Matrix randomNonSingular(int m, int stepBound)
    {
        /* 
        Random r = new Random();
        Matrix mtx = Matrix.identity(m);
        for(int i = 0; i < stepBound;i++)
        {
            switch(r.nextInt(4))
            {
                case 0:
                    mtx.swapRows(r.nextInt(m),r.nextInt(m));
                    break;
                case 1:
                    mtx.scaleRow(r.nextInt(10) + 1.0, r.nextInt(m));
                case 2:
                    mtx.scaleRow(-1.0,r.nextInt(m));
                case 3:
                    mtx.addRows(r.nextInt(m), r.nextInt(m));
            }
        }
        return mtx;
        */

        Matrix mtx = Matrix.identity(m);
        mtx.complexify(stepBound);
        return mtx;
    };

    /**
     * Generates a matrix with 1s along its diagonal;excepting those that have been removed so that its rank is rank
     * @param m number of rows
     * @param n number of columns
     * @param rank number of pivot columns / dim(col())
     * @return
     */
    public static Matrix baseMatrixOfRank(int m, int n, int rank)
    {
        System.out.println("Rank " + rank);
        Matrix mtx = new Matrix(m, n);
        if(rank > m)
        {
            throw new IllegalArgumentException("Rank exceeds dimension of codomain");
        }
        for(int i = 0; i < rank; i++)
        {
            mtx.setValue(i, i, 1.0);
        }
        return mtx;
    }

    /**
     * returns a random matrix of rank rank
     * @param m rows
     * @param n cols
     * @param rank rank of the matrix to be produced. Must not be greater than m
     * @return
     */
    public static Matrix randomMatrixOfRank(int m, int n, int rank)
    {
        return randomMatrixOfRank(m, n, rank, DEFAULT_COMPLEXIFICATION);
    }

    /**
     * returns a randm matrix of rank rank.
     * @param m rows
     * @param n cols
     * @param rank rank of the matrix to be produced. Must not be greater than m
     * @param stepBound number of steps of complexification
     * @return
     */
    public static Matrix randomMatrixOfRank(int m, int n, int rank, int stepBound)
    {
        if(rank > m)
        {
            throw new IllegalArgumentException("Rank exceeds dimension of codomain");
        };

        Matrix mtx = new Matrix(m,n);

        Random rng = new Random();
        
        //First we generate a random upper triangular matrix
        for(int i = 0;i < rank; i++)
        {
            mtx.setValue(i,i, 1.0);
            for(int k = 0; k < i; k++)
            {
                mtx.setValue(i,k,0.0 + rng.nextInt(10) - rng.nextInt(10));
            }
        }

        mtx.complexify(stepBound);
        return mtx;
    }

    /**
     * Returns a matrix with randomly chosen numbers along the diagonal and zeros all elsewhere
     * @param m
     * @return
     */
    public static Matrix randomDiagonalMatrix(int m)
    {
        Matrix mtx = new Matrix(m,m);
        Random r = new Random();

        for(int i = 0; i < m; i++)
        {
            mtx.setValue(i, i, r.nextDouble());
        }
        return mtx;
    }

    /**
     * Performs random row operations on a matrix
     * The image, kernel, etc remain fixed
     * @param stepBound number of row operations to perform
     */
    public void complexify(int stepBound)
    { 
        Random r = new Random();
        for(int i = 0; i < stepBound;i++)
        {
            switch(r.nextInt(4))
            {
                case 0:
                    swapRows(r.nextInt(m),r.nextInt(m));
                    break;
                case 1:
                    scaleRow(r.nextInt(10) + 1.0, r.nextInt(m));
                case 2:
                    scaleRow(-1.0,r.nextInt(m));
                case 3:
                    addRows(r.nextInt(m), r.nextInt(m));
            }
        }
    }

    
    /**
     * performs an in-place swap row operation exchanging rows (a,b)
     * @param a first row
     * @param b secord row
     */
    public void swapRows(int a, int b)
    {
        //for each column
        for(int j = 0; j < n; j++)
        {
            final double old_a = getValue(a,j);
            setValue(a,j,getValue(b,j));
            setValue(b,j,old_a);
        }
    }

    /**
     * performs an in-place scaling of row "row" by scale
     * @param scale scalar multiple of row
     * @param row index of row to scale
     */
    public void scaleRow(Double scale, int row)
    {
        //for each column
        for(int j = 0; j < n; j++)
        {
            setValue(row, j, scale * getValue(row,j));
        }
    }

    /**
     * performs the elementary row operation of adding two rows. 
     * @param src The row to take as the addend
     * @param dest the row that will be modified by adding src
     */
    public void addRows(int src, int dest)
    {
        for(int j = 0; j < n; j++)
        {
            setValue(dest,j,getValue(dest,j) + getValue(src,j));
        }
    }

    /**
     * determines if 'this' is equal to b. May produce dubious results due to floating point
     * use roughlyEqual to compare with tolerance for fp sketchiness.
     * @param b Matrix to compare with
     * @return
     */    
    public boolean isEqual(Matrix b)
    {
        if (this.m != b.m || this.n != b.n)
            return false;

        for (int i = 0; i < this.m * this.n; i++)
        {
            if (this.getValue(i) != b.getValue(i))
                return false;
        }
        return true;
    }

    /**
     * Compare if two matrices are equal within default tolerance
     * @param a first matrix
     * @param b second matrix
     * @return
     */
    public static boolean roughlyEqual(Matrix a, Matrix b)
    {
        return roughlyEqual(a, b, 0.001);
    }
    
    /**
     * Compare if two matrcies are equal with a given tolerance
     * @param a first matrix
     * @param b second matrix
     * @param tolerance maximum difference allowed between elements
     * @return
     */
    public static boolean roughlyEqual(Matrix a, Matrix b,double tolerance)
    {
        if(a.m != b.m || a.n != b.n)
        {
            return false;
        }
        for(int i = 0; i < a.m * a.n; i++)
        {
            if(Math.abs(a.getValue(i) - b.getValue(i)) >= tolerance)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * get the value at ith row and jth column of a matrix (from zero)
     * @param i row
     * @param j column
     * @return
     */
    public Double getValue(int i, int j)
    {
        return values[i * n + j];
    }

    public Double getValue(int idx)
    {
        return values[idx];
    }

    /**
     * Set the value at ith row and jth column
     * @param i row
     * @param j column
     * @param value replacement value
     */
    public void setValue(int i, int j, Double value)
    {
        values[i * n + j] = value;
    }

    public void setValue(int idx, Double value)
    {
        values[idx] = value;
    }

    public Matrix getMinor(int i, int j)
    {
        if (m < 2 || n < 2)
            throw new IllegalStateException("This matrix does not have a minor");
        Matrix minor = new Matrix(m - 1, n - 1);
        int idx = 0;
        for (int k = 0; k < m; k++)
        {
            if (k == i)
                continue;
            for (int l = 0; l < n; l++)
            {
                if (l == j)
                    continue;
                minor.setValue(idx, this.getValue(k, l));
                idx++;
            }
        }
        
        return minor;
    }

    /**
     * get a given row as a vector
     * @param row the index of the row starting from zero
     * @return
     */
    public RealVector getRow(int row)
    {
        RealVector v = new RealVector(n);
        for (int i = 0; i < n; i++)
        {
            v.setValue(i, this.getValue(row, i));
        }
        return v;
    }

    /**
     * Get a given column as a vector
     * @param col the index of the column, starting from zero
     * @return
     */
    public RealVector getColumn(int col)
    {
        RealVector v = new RealVector(m);
        for (int i = 0; i < m; i++)
        {
            v.setValue(i, this.getValue(i, col));
        }
        return v;
    }


    public Double determinant()
    {
        if (n != m)
            throw new IllegalStateException("Cannot get the determinant of non-square matrices");

        if (n == 2)
            return getValue(0) * getValue(3) - getValue(1) * getValue(2);

        int sign = 1;
        Double sum = 0d;
        for (int i = 0; i < n; i++)
        {
            sum += getValue(i) * sign * getMinor(i, 0).determinant();
            sign *= -1;
        }
        return sum;
    }

    /**
     * returns the transpose of the matrix (does not modify it)
     * @return
     */
    public Matrix getTranspose()
    {
        Matrix t = new Matrix(n, m);
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                t.setValue(i, j, this.getValue(j, i));
            }
        }
        return t;
    }

    /**
     * returns the product of the matrix with b (i.e. this * b)
     * @param b the matrix to multiply with
     * @return
     */
    public Matrix multiply(Matrix b) // this * b
    {
        Matrix result = new Matrix(this.n, b.m);
        for (int i = 0; i < this.n; i++)
        {
            for (int j = 0; j < b.m; j++)
            {
                result.setValue(i, j, this.getRow(i).dot(b.getColumn(j)));
            }
        }
        return result;
    }

    @Override
    public String toString()
    {
        String s = "";
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                s += getValue(i, j).toString() + " ";
            }
            s += "\n";
        }
        return s;
    }
}