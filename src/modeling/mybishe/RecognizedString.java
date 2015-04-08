package modeling.mybishe;

public class RecognizedString {

	private String str;
	private int rowStartNumber;
	private int rowEndNumber;
	private int columnStartNumber;
	private int columnEndNumber;
	private String key;//描述是注释还是主体代码	
	
	public RecognizedString(String str, int columnStartNumber,
			int columnEndNumber, String key) {
		super();
		this.str = str;
		this.columnStartNumber = columnStartNumber;
		this.columnEndNumber = columnEndNumber;
		this.key = key;
	}
	
	public RecognizedString(String str, int rowStartNumber, int rowEndNumber,
			int columnStartNumber, int columnEndNumber, String key) {
		super();
		this.str = str;
		this.rowStartNumber = rowStartNumber;
		this.rowEndNumber = rowEndNumber;
		this.columnStartNumber = columnStartNumber;
		this.columnEndNumber = columnEndNumber;
		this.key = key;
	}

	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	
	public int getRowStartNumber() {
		return rowStartNumber;
	}

	public void setRowStartNumber(int rowStartNumber) {
		this.rowStartNumber = rowStartNumber;
	}

	public int getRowEndNumber() {
		return rowEndNumber;
	}

	public void setRowEndNumber(int rowEndNumber) {
		this.rowEndNumber = rowEndNumber;
	}

	public int getColumnStartNumber() {
		return columnStartNumber;
	}
	public void setColumnStartNumber(int columnStartNumber) {
		this.columnStartNumber = columnStartNumber;
	}
	public int getColumnEndNumber() {
		return columnEndNumber;
	}
	public void setColumnEndNumber(int columnEndNumber) {
		this.columnEndNumber = columnEndNumber;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
