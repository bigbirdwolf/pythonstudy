import sys
from PyQt5.QtWidgets import QApplication, QLabel, QMainWindow,QVBoxLayout,QWidget
from PyQt5.QtGui import QPalette,QPixmap
from PyQt5.QtCore import Qt

class MainWindow(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        label1 = QLabel(self)
        label2 = QLabel(self)
        label3 = QLabel(self)
        label4 = QLabel(self)

        label1.setText("<font color='yellow'>这是一个文本标签.</font>")
        label1.setAutoFillBackground(True)
        palette = QPalette() #创建一个调色板
        palette.setColor(QPalette.Window, Qt.blue) #设置背景颜色
        label1.setPalette(palette)
        label1.setAlignment(Qt.AlignCenter) #文本居中

        label2.setText("<a href='http://www.baidu.com'>这是一个链接标签.</a>")

        label3.setAlignment(Qt.AlignCenter)
        label3.setToolTip("这是一个图片标签") #设置提示信息
        label3.setPixmap(QPixmap("./images/down.jpeg"))

        label4.setOpenExternalLinks(True) #如果设置为True，则点击链接时会打开浏览器,否则只会发出信号
        label4.setText("<a href='https://item.jd.com/10000000000.html'>感谢关注京东商城！</a>")

        label4.setAlignment(Qt.AlignRight)

        label4.setToolTip("这是一个超级连接")

        vbox = QVBoxLayout()
        vbox.addWidget(label1)
        vbox.addWidget(label2)
        vbox.addWidget(label3)
        vbox.addWidget(label4)

        label2.linkHovered.connect(self.linkHovered) #绑定鼠标滑过事件
        label4.linkActivated.connect(self.linkClicked) #绑定单击事件

        self.setLayout(vbox)
        self.setWindowTitle("QLabelDemo")

    def linkHovered(self, link):
        print("当鼠标滑过label2标签时，触发事件")

    def linkClicked(self, link):
        print("当label4标签被点击时，触发事件")


if __name__ == '__main__':
    app = QApplication(sys.argv)
    main = MainWindow()
    main.show()
    sys.exit(app.exec_())



