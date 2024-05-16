import sys
import time

from PyQt5.QtCore import QThread, pyqtSignal, pyqtSlot, QTime, QDateTime
from PyQt5.QtWidgets import QApplication, QMainWindow, QPushButton, QLabel, QWidget, QLineEdit, QVBoxLayout, \
    QDialogButtonBox


class updateThread(QThread):

    update_signal = pyqtSignal(str)

    def __init__(self):
        super().__init__()

    def run(self):
        while True:
            current_time = QDateTime.currentDateTime().toString('yyyy-MM-dd hh:mm:ss')
            self.update_signal.emit(current_time)
            time.sleep(1)


class MainWindow(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()


    def initUI(self):
        self.setWindowTitle('Thread Update UI')
        self.resize(300, 100)
        self.layout = QVBoxLayout()
        self.updateTime = QLineEdit()


        self.layout.addWidget(self.updateTime)

        self.updateSignal = updateThread()
        self.updateSignal.update_signal.connect(self.updateTime.setText)
        self.updateSignal.start()
        self.setLayout(self.layout)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    mainWindow = MainWindow()
    mainWindow.show()
    sys.exit(app.exec_())


