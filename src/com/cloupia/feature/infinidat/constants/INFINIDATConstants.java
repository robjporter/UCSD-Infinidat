package com.cloupia.feature.infinidat.constants;

public class INFINIDATConstants {

	public static final String	BUILD												= "0.9.1.7";
	public static final boolean	DEBUG												= true;
	public static final boolean	DEBUG1												= true;
	public static final boolean	DEBUG2												= true;
	public static final boolean	DEBUG3												= true;
	public static final boolean	DEBUG4												= true;
	public static final boolean	DEBUG5												= true;

	// DEBUG CATEGORIES
	public static final boolean	DEBUG_ABOUT											= false;
	public static final boolean	DEBUG_CLUSTERS										= false;
	public static final boolean	DEBUG_CORE											= false;
	public static final boolean	DEBUG_DRIVES										= false;
	public static final boolean	DEBUG_ENETWORK										= false;
	public static final boolean	DEBUG_ERRORS										= true;
	public static final boolean	DEBUG_EXPORTS										= true;
	public static final boolean	DEBUG_FCNETWORK										= false;
	public static final boolean	DEBUG_FILESYSTEM									= false;
	public static final boolean	DEBUG_GRAPHS										= true;
	public static final boolean	DEBUG_HOSTS											= false;
	public static final boolean	DEBUG_IBNETWORK										= false;
	public static final boolean	DEBUG_LOGS											= false;
	public static final boolean	DEBUG_NODES											= false;
	public static final boolean	DEBUG_POOLS											= false;
	public static final boolean	DEBUG_SERVICES										= false;
	public static final boolean	DEBUG_SETTINGS										= false;
	public static final boolean	DEBUG_SNAPSHOTS										= false;
	public static final boolean	DEBUG_SUMMARY										= false;
	public static final boolean	DEBUG_TASKS											= true;
	public static final boolean DEBUG_USERS											= true;
	public static final boolean	DEBUG_VOLUMES										= false;

	// CATEGORY POSITIONS - ERROR
	public static final int		POS_ERROR_STATUS									= 0;
	public static final int		POS_ERROR_MESSAGE									= 1;
	public static final int		POS_ERROR_DESCRIPTION								= 2;
	public static final int		POS_ERROR_SEVERITY									= 3;
	public static final int		POS_ERROR_DATA										= 4;
	public static final int		POS_ERROR_REMOTE									= 5;
	public static final int		POS_ERROR_CODE										= 6;

	// CATEGORY POSITIONS - POOLS
	public static final int		POS_POOL_STATUS										= 0;
	public static final int		POS_POOL_ID											= 1;
	public static final int		POS_POOL_NAME										= 2;
	public static final int		POS_POOL_VOLUME_COUNT								= 3;
	public static final int		POS_POOL_MAX_EXTEND									= 4;
	public static final int		POS_POOL_ALLOCATED									= 5;
	public static final int		POS_POOL_RESERVED_CAPACITY							= 6;
	public static final int		POS_POOL_FS_COUNT									= 7;
	public static final int		POS_POOL_FS_CLONE_COUNT								= 8;
	public static final int		POS_POOL_FS_SNAP_COUNT								= 9;
	public static final int		POS_POOL_SSD										= 10;
	public static final int		POS_POOL_CLONE_COUNT								= 11;
	public static final int		POS_POOL_SNAP_COUNT									= 12;
	public static final int		POS_POOL_STATE										= 13;
	public static final int		POS_POOL_FREE_PHYSICAL								= 14;
	public static final int		POS_POOL_FREE_VIRTUAL								= 15;
	public static final int		POS_POOL_PHYSICAL									= 16;
	public static final int		POS_POOL_VIRTUAL									= 17;
	public static final int		POS_POOL_WARNING									= 18;
	public static final int		POS_POOL_CRITICAL									= 19;
	public static final int		POS_POOL_CODE										= 20;

	// CATEGORY POSITIONS - VOLUMES
	public static final int		POS_VOLUME_STATUS									= 0;
	public static final int		POS_VOLUME_ID										= 1;
	public static final int		POS_VOLUME_NAME										= 2;
	public static final int		POS_VOLUME_POOLID									= 3;
	public static final int		POS_VOLUME_SERIAL									= 4;
	public static final int		POS_VOLUME_BLOCKS									= 5;
	public static final int		POS_VOLUME_ALLOCATED								= 6;
	public static final int		POS_VOLUME_SIZE										= 7;
	public static final int		POS_VOLUME_PARENTID									= 8;
	public static final int		POS_VOLUME_SSD										= 9;
	public static final int		POS_VOLUME_TYPE										= 10;
	public static final int		POS_VOLUME_DATASET									= 11;
	public static final int		POS_VOLUME_DATA										= 12;
	public static final int		POS_VOLUME_PROVTYPE									= 13;
	public static final int		POS_VOLUME_PROTECTED								= 14;
	public static final int		POS_VOLUME_MAPPED									= 15;
	public static final int		POS_VOLUME_USED										= 16;
	public static final int		POS_VOLUME_CODE										= 17;
	
	// CATEGORY POSITIONS - EXPORTS
	public static final int		POS_EXPORT_STATUS									= 0;
	public static final int		POS_EXPORT_ID										= 1;
	public static final int		POS_EXPORT_FILEID									= 2;
	public static final int		POS_EXPORT_INNERPATH								= 3;
	public static final int		POS_EXPORT_PERFWRITE								= 4;
	public static final int		POS_EXPORT_BITFILE									= 5;
	public static final int		POS_EXPORT_PERFREAD									= 6;
	public static final int		POS_EXPORT_MAXREAD									= 7;
	public static final int		POS_EXPORT_PERFREADDIR								= 8;
	public static final int		POS_EXPORT_ENABLED									= 9;
	public static final int		POS_EXPORT_ALLUSERS									= 10;
	public static final int		POS_EXPORT_TRANSPORT								= 11;
	public static final int		POS_EXPORT_ANONYMOUSUID								= 12;
	public static final int		POS_EXPORT_ANONYMOUSGID								= 13;
	public static final int		POS_EXPORT_MAXWRITE									= 14;
	public static final int		POS_EXPORT_PORT										= 15;
	public static final int		POS_EXPORT_PATH										= 16;
	public static final int		POS_EXPORT_CREATED									= 17;
	public static final int		POS_EXPORT_UPDATED									= 18;
	public static final int		POS_EXPORT_CODE										= 19;
	
	// CATEGORY POSITIONS - EDIT VOLUME
	public static final int		POS_EDIT_VOLUME_NAME 								= 0;
	public static final int		POS_EDIT_VOLUME_POOLID 								= 1;
	public static final int		POS_EDIT_VOLUME_PROVTYPE 							= 2;
	public static final int		POS_EDIT_VOLUME_SIZE 								= 3;
	public static final int		POS_EDIT_VOLUME_SSD 								= 4;

	// INFINIDAT DEFAULTS
	public static final int		INFINIDAT_HTTP_REQUEST_MAX_RESULTS					= 1000;

	// TASK OUTPUT ERRORS
	public static final String	TASK_OUTPUT_ERROR_STATUS							= "infinidat_task_error_status";
	public static final String	TASK_OUTPUT_ERROR_MESSAGE							= "infinidat_task_error_message";
	public static final String	TASK_OUTPUT_ERROR_DESCRIPTION						= "infinidat_task_error_description";
	public static final String	TASK_OUTPUT_ERROR_SEVERITY							= "infinidat_task_error_severity";
	public static final String	TASK_OUTPUT_ERROR_DATA								= "infinidat_task_error_data";
	public static final String	TASK_OUTPUT_ERROR_REMOTE							= "infinidat_task_error_remote";
	public static final String	TASK_OUTPUT_ERROR_CODE								= "infinidat_task_http_code";

	// REPORT DATA
	public static final String	REPORT_DATA_SEPARATOR								= ";";
	public static final String	REPORT_DATA_STATIC_SEPARATOR						= ";";

	public static final String	WWPN_TEMPLATE										= "00:00:00:00:00:00:00:00";
	public static final String	INFINIDAT_HELLO_WORLD_NAME							= "infinidat_name_from_other_tasks";

	// HTTP SIMPLE GET PARAMETERS
	public static final String	HTTP_USER_AGENT										= "Mozilla/5.0";
	public static final int		HTTP_CONNECTION_TIMEOUT								= 200;
	public static final String	HTTP_PROXY_SERVER_URL								= "proxy.esl.cisco.com";
	public static final int		HTTP_PROXY_SERVER_PORT								= 80;

	// ACTION DEFAULTS
	public static final String	DEFAULT_PROVISION_TYPE								= "THIN";
	public static final String	DEFAULT_DATE_TIME_FORMAT							= " dd/MM/yyyy hh:mm:ss";
	public static final int		DEFAULT_INVENTORY_COLLECTION_TIMEOUT				= 15;

	// EVENTS
	public static final int		EVENTS_NO_EVENTS_TO_FETCH							= 200;

	public static final int		INFINIDAT_ACCOUNT_TYPE								= 6080;
	public static final String	INFINIDAT_VENDOR_NAME								= "INFINIDAT";
	public static final String	INFINIDAT_VENDOR_IMAGE								= "infinidat_logo.png";
	public static final String	INFINIDAT_DEFAULT_MODEL								= "INFINIDAT";
	public static final String	INFINIDAT_DEFAULT_VERSION							= "2.2.2.1";
	public static final int		INFINIDAT_MAX_VOLUME_SIZE							= 100;
	public static final String	INFINIDAT_INVENTORY_COLLECTOR_NAME					= "INFINIDAT_Inventory_Collector";
	public static final int		INFINIDAT_MAX_LUN_SIZE								= 8192;
	public static final String	INFINIDAT_NEW_ITEM_COMMENT							= "Created from Cisco UCS Director";
	public static final String	INFINIDAT_WORKFLOW_CATEGORY							= "INFINIDAT";
	public static final String	INFINIDAT_POD_TYPE									= "INFINI-POD";
	public static final int		INFINIDAT_MIN_POOL_SIZE								= 1000;
	public static final int		INFINIDAT_MAX_PHYSICAL_POOL_SIZE					= 1000000;
	public static final int		INFINIDAT_MAX_VIRTUAL_POOL_SIZE						= 1000000;
	public static final int		INFINIDAT_POOL_MAX_EXTEND							= -1;

	// GRAPHS
	public static final String	REPORT_GRAPH_BAR_COUNTS_CATEGORY					= "Elements";
	public static final String	REPORT_GRAPH_BAR_COUNTS_AXIS_NAME					= "Element Count";

	// REPORTS
	public static final String	REPORT_DISK_PHYSICAL_USAGE_NAME						= "infinidat.pie.report.disk.physical.usage";
	public static final String	REPORT_DISK_PHYSICAL_USAGE_LABEL					= "Physical Utilisation Chart";
	public static final String	REPORT_DISK_VIRTUAL_USAGE_NAME						= "infinidat.pie.report.disk.virtual.usage";
	public static final String	REPORT_DISK_VIRTUAL_USAGE_LABEL						= "Virtual Utilisation Chart";
	public static final String	REPORT_GRAPH_PIE_VIRTUAL_USAGE_NAME					= "infinidat.pie.report.pool.virtual.usage";
	public static final String	REPORT_GRAPH_PIE_VIRTUAL_USAGE_LABEL				= "Pool Virtual Utilisation";
	public static final String	REPORT_GRAPH_PIE_PHYSICAL_USAGE_NAME				= "infinidat.pie.report.pool.physical.usage";
	public static final String	REPORT_GRAPH_PIE_PHYSICAL_USAGE_LABEL				= "Pool Physical Utilisation";

	// TASKS - CREATE
	public static final String	TASK_NAME_NEW_CLUSTER								= "Infinidat Create Cluster";
	public static final String	TASK_NAME_NEW_VOLUME								= "Infinidat Create Volume";
	public static final String	TASK_NAME_NEW_HOST									= "Infinidat Create Host";
	public static final String	TASK_NAME_CREATE_POOL								= "Infinidat Create Pool";

	// TASKS - EDIT
	public static final String	TASK_NAME_EDIT_POOL									= "Infinidat Edit Pool";
	public static final String	TASK_NAME_EDIT_HOST									= "Infinidat Edit Host";
	public static final String	TASK_NAME_EDIT_VOLUME								= "Infinidat Edit Volume";
	public static final String	TASK_NAME_EDIT_CLUSTER								= "Infinidat Edit Cluster";

	// TASKS - DELETE
	public static final String	TASK_NAME_DELETE_NAME_VOLUME						= "Infinidat Delete Volume";
	public static final String	TASK_NAME_DELETE_NAME_POOL							= "Infinidat Delete Pool By Name";
	public static final String	TASK_NAME_DELETE_ID_POOL							= "Infinidat Delete Pool By ID";
	public static final String	TASK_NAME_DELETE_HOST								= "Infinidat Delete Host";
	public static final String	TASK_NAME_DELETE_CLUSTER							= "Infinidat Delete Cluster";
	public static final String	TASK_NAME_DELETE_ID_VOLUME							= "Infinidat Delete ID Volume";

	// TASKS - ADD
	public static final String	TASK_NAME_ADD_HOST_CLUSTER							= "Infinidat Add Host to Cluster";
	public static final String	TASK_NAME_ADD_VOLUME_CLUSTER						= "Infinidat Add Volume to Cluster";

	// TASKS - REMOVE
	public static final String	TASK_NAME_DELETE_HOST_CLUSTER						= "Infinidat Remove Host from Cluster";
	public static final String	TASK_NAME_DELETE_VOLUME_CLUSTER						= "Infinidat Remove Volume from Cluster";

	// TASKS - OTHER
	public static final String	TASK_NAME_DISCONNECT_VOLUME							= "Infinidat Disconnect Volume";
	public static final String	TASK_NAME_CONNECT_VOLUME							= "Infinidat Connect Volume";
	public static final String	TASK_NAME_RESIZE_VOLUME								= "Infinidat Resize Volume";
	public static final String	TASK_NAME_SNAPSHOT_VOLUME							= "Infinidat Snapshot Volume";
	public static final String	TASK_NAME_CLONE_SNAPSHOT							= "Infinidat Clone Snapshot";

	// TASKS - OUTPUT NAMES
	public static final String	TASK_OUTPUT_DELETE_VOLUME_NAME						= "Delete Volume Output";
	public static final String	TASK_OUTPUT_DELETE_VOLUME_TYPE						= "status-as-boolean";
	public static final String	TASK_OUTPUT_NEW_VOLUME_NAME							= "Create New Volume";
	public static final String	TASK_OUTPUT_NEW_VOLUME_TYPE							= "status-as-boolean";

	// TASK - VOLUME OUTPUTS
	public static final String	TASK_OUTPUT_VOLUME_ID								= "infinidat_task_output_volume_id";
	public static final String	TASK_OUTPUT_VOLUME_NAME								= "infinidat_task_output_volume_name";
	public static final String	TASK_OUTPUT_VOLUME_TYPE								= "infinidat_task_output_volume_type";
	public static final String	TASK_OUTPUT_VOLUME_SIZE								= "infinidat_task_output_volume_size";
	public static final String	TASK_OUTPUT_VOLUME_SERIAL							= "infinidat_task_output_volume_serial";
	public static final String	TASK_OUTPUT_VOLUME_PROVTYPE							= "infinidat_task_output_volume_provtype";
	public static final String	TASK_OUTPUT_VOLUME_USED								= "infinidat_task_output_volume_used";
	public static final String	INFINIDAT_VOLUME_LIST_TABLE_NAME					= "infinidatVolumeList";

	// TASK - POOL OUTPUTS
	public static final String	TASK_OUTPUT_POOL_ID									= "infinidat_task_output_pool_id";
	public static final String	TASK_OUTPUT_POOL_NAME								= "infinidat_task_output_pool_name";
	public static final String	TASK_OUTPUT_POOL_STATE								= "infinidat_task_output_pool_physical_size";
	public static final String	TASK_OUTPUT_POOL_SIZE								= "infinidat_task_output_pool_virtual_size";

	// LOV
	public static final String	INFINIDAT_ACCOUNT_LOV_NAME							= "InfinidatAccount";
	public static final String	INFINIDAT_VOLUME_SIZE_UNIT_LOV_NAME					= "infinidatVolumeSizeUnit";
	public static final String	INFINIDAT_VOLUME_PROVISION_TYPE_LOV_NAME			= "infinidatVolumeProvisionType";
	public static final String	INFINIDAT_VOLUME_INVENTORY_PROVISION_TYPE_LOV_NAME	= "infinidatVolumeInventoryProvisionType";
	public static final String	INFINIDAT_POOL_PROVISION_TYPE_LOV_NAME				= "infinidatPoolProvisionType";

	public static final int		INFINIDAT_HTTP_INT_CODE_OK							= 201;
	public static final String	INFINIDAT_HTTP_STR_CODE_OK							= "201";
	public static final int		INFINIDAT_HTTP_INT_CODE_SUCCESS						= 204;
	public static final String	INFINIDAT_HTTP_STR_CODE_SUCCESS						= "204";
	public static final int		INFINIDAT_HTTP_INT_CODE_NOTAUTH						= 401;
	public static final String	INFINIDAT_HTTP_STR_CODE_NOTAUTH						= "401";
	public static final int		INFINIDAT_HTTP_INT_CODE_NOTFOUND					= 404;
	public static final String	INFINIDAT_HTTP_STR_CODE_NOTFOUND					= "404";
	public static final int		INFINIDAT_HTTP_INT_CODE_CONFLICT					= 409;
	public static final String	INFINIDAT_HTTP_STR_CODE_CONFLICT					= "409";

	// some dummy strings used to represent inventory items
	public static final String	CONTROLLERS											= "controllers";
	public static final String	CAPABILITIES										= "capabilities";
	public static final String	LUNS												= "luns";

	public static final String	INFINIDAT_CONTEXT_ONE_NAME							= "infinidat.dummy.context.one";
	public static final String	INFINIDAT_CONTEXT_ONE_LABEL							= "INFINIDAT Context One";

	public static final String	INFRA_ACCOUNT_LABEL									= "INFINIDAT Account";
	public static final String	INFRA_ACCOUNT_TYPE									= "INFINIDAT Account";

}
