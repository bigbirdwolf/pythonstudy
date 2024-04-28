'''

QlineEdit综合示例

'''

import sys

from PyQt5.QtGui import QIntValidator, QDoubleValidator
from PyQt5.QtCore import Qt
from PyQt5.QtWidgets import *


class QlineEditDemo(QWidget):
    def __init__(self):
        super(QlineEditDemo, self).__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle('QLineEditDemo')

        lineEditInt = QLineEdit()
        lineEditInt.setPlaceholderText('输入整数')
        lineEditInt.setMaxLength(4)
        lineEditInt.setAlignment(Qt.AlignRight)
        intValidator = QIntValidator()
        lineEditInt.setValidator(intValidator)

        lineEditDouble = QLineEdit()
        lineEditDouble.setPlaceholderText('输入浮点数')
        doubleValidator = QDoubleValidator(0.99,99.99,2)
        lineEditDouble.setValidator(doubleValidator)

        lineEditInputMask = QLineEdit()
        lineEditInputMask.setInputMask('999-999-9999;_')

        lineEditTextChanged = QLineEdit()
        lineEditTextChanged.setEchoMode(QLineEdit.Password)
        lineEditTextChanged.textChanged.connect(self.textChanged)

        lineEditTextFinished = QLineEdit()
        lineEditTextFinished.textEdited.connect(self.textFinished)

        lineEditReadOnly = QLineEdit()
        lineEditReadOnly.setReadOnly(True)

        formLayout = QFormLayout()
        formLayout.addRow('整数校验', lineEditInt)
        formLayout.addRow('浮点数校验', lineEditDouble)
        formLayout.addRow('掩码校验',lineEditInputMask)
        formLayout.addRow('文本改变', lineEditTextChanged)
        formLayout.addRow('文本结束', lineEditTextFinished)
        formLayout.addRow('只读', lineEditReadOnly)

        self.setLayout(formLayout)

    def textChanged(self, text):
        print('text changed:', text)

    def textFinished(self):
        print('text finished:')


if __name__ == '__main__':
    app = QApplication(sys.argv)
    main = QlineEditDemo()
    main.show()
    sys.exit(app.exec_())
