package org.gcube.portlets.user.td.expressionwidget.client.utils;

import com.sencha.gxt.widget.core.client.box.MessageBox;

/**
 * 
 * @author Giancarlo Panichi 
 * 
 *
 */
public class InfoMessageBox extends MessageBox {

	/**
	 * Creates a message box with an info icon and the specified title and
	 * message.
	 * 
	 * @param title
	 *            the message box title
	 * @param message
	 *            the message displayed in the message box
	 */
	public InfoMessageBox(String title, String message) {
		super(title, message);

		setIcon(ICONS.info());
	}

}