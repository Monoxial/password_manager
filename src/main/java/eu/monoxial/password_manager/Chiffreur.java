package eu.monoxial.password_manager;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.spec.SecretKeySpec;

/**
 * La classe Chiffreur est chargé de chiffrer et de déchiffrer des objets
 * sérialisable à partir d'un algorithme de chiffrement.
 *
 * @author Monoxial
 */
public class Chiffreur {

    /**
     * L'algorithme de chiffrement
     */
    private final static String CIPHER = "Blowfish";
    /**
     * Tableau de byte contenant la clef pour le chiffrement
     */
    private static byte[] key;

    /**
     * Constructeur de Chiffreur
     *
     * @param _key La clef pour le chiffrement qui va être haché
     */
    Chiffreur(char[] _key) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-384");
            key = messageDigest.digest(toBytes(_key));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Chiffreur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Chiffre un objet puis le redirige sur un flux de sortis
     *
     * @param object L'objet a chiffré
     * @param ostream Flux de sortis
     */
    public static void encrypt(Serializable object, OutputStream ostream) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException {
        try {
            // Taille de la clef entre 32 à 448 bits
            var sks = new SecretKeySpec(key, CIPHER);

            // Création du "chiffreur"
            Cipher cipher = Cipher.getInstance(CIPHER);
            cipher.init(Cipher.ENCRYPT_MODE, sks);
            //Chiffrement de l'objet
            var sealedObject = new SealedObject(object, cipher);

            // On écrit l'objet chiffré sur le flux 
            var cos = new CipherOutputStream(ostream, cipher);
            try (ObjectOutputStream outputStream = new ObjectOutputStream(cos)) {
                outputStream.writeObject(sealedObject);
            }
        } catch (IllegalBlockSizeException e) {
            throw e;
        }
    }

    /**
     * Déchiffre un objet à partir d'un flux
     *
     * @return Un objet déchiffré
     * @param istream Flux d'entrée
     */
    public static Object decrypt(InputStream istream) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        try {
            SecretKeySpec sks = new SecretKeySpec(key, CIPHER);
            Cipher cipher = Cipher.getInstance(CIPHER);
            cipher.init(Cipher.DECRYPT_MODE, sks);

            var cipherInputStream = new CipherInputStream(istream, cipher);
            SealedObject sealedObject;
            try (java.io.ObjectInputStream inputStream = new ObjectInputStream(cipherInputStream)) {
                sealedObject = (SealedObject) inputStream.readObject();
            }
            return sealedObject.getObject(cipher);
        } catch (ClassNotFoundException | IllegalBlockSizeException | BadPaddingException e) {
            return null;
        }
    }

    /**
     * Convertis des char[] en byte[] sans passer par des Strings
     *
     * @return Un tableau de byte
     * @param chars Un tableau de caractères
     */
    private static byte[] toBytes(char[] chars) {
        var charBuffer = CharBuffer.wrap(chars);
        var byteBuffer = StandardCharsets.UTF_8.encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
                byteBuffer.position(), byteBuffer.limit());
        Arrays.fill(byteBuffer.array(), (byte) 0);
        return bytes;
    }

}
