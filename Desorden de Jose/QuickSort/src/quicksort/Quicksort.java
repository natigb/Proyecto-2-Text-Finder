/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksort;

import java.text.Collator;
import java.util.Locale;

/**
 *
 * @author Jose
 * Based on: https://www.baeldung.com/java-quicksort
 */
public class Quicksort {
    
    Collator espCollator = Collator.getInstance(Locale.getDefault());
    
    public void quicksort(String arr[]){
        quickSortAux(arr,0,arr.length-1);
        espCollator.setStrength(Collator.SECONDARY);
    }
    
    public void quickSortAux(String arr[], int begin, int end) {
    if (begin < end) {
        int partitionIndex = partition(arr, begin, end);
 
        quickSortAux(arr, begin, partitionIndex-1);
        quickSortAux(arr, partitionIndex+1, end);
    }
}
    private int partition(String arr[], int begin, int end) {
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
    
    
}
