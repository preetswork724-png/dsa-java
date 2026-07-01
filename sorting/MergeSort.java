package sorting;

public class MergeSort {

    public int[] partition(int[] arr, int start, int end){

        if(end - start == 1) return arr;

        int mid = start + (end - start) / 2;

        int[] left = partition(arr, start, mid);
        int[] right = partition(arr, mid, end);

        return mergeSort(left, right);
    }

    public int[] mergeSort(int[] left, int[] right){
        int[] merged = new int[left.length + right.length];
        int i = 0, j = 0 , k = 0;

        while(i < left.length && j < right.length){
            if(left[i] < right[j]){
                merged[k] = left[i] ;
                i++;
            }else{
                merged[k] = right[j];
                j++;
            }
            k++ ;
        }

        while(i < left.length){
            merged[k++] = left[i++];
        }

        while(j < right.length){
            merged[k++] = right[j++];
        }

        return merged;
    }
}
