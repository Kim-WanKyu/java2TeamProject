package gui.table;

import javax.swing.table.DefaultTableModel;

public class SearchTable {
	private Object[][] defaultSearchBookData ;
	public Object[][] getDefaultSearchBookData() { return defaultSearchBookData; }
	private DefaultTableModel searchBookModel = new DefaultTableModel(defaultSearchBookData,AllBookTable.getAllBookColumnName());
	public DefaultTableModel getSearchBookModel() { return searchBookModel; }

	
}
