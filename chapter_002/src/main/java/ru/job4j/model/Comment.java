package ru.job4j.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**Implementation of comment. */
public class Comment {

	/**Comment's text.*/
	private String textField;
	/**When comment was create.*/
	private Calendar create;
	/**Comment's author.*/
	private String author;

	/**Setter of the comment's author.
	*@param auth - author*/
	public void setAuthor(String auth) {
		this.author = auth;
	}

	/**Setter of the comment's text.
	*@param text - text*/
	public void setText(String text) {
		this.textField = text;
	}

	/**Setter of comment created date and time.
	*@param millis - time in milliseconds*/
	public void setCreate(long millis) {
		this.create = new GregorianCalendar();
		this.create.setTimeInMillis(millis);
	}

	/**Getter of comment's text.
	*@return textField - text*/
	public String getText() {
		return this.textField;
	}

	/**Getter of comment's author.
	*@return author - author*/
	public String getAuthor() {
		return this.author;
	}

	/**Getter of comment's create date and time.
	*@return date and time in format "HH:mm DD/MM/YY"*/
	public String getCreate() {
		return new StringBuilder()
					.append(this.create.get(Calendar.HOUR_OF_DAY))
					.append(":")
					.append(this.create.get(Calendar.MINUTE))
					.append(" ")
					.append(this.create.get(Calendar.DAY_OF_MONTH))
					.append("/")
					.append(this.create.get(Calendar.MONTH) + 1)
					.append("/")
					.append(this.create.get(Calendar.YEAR))
					.toString();
	}

}
