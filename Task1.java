import java.util.Arrays;
import java.util.Scanner;
public class Task1 {
	public static void main(String[] args){
		Scanner reader = new Scanner(System.in);
		System.out.println("1) Pick a number for the size of the array to be sorted");
		System.out.println("2) Generate the times that each method takes for sizes 1-10000");
		int input = reader.nextInt();
		
		//User chooses size of array to be sorted
		if (input == 1){
			System.out.println("What do you want the size of the array to be");
			int size = reader.nextInt();
			
			System.out.println("Merge Sort");
			int[] arr = createArray(size);
			System.out.println(Arrays.toString(arr));

			int[] sorted = mergeSort(arr);

			System.out.println(Arrays.toString(sorted));
			System.out.println();
			
			System.out.println("Quick sort");
			System.out.println(Arrays.toString(arr));
			quickSort(arr,0,arr.length-1);
			System.out.println(Arrays.toString(arr));
		}
		
		//System runs from 0-10,000 values incrementing by 1000 and calculates the time
		else if (input == 2){
			String fileWord = "";
			System.out.println("Size \t Merge \t Quick");
			long start, mergeTime, quickTime;
		
			for(int i = 0; i <= 10000; i+=100){
				int[] arr = createArray(i);
				
				start = System.nanoTime();
				mergeSort(arr);
				mergeTime = System.nanoTime() - start;
				mergeTime*=.001; //convert from nanoseconds to microseconds
				
				start = System.nanoTime();
				quickSort(arr,0,arr.length-1);
				quickTime = System.nanoTime() - start;
				quickTime*=.001; //convert from nanoseconds to microseconds
				
				System.out.println(i + "\t" + mergeTime + "\t" + quickTime);
			}
			
		}
		else{
			System.out.println("Invalid input!!");
		}
			
	}
	
	/**
	 * @return an array filled with random integers from 1-10000000
	 */
	static int[] createArray(int size){
		int[] list = new int[size];
		int randNum;
		for (int i = 0; i < list.length; i++){
			randNum = (int)(Math.random()*10000000);
			list[i] = randNum;
		}
		return list;
	}
	
	/**
	 * @param list
	 * @return a sorted list using merge sort method
	 */
	static int[] mergeSort(int[] list){
		int length = list.length;
		
		//if array cannot be divided anymore, return it
		if (length <= 1){
			return list;
		}
		
		//create two separate arrays
		int[] a = new int[length/2];
		int[] b = new int[length - (length/2)];
		
		//fill the first array with the elements in the first half of the original array
		for (int i = 0; i < a.length; i++){
			a[i] = list[i];
		}
		
		//fill the second array with the elements in second half of the original array
		for (int i = 0; i < b.length; i++){
			b[i] = list[i + (length/2)];
		}
		
		//merge the two separate arrays recursively
		return merge(mergeSort(a),mergeSort(b));
		
	}
	
	/**
	 * @param array a
	 * @param array b
	 * @return an array of the elements from a and b
	 */
	static int[] merge(int[] a, int[] b){
		//create empty array of both
		int[] c = new int[a.length+b.length];
		
		
		int i = 0;
		int j = 0;
		
		//for each element in both of the arrays, put them in the array c
		for (int k = 0; k < c.length; k++){
			if (i >= a.length){
				c[k] = b[j++];
			}
			else if(j >= b.length){
				c[k] = a[i++];
			}
			else if (a[i] <= b[j]){
				c[k] = a[i++];
			}
			else{
				c[k] = b[j++];
			}
		}
		return c;
	}
	
	/**
	 * @param arr
	 * @param low
	 * @param high
	 * @return sorted array using the quick sort method
	 */
	static void quickSort(int[] arr, int low, int high){
		//if the array is empty, return
		if (arr == null || arr.length == 0){
			return;
		}
		
		//if the two pointers are equal or cross each other, return
		if (low >= high){
			return;
		}
		
		//set the pivot to the middle of the array
		int middle = low + (high-low)/2;
		int pivot = arr[middle];
		
		//set the low to i and the high to j
		int i = low;
		int j = high;
		
		//while the low pointer is lower than the high pointer
		while (i<= j){
			
			//while the array at i is less than the pivot
			while (arr[i] < pivot){
				//increment the low
				i++;
			}
			
			//while the array at j is greater than the pivot
			while (arr[j] > pivot){
				//decrement the high
				j--;
			}
			
			//if low is less than high
			if (i <= j){
				//switch the elements from low pointer and high pointer
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
			
			//if low is less than the low pointer
			if (low < j){
				//recursively call quicksort with the high pointer
				quickSort(arr,low,j);
			}
			
			//if high is greater than the high pointer
			if (high > i){
				//recursively call quicksort with the low pointer
				quickSort(arr,i,high);
			}
		}
	}

	
	
}
