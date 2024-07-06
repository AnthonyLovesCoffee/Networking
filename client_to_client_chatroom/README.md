# Simple Chatroom

This folder contains the server and client scripts for the chatroom implementation.

## Overview

The project consists of two main scripts:

1. **Server-side script** (`server.py`): This script sets up a server that listens for incoming client connections. Each connected client is handled in a separate thread, allowing multiple clients to communicate simultaneously.

2. **Client-side script** (`client.py`): This script allows a user to connect to the chatroom server, choose an alias, and send and receive messages.

## Features

- Multi-client support: The server can handle multiple clients at the same time.
- Real-time messaging: Clients can send and receive messages in real-time.
- Alias system: Clients can choose an alias which is displayed with their messages.

## TODOs

- [ ] Allow clients to change their alias during the session.
- [ ] Add a GUI for the client-side to improve user experience.
- [ ] Implement encryption for messages to enhance security.
- [ ] Improve error handling and reconnection logic for clients.
- [ ] Implement server-side command handling (e.g., kicking users, banning).