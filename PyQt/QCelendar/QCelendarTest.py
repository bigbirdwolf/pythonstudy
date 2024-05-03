import sys
from PyQt5.QtWidgets import QApplication, QWidget, QVBoxLayout, QCalendarWidget, QLabel
from PyQt5.QtCore import QDate

class CalendarExample(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        layout = QVBoxLayout()

        calendar = QCalendarWidget(self)
        calendar.setGridVisible(True)  # 显示日期网格
        calendar.setSelectedDate(QDate.currentDate())  # 设置初始选中日期

        calendar.clicked.connect(self.showDate)  # 连接信号与槽函数

        self.label = QLabel(self)
        self.showDate(calendar.selectedDate())  # 显示初始选中日期

        layout.addWidget(calendar)
        layout.addWidget(self.label)

        self.setLayout(layout)
        self.setGeometry(100, 100, 400, 300)
        self.setWindowTitle('Calendar Example')

    def showDate(self, date):
        self.label.setText(date.toString())

app = QApplication(sys.argv)
ex = CalendarExample()
ex.show()
sys.exit(app.exec_())