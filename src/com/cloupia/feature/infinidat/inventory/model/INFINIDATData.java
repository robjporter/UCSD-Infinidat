package com.cloupia.feature.infinidat.inventory.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.model.cIM.InventoryDBItemIf;
import com.cloupia.service.cIM.inframgr.reports.simplified.ReportableIf;

@SuppressWarnings("unused")
@PersistenceCapable( detachable = "true", table = "infinidat_data" )
public class INFINIDATData implements InventoryDBItemIf, ReportableIf {
	private static Logger logger = Logger.getLogger( INFINIDATData.class );
	@Persistent
	private String accountName;
	@Persistent
	private long usedPhysicalCapacity = 0;
	@Persistent
	private long freePhysicalCapacity = 0;
	@Persistent
	private long usedVirtualCapacity = 0;
	@Persistent
	private long freeVirtualCapacity = 0;
	@Persistent
	private int nodeCount = 0;
	@Persistent
	private int eventCount = 0;
	@Persistent
	private int poolCount = 0;
	@Persistent
	private int volumeCount = 0;
	@Persistent
	private int snapshotCount = 0;
	@Persistent
	private int hostCount = 0;
	@Persistent
	private int clusterCount = 0;
	@Persistent
	private int filesystemCount = 0;
	@Persistent
	private int driveCount = 0;
	@Persistent
	private int cloneCount = 0;
	@Persistent
	private int exportCount = 0;
	@Persistent
	private long longPoolPhysicalCapacity = 0;
	@Persistent
	private long longPoolVirtualCapacity = 0;
	@Persistent
	private long longPoolPhysicalFree = 0;
	@Persistent
	private long longPoolVirtualFree = 0;
	@Persistent
	private long uptime = 0;
	@Persistent
	private long localtime = 0;
	@Persistent
	private String controllerIP = "";
	@Persistent
	private String controllerNetmask = "";
	@Persistent
	private String controllerGateway = "";
	@Persistent
	private String systemVersion = "";
	@Persistent
	private String guiVersion = "";
	@Persistent
	private String shellVersion = "";
	@Persistent
	private int enclousures = 0;
	@Persistent
	private int racks = 0;
	@Persistent
	private long readLatency = 0;
	@Persistent
	private long writeLatency = 0;
	@Persistent
	private long readBytes = 0;
	@Persistent
	private long writeBytes = 0;
	@Persistent
	private long readOps = 0;
	@Persistent
	private long writeOps = 0;
	@Persistent
	private long totalOps = 0;
	@Persistent
	private long totalBytes = 0;
	@Persistent
	private long totalLatency = 0;
	@Persistent
	private int intInfoCount = 0;
	@Persistent
	private int intWarningCount = 0;
	@Persistent
	private int intErrorCount = 0;
	@Persistent
	private int intCriticalCount = 0;
	@Persistent
	private double lowestAverageReadLatency = 0;
	@Persistent
	private double lowestAverageWriteLatency = 0;
	@Persistent
	private double highestAverageReadLatency = 0;
	@Persistent
	private double highestAverageWriteLatency = 0;
	@Persistent
	private int localUsersCount = 0;
	
	public INFINIDATData() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:INFINIDATData ####----" );}
	}
	public INFINIDATData( String accountName, long capacity, long free, long vcapacity, long vfree, int node, int ecount, int pcount, int vcount, int scount, int hcount, int ccount, int fscount, int dcount, int clcount, int eCount, long ppcapacity, long pvcapacity, long ppfree, long pvfree, int enc, int rack, long uptime, long ltime, String ip, String netmask, String gateway, String ver1, String ver2, String ver3, long rlat, long wlat, long rbyt, long wbyt, long rops, long wops, long tops, long tbyt, long tlat, int info, int warning, int error, int critical, double larl, double lawl, double harl, double hawl, int luc) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:INFINIDATData ####----" );}
		this.accountName = accountName;
		this.usedPhysicalCapacity = capacity;
		this.freePhysicalCapacity = free;
		this.usedVirtualCapacity = vcapacity;
		this.freeVirtualCapacity = vfree;
		this.nodeCount = node;
		this.eventCount = ecount;
		this.poolCount = pcount;
		this.volumeCount = vcount;
		this.snapshotCount = scount;
		this.hostCount = hcount;
		this.clusterCount = ccount;
		this.filesystemCount = fscount;
		this.driveCount = dcount;
		this.cloneCount = clcount;
		this.exportCount = ecount;
		this.longPoolPhysicalCapacity = ppcapacity;
		this.longPoolVirtualCapacity = pvcapacity;
		this.longPoolPhysicalFree = ppfree;
		this.longPoolVirtualFree = pvfree;
		this.enclousures = enc;
		this.racks = rack;
		this.uptime = uptime;
		this.localtime = ltime;
		this.controllerIP = ip;
		this.controllerNetmask = netmask;
		this.controllerGateway = gateway;
		this.systemVersion = ver1;
		this.guiVersion = ver2;
		this.shellVersion = ver3;
		this.readLatency = rlat;
		this.writeLatency = wlat;
		this.readBytes = rbyt;
		this.writeBytes = wbyt;
		this.readOps = rops;
		this.writeOps = wops;
		this.totalOps = tops;
		this.totalBytes = tbyt;
		this.totalLatency = tlat;
		this.intInfoCount = info;
		this.intWarningCount = warning;
		this.intErrorCount = error;
		this.intCriticalCount = critical;
		this.lowestAverageReadLatency = larl;
		this.lowestAverageWriteLatency = lawl;
		this.highestAverageReadLatency = harl;
		this.highestAverageWriteLatency = hawl;
		this.localUsersCount = luc;
	}
	
	public String debug() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:debug ####----" );}
		String space = " | ";
		String tmp = "";
		tmp += space + "USED PHYSICAL CAPACITY: " + this.usedPhysicalCapacity;
		tmp += space + "FREE PHYSICAL CAPACITY: " + this.freePhysicalCapacity;
		tmp += space + "USED VIRTUAL CAPACITY: " + this.usedVirtualCapacity;
		tmp += space + "FREE VIRTUAL CAPACITY: " + this.freeVirtualCapacity;
		tmp += space + "NODE COUNT: " + this.nodeCount;
		tmp += space + "EVENT COUNT: " + this.eventCount;
		tmp += space + "POOL COUNT: " + this.poolCount;
		tmp += space + "VOLUME COUNT: " + this.volumeCount;
		tmp += space + "VOLUME SNAPSHOT COUNT: " + this.snapshotCount;
		tmp += space + "HOST COUNT: " + this.hostCount;
		tmp += space + "CLUSTER COUNT: " + this.clusterCount;
		tmp += space + "FILESYSTEM COUNT: " + this.filesystemCount;
		tmp += space + "DRIVE COUNT: " + this.driveCount;
		tmp += space + "CLONE COUNT: " + this.cloneCount;
		tmp += space + "EXPORT COUNT: " + this.exportCount;
		tmp += space + "POOL PHYSICAL CAPACITY: " + this.longPoolPhysicalCapacity;
		tmp += space + "POOL VIRTUAL CAPACITY: " + this.longPoolVirtualCapacity;
		tmp += space + "POOL PHYSICAL FREE: " + this.longPoolPhysicalFree;
		tmp += space + "POOL VIRTUAL FREE: " + this.longPoolVirtualFree;
		tmp += space + "UPTIME: " + this.uptime;
		tmp += space + "LOCALTIME: " + this.localtime;
		tmp += space + "CONTROLLER IP: " + this.controllerIP;
		tmp += space + "CONTROLLER NETMASK: " + this.controllerNetmask;
		tmp += space + "CONTROLLER GATEWAY: " + this.controllerGateway;
		tmp += space + "SYSTEM VERSION: " + this.systemVersion;
		tmp += space + "GUI VERSION: " + this.guiVersion;
		tmp += space + "SHELL VERSION: " + this.shellVersion;
		tmp += space + "ENCLOUSURES: " + this.enclousures;
		tmp += space + "RACKS: " + this.racks;
		tmp += space + "READ LATENCY: " + this.readLatency;
		tmp += space + "WRITE LATENCY: " + this.writeLatency;
		tmp += space + "READ BYTES: " + this.readBytes;
		tmp += space + "WRITE BYTES: " + this.writeBytes;
		tmp += space + "READ OPS: " + this.readOps;
		tmp += space + "WRITE OPS: " + this.writeOps;
		tmp += space + "TOTAL OPS: " + this.totalOps;
		tmp += space + "TOTAL BYTES: " + this.totalBytes;
		tmp += space + "TOTAL LATENCY: " + this.totalLatency;
		tmp += space + "INFO COUNT: " + this.intInfoCount;
		tmp += space + "WARNING COUNT: " + this.intWarningCount;
		tmp += space + "ERROR COUNT: " + this.intErrorCount;
		tmp += space + "CRITICAL COUNT: " + this.intCriticalCount;
		tmp += space + "LOWEST AVERAGE READ LATENCY: " + this.lowestAverageReadLatency;
		tmp += space + "LOWEST AVERAGE WRITE LATENCY: " + this.lowestAverageWriteLatency;
		tmp += space + "HIGHEST AVERAGE READ LATENCY: " + this.highestAverageReadLatency;
		tmp += space + "HIGHEST AVERAGE WRITE LATENCY: " + this.highestAverageWriteLatency;
		tmp += space + "LOCAL USER COUNT: " + this.localUsersCount;
		return tmp;
	}

	public long getUsedPhysicalCapacity() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getUserCapacity ####---- " + this.usedPhysicalCapacity );}
		return this.usedPhysicalCapacity;
	}
	public void setUserPhysicalCapacity(long usedCapacity) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setUserCapacity ####----" );}
		this.usedPhysicalCapacity = usedCapacity;
	}
	public long getFreePhysicalCapacity() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getFreeCapacity ####---- " + this.freePhysicalCapacity );}
		return this.freePhysicalCapacity;
	}
	public void setFreePhysicalCapacity(long freeCapacity) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setFreeCapacity ####----" );}
		this.freePhysicalCapacity = freeCapacity;
	}
	public long getUsedVirtualCapacity() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getUserCapacity ####---- " + this.usedPhysicalCapacity );}
		return this.usedVirtualCapacity;
	}
	public void setUserVirtualCapacity(long usedCapacity) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setUserCapacity ####----" );}
		this.usedVirtualCapacity = usedCapacity;
	}
	public long getFreeVirtualCapacity() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getFreeCapacity ####---- " + this.freePhysicalCapacity );}
		return this.freeVirtualCapacity;
	}
	public void setFreeVirtualCapacity(long freeCapacity) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setFreeCapacity ####----" );}
		this.freeVirtualCapacity = freeCapacity;
	}
	public int getNodeCount() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getNodeCount ####---- " + this.nodeCount );}
		return this.nodeCount;
	}
	public void setNodeCount(int nodeCount) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setNodeCount ####----" );}
		this.nodeCount = nodeCount;
	}
	public int getEventCount() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getEventCount ####---- " + this.eventCount );}
		return this.eventCount;
	}
	public void setEventCount(int eventCount) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setEventCount ####----" );}
		this.eventCount = eventCount;
	}
	public int getPoolCount() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getPoolCount ####---- " + this.poolCount );}
		return this.poolCount;
	}
	public void setPoolCount(int poolCount) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setPoolCount ####----" );}
		this.poolCount = poolCount;
	}
	public int getVolumeCount() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getVolumeCount ####---- " + this.volumeCount );}
		return this.volumeCount;
	}
	public void setVolumeCount(int volumeCount) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setVolumeCount ####----" );}
		this.volumeCount = volumeCount;
	}
	public int getVolumeSnapshotCount() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getVolumeSnapshotCount ####---- " + this.snapshotCount );}
		return this.snapshotCount;
	}
	public void setVolumeSnapshotCount(int snapshotCount) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setVolumeSnapshotCount ####----" );}
		this.snapshotCount = snapshotCount;
	}
	public int getHostCount() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getHostCount ####---- " + this.hostCount );}
		return this.hostCount;
	}
	public void setHostCount(int hostCount) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setHostCount ####----" );}
		this.hostCount = hostCount;
	}
	public int getClusterCount() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getClusterCount ####----" );}
		return this.clusterCount;
	}
	public void setClusterCount(int clusterCount) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setClusterCount ####----" );}
		this.clusterCount = clusterCount;
	}
	public int getFileSystemCount() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getFileSystemCount ####---- " + this.filesystemCount );}
		return this.filesystemCount;
	}
	public void setFileSystemCount(int filesystemCount) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setFileSystemCount ####----" );}
		this.filesystemCount = filesystemCount;
	}
	public int getDriveCount() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getDriveCount ####---- " + this.driveCount );}
		return this.driveCount;
	}
	public void setDriveCount(int driveCount) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setDriveCount ####----" );}
		this.driveCount = driveCount;
	}
	public int getCloneCount() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getCloneCount ####---- " + this.cloneCount );}
		return this.cloneCount;
	}
	public void setCloneCount(int cloneCount) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setCloneCount ####----" );}
		this.cloneCount = cloneCount;
	}
	public int getExportCount() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getExportCount ####---- " + this.exportCount );}
		return this.exportCount;
	}
	public void setExportCount(int exportCount) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setExportCount ####----" );}
		this.exportCount = exportCount;
	}
	public long getPhysicalPoolCapacity() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getPhysicalPoolCapacity ####---- " + this.longPoolPhysicalCapacity );}
		return this.longPoolPhysicalCapacity;
	}
	public void setPhysicalPoolCapacity(long capacity) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setPhysicalPoolCapacity ####----" );}
		this.longPoolPhysicalCapacity = capacity;
	}
	public long getVirtualPoolCapacity() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getVirtualPoolCapacity ####---- " + this.longPoolVirtualCapacity );}
		return this.longPoolVirtualCapacity;
	}
	public void setVirtualPoolCapacity(long capacity) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setVirtualPoolCapacity ####----" );}
		this.longPoolVirtualCapacity = capacity;
	}
	public long getPhysicalPoolFree() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getPhysicalPoolFree ####---- " + this.longPoolPhysicalFree );}
		return this.longPoolPhysicalFree;
	}
	public void setPhysicalPoolFree(long capacity) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setPhysicalPoolFree ####----" );}
		this.longPoolPhysicalFree = capacity;
	}
	public long getVirtualPoolFree() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getVirtualPoolFree ####---- " + this.longPoolVirtualFree );}
		return this.longPoolVirtualFree;
	}
	public void setVirtualPoolFree(long capacity) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setVirtualPoolFree ####----" );}
		this.longPoolVirtualFree = capacity;
	}
	public long getUpTime() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getUpTime ####---- " + this.uptime );}
		return this.uptime;
	}
	public void setUpTime(long uptime) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setUpTime ####----" );}
		this.uptime = uptime;
	}
	public long getLocalTime() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getLocalTime ####---- " + this.localtime );}
		return this.localtime;
	}
	public void setLocalTime(long localtime) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setLocalTime ####----" );}
		this.localtime = localtime;
	}
	public String getControllerIP() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getControllerIP ####---- " + this.controllerIP );}
		return this.controllerIP;
	}
	public void setControllerIP(String controllerIP) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setControllerIP ####----" );}
		this.controllerIP = controllerIP;
	}
	public String getControllerNetmask() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getControllerNetmask ####---- " + this.controllerNetmask );}
		return this.controllerNetmask;
	}
	public void setControllerNetmask(String controllerNetmask) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setControllerNetmask ####----" );}
		this.controllerNetmask = controllerNetmask;
	}
	public String getControllerGateway() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getControllerGateway ####---- " + this.controllerGateway );}
		return this.controllerGateway;
	}
	public void setControllerGateway(String controllerGateway) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setControllerGateway ####----" );}
		this.controllerGateway = controllerGateway;
	}
	public String getSystemVersion() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getSystemVersion ####---- " + this.systemVersion );}
		return this.systemVersion;
	}
	public void setSystemVersion(String systemVersion) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setSystemVersion ####----" );}
		this.systemVersion = systemVersion;
	}
	public String getGUIVersion() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getGUIVersion ####---- " + this.guiVersion );}
		return this.guiVersion;
	}
	public void setGUIVersion(String guiVersion) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setGUIVersion ####----" );}
		this.guiVersion = guiVersion;
	}
	public String getShellVersion() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getShellVersion ####---- " + this.shellVersion );}
		return this.shellVersion;
	}
	public void setShellVersion(String shellVersion) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setShellVersion ####----" );}
		this.shellVersion = shellVersion;
	}
	public int getEnclousuresCount() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getEnclousuresCount ####---- " + this.enclousures );}
		return this.enclousures;
	}
	public void setEnclousuresCount(int enclousures) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setEnclousuresCount ####----" );}
		this.enclousures = enclousures;
	}
	public int getRacksCount() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getRacksCount ####---- " + this.racks );}
		return this.racks;
	}
	public void setRacksCount(int racks) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setRacksCount ####----" );}
		this.racks = racks;
	}
	public long getReadLatency() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getReadLatency ####---- " + this.readLatency );}
		return this.readLatency;
	}
	public void setReadLatency(long readLatency) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setReadLatency ####----" );}
		this.readLatency = readLatency;
	}
	public long getWriteLatency() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getWriteLatency ####---- " + this.writeLatency );}
		return this.writeLatency;
	}
	public void setWriteLatency(long writeLatency) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setWriteLatency ####----" );}
		this.writeLatency = writeLatency;
	}
	public long getReadBytes() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getReadBytes ####---- " + this.readBytes );}
		return this.readBytes;
	}
	public void setReadBytes(long readBytes) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setReadBytes ####----" );}
		this.readBytes = readBytes;
	}
	public long getWriteBytes() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getWriteBytes ####---- " + this.writeBytes );}
		return this.writeBytes;
	}
	public void setWriteBytes(long writeBytes) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setWriteBytes ####----" );}
		this.writeBytes = writeBytes;
	}
	public long getReadOps() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getReadOps ####---- " + this.readOps );}
		return this.readOps;
	}
	public void setReadOps(long readOps) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setReadOps ####----" );}
		this.readOps = readOps;
	}
	public long getWriteOps() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getWriteOps ####---- " + this.writeOps );}
		return this.writeOps;
	}
	public void setWriteOps(long writeOps) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setWriteOps ####----" );}
		this.writeOps = writeOps;
	}
	public long getTotalOps() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getTotalOps ####---- " + this.totalOps );}
		return this.totalOps;
	}
	public void setTotalOps(long totalOps) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setTotalOps ####----" );}
		this.totalOps = totalOps;
	}
	public long getTotalBytes() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getTotalBytes ####---- " + this.totalBytes );}
		return this.totalBytes;
	}
	public void setTotalBytes(long totalBytes) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setTotalBytes ####----" );}
		this.totalBytes = totalBytes;
	}
	public long getTotalLatency() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getTotalLatency ####---- " + this.totalLatency );}
		return this.totalLatency;
	}
	public void setTotalLatency(long totalLatency) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setTotalLatency ####----" );}
		this.totalLatency = totalLatency;
	}
	public int getInfoCount() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getInfoCount ####---- " + this.intInfoCount );}
		return this.intInfoCount;
	}
	public void setInfoCount(int intInfoCount) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setInfoCount ####----" );}
		this.intInfoCount = intInfoCount;
	}
	public int getWarningCount() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getWarningCount ####---- " + this.intWarningCount );}
		return this.intWarningCount;
	}
	public void setWarningCount(int intWarningCount) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setWarningCount ####----" );}
		this.intWarningCount = intWarningCount;
	}
	public int getErrorCount() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getErrorCount ####---- " + this.intErrorCount );}
		return this.intErrorCount;
	}
	public void setErrorCount(int intErrorCount) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setErrorCount ####----" );}
		this.intErrorCount = intErrorCount;
	}
	public int getCriticalCount() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getCriticalCount ####---- " + this.intCriticalCount );}
		return this.intCriticalCount;
	}
	public void setCriticalCount(int intCriticalCount) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setCriticalCount ####----" );}
		this.intCriticalCount = intCriticalCount;
	}
	public double getLowestAverageReadLatency() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getLowestAverageReadLatency ####---- " + this.lowestAverageReadLatency );}
		return this.lowestAverageReadLatency;
	}
	public void setLowestAverageReadLatency(double lowestAverageReadLatency) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setLowestAverageReadLatency ####----" );}
		this.lowestAverageReadLatency = lowestAverageReadLatency;
	}	
	public double getLowestAverageWriteLatency() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getLowestAverageWriteLatency ####---- " + this.lowestAverageWriteLatency );}
		return this.lowestAverageWriteLatency;
	}
	public void setLowestAverageWriteLatency(double lowestAverageWriteLatency) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setLowestAverageWriteLatency ####----" );}
		this.lowestAverageWriteLatency = lowestAverageWriteLatency;
	}	
	public double getHighestAverageReadLatency() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getHighestAverageReadLatency ####---- " + this.highestAverageReadLatency );}
		return this.highestAverageReadLatency;
	}
	public void setHighestAverageReadLatency(double highestAverageReadLatency) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setHighestAverageReadLatency ####----" );}
		this.highestAverageReadLatency = highestAverageReadLatency;
	}	
	public double getHighestAverageWriteLatency() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getHighestAverageWriteLatency ####---- " + this.highestAverageWriteLatency );}
		return this.highestAverageWriteLatency;
	}
	public void setHighestAverageWriteLatency(double highestAverageWriteLatency) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setHighestAverageWriteLatency ####----" );}
		this.highestAverageWriteLatency = highestAverageWriteLatency;
	}
	public int getLocalUsersCount() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getLocalUsersCount ####---- " + this.localUsersCount );}
		return this.localUsersCount;
	}
	public void setLocalUsersCount(int localUsersCount) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:setLocalUsersCount ####----" );}
		this.localUsersCount = localUsersCount;
	}

	@Override
	public String getAccountName() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getAccountName ####---- AccountName: " + this.accountName );}
		return this.accountName;
	}
	@Override
	public void setAccountName( String accountName ) {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getAccountName ####---- AccountName: " + accountName );}
		this.accountName = accountName;
	}
	@Override
	public String getInstanceQuery() {
		if(INFINIDATConstants.DEBUG_SUMMARY && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATData:getInstanceQuery ####----" );}
		return "accountName == '" + this.accountName + "'";
	}
}
