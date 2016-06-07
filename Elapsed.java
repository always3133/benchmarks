package benchmark;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

public final class Elapsed
{
    private static final NumberFormat elapsedformatter = new DecimalFormat ("00.000000");
    private long startTime = 0;
    private long estimatedTime = 0;
    public Elapsed()
    {
        long startTime = System.currentTimeMillis();
    }

    public float Done()
    {
        estimatedTime = System.currentTimeMillis() - startTime;
        return ((float)estimatedTime)/1000;
    }

    public void Print(int ti)
    {
        System.out.printf("Try #%d : 00:00:%s\n", ti, elapsedformatter.format((float)estimatedTime/1000));
    }
} 