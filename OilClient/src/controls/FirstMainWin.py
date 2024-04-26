import sys

from PyQt5.QtGui import QIcon
from PyQt5.QtWidgets import QApplication, QWidget, QMainWindow, QLabel


class FirstMainWin(QMainWindow):
    def __init__(self,parent=None):
        super(FirstMainWin,self).__init__(parent)

        # 设置窗口标题
        self.setWindowTitle('第一个主窗口应用')

        # 设置窗口大小
        self.resize(400,300)
        self.status = self.statusBar()
        self.status.showMessage('只存在5秒的消息',5000)


if __name__ == '__main__':
    app = QApplication(sys.argv)

    app.setWindowIcon(QIcon('./img/logo.png'))
    main = FirstMainWin()
    main.show()
    sys.exit(app.exec_())

 