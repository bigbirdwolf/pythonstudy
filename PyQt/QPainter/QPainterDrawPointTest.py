import sys
import math
from PyQt5.QtWidgets import QApplication, QWidget
from PyQt5.QtGui import QPainter, QPen
from PyQt5.QtCore import Qt

class Example(QWidget):
    def __init__(self):
        super().__init__()

    def paintEvent(self, event):
        painter = QPainter(self)

        # 设置画笔颜色和宽度
        pen = QPen()
        pen.setColor(Qt.blue)
        pen.setWidth(2)
        painter.setPen(pen)

        # 绘制两个周期的正弦曲线
        y_amplitude = 50  # 正弦波的振幅
        period_count = 2  # 周期数量

        for i in range(0, self.width()):
            # 计算正弦波的y坐标
            y1 = y_amplitude * math.sin(2 * math.pi * period_count * i / self.width())
            y2 = y_amplitude * math.sin(2 * math.pi * period_count * i / self.width() + math.pi)

            painter.drawPoint(i, int(y1) + self.height()//2)  # 平移到屏幕中间
            # painter.drawPoint(i, int(y2) + self.height()//2)

if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    ex.setGeometry(300, 300, 800, 200)
    ex.setWindowTitle('绘制两个周期的正弦曲线')
    ex.show()
    sys.exit(app.exec_())
