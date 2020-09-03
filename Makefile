

run    : compil
	java DnsApp
	rm -f *.class
compil :
	javac src/*.java
	mv src/*class .

make clean : 
	rm -f *.class
	rm -f src/*.class