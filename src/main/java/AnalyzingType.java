public class AnalyzingType {
    public String example;
    public enum Type {
        STRING("STRING"), INTEGER("INTEGER"), DOUBLE("DOUBLE"), EMPTY ( "empty" );

        final String name;
        Type( String name )
        {
            this.name = name;
        }
    }
    public Type type = Type.EMPTY;

    public void analyze(String s)
    {
        if( s.isEmpty() ) return;
        if( type == Type.EMPTY )
        {
            type = Type.INTEGER;
            example = s;
        }
        if( type == Type.INTEGER)
        {
            try{
                Integer.parseInt(s);
            } catch( Exception e )
            {
                type = Type.DOUBLE;
                example = s;
            }
        }
        if( type == Type.DOUBLE)
        {
            try{
                Double.parseDouble(s);
            } catch( Exception e )
            {
                type = Type.STRING;
                example = s;
            }
        }
    }
    public void toStringBuilder(StringBuilder sb)
    {
        if (example != null) {
            sb.append("(e.g. " + example + ")");
        }

        sb.append( ", type = " );
        sb.append( type.name );
        sb.append( '\n' );
    }
}
