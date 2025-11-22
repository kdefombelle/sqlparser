# sqlparser
Download antlr-4.7.2-complete.jar
# env
- ~/.profile
    ```bash
    export ANTLR_JAR=$HOME/bin/antlr/antlr-4.7.2-complete.jar
    export CLASSPATH=".:$ANTLR_JAR:$CLASSPATH"
	```
- ~/.bash_profile
    ```bash
    alias antlr4='java -Xmx500M -cp "$HOME/bin/antlr/antlr-4.7.2-complete.jar:$CLASSPATH" org.antlr.v4.Tool'
    alias grun='java -Xmx500M -cp "$HOME/bin/antlr/antlr-4.7.2-complete.jar:$CLASSPATH" org.antlr.v4.gui.TestRig'
	```

# run command line
In a folder put the following resources relying on resource found in project antlr/grammar-v4
- [PlSqlLexer.g4](https://github.com/antlr/grammars-v4/blob/master/sql/plsql/PlSqlLexer.g4) and [PlSqlParser.g4](https://github.com/antlr/grammars-v4/blob/master/sql/plsql/PlSqlParser.g4)<br>
- PlSqlLexer.java and PlSqlParser.java classes from [here](https://github.com/antlr/grammars-v4/tree/master/sql/plsql/Java)<br>
- tail -n +2 sqlparser/src/main/java/fr/kdefombelle/antlr/PlSqlLexerBase.java > ./PlSqlLexerBase.java
- tail -n +2  sqlparser/src/main/java/fr/kdefombelle/antlr/PlSqlParserBase.java > ./PlSqlParserBase.java

Then
- Run
	```bash
	antlr4 PlSqlLexer.g4
	java -cp $ANTLR_JAR org.antlr.v4.Tool PlSqlLexer.g4 
	antlr4 PlSqlParser.g4
	java -cp $ANTLR_JAR org.antlr.v4.Tool PlSqlParser.g4
	javac -cp .:$ANTLR_JAR *.java
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

# mvn project
Run mvn install to generate the classes automatically and see in a unit test how to be called on enter / exit element