'''

QFileDialog 使用示例
创建两个按钮分别打开文件对话框，其中一个按钮选择图片显示在label，另外一个按钮选择文件，并将文件的内容显示在textEdit中。
'''

import sys
from PyQt5.QtWidgets import QApplication, QFileDialog, QLabel, QPushButton, QTextEdit, QWidget,QVBoxLayout,QHBoxLayout
from PyQt5.QtGui import QPixmap
from PyQt5.QtCore import Qt

class QFileDialogTest(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()


    def initUI(self):
        self.setWindowTitle('QFileDialog Test')
        self.resize(400, 300)

        self.label = QLabel(self)
        self.label.setAlignment(Qt.AlignCenter)

        self.openImageButton = QPushButton('Open Image', self)
        self.openImageButton.clicked.connect(self.openImage)

        self.openFileButton = QPushButton('Open File', self)
        self.openFileButton.clicked.connect(self.openFile)

        self.textEdit = QTextEdit(self)

        layout = QVBoxLayout()
        layout.addWidget(self.openImageButton)
        layout.addWidget(self.openFileButton)
        layout.addWidget(self.label)
        layout.addWidget(self.textEdit)
        self.setLayout(layout)

    def openImage(self):

        fileName, _ = QFileDialog.getOpenFileName(self, 'Open Image', '', 'Image Files (*.jpg *.png *.jpeg)')
        if fileName:
            pixmap = QPixmap(fileName)
            self.label.setPixmap(pixmap)

    def openFile(self):
        fileName, _ = QFileDialog.getOpenFileName(self, 'Open File', '', 'All Files (*)')
        if fileName:
            with open(fileName, encoding='utf-8',mode='r') as f:
                text = f.read()
                self.textEdit.setText(text)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = QFileDialogTest()
    ex.show()
    sys.exit(app.exec_())