package cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n01.Exceptions;

public class ElementNotFoundException extends RuntimeException{

    public ElementNotFoundException(Class type, Integer id) {
        super("Element of " + type.getSimpleName() + " with id " + id + " not found");
    }
}
