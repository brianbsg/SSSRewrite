package com.bsgco;

public final class Editor {
	private final String data;
	
	public Editor(String file) {
		//split one line into many around newline
		String [] lines = file.split("\n");
		//start a new String
		StringBuilder sb = new StringBuilder();
		//denotes first line
		boolean first = true;
		for(String line : lines) {
			if(first) //if first line do nothing
				first = false;
			else
				sb.append('\n'); //otherwise put a line break
			//add the line after fixes
			sb.append(handleLine(line));
		}
		//set end result
		data = sb.toString();
	}
	
	/**
	 * Get the String data
	 */
	public String toString() { return data; }
	
	private String handleLine(String line) {
		//if it has these three things
		if(line.contains("<position") && line.contains("finish=") && line.contains("start=")) {
			String [] strs = line.replace("<position", "") //cut the beginning
					.replace("/>", "") //cut the end
					.trim() //cut the whitespace
					.split("[\\s]+"); //break into 2 around whitespace
			if(strs[0].startsWith("finish")) //if finish is first, flip
				return "<position " + strs[1] + ' ' + strs[0] + "/>";
			else //return in order
				return "<position " + strs[0] + ' ' + strs[1] + "/>";
		}
		else //return original
			return line;
	}
}
