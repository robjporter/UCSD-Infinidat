package com.cloupia.feature.infinidat.reports.device;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.reports.charts.bar.INFINIDATBarCountsReport;
import com.cloupia.feature.infinidat.reports.charts.bar.INFINIDATBarEventErrorsReport;
import com.cloupia.feature.infinidat.reports.charts.bar.INFINIDATBarReadWriteLatencyReport;
import com.cloupia.feature.infinidat.reports.charts.pie.INFINIDATReadWriteBytesReport;
import com.cloupia.feature.infinidat.reports.charts.pie.INFINIDATPieReadWriteLatencyReport;
import com.cloupia.feature.infinidat.reports.charts.pie.INFINIDATPieReadWriteOpsReport;
import com.cloupia.feature.infinidat.reports.charts.pie.INFINIDATPieReportDiskPhysicalUsage;
import com.cloupia.feature.infinidat.reports.charts.pie.INFINIDATPieReportDiskVirtualUsage;
import com.cloupia.feature.infinidat.reports.charts.pie.INFINIDATPieReportPoolPhysicalUsage;
import com.cloupia.feature.infinidat.reports.charts.pie.INFINIDATPieReportPoolVirtualUsage;
import com.cloupia.feature.infinidat.reports.device.about.INFINIDATDeviceAboutReport;
import com.cloupia.feature.infinidat.reports.device.clusters.INFINIDATDeviceClusterReport;
import com.cloupia.feature.infinidat.reports.device.drives.INFINIDATDeviceDriveReport;
import com.cloupia.feature.infinidat.reports.device.enetwork.INFINIDATDeviceEInterfaceReport;
import com.cloupia.feature.infinidat.reports.device.fcnetwork.INFINIDATDeviceFCInterfaceReport;
import com.cloupia.feature.infinidat.reports.device.filesystems.INFINIDATDeviceFileSystemsReport;
import com.cloupia.feature.infinidat.reports.device.filesystems.clones.INFINIDATDeviceFileSystemsClonesReport;
import com.cloupia.feature.infinidat.reports.device.filesystems.exports.INFINIDATDeviceFileSystemsExportsReport;
import com.cloupia.feature.infinidat.reports.device.filesystems.snapshots.INFINIDATDeviceFileSystemsSnapshotsReport;
import com.cloupia.feature.infinidat.reports.device.hosts.INFINIDATDeviceHostReport;
import com.cloupia.feature.infinidat.reports.device.ibnetwork.INFINIDATDeviceIBInterfaceReport;
import com.cloupia.feature.infinidat.reports.device.logs.INFINIDATDeviceEventLogReport;
import com.cloupia.feature.infinidat.reports.device.pools.INFINIDATDevicePoolsReport;
import com.cloupia.feature.infinidat.reports.device.services.INFINIDATDeviceServicesReport;
import com.cloupia.feature.infinidat.reports.device.snapshots.INFINIDATDeviceVolumeSnapshotReport;
import com.cloupia.feature.infinidat.reports.device.summary.INFINIDATDeviceSummaryReport;
import com.cloupia.feature.infinidat.reports.device.volume.INFINIDATDeviceVolumeReport;
import com.cloupia.feature.infinidat.reports.device.volume.clones.INFINIDATDeviceVolumeClonesReport;
import com.cloupia.model.cIM.InfraAccountTypes;
import com.cloupia.service.cIM.inframgr.collector.impl.GenericInfraAccountReport;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReport;

@SuppressWarnings("unused")
public class INFINIDATDeviceMgmtReport extends GenericInfraAccountReport {
	private static Logger logger = Logger.getLogger( INFINIDATDeviceMgmtReport.class );
	private static String NAME = "INFINIDATAccount";
	
	private CloupiaReport[] ddReports = new CloupiaReport[] {
			new INFINIDATDeviceSummaryReport(),
			new INFINIDATPieReportDiskPhysicalUsage(),
			new INFINIDATPieReportDiskVirtualUsage(),
			new INFINIDATPieReportPoolPhysicalUsage(),
			new INFINIDATPieReportPoolVirtualUsage(),
			new INFINIDATReadWriteBytesReport(),
			//new INFINIDATPieReadWriteLatencyReport(),
			new INFINIDATBarReadWriteLatencyReport(),
			new INFINIDATPieReadWriteOpsReport(),
			new INFINIDATBarCountsReport(),
			new INFINIDATBarEventErrorsReport(),
			//new INFINIDATDeviceNodesReport(),
			new INFINIDATDeviceEInterfaceReport(),
			new INFINIDATDeviceFCInterfaceReport(),
			new INFINIDATDeviceIBInterfaceReport(),
			new INFINIDATDeviceDriveReport(),
			new INFINIDATDevicePoolsReport(),
			new INFINIDATDeviceClusterReport(),
			new INFINIDATDeviceVolumeReport(),
			new INFINIDATDeviceVolumeSnapshotReport(),
			new INFINIDATDeviceVolumeClonesReport(),
			new INFINIDATDeviceFileSystemsReport(),
			new INFINIDATDeviceFileSystemsSnapshotsReport(),
			new INFINIDATDeviceFileSystemsClonesReport(),
			new INFINIDATDeviceFileSystemsExportsReport(),
			new INFINIDATDeviceHostReport(),
			new INFINIDATDeviceServicesReport(),
			new INFINIDATDeviceEventLogReport(),
			new INFINIDATDeviceAboutReport()
	};
	
	public INFINIDATDeviceMgmtReport() {
		super( NAME, INFINIDATConstants.INFINIDAT_ACCOUNT_TYPE, InfraAccountTypes.CAT_STORAGE );
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceMgmtReport:INFINIDATDeviceMgmtReport ####----" );}
	}
	@Override
	public CloupiaReport[] getDrilldownReports() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATDeviceMgmtReport:getDrilldownReports ####----" );}
		return this.ddReports;
	}
}
