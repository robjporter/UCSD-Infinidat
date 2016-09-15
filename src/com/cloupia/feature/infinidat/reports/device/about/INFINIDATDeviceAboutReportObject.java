package com.cloupia.feature.infinidat.reports.device.about;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

@SuppressWarnings("unused")
public class INFINIDATDeviceAboutReportObject {
	static Logger logger = Logger.getLogger(INFINIDATDeviceAboutReportObject.class);
	String cisco_logo = "<a href='http://www.cisco.com' style='color: #FFFFFF;text-decoration:none'><img src='http://www.cisco.com/web/KR/cspp/common/images/virtualLab/ciscoLogo.png'></a>";
	String infinidat_logo = "<a href='http://www.infinidat.com' style='color: #FFFFFF;text-decoration:none'><img src='https://partner.infinidat.com/web/infinidat/logo-38f085716c6c77671aea4c8fb6c516c4.png'></a>";
	
	@FormField(type = FormFieldDefinition.FIELD_TYPE_HTML_LABEL, label = "", htmlPopupLabel = "")
	private String ciscoLogo;

	@FormField(type = FormFieldDefinition.FIELD_TYPE_HTML_LABEL, label = "", htmlPopupLabel = "")
	private String infinidatLogo;

	@FormField(type = FormFieldDefinition.FIELD_TYPE_HTML_LABEL, label = "", htmlPopupLabel = "")
	private String jointLogo;
	
	@FormField(type = FormFieldDefinition.FIELD_TYPE_HTML_LABEL, label = "", htmlPopupLabel = "")
	private String buildLogo;

	@FormField(type = FormFieldDefinition.FIELD_TYPE_HTML_LABEL, label = "", htmlPopupLabel = "")
	private String buildVersion;

	@FormField(type = FormFieldDefinition.FIELD_TYPE_HTML_LABEL, label = "", htmlPopupLabel = "")
	private String newVersion;
	
	@FormField(label = "HTTP Timeout", type = FormFieldDefinition.FIELD_TYPE_NUMBER, minValue = 1 , maxValue = 65535)
    private int httpTimeout;
	
	@FormField(label = "HTTP Proxy URL", help = "HTTP Proxy")
 	private String proxyURL;
	
	@FormField(label = "HTTP Proxy Port", type = FormFieldDefinition.FIELD_TYPE_NUMBER, minValue = 1000 , maxValue = 65535)
    private int proxyPort;

	
	public String getCiscoLogo() {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportObject:getCiscoLogo ####----");}
		return ciscoLogo;
	}
	public void setCiscoLogo(String dummyLink) {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportObject:setCiscoLogo ####----");}
		this.ciscoLogo = dummyLink;
	}
	public String getInfinidatLogo() {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportObject:getInfinidatLogo ####----");}
		return infinidatLogo;
	}
	public void setInfinidatLogo(String dummyLink) {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportObject:setInfinidatLogo ####----");}
		this.infinidatLogo = dummyLink;
	}
	public String getJointLogo() {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportObject:getJointLogo ####----");}
		return jointLogo;
	}
	public void setJointLogo(String dummyLink) {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportObject:setJointLogo ####----");}
		this.jointLogo = dummyLink;
	}
	public String getBuildLogo() {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportObject:getBuildLogo ####----");}
		return buildLogo;
	}
	public void setBuildLogo(String dummyLink) {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportObject:setBuildLogo ####----");}
		this.buildLogo = dummyLink;
	}
	public String getBuildVersion() {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportObject:getBuildVersion ####----");}
		return buildVersion;
	}
	public void setBuildVersion(String dummyLink) {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportObject:setBuildVersion ####----");}
		this.buildVersion = dummyLink;
	}
	public String getNewBuildVersion() {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportObject:getNewBuildVersion ####----");}
		return newVersion;
	}
	public void setNewBuildVersion(String dummyLink) {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportObject:setNewBuildVersion ####----");}
		this.newVersion = dummyLink;
	}
	public int getHTTPTimeout() {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportObject:getHTTPTimeout ####----");}
		return httpTimeout;
	}
	public void setHTTPTimeout(int data) {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportObject:setHTTPTimeout ####----");}
		this.httpTimeout = data;
	}
	public String getHTTPProxyURL() {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportObject:getHTTPProxyURL ####----");}
		return proxyURL;
	}
	public void setHTTPProxyURL(String data) {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportObject:setHTTPProxyURL ####----");}
		this.proxyURL = data;
	}
	public int getHTTPProxyPort() {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportObject:getHTTPProxyPort ####----");}
		return proxyPort;
	}
	public void setHTTPProxyPort(int data) {
		if(INFINIDATConstants.DEBUG_ABOUT && INFINIDATConstants.DEBUG){logger.info("----#### INFINIDATDeviceAboutReportObject:setHTTPProxyPort ####----");}
		this.proxyPort = data;
	}
}
