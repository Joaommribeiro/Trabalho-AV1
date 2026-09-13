//package Matriz;

import java.util.Scanner;

public class Main {

    private static final Scanner userInput = new Scanner(System.in);

    /**
      Solicita as dimensões e elementos ao usuário para criar um objeto Matrix.
     */
    private static Matrix readMatrix() {
        System.out.println("\nConstruindo a Matriz:\n");
        System.out.print("Quantidade de Linhas: ");
        int rows = userInput.nextInt();
        System.out.print("Quantidade de Colunas: ");
        int cols = userInput.nextInt();

        float[] elements = new float[rows * cols];
        System.out.println("\nInserindo os valores da Matriz:\n");
        for (int i = 0; i < rows; i++) {
            System.out.printf("Linha (%d):\n", i + 1);
            for (int j = 0; j < cols; j++) {
                System.out.printf("  Coluna (%d): ", j + 1);
                elements[i * cols + j] = userInput.nextFloat();
            }
        }
        return new Matrix(rows, cols, elements);
    }

    /**
      Solicita a dimensão e elementos ao usuário para criar um objeto Vector.
     */
    private static Vector readVector() {
        System.out.println("\nConstruindo o Vetor:\n");
        System.out.print("Dimensão do Vetor: ");
        int dim = userInput.nextInt();

        float[] elements = new float[dim];
        System.out.println("\nInserindo os valores do Vetor:\n");
        for (int i = 0; i < dim; i++) {
            System.out.printf("Elemento (%d): ", i + 1);
            elements[i] = userInput.nextFloat();
        }
        return new Vector(dim, elements);
    }

    /**
      Solicita ao usuário um escalar, validando se é um número natural no caso um inteiro positivo.
     */
    private static float readScalar() {
        System.out.print("\nDigite um número natural (inteiro positivo) para multiplicar: ");
        float scalar = userInput.nextFloat();

        // Valida se o número é realmente natural verificando se e maior que zero e sem casas decimais
        if (scalar <= 0 || scalar != Math.floor(scalar)) {
            throw new IllegalArgumentException("O valor precisa ser um número natural (inteiro maior que zero).");
        }

        return scalar;
    }

    public static void main(String[] args) {
        System.out.println("\t\t Calculador de Matrizes e Vetores:\n");
        System.out.println("-".repeat(75));

        // Seleção do tipo de estrutura de dados
        System.out.println("O que deseja operar?");
        System.out.println("1 - Matrizes");
        System.out.println("2 - Vetores");
        int structureType = userInput.nextInt();

        // Seleção da operação desejada
        System.out.println("\n\t\t Selecione a Operação:\n");
        System.out.println("-".repeat(75));
        System.out.println("1º - Transposição");
        System.out.println("2º - Soma");
        System.out.println("3º - Multiplicação por escalar");
        System.out.print("Opção: ");
        int selectedOperation = userInput.nextInt();

        try {
            if (structureType == 1) {
                // FLUXO DE MATRIZ
                Matrix m1 = readMatrix();
                System.out.print("\nMatriz original:");
                m1.display();

                switch (selectedOperation) {
                    case 1 -> {
                        System.out.println("\nMatriz Transposta:");
                        Operations.transpose(m1).display();
                    }
                    case 2 -> {
                        System.out.println("\nInsira a segunda Matriz para somar:");
                        Matrix m2 = readMatrix();
                        System.out.println("\nResultado da Soma (M1 + M2):");
                        Operations.add(m1, m2).display();
                    }
                    case 3 -> {
                        float scalar = readScalar();
                        System.out.println("\nResultado da Multiplicação (escalar * M1):");
                        Operations.times(scalar, m1).display();
                    }
                    default -> System.out.println("Opção inválida.");
                }

            } else if (structureType == 2) {
                // FLUXO DE VETOR
                Vector v1 = readVector();
                System.out.print("\nVetor original: ");
                v1.display();

                switch (selectedOperation) {
                    case 1 -> {
                        System.out.println("\nVetor Transposto:");
                        Operations.transpose(v1).display();
                    }
                    case 2 -> {
                        System.out.println("\nInsira o segundo Vetor para somar:");
                        Vector v2 = readVector();
                        System.out.println("\nResultado da Soma (V1 + V2):");
                        Operations.add(v1, v2).display();
                    }
                    case 3 -> {
                        float scalar = readScalar();
                        System.out.println("\nResultado da Multiplicação (escalar * V1):");
                        Operations.times(scalar, v1).display();
                    }
                    default -> System.out.println("Opção inválida.");
                }
            } else {
                System.out.println("Opção de estrutura inválida.");
            }
        } catch (Exception e) {
            // Captura qualquer erro de validação (ex: dimensões incompatíveis) e exibe ao usuário
            System.out.println("\nErro durante a execução: " + e.getMessage());
        }
    }
}
