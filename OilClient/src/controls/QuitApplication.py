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
        #将信号与槽关联
        self.button.clicked.connect(self.onClick_Button)

        self.status = self.statusBar()
        self.status.showMessage('只存在5秒的消息', 5000)

        layout = QHBoxLayout()
        layout.addWidget(self.button)

        mainFrame = QWidget()
        mainFrame.setLayout(layout)

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

