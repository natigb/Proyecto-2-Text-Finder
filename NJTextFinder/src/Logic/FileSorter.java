package Logic;

import java.text.Collator;
import java.util.Locale;
import java.util.Arrays;

/**
 *
 * @author Jose and Natalia
 */
public class FileSorter {
    private static final Collator espCollator = Collator.getInstance(Locale.getDefault());
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
    public static void sortByName(String arr[]){
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
    
    public static void sortBySize(int arr[]){
        sortBySizeAux(arr, arr.length);
    }
    private static void sortBySizeAux(int arr[], int n){
        int m = getMax(arr, n);
        
        for (int exp=1; m/exp>0;exp*=10)
            countSort(arr, n, exp);
    }
    private static int getMax(int arr[], int n){
        int max = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > max)
                max = arr[i];
        return max;
    }
    private static void countSort(int arr[], int n, int exp){
        int output[] = new int[n];
        int i;
        int count[] = new int[10];
        
        Arrays.fill(count,0);
        for (i=0; i<n; i++)
            count[(arr[i]/exp)%10]++;
        
        
        for (i=1; i<10;i++)
            count[i] += count[i-1];
            
        
        for (i=n-1; i>=0;i--){
            output[count[((arr[i]/exp)%10)]-1]= arr[i];
            count [(arr[i]/exp)%10]--;
        }
        
        for (i=0; i<n;i++)
            arr[i] = output[i];    
    }
    
    
    
    
    //         ____________
    //________/BUBBLESORT
    
    
    
}
