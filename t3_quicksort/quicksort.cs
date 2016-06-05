using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
 
public class Quicksort
{
    public static void DoSort(Int32[] elements, int left, int right)
    {
        if (elements == null || elements.Length == 0)
			return;
 
		if (left >= right)
			return;
            
        int i = left, j = right;
        var pivot = elements[(left + right) / 2];

        while (i <= j)
        {
            while (elements[i] < pivot)
            {
                i++;
            }

            while (elements[j] > pivot)
            {
                j--;
            }

            if (i <= j)
            {
                // Swap
                var tmp = elements[i];
                elements[i] = elements[j];
                elements[j] = tmp;

                i++;
                j--;
            }
        }

        // Recursive calls
        if (left < j)
        {
            DoSort(elements, left, j);
        }

        if (i < right)
        {
            DoSort(elements, i, right);
        }
    }
}