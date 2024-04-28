'''

QMessageBox 练习使用

'''
import sys
from PyQt5.QtWidgets import QMessageBox, QApplication, QDialog, QWidget,QPushButton, QVBoxLayout

class MyWindow(QDialog):
    def __init__(self):
        super().__init__()
        self.initUI()

        self.setWindowTitle('测试QMessageBox')

    def initUI(self):
        self.resize(300, 200)
        #定义5个按钮点击事件不同的消息类型
        self.buttonInfo = QPushButton('Info', self)
        self.buttonAbout = QPushButton('About', self)
        self.buttonQuestion = QPushButton('Question', self)
        self.buttonWarning = QPushButton('Warning', self)
        self.buttonCritical = QPushButton('Critical', self)
        self.buttonMessagebox = QPushButton('Messagebox', self)


        self.messagebox = QMessageBox()
        self.messagebox.setText('这是一个对话框')
        self.messagebox.setInformativeText('是想要保存更改吗？')
        self.messagebox.setDetailedText('详细信息')
        self.messagebox.setWindowTitle('消息框标题')
        self.messagebox.setStandardButtons( QMessageBox.Discard | QMessageBox.Cancel)
        self.messagebox.setDetailedText('显示详细信息')
        self.save_button = self.messagebox.addButton("保存",QMessageBox.AcceptRole)


        self.buttonInfo.clicked.connect(self.ButtonClicked)
        self.buttonAbout.clicked.connect(self.ButtonClicked)
        self.buttonQuestion.clicked.connect(self.ButtonClicked)
        self.buttonWarning.clicked.connect(self.ButtonClicked)
        self.buttonCritical.clicked.connect(self.ButtonClicked)
        self.buttonMessagebox.clicked.connect(self.ButtonClicked)

        #创建垂直布局
        layout = QVBoxLayout()
        layout.addWidget(self.buttonInfo)
        layout.addWidget(self.buttonAbout)
        layout.addWidget(self.buttonQuestion)
        layout.addWidget(self.buttonWarning)
        layout.addWidget(self.buttonCritical)
        layout.addWidget(self.buttonMessagebox)

        self.setLayout(layout)

    def ButtonClicked(self):
        sender = self.sender()
        if sender == self.buttonInfo:
            QMessageBox.information(self, '信息', '这是一条信息消息')
        elif sender == self.buttonAbout:
            QMessageBox.about(self, '关于', '这是关于消息')
        elif sender == self.buttonQuestion:
            reply = QMessageBox.question(self, '询问', '这是一条询问消息', QMessageBox.Yes | QMessageBox.No, QMessageBox.Yes)
            if reply == QMessageBox.Yes:
                print('用户选择了Yes')
            else:
                print('用户选择了No')
        elif sender == self.buttonWarning:
            QMessageBox.warning(self, '警告', '这是一条警告消息')
        elif sender == self.buttonCritical:
            QMessageBox.critical(self, '严重', '这是一条严重消息')
        elif sender == self.buttonMessagebox:
            res = self.messagebox.exec_()
            if self.messagebox.clickedButton() == self.save_button:
                print('用户选择了保存')
            elif res == QMessageBox.Discard:
                print('用户选择了丢弃')
            elif res == QMessageBox.Cancel:
                print('用户选择了取消')
            else:
                print('用户关闭了消息框')


if __name__ == '__main__':
    app = QApplication(sys.argv)
    myWin = MyWindow()
    myWin.show()
    sys.exit(app.exec_())