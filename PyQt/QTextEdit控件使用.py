import sys
from PyQt5.QtWidgets import (QApplication, QWidget,
                             QTextEdit, QVBoxLayout, QPushButton,QHBoxLayout, QVBoxLayout)


class Example(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.textEdit = QTextEdit()
        self.textEdit.setText("Hello PyQt5")
        self.resize(300, 200)
        self.setWindowTitle("QTextEdit控件使用")

        self.textHtmlEdit = QTextEdit()
        self.textHtmlEdit.setHtml("<span style=\"color:red\">textEditHtml</span>")

        #定义两个按钮
        self.getTextButton = QPushButton("获取文本")
        self.setTextButton = QPushButton("设置文本")

        #设置按钮位置
        self.getTextButton.move(10, 10)
        self.setTextButton.move(10, 50)

        #设置按钮的位置

        #绑定按钮信号槽
        self.getTextButton.clicked.connect(self.getText)
        self.setTextButton.clicked.connect(self.setText)

        #添加布局
        qvb = QVBoxLayout() #垂直布局
        #添加一个水平布局
        hlayout = QHBoxLayout()
        hlayout.addWidget(self.getTextButton)
        hlayout.addWidget(self.setTextButton)
        qvb.addWidget(self.textEdit)
        qvb.addWidget(self.textHtmlEdit)
        qvb.addLayout(hlayout)
        self.setLayout(qvb)

    def getText(self):
        text = self.textEdit.toPlainText()
        textHtml = self.textHtmlEdit.toHtml()
        print(text, textHtml)

    def setText(self):
        self.textEdit.setText("设置文本成功")
        self.textHtmlEdit.setHtml("<span style=\"color:red\">改变了文本内容</span>")

if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    ex.show()
    sys.exit(app.exec_())