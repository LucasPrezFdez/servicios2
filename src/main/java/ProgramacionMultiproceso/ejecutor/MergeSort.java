package ProgramacionMultiproceso.ejecutor;
import java.util.Arrays;
import java.util.Random;

public class MergeSort {

    public static int[] mergeSort(int array[]) {
        // if the array has more than 1 element, we need to split it and merge the
        // sorted halves
        if (array.length > 1) {
            int elementsInA1 = array.length / 2;
            int elementsInA2 = array.length - elementsInA1;

            int arr1[] = new int[elementsInA1];
            int arr2[] = new int[elementsInA2];

            for (int i = 0; i < elementsInA1; i++)
                arr1[i] = array[i];

            for (int i = elementsInA1; i < elementsInA1 + elementsInA2; i++)
                arr2[i - elementsInA1] = array[i];
            //LLAMADA RECURSIVA DE CADA MITAD
            arr1 = mergeSort(arr1);
            arr2 = mergeSort(arr2);

            int i = 0, j = 0, k = 0;
            while (arr1.length != j && arr2.length != k) {
                if (arr1[j] < arr2[k]) {
                    array[i] = arr1[j];
                    i++;
                    j++;
                } else {
                    array[i] = arr2[k];
                    i++;
                    k++;
                }
            }
            while (arr1.length != j) {
                array[i] = arr1[j];
                i++;
                j++;
            }
            while (arr2.length != k) {
                array[i] = arr2[k];
                i++;
                k++;
            }
        }
        return array;
    }

    public static void main(String[] args) {

        // vamos a probarlo
        int[] datos = { 2, 5, 0, 9, 3, 1, 8, 4, 7, 6, 2, 5, 9, 0, 3, 1, 4, 7, 8, 2 };
        System.out.println(Arrays.toString(datos));
        datos = mergeSort(datos);
        System.out.println(Arrays.toString(datos));


        // 1. Declaramos el array de 100 posiciones (capacidad fija)
        int[] numeros = new int[1000000];
        Random random = new Random();

        // 2. Llenamos el array con el bucle
        for (int i = 0; i < numeros.length; i++) {
            // nextInt(10) genera un entero aleatorio entre 0 (inclusive) y 10 (exclusive)
            // Es decir: 0, 1, 2, 3, 4, 5, 6, 7, 8 o 9.
            numeros[i] = random.nextInt(10);
        }
        System.out.println(Arrays.toString(numeros));
        numeros = mergeSort(numeros);
        System.out.println(Arrays.toString(numeros));


    }

}