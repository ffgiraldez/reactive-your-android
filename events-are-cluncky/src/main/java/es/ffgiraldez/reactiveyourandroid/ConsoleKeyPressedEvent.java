package es.ffgiraldez.reactiveyourandroid;

/**
 * @author Fernando Franco Giráldez
 */
class ConsoleKeyPressedEvent {

    private final String character;

    public ConsoleKeyPressedEvent(String character) {
        this.character = character;
    }

    public String getCharacter() {
        return character;
    }
}
