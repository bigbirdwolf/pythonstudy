'''

限制QlineEdit输入的字符, 例如只能输入数字、字母、汉字等。

'''


from PyQt5.QtWidgets import * # 导入PyQt5模块
from PyQt5.QtGui import QIntValidator,QDoubleValidator,QRegExpValidator # 导入验证器模块
from PyQt5.QtCore import QRegExp # 导入正则表达式模块

class QLineEditValidator(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle('校验器')

        formLayout = QFormLayout() # 创建表单布局

        lineEditInt = QLineEdit() # 创建整数输入框
        lineEditDouble = QLineEdit() # 创建浮点数输入框
        lineEditRegex = QLineEdit() # 创建正则表达式输入框

        formLayout.addRow('整数输入框', lineEditInt) # 添加到表单布局
        formLayout.addRow('浮点数输入框', lineEditDouble)
        formLayout.addRow('正则表达式输入框', lineEditRegex)

        intValidator = QIntValidator(); # 创建整数验证器
        intValidator.setRange(1,99)

        doubleValidator = QDoubleValidator(); # 创建浮点数验证器
        doubleValidator.setRange(-99.99,99.99)
        doubleValidator.setNotation(QDoubleValidator.StandardNotation) # 设置浮点数显示格式
        doubleValidator.setDecimals(2) # 设置浮点数精度


        regexValidator = QRegExpValidator(QRegExp('[a-zA-Z0-9]+$')); # 创建正则表达式验证器

        lineEditInt.setValidator(intValidator) # 设置整数输入框的验证器
        lineEditDouble.setValidator(doubleValidator) # 设置浮点数输入框的验证器
        lineEditRegex.setValidator(regexValidator) # 设置正则表达式输入框的验证器

        self.setLayout(formLayout) # 设置布局

if __name__ == '__main__':
    app = QApplication([])
    ex = QLineEditValidator()
    ex.show()
    app.exec_()

