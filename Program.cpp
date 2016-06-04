// class Elapsed
// {
// public:
//     Elapsed()
//     {
//         start = std::chrono::system_clock::now();
//     }
    
//     std::chrono::duration<int, std::chrono::milliseconds> Done()
//     {
//         return std::chrono::duration_cast<std::chrono::milliseconds>(std::chrono::system_clock::now() - start);
//     }
// private:
//     std::chrono::system_clock::time_point start;
// }

#include <iostream>
#include <chrono>
#include "t1_nbody/nbody.h"
#include "t2_spectralnorm/spectralnorm.h"
int main(int argc, char** argv) {
    {
        auto n = 50000000;
        NBodySystem bodies;
        printf("%.9f\n", bodies.energy());
        
        auto start = std::chrono::system_clock::now();
    // Elapsed e = new Elapsed();
        for (int i=0; i<n; ++i)
            bodies.advance(0.01);
        // auto d = e.Done();
        // delete e;
        auto end = std::chrono::system_clock::now();
        auto elapsed = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);
        
        printf("%.9f\n", bodies.energy());
        std::cout << elapsed.count() << '\n';
    }
    
    // {
    //     auto n = 25000000;
    //     auto start = std::chrono::system_clock::now();
    //     Repeater(alu).run( "ONE", "Homo sapiens alu", n*2 );
    //     Randomized(iub).run( "TWO", "IUB ambiguity codes", n*3 );
    //     Randomized(homosapiens).run( "THREE", "Homo sapiens frequency", n*5 );
    //     auto end = std::chrono::system_clock::now();
    //     auto elapsed = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);
    //     std::cout << elapsed.count() << '\n';
    // }
    {
        auto start = std::chrono::system_clock::now();
        auto r = spectral_game(5500);
        auto end = std::chrono::system_clock::now();
        auto elapsed = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);
        printf("%.9f\n", r);
        std::cout << elapsed.count() << '\n';
    }
}