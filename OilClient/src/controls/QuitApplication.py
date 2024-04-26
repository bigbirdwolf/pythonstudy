import sys

from PyQt5.QtGui import QIcon
from PyQt5.QtWidgets import (QApplication,
                             QWidget,
                             QMainWindow,
                             QLabel,
                             QHBoxLayout,
                             QPushButton)


class QuitApplication(QMainWindow):
    def __init__(self, parent=None):
        super(QuitApplication, self).__init__(parent)

        # 设置窗口标题
        self.setWindowTitle('退出应用程序')

        # 设置窗口大小
        self.resize(400, 300)

        #添加一个button
        self.button = QPushButton('退出应用程序')
        #将点击信号与槽关联
        self.button.clicked.connect(self.onClick_Button)

        #创建一个水平布局
        layout = QHBoxLayout()
        #将button添加到布局中
        layout.addWidget(self.button)
        #创建一个主窗口
        mainFrame = QWidget()
        #将布局设置为主窗口
        mainFrame.setLayout(layout)
        #将主窗口设置到屏幕
        self.setCentralWidget(mainFrame)

    def onClick_Button(self):
        sender = self.sender()
        print(sender.text())
        QApplication.instance().quit()


if __name__ == '__main__':
    app = QApplication(sys.argv)

    main = QuitApplication()
    main.show()
    sys.exit(app.exec_())

