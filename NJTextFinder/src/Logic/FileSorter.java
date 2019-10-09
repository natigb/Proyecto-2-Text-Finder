package Logic;

import java.text.Collator;
import java.util.Locale;

/**
 *
 * @author Jose and Natalia
 */
public class FileSorter {
    private static Collator espCollator = Collator.getInstance(Locale.getDefault());
    private static int sortID;

    //         __________________
    //________/Getters n' Setters
    public static int getSortID() {
        return sortID;
    }

    public static void setSortID(int sortID) {
        FileSorter.sortID = sortID;
    }
    
    
    //         ____________
    //________/QUICKSORT
    /**
     * Funcion pricipal que recibe una array de palabras y las organiza con quicksort
     * @param arr Lista de palabras a ordenar.
     */
    public static void quickSort(String arr[]){
       quickSortAux(arr,0,arr.length-1);
       espCollator.setStrength(Collator.SECONDARY); 
    }
    
    private static void quickSortAux(String arr[], int begin, int end) {
    if (begin < end) {
        int partitionIndex = partition(arr, begin, end);
 
        quickSortAux(arr, begin, partitionIndex-1);
        quickSortAux(arr, partitionIndex+1, end);
    }
}
    private static int partition(String arr[], int begin, int end) {
    String pivot = arr[end];
    int i = (begin-1);
 
    for (int j = begin; j < end; j++) {
        if((espCollator.compare(arr[j], pivot)<= 0)){
            i++;
 
            String swapTemp = arr[i];
            arr[i] = arr[j];
            arr[j] = swapTemp;
        }
    }
 
    String swapTemp = arr[i+1];
    arr[i+1] = arr[end];
    arr[end] = swapTemp;
 
    return i+1;
}
    
    //         ____________
    //________/RADIX SORT
    
    
    
    //         ____________
    //________/BUBBLESORT
    
    
    
}
