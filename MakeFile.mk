JC = javac
JR = java
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

.PHONY: algo1 algo2 algo3 algo4 algo5 algo6 clean

all: $(classes)

$(classes): %.class: %.java
	$(JC) $<

algo1: Solution1_Approach1.class
	$(JR) Solution1_Approach1

algo2: Solution1_Approach2.class
	$(JR) Solution1_Approach2

algo3: Solution1_Approach3.class
	$(JR) Solution1_Approach3

algo4: Solution2_Approach1.class
	$(JR) Solution2_Approach1

algo5: Solution2_Approach2.class
	$(JR) Solution2_Approach2

algo6: Solution3_Approach1.class
	$(JR) Solution3_Approach1

#algo7: Solution3_Approach2.class
#	$(JR) Solution3_Approach2

clean:
	rm -f *.class