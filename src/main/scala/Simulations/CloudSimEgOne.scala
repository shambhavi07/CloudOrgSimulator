package Simulations
import HelperUtils.{CreateLogger, ObtainConfigReference}
import org.cloudbus.cloudsim.allocationpolicies.VmAllocationPolicySimple
import org.cloudbus.cloudsim.brokers.DatacenterBrokerSimple
import org.cloudbus.cloudsim.cloudlets.CloudletSimple
import org.cloudbus.cloudsim.core.CloudSim
import org.cloudbus.cloudsim.datacenters.DatacenterSimple
import org.cloudbus.cloudsim.hosts.{Host, HostSimple}
import org.cloudbus.cloudsim.resources.{Pe, PeSimple}
import org.cloudbus.cloudsim.utilizationmodels.UtilizationModelDynamic
import org.cloudbus.cloudsim.vms.VmSimple
import org.cloudsimplus.builders.tables.CloudletsTableBuilder

import java.util
import collection.JavaConverters.*

class CloudSimEgOne
//object NetworkOne
object CloudSimEgOne:
  //Initialize config and logger
  val config = ObtainConfigReference("CloudSimEgOne") match {
    case Some(value) => value
    case None => throw new RuntimeException("Cannot obtain a reference to the config data.")
  }
  val logger = CreateLogger(classOf[CloudSimEgOne])

  def start() =
    //create a broker to act on the behalf of a client
    val cloudsim = new CloudSim();
    val broker0 = new DatacenterBrokerSimple(cloudsim);
  //next we want to create datacenters for which we need to create hostlist first
    val hostPes = List.fill(10)(new PeSimple(config.getDouble("CloudSimEgOne.host.mipsCapacity")))
    logger.info(s"Created one processing element: $hostPes")
    val HostList = List.fill(10)(new HostSimple(
      config.getLong("CloudSimEgOne.host.RAMInMBs"),
      config.getLong("CloudSimEgOne.host.StorageInMBs"),
      config.getLong("CloudSimEgOne.host.BandwidthInMBps"), hostPes.asJava))
    logger.info(s"Created one host: $HostList")
    //Now we move on to create datacenter, we want 2 of them
    val dc0 = new DatacenterSimple(cloudsim, HostList.asJava);
    val dc1 = new DatacenterSimple(cloudsim, HostList.asJava);
    //val DatacenterList = List.fill(2)(new DatacenterSimple(cloudsim, HostList.asJava));
    logger.info("Configuring network...")

    //we now setup our virtual machines
    val VirtualMachineList = List(new VmSimple(
      config.getDouble("CloudSimEgOne.vm.mipsCapacity"),hostPes.length)
      .setRam(1000).setSize(1000).setBw(1000));
    logger.info(s"Created 3 virtual machine: $VirtualMachineList")

//    broker1.submitVmList(VirtualMachineList.asJava);
    //create a network for datacenters
    //def NetworkOne() =

    //Now we create and submit cloudlets
    val utilizationModel = new UtilizationModelDynamic(
      config.getDouble("CloudSimEgOne.utilizationRatio"))
    val CloudletList = new CloudletSimple(config.getLong("CloudSimEgOne.cloudlet.size"), config.getInt("cloudSimulator.cloudlet.PEs"), utilizationModel) ::
      new CloudletSimple(config.getLong("CloudSimEgOne.cloudlet.size"), config.getInt("cloudSimulator.cloudlet.PEs"), utilizationModel) :: Nil

    logger.info(s"Created a list of cloudlets: $CloudletList")
    broker0.submitVmList(VirtualMachineList.asJava);
    broker0.submitCloudletList(CloudletList.asJava);
    //broker1.submitCloudletList(CloudletList.asJava);
    logger.info("Starting cloud simulation...")
    cloudsim.start();

    new CloudletsTableBuilder(broker0.getCloudletFinishedList()).build();
//    def createHost: Host = {
//      val peList = new util.ArrayList[Pe]()
//      //List of Host's CPUs (Processing Elements, PEs)
//      var i: Int = 0
//      while ( {
//        i < config.getLong("testCloud1.host.PEs")
//      }) { //Uses a PeProvisionerSimple by default to provision PEs for VMs
//        peList.add(new PeSimple(config.getDouble("testCloud1.host.mipsCapacity")))
//        i += 1
//      }
   // }

//      def createDatacenter(cloudSim: CloudSim): DatacenterSimple = {
//        val hostList = new util.ArrayList[Host]()
//        var i = 0
//        while ( {
//          i < config.getLong("testCloud1.host.Hosts")
//        }) {
//          val host = createHost
//          hostList.add(host)
//
//          i += 1
//        }
//        new DatacenterSimple(cloudSim, hostList, new VmAllocationPolicySimple)
//      }
  //new CloudletsTableBuilder(broker1.getCloudletFinishedList()).build();




















