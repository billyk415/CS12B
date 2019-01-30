// Billy Kwong
// bqkwong
// 12B/12M
// 3/11/2018
// PA5
// DictionaryTest.c

#include <stdio.h>
#include <stdlib.h>
#include "Dictionary.h"

int main(void){
	DictionaryRef D = newDictionary();
	
	insert(D,52,16);
	insert(D,21,81);
	insert(D,34,2);
	insert(D,23,1);
        insert(D,68,15);
        insert(D,112,16);
	insert(D,45,45);
        insert(D,33,67);
        insert(D,20,78);
        insert(D,80,50);
        insert(D,51,32);
        insert(D,69,13);
	
	printDictionary(D,stdout);

	delete(D,51);
	delete(D,45);
	delete(D,69);
	
	printDictionary(D,stdout);
	
	makeEmpty(D);
	printDictionary(D,stdout);

}