'''

QFontDialog 使用示例

'''

import sys
from PyQt5.QtWidgets import QApplication, QFontDialog, QPushButton, QWidget, QVBoxLayout, QLabel


class QFontDialogTest(QWidget):
    def __init__(self):
        super(QFontDialogTest, self).__init__()
        self.initUI()


    def initUI(self):
        self.setWindowTitle('QFontDialog使用示例')
        self.resize(400, 300)

        button = QPushButton('选择字体', self)
        button.clicked.connect(self.showDialog)
        self.label = QLabel('设置字体后，点击按钮查看效果')

        layout = QVBoxLayout()
        layout.addWidget(button)
        layout.addWidget(self.label)
        self.setLayout(layout)


    def showDialog(self):
        font, ok = QFontDialog.getFont()
        if ok:
            self.label.setFont(font)


if __name__ == '__main__':
    app = QApplication([])
    ex = QFontDialogTest()
    ex.show()
    sys.exit(app.exec_())