package Logic;

import LinkedList.LinkedList;
import LinkedList.Node;
import java.text.Collator;
import java.util.Locale;
import java.util.Arrays;

/**
 *
 * @author Jose and Natalia
 */
public class FileSorter {
    private static final Collator espCollator = Collator.getInstance(Locale.getDefault());
 
    public static LinkedList<Document> sortDocumentsBy(LinkedList<Document> documents,SortBy criterion ){
        
        Document[] arr = toArray(documents);
        LinkedList<Document> sortedList = new LinkedList<>();
        switch(criterion){
            case Name:
                sortByName(arr);
                
                for (Document DocTemp : arr){
                    sortedList.insertLast(DocTemp);
                }
                break;
            case Size:
                sortBySize(arr);
  
                for (Document DocTemp : arr){
                    sortedList.insertFirst(DocTemp);
                }
                break;
            case Date:
                sortByDate(arr);
  
                for (Document DocTemp : arr){
                    sortedList.insertLast(DocTemp);
                }
                break;      
        
        
        }
        return sortedList;
    } 
         
    //         ____________
    //________/QUICKSORT
    /**
     * Funcion pricipal que recibe una array de palabras y las organiza con quicksort en criterio alfabetico
     * @param arr Lista de palabras a ordenar.
     */
    private static void sortByName(Document arr[]){
       espCollator.setStrength(Collator.SECONDARY); 
       quickSort(arr,0,arr.length-1);
       System.out.println("sorted by name!");
    }
    
    private static void quickSort(Document arr[], int begin, int end) {
    if (begin < end) {
        int partitionIndex = partition(arr, begin, end);
 
        quickSort(arr, begin, partitionIndex-1);
        quickSort(arr, partitionIndex+1, end);
    }
}
    private static int partition(Document arr[], int begin, int end) {
    String pivot = arr[end].getName();
    int i = (begin-1);
 
    for (int j = begin; j < end; j++) {
        if((espCollator.compare(arr[j].getName(), pivot)<= 0)){
            i++;
 
            Document swapTemp = arr[i];
            arr[i] = arr[j];
            arr[j] = swapTemp;
        }
    }
 
    Document swapTemp = arr[i+1];
    arr[i+1] = arr[end];
    arr[end] = swapTemp;
 
    return i+1;
}
    
    //         ____________
    //________/RADIX SORT
    
    /**
     * Funcion pricipal que recibe una array de numeros y las organiza con radixSort en criterio de tamaño
     * @param arr Lista de numeros a ordenar.
     */
    
    private static void sortBySize(Document arr[]){
        radixSort(arr, arr.length);
        System.out.println("sorted by size!");
    }
    private static void radixSort(Document arr[], int n){
        int m = getMax(arr, n);
        
        for (int exp=1; m/exp>0;exp*=10)
            countSort(arr, n, exp);
    }
    
    private static int getMax(Document arr[], int n){
        int max = arr[0].getSize();
        for (int i = 1; i < n; i++)
            if (arr[i].getSize() > max)
                max = arr[i].getSize();
        return max;
    }
    
    private static void countSort(Document arr[], int n, int exp){
        Document output[] = new Document[n];
        int i;
        int count[] = new int[10];
        
        Arrays.fill(count,0);
        for (i=0; i<n; i++)
            count[(arr[i].getSize()/exp)%10]++;
        
        
        for (i=1; i<10;i++)
            count[i] += count[i-1];
            
        
        for (i=n-1; i>=0;i--){
            output[count[((arr[i].getSize()/exp)%10)]-1]= arr[i];
            count [(arr[i].getSize()/exp)%10]--;
        }
        
        for (i=0; i<n;i++)
            arr[i] = output[i];
    }
    
    
    //         ____________
    //________/BUBBLESORT
    
    /**
     * Funcion pricipal que recibe una array de numeros y las organiza con bubbleSort a criterio de fecha
     * @param arr Lista de numeros a ordenar.
     *
     */
    
    private static void sortByDate(Document arr[]){
        bubbleSort(arr, arr.length);
        System.out.println("sorted by date!");
    
}
    private static void bubbleSort(Document[] arr, int length) {

        int n = length;
        Document temp;

        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(arr[j-1].getDate() > arr[j].getDate()){
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                    }
                }
            }
        }
    
    /**
     * Metedo que retorna un array a partir de la listas enlazada.
     * @param documents
     * @return Un array de la data de los nodos de la lista enlazada. 
     */
    public static Document[] toArray(LinkedList<Document> documents){
        
        Document[] newArray = new Document[documents.getSize()]; 
        Node currentNode = documents.getHead();
        int indx = 0;
        while (currentNode.getNext()!= null){
            newArray[indx] = (Document)currentNode.getData();
            currentNode = currentNode.getNext();
            indx++;
        }
        newArray[indx] = (Document)currentNode.getData();
        Arrays.toString(newArray);
        return newArray;
        }
    }
