//-----------------------------------------------------------------------------
// Billy Kwong
// bqkwong
// 12B/12M
// 2/7/2018
// DuplicateKeyException.java
// pa3
// Exception error for input of a key that already exists
//-----------------------------------------------------------------------------

public class DuplicateKeyException extends RuntimeException{
   public DuplicateKeyException(String s){
      super(s);
   }
}