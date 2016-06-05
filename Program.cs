using System;
using System.Diagnostics;
using System.Linq;
using System.Collections.Generic;
using System.IO;

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
            var target = "C#";
            if (args.Length > 0)
                target = args[0];
            Console.WriteLine($"============ {target} ============");
            {
                Console.WriteLine("##### N-Body #####");
                NBodySystem dummy = new NBodySystem();
                dummy.Advance(0.01);
                
                Func<Int32,TimeSpan> test = (ti) =>
                { 
                    NBodySystem bodies = new NBodySystem();
                    var e = new Elapsed("11");
                    for (int i = 0; i < 50000000; i++) bodies.Advance(0.01);
                    var d = e.Done();
                    Console.WriteLine($"Try #{ti} : {d}"); 
                    return d;               
                };
                
                var times0 = new List<TimeSpan>(10);
                for (var i = 0; i < 10; i++)
                    times0.Add(test(i));
                
                var times = times0.Where(x => x != times0.Min() && x != times0.Max()).ToList();
                var average = new TimeSpan((long)times.Select(ts => ts.Ticks).Average());
                //Console.WriteLine($"{target} [N-Body] : {average}\n");
                using (StreamWriter sw = File.AppendText("averages.txt")) 
                {
                    sw.WriteLine($"{target} [N-Body] : {average}");
                }	
            }
            {
                Console.WriteLine("##### Spectral-Norm #####");
                SpectralNorm.RunGame(0);
                
                Func<Int32,TimeSpan> test = (ti) =>
                { 
                    var e = new Elapsed("11");
                    var r = SpectralNorm.RunGame(5500);
                    var d = e.Done();
                    //Console.WriteLine("{0:f9}", r);
                    Console.WriteLine($"Try #{ti} : {d}");
                    return d;
                };
                
                var times0 = new List<TimeSpan>(10);
                for (var i = 0; i < 10; i++)
                    times0.Add(test(i));
                    
                var times = times0.Where(x => x != times0.Min() && x != times0.Max()).ToList();
                var average = new TimeSpan((long)times.Select(ts => ts.Ticks).Average());
                //Console.WriteLine($"{target} [Spectral-Norm] : {average}");
                using (StreamWriter sw = File.AppendText("averages.txt")) 
                {
                    sw.WriteLine($"{target} [S-Norm] : {average}");
                }
            }
        }
    }
}
