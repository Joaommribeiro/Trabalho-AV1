package CalculatorPackage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner userInput = new Scanner(System.in);

    /**
     * Lê um inteiro positivo do console, repetindo até o usuário digitar um valor válido.
     */
    private static int readIntStrictlyPositive(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = userInput.nextInt();
                if (value <= 0) {
                    System.out.println(" Entrada inválida: Digite um número inteiro maior que zero.");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println(" Entrada inválida: Digite apenas números inteiros.");
                userInput.nextLine(); // Limpa o buffer do Scanner
            }
        }
    }

    /**
     * Lê um valor float do console tratando erros de digitação.
     */
    private static float readFloat(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return userInput.nextFloat();
            } catch (InputMismatchException e) {
                System.out.println(" Entrada inválida: Digite apenas números (ex: 5 ou 3.14).");
                userInput.nextLine(); // Limpa o buffer
            }
        }
    }

    /**
     * Solicita as dimensões e elementos ao usuário para criar um objeto Matrix.
     */
    private static Matrix readMatrix() {
        System.out.println("\nConstruindo a Matriz:\n");
        int rows = readIntStrictlyPositive("Quantidade de Linhas: ");
        int cols = readIntStrictlyPositive("Quantidade de Colunas: ");

        float[] elements = new float[rows * cols];
        System.out.println("\nInserindo os valores da Matriz:\n");
        for (int i = 0; i < rows; i++) {
            System.out.printf("Linha (%d):\n", i + 1);
            for (int j = 0; j < cols; j++) {
                elements[i * cols + j] = readFloat(String.format("  Coluna (%d): ", j + 1));
            }
        }
        return new Matrix(rows, cols, elements);
    }

    /**
     * Solicita a dimensão e elementos ao usuário para criar um objeto Vector.
     */
    private static Vector readVector() {
        System.out.println("\nConstruindo o Vetor:\n");
        int dim = readIntStrictlyPositive("Dimensão do Vetor: ");

        float[] elements = new float[dim];
        System.out.println("\nInserindo os valores do Vetor:\n");
        for (int i = 0; i < dim; i++) {
            elements[i] = readFloat(String.format("Elemento (%d): ", i + 1));
        }
        return new Vector(dim, elements);
    }

    /**
     * Solicita ao usuário um escalar, validando se é um número natural (inteiro positivo).
     */
    private static float readScalar() {
        while (true) {
            float scalar = readFloat("\nDigite um número natural (inteiro positivo) para multiplicar: ");
            if (scalar <= 0 || scalar != Math.floor(scalar)) {
                System.out.println(" Erro: O valor precisa ser um número natural (inteiro maior que zero).");
            } else {
                return scalar;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("\t\t Calculador de Matrizes e Vetores:\n");
        System.out.println("-".repeat(75));

        // 1. Seleção e validação do tipo de estrutura de dados
        int structureType;
        while (true) {
            System.out.println("O que deseja operar?");
            System.out.println("1 - Matrizes");
            System.out.println("2 - Vetores");
            structureType = readIntStrictlyPositive("Opção: ");
            if (structureType == 1 || structureType == 2) break;
            System.out.println(" Opção inválida. Escolha 1 para Matrizes ou 2 para Vetores.\n");
        }

        // 2. Seleção e validação do menu de operações ANTES de ler matrizes/vetores
        int selectedOperation;
        int maxOp = (structureType == 1) ? 5 : 3;

        while (true) {
            System.out.println("\n\t\t Selecione a Operação:\n");
            System.out.println("-".repeat(75));
            System.out.println("1º - Transposição");
            System.out.println("2º - Soma");
            System.out.println("3º - Multiplicação por escalar");
            if (structureType == 1) {
                System.out.println("4º - Eliminação Gaussiana");
                System.out.println("5º - Resolver Sistema Linear / Gauss-Jordan");
            }

            selectedOperation = readIntStrictlyPositive("Opção: ");
            if (selectedOperation >= 1 && selectedOperation <= maxOp) {
                break; // Opção válida, sai do loop e prossegue
            }
            System.out.printf(" Opção inválida! Escolha um número entre 1 e %d.\n", maxOp);
        }

        // 3. Execução das Operações
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
                    case 4 -> {
                        System.out.println("\nResultado da Eliminação Gaussiana:");
                        Operations.gauss(m1).display();
                    }
                    case 5 -> {
                        System.out.println("\nResultado da Resolução do Sistema (Gauss-Jordan):");
                        Operations.solve(m1).display();
                    }
                }

            } else {
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
                }
            }
        } catch (Exception e) {
            // Captura exceções matemáticas (ex: tentar somar matrizes com dimensões diferentes)
            System.out.println("\n Erro durante a execução do cálculo: " + e.getMessage());
        }
    }
}
