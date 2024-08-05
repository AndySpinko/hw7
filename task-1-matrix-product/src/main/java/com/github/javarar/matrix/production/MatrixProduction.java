package com.github.javarar.matrix.production;

import java.util.stream.IntStream;

public class MatrixProduction {

    /**
     * @param l строки матрицы 'a'
     * @param m колонки матрицы 'a' и строки матрицы 'b'
     * @param n колонки матрицы 'b'
     * @param a первая матрица 'l×m'
     * @param b вторая матрица 'm×n'
     * @return результирующая матрица 'l×n'
     */
    public static int[][] parallelMatrixMultiplication(int l, int m, int n, int[][] a, int[][] b) {
        // результирующая матрица
        int[][] c = new int[l][n];
        // обходим индексы строк матрицы 'a' в параллельном режиме
        IntStream.range(0, l).parallel().forEach(i -> {
            // обходим индексы общей стороны двух матриц:
            // колонок матрицы 'a' и строк матрицы 'b'
            for (int j = 0; j < m; j++)
                // обходим индексы колонок матрицы 'b'
                for (int k = 0; k < n; k++)
                    // сумма произведений элементов i-ой строки
                    // матрицы 'a' и k-ой колонки матрицы 'b'
                    c[i][k] += a[i][j] * b[j][k];
        });
        return c;
    }
}
