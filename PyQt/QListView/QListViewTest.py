'''

QListView控件使用示例

'''

import sys

from PyQt5.QtCore import QStringListModel
from PyQt5.QtWidgets import QApplication, QWidget, QVBoxLayout, QHBoxLayout, QListView, QPushButton, QMessageBox


class ListViewTest(QWidget):
    def __init__(self):
        super(ListViewTest, self).__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle('QListView控件使用示例')
        self.resize(400,300)

        listView = QListView()
        listModel = QStringListModel()
        self.list = ['乔丹','科比','麦迪']

        listModel.setStringList(self.list)
        listView.setModel(listModel)

        listView.clicked.connect(self.clicked)

        vbox = QVBoxLayout()
        vbox.addWidget(listView)
        self.setLayout(vbox)

    def clicked(self,item):
        QMessageBox.information(self,'篮球明星','你选中了：'+self.list[item.row()])

if __name__ == '__main__':
    app = QApplication(sys.argv)
    main = ListViewTest()
    main.show()
    sys.exit(app.exec_())

