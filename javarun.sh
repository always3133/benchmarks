javac -d . t1_nbody/NBodySystem.java t2_spectralnorm/spectralnorm.java Program.java
java -server -XX:+TieredCompilation -XX:+AggressiveOpts benchmark.Program

