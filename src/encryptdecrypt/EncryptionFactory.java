package encryptdecrypt;

public class EncryptionFactory {
    public Encryption getEncryption(String alg){
        switch (alg){
            case "shift":
                return new ShiftEncryption();
            case "unicode":
                return new UnicodeEncryption();
            default:
                return null;
        }
    }
}
