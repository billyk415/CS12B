//-----------------------------------------------------------------------------
//  LinkedList.c
//  This constitutes answers to problems 9 and 10abc on the 12B final review
//  sheet.  An additional function is included called prodList() that returns 
//  the product of all the data values in a linked list.  Also function main()
//  is included, which tests all functions.
//
//  Compile: gcc -std=c99 -o LinkedList LinkedList.c
//
//-----------------------------------------------------------------------------

#include<stdlib.h>
#include<stdio.h>

typedef struct NodeObj{
   int item;
   struct NodeObj* next;
} NodeObj;

typedef NodeObj* Node;

Node newNode(int x){
   Node N = malloc(sizeof(NodeObj));
   N->item = x;
   N->next = NULL;
}

void freeNode(Node* pN){
   if(pN!=NULL && *pN!=NULL){
      free(*pN);
      *pN = NULL;
   }
}

int sumList(Node H){
   if(H==NULL){
      return 0;
   }else{
      return H->item + sumList(H->next);
   }
}

int prodList(Node H){
   if(H==NULL) return 1;
   else return H->item*prodList(H->next);
}

void clearList(Node H){
   if(H!=NULL){
      clearList(H->next);
      freeNode(&H);
   }
}

int main(void){
   int i, j;
   Node H, N;

   H = newNode(1);
   for(i=2; i<=10; i++){
      N = newNode(i);
      N->next = H;
      H = N;
   }

   printf("%d\n", sumList(H));  // prints 1+2+3+4+5+6+7+8+9+10 = 55
   printf("%d\n", prodList(H)); // prints 10! = 3628800

   clearList(H);
   H = NULL;

   return(EXIT_SUCCESS);
}
