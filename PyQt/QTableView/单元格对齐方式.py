import sys
from PyQt5.QtWidgets import QApplication, QMainWindow, QTableWidget, QTableWidgetItem, QVBoxLayout, QWidget
from PyQt5.Qt import Qt

class TableAlignmentDemo(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle('QTableWidget Cell Alignment')
        self.setGeometry(300, 100, 600, 400)

        self.table_widget = QTableWidget(self)
        self.table_widget.setRowCount(3)
        self.table_widget.setColumnCount(3)

        # 设置单元格内容
        self.table_widget.setItem(0, 0, QTableWidgetItem("Item 1"))
        self.table_widget.setItem(0, 1, QTableWidgetItem("Item 2"))
        self.table_widget.setItem(0, 2, QTableWidgetItem("Item 3"))

        # 在第二行第二列的单元格中设置文本对齐方式
        item = QTableWidgetItem("Aligned Text")
        item.setTextAlignment(Qt.AlignCenter)  # 设置文本水平垂直居中对齐
        self.table_widget.setItem(1, 1, item)

        item2 = QTableWidgetItem("乔丹")
        item2.setTextAlignment(Qt.AlignRight|Qt.AlignTrailing|Qt.AlignVCenter)
        self.table_widget.setItem(1, 2, item2)

        item3 = QTableWidgetItem("科比")
        item3.setTextAlignment(Qt.AlignLeft | Qt.AlignVCenter)
        self.table_widget.setItem(1, 0, item3)

        layout = QVBoxLayout()
        layout.addWidget(self.table_widget)

        central_widget = QWidget()
        central_widget.setLayout(layout)
        self.setCentralWidget(central_widget)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = TableAlignmentDemo()
    window.show()
    sys.exit(app.exec_())
