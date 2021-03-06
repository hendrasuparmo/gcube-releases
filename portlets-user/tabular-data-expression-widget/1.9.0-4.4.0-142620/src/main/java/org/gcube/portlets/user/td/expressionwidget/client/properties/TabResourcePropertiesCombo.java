package org.gcube.portlets.user.td.expressionwidget.client.properties;

import org.gcube.portlets.user.td.gwtservice.shared.tr.TabResource;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * 
 * @author "Giancarlo Panichi" 
 * <a href="mailto:g.panichi@isti.cnr.it">g.panichi@isti.cnr.it</a> 
 *
 */
public interface TabResourcePropertiesCombo extends
		PropertyAccess<TabResource> {

	@Path("id")
	ModelKeyProvider<TabResource> id();
	
	@Path("name")
	LabelProvider<TabResource> label();

}
