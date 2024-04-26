import socketserver
import subprocess
import struct

class Myserver(socketserver.BaseRequestHandler):
    def handle(self):
        print('connection from', self.client_address)
        print('address: ', self.client_address)
        buffer_size = 1024
        while True:
            try:
                cmd = self.request.recv(buffer_size)
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
                self.request.send(len_res)
                self.request.send(cmd_res)
            except Exception as e:
                print(e)
                break



if __name__ == '__main__':
    server = socketserver.ThreadingTCPServer(('127.0.0.1', 8080), Myserver)
    print(server.server_address)
    server.serve_forever()