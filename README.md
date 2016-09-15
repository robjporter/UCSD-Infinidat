# UCS Director support for Infinidat #

This work was created to integrate Infinidat arrays into UCSD director.  In this particular case the plan for the integration to include;<br/>
1. A new pod type<br/>
2. Infinidat array statistics<br/>
3. Infinidat array management<br/>
4. UCS Director tasks to automate Infinidat arrays<br/>
5. Reports related to Infinidat arrays<br/>

## 1. A new pod type ##
I created a new pod type, although not an official name, I have called it an "INFINI-POD".  This will allow, a hypervisor, UCS, Nexus and an Infinidat array to be added to form the "INFINI-POD".  The Infinidat array can also be added to a Generic pod, if you are building your own mixed environment or wish to add to a current pod.

![alt text](https://github.com/robjporter/UCSD-Infinidat/blob/master/site/screenshot2.png "Cisco UCS Director Infinidat Integration 1")

## 2. Infinidat array statistics ##
As of now, a lot of data is being collected from the array and from research and customer conversations these are the stats that seem to be the most relevant to show.

![alt text](https://github.com/robjporter/UCSD-Infinidat/blob/master/site/screenshot4.png "Cisco UCS Director Infinidat Integration 4")

## 3. Infinidat array management ##
All elements have now got management capability.  On the volumes tab you can create, edit and delete volumes from within UCSD and the same is true for pools and hosts.  The hosts tab has additional functionality to create, edit and delete ports and lun mappings!  Cluster management has now been added, along with snapshot and clones!

![alt text](https://github.com/robjporter/UCSD-Infinidat/blob/master/site/screenshot5.png "Cisco UCS Director Infinidat Integration 5")

## 4. UCS Director tasks to automate Infinidat arrays ##
There are a number of tasks currently in place and they are in various stage of development.  Below is a list of their current status;

Active | Task | Status | Action
:---: | :--- | :---: | :---:
[ ] | New Volume | 100% | Allows the creation of a new volume, setting, size, provisioning type, pool, etc
[ ] | Delete Volume | 100% | TBC
[ ] | Delete Volume By ID | 100% | TBC
[ ] | Edit Volume | 10% | TBC
[ ] | Rollback Volume | 10% | TBC
[ ] | New Host | 10% | TBC
[ ] | Edit Host | 10% | TBC
[ ] | Delete Host | 10% | TBC
[ ] | Rollback Host | 10% | TBC
[ ] | New Pool | 100% | TBC
[ ] | Edit Pool | 10% | TBC
[ ] | Delete Pool | 100% | TBC
[ ] | Rollback Pool | 10% | TBC
[ ] | Map Host to Volume | 10% | TBC
[ ] | Unmap Host to Volume | 10% | TBC
[ ] | Map rollback | 10% | TBC
[ ] | New Cluster | 10% | TBC
[ ] | Edit Cluster | 10% | TBC
[ ] | Delete Cluster | 10% | TBC
[ ] | Add Host to Cluster | 10% | TBC
[ ] | Delete Host from Cluster | 10% | TBC
[ ] | Add Volume to Cluster | 10% | TBC
[ ] | Delete Volume from Cluster | 10% | TBC

## 5. Reports related to Infinidat arrays ##
In planning stage

# Supported Platforms #
The majority of the development work was completed using a VM image of the Infindat array, however this has now been tested against real arrays and changes complete to ensure wider compatibility.

* Infinidat VM Image
* F2130

If you do use this with other Infinidat arrays and encounter bugs(or features), please create issues so I can track them and fix them.

# UCS Director #
If you are new to UCS Director or need to download it to test in your environment and ultimately test this plugin, please go to this site;
[Cisco UCS Director Download](https://ciscosoftware.mediuscorp.com/market/networkers/productView?/nxt/rcrs/proieidentity/=20018)

# Reporting Issues #
Please report as many issues as you encounter, the more the merrier!

If your new to github or need a reminder on how to open, update or close issues, please see here;<br/>
https://help.github.com/articles/creating-an-issue/

# Built with - tools #
The tools used during the creation of the plugin were;
* Eclipse 4.5.2 - https://eclipse.org/
* UCS Director SDK 5.4 - https://developer.cisco.com/site/ucs-director/overview/
* ZOC 7.07.3 - www.emtec.com/zoc/
* Google Chrome 50.0.2661.86 (64-bit) - https://www.google.com/chrome/

# Built with - Java extensions #
These were the Java JAR files added for additional functionality;
* jackson 2.8.0 - https://github.com/FasterXML/jackson
* log4j 1.2.15 - http://logging.apache.org/log4j/
* jsch 0.1.45 - http://www.jcraft.com/jsch/
* jdo 2.3 - http://db.apache.org/jdo/index.html
* UCSD-OA-SDK - https://developer.cisco.com/site/ucs-director/overview/
* UCSD-REST-SDK v2 -https://developer.cisco.com/site/ucs-director/overview/

# Installation #
The installation of the plugin will require a) Shelladmin access to the UCS Director server, b) downtime to the UCS Director server and c) approximately 10 minutes to reload all of the UCS Director services.

A quick installation process is documented here;

http://www.cisco.com/c/en/us/td/docs/unified_computing/ucs/ucs-director/open-automation-api-guide/5-3/b_UCSD_Open_Automation_Developer_Guide_5_3/b_UCSD_Open_Automation_Developer_Guide5-x_chapter_0100.html

# Builds #
I try to ensure that all branches and tags are fully working without issues, but at the least each are full releases with all code, dependencies and successful build files.

If you try the master and encounter bugs, please try the latest tag release, which is tested and verified in a working state.

# Compatibility #
This module was primarily developed using UCS Director 5.4.0.3, however it was tested with 5.5 and on the 23rd June 2016 was migrated for development and testing onto UCSD 6.0 Beta 1.
I would therefore expect it to be compatible with all 5.4.x and 5.5.x releases and initial tests with 6.0 have been successful.

# Roadmap #
As well as addressing the issues as they are tracked within Github, there are a few items that I would like to implement in upcoming releases;
1) Prebuilt workflows
2) Testing on a wider number of Infinidat arrays

# Contributors #
Thanks to anyone and everyone who has in any small part had input into this plugin, with special thanks to John Brooker from Infinidat for his import during the development and testing of the plugin.

# License #
This project is licensed under the MIT License - see the LICENSE.md file for details

# Support #
The Infinidat module is not supported by Infinidat or Cisco directly, it is a community supported module. Cisco Technical Assistance Center provides support for UCS Director as part of a customer support contract included in the software license purchase. Cisco does not provide support for 3rd party developed modules.
