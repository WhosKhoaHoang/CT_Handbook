import java.io.IOException;
import java.io.Reader;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

//Credit to jitter from Stack Overflow
public class HTMLToText extends HTMLEditorKit.ParserCallback {
	StringBuffer s;
	    
	public HTMLToText() {}
	public void parse(Reader in) throws IOException {
		s = new StringBuffer();
		ParserDelegator delegator = new ParserDelegator();
		delegator.parse(in, this, Boolean.TRUE);
	}
	
	public void handleText(char[] text, int pos) {
		s.append(text);
		s.append("\n");
		//Removed extra \n to get rid of double spacing for copy->paste
	}
	
	public String getText() {
		return s.toString();
	}
}
