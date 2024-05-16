'''

关闭窗口信号槽

'''
import sys
import PyQt5.QtWidgets as QtWidgets
import PyQt5.QtCore as QtCore


class mySignal(QtCore.QObject):

    btn_clicked_signal = QtCore.pyqtSignal()

    def __init__(self):
        super(mySignal, self).__init__()


class MainWindow(QtWidgets.QMainWindow):

    def __init__(self):
        super(MainWindow, self).__init__()
        self.setWindowTitle("Signal Slot Example")
        self.setGeometry(500, 200, 300, 300)
        self.button = QtWidgets.QPushButton("")
        self.btn_close_signal = mySignal()
        self.button.clicked.connect(self.btn_close_signal.btn_clicked_signal)
        self.btn_close_signal.btn_clicked_signal.connect(self.button_clicked)
        self.setCentralWidget(self.button)

    def button_clicked(self):
        self.close()

if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    window = MainWindow()
    window.show()
    sys.exit(app.exec_())


