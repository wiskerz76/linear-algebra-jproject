import java.util.Random;

public class Matrix
{
    Double[] values;
    public int m;
    public int n;

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
     * Generates a random matrix of size m x n
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
            mtx.setValue(i, (double)r.nextInt(20));
        }
        return mtx;
    }

    /** 
     * Generates a random, non singular square matrix
     * @param m size of produced matrix
     */
    public static Matrix randomNonSingular(int m)
    {
        return randomNonSingular(m, 3);
    }

    /** 
     * Generates a random, non singular square matrix by applying random row operations
     * @param m - size of the generated matrix
     * @param stepBound - the maximum number of steps to use
     */
    public static Matrix randomNonSingular(int m, int stepBound)
    {
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
    }

    /**
     * performs an in-place swap row operation exchanging (a,b)
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
     * 
     * @param src
     * @param dest
     */
    public void addRows(int src, int dest)
    {
        for(int j = 0; j < n; j++)
        {
            setValue(dest,j,getValue(dest,j) + getValue(src,j));
        }
    }

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

    public static boolean roughlyEqual(Matrix a, Matrix b)
    {
        final double tolerance = 0.001;
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

    public Double getValue(int i, int j)
    {
        return values[i * n + j];
    }

    public Double getValue(int idx)
    {
        return values[idx];
    }

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

    public RealVector getRow(int row)
    {
        RealVector v = new RealVector(n);
        for (int i = 0; i < n; i++)
        {
            v.setValue(i, this.getValue(row, i));
        }
        return v;
    }

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

    public Matrix transpose()
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