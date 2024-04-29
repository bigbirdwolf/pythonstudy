'''
QInputDialog 控件练习
'''

import sys
from PyQt5.QtWidgets import (QApplication, QPushButton, QLineEdit,
                             QInputDialog,
                             QLineEdit, QDialog, QDialogButtonBox,QFormLayout,QHBoxLayout)


class InputDialogTest(QDialog):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle('Input Dialog')
        self.resize(300, 100)

        #创建一个表单布局
        layout = QFormLayout()

        #创建3个按钮和输入框
        self.buttonItem = QPushButton()
        self.buttonItem.setText('使用InputDialog获取Item')
        self.qlineEditItem = QLineEdit()
        self.buttonItem.clicked.connect(self.getItem)

        self.buttonText = QPushButton()
        self.buttonText.setText('使用InputDialog获取文本')
        self.qlineEditText = QLineEdit()
        self.buttonText.clicked.connect(self.getText)

        self.buttonInt = QPushButton()
        self.buttonInt.setText('使用InputDialog获取整数')
        self.qlineEditInt = QLineEdit()
        self.buttonInt.clicked.connect(self.getInt)


        layout.addRow(self.buttonItem, self.qlineEditItem)
        layout.addRow(self.buttonText, self.qlineEditText)
        layout.addRow(self.buttonInt, self.qlineEditInt)

        self.setLayout(layout)

    def getItem(self):
        item, ok = QInputDialog.getItem(self, '选择球队', '请选择一个球队', ['湖人', '快船', '热火'])
        if ok and item:
            self.qlineEditItem.setText(item)

    def getText(self):
        text, ok = QInputDialog.getText(self, '输入文本', '请输入文本:')
        if ok and text:
            self.qlineEditText.setText(text)

    def getInt(self):
        num, ok = QInputDialog.getInt(self, '输入整数', '请输入整数:')
        if ok and num:
            self.qlineEditInt.setText(str(num))


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = InputDialogTest()
    ex.show()
    sys.exit(app.exec_())