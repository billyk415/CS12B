#------------------------------------------------------------------------------
# Billy Kwong
# bqkwong
# 12B/12M
# 1/15/2018
# Makefile
# Makefile for pa1
#------------------------------------------------------------------------------
JAVASRC = Recursion.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = Recursion
CLASSES = Recursion.class
JARFILE = Recursion
SUBMIT = submit cmps012b-pt.w18 pa1
CHECK  = /afs/cats.ucsc.edu/class/cmps012b-pt.w18/pa1/bqkwong
all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)
$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)
submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
	
check: $(CHECK)
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.w18/pa1/bqkwong