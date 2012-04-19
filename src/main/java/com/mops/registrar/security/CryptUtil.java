package com.mops.registrar.security;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

/**
 * Provides security operations
 * 
 * @author dylants
 * 
 */
@Component
public class CryptUtil {

    /**
     * Encodes a message, returning the encoded String. If input is {@literal null}, will return {@literal null}
     * 
     * @param message
     *            The message to encode
     * @return The encoded message or {@literal null} if {@literal null} message input
     */
    public String encode(String message) {
        // sanity check
        if (message == null) {
            return null;
        }

        // convert the String to a byte[]
        byte[] byteArray = message.getBytes();

        // encode away
        return new String(Base64.encodeBase64(byteArray));
    }

    /**
     * Decodes a message, return the decoded String or {@literal null} if any problems occur.
     * 
     * @param message
     *            The message to decode
     * @return The decoded message or {@literal null} if any problems occur.
     */
    public String decode(String message) {
        // sanity check
        if (message == null) {
            return null;
        }

        // convert the String to a byte[]
        byte[] byteArray = message.getBytes();

        // decode the message to a String
        return new String(Base64.decodeBase64(byteArray));
    }
}