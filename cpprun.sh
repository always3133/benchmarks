#g++ -c -pipe -O3 -fomit-frame-pointer -march=native -mfpmath=sse -msse3 --std=c++11 Program.cpp -o Program.o && g++ Program.o -o Program.cpprun -fopenmp
g++ -c -pipe -O3 -fomit-frame-pointer -march=native -mfpmath=sse -msse3 -fopenmp -mfpmath=sse -msse2 --std=c++11 Program.cpp -o Program.o && g++ Program.o -o Program.cpprun -fopenmp
./Program.cpprun
