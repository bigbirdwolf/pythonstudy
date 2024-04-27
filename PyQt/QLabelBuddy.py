'''
QLabel与伙伴控件

mainLayout.addWidget(控件对象,行号,列号,行跨度,列跨度)
'''


from PyQt5.QtWidgets import *
import sys

class QlabelBuddy(QDialog): # 继承QDialog对话框类
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle('QLabel与伙伴控件')

        nameLabel = QLabel('&Name',self)
        nameLineEdit = QLineEdit(self)

        nameLabel.setBuddy(nameLineEdit) # 设置伙伴控件

        passwordLabel = QLabel('&Password', self)
        passwpordLineEdit = QLineEdit(self)

        passwordLabel.setBuddy(passwpordLineEdit)  # 设置伙伴控件

        btnOkButton = QPushButton('&OK')
        btnCancel = QPushButton('&Cancel')

        mainLayout = QGridLayout(self)
        mainLayout.addWidget(nameLabel,0,0)
        mainLayout.addWidget(nameLineEdit,0,1,1,2)

        mainLayout.addWidget(passwpordLineEdit,1,1,1,2)
        mainLayout.addWidget(passwordLabel,1,0)

        mainLayout.addWidget(btnOkButton,2,1)
        mainLayout.addWidget(btnCancel,2,2)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    main = QlabelBuddy()
    main.show()
    sys.exit(app.exec_())