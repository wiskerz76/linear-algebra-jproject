public class RealVector
{
    double[] values;

    public RealVector(int n)
    {
        values = new double[n];
    }

    public Double dot(RealVector b) // this * b
    {
        double sum = 0;
        for (int i = 0; i < values.length; i++)
        {
           sum += this.values[i] * b.getValue(i);
        }
        return sum;
    }

    public double getValue(int i)
    {
        return this.values[i];
    }

    public void setValue(int i, double value)
    {
        this.values[i] = value;
    }
}