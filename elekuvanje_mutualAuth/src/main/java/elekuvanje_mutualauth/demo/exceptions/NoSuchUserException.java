package elekuvanje_mutualauth.demo.exceptions;

public class NoSuchUserException extends Exception {
    public NoSuchUserException() {
        super("No such user");
    }
}
