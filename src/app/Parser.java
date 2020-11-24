package app;

import exceptions.FormatoDeSaídaInvalidoException;

public class Parser {
	private String formatToSave;

	public String getFormatToSave() {
		return this.formatToSave;
	}
	
	public void setFormatToSave(String format) throws FormatoDeSaídaInvalidoException {
		checkValidOutputFormat(format);
		this.formatToSave = format;
	}
	
	private void checkValidOutputFormat(String format) throws FormatoDeSaídaInvalidoException {
		if (!format.equals("column") && !format.equals("row")) {
			throw new FormatoDeSaídaInvalidoException(format);
		}
	}
	
	public void save() {
		if (this.formatToSave.equals("column")) {
			saveAsColumn();
		} else {
			saveAsRow();
		}
	}
	
	public void saveAsRow() {
		
	}

	public void saveAsColumn() {

	}
}
