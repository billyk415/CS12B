#------------------------------------------------------------------------------
# Billy Kwong
# bqkwong
# 12B/12M
# 2/1/2018
# Makefile
# Makefile for lab3
#------------------------------------------------------------------------------
FLAGS = -std=c99 -Wall 
SOURCES = README Makefile FileReverse.c
OBJECTS = FileReverse.o
EXEBIN = FileReverse
SUBMIT = submit cmps012b-pt.w18 lab3
CHECK  = /afs/cats.ucsc.edu/class/cmps012b-pt.w18/lab3/bqkwong
all: $(EXEBIN)

$(EXEBIN): $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES) 
 
clean : 
	rm -f $(EXEBIN) $(OBJECTS) 

submit : $(SOURCES)
	$(SUBMIT) $(SOURCES)
	
check: $(CHECK)
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.w18/lab3/bqkwong