package benchmark;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public final class Program 
{
    private static final NumberFormat formatter = new DecimalFormat ("#.000000000");
    private static final NumberFormat elapsedformatter = new DecimalFormat ("00.000000");
    
    static void NBodyTest(int ti)
    {
        NBodySystem bodies = new NBodySystem();
        long startTime = System.currentTimeMillis();
        for (int i=0; i< 50000000; ++i)
            bodies.advance(0.01);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.printf("Try #%d : 00:00:%s\n", ti, elapsedformatter.format((float)estimatedTime/1000));
    }
    
    static void SpectralNormTest(int ti)
    {
        long startTime = System.currentTimeMillis();
        double r = spectralnorm.spectralnormGame(5500);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.printf("Try #%d : 00:00:%s\n", ti, elapsedformatter.format((float)estimatedTime/1000));
    }
    
    public static void main(String[] args) 
    {
        {
            System.out.println("##### N-Body #####");
            NBodySystem dummy = new NBodySystem();
            dummy.advance(0.01);

            for (int i = 0; i < 10; i++)
                NBodyTest(i);
        }
        System.out.printf("\n");
        {
            System.out.println("##### Spectral-Norm #####");
            spectralnorm.spectralnormGame(0);
            
            for (int i = 0; i < 10; i++)
                SpectralNormTest(i);
        }
    }
}