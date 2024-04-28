import sys
from PyQt5.QtWidgets import (QApplication,
                             QDialog,
                             QPushButton,
                             QVBoxLayout,
                             QLabel,
                             QMainWindow)

class MyDialog(QDialog):
    def __init__(self):
        super().__init__()

        self.setWindowTitle('QDialog Example')

        # 创建一个垂直布局
        vbox = QVBoxLayout()

        # 创建一个标签用于显示消息
        self.label = QLabel('This is a QDialog', self)
        vbox.addWidget(self.label)

        # 创建一个按钮用于关闭对话框
        button = QPushButton('Close', self)
        button.clicked.connect(self.close)  # 点击按钮关闭对话框
        vbox.addWidget(button)

        # 应用布局
        self.setLayout(vbox)

class MyWindow(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle('QMainWindow Example')

        # 创建一个按钮用于显示对话框
        button = QPushButton('Show Dialog', self)
        button.clicked.connect(self.showDialog)  # 点击按钮显示对话框
        self.setCentralWidget(button)

    def showDialog(self):
        dialog = MyDialog()
        dialog.exec_()  # 以模态方式显示对话框

if __name__ == '__main__':
    app = QApplication(sys.argv)

    window = MyWindow()
    window.show()

    sys.exit(app.exec_())
