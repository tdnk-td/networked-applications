files include:
topo.py
L2-Learning.py
README.txt

Requirements:
You are required to use the NOX/POX controller platform for programming
an OpenFlow-based SDN-enabled switch. The goal is to supply rules to the switch's 
table to implement a L2 learning switching functionality. A learning switch creates rules for
source address to port mapping by examining incoming packets. Since this is L2, the addresses
considered here are all MAC and the ports are the switch's ports (i.e., physical ports).

sudo mn --custom L2-Learning.py --topo topo --controller remote --mac 
