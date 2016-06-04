using System;
using System.Diagnostics;

namespace ConsoleApplication
{
    class Elapsed
    {
        public string name {get;private set;}
        private Stopwatch _sw = new Stopwatch();
        public Elapsed(String _name)
        {
            name = _name;
            _sw.Start();
        }
        
        public TimeSpan Done()
        {
            _sw.Stop();
            return _sw.Elapsed;
        }
    }
    public class Program
    {
        public static void Main(string[] args)
        {
            {
                Console.WriteLine("##### N-Body #####");
                NBodySystem dummy = new NBodySystem();
                dummy.Advance(0.01);
                
                Action<Int32> test = (ti) =>
                { 
                    NBodySystem bodies = new NBodySystem();
                    var e = new Elapsed("11");
                    for (int i = 0; i < 50000000; i++) bodies.Advance(0.01);
                    var d = e.Done();
                    Console.WriteLine($"Try #{ti} : {d}");                
                };
                
                for (var i = 0; i < 10; i++)
                    test(i);
            }
            
            Console.WriteLine("\n");
            
            {
                Console.WriteLine("##### Spectral-Norm #####");
                SpectralNorm.RunGame(0);
                
                Action<Int32> test = (ti) =>
                { 
                    var e = new Elapsed("11");
                    var r = SpectralNorm.RunGame(5500);
                    var d = e.Done();
                    //Console.WriteLine("{0:f9}", r);
                    Console.WriteLine($"Try #{ti} : {d}");                
                };
                
                for (var i = 0; i < 10; i++)
                    test(i);
            }
        }
    }
}
