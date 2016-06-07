package scalabench {
    object Program 
    {
        def main(args: Array[String]) = {
            println("============ Scala ============");
            {
                println("##### N-Body #####");
                def fn(ti:Int) : Float = {
                    var n = 50000000
                    var j = new JovianSystem();
                    val t0 = System.nanoTime()
                    while (n > 0) { j.advance(0.01); n -= 1 }
                    val t1 = System.nanoTime()
                    //println("Elapsed time: " + (t1 - t0).toFloat/1000000 + "s")
                    println(printf("Try #%d : 00:00:%09.6f", ti, (t1 - t0).toFloat/1000000000))

                    return (t1 - t0).toFloat/1000000000
                }
                var times = List[Float]();
                for (i <- 0 to 9)
                    times ::= fn(i);

                println(times.sorted)
                var sum = times.sorted.drop(1).dropRight(1).foldRight(0.0f)((m: Float, n: Float) => m + n);
                var average = sum/8;
                println(sum, average);

                var content = printf("Scala [N-Body] : 00:00:%09.6f", average)
                println(content)
            }
            {
                println("##### S-Norm #####");
                def fn(ti:Int) : Float = {
                    val t0 = System.nanoTime()
                    var s = new spectralnorm(5500)
                    val t1 = System.nanoTime()
                    //println("Elapsed time: " + (t1 - t0).toFloat/1000000 + "s")
                    println(printf("Try #%d : 00:00:%09.6f", ti, (t1 - t0).toFloat/1000000000))

                    return (t1 - t0).toFloat/1000000000
                }
                var times = List[Float]();
                for (i <- 0 to 9)
                    times ::= fn(i);

                println(times.sorted)
                var sum = times.sorted.drop(1).dropRight(1).foldRight(0.0f)((m: Float, n: Float) => m + n);
                var average = sum/8;
                println(sum, average);

                var content = printf("Scala [S-Norm] : 00:00:%09.6f", average)
                println(content)
            }
        }
    }
}