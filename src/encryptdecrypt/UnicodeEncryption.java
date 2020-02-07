package encryptdecrypt;

public class UnicodeEncryption implements Encryption {
    @Override
    public String encryptMessage(String message, int key) {
        char[] msgCharArray = message.toCharArray();
        int msgLength = msgCharArray.length;
        char[] encryptedMessage = new char[msgLength];
        for (int i = 0; i < msgLength; i++) {
            encryptedMessage[i] = (char) (msgCharArray[i] + key);
        }
        return new String(encryptedMessage);
    }

    @Override
    public String decryptMessage(String message, int key) {
        return encryptMessage(message, -key);
    }
}
