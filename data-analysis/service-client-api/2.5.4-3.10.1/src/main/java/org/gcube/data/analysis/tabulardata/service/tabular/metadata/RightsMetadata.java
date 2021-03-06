package org.gcube.data.analysis.tabulardata.service.tabular.metadata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Rights")
@XmlAccessorType(XmlAccessType.FIELD)
public class RightsMetadata implements TabularResourceMetadata<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7840537041687523155L;

	private String value;

	public RightsMetadata() {
	}

	public RightsMetadata(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RightsMetadata [\n\tvalue=");
		builder.append(value);
		builder.append("\n]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RightsMetadata other = (RightsMetadata) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
