package fr.kdefombelle.antlr;

import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import fr.kdefombelle.antlr.CaseChangingCharStream;
import fr.kdefombelle.antlr.PlSqlLexer;
import fr.kdefombelle.antlr.PlSqlParser;
import fr.kdefombelle.antlr.PlSqlParserListener;
import fr.kdefombelle.antlr.TestPlSqlParserListener;

public class AntlrTest {
	
	@Test
	public void testPlSqlGrammar() throws IOException {
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("test.sql");
	    CharStream s = CharStreams.fromStream(is);
	    CaseChangingCharStream upper = new CaseChangingCharStream(s, true);
	    Lexer lexer = new PlSqlLexer(upper);
	    final CommonTokenStream tokenStream = new CommonTokenStream(lexer);
	    PlSqlParser parser = new PlSqlParser(tokenStream);

	    PlSqlParserListener listener = new TestPlSqlParserListener(tokenStream);

	    ParseTreeWalker walker = new ParseTreeWalker();
	    parser.addParseListener(listener);
	    parser.sql_script(); // <-- here I called this method which trigger a processing
	}
}
