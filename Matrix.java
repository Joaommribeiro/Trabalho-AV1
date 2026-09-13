//package Matriz;

/*
 	Classe que representa uma Matriz bidimensional armazenada internamente como um array 1D.
*/
public class Matrix {
    private final int rows;
    private final int cols;
    private final float[] elements;

    /*
      Construtor da classe Matrix.
      @param rows Número de linhas.
      @param cols Número de colunas.
      @param elements Array com todos os elementos da matriz.
    */
    public Matrix(int rows, int cols, float[] elements) {
        // Valida se o total de elementos corresponde a linhas * colunas
        if (elements.length != rows * cols) {
            throw new IllegalArgumentException("O número de elementos deve ser igual a rows * cols.");
        }
        this.rows = rows;
        this.cols = cols;
        // Cópia defensiva do array
        this.elements = elements.clone();
    }

    // Retorna a quantidade de linhas
    public int getRows() {
        return rows;
    }

    // Retorna a quantidade de colunas
    public int getCols() {
        return cols;
    }

    // Obtém um elemento específico dada a linha (i) e a coluna (j)
    public float get(int row, int col) {
        validateBounds(row, col);
        // Mapeamento 2D para índice 1D: (linha * total_colunas) + coluna
        return elements[row * cols + col];
    }

    // Define um elemento específico dada a linha (i) e a coluna (j)
    public void set(int row, int col, float value) {
        validateBounds(row, col);
        elements[row * cols + col] = value;
    }

    // Valida se as coordenadas de linha e coluna existem dentro da matriz
    private void validateBounds(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Posição (" + row + ", " + col + ") fora dos limites da matriz.");
        }
    }

    // Exibe a matriz formatada em linhas e colunas
    public void display() {
        for (int i = 0; i < rows; i++) {
            System.out.print("\n");
            for (int j = 0; j < cols; j++) {
                System.out.printf("%.2f ", get(i, j));
            }
        }
        System.out.println();
    }
}
