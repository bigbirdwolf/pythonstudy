import sys
import PyQt5.QtCore as QtCore
import PyQt5.QtGui as QtGui
import PyQt5.QtWidgets as QtWidgets
from PyQt5.QtGui import QPixmap


class Drawing(QtWidgets.QWidget):
    def __init__(self):
        super().__init__()
        self.resize(800, 600)
        self.setWindowTitle('Drawing')
        self.lastPoint = QtCore.QPoint()
        self.endPoint = QtCore.QPoint()
        self.initUI()

    def initUI(self):
        self.pix = QPixmap(self.size())
        self.pix.fill(QtCore.Qt.white)

    def paintEvent(self, event):
        ppainter = QtGui.QPainter(self.pix)
        ppainter.drawLine(self.lastPoint, self.endPoint) # draw the line
        self.lastPoint = self.endPoint # update the last point
        painter = QtGui.QPainter(self)
        painter.drawPixmap(0, 0, self.pix) # draw the pixmap on the widget

    def mousePressEvent(self, event):
        if event.button() == QtCore.Qt.LeftButton:
            self.lastPoint = event.pos() # set the last point to the current point

    def mouseMoveEvent(self, event):
        if event.buttons() & QtCore.Qt.LeftButton:
            self.endPoint = event.pos() # set the end point to the current point
            self.update() # update the widget to redraw the line


    def mouseReleaseEvent(self, event):
        if event.button() == QtCore.Qt.LeftButton:
            self.endPoint = event.pos() # set the end point to the current point
            self.update() # update the widget to redraw the line


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    ex = Drawing()
    ex.show()
    sys.exit(app.exec_())



