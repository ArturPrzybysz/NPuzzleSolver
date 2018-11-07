package main;

public class Util {
    public static byte[][] copy2DArray(byte[][] matrix) {
        byte[][] matrixCopy = new byte[matrix.length][];
        for (short i = 0; i < matrix.length; i++)
            matrixCopy[i] = matrix[i].clone();
        return matrixCopy;
    }
}
