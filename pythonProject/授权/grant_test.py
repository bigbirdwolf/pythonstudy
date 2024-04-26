import time
class FileHandle:
    def __init__(self, filename,mode='r',encoding='utf-8'):
       # self.filename = filename
        self.mode = mode
        self.encoding = encoding
        self.file = open(filename,mode,encoding=encoding)

    def write(self, item):
        self.file.write(time.strftime('%Y-%m-%d %H:%M:%S', time.localtime()) + ' ' + item)

    def __getattr__(self, item):
        return getattr(self.file,item)



if __name__ == '__main__':
    f = FileHandle('test.txt','w+')
    # f.seek(0)
    # print(f.read())
    f.write('111111111111111\n')
    f.write('2222222222222221\n')
    f.write('2222222222222223\n')
    f.close()

