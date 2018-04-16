set -e
cd "$(dirname "$0")"
mkdir -p bin
find ./src -name *.java | javac -d bin -classpath "lib/antlr-runtime-4.7.1.jar" @/dev/stdin
