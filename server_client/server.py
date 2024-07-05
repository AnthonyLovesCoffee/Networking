import socket

s = socket.socket()
print('socket successfully created')

# reserve port
port = 56789

# binding the port
s.bind(('', port))
print(f'socket binded to port {port}')

# put socket into listening mode
s.listen(5)
print('socket listening...')

while True:
    conn, addr = s.accept()
    print('Got connection from', addr)
    cli_msg = ('Connected successfully')
    conn.send(cli_msg.encode())
    conn.close()