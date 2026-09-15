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

/*
  Realiza a eliminação gaussiana em uma matriz, transformando-a na forma escalonada.
*/
public static Matrix gauss(Matrix a) {
    int rows = a.getRows();
    int cols = a.getCols();

    // Cria uma cópia da matriz original para não alterar os dados de entrada
    float[] elements = new float[rows * cols];
    for (int i = 0; i < rows; i++)
        for (int j = 0; j < cols; j++)
            elements[i * cols + j] = a.get(i, j);
    Matrix result = new Matrix(rows, cols, elements);

    int pivotRow = 0;

    // Percorre cada coluna procurando um pivo para eliminar os elementos abaixo dele
    for (int col = 0; col < cols && pivotRow < rows; col++) {

        // Procura uma linha com valor não 0 nessa coluna, a partir da linha atual do pivo
        int maxRow = pivotRow;
        for (int i = pivotRow + 1; i < rows; i++) {
            if (Math.abs(result.get(i, col)) > Math.abs(result.get(maxRow, col))) {
                maxRow = i;
            }
        }

        // Se o maior valor da coluna for zero, não há pivô possível nessa coluna, pula pra próxima
        if (result.get(maxRow, col) == 0) {
            continue;
        }

        // Troca a linha atual do pivo com a linha de maior valor (pivotamento parcial)
        if (maxRow != pivotRow) {
            for (int j = 0; j < cols; j++) {
                float temp = result.get(pivotRow, j);
                result.set(pivotRow, j, result.get(maxRow, j));
                result.set(maxRow, j, temp);
            }
        }

        // Zera todos os elementos abaixo do pivô nessa coluna
        for (int i = pivotRow + 1; i < rows; i++) {
            float factor = result.get(i, col) / result.get(pivotRow, col);
            for (int j = col; j < cols; j++) {
                result.set(i, j, result.get(i, j) - factor * result.get(pivotRow, j));
            }
        }

        pivotRow++;
    }

    return result;
}

/*
  Resolve um sistema de equações lineares usando eliminação de Gauss-Jordan.
  Recebe uma matriz aumentada (coeficientes + termos independentes) e retorna a matriz já resolvida.
*/
public static Matrix solve(Matrix a) {
    int rows = a.getRows();
    int cols = a.getCols();

    // Primeiro aplica a eliminação gaussiana normal (zera abaixo da diagonal)
    Matrix result = gauss(a);

    // Agora percorre de baixo pra cima, normalizando cada linha e zerando acima da diagonal também
    for (int row = rows - 1; row >= 0; row--) {

        // Encontra a posição do pivô nessa linha (primeiro valor não-zero)
        int pivotCol = -1;
        for (int col = 0; col < cols; col++) {
            if (result.get(row, col) != 0) {
                pivotCol = col;
                break;
            }
        }

        // Se a linha for toda zero, não há pivo pula
        if (pivotCol == -1) {
            continue;
        }

        // Normaliza a linha para que o pivo vire 1
        float pivotValue = result.get(row, pivotCol);
        for (int col = 0; col < cols; col++) {
            result.set(row, col, result.get(row, col) / pivotValue);
        }

        // Zera todos os elementos acima do pivo nessa coluna
        for (int i = row - 1; i >= 0; i--) {
            float factor = result.get(i, pivotCol);
            for (int col = 0; col < cols; col++) {
                result.set(i, col, result.get(i, col) - factor * result.get(row, col));
            }
        }
    }

    return result;
}

}
