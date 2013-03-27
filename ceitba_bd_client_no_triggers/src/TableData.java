
public class TableData {
	private String[] columnNames;
	private Object[][] info;
	
	public TableData(String[] columnNames, Object[][] info){
		this.setInfo(info);
		this.setColumnNames(columnNames);
	}
	public TableData(){
        Object[][] info = {};
        String[] columnNames = {};
		this.setInfo(info);
		this.setColumnNames(columnNames);		
	}

	public Object[][] getInfo() {
		return info;
	}

	private void setInfo(Object[][] info) {
		this.info = info;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	private void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}	
}
