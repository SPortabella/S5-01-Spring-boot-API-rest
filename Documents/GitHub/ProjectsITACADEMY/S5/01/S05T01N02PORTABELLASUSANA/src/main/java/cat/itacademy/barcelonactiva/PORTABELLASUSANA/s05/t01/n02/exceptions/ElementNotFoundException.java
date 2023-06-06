package cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n02.exceptions;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(Class type, Integer id) {
        super("Element of " + type.getSimpleName() + " with id " + id + " not found");
    }
}
