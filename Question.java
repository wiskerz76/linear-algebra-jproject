import java.util.Vector;
import java.util.Random;


public class Question
{   
    
    public final String question;
    public final Vector<String> options = new Vector<>(4, 0);
    public final int correct;
    public final int corval;
    public final int questionType;
    public final String explanation;
    
    public static final int BOOLEAN = 0;
    public static final int NUMERIC = 1;

    public Question(boolean answer, String query)
    {
        this(answer, query, "");
    }

    public Question(int answer, String query)
    {
        this(answer, query, "");
    }

    public Question(boolean answer, String query, String explanation) 
    {
        this.questionType = BOOLEAN;
        this.explanation = explanation;
        question = query;
        options.add("True");
        options.add("False");
        correct = answer ? 0 : 1;
        corval = answer ? 1 : 0;
    }

    public Question(int answer, String query, String explanation)
    {
        this.questionType = NUMERIC;
        this.explanation = explanation;
        Random rng = new Random();
        question = query;
        corval = answer;
        boolean inserted = false;
        correct = rng.nextInt(4);
        for(int i = 0; i < 4; i++)
        {
            //Insert the answer
            if(i == correct)
            {
                options.add("" + answer);
            }
            else
            {
                int r = rng.nextInt(Math.abs(5 * answer) + 1);
                while(r == answer || options.contains(r + ""))
                {
                    r += 1 + rng.nextInt(4);
                }
                options.add("" + r);
            }
        }
    }

    /**
     * Returns a random question from the question bank
     * @return
     */
    public static Question getRandomQuestion()
    {
        Random r = new Random();
        return getQuestion(r.nextInt(Question.BANK_SIZE));
    }

    /**
     * This implements a question bank. 
     * @param ix the index of the question 
     * @return
     */
    public static Question getQuestion(int ix)
    {

        //Generate some random variables that can be reused.
        Random rng = new Random();
        int i = rng.nextInt(100) + 1;
        int j = rng.nextInt(100) + 1;
        int k = rng.nextInt(100) + 1;

        //Lookup a question by index
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
                    false,
                    "All linear transformations are injective or surjective"
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
                    String.format("Can an isomorphism exist between the set of %d x %d matrices and R%d?",i,j,i*j + k)
                );
            case 8:
                return new Question(
                    true,
                    "Suppose that R is the set of real numbers \n"
                    + "Is it a vector space with addition of vectors given by addition?"
                );
            case 9:
                return new Question(
                    false,
                    "Suppose that R is the set of real numbers \n"
                    + "Is it a vector space with addition of vectors given by multiplication?"
                );
            case 10:
                return new Question(
                    false,
                    String.format("Suppose that f is a linear transformation from the set {0} to the set R%d; can f be a bijection?", i + 2)
                );
            case 11:
                return new Question(
                    true,
                    String.format("Consider the set of polynomials of degree %d or less; can a linear transformation T exist to the set of %d x %d matrices\n"
                        + "such that the kernel of T has dimension zero?",i * j, i, j)
                );
            case 12:
                return new Question(
                    true,
                    String.format("Consider the set of polynomials of degree %d or less; can a linear transformation T exist to the set of %d x %d matrices\n"
                        + "such that T has an inverse?",i * j, i, j)
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
                    "Suppose that matrices P and Q are similar. Does this imply that P and Q have the same determinant?"
                );
            case 15:
                return new Question(
                    i, 
                    String.format("If dim(W) is %d, and H represents a surjection from R%d to W, then the rank of H is:",i, i + j)
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
                    String.format("What is the dimension of R^%d?",i)
                );
            case 20:
                return new Question(
                    i,
                    String.format("Suppose that the set S spans R^%d and that all vectors in S are linearly independent; what is dim(S)?",i)
                );
            case 21:
                return new Question(
                    i,
                    String.format("Suppose that f(x) = Mx is a surjection onto V. If the rank of M is %d, what is the dimension of V?", i)
                );
            case 22:
                return new Question(
                    true,
                    String.format("Suppose that V is a subspace of R^%d. Is V a vector space?", k > 70 ? i * j * k : j)
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
                    String.format("Suppose that v1 and v2 are non-zero vectors in R%d, such that for some scalar x, v1 = x*v2. What is dim(span{v1,v2})?", i + 1)
                );
            case 26:
                return new Question(
                    i,
                    String.format("What is the rank of the matrix M such that for every vector x in R%d, Mx = x?", i)
                );
            case 27:
                return new Question(
                    i,
                    String.format("Suppose that M is a %d x %d matrix such that the nullity of M is %d. What is the rank of M?", i + j, i + j, j)
                );
            case 28:
                return new Question(
                    i,
                    String.format("Suppose that M is a %d x %d matrix such that the rank of M is %d. What is the nullity of M?", i + j, i + j, j)
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
                    String.format("Is there a pair of %d x %d matrices such that det(A + B) = det(BA)?",i + 1 ,i + 1)
                );
            case 37:
                return new Question(
                    true,
                    "Suppose that a matrix is row-reducible to the identity. Is it invertible?"
                );
            case 38:
                return new Question(
                    false, 
                    String.format("If a matrix M is of size %d by %d, then rank(M) + nullity(M) = %d", i, j, i)
                );
            case 39:
                return new Question(
                    i,
                    String.format("If a space V is spanned by %d linearly independent vectors, what is dim(V)?", i)
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
                     String.format("If T is a linear transformation from R%d -> R%d, and T^{-1} exists, then dim(Im(T)) = ?",i,i)
                );
            case 44:
                return new Question(
                    0,
                    String.format("If T is a linear transformation from R%d -> R%d, and T^{-1} exists, then dim(kern(T)) = ?",i,i)
                );
            case 45:
                return new Question(
                    true,
                    String.format("If for a matrix M and N, if MN = I, then M = N^-1")
                );
            case 46:
                return new Question(
                    false,
                    String.format("If M = M^T, then M^-1 exists")
                );
            case 47:
                return new Question(
                    i * j * k,
                    String.format("If M is similar to a diagonal matrix with eigenvalues %d, %d, %d, then its determinant is:",i,j,k)
                );
            case 48:
                return new Question(
                    i * j,
                    String.format("If a 2x2 matrix has eigenvalues %d and %d, then its determinant is:",i,j)
                );
            case 49:
                return new Question(
                    false,
                    String.format("If a %d x %d matrix is equal to its transpose, then it must be diagonal",i,i)
                );
            case 50:
                return new Question(
                    false,
                    "A vector space is closed under vector multiplication"
                );
            case 51:
                return new Question(
                    true,
                    "A subspace includes the zero vector"
                );
            case 52:
                return new Question(
                    false,
                    String.format("The subset of R%d of vectors with magnitude greater than %d is a subspace", i,j)
                );
            case 53:
                return new Question(
                    false,
                    "All subsets of vector spaces are subspaces"                
                );
            case 54:
                return new Question(
                    true,
                    String.format("All subspaces of R%d are vector spaces",i)
                );
            case 55:
                return new Question(
                    true,
                    String.format("The the set {0} is a subspace of R%d",k > 70 ? i * j * k : i)
                );
            case 56:
                return new Question(
                    true,
                    "If a matrix is invertible, then the dimension of the span of its columns is equal to the number of its rows"
                );
            case 57:
                StringBuilder b = new StringBuilder("v1");
                for(int n = 0; n < i %8; n++)
                {
                    b.append(String.format(", v%d",n + 2));
                }
                return new Question(
                    false,
                    String.format("If a subspace of R%d is spanned by " 
                            + b.toString() +
                            " then its dimension is %d",
                            j,
                            i % 8
                        )
                );
            case 58:
                return new Question(
                    true,
                    "There exist vectors p and q in R2 such that all vectors in R2 can be writen as ap + bq for some scalars a, b"
                );
            case 59:
                return new Question(
                    false,
                    String.format("For two subspaces W & V of R%d, the dimension of their union = dim(W) + dim(V)",i)
                );
            default:
                throw new IllegalArgumentException("Invalid question index");
        }
    };
    

    /**
     * The number of questions in the question bank.
     */
    public static final int BANK_SIZE = 60; // This i\needs to be kept up to date with the actual bank
}