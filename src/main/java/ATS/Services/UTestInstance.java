package ATS.Services;

import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import java.lang.reflect.Method;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class UTestInstance {
    private Method main;
    private String data;
    private String result;
    @Rule
    public final TextFromStandardInputStream systemInMock
            = emptyStandardInputStream();

    public UTestInstance(Method main, String data, String result) {
        this.main = main;
        this.data = data;
        this.result = result;
    }

    @Test
    public boolean test(){
        try {
            systemInMock.provideLines(data);
            Assert.assertEquals(result, main);
        }
        catch (AssertionFailedError e){
            return false;
        }
        return true;
    }
}
