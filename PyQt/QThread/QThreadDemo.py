'''
QThreadDemo.py

This is a simple example of using QThread in PyQt.

To run this example, you need to have PyQt5 installed.
'''
from PyQt5.QtCore import QThread, pyqtSignal
from PyQt5.QtWidgets import QApplication, QLabel, QPushButton, QVBoxLayout, QWidget, QMessageBox

sec = 0
threadStatus = False

class WorkThread(QThread):
    #创建一个信号，用于线程间通信
    startSignal = pyqtSignal()
    finishSignal = pyqtSignal()
    def run(self):
        while True:
            self.sleep(1)
            if sec == 10 or threadStatus == False:
                self.finishSignal.emit()
                break
            self.startSignal.emit()

class MainWindow(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()
        self.setWindowTitle('')

    def initUI(self):
        self.label = QLabel('0')
        self.startBtn = QPushButton('Start')
        self.startBtn.clicked.connect(self.start)
        self.stopBtn = QPushButton('Stop')
        self.stopBtn.clicked.connect(self.stop)
        self.stopBtn.setEnabled(False)
        self.layout = QVBoxLayout()
        self.layout.addWidget(self.label)
        self.layout.addWidget(self.startBtn)
        self.layout.addWidget(self.stopBtn)
        self.workThread = WorkThread()
        self.workThread.startSignal.connect(self.startTimer)
        self.workThread.finishSignal.connect(self.stopTimer)
        self.setLayout(self.layout)

    def start(self):
        global threadStatus
        threadStatus = True
        self.workThread.start()
        self.startBtn.setEnabled(False)
        self.stopBtn.setEnabled(True)

    def stop(self):
        # self.workThread.terminate()
        global threadStatus
        threadStatus = False
        self.startBtn.setEnabled(True)
        self.stopBtn.setEnabled(False)

    def startTimer(self):
        global sec
        sec += 1
        self.label.setText(str(sec))


    def stopTimer(self):
        global sec
        global threadStatus
        QMessageBox.information(self, '消息', '计数结束！')
        self.label.setText(str(sec))
        threadStatus = False
        self.startBtn.setEnabled(True)
        self.stopBtn.setEnabled(False)


if __name__ == '__main__':
    app = QApplication([])
    mainWindow = MainWindow()
    mainWindow.show()
    app.exec_()
