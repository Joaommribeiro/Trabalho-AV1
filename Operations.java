//package Matriz;

/*
	Classe utilitária contendo as operações matemáticas sobre Matrizes e Vetores.
*/
public class Operations {

    /*
      Realiza a transposição de uma matriz (inverte linhas por colunas).
    */
    public static Matrix transpose(Matrix matrix) {
        float[] resultElements = new float[matrix.getRows() * matrix.getCols()];
        // A matriz transposta troca a quantidade de linhas pelas de colunas
        Matrix result = new Matrix(matrix.getCols(), matrix.getRows(), resultElements);

        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                // O elemento na posição (i, j) vai para a posição (j, i) na transposta
                result.set(j, i, matrix.get(i, j));
            }
        }
        return result;
    }

    /*
      Realiza a transposição de um vetor (retorna uma cópia do vetor original).
    */
    public static Vector transpose(Vector vector) {
        float[] resultElements = new float[vector.getDim()];
        for (int i = 0; i < vector.getDim(); i++) {
            resultElements[i] = vector.get(i);
        }
        return new Vector(vector.getDim(), resultElements);
    }

    /*
      Realiza a soma de duas matrizes (elemento a elemento).
    */
    public static Matrix add(Matrix a, Matrix b) {
        // Validação de dimensões compatíveis para a soma
        if (a.getRows() != b.getRows() || a.getCols() != b.getCols()) {
            throw new IllegalArgumentException("Matrizes precisam ter a mesma dimensão para a soma.");
        }
        
        float[] resultElements = new float[a.getRows() * a.getCols()];
        Matrix result = new Matrix(a.getRows(), a.getCols(), resultElements);

        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < a.getCols(); j++) {
                result.set(i, j, a.get(i, j) + b.get(i, j));
            }
        }
        return result;
    }

    /*
      Realiza a soma de dois vetores (elemento a elemento).
    */
    public static Vector add(Vector a, Vector b) {
        // Validação das dimensoes
        if (a.getDim() != b.getDim()) {
            throw new IllegalArgumentException("Vetores precisam ter a mesma dimensão para a soma.");
        }
        
        float[] resultElements = new float[a.getDim()];
        for (int i = 0; i < a.getDim(); i++) {
            resultElements[i] = a.get(i) + b.get(i);
        }
        return new Vector(a.getDim(), resultElements);
    }

/*
  Multiplica uma matriz por um escalar, multiplicando cada elemento.
*/
public static Matrix times(float scalar, Matrix a) {
    // Cria uma matriz vazia do mesmo tamanho da original para guardar seu resultado
    Matrix result = new Matrix(a.getRows(), a.getCols(), new float[a.getRows() * a.getCols()]);
      // Percorre cada posição (i, j) da matriz e multiplica esse valor pelo escalar
    for (int i = 0; i < a.getRows(); i++)
        for (int j = 0; j < a.getCols(); j++)
            result.set(i, j, scalar * a.get(i, j));

    return result;
}

/*
  Multiplica um vetor por um escalar, multiplicando cada elemento.
*/
public static Vector times(float scalar, Vector a) {
    float[] result = new float[a.getDim()];
     // Cria um array vazio do mesmo tamanho do vetor original para guardar o resultado
    for (int i = 0; i < a.getDim(); i++)
        result[i] = scalar * a.get(i);
    // Percorre cada posição do vetor e multiplica o valor pelo escalar
    return new Vector(a.getDim(), result);
}
/*
  Multiplica duas matrizes .
  obs : Para multiplicar A x B, o número de colunas de A precisa ser igual ao número de linhas de B.
*/
public static Matrix times(Matrix a, Matrix b) {
    if (a.getCols() != b.getRows()) {
        throw new IllegalArgumentException("O número de colunas de A deve ser igual ao número de linhas de B.");
    }

    Matrix result = new Matrix(a.getRows(), b.getCols(), new float[a.getRows() * b.getCols()]);
    // Para cada posição (i, j) do resultado, soma o produto de uma linha de A por uma coluna de B
    for (int i = 0; i < a.getRows(); i++) {
        for (int j = 0; j < b.getCols(); j++) {
            float sum = 0;
            for (int k = 0; k < a.getCols(); k++) {
                sum += a.get(i, k) * b.get(k, j);
            }
            result.set(i, j, sum);
        }
    }

    return result;
}
/*
  Multiplica dois vetores (produto interno / produto escalar).
  Retornando um único número (float), não outro vetor.
*/
public static float times(Vector a, Vector b) {
    // Valida se os vetores têm a mesma dimensão
    if (a.getDim() != b.getDim()) {
        throw new IllegalArgumentException("Vetores precisam ter a mesma dimensão para o produto interno.");
    }

    float result = 0;
    // Multiplica os pares dos elementos correspondentes e soma tudo
    for (int i = 0; i < a.getDim(); i++) {
        result += a.get(i) * b.get(i);
    }

    return result;
}
}
