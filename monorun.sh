mcs -r:Mono.Simd.dll -optimize+ -platform:x64 -out:Program.csharp_run Program.cs t1_nbody/nbody.cs t2_spectralnorm/spectralnorm.cs t3_quicksort/quicksort.cs
mono --llvm --gc=sgen Program.csharp_run "C# mono"
rm Program.csharp_run
