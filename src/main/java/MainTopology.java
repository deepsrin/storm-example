import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

public class MainTopology {

    public static void main(String[] args) {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("IntegerSpout", new IntegerSpout());
        builder.setBolt("MultiplierBolt", new MultiplierBolt()).shuffleGrouping("IntegerSpout");

        Config config = new Config();
        //config.setDebug(true);

        LocalCluster cluster = null;
        try {
            cluster = new LocalCluster();
            cluster.submitTopology("Multiplier Topology", config, builder.createTopology());
            Thread.sleep(1000);
        } catch(Exception ex) {
            cluster.shutdown();
        }

    }
}
