import sys
from PyQt5.QtWidgets import QApplication, QWidget, QRadioButton, QVBoxLayout

class MyWidget(QWidget):
    def __init__(self):
        super().__init__()

        self.initUI()

    def initUI(self):
        self.setWindowTitle('QRadioButton Example')

        # 创建单选按钮
        self.radio1 = QRadioButton('Option 1', self)
        self.radio2 = QRadioButton('Option 2', self)
        self.radio3 = QRadioButton('Option 3', self)

        # 将单选按钮添加到垂直布局
        vbox = QVBoxLayout()
        vbox.addWidget(self.radio1)
        vbox.addWidget(self.radio2)
        vbox.addWidget(self.radio3)

        # 使用垂直布局作为主布局
        self.setLayout(vbox)

        # 为单选按钮添加信号槽连接
        self.radio1.toggled.connect(self.on_radio_button_toggled)
        self.radio2.toggled.connect(self.on_radio_button_toggled)
        self.radio3.toggled.connect(self.on_radio_button_toggled)

    def on_radio_button_toggled(self):
        sender = self.sender()
        if sender.isChecked():
            print(sender.text() + " is selected")

if __name__ == '__main__':
    app = QApplication(sys.argv)
    widget = MyWidget()
    widget.show()
    sys.exit(app.exec_())