package com.github.javarar.matrix.production;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MatrixProductionTest {

    @DisplayName("Задание 2. Вычисление произведения квадратных матриц")
    @ParameterizedTest
    @MethodSource("matrixProducer")
    public void validateMatrixProductionTest(int l, int m, int n) {
        int[][] a = createMatrix(l, m), b = createMatrix(m, n);
        MatrixProduction.parallelMatrixMultiplication(l, m, n, a, b);
    }

    private static int[][] createMatrix(int row, int col) {
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                matrix[i][j] = (int) (Math.random() * row * col);
        return matrix;
    }

    private static Stream<Arguments> matrixProducer() {
        return Stream.of(
                Arguments.of(3, 3, 3),
                Arguments.of(100, 100, 100),
                Arguments.of(1000, 1000, 1000)
        );
    }
}
