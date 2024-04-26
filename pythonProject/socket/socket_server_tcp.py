import socket
import subprocess
import struct

HOST = '127.0.0.1'
port = 8080
back_log = 5
buffer_size = 1024

#实例化socket tcp协议
tcp_server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
tcp_server.bind((HOST, port))
tcp_server.listen(back_log)
while True:
    conn, addr = tcp_server.accept()
    print('Connected by', addr)
    while True:
        try:
            cmd = conn.recv(buffer_size)
            if not cmd:
                break
            print('Received', repr(cmd))
            # 接收到的数据是二进制数据，需要进行解码
            res = subprocess.Popen(cmd.decode('utf-8'), shell=True,
                                   stdout=subprocess.PIPE,
                                   stderr=subprocess.PIPE,
                                   stdin=subprocess.PIPE)
            # 打印接收到的数据
            err = res.stderr.read()
            if err:
                cmd_res = err
            else:
                cmd_res = res.stdout.read()
            # 发送数据
            if not cmd_res:
                cmd_res = '执行成功'.encode('gbk')
            len_res = struct.pack('i', len(cmd_res))
            conn.send(len_res)
            conn.send(cmd_res)
        except Exception as e:
            print(e)
            break
