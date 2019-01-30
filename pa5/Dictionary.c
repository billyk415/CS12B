// Billy Kwong
// bqkwong
// 12B/12M
// 3/11/2018
// PA5
// Dictionary.c

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

const int tableSize = 101;

typedef struct DictionaryObj* Dictionary;

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
  int sizeInBits = 8*sizeof(unsigned int);
  shift = shift & (sizeInBits - 1);
  if ( shift == 0 ){
    return value;
  }
  return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
  unsigned int result = 0xBAE86554;
  while (*input) {
     result ^= *input++;
     result = rotate_left(result, 5);
  }
  return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
  return pre_hash(key)%tableSize;
}

// NodeObj
typedef struct NodeObj{
  char* key;
  char* value;
  struct NodeObj* next;
} NodeObj;

// Node
typedef struct NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* x, char* y){
  Node N = malloc(sizeof(NodeObj));
  assert(N!=NULL);
  N->key = x;
  N->value = y;
  N->next = NULL;
  return(N);
}

// freeNode()
void freeNode(Node* pN){
   if(pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}

//DictionaryObj
typedef struct DictionaryObj{
  Node* table;
  int numItems;
} DictionaryObj;

// findKey
Node findKey(Dictionary D, char* key){
  Node N = D->table[hash(key)];
  for(; N!=NULL; N = N->next){
    if(strcmp(N->key, key) == 0){
      return N;
    }
  }
  return NULL;
}

// newDictionary()
Dictionary newDictionary(void){
  Dictionary D = malloc(sizeof(DictionaryObj));
  assert(D!=NULL);
  D->table = calloc(tableSize, sizeof(NodeObj));
  assert(D->table!=NULL);
  D->numItems = 0;
  for(int i = 0; i < tableSize; i++){
    D->table[i] = NULL;
  }
  return (D);
}

// freeDictionary()
void freeDictionary(Dictionary* pD){
  if(pD !=  NULL && *pD != NULL){
    free(*pD);
    *pD = NULL;
  }
}

// isEmpty()
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
  return (D->numItems == 0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
  return D->numItems;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
   if(D == NULL){
	  fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary reference\n");	
	  exit(EXIT_FAILURE);
   }
   Node n = findKey(D, k);
   if(n == NULL){
	  return NULL;
   }else{
	  return n -> value;
   }
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
  if(findKey(D, k) != NULL){
    fprintf(stderr, "Key is in use");
    exit(EXIT_FAILURE);
  }
  int index = hash(k);
  Node N = newNode(k, v);
  N->next = D->table[index];
  D->table[index] = N;
  D->numItems++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
   if(D == NULL){
	  fprintf(stderr, "Dictionary Error: calling delete() on NULL Dictionary reference\n");
	  exit(EXIT_FAILURE);
   }
   Node b = findKey(D, k);
   if(b == NULL){
	  printf("Dictionary Error: calling delete() on Dictionary without existing key\n");
	  exit(EXIT_FAILURE);
   }else{
	  int n = hash(k);
	  if(b == D -> table[n]){
		 D -> table[n] = D -> table[n] -> next;
	  }else{
		 Node a = D -> table[n];
		 while(a -> next != b){
		    a = a -> next;
		 }
		 a -> next = b -> next;
	  }
	  freeNode(&b);
	  b = NULL;
	  D -> numItems--;
   }
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
  for(int i = 0; i < tableSize; i++){
    Node N = D->table[i];
  	while(N!=NULL){
      Node M =  N;
      N = N->next;
      freeNode(&M);
      D->table[i] = N;
    }
  }
  D->numItems = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
   Node N;
   if(D == NULL){
	  fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL Dictionary referemce\n");
	  exit(EXIT_FAILURE);
   }
   for(int i = 0; i < tableSize; i++){
	  N = D -> table[i];
	  while(N != NULL){
		 fprintf(out, "%s %s\n" , N -> key, N -> value);
		 N = N -> next;
	  }
   }
}