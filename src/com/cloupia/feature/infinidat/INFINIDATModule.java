package com.cloupia.feature.infinidat;

import org.apache.log4j.Logger;

import com.cloupia.feature.infinidat.accounts.INFINIDATAccount;
import com.cloupia.feature.infinidat.accounts.handler.INFINIDATTestConnectionHandler;
import com.cloupia.feature.infinidat.accounts.inventory.INFINIDATConvergedStackBuilder;
import com.cloupia.feature.infinidat.accounts.inventory.INFINIDATInventoryItemHandler;
import com.cloupia.feature.infinidat.accounts.inventory.INFINIDATInventoryListener;
import com.cloupia.feature.infinidat.constants.INFINIDATConstants;
import com.cloupia.feature.infinidat.inventory.INFINIDATCollectorFactory;
import com.cloupia.feature.infinidat.reports.device.INFINIDATDeviceMgmtReport;
import com.cloupia.feature.infinidat.tasks.INFINIDATaddPortToHostTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATaddHostToClusterTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATaddVolumeToClusterTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATcloneSnapshotTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATconnectVolumeHostTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATcreateClusterTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATcreateHostTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATcreatePoolTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATcreateVolumeTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATdeleteClusterTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATdeleteHostFromClusterTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATdeleteHostTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATdeletePoolByIDTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATdeletePoolByNameTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATdeleteVolumeFromClusterTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATdeleteVolumeByIDTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATdeleteVolumeByNameTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATdisconnectVolumeHostTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATeditClusterTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATeditHostTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATeditPoolTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATeditVolumeTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATresizeVolumeTask;
import com.cloupia.feature.infinidat.tasks.INFINIDATsnapshotVolumeTask;
import com.cloupia.feature.infinidat.lovs.INFINIDATAccountsNameProvider;
import com.cloupia.feature.infinidat.lovs.INFINIDATClusterHostsConnectedLOV;
import com.cloupia.feature.infinidat.lovs.INFINIDATClusterLunsLOV;
import com.cloupia.feature.infinidat.lovs.INFINIDATClusteredLunsLOV;
import com.cloupia.feature.infinidat.lovs.INFINIDATHostClonesLOV;
import com.cloupia.feature.infinidat.lovs.INFINIDATHostLuns;
import com.cloupia.feature.infinidat.lovs.INFINIDATHostMappedLuns;
import com.cloupia.feature.infinidat.lovs.INFINIDATHostPorts;
import com.cloupia.feature.infinidat.lovs.INFINIDATHostSnapshotsLOV;
import com.cloupia.feature.infinidat.lovs.INFINIDATHostsConnectedLOV;
import com.cloupia.feature.infinidat.lovs.INFINIDATHostsNotConnectedLOV;
import com.cloupia.feature.infinidat.lovs.INFINIDATPoolProvisionTypeProvider;
import com.cloupia.feature.infinidat.lovs.INFINIDATVolumeInventoryProvisionTypeProvider;
import com.cloupia.feature.infinidat.lovs.INFINIDATVolumeProvisionTypeProvider;
import com.cloupia.feature.infinidat.lovs.SimpleTabularProvider;
import com.cloupia.feature.infinidat.lovs.INFINIDATTimeClockProvider;
import com.cloupia.feature.infinidat.lovs.INFINDATVolumeSizeUnitProvider;
import com.cloupia.lib.connector.ConfigItemDef;
import com.cloupia.lib.connector.account.AccountTypeEntry;
import com.cloupia.lib.connector.account.PhysicalAccountTypeManager;
import com.cloupia.model.cIM.InfraAccountTypes;
import com.cloupia.model.cIM.ReportContextRegistry;
import com.cloupia.service.cIM.inframgr.AbstractCloupiaModule;
import com.cloupia.service.cIM.inframgr.AbstractTask;
import com.cloupia.service.cIM.inframgr.CustomFeatureRegistry;
import com.cloupia.service.cIM.inframgr.collector.controller.CollectorFactory;
import com.cloupia.service.cIM.inframgr.reports.simplified.CloupiaReport;

@SuppressWarnings("unused")
public class INFINIDATModule extends AbstractCloupiaModule {
	private static Logger logger = Logger.getLogger( INFINIDATModule.class );

	@Override
	public AbstractTask[] getTasks() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATModule:getTasks ####----" );}
		
		AbstractTask task1 = new INFINIDATaddHostToClusterTask();
		AbstractTask task2 = new INFINIDATaddPortToHostTask();
		AbstractTask task3 = new INFINIDATaddVolumeToClusterTask();
		AbstractTask task4 = new INFINIDATcloneSnapshotTask();
		AbstractTask task5 = new INFINIDATconnectVolumeHostTask();
		AbstractTask task6 = new INFINIDATcreateClusterTask();
		AbstractTask task7 = new INFINIDATcreateHostTask();
		AbstractTask task8 = new INFINIDATcreatePoolTask();
		AbstractTask task9 = new INFINIDATcreateVolumeTask();
		AbstractTask task10 = new INFINIDATdeleteClusterTask();
		AbstractTask task11 = new INFINIDATdeleteHostFromClusterTask();
		AbstractTask task12 = new INFINIDATdeleteHostTask();
		AbstractTask task13 = new INFINIDATdeletePoolByIDTask();
		AbstractTask task14 = new INFINIDATdeletePoolByNameTask();
		AbstractTask task15 = new INFINIDATdeleteVolumeFromClusterTask();
		AbstractTask task16 = new INFINIDATdeleteVolumeByIDTask();
		AbstractTask task17 = new INFINIDATdeleteVolumeByNameTask();
		AbstractTask task18 = new INFINIDATdisconnectVolumeHostTask();
		AbstractTask task19 = new INFINIDATeditClusterTask();
		AbstractTask task20 = new INFINIDATeditHostTask();
		AbstractTask task21 = new INFINIDATeditPoolTask();
		AbstractTask task22 = new INFINIDATeditVolumeTask();
		AbstractTask task23 = new INFINIDATresizeVolumeTask();
		AbstractTask task24 = new INFINIDATsnapshotVolumeTask();
		
		AbstractTask[] tasks = new AbstractTask[24];
		tasks[0] = task1;
		tasks[1] = task2;
		tasks[2] = task3;
		tasks[3] = task4;
		tasks[4] = task5;
		tasks[5] = task6;
		tasks[6] = task7;
		tasks[7] = task8;
		tasks[8] = task9;
		tasks[9] = task10;
		tasks[10] = task11;
		tasks[11] = task12;
		tasks[12] = task13;
		tasks[13] = task14;
		tasks[14] = task15;
		tasks[15] = task16;
		tasks[16] = task17;
		tasks[17] = task18;
		tasks[18] = task19;
		tasks[19] = task20;
		tasks[20] = task21;
		tasks[21] = task22;
		tasks[22] = task23;
		tasks[23] = task24;
		
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATModule:getTasks ####---- TASKS ASSIGNED" );}
		
		return tasks;
	}
	
	@Override
	public CollectorFactory[] getCollectors() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATModule:getCollectors ####----" );}
		INFINIDATCollectorFactory collector = new INFINIDATCollectorFactory( INFINIDATConstants.INFINIDAT_ACCOUNT_TYPE );
		CollectorFactory[] collectors = new CollectorFactory[ 1 ];
		collectors[ 0 ] = collector;
		return collectors;
	}

	@Override
	public CloupiaReport[] getReports() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATModule:getReports ####----" );}
		INFINIDATDeviceMgmtReport INFINIDATDeviceReport = new INFINIDATDeviceMgmtReport();
		CloupiaReport[] reports = new CloupiaReport[ 1 ];
		reports[ 0 ] = INFINIDATDeviceReport;
		//reports[ 1 ] = new INFINIDATDeviceSummaryReport();
		return reports;
	}

	@Override
	public void onStart(CustomFeatureRegistry cfr) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATModule:onStart ####----" );}
		try {
			cfr.registerLovProviders( INFINIDATAccountsNameProvider.NAME, new INFINIDATAccountsNameProvider());
			cfr.registerLovProviders( INFINIDATVolumeProvisionTypeProvider.NAME, new INFINIDATVolumeProvisionTypeProvider());
			cfr.registerLovProviders( INFINIDATVolumeInventoryProvisionTypeProvider.NAME, new INFINIDATVolumeInventoryProvisionTypeProvider());
			cfr.registerLovProviders( INFINIDATPoolProvisionTypeProvider.NAME, new INFINIDATPoolProvisionTypeProvider());
			cfr.registerLovProviders( INFINDATVolumeSizeUnitProvider.NAME, new INFINDATVolumeSizeUnitProvider());
			cfr.registerLovProviders( INFINIDATTimeClockProvider.NAME, new INFINIDATTimeClockProvider());
			cfr.registerTabularField( INFINIDATHostClonesLOV.HOSTS_TABULAR_PROVIDER, INFINIDATHostClonesLOV.class, "0", "0" );
			cfr.registerTabularField( INFINIDATHostSnapshotsLOV.HOSTS_TABULAR_PROVIDER, INFINIDATHostSnapshotsLOV.class, "0", "0" );
			cfr.registerTabularField( INFINIDATClusteredLunsLOV.HOSTS_TABULAR_PROVIDER, INFINIDATClusteredLunsLOV.class, "0", "0" );
			cfr.registerTabularField( INFINIDATClusterLunsLOV.HOSTS_TABULAR_PROVIDER, INFINIDATClusterLunsLOV.class, "0", "0" );
			cfr.registerTabularField( INFINIDATClusterHostsConnectedLOV.HOSTS_TABULAR_PROVIDER, INFINIDATClusterHostsConnectedLOV.class, "0", "0" );
			cfr.registerTabularField( INFINIDATHostsConnectedLOV.HOSTS_TABULAR_PROVIDER, INFINIDATHostsConnectedLOV.class, "0", "0" );
			cfr.registerTabularField( INFINIDATHostsNotConnectedLOV.HOSTS_TABULAR_PROVIDER, INFINIDATHostsNotConnectedLOV.class, "0", "0" );
			cfr.registerTabularField( INFINIDATHostPorts.HOST_PORT_TABULAR_PROVIDER, INFINIDATHostPorts.class, "0", "0" );
			cfr.registerTabularField( INFINIDATHostLuns.HOST_LUNS_TABULAR_PROVIDER, INFINIDATHostLuns.class, "0", "0" );
			cfr.registerTabularField( INFINIDATHostMappedLuns.HOST_MAPPED_LUNS_TABULAR_PROVIDER, INFINIDATHostMappedLuns.class, "0", "0" );
			cfr.registerTabularField( SimpleTabularProvider.SIMPLE_TABULAR_PROVIDER, SimpleTabularProvider.class, "0", "0" );
			ReportContextRegistry.getInstance().register( INFINIDATConstants.INFRA_ACCOUNT_TYPE, INFINIDATConstants.INFRA_ACCOUNT_LABEL );
			//MonitoringTrigger monTrigger = new MonitoringTrigger( new MonitorDummyDeviceType(), new MonitorDummyDeviceStatusParam());
			//MonitoringTriggerUtil.register( monTrigger );
			createAccountType();
		} catch (Exception e) {
			if(INFINIDATConstants.DEBUG_ERRORS){logger.error( "----#### INFINIDATModule:onStart::ERROR ####----", e );}
		}
	}

	private void createAccountType() {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATModule:createAccountType ####----" );}
		AccountTypeEntry entry = new AccountTypeEntry();
		entry.setCredentialClass( INFINIDATAccount.class );
		entry.setAccountType( INFINIDATConstants.INFRA_ACCOUNT_TYPE );
		entry.setAccountLabel( INFINIDATConstants.INFRA_ACCOUNT_LABEL );
		entry.setCategory( InfraAccountTypes.CAT_STORAGE );
		entry.setContextType( ReportContextRegistry.getInstance().getContextByName( INFINIDATConstants.INFRA_ACCOUNT_TYPE ).getType());
		entry.setAccountClass( AccountTypeEntry.PHYSICAL_ACCOUNT );
		entry.setInventoryTaskPrefix( "INFINIDAT Module Inventory Task" );
		entry.setWorkflowTaskCategory(INFINIDATConstants.INFINIDAT_WORKFLOW_CATEGORY);
		entry.setInventoryFrequencyInMins( 15 );
		entry.setPodTypes( new String[] { INFINIDATConstants.INFINIDAT_POD_TYPE, "GenericPod" });
		entry.setTestConnectionHandler( new INFINIDATTestConnectionHandler() );
		entry.setInventoryListener( new INFINIDATInventoryListener() );
		entry.setConvergedStackComponentBuilder( new INFINIDATConvergedStackBuilder() );
		try {
			registerInventoryObjects( entry );
			PhysicalAccountTypeManager.getInstance().addNewAccountType(entry);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void registerInventoryObjects( AccountTypeEntry INFINIDATRecoverPointAccountEntry ) {
		if(INFINIDATConstants.DEBUG_CORE && INFINIDATConstants.DEBUG){logger.info( "----#### INFINIDATModule:registerInventoryObjects ####----" );}
		ConfigItemDef INFINIDATRecoverPointStateInfo = INFINIDATRecoverPointAccountEntry.createInventoryRoot( "infinidat.inventory.root", INFINIDATInventoryItemHandler.class );
	}
}
