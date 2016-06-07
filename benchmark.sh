rm averages.txt

echo "============= result =============" >> averages.txt
sh cpprun.sh
sh dotnetrun.sh
sh monorun.sh
sh javarun.sh
sh scalarun.sh

cat averages.txt
