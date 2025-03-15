package com.tc.labt.sgabs.cokpit.datatable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Column implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement
    public String data;
    public String name;
    public boolean searchable;
    public boolean orderable;

    public Search search;
}
