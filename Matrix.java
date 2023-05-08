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

    public Double getValue(int i, int j)
    {
        return values[i + j * n];
    }

    public Double getValue(int idx)
    {
        return values[idx];
    }

    public void setValue(int i, int j, Double value)
    {
        values[i + j * n] = value;
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