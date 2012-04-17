package com.mops.registrar.security;

import java.io.IOException;

import org.springframework.stereotype.Component;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Provides security operations
 * 
 * @author dylants
 * 
 */
@Component
public class CryptUtil {

    private BASE64Encoder encoder = null;
    private BASE64Decoder decoder = null;

    public CryptUtil() {
        encoder = new BASE64Encoder();
        decoder = new BASE64Decoder();
    }

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
        return this.encoder.encode(byteArray);
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

        // decode the message to a byte[]
        try {
            byte[] byteArray = this.decoder.decodeBuffer(message);
            if (byteArray == null) {
                return null;
            }
            return byteArray.toString();
        } catch (IOException e) {
            // TODO add logging
            return null;
        }
    }
}