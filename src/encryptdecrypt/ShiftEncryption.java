package encryptdecrypt;

public class ShiftEncryption implements Encryption {
    private final int shiftRange = 'z' - 'a' + 1;

    @Override
    public String encryptMessage(String message, int key) {
        char[] msgCharArray = message.toCharArray();
        int msgLength = msgCharArray.length;
        char[] encryptedMessage = new char[msgLength];
        for (int i = 0; i < msgLength; i++) {
            char c = msgCharArray[i];
            if (Character.isLetter(c)) {
                char e = (char) (c + key);
                if ((Character.isLowerCase(c) && e > 'z') || (Character.isUpperCase(c) && e > 'Z')) {
                    e -= shiftRange;
                }
                if ((Character.isLowerCase(c) && e < 'a') || (Character.isUpperCase(c) && e < 'A')) {
                    e += shiftRange;
                }
                encryptedMessage[i] = e;
            } else {
                encryptedMessage[i] = c;
            }
        }
        return new String(encryptedMessage);
    }

    @Override
    public String decryptMessage(String message, int key) {
        return encryptMessage(message, -key);
    }
}
