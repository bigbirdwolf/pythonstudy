'''
在 PyQt 中，clicked() 和 click() 是两个不同的方法：

clicked() 是一个信号（Signal），用于在按钮被点击时发出信号，可以连接到槽（Slot）函数，以便在按钮被点击时执行特定操作。

click() 是一个方法，用于模拟按钮被点击的动作。调用 click() 方法等同于用户实际点击了按钮一样，会触发按钮的 clicked 信号，并执行与之相连接的槽函数。

下面是一个简单的示例，演示了 clicked() 信号和 click() 方法的使用：

'''

import sys
from PyQt5.QtWidgets import QApplication, QMainWindow, QPushButton

class MyWindow(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle('Button Example')
        self.setGeometry(200, 200, 300, 100)

        self.button = QPushButton('Click Me', self)
        self.button.clicked.connect(self.on_button_clicked)
        self.button.setGeometry(100, 40, 100, 30)

    def on_button_clicked(self):
        print('Button clicked')

    def simulate_button_click(self):
        # 模拟按钮点击
        self.button.click()

if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = MyWindow()
    window.show()

    # 模拟按钮点击
    window.simulate_button_click()

    sys.exit(app.exec_())
