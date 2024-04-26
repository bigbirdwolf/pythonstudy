import socket

phone = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
phone.connect(('localhost', 8000))
phone.send('hello'.encode('utf-8'))
data = phone.recv(1024)
print(data)
# while True:
#     data = phone.recv(1024)
#     msg = input("请输入:")
#     if msg == "exit":
#         break
#     phone.send(msg)