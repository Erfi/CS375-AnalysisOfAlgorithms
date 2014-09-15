public class Sort
{

    public Sort()
    {
    }

    public static int[] callMethod(int n, int A[])
    {
        int B[] = null;
        switch(n)
        {
        case 1: // '\001'
            B = method1(A);
            break;

        case 2: // '\002'
            B = method2(A);
            break;

        case 3: // '\003'
            B = method3(A);
            break;

        case 4: // '\004'
            B = method4(A);
            break;

        case 5: // '\005'
            B = method5(A);
            break;

        case 6: // '\006'
            B = method6(A);
            break;
        }
        return B;
    }

    public static long getMethodCount(int n)
    {
        switch(n)
        {
        case 1: // '\001'
            return method1Steps;

        case 2: // '\002'
            return method2Steps;

        case 3: // '\003'
            return method3Steps;

        case 4: // '\004'
            return method4Steps;

        case 5: // '\005'
            return method5Steps;

        case 6: // '\006'
            return method6Steps;
        }
        return 0L;
    }

    private static int[] method1(int A[])
    {
        method1Steps = 0L;
        int data[] = new int[A.length];
        for(int i = 0; i < data.length; i++)
        {
            data[i] = A[i];
            method1Steps++;
        }

        for(int i = 1; i < data.length; i++)
        {
            method1Steps += 2L;
            if(data[i] >= data[i - 1])
                continue;
            int temp = data[i];
            method1Steps++;
            int j = i;
            boolean found;
            do
            {
                j--;
                data[j + 1] = data[j];
                method1Steps += 2L;
                if(j == 0)
                {
                    found = true;
                } else
                {
                    method1Steps++;
                    found = data[j - 1] <= temp;
                }
            } while(!found);
            data[j] = temp;
            method1Steps++;
        }

        return data;
    }

    private static int[] method2(int A[])
    {
        method2Steps = 0L;
        int temp[] = new int[A.length];
        int data[] = new int[A.length];
        for(int i = 0; i < data.length; i++)
        {
            data[i] = A[i];
            method2Steps++;
        }

        method2Util1(data, 0, data.length - 1, temp);
        return data;
    }

    private static void method2Util1(int data[], int start, int end, int temp[])
    {
        if(start < end)
        {
            int mid = (start + end) / 2;
            method2Util1(data, start, mid, temp);
            method2Util1(data, mid + 1, end, temp);
            method2Util2(data, start, mid, end, temp);
        }
    }

    private static void method2Util2(int A[], int start, int mid, int end, int B[])
    {
        int ANext1 = start;
        int ANext2 = mid + 1;
        int BNext = start;
        while(ANext1 <= mid && ANext2 <= end) 
        {
            if(A[ANext1] < A[ANext2])
                B[BNext] = A[ANext1++];
            else
                B[BNext] = A[ANext2++];
            BNext++;
            method2Steps += 4L;
        }
        for(int i = ANext1; i <= mid; i++)
        {
            B[(BNext + i) - ANext1] = A[i];
            method2Steps += 2L;
        }

        for(int i = ANext2; i <= end; i++)
        {
            B[(BNext + i) - ANext2] = A[i];
            method2Steps += 2L;
        }

        for(int i = start; i <= end; i++)
        {
            A[i] = B[i];
            method2Steps += 2L;
        }

    }

    private static int[] method3(int A[])
    {
        method3Steps = 0L;
        long temp = method2Steps;
        int data[] = method2(A);
        method3Steps = method2Steps;
        method2Steps = temp;
        for(long i = 2L * method3Steps; i < (long)(0x1c9c380 - 2 * A.length); i++)
        {
            temp = A[0];
            method3Steps++;
        }

        return data;
    }

    private static int[] method4(int A[])
    {
        method4Steps = 0L;
        int data[] = new int[A.length];
        for(int i = 0; i < data.length; i++)
        {
            data[i] = A[i];
            method4Steps++;
        }

        method4Util(data, 0, data.length - 1);
        return data;
    }

    private static void method4Util(int data[], int start, int end)
    {
        int pivot = data[start];
        method4Steps++;
        int i = start;
        int j = end;
        do
        {
            while(data[i] < pivot) 
            {
                i++;
                method4Steps++;
            }
            while(pivot < data[j]) 
            {
                j--;
                method4Steps++;
            }
            if(i <= j)
            {
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
                method4Steps += 4L;
                i++;
                j--;
            }
        } while(i <= j);
        if(start < j)
            method4Util(data, start, j);
        if(i < end)
            method4Util(data, i, end);
    }

    private static int[] method5(int A[])
    {
        method5Steps = 0L;
        int data[] = new int[A.length];
        for(int i = 0; i < data.length; i++)
        {
            data[i] = A[i];
            method5Steps++;
        }

        for(int i = data.length - 1; i >= 1; i--)
        {
            int indexOfMax = 0;
            for(int j = 1; j <= i; j++)
            {
                method5Steps++;
                if(data[j] > data[indexOfMax])
                    indexOfMax = j;
            }

            int temp = data[i];
            data[i] = data[indexOfMax];
            data[indexOfMax] = temp;
            method5Steps += 4L;
        }

        return data;
    }

    public static int[] method6(int A[])
    {
        method6Steps = 0L;
        int data[] = new int[A.length];
        System.arraycopy(A, 0, data, 0, data.length);
        method6Steps += A.length;
        for(int i = data.length - 1; i >= 0; i--)
        {
            boolean swapped = false;
            for(int j = 0; j < i; j++)
            {
                method6Steps += 2L;
                if(data[j] > data[j + 1])
                {
                    swap(data, j, j + 1);
                    method6Steps += 4L;
                    swapped = true;
                }
            }

            if(!swapped)
                return data;
        }

        return data;
    }

    private static void swap(int data[], int j, int i)
    {
        int temp = data[j];
        data[j] = data[i];
        data[i] = temp;
    }

    private static long method1Steps;
    private static long method2Steps;
    private static long method3Steps;
    private static long method4Steps;
    private static long method5Steps;
    private static long method6Steps;
}