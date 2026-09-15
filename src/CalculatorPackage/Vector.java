package CalculatorPackage;

//package Matriz;

/*
Classe que representa um Vetor unidimensional.
*/
public class Vector {
  private final int dim;
  private final float[] elements;

  /*
    Construtor da classe Vector.
    @param dim Dimensão (tamanho) do vetor.
    @param elements Array de elementos do vetor.
  */
  public Vector(int dim, float[] elements) {
      // Valida se a quantidade de elementos fornecida corresponde à dimensão
      if (elements.length != dim) {
          throw new IllegalArgumentException("O tamanho do array não bate com a dimensão informada.");
      }
      this.dim = dim;
      // Clona o array para garantir o encapsulamento (evita alterações externas diretas)
      this.elements = elements.clone();
  }

  // Retorna a dimensão do vetor
  public int getDim() {
      return dim;
  }

  // Obtém o elemento no índice especificado
  public float get(int index) {
      validateIndex(index);
      return elements[index];
  }

  // Altera o elemento no índice especificado
  public void set(int index, float value) {
      validateIndex(index);
      elements[index] = value;
  }

  // Verifica se o índice fornecido está dentro dos limites válidos do array
  private void validateIndex(int index) {
      if (index < 0 || index >= dim) {
          throw new IndexOutOfBoundsException("Índice " + index + " fora dos limites do vetor.");
      }
  }

  // Exibe o vetor formatado na tela
  public void display() {
      System.out.print("[ ");
      for (int i = 0; i < dim; i++) {
          System.out.printf("%.2f ", elements[i]);
      }
      System.out.println("]");
  }
}