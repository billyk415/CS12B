//-----------------------------------------------------------------------------
// Billy Kwong
// bqkwong
// 12B/12M
// 2/7/2018
// KeyNotFoundException.java
// pa3
// Exception error for when a key is not found
//-----------------------------------------------------------------------------

public class KeyNotFoundException extends RuntimeException{
   public KeyNotFoundException(String s){
      super(s);
   }
}