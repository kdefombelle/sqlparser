package fr.kdefombelle.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.misc.Interval;

public class CaseChangingCharStream
    implements CharStream
{

    final CharStream stream;
    final boolean upper;

    public CaseChangingCharStream(CharStream stream, boolean upper)
    {
        this.stream = stream;
        this.upper = upper;
    }

    public String getText(Interval interval)
    {
        return stream.getText(interval);
    }

    public void consume()
    {
        stream.consume();
    }

    public int LA(int i)
    {
        int c = stream.LA(i);
        if(c <= 0)
        {
            return c;
        }
        if(upper)
        {
            return Character.toUpperCase(c);
        } else
        {
            return Character.toLowerCase(c);
        }
    }

    public int mark()
    {
        return stream.mark();
    }

    public void release(int marker)
    {
        stream.release(marker);
    }

    public int index()
    {
        return stream.index();
    }

    public void seek(int index)
    {
        stream.seek(index);
    }

    public int size()
    {
        return stream.size();
    }

    public String getSourceName()
    {
        return stream.getSourceName();
    }
}
