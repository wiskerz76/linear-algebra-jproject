import java.util.Vector;
import java.util.Random;


public class Question
{
    public final String question;
    public final Vector<String> options = new Vector<>(4, 0);
    public final int correct;
    public final int corval;
    
    public Question(boolean answer, String query) 
    {
        question = query;
        options.add("Yes");
        options.add("No");
        correct = answer ? 0 : 1;
        corval = answer ? 1 : 0;
    }

    public Question(int answer, String query)
    {
        Random rng = new Random();
        question = query;
        corval = answer;
        boolean inserted = false;
        int c = 0;
        for(int i = 0; i < 4; i++)
        {
            //Insert the answer
            if((i == 3) || !inserted && rng.nextBoolean())
            {
                options.add("" + answer);
                c = i;
            }
            else
            {
                int r = rng.nextInt(2 * answer);
                if(r == answer)
                {
                    r += 1 + rng.nextInt(4);
                }
                options.add("" + r);
            }
        }

        correct = c;
    }
    
    /**
     * This implements a question bank. 
     * @param ix the index of the question 
     * @return
     */
    public Question getQuestion(int ix)
    {
        Random rng = new Random();
        int i = rng.nextInt(100) + 1;
        int j = rng.nextInt(100) + 1;

        double x = rng.nextDouble();
        double y = rng.nextDouble();

        switch(ix)
        {
            case 0:
                return new Question(
                    true,
                    "If A and B are square matrices, and the determinant of AB = 0, then either A or B is not invertible"
                );
            case 1:
                return new Question(
                    false,
                    "If F is a linear transformation from R3 -> R2, then F is surjective"
                );
            case 2:
                return new Question(
                    true,
                    String.format("Suppose that S is the set containing only the %d by %d identity matrix.\n" 
                    + "Is it a vector space, with addition of vectors given by matrix multiplication and scalar multiplication as the function f(x,v) = v?",i,i)
                );
            case 3:
                return new Question(
                    true,
                    String.format("Consider the set of %d by %d matrices with non-zero entries on their diagonals\n"
                    +"Is it a vector space, with addition of vectors given by matrix multiplication and scaling is by the function f(x,v) = v ?",i,i)
                );
            case 4: 
                return new Question(
                    true,
                    String.format("If M is a matrix such that there are two distinct vectors x and y where Mx = My, then the nullity of M > 0")
                );
            case 5:
                return new Question(
                    false,
                    String.format("If a %d x %d matrix has an eigenvalue zero, then its rank is %d",i,i,i)
                );
            case 6:
                return new Question(
                    Math.min(i,j),
                    String.format("Suppose that f(x) = Mx is a surjective linear transformation from R%d to R%d. What is its rank?,",i,Math.min(i,j))
                );
            case 7:
                return new Question(
                    false,
                    "Suppose that R is the set of real numbers (excluding zero)\n"
                    + "Is it a vector space with addition of vectors given by multiplication"
                );
            case 8:
                return new Question(
                    true,
                    "Suppose that R is the set of real numbers \n"
                    + "Is it a vector space with addition of vectors given by addition"
                );
            case 9:
                return new Question(
                    false,
                    "Suppose that R is the set of real numbers \n"
                    + "Is it a vector space with addition of vectors given by multiplication"
                );
            case 10:
                return new Question(
                    false,
                    String.format("Suppose that f is a linear transformation from the set {0} to the set R%d; can f be a bijection", i + 2)
                );
            case 11:
                return new Question(
                    true,
                    String.format("consider the set of polynomials of degree %d or less; can a linear transformation T exist to the set of %d x %d matrices\n"
                        + "such that the kernel of T has dimension zero",i * j, i, j)
                );
            case 12:
                return new Question(
                    true,
                    String.format("consider the set of polynomials of degree %d or less; can a linear transformation T exist to the set of %d x %d matrices\n"
                        + "such that the kernel of T has an inverse",i * j, i, j)
                );
            case 13:
                return new Question(
                    false,
                    "Suppose that T is a linear transformation from V to W and there is a linear transformation U : W -> V such that for every x in W,\n"
                    + "T(U(x)) = x. Must T be an isomorphism?"
                );
            case 14:
                return new Question(
                    true,
                    "Suppose that matrices P and Q are similar. Does this imply that P and Q have the same determinant"
                );
            case 15:
                return new Question(
                    i, 
                    String.format("If dim(W) is %d, and H represents a surjection from R%d to W, then the rank of H is:?",i, i + j)
                );
            case 16:
                return new Question(
                    true,
                    "Is V a subspace of R^2, if V is defined to be {[x,y] in R^2 |x = y}?"
                );
            case 17:
                return new Question(
                    true,
                    "If V is a subspace of W, then dim(V) <= dim(W)"  
                );
            case 18:
                return new Question(
                    false, 
                    "If V is a subspace of W, then dim(V) = dim(W)"
                );
            case 19:
                return new Question(
                    i,
                    String.format("What is the dimension of R^%d",i)
                );
            case 20:
                return new Question(
                    i,
                    String.format("Suppose that the set S spans R^%d and that all vectors in S are linearly independent; what is dim(R^%d)",i)
                );
            case 21:
                return new Question(
                    i,
                    String.format("Suppose that f(x) = Mx is a surjection onto V. If the rank of M is %d, what is the dimension of V", i)
                );
            case 22:
                return new Question(
                    true,
                    "Suppose that V is a subspace of R^%d. Is V a vector space?"
                );
            case 23:
                return new Question(
                    false,
                    "Does the set of all invertible matrices form a vector space if vector addition is defined as matrix addition?"
                );
            case 24:
                return new Question(
                    Math.min(3,i),
                    String.format("Suppose there are three distinct vectors v1, v2, and v3 in R%d. What is the maximum possible value of dim(span{v1,v2,v3})?",i)
                );
            case 25:
                return new Question(
                    1,
                    String.format("Suppose that v1 and v2 are non-zero vectors in R%d, such that for some scalar x, v1 = x*v2. What is dim(span{v1,v2})", i + 1)
                );
            case 26:
                return new Question(
                    i,
                    String.format("What is the rank of the matrix M such that for every vector x in R%d, Mx = x", i)
                );
            case 27:
                return new Question(
                    i,
                    String.format("Suppose that M is a %d x %d matrix such that the nullity of M is %d. What is the rank of M", i + j, i + j, j)
                );
            case 28:
                return new Question(
                    i,
                    String.format("Suppose that M is a %d x %d matrix such that the rank of M is %d. What is the nullity of M", i + j, i + j, j)
                );
            case 29:
                i -= 50;
                j -= 50;
                return new Question(
                    i * j != 0,
                    String.format("Suppose that M is a 2x2 matrix with eigenvalues %d and %d. Is it invertible?", i, j)
                );
            case 30:
                i -= 50;
                j -= 50;
                return new Question(
                    i * j,
                    String.format("Suppose that M is a 2x2 matrix with eigenvalues %d and %d. What is its determinant?", i, j)
                );
            case 31:
                return new Question(
                    true,
                    String.format("If V is a subspace of R%d and x and y are vectors in V is x + y in V?",i)
                );
            case 32:
                return new  Question(
                    true, 
                    String.format("If V is a subspace of R%d and x and y are linearly independent vectors in V, is x + y in V?", (i + 2) * j)
                );
            case 33:
                return new Question(
                    i == 0 || i == 1,
                    String.format("If det(A) = det(B) = %d, then det(AB) = %d",i,i)
                );
            case 34:
                return new Question(
                    true,
                    "det(AB) = det(BA)"
                );
            case 35:
                return new Question(
                    false,
                    "for all matrices A and B, det(A + B) = det(BA)"
                );
            case 36:
                return new Question(
                    true,
                    String.format("Is there a pair of %d x %d matrices such that det(A + B) = det(BA)",i + 1 ,i + 1)
                );
            case 37:
                return new Question(
                    true,
                    "Suppose that a matrix is row-reducible to the identity? Is it invertible"
                );
            case 38:
                return new Question(
                    false, 
                    String.format("If a matrix M is of size %d by %d, then rank(M) + nullity(M) = %d", i, j, i)
                );
            case 39:
                return new Question(
                    i,
                    String.format("If a space V is spanned by %d linearly independent vectors, what is dim(V)", i)
                );
            case 40:
                return new Question(
                    i,
                    String.format("Suppose that dim(V) = %d and dim(W) = %d. \n"
                    + "If f is an injective linear transform V -> W, what is its rank?",i,i)
                );
            case 41:
                return new Question(
                    0,
                    String.format("Suppose that dim(V) = %d and dim(W) = %d. \n"
                    + "If f is an injective linear transform V -> W, what is its nullity?",i,i)
                );
            case 42:
                return new Question(
                    i,
                    String.format("Suppose that dim(V) = %d and dim(W) = %d. \n"
                    + "If f is an injective linear transform V -> W, what is its rank?",i,i)
                );
            case 43:
                return new Question(
                     i,
                     String.format("If T is a linear transformation from R%d -> R%d, and T^{-1} exists, then dim(Im(t)) = ?",i,i)
                );
            case 44:
                return new Question(
                    0,
                    String.format("If T is a linear transformation from R%d -> R%d, and T^{-1} exists, then dim(kern(t)) = ?",i,i)
                );
            default:
                throw new IllegalArgumentException("Invalid question index");
        }
    };
    
    public static final int BANK_SIZE = 45;
}