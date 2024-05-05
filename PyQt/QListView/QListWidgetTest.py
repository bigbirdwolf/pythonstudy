'''
QListWidget 使用示例
'''

import sys
from PyQt5.QtCore import QStringListModel
from PyQt5.QtWidgets import (QApplication,QMainWindow,
                             QWidget, QVBoxLayout,
                             QHBoxLayout, QListView,
                             QPushButton,
                             QListWidget,
                             QMessageBox)

class ListWidgetTest(QMainWindow):
    def __init__(self):
        super(ListWidgetTest, self).__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle('QListWidget')
        self.listWidget = QListWidget()
        self.listWidget.addItem('科比')
        self.listWidget.addItem('詹姆斯')
        self.listWidget.addItem('库里')
        self.listWidget.addItem('杜兰特')
        self.listWidget.addItem('哈登')

        self.listWidget.itemClicked.connect(self.clicked)

        self.setCentralWidget(self.listWidget)

    def clicked(self,index):
        QMessageBox.information(self, 'QListWidget',
                                '你选择了：' + self.listWidget.item(self.listWidget.row(index)).text())


if __name__ == '__main__':
    app = QApplication(sys.argv)
    main = ListWidgetTest()
    main.show()
    sys.exit(app.exec_())