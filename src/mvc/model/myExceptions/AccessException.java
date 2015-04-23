
package mvc.model.myExceptions;

public class AccessException extends Exception {

  
    public AccessException() {
    }

    public AccessException(String msg) {
        msg = "Wrong access";
    }
}
