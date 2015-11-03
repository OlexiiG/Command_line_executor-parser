import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TableParser {
	List<String> table;
	List<String> tablesignatures;
	//StringParser sp;
	
public TableParser(List<String> table, List<String> tablesignatures) {
this.table=table;
this.tablesignatures=preparetablesignatures(table.get(0),tablesignatures);
	}
	
	
	private List<String> preparetablesignatures(String tableheader, List<String> tablesignatures) {
		
		
		class Entry implements Comparable<Entry>{
			private int position;
			private String value;
			
			public Entry (int p,String v){
				this.position=p;
				this.value=v;
			}
			
			int getPosition() {
				return position;
			}
			void setPosition(int position) {
				this.position = position;
			}
			String getValue() {
				return value;
			}
			void setValue(String value) {
				this.value = value;
			}

			@Override
			public int compareTo(Entry e) {
				// TODO Auto-generated method stub
				return position-e.getPosition();
			} 
			
		}
		
		
		
		List<Entry> t = new ArrayList<Entry>();
		for (String signatures:tablesignatures){
			t.add(new Entry (tableheader.indexOf(signatures),signatures));
		}
		
		Collections.sort(t);
		
		
		
		List<String> tablesign = new ArrayList<String>();
		
		t.forEach((a) -> tablesign.add(a.getValue()));
		
		
		
		return tablesign;
	}

	/*public List<String> getcolumn(List<String> table, List<String> tableheader,
			String columname) {
		List<String> column = new ArrayList<String>();
		int columnnumber=tableheader.indexOf(columname);
		for(String s :table){
			column.add(StringParser.split(StringParser.removetabs(s)).get(columnnumber));
		}
		
		return column;
	}*/

	public List<String> getrow(int rownumber){
		if (table.size()>rownumber){
		String row=table.get(rownumber);
		return StringParser.split(StringParser.removetabs(row));}
		return null;
	}
	
	public String getvalue(List<String> row, int valueposition){
		if (row==null) return null;
		if (row.size()<=valueposition) return null;//|row.size()<=valueposition
		return row.get(valueposition);
	}
	
	public String getvalue(List<String> row, String valuename){
		int valueposition=getColumnNumberByName (valuename);
		return getvalue( row, valueposition);
	}

	private int getColumnNumberByName(String valuename) {
		return tablesignatures.indexOf(valuename);
	}


	public List<String> gettablesignatures() {
		return tablesignatures;
	}
	

}
