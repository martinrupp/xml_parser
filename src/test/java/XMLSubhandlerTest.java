import org.junit.Assert;
import org.junit.Test;

public class XMLSubhandlerTest {

    @Test
    public void skipWhitespace() {
        Assert.assertEquals( XMLSubhandler.skipWhitespace( "a \nbc".toCharArray(), 1, 5), 2);

        Assert.assertEquals( XMLSubhandler.skipWhitespace( " \nbc".toCharArray(), 0, 5), 2);
        Assert.assertEquals( XMLSubhandler.skipWhitespace( "  ".toCharArray(), 0, 2), 2);
        Assert.assertEquals( XMLSubhandler.skipWhitespace( "abc  ".toCharArray(), 3, 2), 2);
    }
}