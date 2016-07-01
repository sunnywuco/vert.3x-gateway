package com.stone.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractSettings {

	private static ObjectMapper mapper = new ObjectMapper();

	private static Logger log = LoggerFactory.getLogger(AbstractSettings.class);
	@Override
	public String toString() {
		try {
			return mapper.writeValueAsString(this);
		} catch ( Exception e ) {
			log.error("JDBC Configuration is error");
			//return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
		return "";
	}
}
