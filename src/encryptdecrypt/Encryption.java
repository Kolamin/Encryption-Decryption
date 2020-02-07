package encryptdecrypt;

public interface Encryption {
    String encryptMessage(String message, int key);

    String decryptMessage(String message, int key);
}
