package fr.kdefombelle.jsqlparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsListVisitor;
import net.sf.jsqlparser.expression.operators.relational.ItemsListVisitorAdapter;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.StatementVisitorAdapter;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.FromItemVisitorAdapter;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItemVisitorAdapter;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.SubSelect;

public class TestJsqlParserVisitor {
	
	private final Logger logger = LoggerFactory.getLogger(TestJsqlParserVisitor.class);

	public class TestJsqlFromItemVisitor extends FromItemVisitorAdapter {

		private ItemsListVisitor itemsListVisitor;

		public TestJsqlFromItemVisitor() {
			super();
			itemsListVisitor = new TestJsqlParserItemsListVisitor();
		}
		public void visit(Table table) {
			logger.debug("ItemsListVisitor table {} ", table);
		}

		public void visit(SubSelect subSelect) {
			logger.debug("ItemsListVisitor subSelect {}", subSelect);
			subSelect.accept(itemsListVisitor);
		}

	}

	public class TestJsqlParserItemsListVisitor extends ItemsListVisitorAdapter {

		public TestJsqlParserItemsListVisitor() {
			super();
		}

		public void visit(SubSelect subSelect) {
			logger.debug("ItemsListVisitorAdapter subSelect {}", subSelect);
		}

		public void visit(ExpressionList expressionList) {
			logger.debug("ItemsListVisitorAdapter expressionList {}", expressionList);
		}

		
	}

	public class TestJsqlParserSelectItemVisitor extends SelectItemVisitorAdapter {

		private ExpressionVisitor expressionVisitor;
		
		public TestJsqlParserSelectItemVisitor() {
			super();
			expressionVisitor = new TestJsqlParserExpressionVisitor();
		}

		public void visit(AllColumns columns) {
			logger.debug("SelectItemVisitorAdapter AllColumns {} ", columns);
		}

		public void visit(AllTableColumns columns) {
			logger.debug("SelectItemVisitorAdapter AllTableColumns {} ", columns);
		}

		public void visit(SelectExpressionItem item) {
			logger.debug("SelectItemVisitorAdapter SelectExpressionItem {} ", item);
			item.getExpression().accept(expressionVisitor);
		}

		
	}

	public class TestJsqlParserExpressionVisitor extends ExpressionVisitorAdapter {

		public TestJsqlParserExpressionVisitor() {
			super();
		}

		public void visit(InExpression expr) {
			logger.debug("ExpressionVisitorAdapter InExpression {} ", expr);
		}
		
	}

	public class TestJsqlParserSelectVisitor {}
	
//	public class TestJsqlParserStatementVisitor extends StatementVisitorAdapter {
//
//		private SelectVisitor selectVisitor;
//
//		public void visit(Select select) {
//			select.getSelectBody().accept(selectVisitor);
//		}
//
//		public TestJsqlParserStatementVisitor() {
//			super();
//			selectVisitor = new TestJsqlParserSelectVisitor(this);
//		}
//	}

}
