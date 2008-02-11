package org.eclipse.ui.ide.examples.markers.markerSupport;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.views.markers.MarkerField;
import org.eclipse.ui.views.markers.MarkerItem;
import org.eclipse.ui.views.markers.MarkerSupportConstants;

public class ExampleMarkerField extends MarkerField {

	public ExampleMarkerField() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getValue(MarkerItem item) {

		String message = item.getAttributeValue(IMarker.MESSAGE,
				MarkerSupportConstants.EMPTY_STRING);
		String prefix = getFixablePrefix(item);

		if (prefix == null)
			return message;

		return prefix.concat(message);
	}

	private String getFixablePrefix(MarkerItem item) {

		if (!item.isConcrete())
			return null;

		IMarker marker = item.getMarker();
		// If there is no image get the full image rather than the decorated
		// one
		if (marker != null) {
			if (IDE.getMarkerHelpRegistry().hasResolutions(marker)) {
				return "[Fixable] ";
			}

		}

		return null;

	}
	
	@Override
	public String getColumnHeaderText() {
		return "Alternate Description";
	}
	
	@Override
	public Image getImage(MarkerItem item) {
		return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_INFO);
	}

}
