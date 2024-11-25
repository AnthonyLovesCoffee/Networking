# MAC Throughput Calculations

This folder contains a Java program that calculates the MAC throughputs for *802.11g / ax / ac_w2*.  It calculates the transmission time and throughput based on the selected protocol (TCP/UDP), data rate, and Wi-Fi standard.

## Features

**Standards Supported:**
- 802.11g
- 802.11ax
- 802.11ac_w2 (wave 2)

**Metrics Calculated:**
- Throughput: Actual data throughput in Mbps.
- Transmission Time: Time required to transfer 15GB of data.

**Protocols Supported:**
- TCP
- UDP

## How It Works

The program allows users to:

1. Select a Wi-Fi Standard: Choose between 802.11g, 802.11ac_w2, and 802.11ax in their normal and best-case scenarios.
2. Choose a Data Rate: Select the minimum or maximum data rate supported by the chosen standard.
3. Select a Protocol: Choose between TCP and UDP for the transmission.
4. Compute Results: The program calculates the transmission time for 1500-byte packets and the throughput for a 15GB data transfer.
