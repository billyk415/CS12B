#------------------------------------------------------------------------------
# Billy Kwong
# bqkwong
# 12B/12M
# 1/24/2018
# Makefile
# Makefile for lab2
#------------------------------------------------------------------------------
JAVASRC = FileReverse.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = FileReverse
CLASSES = FileReverse.class
JARFILE = FileReverse
SUBMIT = submit cmps012b-pt.w18 lab2
CHECK  = /afs/cats.ucsc.edu/class/cmps012b-pt.w18/lab2/bqkwong
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
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.w18/lab2/bqkwong