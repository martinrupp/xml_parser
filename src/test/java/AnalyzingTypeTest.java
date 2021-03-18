import org.junit.Assert;

public class AnalyzingTypeTest {

    @org.junit.Test
    public void analyze() {
        AnalyzingType x = new AnalyzingType();
        Assert.assertEquals( x.type, AnalyzingType.Type.EMPTY );
        x.analyze( "2" );
        Assert.assertEquals( x.type, AnalyzingType.Type.INTEGER );
        Assert.assertEquals( x.example, "2" );
        x.analyze( "32" );
        Assert.assertEquals( x.type, AnalyzingType.Type.INTEGER );
        Assert.assertEquals( x.example, "2" );


        x.analyze( "2.4" );
        Assert.assertEquals( x.type, AnalyzingType.Type.DOUBLE );
        Assert.assertEquals( x.example, "2.4" );

        x.analyze("Hello" );
        Assert.assertEquals( x.type, AnalyzingType.Type.STRING );
        Assert.assertEquals( x.example, "Hello" );
    }

}