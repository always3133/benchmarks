package benchmark;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

public final class Program 
{
    private static final NumberFormat formatter = new DecimalFormat ("#.000000000");
    private static final NumberFormat elapsedformatter = new DecimalFormat ("00.000000");
    
    public static void WriteFileAppend(String filename, String content)
    {
        try
        {
            File file = new File(filename);
            
            if(!file.exists()) {
                file.createNewFile();
            }
            
            //true = append file
            FileWriter fileWritter = new FileWriter(file.getName(),true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(content);
            bufferWritter.close();
        } 
        catch(IOException e)
        {
    		e.printStackTrace();
    	}
    }
    
    public static float getMaxValue(float[] numbers) {  
        float maxValue = numbers[0];  
        for(int i=1;i < numbers.length;i++){  
            if(numbers[i] > maxValue){  
                maxValue = numbers[i];  
            }  
        }  
        return maxValue;  
    }  

    public static float getMinValue(float[] numbers) {  
        float minValue = numbers[0];  
        for(int i=1;i<numbers.length;i++){  
            if(numbers[i] < minValue){  
                minValue = numbers[i];  
            }  
        }  
        return minValue;  
    } 
    
    public static float getAccumulate(float[] numbers) {
        float acc = 0;
        for (int i = 0; i < numbers.length; i++)
            acc += numbers[i];
        return acc;
    }
    
    static float NBodyTest(int ti)
    {
        NBodySystem bodies = new NBodySystem();
        long startTime = System.currentTimeMillis();
        for (int i=0; i< 50000000; ++i)
            bodies.advance(0.01);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.printf("Try #%d : 00:00:%s, %1.9f\n", ti, elapsedformatter.format((float)estimatedTime/1000), bodies.energy());
        
        return (float)estimatedTime/1000;
    }
    
    static float SpectralNormTest(int ti)
    {
        long startTime = System.currentTimeMillis();
        double r = spectralnorm.spectralnormGame(5500);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.printf("Try #%d : 00:00:%s, %1.9f\n", ti, elapsedformatter.format((float)estimatedTime/1000), r);
        
        return (float)estimatedTime/1000;
    }
    
    static float QuicksortTest(int ti)
    {
        int n = 50000000;
        Random random = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = random.nextInt(n);
        long startTime = System.currentTimeMillis();
        quicksort.doSort(array, 0, n-1);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.printf("Try #%d : 00:00:%s, %d\n", ti, elapsedformatter.format((float)estimatedTime/1000), array[1000]);
        
        return (float)estimatedTime/1000;
    }
    
    public static void main(String[] args) 
    {
        System.out.println("============ Java ============");
        {
            System.out.println("##### N-Body #####");
            NBodySystem dummy = new NBodySystem();
            dummy.advance(0.01);

            float[] times = new float[10];
            for (int i = 0; i < 10; i++)
                times[i] = NBodyTest(i);
           
            float minv = getMinValue(times);
            float maxv = getMaxValue(times);
            float sum = getAccumulate(times) - minv - maxv;
            
            String content = String.format("Java [N-Body] : 00:00:%s\n", elapsedformatter.format(sum/(times.length-2)));
            WriteFileAppend("averages.txt", content);
        }
        System.out.printf("\n");
        {
            System.out.println("##### Spectral-Norm #####");
            spectralnorm.spectralnormGame(0);
            
            float[] times = new float[10];
            for (int i = 0; i < 10; i++)
                times[i] = SpectralNormTest(i);
                
            float minv = getMinValue(times);
            float maxv = getMaxValue(times);
            float sum = getAccumulate(times) - minv - maxv;
            
            String content = String.format("Java [S-Norm] : 00:00:%s\n", elapsedformatter.format(sum/(times.length-2)));
            WriteFileAppend("averages.txt", content);
        }
        System.out.printf("\n");
        {
            System.out.println("##### Quicksort #####");
            int[] t = {1, 2, 3};
            quicksort.doSort(t, 0, 2);
            
            float[] times = new float[10];
            for (int i = 0; i < 10; i++)
                times[i] = QuicksortTest(i);
                
            float minv = getMinValue(times);
            float maxv = getMaxValue(times);
            float sum = getAccumulate(times) - minv - maxv;
            
            String content = String.format("Java [Q-Sort] : 00:00:%s\n", elapsedformatter.format(sum/(times.length-2)));
            WriteFileAppend("averages.txt", content);
        }
    }
}