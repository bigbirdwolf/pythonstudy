'''

QColorDialog 使用示例

'''

import sys
from PyQt5.QtWidgets import QApplication, QColorDialog, QPushButton, QWidget, QVBoxLayout, QLabel
from PyQt5.QtGui import QPalette, QColor


class QColorDialogTest(QWidget):
    def __init__(self):
        super(QColorDialogTest, self).__init__()
        self.initUI()


    def initUI(self):
        self.setWindowTitle('QColorDialog使用示例')
        self.resize(400, 300)

        button = QPushButton('选择颜色', self)
        button.clicked.connect(self.showDialog)
        self.label = QLabel('设置字体颜色，点击按钮查看效果')

        layout = QVBoxLayout()
        layout.addWidget(button)
        layout.addWidget(self.label)
        self.setLayout(layout)


    def showDialog(self):
        color = QColorDialog.getColor()
        p = QPalette()
        p.setColor(QPalette.WindowText, color)
        self.label.setPalette(p)


if __name__ == '__main__':
    app = QApplication([])
    ex = QColorDialogTest()
    ex.show()
    sys.exit(app.exec_())