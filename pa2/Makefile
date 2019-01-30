#------------------------------------------------------------------------------
# Billy Kwong
# bqkwong
# 12B/12M
# 1/25/2018
# Makefile
# Makefile for pa2
#------------------------------------------------------------------------------
JAVASRC = Search.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = Search
CLASSES = Search.class
JARFILE = Search
SUBMIT = submit cmps012b-pt.w18 pa2
CHECK  = /afs/cats.ucsc.edu/class/cmps012b-pt.w18/pa2/bqkwong
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
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.w18/pa2/bqkwong