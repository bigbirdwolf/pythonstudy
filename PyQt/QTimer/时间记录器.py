'''
QTimer 时间记录器

QTimer 是一个计时器类，它可以用来执行一个函数或一个槽函数，并在指定的时间间隔后执行。
'''

import sys
from PyQt5.QtWidgets import QApplication, QWidget, QPushButton, QVBoxLayout, QHBoxLayout, QLabel
from PyQt5.QtCore import QTimer, QTime, QDateTime


class Window(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("QTimer 时间记录器")
        self.resize(300, 200)
        self.initUI()

    def initUI(self):
        self.button = QPushButton("开始计时", self)
        self.button.clicked.connect(self.startTimer)
        self.buttonStop = QPushButton("停止计时", self)
        self.buttonStop.clicked.connect(self.stopTimer)
        self.timer = QTimer(self)
        self.timer.timeout.connect(self.showTime)
        self.label = QLabel("当前时间:")
        self.layout = QVBoxLayout()
        self.layout.addWidget(self.label)
        self.HBoxLayout = QHBoxLayout()
        self.HBoxLayout.addWidget(self.button)
        self.HBoxLayout.addWidget(self.buttonStop)
        self.layout.addLayout(self.HBoxLayout)
        self.setLayout(self.layout)


    def startTimer(self):
        self.timer.start(1000)
        self.button.setEnabled(False)
        self.buttonStop.setEnabled(True)

    def showTime(self):
        #获取当前时间
        currentTime = QDateTime.currentDateTime()
        #格式化时间
        timeString = currentTime.toString("yyyy-MM-dd hh:mm:ss")
        self.label.setText("当前时间:" + timeString)


    def stopTimer(self):
        self.timer.stop()
        # self.button.setText("开始计时")
        self.buttonStop.setEnabled(False)
        self.button.setEnabled(True)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = Window()
    window.show()
    sys.exit(app.exec_())