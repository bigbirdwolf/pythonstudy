'''

使用QPainter绘制一个矩形和一个文本

'''

import sys
from PyQt5.QtWidgets import QApplication, QWidget
from PyQt5.QtGui import QPainter, QColor, QPen, QFont
from PyQt5.QtCore import Qt

class QPainterTest(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.resize(300, 300)
        self.setWindowTitle('QPainterTest')
        self.painter = QPainter(self)  # 创建QPainter对象
        self.text = 'Hello PyQt5'
        self.setAutoFillBackground(True) # 自动填充背景

    def paintEvent(self, event):
        self.painter.begin(self)  # 开始绘制
        # self.painter.setPen(QPen(QColor(255, 0, 0))) # 设置画笔及颜色
        self.painter.setBrush(QColor(255, 128, 0))
        rect =self.painter.drawRect(10,10,100,100) # 绘制矩形

        # self.painter.setFont(QFont('Arial',30)) # 设置字体及大小
        self.painter.drawText(110//2,110//2, self.text) # 绘制文本(区域，位置，文本)
        self.painter.end() # 结束绘制

if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = QPainterTest()
    ex.show()
    sys.exit(app.exec_())