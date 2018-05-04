set -e
cd "$(dirname "$0")"
export RUN="java -classpath ./lib/antlr-runtime-4.7.1.jar:./bin xng.wrapper.XWrapper testsrc.mx --semantic -vvv"
cat > testsrc.mx
$RUN
