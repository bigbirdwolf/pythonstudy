'''

QLineEdit控件与回显模式

基本功能:输入单行的文本

EchoMode属性:用于设置QLineEdit控件的回显模式。

QLineEdit控件的回显模式有以下几种:

1. Normal:正常模式，即输入的文本显示在控件中。

2. NoEcho:不回显模式，即输入的文本不显示在控件中。

3. Password:密码模式，即输入的文本显示为星号。

4. PasswordEchoOnEdit:编辑时显示密码模式，即输入的文本显示为星号，但在编辑结束后才显示输入的文本。


setPlaceholderText()方法:设置控件的提示文本。

setEchoMode()方法:设置控件的回显模式。

代码示例:
'''

import sys
from PyQt5.QtWidgets import (QApplication,
                             QLineEdit,
                             QPushButton,
                             QVBoxLayout,
                             QFormLayout,
                             QWidget)

class QLineEditEchoMode(QWidget):
    def __init__(self):
        super(QLineEditEchoMode, self).__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle('QLineEdit EchoMode')

        formLayout = QFormLayout()
        normalLineEdit = QLineEdit()
        noEchoLineEdit = QLineEdit()
        passwordLineEdit = QLineEdit()
        passwordEchoOnEditLineEdit = QLineEdit()

        formLayout.addRow('Normal', normalLineEdit)
        formLayout.addRow('NoEcho', noEchoLineEdit)
        formLayout.addRow('Password', passwordLineEdit)
        formLayout.addRow('PasswordEchoOnEdit', passwordEchoOnEditLineEdit)

        normalLineEdit.setPlaceholderText('Normal')
        noEchoLineEdit.setPlaceholderText('NoEcho')
        passwordLineEdit.setPlaceholderText('Password')
        passwordLineEdit.setPlaceholderText('PasswordEchoOnEdit')

        normalLineEdit.setEchoMode(QLineEdit.Normal)
        noEchoLineEdit.setEchoMode(QLineEdit.NoEcho)
        passwordLineEdit.setEchoMode(QLineEdit.Password)
        passwordEchoOnEditLineEdit.setEchoMode(QLineEdit.PasswordEchoOnEdit)

        self.setLayout(formLayout)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = QLineEditEchoMode()
    ex.show()
    sys.exit(app.exec_())
