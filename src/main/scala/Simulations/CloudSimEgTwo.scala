package Simulations

import HelperUtils.{CreateLogger, ObtainConfigReference}
import org.cloudbus.cloudsim.brokers.DatacenterBrokerSimple
import org.cloudbus.cloudsim.cloudlets.CloudletSimple
import org.cloudbus.cloudsim.core.CloudSim
import org.cloudbus.cloudsim.datacenters.DatacenterSimple
import org.cloudbus.cloudsim.hosts.{Host, HostSimple}
import org.cloudbus.cloudsim.resources.{Pe, PeSimple}
import org.cloudbus.cloudsim.utilizationmodels.UtilizationModelDynamic
import org.cloudbus.cloudsim.vms.VmSimple
import org.cloudsimplus.builders.tables.CloudletsTableBuilder

import java.util.Calendar
import collection.JavaConverters.*
//class CloudSimEgTwo
class CloudSimEgTwo
  val Simulation = "Simulation"
  val config = ObtainConfigReference("cloudSimulator") match {
    case Some(value) => value
    case None => throw new RuntimeException("Cannot obtain a reference to the config data.")
  }
  val logger = CreateLogger(classOf[BasicCloudSimPlusExample])
  def start(args: Array[String])={
    //get the start time for simulation total time
    val StartTime = System.currentTimeMillis()
    logger.info("Starting cloud simulation...")
    //get the number of user
    val NumUser = config.getInt(Simulation+".NumUser")
    //you will have to import java calendar file
    val calendar = Calendar.getInstance
    //get the number of virtual machines
    val Vms = config.getInt(Simulation+".Vms")
    //get number of Datacenters
    val NumDataCenters = config.getInt(Simulation+ ".NumDataCenters")

    //Now we start cloudsim
    val cloudsim = new CloudSim();
    val broker0 = new DatacenterBrokerSimple(cloudsim);}







