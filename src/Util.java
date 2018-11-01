class Util {
    static int[][] copy2DArray(int[][] matrix) {
        int[][] matrixCopy = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++)
            matrixCopy[i] = matrix[i].clone();
        return matrixCopy;
    }
}
