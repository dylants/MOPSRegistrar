package com.mops.registrar.security;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link CryptUtil} tests
 * 
 * @author dylants
 * 
 */
public class CryptUtilTest {

    private static final String TEST_MESSAGE = "mymessage";
    private static final String TEST_MESSAGE_WITH_SPACES = "this is my message";
    private static final String TEST_MESSAGE_WITH_SPECIAL_CHARS = "th1s i$ m* m3SS@~e";

    private CryptUtil cryptUtil = null;

    @Before
    public void setupBefore() {
        this.cryptUtil = new CryptUtil();
    }

    @Test
    public void testEncodeDecode() {
        String encodedMessage = this.cryptUtil.encode(TEST_MESSAGE);
        String decodedMesesage = this.cryptUtil.decode(encodedMessage);

        Assert.assertEquals("messages must match", TEST_MESSAGE, decodedMesesage);
    }

    @Test
    public void testEncodeDecodeEncode() {
        String encodedMessage = this.cryptUtil.encode(TEST_MESSAGE);
        String decodedMesesage = this.cryptUtil.decode(encodedMessage);
        String encodedBackMessage = this.cryptUtil.encode(decodedMesesage);

        Assert.assertEquals("messages must match", encodedMessage, encodedBackMessage);
    }
}
