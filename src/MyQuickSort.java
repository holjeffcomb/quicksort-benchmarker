// This class contains the entirety of the sorting code.
// This code was largely written by Rajat Mishra and an unnamed author, who published this on the
// website GeeksForGeeks.com. Minor changes were made to their code to better implement into mine.

public class MyQuickSort implements SortInterface {

    private int count;
    private long time;

    MyQuickSort()
    {
        this.count = 0;
        this.time = 0;
    }

    // Initiating method for recursive quick sort
    public void recursiveSort(int[] arr, int low, int high)
    {
        count++; // Critical operation count - incremented every time recursive function is called

        if (low < high)
        {
            int pi = partition(arr, low, high); // Determine partition index

            // Recursive calls with arrays before and after partition index as arguments
            recursiveSort(arr, low, pi-1);
            recursiveSort(arr, pi+1, high);
        }
    }

    // Method to perform partition for recursive sort
    private int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high]; // Use highest value in array as pivot
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than the pivot
            if (arr[j] < pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

    public void iterativeSort(int[] arr, int l, int h)
    {
        // Create an auxiliary stack
        int[] stack = new int[h - l + 1];

        // initialize top of stack
        int top = -1;

        // push initial values of l and h to stack
        stack[++top] = l;
        stack[++top] = h;

        // Keep popping from stack while is not empty
        while (top >= 0) {
            // Pop h and l
            h = stack[top--];
            l = stack[top--];

            // Set pivot element at its correct position
            // in sorted array
            int p = partitionIterative(arr, l, h);

            // If there are elements on left side of pivot,
            // then push left side to stack
            if (p - 1 > l) {
                stack[++top] = l;
                stack[++top] = p - 1;
            }

            // If there are elements on right side of pivot,
            // then push right side to stack
            if (p + 1 < h) {
                stack[++top] = p + 1;
                stack[++top] = h;
            }
        }
    }

    private int partitionIterative(int[] arr, int low, int high)
    {
        int pivot = arr[high];

        // index of smaller element
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            count++;
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public long getTime() {
        return time;
    }

    void setTime(long time) {
        this.time = time;
    }

    void resetCount() {
        this.count = 0;
    }

    public int getCount() {
        return count;
    }
}
