#include <iostream>
#include <algorithm>
#include <chrono>
#include <vector>
#include "t1_nbody/nbody.h"
#include "t2_spectralnorm/spectralnorm.h"
#include "t3_quicksort/quicksort.h"
#include <fstream>
#include <time.h>
#include <cstdlib>

void WriteFileAppend(std::string file, std::string content) 
{  
    std::ofstream outfile;

    outfile.open(file, std::ios_base::app);
    outfile << content;
}

std::vector<float> WithoutHiLo(std::vector<float>& orig)
{
     std::sort(orig.begin(), orig.end());
     return std::vector<float>(&orig[1], &orig[orig.size()-1]);
}

int main(int argc, char** argv) 
{
    std::cout << "============ C++ ============" << std::endl;
    {
        std::cout << "##### N-Body #####" << std::endl;
        auto fn = [=](int ti)
        {
            NBodySystem bodies;
            auto start = std::chrono::system_clock::now();
            for (int i=0; i< 50000000; ++i)
                bodies.advance(0.01);
            auto end = std::chrono::system_clock::now();
            auto elapsed = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);
            
            printf("Try #%d : 00:00:%09.6f, %1.9lf\n", ti, (float)elapsed.count()/1000, bodies.energy());
            return (float)elapsed.count()/1000;
        };
        
        std::vector<float> times0;
        for (auto i = 0; i < 10; i++)
            times0.push_back(fn(i));
            
        auto times = WithoutHiLo(times0);
        float sum = std::accumulate(std::begin(times), std::end(times), 0.0);
        auto average = sum/(times.size());
        
        char buf[100];
        snprintf(buf, sizeof(buf), "C++ [N-Body] : 00:00:%09.6f\n", average);
        WriteFileAppend("averages.txt", std::string(buf));
    }
    printf("\n");
    {
        std::cout << "##### Spectral-Norm #####" << std::endl;
        auto fn = [=](int ti)
        {
            auto start = std::chrono::system_clock::now();
            auto r = spectral_game(5500);
            auto end = std::chrono::system_clock::now();
            auto elapsed = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);
            
            printf("Try #%d : 00:00:%09.6f, %1.9lf\n", ti, (float)elapsed.count()/1000, r);
            return (float)elapsed.count()/1000;
        };
        
        std::vector<float> times0;
        for (auto i = 0; i < 10; i++)
            times0.push_back(fn(i));
            
        auto times = WithoutHiLo(times0);
        float sum = std::accumulate(std::begin(times), std::end(times), 0.0);
        auto average = sum/(times.size());
        
        char buf[100];
        snprintf(buf, sizeof(buf), "C++ [S-Norm] : 00:00:%09.6f\n", average);
        WriteFileAppend("averages.txt", std::string(buf));
    }
    printf("\n");
    {
        std::cout << "##### Quicksort #####" << std::endl;
        srand(time(0));
        auto fn = [=](int ti)
        {
            int n = 50000000;
            int* array = new int[n];
            for (int i = 0; i < n; i++)
                array[i] = rand()%n;
            
            auto start = std::chrono::system_clock::now();
            quickSort(array, 0, n-1);
            auto end = std::chrono::system_clock::now();
            auto elapsed = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);
            
            printf("Try #%d : 00:00:%09.6f, %d\n", ti, (float)elapsed.count()/1000, array[1000]);
            return (float)elapsed.count()/1000;
        };
        
        std::vector<float> times0;
        for (auto i = 0; i < 10; i++)
            times0.push_back(fn(i));
            
        auto times = WithoutHiLo(times0);
        float sum = std::accumulate(std::begin(times), std::end(times), 0.0);
        auto average = sum/(times.size());
        
        char buf[100];
        snprintf(buf, sizeof(buf), "C++ [Q-Sort] : 00:00:%09.6f\n", average);
        WriteFileAppend("averages.txt", std::string(buf));
    }
}