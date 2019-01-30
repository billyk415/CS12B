#------------------------------------------------------------------------------
# Billy Kwong
# bqkwong
# 12B/12M
# 1/15/2018
# Makefile
# Another Makefile with macros
#------------------------------------------------------------------------------
JAVASRC = HelloUser.java HelloUser2.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = HelloUser2
CLASSES = HelloUser.class HelloUser2.class
JARFILE = Hello
SUBMIT = submit cmps012b-pt.w18 lab1
CHECK  = /afs/cats.ucsc.edu/class/cmps012b-pt.w18/lab1/bqkwong
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
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.w18/lab1/bqkwong