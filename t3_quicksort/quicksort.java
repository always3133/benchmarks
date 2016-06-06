package benchmark;

public class quicksort 
{
	// public static void main(String[] args) {
	// 	int[] x = { 9, 2, 4, 7, 3, 7, 10 };
	// 	System.out.println(Arrays.toString(x));
 
	// 	int low = 0;
	// 	int high = x.length - 1;
 
	// 	quickSort(x, low, high);
	// 	System.out.println(Arrays.toString(x));
	// }
	
	private int[] arr;
	
	public static void sort(int[] arr, int low, int high)
	{
		if (arr == null || arr.length == 0)
			return;
			
		quicksort q = new quicksort();
		q.arr = arr;
		q.doSort(low, high);
	}
 
	private void doSort(int low, int high) 
    {
		if (low >= high)
			return;
 
		// pick the pivot
		int middle = low + (high - low) / 2;
		int pivot = arr[middle];
 
		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (arr[i] < pivot) {
				i++;
			}
 
			while (arr[j] > pivot) {
				j--;
			}
 
			if (i <= j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		}
 
		// recursively sort two sub parts
		if (low < j)
			doSort(low, j);
 
		if (high > i)
			doSort(i, high);
	}
}