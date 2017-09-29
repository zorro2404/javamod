package jsonbuilder;

import java.util.ArrayList;

public class Tags {
 
	private ArrayList<tag> TagList=null;

	public Tags() {
		this.TagList=new ArrayList<tag>();
	}
	
	public void addTag(tag element) {
		this.TagList.add(element);
	}
	
	public ArrayList<tag> GetTags(){
		return this.TagList;
	}
	
	public String toString() {
		
		return "[ TAGS="+ TagList + "]";
	}
	
	
}
