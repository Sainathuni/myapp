package org.saibaba.common.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.config.ListFactoryBean;

public class ListMergerFactory extends ListFactoryBean {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setSourceList(List listOfLists) {
		List mergedList = new ArrayList();
		if(listOfLists != null && listOfLists.size() > 0) {
			for(Iterator iter = listOfLists.iterator(); iter.hasNext();) {
				List list = (List) iter.next();
				if(list != null && list.size() > 0) {
					mergedList.addAll(list);
				}
			}
		}
		super.setSourceList(mergedList);
	}
}
