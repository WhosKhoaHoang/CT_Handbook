//Credit: http://www.javaquizplayer.com/examples/text-editor-using-java-example.html

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;
import javax.swing.text.DefaultEditorKit.CopyAction;
import javax.swing.text.DefaultEditorKit.CutAction;
import javax.swing.text.DefaultEditorKit.PasteAction;
import javax.swing.text.StyledEditorKit.BoldAction;
import javax.swing.text.StyledEditorKit.FontFamilyAction;
import javax.swing.text.StyledEditorKit.FontSizeAction;
import javax.swing.text.StyledEditorKit.ItalicAction;
import javax.swing.text.StyledEditorKit.UnderlineAction;
import javax.swing.text.html.HTMLDocument;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import net.miginfocom.swing.MigLayout;


public class TextEditor extends JPanel {
	
	private JTextPane editor__;
	private JPanel curPanel = this;
	private String contentCategory = "";
	
	private JComboBox<String> fontSizeComboBox__;
	private JComboBox<String> fontFamilyComboBox__;
	private String pictureButtonName__;

	enum BulletActionType {INSERT, REMOVE};
	enum NumbersActionType {INSERT, REMOVE};
	enum UndoActionType {UNDO, REDO};
	
	// This flag checks true if the caret position within a bulleted para
	// is at the first text position after the bullet (bullet char + space).
	// Also see EditorCaretListener and BulletParaKeyListener.
	private boolean startPosPlusBullet__;

	// This flag checks true if the caret position within a numbered para
	// is at the first text position after the number (number + dot + space).
	// Alse see EditorCaretListener and NumbersParaKeyListener.		
	private boolean startPosPlusNum__;
	
	private static final List<String> FONT_LIST = Arrays.asList(new String [] {"Arial", "Calibri", "Cambria", "Courier New", "Comic Sans MS", "Dialog", "Georgia", "Helevetica", "Lucida Sans", "Monospaced", "Tahoma", "Times New Roman", "Verdana"});
	private static final String [] FONT_SIZES  = {"Font Size", "12", "14", "16", "18", "20", "22", "24", "26", "28", "30"};
	private static final char BULLET_CHAR = '\u2022';
	private static final String BULLET_STR = new String(new char [] {BULLET_CHAR});
	private static final String BULLET_STR_WITH_SPACE = BULLET_STR + " ";
	private static final int BULLET_LENGTH = BULLET_STR_WITH_SPACE.length();
	private static final String NUMBERS_ATTR = "NUMBERS";
	private static final String ELEM = AbstractDocument.ElementNameAttribute;
	private static final String COMP = StyleConstants.ComponentElementName;
	
	
	//DEFINE THE CONSTRUCTOR!
	public TextEditor() {
		
		//setLayout(new MigLayout()); 
		setLayout(new BorderLayout()); 
		//Default is Flow Layout, which uses a component's preferred size, which can be a pretty crappy size.
		//^that is mentioned here: http://stackoverflow.com/questions/19467217/how-to-make-a-jtable-fill-entire-available-space
		
		editor__ = new JTextPane();
		JScrollPane editorScrollPane = new JScrollPane(editor__);

		//Wanna change things to RTF? Then say setContentType("text/rtf") and replace new HTMLDocument() with new DefaultStyledDocument()
		editor__.setContentType("text/html");
		editor__.setDocument(new HTMLDocument());
								
		editor__.addKeyListener(new BulletParaKeyListener());
		editor__.addKeyListener(new NumbersParaKeyListener());
		editor__.addCaretListener(new EditorCaretListener());

		EditButtonActionListener editButtonActionListener = new EditButtonActionListener();

		// ############################## CUT/COPY/PASTE BUTTONS ##############################
		// ===== Cut Button Stuff =====
		JButton cutButton = new JButton(new CutAction());
		cutButton.setHideActionText(true);
		cutButton.setText("Cut");
		cutButton.addActionListener(editButtonActionListener);

		// ===== Copy Button Stuff =====
		JButton copyButton = new JButton(new CopyAction());
		copyButton.setHideActionText(true);
		copyButton.setText("Copy");
		copyButton.addActionListener(editButtonActionListener);
		
		// ===== Paste Button Stuff =====
		JButton pasteButton = new JButton(new PasteAction());
		pasteButton.setHideActionText(true);
		pasteButton.setText("Paste");
		pasteButton.addActionListener(editButtonActionListener);
		
		
		// ############################## TEXT FORMATTING BUTTONS ##############################
		// ===== Bold Button Stuff =====
		JButton boldButton = new JButton(new BoldAction());
		boldButton.setHideActionText(true);
		boldButton.setText("Bold");
		boldButton.addActionListener(editButtonActionListener);
		
		// ===== Italic Button Stuff =====
		JButton italicButton = new JButton(new ItalicAction());
		italicButton.setHideActionText(true);
		italicButton.setText("Italic");
		italicButton.addActionListener(editButtonActionListener);
		
		// ===== Underline Button Stuff =====
		JButton underlineButton = new JButton(new UnderlineAction());
		underlineButton.setHideActionText(true);
		underlineButton.setText("Underline");
		underlineButton.addActionListener(editButtonActionListener);
		
		// ===== Color Button Stuff =====
		JButton colorButton = new JButton("Set Color");
		colorButton.addActionListener(new ColorActionListener());

		// ===== Combo Box Stuff =====
		fontSizeComboBox__ = new JComboBox<String>(FONT_SIZES);
		fontSizeComboBox__.setEditable(false);
		fontSizeComboBox__.addItemListener(new FontSizeItemListener());
		
		Vector<String> editorFonts = getEditorFonts();
		editorFonts.add(0, "Font Family");
		fontFamilyComboBox__ = new JComboBox<String>(editorFonts);
		fontFamilyComboBox__.setEditable(false);
		fontFamilyComboBox__.addItemListener(new FontFamilyItemListener());
		
		
		// ===== Picture Button Stuff =====
		JButton insertPictureButton = new JButton("Picture Insert");
		insertPictureButton.addActionListener(new PictureInsertActionListener());
		JButton deletePictureButton = new JButton("Picture Delete");
		deletePictureButton.addActionListener(new PictureDeleteActionListener());
		
		JButton bulletInsertButton = new JButton("Bullets Insert");
		bulletInsertButton.addActionListener(new BulletActionListener(BulletActionType.INSERT));
		JButton bulletRemoveButton = new JButton("Bullets Remove");
		bulletRemoveButton.addActionListener(new BulletActionListener(BulletActionType.REMOVE));
		
		JButton numbersInsertButton = new JButton("Numbers Insert");
		numbersInsertButton.addActionListener(new NumbersActionListener(NumbersActionType.INSERT));
		JButton numbersRemoveButton = new JButton("Numbers Remove");
		numbersRemoveButton.addActionListener(new NumbersActionListener(NumbersActionType.REMOVE));
		
		
		// ############################## MY CUSTOM BUTTONS ##############################
		JButton copyToCBBtn = new JButton("Copy To Clipboard");
		copyToCBBtn.addActionListener(new copyToCBActionListener());
		
		JButton readDbBtn = new JButton("Load Content");
		readDbBtn.addActionListener(new ReadDbActionListener());
		
		JButton updateDbBtn = new JButton("Save Content");
		updateDbBtn.addActionListener(new UpdateDbActionListener());
		// FOCUS
		// ######### Perhaps provide argument to UpdateDBActionListener to specify what category needs to be updated? #########
		//   - No, not an argument to those guys. That won't work. Make an instance variable for this TextEditor that can be
		//      accessed in those Action Listeners...That instance variable is contentCategory

		
		// ############################## PANEL FOR TOOLBAR PANEL ##############################
		// Each panel number corresponds to a row...
		// ====== First Row ======
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel1.add(cutButton);
		panel1.add(copyButton);
		panel1.add(pasteButton);
		panel1.add(copyToCBBtn);
		panel1.add(new JSeparator(SwingConstants.VERTICAL));
		panel1.add(boldButton);
		panel1.add(italicButton);
		panel1.add(underlineButton);
		panel1.add(new JSeparator(SwingConstants.VERTICAL));	
		panel1.add(colorButton);
		//panel1.add(new JSeparator(SwingConstants.VERTICAL));
		//panel1.add(fontSizeComboBox__);
		
		// ====== Second Row ======
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel2.add(fontSizeComboBox__);
		panel2.add(fontFamilyComboBox__);	
		panel2.add(new JSeparator(SwingConstants.VERTICAL));
		panel2.add(insertPictureButton);
		panel2.add(deletePictureButton);
		panel2.add(new JSeparator(SwingConstants.VERTICAL));
		panel2.add(bulletInsertButton);
		panel2.add(bulletRemoveButton);
		panel2.add(new JSeparator(SwingConstants.VERTICAL));
		
		// ====== Third Row ======
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel3.add(numbersInsertButton);
		panel3.add(numbersRemoveButton);
		// (My Custom Buttons)
		panel3.add(new JSeparator(SwingConstants.VERTICAL));
		panel3.add(readDbBtn);
		panel3.add(updateDbBtn);
		
		
		// ===== Add contents to toolbar panel =====		
		JPanel toolBarPanel = new JPanel();
		toolBarPanel.setLayout(new BoxLayout(toolBarPanel, BoxLayout.PAGE_AXIS));
		toolBarPanel.add(panel1);
		toolBarPanel.add(panel2);
		//===== Mine =====
		toolBarPanel.add(panel3);

		add(toolBarPanel, BorderLayout.NORTH);
		add(editorScrollPane, BorderLayout.CENTER);
				
		editor__.requestFocusInWindow(); //Do I need this?

	}
	
	
	//Perhaps you can pass a DBConnect object to this constructor...load time could be shortened if you passed
	//the same DBConnect object to all menu items, each of which creates a TextEditor...Constantly opening and
	//closing for each menu item is probably contributing to the slow load...
	public TextEditor(String contentCategory) {
		this();
		this.contentCategory = contentCategory;	
		
		//Note how you're not writing anything to a file here, which is good.
		DBConnect connect = new DBConnect();
		ResultSet rs = connect.getData(contentCategory);
					
		InputStream is;
		try {
			if (rs.next()) {
				is = rs.getBlob("content").getBinaryStream();
				editor__.getEditorKit().read(is, editor__.getDocument(), 0);
		        is.close();
			}
			else {
				System.out.println("YO! " + contentCategory + " is empty!");
			}

		} catch (SQLException | IOException | BadLocationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		connect.close();		
	}
	
	
	//OVERLOAD SO CURRENT SYSTEM DOESN'T BREAK!
	public TextEditor(String contentCategory, DBConnect connect) {
		this();
		this.contentCategory = contentCategory;	
		
		//Note how you're not writing anything to a file here, which is good.
		ResultSet rs = connect.getData(contentCategory);
					
		InputStream is;
		try {
			if (rs.next()) {
				is = rs.getBlob("content").getBinaryStream();
				editor__.getEditorKit().read(is, editor__.getDocument(), 0);
		        is.close();
			}
			else {
				System.out.println("YO! " + contentCategory + " is empty!");
			}

		} catch (SQLException | IOException | BadLocationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
	}

	
	private class ReadDbActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//Note how you're not writing anything to a file here, which is good.
			DBConnect connect = new DBConnect();
			//ResultSet rs = connect.getData();
			ResultSet rs = connect.getData(contentCategory);
			
	        Document doc = new HTMLDocument();
			InputStream is;
			try {
				if (rs.next()) { //Should I be saying if (rs.next()) { }? But isn't something always gonna be there cuz I put it there?
					is = rs.getBlob("content").getBinaryStream();
					editor__.getEditorKit().read(is,doc,0);

			        is.close();
				}
				else {
					System.out.println("YO! " + contentCategory + " is empty!");
				}

			} catch (SQLException | IOException | BadLocationException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	        editor__.setDocument(doc);

			connect.close();
		}
	}
	

	private class copyToCBActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) { 
			try {
				//NOTE how you're copying ONLY the plaintext -- no stylings!!
				StringSelection stringSelection = new StringSelection(
						getEditorDocument().getText(0, getEditorDocument().getLength()));
				Clipboard clpBrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpBrd.setContents(stringSelection, null);
			} catch (BadLocationException e2) {
				e2.printStackTrace();
			}
		}

	}
	
	
	//YOU CURRENTLY HARD-CODE THE ID OF THE ROW TO UPDATE!!!
	private class UpdateDbActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {			
						
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			DBConnect connect = new DBConnect();
	        try {
				Document doc = editor__.getDocument();
				editor__.getEditorKit().write(out,doc,0,doc.getLength());
				out.flush(); out.close();
				
				//connect.updateData(new ByteArrayInputStream(out.toByteArray()));
				connect.updateData(new ByteArrayInputStream(out.toByteArray()), contentCategory); //UPDATE CONTENT CATEGORY!!!

			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
	        finally {
				connect.close();
	        }

		}
	}
	
	
	/*
	 * Returns a collection of Font names that are available from the
	 * system fonts and are matched with the desired font list (FONT_LIST).
	 */
	private Vector<String> getEditorFonts() {
	
		String [] availableFonts =
			GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		Vector<String> returnList = new Vector<>();
	
		for (String font : availableFonts) {
	
			if (FONT_LIST.contains(font)) {

				returnList.add(font);
			}
		}
	
		return returnList;
	}
	
	
	//ActionListeners will call this method so that they could, DUH, edit the current document associated with editor__
	private StyledDocument getEditorDocument() {
		
		StyledDocument doc = (HTMLDocument) editor__.getDocument();
		return doc;
	}
	
	
	private class EditButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		
			editor__.requestFocusInWindow();
		}
	}
	
	
	private class ColorActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
		
			Color newColor = JColorChooser.showDialog(curPanel, "Choose a color",
														Color.BLACK);
			if (newColor == null) {
			
				editor__.requestFocusInWindow();
				return;
			}
			
			SimpleAttributeSet attr = new SimpleAttributeSet();
			StyleConstants.setForeground(attr, newColor);
			editor__.setCharacterAttributes(attr, false);
			editor__.requestFocusInWindow();
		}
	}
	
	
	
	//============ HELPER METHODS FOR BulletParaKeyLisener BEGIN ============
	/*
	 * The insert bullet routine; inserts the bullet in the editor document. This
	 * routine is used from the insert action (ActionListener) as well as bullet
	 * para key press actions (keyPressed or keyReleased methods of KeyListener).
	 *
	 * The parameter insertPos is the position at which the bullet is to be
	 * inserted. The parameter attributesPos is the position from which the bullet
	 * is to get its attributes (like color, font, size, etc.). The two parameter
	 * values are derived differently for bullet insert and bullet para Enter
	 * key press actions. 
	 *
	 * Bullet insert action: the insertPos and attributesPos is the same,
	 * the paraEleStart.
	 * Enter key press: the insertPos is the current caret position of keyReleased(),
	 * and the attributesPos is the previous paraEleStart position from
	 * keyPressed() method.
	 */
	private void insertBullet(int insertPos, int attributesPos) {
								
		try {
			getEditorDocument().insertString(insertPos,
												BULLET_STR_WITH_SPACE,
												getParaStartAttributes(attributesPos));
		}
		catch(BadLocationException ex) {
				
			throw new RuntimeException(ex);
		}
	}

	
	
	private AttributeSet getParaStartAttributes(int pos) {
	
		StyledDocument doc = (HTMLDocument) editor__.getDocument();
		Element	charEle = doc.getCharacterElement(pos);
		return charEle.getAttributes();
	}
	
	/*
	 * The remove bullet routine; removes the bullet in the editor document. This
	 * routine is used from the delete action (ActionListener) as well as bullet
	 * para key press actions (keyPressed or keyRemoved methods of KeyListener).
	 * The keys include the Enter, Backspace, Delete keys.
	 *
	 * The parameter removePos is the start position and the length is the length
	 * of text to be removed. Length of characters removed is: BULLET_LENGTH
	 * or +1 (includes carriage return folowing the BULLET_LENGTH). The two
	 * parameter values are derived differently for bullet remove and bullet
	 * para key press actions. 
	 *
	 * Bullet remove action: removePos is paraEleStart and the BULLET_LENGTH.
	 * Delete key press: removePos is current caret pos of keyPressed() and
	 * the BULLET_LENGTH.
	 * Backspace key press: removePos is paraEleStart of keyPressed() and
	 * the length is BULLET_LENGTH.
	 * Enter key press: removePos is previous paraEleStart of keyPressed() and
	 * the length is BULLET_LENGTH + 1 (+1 includes CR).
	 */
	private void removeBullet(int removePos, int length) {

		try {
			getEditorDocument().remove(removePos, length);
		}
		catch(BadLocationException ex) {
				
			throw new RuntimeException(ex);
		}
	}
	
	
	private boolean isBulletedPara(int paraEleStart) {
		
		if (getParaFirstCharacter(paraEleStart) == BULLET_CHAR) {
			
			return true;
		}
			
		return false;
	}
	
	
	private char getParaFirstCharacter(int paraEleStart) {
		
		String firstChar = "";
			
		try {
			firstChar = editor__.getText(paraEleStart, 1);
		}
		catch (BadLocationException ex) {
			
			throw new RuntimeException(ex);
		}
			
		return firstChar.charAt(0);
	}
	
	
	/*
	 * Left arrow key press routine within a bulleted and numbered paras.
	 * Moves the cursor when caret is at position startPosPlusBullet__ or at
	 * startPosPlusNum__ for bullets or numbers respectively.
	 * Also see EditorCaretListener.
	 *
	 * The parameter startTextPos indicates if startPosPlusBullet__ or
	 * startPosPlusNum__. pos is the present caret postion.
	 */
	private void doLeftArrowKeyRoutine(int pos, boolean startTextPos) {
	
		if (! startTextPos) {
		
			return;
		}
		
		// Check if this is start of document
		Element paraEle = getEditorDocument().getParagraphElement(editor__.getCaretPosition());
		final int newPos = (paraEle.getStartOffset() == 0) ? 0 : pos;
		
		// Position the caret in an EDT, otherwise the caret is
		// positioned at one less position than intended.
		SwingUtilities.invokeLater(new Runnable() {
		
			public void run() {
			
				editor__.setCaretPosition(newPos);
			}
		});
	}
	
	
	private String getPrevParaText(int prevParaEleStart, int prevParaEleEnd) {
		
		String prevParaText = "";
			
		try {
			prevParaText = getEditorDocument().getText(prevParaEleStart, 
											(prevParaEleEnd -  prevParaEleStart));
		}
		catch(BadLocationException ex) {
				
			throw new RuntimeException(ex);
		}
			
		return prevParaText;
	}
	
	//============ HELPER METHODS FOR BulletParaKeyLisener END ============

	
	/*
	 * Key listener class for key press and release actions within a bulleted
	 * para. The keys include Enter, Backspace, Delete and Left. The Enter press
	 * is implemented with both the keyPressed and keyReleased methods. The Delete,
	 * Backspace and Left key press is implemented within the keyPressed.
	 */
	public class BulletParaKeyListener implements KeyListener {
	
		// These two variables are derived in the keyPressed and are used in
		// keyReleased method.
		private String prevParaText_;
		private int prevParaEleStart_;
		
		// Identifies if a key is pressed in a bulleted para. 
		// This is required to distinguish from the numbered para.
		private boolean bulletedPara_; 


		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			bulletedPara_ = false;
			int pos = editor__.getCaretPosition();
			
			if (! isBulletedParaForPos(pos)) {
			
				return;
			}
			
			Element paraEle = getEditorDocument().getParagraphElement(pos);
			int paraEleStart = paraEle.getStartOffset();
			
			switch (e.getKeyCode()) {
			
				case KeyEvent.VK_LEFT: // same as that of VK_KP_LEFT
				case KeyEvent.VK_KP_LEFT: int newPos = pos - (BULLET_LENGTH + 1);
										doLeftArrowKeyRoutine(newPos, startPosPlusBullet__);
										break;			
				case KeyEvent.VK_DELETE: doDeleteKeyRoutine(paraEle, pos);
										break;
				case KeyEvent.VK_BACK_SPACE: doBackspaceKeyRoutine(paraEle);
										break;
				case KeyEvent.VK_ENTER: getPrevParaDetails(pos);
			}

		} // keyPressed()
		
		private boolean isBulletedParaForPos(int caretPos) {

			Element paraEle = getEditorDocument().getParagraphElement(caretPos);
		
			if (isBulletedPara(paraEle.getStartOffset())) {
			
				return true;
			}
			
			return false;
		}
		
		// This method is used with Enter key press routine.
		// Two instance variable values are derived here and are used
		// in the keyReleased() method: prevParaEleStart_ and prevParaText_
		private void getPrevParaDetails(int pos) {
		
			pos =  pos - 1;
			
			if (isBulletedParaForPos(pos)) {
			
				bulletedPara_ = true;
				Element paraEle = getEditorDocument().getParagraphElement(pos);
				prevParaEleStart_ = paraEle.getStartOffset();
				prevParaText_ = getPrevParaText(prevParaEleStart_, paraEle.getEndOffset());
			}
		}
		
		// Delete key press routine within bulleted para.
		private void doDeleteKeyRoutine(Element paraEle, int pos) {

			int paraEleEnd = paraEle.getEndOffset();
			
			if (paraEleEnd > getEditorDocument().getLength()) {

				return; // no next para, end of document text
			}
				
			if (pos == (paraEleEnd - 1)) { // last char of para; -1 is for CR
				
				if (isBulletedParaForPos(paraEleEnd + 1)) {

					// following para is bulleted, remove
					removeBullet(pos, BULLET_LENGTH);
				}
				// else, not a bulleted para
				// delete happens normally (one char)
			}
		}
		
		// Backspace key press routine within a bulleted para.
		// Also, see EditorCaretListener.
		private void doBackspaceKeyRoutine(Element paraEle) {
			
			// In case the position of cursor at the backspace is just 
			// before the bullet (that is BULLET_LENGTH).
			if (startPosPlusBullet__) {

				removeBullet(paraEle.getStartOffset(), BULLET_LENGTH);
				startPosPlusBullet__ = false;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		
			if (! bulletedPara_) {

				return;
			}
				
			switch (e.getKeyCode()) {
		
				case KeyEvent.VK_ENTER: doEnterKeyRoutine();
										break;
			}
		}

		// Enter key press routine within a bulleted para.
		// Also, see keyPressed().
		private void doEnterKeyRoutine() {

			String prevParaText = prevParaText_;
			int prevParaEleStart = prevParaEleStart_;

			// Check if prev para with bullet has text					
			if (prevParaText.length() < 4) {
				
				// Para has bullet and no text, remove bullet+CR from para
				removeBullet(prevParaEleStart, (BULLET_LENGTH + 1));
				editor__.setCaretPosition(prevParaEleStart);
				return;
			}
			// Prev para with bullet and text
			
			// Insert bullet for next para (current position), and
			// prev para attributes are used for this bullet.	
			insertBullet(editor__.getCaretPosition(), prevParaEleStart); 
		}
		
	} // BulletParaKeyListener
	
	
	
	//============ HELPER METHODS FOR NumbersParaKeyListener BEGIN ============
	private Integer getParaNumber(int paraEleStart) {
		
		AttributeSet attrSet = getParaStartAttributes(paraEleStart);		
		Integer paraNum = (Integer) attrSet.getAttribute(NUMBERS_ATTR);
		return paraNum;
	}
	
	
	/*
	 * Returns the numbered para's number length. This length includes
	 * the number + dot + space. For example, the text "12. A Numbered para..."
	 * has the number length of 4.
	 */
	private int getNumberLength(int paraEleStart) {
	
		Integer num = getParaNumber(paraEleStart);
		int len = num.toString().length() + 2; // 2 = dot + space after number
		return len;
	}
	
	
	private boolean isNumberedPara(int paraEleStart) {

		AttributeSet attrSet = getParaStartAttributes(paraEleStart);		
		Integer paraNum = (Integer) attrSet.getAttribute(NUMBERS_ATTR);

		if ((paraNum == null) || (! isFirstCharNumber(paraEleStart))) {

			return false;
		}

		return true;
	}
	
	
	private boolean isFirstCharNumber(int paraEleStart) {
		
		if (Character.isDigit(getParaFirstCharacter(paraEleStart))) {
			
			return true;
		}
		
		return false;
	}
	
	
	private String getNumberString(Integer nextNumber) {
		
		return new String(nextNumber.toString() + "." + " ");
	}
	
	
	private AttributeSet getNumbersAttributes(int paraEleStart, Integer number) {
		
		AttributeSet attrs1 = getParaStartAttributes(paraEleStart);
		SimpleAttributeSet attrs2 = new SimpleAttributeSet(attrs1);
		attrs2.addAttribute(NUMBERS_ATTR, number);
		return attrs2;
	}
	
	
	/*
	 * The remove number routine; removes the number in the editor document. This
	 * routine is used from the delete action (ActionListener) as well as the number
	 * para key press actions (keyPressed or keyRemoved methods of KeyListener).
	 * The keys include the Enter, Backspace, Delete keys.
	 *
	 * The parameter removePos is the start position and the length is the length
	 * of text to be removed. Length of characters removed is derived from the
	 * method getNumberLength() or +1 (includes carriage return folowing the
	 * number length). The two parameter values are derived differently for
	 * number remove action and number para key press actions. 
	 *
	 * Number remove action: removePos is paraEleStart and the length from
	 * the method getNumberLength().
	 * Delete key press: removePos is current caret pos of keyPressed() and
	 * the length from the method getNumberLength().
	 * Backspace key press: removePos is paraEleStart of keyPressed() and
	 * the length from the method getNumberLength().
	 * Enter key press: removePos is previous paraEleStart of keyPressed() and
	 * the length from the method getNumberLength() + 1 (+1 includes CR).
	 */
	private void removeNumber(int removePos, int length) {
				
		try {
			getEditorDocument().remove(removePos, length);
		}
		catch(BadLocationException ex) {
				
			throw new RuntimeException(ex);
		}
	}
	
	
	/*
	 * The insert number routine; inserts the number in the editor document. This
	 * routine is used from the insert action (ActionListener) as well as number
	 * para key press actions (keyPressed or keyReleased methods of KeyListener).
	 *
	 * The parameter insertPos is the position at which the number is to be
	 * inserted. The parameter attributesPos is the position from which the number
	 * is to get its attributes (like color, font, size, etc.). The two parameter
	 * values are derived differently for the insert and the number para key press
	 * actions. The patameter num is the number being inserted.
	 *
	 * Number insert action: the insertPos and attributesPos is the same,
	 * the paraEleStart.
	 * Enter key press: the insertPos is the current caret position of keyReleased(),
	 * and the attributesPos is the previous paraEleStart position from
	 * keyPressed() method.
	 */
	private void insertNumber(int insertPos, int attributesPos, Integer num) {

		try {
			getEditorDocument().insertString(insertPos,
								getNumberString(num),
								getNumbersAttributes(attributesPos, num));
		}
		catch(BadLocationException ex) {

			throw new RuntimeException(ex);
		}
	}
	
	//============ HELPER METHODS FOR NumbersParaKeyListener END ============

	
	/*
	 * Key listener class for key press and release actions within a numbered
	 * para. The keys include Enter, Backspace, Delete and Left. The Enter press
	 * is implemented with both the keyPressed and keyReleased methods. The Delete,
	 * Backspace and Left key press is implemented within the keyPressed.
	 *
	 * This also includes key press actions (backspace, enter and delete) for
	 * the text selected within the numbered paras.
	 */
	public class NumbersParaKeyListener implements KeyListener {
	
		// These two variables are derived in the keyPressed and are used in
		// keyReleased method.
		private String prevParaText_;
		private int prevParaEleStart_;
		
		// Identifies if a key is pressed in a numbered para.
		// This is required to distinguish from the bulleted para.
		private boolean numberedPara_; 


		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
		
			String selectedText = editor__.getSelectedText();
			
			if ((selectedText == null) || (selectedText.trim().isEmpty())) {

				// continue, processing key press without any selected text
			}
			else {
				// text is selected within numbered para and a key is pressed
				doReplaceSelectionRoutine();
				return;
			}
			
			numberedPara_ = false;
			int pos = editor__.getCaretPosition();
			
			if (! isNumberedParaForPos(pos)) {
			
				return;
			}
			
			Element paraEle = getEditorDocument().getParagraphElement(pos);
			int paraEleStart = paraEle.getStartOffset();
			
			switch (e.getKeyCode()) {
			
				case KeyEvent.VK_LEFT: // same as that of VK_KP_LEFT
				case KeyEvent.VK_KP_LEFT: int newPos = pos - 
													(getNumberLength(paraEleStart) + 1);
										doLeftArrowKeyRoutine(newPos, startPosPlusNum__);
										break;			
				case KeyEvent.VK_DELETE: doDeleteKeyRoutine(paraEle, pos);
										break;
				case KeyEvent.VK_BACK_SPACE: doBackspaceKeyRoutine(paraEle);
										break;
				case KeyEvent.VK_ENTER: getPrevParaDetails(pos);
										break;
			}

		} // keyPressed()

		private boolean isNumberedParaForPos(int caretPos) {

			Element paraEle = getEditorDocument().getParagraphElement(caretPos);
		
			if (isNumberedPara(paraEle.getStartOffset())) {
			
				return true;
			}
			
			return false;
		}
		
		/*
		 * Routine for processing selected text with numbered paras
		 * after pressing Enter, Backspace or Delete keys, and the
		 * paste insert replacing the selected text.
		 */
		private void doReplaceSelectionRoutine() {
			
			// Get selection start and end para details.
			// Check if there are numbered paras at top and bottom
			// of the selection. Re-number if needed i.e., when selection
			// is replaced in the middle of numbered paras or at the top
			// items of the numbered paras.
			
			StyledDocument doc = getEditorDocument();			
			Element topParaEle = doc.getParagraphElement(editor__.getSelectionStart());
			Element bottomParaEle = doc.getParagraphElement(editor__.getSelectionEnd());

			int bottomParaEleStart = bottomParaEle.getStartOffset();			
			int bottomParaEleEnd = bottomParaEle.getEndOffset();
			
			// No numbered text at bottom, no processing required -or-
			// no next para after selection end (end of document text).
			if ((! isNumberedPara(bottomParaEleStart)) ||
					(bottomParaEleEnd > doc.getLength())) {

				return;
			}
			
			// Check if para following the selection end is numbered or not.
			Element paraEle = doc.getParagraphElement(bottomParaEleEnd + 1);
			int paraEleStart = paraEle.getStartOffset();
			
			if (! isNumberedPara(paraEleStart)) {
			
				return;
			}
			
			// Process re-numbering

			Integer numTop = getParaNumber(topParaEle.getStartOffset());
			
			if (numTop != null) {
				
				// There are numbered items above the removed para, and
				// there are numbered items following the removed para;
				// bottom numbers start from numTop + 1.
				doNewNumbers(paraEleStart, numTop);
			}
			else { 
				// numTop == null
				// There are no numbered items above the removed para, and
				// there are numbered items following the removed para;
				// bottom numbers start from 1.
				doNewNumbers(paraEleStart, 0);
			}

		} // doReplaceSelectionRoutine()
		
		
		
		/*
		 * Common routine to arrive at new numbers and replace the previous
		 * ones after the following actions within numbered para list:
		 * - Enter, Delete, Backspace key press.
		 * - Delete, Backspace and paste-insert selected text.
		 */
		private void doNewNumbers(int nextParaEleStart, Integer newNum) {
	
			StyledDocument doc = getEditorDocument();
			Element nextParaEle = doc.getParagraphElement(nextParaEleStart);
			boolean nextParaIsNumbered = true;

			NUMBERED_PARA_LOOP:
			while (nextParaIsNumbered) {

				Integer oldNum = getParaNumber(nextParaEleStart);
				newNum++;				
				replaceNumbers(nextParaEleStart, oldNum, newNum);

				nextParaIsNumbered = false;
				
				// Get following para details after number is replaced for a para

				int nextParaEleEnd = nextParaEle.getEndOffset();
				int nextParaPos = nextParaEleEnd + 1;
				
				if (nextParaPos > doc.getLength()) {

					break NUMBERED_PARA_LOOP; // no next para, end of document text
				}
				
				nextParaEle = doc.getParagraphElement(nextParaPos);
				nextParaEleStart = nextParaEle.getStartOffset();
				nextParaIsNumbered = isNumberedPara(nextParaEleStart);			
			}
			// NUMBERED_PARA_LOOP
			
		} // doNewNumbers()

		private void replaceNumbers(int nextParaEleStart, Integer prevNum,
									Integer newNum) {
				
			try {
				((HTMLDocument) getEditorDocument()).replace(
													nextParaEleStart,
													getNumberString(prevNum).length(), 
													getNumberString(newNum), 
								getNumbersAttributes(nextParaEleStart, newNum));
			}
			catch(BadLocationException ex) {
				
				throw new RuntimeException(ex);
			}
		}	
	
		// Delete key press routine within a numbered para.
		private void doDeleteKeyRoutine(Element paraEle, int pos) {

			int paraEleEnd = paraEle.getEndOffset();
			
			if (paraEleEnd > getEditorDocument().getLength()) {

				return; // no next para, end of document text
			}
				
			if (pos == (paraEleEnd - 1)) { // last char of para; -1 is for CR
			
				Element nextParaEle =
						getEditorDocument().getParagraphElement(paraEleEnd + 1);
				int nextParaEleStart = nextParaEle.getStartOffset();
				
				if (isNumberedPara(nextParaEleStart)) {

					removeNumber(pos, getNumberLength(nextParaEleStart));
					doReNumberingForDeleteKey(paraEleEnd + 1);
				}
				// else, not a numbered para
				// delete happens normally (one char)
			}
		}

		private void doReNumberingForDeleteKey(int delParaPos) {
	
			// Get para element details where delete key is pressed
			StyledDocument doc = getEditorDocument();
			Element paraEle = doc.getParagraphElement(delParaPos);
			int paraEleStart = paraEle.getStartOffset();
			int paraEleEnd = paraEle.getEndOffset();

			// Get bottom para element details
			Element bottomParaEle = doc.getParagraphElement(paraEleEnd + 1);
			int bottomParaEleStart = bottomParaEle .getStartOffset();

			// In case bottom para is not numbered or end of document,
			// no re-numbering is required.
			if ((paraEleEnd > doc.getLength()) || 
					(! isNumberedPara(bottomParaEleStart))) {
			
				return;
			}
			
			Integer n = getParaNumber(paraEleStart);
			doNewNumbers(bottomParaEleStart, n);	
		}

		// Backspace key press routine within a numbered para.
		// Also, see EditorCaretListener.
		private void doBackspaceKeyRoutine(Element paraEle) {
			
			// In case the position of cursor at the backspace is just after
			// the number: remove the number and re-number the following ones.
			if (startPosPlusNum__) {

				int startOffset = paraEle.getStartOffset();
				removeNumber(startOffset, getNumberLength(startOffset));
				doReNumberingForBackspaceKey(paraEle, startOffset);				
				startPosPlusNum__ = false;
			}
		}
		
		private void doReNumberingForBackspaceKey(Element paraEle, int paraEleStart) {
			
			// Get bottom para element and check if numbered.
			StyledDocument doc = getEditorDocument();		
			Element bottomParaEle = doc.getParagraphElement(paraEle.getEndOffset() + 1);
			int bottomParaEleStart = bottomParaEle.getStartOffset();
		
			if (! isNumberedPara(bottomParaEleStart)) {
		
				return; // there are no numbers following this para, and
						// no re-numbering required.
			}
		
			// Get top para element and number
			
			Integer numTop = null;
			
			if (paraEleStart == 0) {
			
				// beginning of document, no top para exists
				// before the document start; numTop = null
			}
			else {	
				Element topParaEle = doc.getParagraphElement(paraEleStart - 1);
				numTop = getParaNumber(topParaEle.getStartOffset());
			}
		
			if (numTop == null) {
		
				// There are no numbered items above the removed para, and
				// there are numbered items following the removed para;
				// bottom numbers start from 1.
				doNewNumbers(bottomParaEleStart, 0);
			}
			else { 
				// numTop != null
				// There are numbered items above the removed para, and
				// there are numbered items following the removed para;
				// bottom numbers start from numTop + 1.
				doNewNumbers(bottomParaEleStart, numTop);
			}
		}
		
		// This method is used with Enter key press routine.
		// Two instance variable values are derived here and are used
		// in the keyReleased() method: prevParaEleStart_ and prevParaText_
		private void getPrevParaDetails(int pos) {
		
			pos =  pos - 1;
			
			if (isNumberedParaForPos(pos)) {
			
				numberedPara_ = true;
				Element paraEle = getEditorDocument().getParagraphElement(pos);
				prevParaEleStart_ = paraEle.getStartOffset();
				prevParaText_ =
						getPrevParaText(prevParaEleStart_, paraEle.getEndOffset());
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		
			if (! numberedPara_) {

				return;
			}
		
			switch (e.getKeyCode()) {
			
				case KeyEvent.VK_ENTER: doEnterKeyRoutine();
										break;
			}
		}
		
		// Enter key press routine within a numbered para.
		// Also, see keyPressed().
		private void doEnterKeyRoutine() {
			
			String prevParaText = prevParaText_;
			int prevParaEleStart = prevParaEleStart_;					
			int len = getNumberLength(prevParaEleStart) + 1; // +1 for CR

			// Check if prev para with numbers has text					
			if (prevParaText.length() == len) {
				
				// Para has numbers and no text, remove number from para
				removeNumber(prevParaEleStart, len);		
				editor__.setCaretPosition(prevParaEleStart);
				return;
			}
			// Prev para with number and text,			
			// insert number for new para (current position)
			Integer num = getParaNumber(prevParaEleStart);
			num++;
			insertNumber(editor__.getCaretPosition(), prevParaEleStart, num);
			
			// After insert, check for numbered paras following the newly
			// inserted numberd para; and re-number those paras.
			
			// Get newly inserted number para details
			StyledDocument doc = getEditorDocument();	
			Element newParaEle = doc.getParagraphElement(editor__.getCaretPosition());
			int newParaEleEnd = newParaEle.getEndOffset();

			if (newParaEleEnd > doc.getLength()) {

				return; // no next para, end of document text
			}

			// Get next para (following the newly inserted one) and
			// re-number para only if already numered.
			Element nextParaEle = doc.getParagraphElement(newParaEleEnd + 1);
			int nextParaEleStart = nextParaEle.getStartOffset();
			
			if (isNumberedPara(nextParaEleStart)) {

				doNewNumbers(nextParaEleStart, num);
			}

		} // doEnterKeyRoutine()
		
	} // NumbersParaKeyListener
	
	
	//============ HELPER METHODS FOR EditorCaretListener BEGIN ============
	//============ HELPER METHODS FOR EditorCaretListener END ============

	/*
	 * This listener is used with the bulleted and numbered para actions.
	 * The bulleted item's bullet is made of bullet + space. The cursor (caret)
	 * is not allowed to position at the bullet para's first position and at 
	 * the space after the bullet. This listener controls the cursor position
	 * in such cases; the cursor jumps/moves to the after bullet+space position
	 * (indicated by startPosPlusBullet__ boolean instance variable).
	 *
	 * Also, the backspace and left-arrow key usage requires startPosPlusBullet__
	 * to perform the routines (see doLeftArrowKeyRoutine() and BulletParaKeyListener).
	 *
	 * This is also similar for numbered paras (see startPosPlusNum__ and
	 * NumbersParaKeyListener).
	 */
	private class EditorCaretListener implements CaretListener {


		@Override
		public void caretUpdate(CaretEvent e) {

			startPosPlusBullet__ = false;
			startPosPlusNum__ = false;
			Element paraEle = 
				getEditorDocument().getParagraphElement(editor__.getCaretPosition());
			int paraEleStart = paraEle.getStartOffset();

			if (isBulletedPara(paraEleStart)) {

				if (e.getDot() == (paraEleStart + BULLET_LENGTH)) {

					startPosPlusBullet__ = true;
				}
				else if (e.getDot() < (paraEleStart + BULLET_LENGTH)) {
			
					editor__.setCaretPosition(paraEleStart + BULLET_LENGTH);
				}
				else {
					// continue
				}
			}
			else if (isNumberedPara(paraEleStart)) {
			
				int numLen = getNumberLength(paraEleStart);

				if (e.getDot() < (paraEleStart + numLen)) {
			
					editor__.setCaretPosition(paraEleStart + numLen);
				}
				else if (e.getDot() == (paraEleStart + numLen)) {

					startPosPlusNum__ = true;
				}
				else {
					// continue
				}
			}
			else {
				// not a bulleted or numbered para
			}
		}
	}


	/*
	 * Action listener class for bullet insert and remove button actions.
	 */
	private class BulletActionListener implements ActionListener {

		private BulletActionType bulletActionType;
		
		public BulletActionListener(BulletActionType actionType) {
		
			bulletActionType = actionType;
		}

		/*
		 * Common routine for insert and remove bullet actions. This routine
		 * loops thru the selected text and inserts or removes a bullet.
		 * - For insert action: inserts a bullet at the beginning of each para
		 * of selected text. The paras already bulleted or numbered are ignored.
		 * - For remove bullet action: removes the bullet in case a para is 
		 * bulleted for the selected text.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
		
			String selectedText = editor__.getSelectedText();
			
			if ((selectedText == null) || (selectedText.trim().isEmpty())) {

				editor__.requestFocusInWindow();
				return;
			}
			
			StyledDocument doc = getEditorDocument();			
			Element paraEle = doc.getParagraphElement(editor__.getSelectionStart());
			int paraEleStart = paraEle.getStartOffset();
			int paraEleEnd = 0;
			
			BULLETS_PARA_LOOP:
			do {
				paraEle = doc.getParagraphElement(paraEleStart);
				paraEleEnd = paraEle.getEndOffset();
				
				if ((paraEleEnd - paraEleStart) <= 1) { // empty line/para
				
					paraEleStart = paraEleEnd;
					paraEle = doc.getParagraphElement(paraEleStart);
					continue BULLETS_PARA_LOOP;
				}

				switch (bulletActionType) {
				
					case INSERT:
						if ((! isBulletedPara(paraEleStart)) &&
								(! isNumberedPara(paraEleStart))) {
				
							insertBullet(paraEleStart, paraEleStart);
						}
						
						break; // switch
				
					case REMOVE:
						if (isBulletedPara(paraEleStart)) {
				
							removeBullet(paraEleStart, BULLET_LENGTH);
						}
				}

				// Get the updated para element details after bulleting
				paraEle = doc.getParagraphElement(paraEleStart);
				paraEleEnd = paraEle.getEndOffset();

				paraEleStart = paraEleEnd;

			} while (paraEleEnd <= editor__.getSelectionEnd());
			// BULLETS_PARA_LOOP
			
			editor__.requestFocusInWindow();
		}
	}
	
	/*
	 * Action listener class for number insert and remove button actions.
	 */
	private class NumbersActionListener implements ActionListener {

		private NumbersActionType numbersActionType;
		private int n;
	
		public NumbersActionListener(NumbersActionType actionType) {
		
			numbersActionType = actionType;
		}

		/*
		 * Common routine for insert and remove numbers actions. This routine
		 * loops thru the selected text and inserts or removes a number.
		 * - For insert action: inserts a number at the beginning of each para
		 * of selected text. The paras already bulleted or numbered are ignored.
		 *  Note that the numbering always starts from 1.
		 * - For remove action: removes the number in case a para is numbered
		 * for the selected text.
		 */		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			StyledDocument doc = getEditorDocument();
			String selectedText = editor__.getSelectedText();
			
			if ((selectedText == null) || (selectedText.trim().isEmpty())) {

				editor__.requestFocusInWindow();
				return;
			}
			
			Element paraEle = doc.getParagraphElement(editor__.getSelectionStart());
			int paraEleStart = paraEle.getStartOffset();
			int paraEleEnd = 0;
			boolean firstPara = true;
			
			NUMBERS_PARA_LOOP:
			do {
				paraEle = doc.getParagraphElement(paraEleStart);
				paraEleEnd = paraEle.getEndOffset();
				
				if ((paraEleEnd - paraEleStart) <= 1) { // empty line
				
					if (firstPara) {
					
						firstPara = false;
						n = 0;
					}

					paraEleStart = paraEleEnd;
					paraEle = doc.getParagraphElement(paraEleStart);
					continue NUMBERS_PARA_LOOP;
				}

				switch (numbersActionType) {
				
					case INSERT:
					
						if (isBulletedPara(paraEleStart)) {
						
							break; // switch
						}
					
						if (firstPara) {
					
							firstPara = false;
							n = 0;
						}
						
						if (isNumberedPara(paraEleStart)) {
				
							// remove any existing number
							removeNumber(paraEleStart, getNumberLength(paraEleStart));
						}
					
						if (! isNumberedPara(paraEleStart)) {
				
							Integer nextN = new Integer(++n);
							insertNumber(paraEleStart, paraEleStart, nextN);
						}
						
						break; // switch
				
					case REMOVE:
					
						if (isNumberedPara(paraEleStart)) {
				
							removeNumber(paraEleStart, getNumberLength(paraEleStart));
						}
				}

				// Get the updated para element details after numbering
				paraEle = doc.getParagraphElement(paraEleStart);
				paraEleEnd = paraEle.getEndOffset();

				paraEleStart = paraEleEnd;

			} while (paraEleEnd <= editor__.getSelectionEnd());
			// NUMBERS_PARA_LOOP

			editor__.requestFocusInWindow();
		}
	}
	
	
	private class FontSizeItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {

			if ((e.getStateChange() != ItemEvent.SELECTED) ||
				(fontSizeComboBox__.getSelectedIndex() == 0)) {

				return;
			}
			
			String fontSizeStr = (String) e.getItem();			
			int newFontSize = 0;
			
			try {
				newFontSize = Integer.parseInt(fontSizeStr);
			}
			catch (NumberFormatException ex) {

				return;
			}

			fontSizeComboBox__.setAction(new FontSizeAction(fontSizeStr, newFontSize));	
			fontSizeComboBox__.setSelectedIndex(0); // initialize to (default) select
			editor__.requestFocusInWindow();
		}
	} // FontSizeItemListener
	
	
	private class FontFamilyItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {

			if ((e.getStateChange() != ItemEvent.SELECTED) ||
				(fontFamilyComboBox__.getSelectedIndex() == 0)) {
			
				return;
			}
			
			String fontFamily = (String) e.getItem();
			fontFamilyComboBox__.setAction(new FontFamilyAction(fontFamily, fontFamily));	
			fontFamilyComboBox__.setSelectedIndex(0); // initialize to (default) select
			editor__.requestFocusInWindow();
		}
	} // FontFamilyItemListener
	
	
	
	private class PictureFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {

			JButton button = (JButton) e.getComponent();
			button.setBorder(new LineBorder(Color.GRAY));
			pictureButtonName__ = button.getName();
		}
		
		@Override
		public void focusLost(FocusEvent e) {

			((JButton) e.getComponent()).setBorder(new LineBorder(Color.WHITE));
		}
	}
	
	
	private class PictureInsertActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			System.out.println("THIS DOESN'T WORK YET!!!");
			
			File pictureFile = choosePictureFile();
			
			if (pictureFile == null) {
			
				editor__.requestFocusInWindow();
				return;
			}
			
			ImageIcon icon = new ImageIcon(pictureFile.toString());			
			JButton picButton = new JButton(icon);
			picButton.setBorder(new LineBorder(Color.WHITE));
			picButton.setMargin(new Insets(0,0,0,0));
			picButton.setAlignmentY(.9f);
			picButton.setAlignmentX(.9f);
			picButton.addFocusListener(new PictureFocusListener());
			picButton.setName("PICTURE_ID_" + new Random().nextInt());
			editor__.insertComponent(picButton);
			editor__.requestFocusInWindow();
		}
		
		private File choosePictureFile() {
		
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
								"PNG, JPG & GIF Images", "png", "jpg", "gif");
			chooser.setFileFilter(filter);
			
			if (chooser.showOpenDialog(curPanel) == JFileChooser.APPROVE_OPTION) {
			
				return chooser.getSelectedFile();
			}
			else {
				return null;
			}
		}
	} // PictureInsertActionListener
	
	
	private class PictureDeleteActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		
			System.out.println("THIS DOESN'T WORK YET!!!");
			
			StyledDocument doc = getEditorDocument();
			ElementIterator iterator = new ElementIterator(doc);
			Element element;
			
			while ((element = iterator.next()) != null) {
			
				AttributeSet attrs = element.getAttributes();
			
				if (attrs.containsAttribute(ELEM, COMP)) {

					JButton button = (JButton) StyleConstants.getComponent(attrs);
				
					if (button.getName().equals(pictureButtonName__)) {

						try {
							doc.remove(element.getStartOffset(), 1); // length = 1
						}
						catch (BadLocationException ex_) {

							throw new RuntimeException(ex_);
						}
					}
				}
			}
	
			editor__.requestFocusInWindow();
			pictureButtonName__ = null;
		}
	} // PictureDeleteActionListener
}