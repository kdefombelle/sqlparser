# sqlparser

To test class generation from command line, go to antlr installation directory containing `$ANTLR_HOME`<br>
- antlr-4.7.2-complete.jar
- antlr4.bat
- grun.bat

Then rely on resource found [here|https://github.com/kdefombelle/sqlparser.git]
- Copy to $ANTLR_HOME grammar files [PlSqlLexer.g4](lexer) and [PlSqlParser.g4](parser)<br>
- Copy to $ANTLR_HOME Lexer and Parser Java classes from [here](antlr-plsql-java-classes)<br>
- Run
	```bash
	antlr4 PlSqlLexer.g4
	java org.antlr.v4.Tool PlSqlLexer.g4
	antlr4 PlSqlParser.g4
	java org.antlr.v4.Tool PlSqlParser.g4
	javac Pl*.java
	```
Then you can test via
`grun PlSql r -tree` or `grun PlSql r -gui`

It prompts you for an input you can enter as follows<br>
```bash
java org.antlr.v4.gui.TestRig PlSql r -gui
SELECT FIELD1 FROM TABLE1;
^Z
```

cf. https://stackoverflow.com/questions/56660700/how-to-use-antlr4-plsql-grammar-java-and-org-antlr-v4-gui-testrig

[lexer]: <https://github.com/antlr/grammars-v4/blob/master/sql/plsql/PlSqlLexer.g4>
[parser]: <https://github.com/antlr/grammars-v4/blob/master/sql/plsql/PlSqlParser.g4>
[antlr-plsql-java-classes]: <https://github.com/antlr/grammars-v4/tree/master/sql/plsql/Java>
