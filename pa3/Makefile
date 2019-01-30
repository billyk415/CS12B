#------------------------------------------------------------------------------
#  Billy Kwong
#  bqkwong
#  12B/12M
#  2/7/2018
#  Makefile for CMPS 12B pa3
#  This Makefile uses wildcards and pattern substitution, which have not been
#  explained in any previous assignment.  See the GNU Make tutorial 
#
#       http://www.gnu.org/software/make/manual/make.html
#
#  to learn more about these features. Alternatively, create a Makefile 
#  without these features.
#------------------------------------------------------------------------------

JAVAC      = javac 
MAINCLASS  = DictionaryClient
JAVASRC    = $(wildcard *.java)
SOURCES    = $(JAVASRC) makefile README Dictionary.java DictionaryTest.java DuplicateKeyException.java KeyNotFoundException.java DictionaryInterface.java DicionaryClient.java
CLASSES    = $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES = $(patsubst %.class, %*.class, $(CLASSES))
JARFILE    = $(MAINCLASS) 
SUBMIT     = submit cmps012b-pt.w18 pa3
CHECK = ls /afs/cats.ucsc.edu/class/cmps012b-pt.w18/pa3/

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(JARCLASSES)
	chmod +x $(JARFILE)
	rm Manifest

%.class: %.java
	$(JAVAC) $<

clean:
	rm -f *.class $(JARFILE)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)

check:
	$(CHECK)
