package com.poetry.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class JacksonConfig
implements BeanPostProcessor
{
	protected final Logger logger = LoggerFactory.getLogger( getClass() );
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		logger.trace( "Before :{} - {}", beanName, bean );
        return bean;
    }

	@Override
    public Object postProcessAfterInitialization(
    	Object bean, String beanName
    )
	throws BeansException
	{
		logger.trace( "After :{} - {}", beanName, bean );
        if ( bean instanceof MappingJackson2HttpMessageConverter )
        {
            final MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) bean;
            ObjectMapper objectMapper = new ObjectMapper() {
            	@Override
            	public
            	void
            	writeValue(
            		final JsonGenerator jgen,
            		final Object value
            	)
            	throws IOException, JsonGenerationException, JsonMappingException
            	{
            		jgen.useDefaultPrettyPrinter();
            		super.writeValue( jgen, value );
            	}
            };
            objectMapper.configure( SerializationFeature.INDENT_OUTPUT, true );
            converter.setObjectMapper(objectMapper);
        }
        return bean;
    }
}
