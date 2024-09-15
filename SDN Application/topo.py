from mininet.topo import Topo
from mininet.net import Mininet
from mininet.node import RemoteController, OVSSwitch


class MyTopology(Topo):
    def __init__(self):
        # Initialize topology
        Topology.__init__(self)
        # Adding the network devices to the topology
        H1 = self.addHost("h1", mac = '00:00:00:00:00:01')
        H2 = self.addHost("h2", mac = 'D2:13:1C:1B:76:A0')
        H3 = self.addHost("h3", mac = '00:00:00:00:00:03')
        H4 = self.addHost("h4", mac = '00:00:00:00:00:04')
        H5 = self.addHost("h5", mac = '00:00:00:00:00:05')
        H6 = self.addHost("h6", mac = 'BA:94:91:62:F1:65')
        S1 = self.addSwitch('s1')
        S2 = self.addSwitch('s2')
        S3 = self.addSwitch('s3')
        S4 = self.addSwitch('s4')
        S5 = self.addSwitch('s5')
        S6 = self.addSwitch('s6')
        S7 = self.addSwitch('s7')

        # the links for the devices
        self.addLink(H1, S4)
        self.addLink(H2, S4)
        self.addLink(H3, S5)
        self.addLink(H4, S6)
        self.addLink(H5, S7)
        self.addLink(H6, S7)
        self.addLink(S2, S4)
        self.addLink(S2, S5)
        self.addLink(S1, S2)
        self.addLink(S1, S3)
        self.addLink(S3, S6)
        self.addLink(S3, S7)


    def newTopology():
        # initialize the topology
        topo = MyTopology()

        # Create the network, start, open CLI
        network = Mininet(topo = topo, controller = lambda name: RemoteController(name, ip = '127.0.0.1', port = 6633))
        network.start()

        # Exit and shutdown
        net.do_help()