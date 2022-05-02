JFLAGS = -g
JC = javac
JVM= java 
FILE= configuracion.txt
MAIN= Stage3

RM = rm

*.class:*.java
	$(JC) $(JFLAGS) *.java


run: $(MAIN).class
	$(JVM) $(MAIN) $(FILE)


clean:
	rm *.class
