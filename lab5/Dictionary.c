// Billy Kwong
// bqkwong
// 12B/12M
// 2/15/18
// Lab5
// Dictionary.c
// Implementation file for Dictionary ADT

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

// private types --------------------------------------------------------------

// NodeObj
typedef struct NodeObj{
	char* key;
    char* value;
    struct NodeObj* next;
	} NodeObj;
	
// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* k, char* v) {
    Node N = malloc(sizeof(NodeObj));
    assert(N!=NULL);
    N->key = k;
    N->value = v;
    N->next = NULL;
    return(N);
}

// freeNode()
// destructor for private Node type
void freeNode(Node* pNode){
    if( pNode!=NULL && *pNode!=NULL ){
        free(*pNode);
        *pNode = NULL;
    }
}

// DictionaryObject
typedef struct DictionaryObj{
    Node root;
    int numPairs;
} DictionaryObj;

// findKey()
Node findKey(Dictionary R, char* k){
    for(Node N = R->root; N!=NULL; N = N->next){
        if(strcmp(N->key, k) == 0){
            return N;
        }
    }
    return NULL;
}

// deleteAll()
// deletes all Nodes in the subtree rooted at N.
void deleteAll(Node N){
    if( N!=NULL ){
        deleteAll(N->next);
        freeNode(&N);
    }
}

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(){
    Dictionary D = malloc(sizeof(DictionaryObj));
    assert(D!=NULL);
    D->root = NULL;
    D->numPairs = 0;
    return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
    if( pD!=NULL && *pD!=NULL ){
        makeEmpty(*pD);
        free(*pD);
        *pD = NULL;
    }
}

// isEmpty()
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
    if( D==NULL ){
        fprintf(stderr,
         "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    return(D->numPairs==0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
    if( D==NULL ){
        fprintf(stderr,
         "Dictionary Error: calling size() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    return(D->numPairs);
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
    Node N;
    if( D==NULL ){
        fprintf(stderr,
         "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    N = findKey(D, k);
    return ( N==NULL ? NULL : N->value );
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
    Node N, A, B;
    if( D==NULL ){
        fprintf(stderr,
         "Dictionary Error: calling insert() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    if( findKey(D, k)!=NULL ){
        fprintf(stderr, "Dictionary Error: cannot insert() duplicate key: \"%s\"\n", k);
        exit(EXIT_FAILURE);
    }
    if(lookup(D,k)==NULL){
        N = newNode(k, v);
        B = NULL;
        A = D->root;
        while(A!=NULL){
            B = A;
            if( strcmp(k, A->key)<0 ){
                A = A->next;
            }
            else{
                A = A->next;
            }
        }
        if(B==NULL){
            D->root = N;
        }
        else if(strcmp(k, B->key) < 0){
            B->next = N;
        }
        else{
            B->next = N;
        }
        D->numPairs++;
    }
}


// delete()
// deletes pair with the key k
void delete(Dictionary D, char* k){
    Node N = findKey(D, k);
    if( D==NULL ){
        fprintf(stderr,
         "Dictionary Error: calling delete() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    if( N==NULL ){
        fprintf(stderr,
         "Dictionary Error: cannot delete() non-existent key: \"%s\"\n", k);
        exit(EXIT_FAILURE);
    }
    if(lookup(D, k) != NULL){
      if(N == D->root){
            D->root = D->root->next;
            N->next = NULL;
         } else {
            Node S = D->root;
            while(S->next != N){
               S = S->next;
            }
            S->next = N->next;
         }
      }
    D->numPairs--;
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
    deleteAll(D->root);
    D->root = NULL;
    D->numPairs = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
    if( D==NULL ){
        fprintf(stderr,
         "Dictionary Error: calling printDictionary() on NULL"
         " Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    for(Node N = D->root; N!=NULL; N = N->next){
        fprintf(out, "%s %s\n", N->key, N->value);
    }
}


























