import socket
import sys

# AF_INET: ipv4
# SOCK_STREAM: connection orientation TCP

try:
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    print('socket successfully created!')
except socket.error as err:
    print(f'socket creation failed with error {err}')

port = 80

try:
    host_ip = socket.gethostbyname('www.github.com')
except socket.gaierror: # problem with DNS
    print('error resolving host')
    sys.exit()
s.connect((host_ip, port))
print(f'socket has successfully connected to GitHub on port: {host_ip}')