QUICK SORT BENCHMARK 

DIRECTIONS: 
1) Run Quicksort.java
2) Run GenerateReport.java

Introduction

Quick Sort is a divide and conquer algorithm that utilizes an element in the list called a pivot, and partitions around that pivot. In the worst case, Quick Sort operates at O(n2) time complexity. However, the worst case is extremely rare to occur, even more so as n increases. As we shall see, the average case is also the best case with Quick Sort, and its time complexity is actually O(n log n).
For my project, I have used two versions of the Quick Sort algorithm; one which is recursive, and one which is iterative. Both algorithms use two separate methods: one for initiating the call to sort a partition, and one for selecting a pivot within the partition and sorting elements around it (both select the last element in the partition as the pivot). 
Note: The recursive algorithm was written by Rajat Mishra, and was obtained from the site geeksforgeeks.com/quick-sort/ and the iterative algorithm was written by an unknown author and obtained from geeksforgeeks.com/iterative-quick-sort/ 

Recursive Pseudo Code
```
/*
low = low index of partition (initiates at 0)
high = high index of partition (initiates at array.length-1)
*/

quickSort(arr[], low, high) 
{
    if (low < high)
    {
        pi = partition(arr, low, high); // set pivot element at correct position

        quickSort(arr, low, pi - 1);  // before pi
        quickSort(arr, pi + 1, high); // after pi
    }
}

partition (arr[], low, high)
{
    pivot = arr[high]; // element to be placed as pivot (in this case, rightmost element)
    i = low - 1  // Index of smaller element
    for (elementsInPartition)
    {
        if (currentElement < pivot) // If current element is smaller than the pivot
        {
            i++;    // increment index of smaller element
            swap arr[i] and currentElement;
        }
    }
    swap (arr[i+1], pivot) // swap index of smaller element and pivot
    return i+1 // return index of pivot
}
```

Iterative Pseudo Code

```
quickSortIterative(arr[], low, high)
{
    Stack = {low, high}

    while (!stackIsEmpty}
    {
	pop(high);
	pop(low);

	p = partitionIterative(arr[], low, high); // set pivot element at correct position

	if (elementsOnLeftOfPivot){
		push(toLeftOfStack);
	}
	if (elementsOnRightOfPivot){
		Push(toRightOfStack);
	}
}

partitionIterative(int[] arr, int low, int high)
{
    pivot = arr[high]; // set pivot to higher element in partition

    nextLowerElement = arr[low-1]; // next lower element
    nextHigherElement = arr[low+1]; // next higher element
    for (elements in arr) 
    {
        if (currentElement <= pivot) {
            swap(arr[index], currentElement);
        }
    }
    swap (nextHigherElement, pivot); 

    return index + 1;
}
```

Big-O Analysis
•	Worst Case Running Time
When analyzing the Big-O of the Quick Sort algorithms, it is helpful to look at the worst case scenarios first to understand the potential, but unlikely, inefficiencies of them. In the worst case scenario, the partitioning method selects a pivot that happens to be either the minimum or maximum element in the partition every time it is called. The amount of times the partition() method is called would then be exactly n2 times. 
•	Best Case Running Time
Fortunately, the probability of choosing a minimum or maximum pivot each time is rare, and becomes increasingly so as n increases. In the best case scenario, the median element in the partition is selected as the pivot each time, which would effectively minimize the amount of subsequent calls to partition(). The time complexity of this scenario is O(n log n), a much more efficient scenario than the worst case.
•	Average Case Running Time
Since it is impossible to select a median pivot each time without having to iterate through the partition and sacrifice efficiency, we can at least select a random element and hope for the best. As long as the order of the elements in the partition are not in any kind of order, we can pick any arbitrary index in the partition as our pivot and effectively get a random pivot. In the algorithms I used for my project, the last index of the partition is used as the pivot. 
As it turns out, when a random pivot is used each time, the average running time of the quick sort algorithm starts to look very similar to the best case running time as the n-value increases. Therefore, the average running time of this algorithm is the same as its best case running time of O(n log n).
JVM Warm Up
The approach I have used to warm up the JVM was taken from an article by Baeldung (2018). The article explains that the JVM operates on a lazy loading scheme, in which all classes that are used in a process are loaded into memory in three separate steps. Since the average response time is significantly slower at the beginning of a Java process than at any other time, a warm up process is necessary in order to get accurate and precise time measurements.
The warm up technique I use is the manual implementation outlined by Baeldung, in which 100,000 instances of a dummy class are created using a for loop. Upon starting, the application’s first task is to initiate the warm up sequence before any of the benchmarking procedures can commence. 
To test the effectiveness of the warm up procedure, I ran 20 test runs: 10 with the warm up and 10 without the warm up. For each run, I recorded the time in nanoseconds of the first quick sort time recorded in the recursive output text file. I then averaged the 10 trial runs, and found that the not only did the warm up times fare better overall, but in one test run (test run 7) without the warm up, a recorded time was double what an expected time would be. See the table below:

 
![alt text](https://github.com/holjeffcomb/quicksort-benchmarker/blob/main/images/fig1.png?raw=true)

Figure 1 - Ten test runs with and without warm up code



Critical Operation

For the recursive algorithm, I chose the critical operation to be whenever the recursive function is actually called. This value will be representative of the number of partitions have to be made, which would in turn be representative of the overall efficiency of the algorithm, with a lower critical operation count being better.
The iterative algorithm, on the other hand, has the critical operation located at the innermost for loop, which in my project is in the partitionIterative() method and essentially counts each time an operation, whether swap or leave in place, is made on an element in the partition. This value, again, makes a good indicator for the overall efficiency of the algorithm, with the lower count being better.
 
![alt text](https://github.com/holjeffcomb/quicksort-benchmarker/blob/main/images/fig2.png?raw=true)
 
Figure 2 - Average Critical Operation Count - Recursive function

![alt text](https://github.com/holjeffcomb/quicksort-benchmarker/blob/main/images/fig3.png?raw=true)
 
Figure 3 - Average Critical Operation Count - Iterative function

Time

![alt text](https://github.com/holjeffcomb/quicksort-benchmarker/blob/main/images/fig4.png?raw=true)
 
Figure 4 - Average Time for Recursive function

![alt text](https://github.com/holjeffcomb/quicksort-benchmarker/blob/main/images/fig5.png?raw=true)
 
Figure 5 - Average Time for Iterative function

Performance Comparison
Looking at both sets of graphs, we can see that both algorithms perform very similarly in terms of how time changes as size increases. The average time for both algorithms seem to have a spike around the 600 size range, and then continue on a gradual increase afterwards. More importantly, upon closer inspection we can see that the average times of the recursive quick sort are significantly faster in these test runs.  At each size interval, the recursive algorithm performs roughly 5000 ns faster on average.


Critical Operation and Time Comparison

The average critical operation counts differ significantly between both algorithms. This is most likely because the critical operation chosen for the recursive function only occurs whenever a new call to the function is made, whereas the critical operation for the iterative function occurs whenever an element is either moved or left in place, which happens much more frequently. 
The graphs both show a similar trend in how critical count grows as size increases. Even though the scales of the critical operation counts are different, both graphs show a more or less linear growth. However, upon closer inspection, we can observe that the recursive algorithm grows at a more linear rate than the iterative algorithm. In the recursive algorithm, if n >= roughly 400, the critical operation count grows almost at a 1 to 1 rate. However, in the iterative function for n >= 400, we can observe that the critical operation count grows at a rate slightly faster than 1 to 1. 
I would be curious to see how the critical operation count changes with a wider range of n values for the test runs.

Significance of Coefficient of Variance
The coefficient of variance has to do with the dispersion of data points around a mean value. In other words, the higher the coefficient of variance for a given element, the further away from an expected average value that element is. A lower coefficient of variance would generally mean that the data is reflecting the expected trend in data, and there are very little or no radical outlier data points.
When comparing the different coefficients of variance for the recursive algorithm and the iterative algorithm, there is a notable difference in the values. If we look at just the data in the Coef Count columns, we observe that the recursive algorithm has an overall lower coefficient of variance, and is thereby less sensitive. This means that the recursive quick sort will have a more predictable number of critical steps taken to execute depending on input size. 
The Coef Time columns, on the other hand, show a more irregular trend in execution times. Interestingly, the data when n = 100 shows a wildly varied and almost random coefficient of variance in both algorithms. This could be because the warm up process is not functioning properly, or that there is some unseen overhead work taking place no matter how big the input size is. As input size increases, the time data becomes more stable.

![alt text](https://github.com/holjeffcomb/quicksort-benchmarker/blob/main/images/fig6.png?raw=true)
 
Figure 6 - Recursive Output Report

![alt text](https://github.com/holjeffcomb/quicksort-benchmarker/blob/main/images/fig7.png?raw=true)
 
Figure 7 - Iterative Output Report


Comparison of Results vs. Big-Θ analysis 
The Big O analysis of the quick sort algorithm is O(n log n). If we were to see this reflected in the data gathered from my test runs, then the growth of the values in the Avg Time columns would grow at a slower rate as the input value increased. Instead, what we observe in the data is a positive trend but with different growth rates when the input size is less than 600 compared to when it is more than 600. In both the recursive and iterative algorithm test run data, the increase in time relative to n increases sharply until about n = 600, then a drop in execution time when n = 700, and then a slower growth rate continuing on. 
As the data stands right now, I would say that it is inconclusive as to whether or not these algorithms truly do reflect their expected Big O analysis. Upon closer inspection, when n > 600, it is possible that the growth rate is growing at a logarithmic rate. According to the data in the Avg Count columns, the amounts by which the time increases do appear get smaller in the last few rows. It is only roughly 4 data points for each algorithm though, so it is not a big enough data pool to make a conclusive statement. Perhaps expanding this project to handle a much wider range of input values would yield a more conclusive result.

Conclusion
In conclusion, the benchmark software seems to be functioning properly with calculating the data, but it is possible to improve it by mitigating the irregular recorded times in both algorithms when n <= 600. This kind of anomaly does not exist if observing the quick sort algorithm mathematically, so this suggests that it is a computer issue and does not accurately reflect benchmarking of the algorithm alone. Furthermore, the fact that the critical operation counts provide a smooth positive trend, I believe that this is an issue with the warm up method and not the quick sort code itself. Because if this issue, I was not able to accurately measure the time complexity of the simulation.
What we can see from the data, however, is that the recursive algorithm appears to perform better than the iterative algorithm in a number of categories. First of all, the coefficients of variation for the critical operations approach zero relatively quickly as n increases, which shows that the algorithm seems to be performing as expected and is picking a suitable pivot. The overall times recorded are relatively faster than the iterative counterparts as well, although the coefficients of variation for time are less stable than for the critical counts (it is worth noting that the recursive function appears slightly more stable than the iterative function). This may be due to the improper warm up routine, at least for the first few test runs. 

References
Data Structure and Algorithms - Quick Sort. (n.d.). Retrieved October 14, 2020, from https://www.tutorialspoint.com/data_structures_algorithms/quick_sort_algorithm.htm
The basic algorithm. (n.d.). Retrieved October 14, 2020, from https://algs4.cs.princeton.edu/23quicksort/
Analysis of quicksort (article) | Quick sort. (n.d.). Retrieved October 14, 2020, from https://www.khanacademy.org/computing/computer-science/algorithms/quick-sort/a/analysis-of-quicksort
Baeldung. (2018, April 15). How to Warm Up the JVM. Retrieved October 14, 2020, from https://www.baeldung.com/java-jvm-warmup

