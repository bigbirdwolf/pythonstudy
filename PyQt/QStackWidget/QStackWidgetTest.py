'''
QStackWidgetTest.py

This is a simple example of using QStackWidget in PyQt.

The QStackWidget is a container widget that allows you to stack multiple widgets on top of each other.
'''
import sys
from PyQt5.QtWidgets import (QApplication, QWidget, QStackedWidget,
                             QPushButton, QListWidget,
                             QHBoxLayout, QFormLayout, QLineEdit, QSpinBox,
                             QComboBox)


class QStackWidgetTest(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()


    def initUI(self):
        self.setWindowTitle('QStackWidget Test')

        # Create a QStackedWidget
        self.stack = QStackedWidget()
        self.page1 = QWidget()
        self.page2 = QWidget()
        self.page3 = QWidget()

        self.stack.addWidget(self.page1)
        self.stack.addWidget(self.page2)
        self.stack.addWidget(self.page3)

        self.list = QListWidget()
        self.list.addItem("Page 1")
        self.list.addItem("Page 2")
        self.list.addItem("Page 3")

        self.page1UI()
        self.page2UI()
        self.page3UI()

        self.list.itemClicked.connect(self.onItemClicked)


        layout = QHBoxLayout()
        layout.addWidget(self.list)
        layout.addWidget(self.stack)

        self.setLayout(layout)

    def onItemClicked(self, item):
        index = self.list.row(item)
        self.stack.setCurrentIndex(index)


    def page1UI(self):
        layout = QFormLayout()
        layout.addRow("Name:", QLineEdit())
        layout.addRow("Age:", QSpinBox())
        layout.addRow("Gender:", QComboBox())
        hlayout = QHBoxLayout()
        button1 = QPushButton("提交")
        button2 = QPushButton("返回")
        hlayout.addWidget(button1)
        hlayout.addWidget(button2)
        layout.addRow(hlayout)
        self.page1.setLayout(layout)

    def page2UI(self):
        layout = QFormLayout()
        layout.addRow("Address:", QLineEdit())
        layout.addRow("Phone:", QLineEdit())
        hlayout = QHBoxLayout()
        button1 = QPushButton("提交")
        button2 = QPushButton("返回")
        hlayout.addWidget(button1)
        hlayout.addWidget(button2)
        layout.addRow(hlayout)
        self.page2.setLayout(layout)

    def page3UI(self):
        layout = QFormLayout()
        layout.addRow("Email:", QLineEdit())
        layout.addRow("Website:", QLineEdit())
        hlayout = QHBoxLayout()
        button1 = QPushButton("提交")
        button2 = QPushButton("返回")
        hlayout.addWidget(button1)
        hlayout.addWidget(button2)
        layout.addRow(hlayout)
        self.page3.setLayout(layout)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = QStackWidgetTest()
    ex.show()
    sys.exit(app.exec_())