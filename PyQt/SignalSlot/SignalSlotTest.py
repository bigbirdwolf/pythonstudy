'''

PyQt5消息系统的信号槽机制的简单使用

信号槽机制是一种在不同线程间通信的机制，在PyQt5中，信号槽机制是通过connect()函数来实现的。

下面是一个简单的例子，演示了如何在两个线程间传递信号，并在另一个线程中接收信号。

'''
import sys

from PyQt5.QtCore import pyqtSignal, QObject


class mySignal(QObject):

    signal_received = pyqtSignal(str)
    signal_sent = pyqtSignal(str)

    def run(self):
        self.signal_received.emit('Hello, Signal!')

    def signal_sender(self):
        self.signal_sent.emit('sent signal')

class myTestSignal(QObject):

    def test_signal(self):
        signal = mySignal()
        signal.signal_received.connect(self.signal_received)
        signal.signal_sent.connect(self.signal_sent)
        signal.run()
        signal.signal_sender()

    def signal_received(self, signal):
        print('Received signal: ', signal)

    def signal_sent(self, signal):
        print('Sent signal: ', signal)

if __name__ == '__main__':
    test = myTestSignal()
    test.test_signal()
    sys.exit(0)