//-----------------------------------------------------------------------------
//  BST.c
//  This constitutes solutions to Problem 1 on the 12B final review sheet, plus
//  a little more.  We've included a constructor newNode() and a function main()
//  to test the functions.
//
//  Compile: gcc -std=c99 -o BST BST.c
//
//-----------------------------------------------------------------------------

#include<stdlib.h>
#include<stdio.h>

typedef struct NodeObj{
   int key;
   struct NodeObj* left;
   struct NodeObj* right;
} NodeObj;

typedef NodeObj* Node;

Node newNode(int x){
   Node N = malloc(sizeof(NodeObj));
   N->key = x;
   N->left = N->right = NULL;
   return N;
}

void printPreOrder(Node N){
   if( N!=NULL ){
      printf("%d ", N->key);
      printPreOrder(N->left);
      printPreOrder(N->right);
   }
}

void printInOrder(Node N){
   if( N!=NULL ){
      printInOrder(N->left);
      printf("%d ", N->key);
      printInOrder(N->right);
   }
}

void printPostOrder(Node N){
   if( N!=NULL ){
      printPostOrder(N->left);
      printPostOrder(N->right);
      printf("%d ", N->key);
   }
}

int main(){
   
   // insert 6, 8, 3, 9, 4, 2 into an initially empty BST
   Node root = newNode(6);
   root->right = newNode(8);
   root->left = newNode(3);
   root->right->right = newNode(9);
   root->left->right = newNode(4);
   root->left->left = newNode(2);

   printPreOrder(root);
   printf("\n");
   printInOrder(root);
   printf("\n");
   printPostOrder(root);
   printf("\n");
}