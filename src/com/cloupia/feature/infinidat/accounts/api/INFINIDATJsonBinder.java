package com.cloupia.feature.infinidat.accounts.api;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.accounts.api.INFINIDATJSONBinder;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.service.cIM.inframgr.collector.controller.ItemDataObjectBinderIf;

@SuppressWarnings("unused")
public abstract class INFINIDATJSONBinder implements ItemDataObjectBinderIf {
	private static Logger logger = Logger.getLogger(INFINIDATJSONBinder.class);
	@SuppressWarnings("rawtypes")
	public abstract List<Class> getPersistantClassList();

	protected void bindContext( Object obj, Map<String, Object> context ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATJSONBinder:bindContext ####----" );}
		for( Map.Entry<String, Object> entry : context.entrySet()) {
			String varName = entry.getKey();
			Object value = entry.getValue();
			try {
				Field field = obj.getClass().getDeclaredField( varName );
				field.setAccessible( true );
				if( value != null ) {
					field.set( obj, value );
				}
			} catch( SecurityException e ) {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATJSONBinder:bindContext::ERROR::: SECURITY EXCEPTION ####---- ERROR: " + e.getMessage());}
			} catch( NoSuchFieldException e ) {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATJSONBinder:bindContext::ERROR::: MISSING FIELD EXCEPTION ####---- ERROR: " + e.getMessage());}
			} catch( IllegalArgumentException e ) {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATJSONBinder:bindContext::ERROR::: ILLEGAL ARGUMENT EXCEPTION ####---- ERROR: " + e.getMessage());}
			} catch (IllegalAccessException e) {
				if(INFINIDATConstants.DEBUG_ERRORS){logger.info( "----#### INFINIDATJSONBinder:bindContext::ERROR::: ILLEGAL ACCESS EXCEPTION ####---- ERROR: " + e.getMessage());}
			}
		}
	}
}