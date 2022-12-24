package gui.table;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class NonEditableTable extends JTable{
	
	//NonEditableTable 생성자
	public NonEditableTable(DefaultTableModel allBookTableModel) {
		super(allBookTableModel);
	}
	
	//필드값 수정 불가
	@Override
	public boolean isCellEditable(int row, int column) {
		return false; 
	}
	
	//@Override
	//public boolean is
}
