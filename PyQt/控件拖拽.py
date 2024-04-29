import sys
from PyQt5.QtWidgets import QApplication, QWidget, QLabel
from PyQt5.QtGui import QPixmap
from PyQt5.QtCore import Qt, QMimeData, QPoint, QRect, QByteArray, QDataStream, QIODevice
from PyQt5.Qt import QPushButton, QDrag

class DragLabel(QLabel):
    def mousePressEvent(self, event):
        if event.button() == Qt.LeftButton:
            drag = QDrag(self)
            mimeData = QMimeData()
            mimeData.setText(self.text())
            drag.setMimeData(mimeData)
            drag.setHotSpot(event.pos() - self.rect().topLeft())
            drag.exec_(Qt.MoveAction)

class DropLabel(QLabel):
    def __init__(self, parent=None):
        super().__init__(parent)
        self.setAcceptDrops(True)

    def dragEnterEvent(self, event):
        if event.mimeData().hasText():
            event.accept()
        else:
            event.ignore()

    def dropEvent(self, event):
        position = event.pos()
        text = event.mimeData().text()
        label = QLabel(text, self)
        label.move(position)
        label.show()

class Example(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle('Drag and Drop Example')
        self.setGeometry(300, 300, 300, 220)

        drag_label = DragLabel('Drag me', self)
        drag_label.move(50, 50)

        drop_label = DropLabel(self)
        drop_label.setGeometry(150, 50, 100, 100)
        drop_label.setStyleSheet("border: 2px dashed #000")

        self.show()

app = QApplication(sys.argv)
ex = Example()
sys.exit(app.exec_())
