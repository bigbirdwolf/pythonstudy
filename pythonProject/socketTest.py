import socket

phone = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
phone.bind(('127.0.0.1',8000))
phone.listen(5)
conn, addr = phone.accept()
data = conn.recv(1024)

print(data)
conn.send(data.upper())
conn.close()
phone.close()
# while True:
#     conn, addr = phone.accept()
#     data = conn.recv(1024)
#     print(data)
#     msg = input("Enter your message: ")
#     conn.send(msg)
#     if msg == "quit":
#         conn.close()
#         phone.close()