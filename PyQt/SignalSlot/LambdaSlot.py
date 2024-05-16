import sys
from PyQt5.QtWidgets import *

class LambdaSlotParam(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()


    def initUI(self):
        self.setWindowTitle('使用lambda表达式作为槽函数参数')
        self.resize(400, 300)

        self.formLayout = QFormLayout()
        self.setLayout(self.formLayout)

        label = QLabel('请输入数字：')
        numEdit = QLineEdit()
        self.formLayout.addRow(label, numEdit)

        label2 = QLabel('请输入数字:')
        numEdit2= QLineEdit()
        self.formLayout.addRow(label2, numEdit2)

        label3 = QLabel('结果：')
        self.numEdit3= QLineEdit()
        self.formLayout.addRow(label3, self.numEdit3)

        btn = QPushButton('计算')
        btn.clicked.connect(lambda: self.calculate(numEdit.text(), numEdit2.text()))
        self.formLayout.addRow(btn)

    def calculate(self, num1,num2):
        result = int(num1) + int(num2)
        self.numEdit3.setText(str(result))


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = LambdaSlotParam()
    ex.show()
    sys.exit(app.exec_())