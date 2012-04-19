package com.mops.registrar.security;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * {@link CryptUtil} tests
 * 
 * @author dylants
 * 
 */
@RunWith(Parameterized.class)
public class CryptUtilTest {

    private static final String TEST_MESSAGE = "mymessage";
    private static final String TEST_MESSAGE_WITH_SPACES = "this is my message";
    private static final String TEST_MESSAGE_WITH_SPECIAL_CHARS = "th1s i$ m* m3SS@~e";

    private String message = null;
    private CryptUtil cryptUtil = null;

    @Parameters
    public static Collection<Object[]> buildData() {
        return Arrays.asList(new Object[][] { { TEST_MESSAGE }, { TEST_MESSAGE_WITH_SPACES },
                { TEST_MESSAGE_WITH_SPECIAL_CHARS } });
    }

    public CryptUtilTest(String message) {
        this.message = message;
    }

    @Before
    public void setupBefore() {
        this.cryptUtil = new CryptUtil();
    }

    @Test
    public void testEncodeDecode() {
        String encodedMessage = this.cryptUtil.encode(this.message);
        String decodedMesesage = this.cryptUtil.decode(encodedMessage);

        Assert.assertEquals("messages must match", this.message, decodedMesesage);
    }

    @Test
    public void testEncodeDecodeEncode() {
        String encodedMessage = this.cryptUtil.encode(this.message);
        String decodedMesesage = this.cryptUtil.decode(encodedMessage);
        String encodedBackMessage = this.cryptUtil.encode(decodedMesesage);

        Assert.assertEquals("messages must match", encodedMessage, encodedBackMessage);
    }
}
