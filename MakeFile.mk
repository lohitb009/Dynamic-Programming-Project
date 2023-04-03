JC = javac
JR = java

sources = $(wildcard *.java)
classes = $(sources:.java=.class)

.PHONY: algo1 algo2 algo3 algo4 algo5a algo5b algo6 clean

all: $(classes)

$(classes): %.class: %.java
	$(JC) $<

algo1: Algorithm1.class
	$(JR) Algorithm1

algo2: Algorithm2.class
	$(JR) Algorithm2

algo3: Algorithm3.class
	$(JR) Algorithm3

algo4: Algorithm4.class
	$(JR) Algorithm4

algo5a: Algorithm5a.class
	$(JR) Algorithm5a

algo5b: Algorithm5b.class
	$(JR) Algorithm5b

algo6: Algorithm6.class
	$(JR) Algorithm1

algo7: Algorithm7.class
	$(JR) Algorithm7

clean:
	rm -f *.class