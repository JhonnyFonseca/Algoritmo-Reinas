package reinas;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reinas {

    public static boolean esSeguro(int[][] tablero, int fila, int columna, int N) {
        for (int i = 0; i < fila; i++) {
            if (tablero[i][columna] == 1) {
                return false;
            }
        }

        for (int i = fila, j = columna; i >= 0 && j >= 0; i--, j--) {
            if (tablero[i][j] == 1) {
                return false;
            }
        }

        for (int i = fila, j = columna; i >= 0 && j < N; i--, j++) {
            if (tablero[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    public static void resolverReinas(int[][] tablero, int fila, int N, List<int[][]> soluciones) {
        if (fila == N) {
            int[][] nuevaSolucion = new int[N][N];
            for (int i = 0; i < N; i++) {
                System.arraycopy(tablero[i], 0, nuevaSolucion[i], 0, N);
            }
            soluciones.add(nuevaSolucion);
            return;
        }

        for (int columna = 0; columna < N; columna++) {
            if (esSeguro(tablero, fila, columna, N)) {
                tablero[fila][columna] = 1;
                resolverReinas(tablero, fila + 1, N, soluciones);
                tablero[fila][columna] = 0;
            }
        }
    }

    public static void mostrarSoluciones(int N) {
        List<int[][]> soluciones = new ArrayList<>();
        int[][] tablero = new int[N][N];
        resolverReinas(tablero, 0, N, soluciones);

        for (int i = 0; i < soluciones.size(); i++) {
            System.out.println("Solución " + (i + 1) + ":");

            int[][] solucion = soluciones.get(i);
            for (int j = 0; j < N; j++) {
                System.out.println("p " + j + ": " + obtenerPosicion(solucion, j));
            }

            for (int[] fila : solucion) {
                for (int j : fila) {
                    System.out.print(j + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static int obtenerPosicion(int[][] solucion, int columna) {
        for (int fila = 0; fila < solucion.length; fila++) {
            if (solucion[fila][columna] == 1) {
                return fila;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Escriba la cantidad de reinas: ");
        int N = scanner.nextInt();
        scanner.nextLine();
        System.out.println("");
        mostrarSoluciones(N);
        
    }
}

