scalac -d . -optimise -target:jvm-1.7 t1_nbody/NBodySystem.scala t2_spectralnorm/spectralnorm.scala Program.scala
java -server -XX:+TieredCompilation -XX:+AggressiveOpts  -Xbootclasspath/a:/root/scala-2.11.7/lib/scala-library.jar:/root/scala-2.11.7/lib/akka-actors.jar:/root/scala-2.11.7/lib/typesafe-config.jar scalabench.Program 50000000
