javac -d . t1_nbody/NBodySystem.java t2_spectralnorm/spectralnorm.java t3_quicksort/quicksort.java Program.java
java -server -XX:+TieredCompilation -XX:+AggressiveOpts benchmark.Program

