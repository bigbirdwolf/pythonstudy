import sys
import time

from PyQt5.QtCore import QTimer
from PyQt5.QtGui import QIcon
from PyQt5.QtWidgets import (QApplication,
                             QPushButton,
                             QWidget,
                             QHBoxLayout,
                             QVBoxLayout)


class Window(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("QPushButtonTest Test")
        qvb = QVBoxLayout()

        self.button = QPushButton("&Click me", self) #设置快捷键Alt+C
        self.button.move(100, 100)
        self.button.clicked.connect(self.button_clicked)

        self.button2 = QPushButton(QIcon("./images/down.jpeg"), '图标初始按钮',self) #设置图标初始化按钮

        self.button3 = QPushButton("点击按钮修改button2的文本",self)
        self.button3.clicked.connect(self.button_clicked2)

        self.button4 = QPushButton('点击按钮效果', self)
        self.button4.clicked.connect(self.on_button_clicked)

        qvb.addWidget(self.button)
        qvb.addWidget(self.button2)
        qvb.addWidget(self.button3)
        qvb.addWidget(self.button4)
        self.setLayout(qvb)

    def button_clicked(self):
        print("Button clicked")

    def button_clicked2(self):
        self.button2.setText("按钮2的文本被修改了")

    def on_button_clicked(self):
        print('Button clicked')

    def animate_button_click(self):
        # 模拟点击按钮的动画效果
        self.button.animateClick(3000)

if __name__ == "__main__":

    app = QApplication(sys.argv)
    window = Window()
    window.show()
    QTimer.singleShot(1000,window.animate_button_click)
    sys.exit(app.exec_())